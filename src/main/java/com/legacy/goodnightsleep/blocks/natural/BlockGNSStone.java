package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockGNSStone extends Block 
{

	public BlockGNSStone()
	{
		super(Material.ROCK);

		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (this == BlocksGNS.delusion_stone)
		{
			return Item.getItemFromBlock(BlocksGNS.delusion_cobblestone);
		}

		else
		{
			return Item.getItemFromBlock(Blocks.COBBLESTONE);
		}
	}
}