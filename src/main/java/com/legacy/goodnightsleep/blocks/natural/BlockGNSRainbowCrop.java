package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.item.Item;

public class BlockGNSRainbowCrop extends BlockCrops
{
	public BlockGNSRainbowCrop() 
	{
		super();
	}
	
	protected Item func_149866_i()
    {
        return ItemsGNS.rainbow_seeds;
    }

    protected Item func_149865_P()
    {
        return ItemsGNS.rainbow_berries;
    }
    
    protected boolean canPlaceBlockOn(Block block)
    {
        return block instanceof BlockFarmland || block == BlocksGNS.dream_farmland;
    }
    
	@Override
	public int getRenderType()
	{
		return CommonProxy.rainbowCropRenderID;
	}
}
