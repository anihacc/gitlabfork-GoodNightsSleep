package com.legacy.goodnightsleep.world;

import java.util.Random;

import com.legacy.goodnightsleep.GNSRegistryHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.server.ServerWorld;

public class TeleporterGNS
{
	Random random;

	public static final TeleporterGNS INSTANCE = new TeleporterGNS();

	public TeleporterGNS() //ServerWorld worldIn
	{
		//super(worldIn);

		//this.random = new Random(worldIn.getSeed());
	}

	//placeInPortal
	//@Override
	public boolean teleport(Entity entityIn, float rotationYaw)
	{
		int i = MathHelper.floor(entityIn.posX);
		int k = MathHelper.floor(entityIn.posZ);

		ServerPlayerEntity player = (ServerPlayerEntity) entityIn;

		//PlayerEntity player = (PlayerEntity) entityIn;
		
		if (player.getBedLocation(DimensionType.OVERWORLD) != null && !(entityIn.dimension == GNSRegistryHandler.dreamType() || entityIn.dimension == GNSRegistryHandler.nightmareType()))
		{
			entityIn.setLocationAndAngles((double) player.getBedLocation(DimensionType.OVERWORLD).getX(), entityIn.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, player.getPosition()).getY(), (double) player.getBedLocation(DimensionType.OVERWORLD).getZ(), entityIn.rotationYaw, 0.0F);
		}
		else
		{
			entityIn.setLocationAndAngles((double) i, entityIn.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, player.getPosition()).getY(), (double) k, entityIn.rotationYaw, 0.0F);
		}
        
		entityIn.setMotion(new Vec3d(0.0D, 0.0D, 0.0D));
		return true;
	}

	//placeInExistingPortal
	//@Override
	//public boolean func_222268_a(Entity entityIn, float rotationYaw)
	{
		//return true;
	}

	//@Override
	public boolean makePortal(Entity entityIn)
	{
		return false;
	}
}