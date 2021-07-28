package com.legacy.goodnightsleep.event;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	@SubscribeEvent
	public void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		Player player = event.getPlayer();

		if ((event.getTarget() instanceof ZombieHorse || event.getTarget() instanceof SkeletonHorse) && player.level.dimension() == GNSDimensions.getKey(false))
		{
			if (!((AbstractHorse) event.getTarget()).isTamed() && player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty())
			{
				event.setResult(Result.ALLOW);
				player.swing(InteractionHand.MAIN_HAND);
				player.startRiding(event.getTarget());
			}
		}
	}

	@SubscribeEvent
	public void onLivingCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if (event.getEntityLiving() instanceof Phantom && event.getEntityLiving().level.dimension() == GNSDimensions.getKey(false) && !GNSConfig.allowNightmarePhantoms)
			event.setResult(Result.DENY);
	}

	@SubscribeEvent
	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if (!(event.getPlayer() instanceof ServerPlayer) || !(event.getWorld() instanceof ServerLevel))
			return;

		ServerPlayer player = (ServerPlayer) event.getPlayer();
		ServerLevel world = (ServerLevel) event.getWorld();
		BlockState state = event.getWorld().getBlockState(event.getPos());

		if (!world.isClientSide && state.getBlock() instanceof BedBlock && (player.level.dimension() == GNSDimensions.getKey(true) || player.level.dimension() == GNSDimensions.getKey(false)))
		{
			player.swing(InteractionHand.MAIN_HAND, true);
			event.setCanceled(true);
			// get the player's bed spawn, otherwise go for the world spawn
			BlockPos pos = player.getRespawnPosition() != null ? player.getRespawnPosition() : world.getSharedSpawnPos();
			GNSTeleporter.changeDimension(Level.OVERWORLD, player, pos);
		}
	}
}