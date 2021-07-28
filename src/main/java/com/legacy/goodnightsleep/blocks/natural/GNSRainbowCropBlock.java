package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GNSRainbowCropBlock extends BeetrootBlock
{
	public GNSRainbowCropBlock() 
	{
		super(Properties.copy(Blocks.BEETROOTS));
	}
	
	@Override
	protected ItemLike getBaseSeedId()
    {
        return GNSItems.rainbow_seeds;
    }

    @Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return state.getBlock() instanceof FarmBlock || state.getBlock() == GNSBlocks.dream_farmland;
	}
}
