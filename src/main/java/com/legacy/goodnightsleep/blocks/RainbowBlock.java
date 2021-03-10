package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RainbowBlock extends Block
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

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
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X).setValue(CORNER_TYPE, 0).setValue(SIDE_TYPE, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		switch ((Direction.Axis) state.getValue(AXIS))
		{
		case Z:
			return Z_AABB;
		case X:
		default:
			return X_AABB;
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		int i = getDistance(facingState) + 1;
		if (i != 1 || stateIn.getValue(DISTANCE) != i)
		{
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
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
			return neighbor.getBlock() instanceof RainbowBlock ? neighbor.getValue(DISTANCE) : 100;
		}
	}

	private static BlockState updateDistance(BlockState state, IWorld worldIn, BlockPos pos)
	{
		int i = 100;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (Direction direction : Direction.values())
		{
			blockpos$mutable.set(pos).move(direction);
			i = Math.min(i, getDistance(worldIn.getBlockState(blockpos$mutable)) + 1);
			if (i == 1)
			{
				break;
			}
		}

		return state.setValue(DISTANCE, Integer.valueOf(i));
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		worldIn.setBlock(pos, updateDistance(state, worldIn, pos), 3);

		if (state.getValue(DISTANCE) >= 100)
		{
			worldIn.destroyBlock(pos, false);
		}
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		return ItemStack.EMPTY;
	}

	public BlockState rotate(BlockState state, Rotation rot)
	{
		switch (rot)
		{
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((Direction.Axis) state.getValue(AXIS))
			{
			case Z:
				return state.setValue(AXIS, Direction.Axis.X);
			case X:
				return state.setValue(AXIS, Direction.Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS, CORNER_TYPE, SIDE_TYPE, DISTANCE);
	}
}