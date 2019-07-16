package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockGNSMushroom extends BlockHugeMushroom
{

	public BlockGNSMushroom(Block block) 
	{
		super(block, Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(0.2F).sound(SoundType.WOOD));
	}

    /*@Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  BlocksGNS.hope_mushroom.asItem();
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  BlocksGNS.despair_mushroom.asItem();
    	}
    	
    	else
    	{
            return Blocks.RED_MUSHROOM.asItem();
    	}
    }*/

    /*@Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, IBlockState state)
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
    }*/

}