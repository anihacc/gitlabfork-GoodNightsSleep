package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGNSDirt extends Block 
{

	public BlockGNSDirt()
	{
		super(Material.ground);

		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setStepSound(Block.soundTypeGravel);
		this.setCreativeTab(GNSCreativeTabs.blocks);
		this.setBlockTextureName("goodnightsleep:dream_dirt");
	}

}