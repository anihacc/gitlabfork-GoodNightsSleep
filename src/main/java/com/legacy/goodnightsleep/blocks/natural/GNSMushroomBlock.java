package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSFeatures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import net.minecraft.block.AbstractBlock.Properties;

public class GNSMushroomBlock extends MushroomBlock
{
	public GNSMushroomBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();

		if (block != Blocks.MYCELIUM && block != Blocks.PODZOL && block != GNSBlocks.dream_grass_block && block != GNSBlocks.nightmare_grass_block)
		{
			return worldIn.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return true;
	}

	public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand)
	{
		world.removeBlock(pos, false);
		ConfiguredFeature<?, ?> configuredfeature;

		if (this == GNSBlocks.despair_mushroom)
		{
			configuredfeature = GNSFeatures.Configured.BASE_HUGE_DESPAIR_MUSHROOM;
		}
		else
		{
			if (this != GNSBlocks.hope_mushroom)
			{
				world.setBlock(pos, state, 3);
				return false;
			}

			configuredfeature = GNSFeatures.Configured.BASE_HUGE_HOPE_MUSHROOM;
		}

		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos))
		{
			return true;
		}
		else
		{
			world.setBlock(pos, state, 3);
			return false;
		}
	}
}
