package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockGNSLeaves extends BlockLeaves
{

	public BlockGNSLeaves(Block.Properties builder)
	{
		super(builder);
	}

	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		int i = getDistance(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i)
		{
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}
		return stateIn;
	}

	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.valueOf(true)), context.getWorld(), context.getPos());
	}

	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
	{
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
	}

	private static IBlockState updateDistance(IBlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_)
	{
		int i = 7;
		try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain())
		{
			for (EnumFacing enumfacing : EnumFacing.values())
			{
				blockpos$pooledmutableblockpos.setPos(p_208493_2_).move(enumfacing);
				i = Math.min(i, getDistance(p_208493_1_.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1)
				{
					break;
				}
			}
		}
		return p_208493_0_.with(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistance(IBlockState neighbor)
	{
		if (neighbor.getBlock() instanceof BlockLog || BlockTags.LOGS.contains(neighbor.getBlock()))
		{
			return 0;
		}
		else
		{
			return neighbor.getBlock() instanceof BlockLeaves ? neighbor.get(DISTANCE) : 7;
		}
	}

	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	{
		Block block = state.getBlock();

		if (block == BlocksGNS.candy_leaves)
		{
			return BlocksGNS.candy_sapling;
		}
		if (block == BlocksGNS.diamond_leaves && worldIn.rand.nextInt(100) == 0)
		{
			return Items.DIAMOND;
		}
		else
		{
			return BlocksGNS.dream_sapling;
		}
	}
}