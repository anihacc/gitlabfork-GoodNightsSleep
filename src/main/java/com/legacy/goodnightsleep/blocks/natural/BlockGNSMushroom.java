package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockGNSMushroom extends BlockHugeMushroom
{

	public BlockGNSMushroom() 
	{
		super(Material.wood, 0);
		this.setHardness(0.2F);
		this.setStepSound(soundTypeWood);
	}

    @Override
    public Item getItemDropped(int state, Random rand, int fortune)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.hope_mushroom);
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.despair_mushroom);
    	}
    	
    	else
    	{
            return Item.getItemFromBlock(Blocks.red_mushroom);
    	}
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.hope_mushroom);
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.despair_mushroom);
    	}
    	else
    	{
    		return Item.getItemFromBlock(Blocks.red_mushroom);
    	}
    }

}