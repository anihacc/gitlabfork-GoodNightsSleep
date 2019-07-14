package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGNSStone extends Block
{

	public BlockGNSStone(Block.Properties builder)
	{
		super(builder);
	}

	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	{
		return BlocksGNS.delusion_cobblestone;
	}
}