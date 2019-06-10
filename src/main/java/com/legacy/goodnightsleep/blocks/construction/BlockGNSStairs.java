package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockGNSStairs extends BlockStairs 
{

	public BlockGNSStairs(Block block)
	{
		super(block, 0);
		this.setLightOpacity(0);	
	}

}