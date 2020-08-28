package com.legacy.goodnightsleep.world;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

public class GNSTeleporter
{
	public static void changeDimension(RegistryKey<World> type, Entity entity, BlockPos pos)
	{
		MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

		if (server == null)
			return;

		RegistryKey<World> overworld = World.OVERWORLD;

		RegistryKey<World> transferDimension = entity.world.getDimensionKey() == type ? overworld : type;
		ServerWorld transferWorld = server.getWorld(transferDimension);

		if (!ForgeHooks.onTravelToDimension(entity, transferDimension))
		{
			return;
		}

		IChunk chunk = transferWorld.getChunk(pos);
		int transferY = /*type == overworld && entity instanceof PlayerEntity && ((PlayerEntity) entity).getBedLocation(overworld) != null ? ((PlayerEntity) entity).getBedLocation(overworld).getY() : */chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1;

		Vector3d endpointPos = new Vector3d(pos.getX() + 0.5, transferY, pos.getZ() + 0.5);

		for (int x = -1; x < 2; ++x)
		{
			for (int z = -1; z < 2; ++z)
			{
				BlockPos newPos = new BlockPos(endpointPos.add(x, -1, z));
				if (transferWorld.getBlockState(new BlockPos(newPos)).getBlock() == Blocks.LAVA || transferWorld.getBlockState(new BlockPos(newPos).up()).getBlock() == Blocks.LAVA)
					transferWorld.setBlockState(newPos, Blocks.GRASS_BLOCK.getDefaultState());
			}
		}

		Entity teleportedEntity = teleportEntity(entity, transferWorld, endpointPos);

		/*if (entity instanceof ServerPlayerEntity)
		{
			teleportEntity(entity)
		}*/

		teleportedEntity.fallDistance = 0.0F;
	}

	private static Entity teleportEntity(Entity entity, ServerWorld transferWorld, Vector3d transferPos)
	{
		if (entity instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			player.teleport(transferWorld, transferPos.x, transferPos.y, transferPos.z, entity.rotationYaw, entity.rotationPitch);
			return player;
		}

		entity.detach();
		//entity.world.getDimensionKey() = transferWorld;
		entity.changeDimension(transferWorld);

		Entity teleportedEntity = entity.getType().create(transferWorld);

		if (teleportedEntity == null)
			return entity;

		teleportedEntity.copyDataFromOld(entity);
		teleportedEntity.setLocationAndAngles(transferPos.x, transferPos.y, transferPos.z, entity.rotationYaw, entity.rotationPitch);
		teleportedEntity.setRotationYawHead(entity.rotationYaw);
		teleportedEntity.setMotion(Vector3d.ZERO);
		transferWorld.addFromAnotherDimension(teleportedEntity);
		entity.remove();

		return teleportedEntity;
	}
}
