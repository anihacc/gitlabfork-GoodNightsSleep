package com.legacy.goodnightsleep.blocks.natural.ores;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockGNSOre extends Block
{

	private Item droppedItem;

	public BlockGNSOre(Item material) 
	{
		super(Material.ROCK);

		this.droppedItem = material;

		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(1) + 1; 
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return this.droppedItem;
    }

}