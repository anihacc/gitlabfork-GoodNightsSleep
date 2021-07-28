package com.legacy.goodnightsleep.capabillity;

import com.legacy.goodnightsleep.capabillity.util.IDreamPlayer;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class DreamPlayer implements IDreamPlayer
{
	@CapabilityInject(IDreamPlayer.class)
	public static Capability<IDreamPlayer> GNS_PLAYER = null;
	private Player player;
	private long enteredGameTime = 0L;

	public DreamPlayer()
	{
	}

	public DreamPlayer(Player player)
	{
		super();
		this.player = player;
	}

	public static IDreamPlayer get(Player player)
	{
		return player.getCapability(GNS_PLAYER).orElse(null);
	}

	@Override
	public void serverTick()
	{
		if (player != null && player instanceof ServerPlayer && !player.getCommandSenderWorld().isClientSide())
		{
			long worldTime = player.level.getGameTime() - this.getEnteredDreamTime();

			if (worldTime > 25000L && (player.level.dimension() == GNSDimensions.getDimensionKeys(true) || player.level.dimension() == GNSDimensions.getDimensionKeys(false)))
			{
				ServerPlayer playerMP = (ServerPlayer) player;

				if (playerMP.level instanceof ServerLevel)
				{
					// try for bed spawn, otherwise go to the world spawn
					BlockPos pos = playerMP.getRespawnPosition() != null ? playerMP.getRespawnPosition() : ((ServerLevel) playerMP.level).getSharedSpawnPos();
					GNSTeleporter.changeDimension(Level.OVERWORLD, playerMP, pos);
				}
				/*playerMP.getBedLocation(DimensionType.OVERWORLD) != null ? playerMP.getBedLocation(DimensionType.OVERWORLD) : playerMP.world.getSpawnPoint();*/
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientTick()
	{
		/*if (player != null && player instanceof net.minecraft.client.entity.player.ClientPlayerEntity && player.getEntityWorld().isRemote())
		{
		}*/
	}

	@Override
	public void writeAdditional(CompoundTag compound)
	{
		compound.putLong("EnteredDreamTime", this.getEnteredDreamTime());
	}

	@Override
	public void read(CompoundTag compound)
	{
		this.setEnteredDreamTime(compound.getLong("EnteredDreamTime"));
	}

	@Override
	public Player getPlayer()
	{
		return this.player;
	}

	@Override
	public long getEnteredDreamTime()
	{
		return this.enteredGameTime;
	}

	@Override
	public void setEnteredDreamTime(long timeIn)
	{
		this.enteredGameTime = timeIn;
	}

	@Override
	public void onDeath()
	{
	}
}
