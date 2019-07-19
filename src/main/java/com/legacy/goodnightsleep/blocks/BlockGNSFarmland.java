package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockGNSFarmland extends Block
{

	public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE_0_7;

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	protected BlockGNSFarmland(Block.Properties builder)
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(MOISTURE, Integer.valueOf(0)));
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos))
		{
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		BlockState iblockstate = worldIn.getBlockState(pos.up());
		return !iblockstate.getMaterial().isSolid() || iblockstate.getBlock() instanceof FenceGateBlock;
	}

	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? BlocksGNS.dream_dirt.getDefaultState() : super.getStateForPlacement(context);
	}

	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return worldIn.getMaxLightLevel();
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
	{
		if (!state.isValidPosition(worldIn, pos))
		{
			turnToDirt(state, worldIn, pos);
		}
		else
		{
			int i = state.get(MOISTURE);
			if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up()))
			{
				if (i > 0)
				{
					worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);
				}
				else if (!hasCrops(worldIn, pos))
				{
					turnToDirt(state, worldIn, pos);
				}
			}
			else if (i < 7)
			{
				worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);
			}
		}
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, BlocksGNS.dream_dirt.getDefaultState(), fallDistance, entityIn))
		{
			turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
		}
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}

	public static void turnToDirt(BlockState state, World worldIn, BlockPos pos)
	{
		worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, BlocksGNS.dream_dirt.getDefaultState(), worldIn, pos));
	}

	private boolean hasCrops(IBlockReader p_176529_0_, BlockPos worldIn)
	{
		BlockState state = p_176529_0_.getBlockState(worldIn.up());
		return state.getBlock() instanceof net.minecraftforge.common.IPlantable && canSustainPlant(state, p_176529_0_, worldIn, Direction.UP, (net.minecraftforge.common.IPlantable) state.getBlock());
	}

	private static boolean hasWater(IWorldReader p_176530_0_, BlockPos worldIn)
	{
		for (BlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(worldIn.add(-4, 0, -4), worldIn.add(4, 1, 4)))
		{
			if (p_176530_0_.getFluidState(blockpos$mutableblockpos).isTagged(FluidTags.WATER))
			{
				return true;
			}
		}
		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(p_176530_0_, worldIn);
	}

	public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune)
	{
		return BlocksGNS.dream_dirt;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(MOISTURE);
	}

	//public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, BlockState state, BlockPos pos, Direction face)
	{
		//return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}