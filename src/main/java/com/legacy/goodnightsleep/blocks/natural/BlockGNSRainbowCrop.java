package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockGNSRainbowCrop extends BlockCrops
{
	public BlockGNSRainbowCrop() 
	{
		super();
	}
	
	protected Item getSeed()
    {
        return ItemsGNS.rainbow_seeds;
    }

    protected Item getCrop()
    {
        return ItemsGNS.rainbow_berries;
    }
    
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() instanceof BlockFarmland;
    }
}
