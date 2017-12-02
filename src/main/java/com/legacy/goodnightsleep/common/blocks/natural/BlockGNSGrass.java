package com.legacy.goodnightsleep.common.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockGNSGrass extends Block 
{

	public BlockGNSGrass() 
	{
		super(Material.GRASS);

		this.setHardness(0.6F);
		this.setTickRandomly(true);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if (this == BlocksGNS.dream_grass)
		{
			return Item.getItemFromBlock(BlocksGNS.dream_dirt);
		}
		
		else
		{
			return Item.getItemFromBlock(Blocks.DIRT);
		}
    }
}