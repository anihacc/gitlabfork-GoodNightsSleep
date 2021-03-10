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

import net.minecraft.block.AbstractBlock.Properties;

public class GNSRainbowCropBlock extends BeetrootBlock
{
	public GNSRainbowCropBlock() 
	{
		super(Properties.copy(Blocks.BEETROOTS));
	}
	
	@Override
	protected IItemProvider getBaseSeedId()
    {
        return GNSItems.rainbow_seeds;
    }

    @Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.getBlock() instanceof FarmlandBlock || state.getBlock() == GNSBlocks.dream_farmland;
	}
}
