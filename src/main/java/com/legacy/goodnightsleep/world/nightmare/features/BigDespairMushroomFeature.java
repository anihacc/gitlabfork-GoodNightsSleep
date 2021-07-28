package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BigDespairMushroomFeature extends Feature<NoneFeatureConfiguration>
{
	public BigDespairMushroomFeature(Codec<NoneFeatureConfiguration> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = context.random();

		int i = rand.nextInt(3) + 4;
		if (rand.nextInt(12) == 0)
		{
			i *= 2;
		}
		int j = pos.getY();
		if (j >= 1 && j + i + 1 < 256)
		{
			Block block = level.getBlockState(pos.below()).getBlock();
			if (block != Blocks.DIRT && block != Blocks.GRASS_BLOCK && block != GNSBlocks.nightmare_grass_block)
			{
				return false;
			}
			else
			{
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
				for (int k = 0; k <= 1 + i; ++k)
				{
					int l = k <= 3 ? 0 : 3;
					for (int i1 = -l; i1 <= l; ++i1)
					{
						for (int j1 = -l; j1 <= l; ++j1)
						{
							BlockState iblockstate = level.getBlockState(blockpos$mutableblockpos.set(pos).move(i1, k, j1));
							if (!iblockstate.isAir() && !iblockstate.is(BlockTags.LEAVES))
							{
								return false;
							}
						}
					}
				}
				BlockState iblockstate1 = GNSBlocks.despair_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, false);
				for (int l1 = -3; l1 <= 3; ++l1)
				{
					for (int i2 = -3; i2 <= 3; ++i2)
					{
						boolean flag9 = l1 == -3;
						boolean flag = l1 == 3;
						boolean flag1 = i2 == -3;
						boolean flag2 = i2 == 3;
						boolean flag3 = flag9 || flag;
						boolean flag4 = flag1 || flag2;
						if (!flag3 || !flag4)
						{
							blockpos$mutableblockpos.set(pos).move(l1, i, i2);
							if (level.getBlockState(blockpos$mutableblockpos).isSolidRender(level, blockpos$mutableblockpos))
							{
								boolean flag5 = flag9 || flag4 && l1 == -2;
								boolean flag6 = flag || flag4 && l1 == 2;
								boolean flag7 = flag1 || flag3 && i2 == -2;
								boolean flag8 = flag2 || flag3 && i2 == 2;
								this.setBlock(level, blockpos$mutableblockpos, iblockstate1.setValue(HugeMushroomBlock.WEST, Boolean.valueOf(flag5)).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(flag6)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(flag7)).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag8)));
							}
						}
					}
				}
				BlockState iblockstate2 = Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false);
				for (int j2 = 0; j2 < i; ++j2)
				{
					blockpos$mutableblockpos.set(pos).move(Direction.UP, j2);
					if (level.getBlockState(blockpos$mutableblockpos).isSolidRender(level, blockpos$mutableblockpos))
					{
						this.setBlock(level, blockpos$mutableblockpos, iblockstate2);
					}
				}
				return true;
			}
		}
		else
		{
			return false;
		}
	}
}