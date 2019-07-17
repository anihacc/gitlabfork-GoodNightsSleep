package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.item.ItemsGNS;

import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockGNSRainbowCrop extends BlockBeetroot
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
    protected IItemProvider getCropsItem()
    {
        return ItemsGNS.rainbow_berries;
    }
    
    @Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.getBlock() instanceof BlockFarmland || state.getBlock() == BlocksGNS.dream_farmland;
	}
}
