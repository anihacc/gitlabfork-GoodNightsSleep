package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGNSMushroom extends BlockHugeMushroom
{

	public BlockGNSMushroom() 
	{
		super(Material.WOOD, MapColor.DIRT, null);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.WOOD);
	}

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
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
            return Item.getItemFromBlock(Blocks.RED_MUSHROOM);
    	}
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  new ItemStack(BlocksGNS.hope_mushroom);
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  new ItemStack(BlocksGNS.despair_mushroom);
    	}
    	else
    	{
    		return new ItemStack(Blocks.RED_MUSHROOM);
    	}
    }

}