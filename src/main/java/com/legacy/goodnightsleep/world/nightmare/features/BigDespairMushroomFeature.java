package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BigDespairMushroomFeature extends Feature<NoFeatureConfig>
{
	public BigDespairMushroomFeature(Codec<NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean generate(ISeedReader worldIn, ChunkGenerator generatorIn, Random randIn, BlockPos posIn, NoFeatureConfig configIn)
	{
		int i = randIn.nextInt(3) + 4;
		if (randIn.nextInt(12) == 0)
		{
			i *= 2;
		}
		int j = posIn.getY();
		if (j >= 1 && j + i + 1 < 256)
		{
			Block block = worldIn.getBlockState(posIn.down()).getBlock();
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
							BlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(posIn).move(i1, k, j1));
							if (!iblockstate.isAir(worldIn, blockpos$mutableblockpos) && !iblockstate.isIn(BlockTags.LEAVES))
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
							blockpos$mutableblockpos.setPos(posIn).move(l1, i, i2);
							if (worldIn.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(worldIn, blockpos$mutableblockpos))
							{
								boolean flag5 = flag9 || flag4 && l1 == -2;
								boolean flag6 = flag || flag4 && l1 == 2;
								boolean flag7 = flag1 || flag3 && i2 == -2;
								boolean flag8 = flag2 || flag3 && i2 == 2;
								this.setBlockState(worldIn, blockpos$mutableblockpos, iblockstate1.with(HugeMushroomBlock.WEST, Boolean.valueOf(flag5)).with(HugeMushroomBlock.EAST, Boolean.valueOf(flag6)).with(HugeMushroomBlock.NORTH, Boolean.valueOf(flag7)).with(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag8)));
							}
						}
					}
				}
				BlockState iblockstate2 = Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
				for (int j2 = 0; j2 < i; ++j2)
				{
					blockpos$mutableblockpos.setPos(posIn).move(Direction.UP, j2);
					if (worldIn.getBlockState(blockpos$mutableblockpos).canBeReplacedByLeaves(worldIn, blockpos$mutableblockpos))
					{
						this.setBlockState(worldIn, blockpos$mutableblockpos, iblockstate2);
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