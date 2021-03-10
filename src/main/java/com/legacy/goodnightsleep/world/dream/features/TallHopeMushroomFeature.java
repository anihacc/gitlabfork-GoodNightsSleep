package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BigRedMushroomFeature;

public class TallHopeMushroomFeature extends BigRedMushroomFeature
{
	public TallHopeMushroomFeature(Codec<BigMushroomFeatureConfig> codecIn)
	{
		super(codecIn);
	}

	@Override
	protected void placeTrunk(IWorld worldIn, Random randomIn, BlockPos posIn, BigMushroomFeatureConfig configIn, int stemHeight, BlockPos.Mutable newPosIn)
	{
		/*if (stemHeight >= 20 && configIn.foliageRadius > 3)
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
							this.setBlock(worldIn, newPosIn, configIn.stemProvider.getBlockState(randomIn, posIn));
						}
					}
				}
			}
		}
		else*/
		{
			for (int i = 0; i < stemHeight; ++i)
			{
				newPosIn.set(posIn).move(Direction.UP, i);

				if (worldIn.getBlockState(newPosIn).canBeReplacedByLogs(worldIn, newPosIn))
				{
					this.setBlock(worldIn, newPosIn, configIn.stemProvider.getState(randomIn, posIn));
				}
			}
		}
	}

	@Override
	protected int getTreeHeight(Random randIn)
	{
		return 10 + randIn.nextInt(15);
	}
}