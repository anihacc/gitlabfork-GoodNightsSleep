package com.legacy.goodnightsleep.common.blocks.natural;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGNSDirt extends Block 
{

	public BlockGNSDirt()
	{
		super(Material.GROUND);

		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.GROUND);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

}