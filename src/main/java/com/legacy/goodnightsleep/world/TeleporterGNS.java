package com.legacy.goodnightsleep.world;

import java.util.Random;

import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap.Type;

public class TeleporterGNS extends Teleporter
{
	Random random;

	public TeleporterGNS(WorldServer worldIn)
	{
		super(worldIn);

		this.random = new Random(worldIn.getSeed());
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw)
	{
		int i = MathHelper.floor(entityIn.posX);
		int k = MathHelper.floor(entityIn.posZ);

		EntityPlayer player = (EntityPlayer) entityIn;
		
		if (player.getBedLocation(DimensionType.OVERWORLD) != null && !(entityIn.dimension == DreamWorldManager.getDimensionType() || entityIn.dimension == NightmareWorldManager.getDimensionType()))
		{
			entityIn.setLocationAndAngles((double) player.getBedLocation(DimensionType.OVERWORLD).getX(), this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, player.getBedLocation(DimensionType.OVERWORLD)).getY(), (double) player.getBedLocation(DimensionType.OVERWORLD).getZ(), entityIn.rotationYaw, 0.0F);
		}
		else
		{
			entityIn.setLocationAndAngles((double) i, this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(player.getPosition())).getY(), (double) k, entityIn.rotationYaw, 0.0F);
		}
        
		entityIn.motionX = 0.0D;
		entityIn.motionY = 0.0D;
		entityIn.motionZ = 0.0D;
	}
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw)
	{
		return false;
	}

	@Override
	public boolean makePortal(Entity entityIn)
	{
		return false;
	}
}