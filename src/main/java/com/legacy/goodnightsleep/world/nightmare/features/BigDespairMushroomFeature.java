package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BigDespairMushroomFeature extends Feature<NoFeatureConfig>
{

	public BigDespairMushroomFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
	{
		int i = p_212245_3_.nextInt(3) + 4;
		if (p_212245_3_.nextInt(12) == 0)
		{
			i *= 2;
		}
		int j = p_212245_4_.getY();
		if (j >= 1 && j + i + 1 < 256)
		{
			Block block = p_212245_1_.getBlockState(p_212245_4_.down()).getBlock();
			if (block != Blocks.DIRT && block != Blocks.GRASS_BLOCK && block != GNSBlocks.nightmare_grass_block)
			{
				return false;
			}
			else
			{
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
				for (int k = 0; k <= 1 + i; ++k)
				{
					int l = k <= 3 ? 0 : 3;
					for (int i1 = -l; i1 <= l; ++i1)
					{
						for (int j1 = -l; j1 <= l; ++j1)
						{
							BlockState iblockstate = p_212245_1_.getBlockState(blockpos$mutableblockpos.setPos(p_212245_4_).move(i1, k, j1));
							if (!iblockstate.isAir(p_212245_1_, blockpos$mutableblockpos) && !iblockstate.isIn(BlockTags.LEAVES))
							{
								return false;
							}
						}
					}
				}
				BlockState iblockstate1 = GNSBlocks.despair_mushroom_block.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(true)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
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
							blockpos$mutableblockpos.setPos(p_212245_4_).move(l1, i, i2);
							if (p_212245_1_.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(p_212245_1_, blockpos$mutableblockpos))
							{
								boolean flag5 = flag9 || flag4 && l1 == -2;
								boolean flag6 = flag || flag4 && l1 == 2;
								boolean flag7 = flag1 || flag3 && i2 == -2;
								boolean flag8 = flag2 || flag3 && i2 == 2;
								this.setBlockState(p_212245_1_, blockpos$mutableblockpos, iblockstate1.with(HugeMushroomBlock.WEST, Boolean.valueOf(flag5)).with(HugeMushroomBlock.EAST, Boolean.valueOf(flag6)).with(HugeMushroomBlock.NORTH, Boolean.valueOf(flag7)).with(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag8)));
							}
						}
					}
				}
				BlockState iblockstate2 = Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
				for (int j2 = 0; j2 < i; ++j2)
				{
					blockpos$mutableblockpos.setPos(p_212245_4_).move(Direction.UP, j2);
					if (p_212245_1_.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(p_212245_1_, blockpos$mutableblockpos))
					{
						this.setBlockState(p_212245_1_, blockpos$mutableblockpos, iblockstate2);
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