package com.legacy.goodnightsleep.common.blocks.natural;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
		return 
			state.getBlock() == BlocksGNS.dream_grass || 
			state.getBlock() == BlocksGNS.nightmare_grass || 
			state.getBlock() == BlocksGNS.dream_dirt ||
			state.getBlock() == Blocks.DIRT ||
			state.getBlock() == Blocks.GRASS;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

}