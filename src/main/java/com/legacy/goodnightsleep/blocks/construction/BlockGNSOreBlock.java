package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGNSOreBlock extends Block 
{

	public BlockGNSOreBlock() 
	{
		super(Material.iron);

		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
	}

}