package com.legacy.goodnightsleep.blocks.construction;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGNSPlank extends Block
{

	public BlockGNSPlank()
	{
		super(Material.WOOD);

		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setHarvestLevel("axe", 0);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

}