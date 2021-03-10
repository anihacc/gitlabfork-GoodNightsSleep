package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
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
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

	public GoldPotBlock(Block.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return INSIDE;
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (!worldIn.isClientSide)
			player.addEffect(new EffectInstance(Effects.REGENERATION, 180, 0, true, true));

		return ActionResultType.SUCCESS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
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
			worldIn.setBlock(new BlockPos(xPos, yPos + var10, zPos), GNSBlocks.rainbow.defaultBlockState().setValue(RainbowBlock.AXIS, Axis.Z).setValue(RainbowBlock.SIDE_TYPE, 0), 2); // 0
		}

		if (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + modifyZ)).isAir())
		{
			worldIn.setBlock(new BlockPos(xPos, yPos + var10, zPos), GNSBlocks.rainbow.defaultBlockState().setValue(RainbowBlock.AXIS, Axis.Z).setValue(RainbowBlock.CORNER_TYPE, 1).setValue(RainbowBlock.SIDE_TYPE, 0), 2); // 2

			int var11;
			for (var11 = modifyZ + 1; var11 < 40 && worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir(); ++var11)
			{
				worldIn.setBlock(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.defaultBlockState().setValue(RainbowBlock.AXIS, Axis.Z).setValue(RainbowBlock.SIDE_TYPE, 1), 2); // 1
			}

			if (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir())
			{
				// 3 End Corner
				worldIn.setBlock(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.defaultBlockState().setValue(RainbowBlock.AXIS, Axis.Z).setValue(RainbowBlock.CORNER_TYPE, 2), 2);
				--var10;

				while (worldIn.getBlockState(new BlockPos(xPos, yPos + var10, zPos + var11)).isAir())
				{
					// End Line
					worldIn.setBlock(new BlockPos(xPos, yPos + var10, zPos + var11), GNSBlocks.rainbow.defaultBlockState().setValue(RainbowBlock.AXIS, Axis.Z).setValue(RainbowBlock.SIDE_TYPE, 2), 2); // 4
					--var10;
				}
			}
		}

		super.setPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}