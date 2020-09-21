package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BigRedMushroomFeature;

public class FatHopeMushroomFeature extends BigRedMushroomFeature
{
	public FatHopeMushroomFeature(Codec<BigMushroomFeatureConfig> codecIn)
	{
		super(codecIn);
	}

	@Override
	protected void func_227210_a_(IWorld worldIn, Random randomIn, BlockPos posIn, BigMushroomFeatureConfig configIn, int stemHeight, BlockPos.Mutable newPosIn)
	{
		for (int x = -1; x < 2; ++x)
		{
			for (int z = -1; z < 2; ++z)
			{
				for (int i = 0; i < stemHeight; ++i)
				{
					newPosIn.setPos(posIn).move(Direction.UP, i).move(Direction.NORTH, x).move(Direction.EAST, z);

					if (worldIn.getBlockState(newPosIn).canBeReplacedByLogs(worldIn, newPosIn))
					{
						this.setBlockState(worldIn, newPosIn, configIn.field_227273_b_.getBlockState(randomIn, posIn));
					}
				}
			}
		}
	}

	@Override
	protected int func_227211_a_(Random randIn)
	{
		return 20 + randIn.nextInt(10);
	}
}