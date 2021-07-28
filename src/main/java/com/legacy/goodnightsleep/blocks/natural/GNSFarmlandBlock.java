package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.ForgeHooks;

public class GNSFarmlandBlock extends Block
{

	public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;

	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public GNSFarmlandBlock(Block.Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos))
		{
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		}
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		BlockState iblockstate = worldIn.getBlockState(pos.above());
		return !iblockstate.getMaterial().isSolid() || iblockstate.getBlock() instanceof FenceGateBlock;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? GNSBlocks.dream_dirt.defaultBlockState() : super.getStateForPlacement(context);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return worldIn.getMaxLightLevel();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random)
	{
		if (!state.canSurvive(worldIn, pos))
		{
			turnToDirt(state, worldIn, pos);
		}
		else
		{
			int i = state.getValue(MOISTURE);
			if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.above()))
			{
				if (i > 0)
				{
					worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
				}
				else if (!hasCrops(worldIn, pos))
				{
					turnToDirt(state, worldIn, pos);
				}
			}
			else if (i < 7)
			{
				worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);
			}
		}
	}

	@Override
	public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance)
	{
		if (!worldIn.isClientSide && ForgeHooks.onFarmlandTrample(worldIn, pos, GNSBlocks.dream_dirt.defaultBlockState(), fallDistance, entityIn))
			turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
		
		super.fallOn(worldIn, state, pos, entityIn, fallDistance);
	}

	public static void turnToDirt(BlockState state, Level worldIn, BlockPos pos)
	{
		worldIn.setBlockAndUpdate(pos, pushEntitiesUp(state, GNSBlocks.dream_dirt.defaultBlockState(), worldIn, pos));
	}

	private boolean hasCrops(BlockGetter p_176529_0_, BlockPos worldIn)
	{
		BlockState state = p_176529_0_.getBlockState(worldIn.above());
		return state.getBlock() instanceof net.minecraftforge.common.IPlantable && canSustainPlant(state, p_176529_0_, worldIn, Direction.UP, (net.minecraftforge.common.IPlantable) state.getBlock());
	}

	private static boolean hasWater(LevelReader p_176530_0_, BlockPos worldIn)
	{
		for (BlockPos blockpos$mutableblockpos : BlockPos.betweenClosed(worldIn.offset(-4, 0, -4), worldIn.offset(4, 1, 4)))
		{
			if (p_176530_0_.getFluidState(blockpos$mutableblockpos).is(FluidTags.WATER))
			{
				return true;
			}
		}
		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(p_176530_0_, worldIn);
	}

	public ItemLike getItemDropped(BlockState state, Level worldIn, BlockPos pos, int fortune)
	{
		return GNSBlocks.dream_dirt;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(MOISTURE);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type)
	{
		return false;
	}
}