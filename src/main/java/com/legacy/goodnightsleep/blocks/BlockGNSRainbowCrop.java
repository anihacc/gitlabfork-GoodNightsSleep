package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.item.ItemsGNS;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockGNSRainbowCrop extends BeetrootBlock
{
	public BlockGNSRainbowCrop() 
	{
		super(Properties.from(Blocks.BEETROOTS));
	}
	
	@Override
	protected IItemProvider getSeedsItem()
    {
        return ItemsGNS.rainbow_seeds;
    }

    @Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.getBlock() instanceof FarmlandBlock || state.getBlock() == BlocksGNS.dream_farmland;
	}
}
