package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class RainbowBlock extends Block
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	/*
	 * Corner Types: 0 = none, 1 = starting corner, 2 = ending corner Side Types: 0
	 * = starting, 1 = top, 2 = ending
	 */
	public static final IntegerProperty CORNER_TYPE = IntegerProperty.create("corner_type", 0, 2);
	public static final IntegerProperty SIDE_TYPE = IntegerProperty.create("side_type", 0, 2);
	public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 100);

	public RainbowBlock(Block.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X).with(CORNER_TYPE, 0).with(SIDE_TYPE, 0));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		switch ((Direction.Axis) state.get(AXIS))
		{
		case Z:
			return Z_AABB;
		case X:
		default:
			return X_AABB;
		}
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		int i = getDistance(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i)
		{
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		return stateIn;
	}

	private static int getDistance(BlockState neighbor)
	{
		if (neighbor.getBlock() == GNSBlocks.pot_of_gold)
		{
			return 0;
		}
		else
		{
			return neighbor.getBlock() instanceof RainbowBlock ? neighbor.get(DISTANCE) : 100;
		}
	}

	private static BlockState updateDistance(BlockState state, IWorld worldIn, BlockPos pos)
	{
		int i = 100;

		try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain())
		{
			for (Direction direction : Direction.values())
			{
				blockpos$pooledmutableblockpos.setPos(pos).move(direction);
				i = Math.min(i, getDistance(worldIn.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1)
				{
					break;
				}
			}
		}

		return state.with(DISTANCE, Integer.valueOf(i));
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
	{
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);

		if (state.get(DISTANCE) >= 100)
		{
			worldIn.destroyBlock(pos, false);
		}
	}

	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		return ItemStack.EMPTY;
	}

	public BlockState rotate(BlockState state, Rotation rot)
	{
		switch (rot)
		{
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((Direction.Axis) state.get(AXIS))
			{
			case Z:
				return state.with(AXIS, Direction.Axis.X);
			case X:
				return state.with(AXIS, Direction.Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS, CORNER_TYPE, SIDE_TYPE, DISTANCE);
	}
}