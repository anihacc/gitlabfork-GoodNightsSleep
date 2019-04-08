package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGNSCobblestone extends Block 
{

	public BlockGNSCobblestone() 
	{
		super(Material.ROCK);

		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
	}
}