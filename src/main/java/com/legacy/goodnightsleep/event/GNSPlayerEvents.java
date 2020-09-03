package com.legacy.goodnightsleep.event;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.capabillity.util.CapabilityProvider;
import com.legacy.goodnightsleep.capabillity.util.IDreamPlayer;
import com.legacy.goodnightsleep.network.PacketHandler;
import com.legacy.goodnightsleep.network.SendEnteredTimePacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSPlayerEvents
{
	@SubscribeEvent
	public void onCapabilityAttached(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity && DreamPlayer.get((PlayerEntity) event.getObject()) == null)
			event.addCapability(GoodNightSleep.locate("player_capability"), new CapabilityProvider(new DreamPlayer((PlayerEntity) event.getObject())));
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && DreamPlayer.get((PlayerEntity) event.getEntity()) != null)
			DreamPlayer.get((PlayerEntity) event.getEntity()).onDeath();
	}

	@SubscribeEvent
	public void onPlayerCloned(Clone event)
	{
		IDreamPlayer original = DreamPlayer.get(event.getOriginal());
		IDreamPlayer clone = DreamPlayer.get(event.getPlayer());
		CompoundNBT compound = new CompoundNBT();

		if (original != null && clone != null)
		{
			if (event.isWasDeath())
			{
				if (!clone.getPlayer().world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY))
				{
				}

				original.writeAdditional(compound);
				clone.read(compound);
			}
			else
			{
				if (event.getPlayer() instanceof ServerPlayerEntity)
					PacketHandler.sendTo(new SendEnteredTimePacket(event.getPlayer().world.getGameTime()), (ServerPlayerEntity) event.getPlayer());

				original.writeAdditional(compound);
				clone.read(compound);
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && DreamPlayer.get((PlayerEntity) event.getEntityLiving()) != null)
			DreamPlayer.get((PlayerEntity) event.getEntityLiving()).serverTick();
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof ServerPlayerEntity && DreamPlayer.get((ServerPlayerEntity) event.getEntity()) != null)
			PacketHandler.sendTo(new SendEnteredTimePacket(DreamPlayer.get((ServerPlayerEntity) event.getEntity()).getEnteredDreamTime()), (ServerPlayerEntity) event.getEntity());
	}
}
