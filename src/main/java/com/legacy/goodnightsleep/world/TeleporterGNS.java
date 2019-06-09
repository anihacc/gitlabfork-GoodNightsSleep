package com.legacy.goodnightsleep.world;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

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
		int i = MathHelper.floor_double(entityIn.posX);
		int k = MathHelper.floor_double(entityIn.posZ);
        entityIn.setLocationAndAngles((double)i, entityIn.worldObj.getTopSolidOrLiquidBlock(new BlockPos(i, 0, k)).getY(), (double)k, entityIn.rotationYaw, 0.0F);
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