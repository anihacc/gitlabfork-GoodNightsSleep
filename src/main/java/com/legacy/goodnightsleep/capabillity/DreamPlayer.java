package com.legacy.goodnightsleep.capabillity;

import com.legacy.goodnightsleep.capabillity.util.IDreamPlayer;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class DreamPlayer implements IDreamPlayer
{
	@CapabilityInject(IDreamPlayer.class)
	public static Capability<IDreamPlayer> GNS_PLAYER = null;
	private PlayerEntity player;
	private long enteredGameTime = 0L;

	public DreamPlayer()
	{
	}

	public DreamPlayer(PlayerEntity player)
	{
		super();
		this.player = player;
	}

	public static IDreamPlayer get(PlayerEntity player)
	{
		return player.getCapability(GNS_PLAYER).orElse(null);
	}

	@Override
	public void serverTick()
	{
		if (player != null && player instanceof ServerPlayerEntity && !player.getEntityWorld().isRemote())
		{
			long worldTime = player.world.getGameTime() - this.getEnteredDreamTime();

			if (worldTime > 25000L && player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(true))
			{

				// System.out.println("time to leave");
				ServerPlayerEntity playerMP = (ServerPlayerEntity) player;

				if (playerMP.world instanceof ServerWorld)
				{
					// try for bed spawn, otherwise go to the world spawn
					BlockPos pos = playerMP.func_241140_K_() != null ? playerMP.func_241140_K_() : ((ServerWorld) playerMP.world).getSpawnPoint();
					GNSTeleporter.changeDimension(World.OVERWORLD, playerMP, pos);
				}
				/*playerMP.getBedLocation(DimensionType.OVERWORLD) != null ? playerMP.getBedLocation(DimensionType.OVERWORLD) : playerMP.world.getSpawnPoint();*/
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientTick()
	{
		if (player != null && player instanceof net.minecraft.client.entity.player.ClientPlayerEntity && player.getEntityWorld().isRemote())
		{
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		compound.putLong("EnteredDreamTime", this.getEnteredDreamTime());
	}

	@Override
	public void read(CompoundNBT compound)
	{
		this.setEnteredDreamTime(compound.getLong("EnteredDreamTime"));
	}

	@Override
	public PlayerEntity getPlayer()
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
