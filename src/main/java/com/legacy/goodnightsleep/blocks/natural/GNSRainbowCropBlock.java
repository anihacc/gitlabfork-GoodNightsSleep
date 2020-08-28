package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GNSRainbowCropBlock extends BeetrootBlock
{
	public GNSRainbowCropBlock() 
	{
		super(Properties.from(Blocks.BEETROOTS));
	}
	
	@Override
	protected IItemProvider getSeedsItem()
    {
        return GNSItems.rainbow_seeds;
    }

    @Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.getBlock() instanceof FarmlandBlock || state.getBlock() == GNSBlocks.dream_farmland;
	}
}
