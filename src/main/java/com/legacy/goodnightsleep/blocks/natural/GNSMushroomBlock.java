package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.blocks.GNSBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class GNSMushroomBlock extends MushroomBlock
{
	public GNSMushroomBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();

		if (block != Blocks.MYCELIUM && block != Blocks.PODZOL && block != GNSBlocks.dream_grass_block && block != GNSBlocks.nightmare_grass_block)
		{
			return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return false;
	}
}
