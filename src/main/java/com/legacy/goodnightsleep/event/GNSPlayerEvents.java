package com.legacy.goodnightsleep.event;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.capabillity.util.CapabilityProvider;
import com.legacy.goodnightsleep.capabillity.util.IDreamPlayer;
import com.legacy.goodnightsleep.network.PacketHandler;
import com.legacy.goodnightsleep.network.SendEnteredTimePacket;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSPlayerEvents
{
	@SubscribeEvent
	public void onCapabilityAttached(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof Player && !event.getObject().getCapability(DreamPlayer.GNS_PLAYER).isPresent())
			event.addCapability(GoodNightSleep.locate("player_capability"), new CapabilityProvider(new DreamPlayer((Player) event.getObject())));
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof Player)
			event.getEntity().getCapability(DreamPlayer.GNS_PLAYER).ifPresent(c -> c.onDeath());
	}

	@SubscribeEvent
	public void onPlayerCloned(Clone event)
	{
		if (!event.getEntity().getCapability(DreamPlayer.GNS_PLAYER).isPresent())
			return;

		IDreamPlayer original = DreamPlayer.get(event.getOriginal());
		IDreamPlayer clone = DreamPlayer.get(event.getPlayer());
		CompoundTag compound = new CompoundTag();

		if (original != null && clone != null)
		{
			if (event.isWasDeath())
			{
				if (!clone.getPlayer().level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY))
				{
				}

				original.writeAdditional(compound);
				clone.read(compound);
			}
			else
			{
				if (event.getPlayer() instanceof ServerPlayer)
					PacketHandler.sendTo(new SendEnteredTimePacket(event.getPlayer().level.getGameTime()), (ServerPlayer) event.getPlayer());

				original.writeAdditional(compound);
				clone.read(compound);
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntity() instanceof Player)
			event.getEntity().getCapability(DreamPlayer.GNS_PLAYER).ifPresent(c -> c.serverTick());
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof ServerPlayer)
			event.getEntity().getCapability(DreamPlayer.GNS_PLAYER).ifPresent(c -> PacketHandler.sendTo(new SendEnteredTimePacket(c.getEnteredDreamTime()), (ServerPlayer) event.getEntity()));

	}
	
	@SubscribeEvent
	public void onEntityChangeDimension(PlayerChangedDimensionEvent event)
	{
		if (event.getEntity() instanceof ServerPlayer)
			event.getEntity().getCapability(DreamPlayer.GNS_PLAYER).ifPresent(c -> PacketHandler.sendTo(new SendEnteredTimePacket(c.getEnteredDreamTime()), (ServerPlayer) event.getEntity()));

	}
}
