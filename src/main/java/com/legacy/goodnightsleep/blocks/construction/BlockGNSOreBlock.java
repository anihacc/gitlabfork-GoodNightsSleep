package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGNSOreBlock extends Block 
{

	public BlockGNSOreBlock(SoundType soundType) 
	{
		super(Material.IRON);

		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setSoundType(soundType);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
	{
		return true;
	}

}