package com.legacy.goodnightsleep.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fmllegacy.LogicalSidedProvider;

public class GNSTeleporter
{
	public static void changeDimension(ResourceKey<Level> type, Entity entity, BlockPos pos)
	{
		MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

		if (server == null)
			return;

		ResourceKey<Level> transferDimension = entity.level.dimension() == type ? Level.OVERWORLD : type;
		ServerLevel transferWorld = server.getLevel(transferDimension);

		if (!ForgeHooks.onTravelToDimension(entity, transferDimension))
			return;

		Entity teleportedEntity = teleportEntity(entity, transferWorld, pos);

		teleportedEntity.fallDistance = 0.0F;
	}

	private static Entity teleportEntity(Entity entity, ServerLevel transferWorld, BlockPos pos)
	{
		if (entity instanceof ServerPlayer)
		{
			ServerPlayer player = (ServerPlayer) entity;

			player.teleportTo(transferWorld, pos.getX(), pos.getY(), pos.getZ(), entity.getYRot(), entity.getXRot());

			if (transferWorld.dimension() != Level.OVERWORLD)
			{
				int maxY = entity.level.getHeight(Types.MOTION_BLOCKING, pos.getX(), pos.getZ());
				//int transferY = transferWorld.getDimensionKey() == World.OVERWORLD && entity instanceof ServerPlayerEntity && ((ServerPlayerEntity) entity).getRespawnPosition(/*overworld*/) != null ? ((ServerPlayerEntity) entity).getRespawnPosition(/*overworld*/).getY() : maxY;
	
				BlockPos endpointPos = new BlockPos(pos.getX() + 0.5, maxY, pos.getZ() + 0.5);
	
				for (int x = -1; x < 2; ++x)
				{
					for (int z = -1; z < 2; ++z)
					{
						BlockPos newPos = new BlockPos(endpointPos.offset(x, -1, z));
						if (transferWorld.getBlockState(new BlockPos(newPos)).getBlock() == Blocks.LAVA || transferWorld.getBlockState(new BlockPos(newPos).above()).getBlock() == Blocks.LAVA)
							transferWorld.setBlockAndUpdate(newPos, Blocks.GRASS_BLOCK.defaultBlockState());
					}
				}
	
				player.moveTo(endpointPos.getX(), endpointPos.getY(), endpointPos.getZ(), entity.getYRot(), entity.getXRot());
			}
			
			return player;
		}

		entity.unRide();
		entity.changeDimension(transferWorld);

		Entity teleportedEntity = entity.getType().create(transferWorld);

		if (teleportedEntity == null)
			return entity;

		teleportedEntity.restoreFrom(entity);
		teleportedEntity.moveTo(pos.getX(), pos.getY(), pos.getZ(), entity.getYRot(), entity.getXRot());
		teleportedEntity.setYHeadRot(entity.getYRot());
		teleportedEntity.setDeltaMovement(Vec3.ZERO);
		transferWorld.addDuringTeleport(teleportedEntity);
		entity.remove(Entity.RemovalReason.CHANGED_DIMENSION);

		return teleportedEntity;
	}
}
