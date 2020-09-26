package com.legacy.goodnightsleep.event;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	@SubscribeEvent
	public void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		PlayerEntity player = event.getPlayer();

		if ((event.getTarget() instanceof ZombieHorseEntity || event.getTarget() instanceof SkeletonHorseEntity) && player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false))
		{
			if (!((AbstractHorseEntity) event.getTarget()).isTame() && player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty())
			{
				event.setResult(Result.ALLOW);
				player.swingArm(Hand.MAIN_HAND);
				player.startRiding(event.getTarget());
			}
		}
	}

	@SubscribeEvent
	public void onLivingCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if (event.getEntityLiving() instanceof PhantomEntity && event.getEntityLiving().world.getDimensionKey() == GNSDimensions.getDimensionKeys(false) && !GNSConfig.allowNightmarePhantoms)
			event.setResult(Result.DENY);
	}

	@SubscribeEvent
	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if (!(event.getPlayer() instanceof ServerPlayerEntity) || !(event.getWorld() instanceof ServerWorld))
			return;

		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		ServerWorld world = (ServerWorld) event.getWorld();
		BlockState state = event.getWorld().getBlockState(event.getPos());

		if (!world.isRemote && state.getBlock() instanceof BedBlock && (player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(true) || player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false)))
		{
			player.swing(Hand.MAIN_HAND, true);
			event.setCanceled(true);
			// get the player's bed spawn, otherwise go for the world spawn
			BlockPos pos = player.func_241140_K_() != null ? player.func_241140_K_() : world.getSpawnPoint();
			GNSTeleporter.changeDimension(World.OVERWORLD, player, pos);
		}
	}
}