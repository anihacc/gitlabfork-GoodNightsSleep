package com.legacy.goodnightsleep.world;

import java.util.Random;

import net.minecraft.entity.Entity;
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
	public void placeInPortal(Entity entityIn, double x, double y, double z, float rotationYaw)
	{
		int i = MathHelper.floor_double(entityIn.posX);
		int k = MathHelper.floor_double(entityIn.posZ);
        entityIn.setLocationAndAngles((int)i, entityIn.worldObj.getTopSolidOrLiquidBlock(i, k), (double)k, entityIn.rotationYaw, 0.0F);
		entityIn.motionX = 0.0D;
		entityIn.motionY = 0.0D;
		entityIn.motionZ = 0.0D;
	}

	@Override
    public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_)
	{
		return false;
	}

	@Override
	public boolean makePortal(Entity entityIn)
	{
		return false;
	}
}