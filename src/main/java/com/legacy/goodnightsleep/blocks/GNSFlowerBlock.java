package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class GNSFlowerBlock extends BushBlock
{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

	public GNSFlowerBlock(Block.Properties builder)
	{
		super(builder);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Vec3d vec3d = state.getOffset(worldIn, pos);
		return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.down();
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		
		if (this == GNSBlocks.hope_mushroom || this == GNSBlocks.despair_mushroom)
		{
			return iblockstate.getMaterial() == Material.ROCK || block == GNSBlocks.delusion_stone || block == GNSBlocks.dream_grass_block || block == GNSBlocks.dream_dirt || block == Blocks.DIRT || block == GNSBlocks.nightmare_grass_block;
		}

		return block == GNSBlocks.dream_grass_block || block == GNSBlocks.dream_dirt || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == GNSBlocks.nightmare_grass_block;
	}

	public Block.OffsetType getOffsetType()
	{
		return Block.OffsetType.XZ;
	}
}