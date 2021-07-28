package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class GoldPotBlock extends Block
{
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

	public GoldPotBlock(Block.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return INSIDE;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		if (!worldIn.isClientSide)
			player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 180, 0, true, true));

		return InteractionResult.SUCCESS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
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
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type)
	{
		return false;
	}
}