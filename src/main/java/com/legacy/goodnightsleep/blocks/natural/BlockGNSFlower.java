package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSFlower extends BlockBush 
{

	public BlockGNSFlower()
	{
		super();

		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
	protected boolean canSustainBush(IBlockState state)
	{
		if (this == BlocksGNS.hope_mushroom || this == BlocksGNS.despair_mushroom)
		{
			return state.getBlock() == Blocks.STONE;
		}
		return state.getBlock() == BlocksGNS.dream_grass || state.getBlock() == BlocksGNS.nightmare_grass || state.getBlock() == BlocksGNS.dream_dirt || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS;
	}
	
	@Override
	 public Item getItemDropped(IBlockState state, Random rand, int fortune)
	 {
		 if (this == BlocksGNS.lolipop_bush)
		 {
			 return ItemsGNS.lolipop;
		 }
		 else
		 {
			 return super.getItemDropped(state, rand, fortune);
		 }
	 }

	@Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

}