package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class GoldPotBlock extends Block
{
	private static final VoxelShape INSIDE = makeCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.or(makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

	public GoldPotBlock(Block.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState());
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	public boolean isSolid(BlockState state)
	{
		return false;
	}

	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return INSIDE;
	}

	@SuppressWarnings("deprecation")
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		int xPos = pos.getX();
		int yPos = pos.getY();
		int zPos = pos.getZ();

		byte modifyY = 0;
		byte modifyZ = 0;

		int var10;

		for (var10 = modifyY + 1; var10 < 20 && worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + modifyZ)).isAir(); ++var10)
		{
			// Beginning Line
			worldIn.setBlockState(new BlockPos(xPos, yPos + var10, zPos), GNSBlocks.rainbow.getDefaultState().with(RainbowBlock.AXIS, Axis.Z).with(RainbowBlock.SIDE_TYPE, 0), 2); // 0
		}

		if (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + modifyZ)).isAir())
		{
			worldIn.setBlockState(new BlockPos(xPos, yPos + var10, zPos), GNSBlocks.rainbow.getDefaultState().with(RainbowBlock.AXIS, Axis.Z).with(RainbowBlock.CORNER_TYPE, 1).with(RainbowBlock.SIDE_TYPE, 0), 2); // 2

			int var11;
			for (var11 = modifyZ + 1; var11 < 40 && worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir(); ++var11)
			{
				worldIn.setBlockState(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.getDefaultState().with(RainbowBlock.AXIS, Axis.Z).with(RainbowBlock.SIDE_TYPE, 1), 2); // 1
			}

			if (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir())
			{
				// 3 End Corner
				worldIn.setBlockState(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.getDefaultState().with(RainbowBlock.AXIS, Axis.Z).with(RainbowBlock.CORNER_TYPE, 2), 2);
				--var10;

				while (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir())
				{
					// End Line
					worldIn.setBlockState(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.getDefaultState().with(RainbowBlock.AXIS, Axis.Z).with(RainbowBlock.SIDE_TYPE, 2), 2); // 4
					--var10;
				}
			}
		}

		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}