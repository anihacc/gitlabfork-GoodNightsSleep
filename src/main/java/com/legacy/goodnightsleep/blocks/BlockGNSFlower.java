package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.item.ItemsGNS;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockGNSFlower extends BlockBush
{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

	public BlockGNSFlower(Block.Properties builder)
	{
		super(builder);
	}

	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		Vec3d vec3d = state.getOffset(worldIn, pos);
		return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.down();
		IBlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		
		if (this == BlocksGNS.hope_mushroom || this == BlocksGNS.despair_mushroom)
		{
			return iblockstate.getMaterial() == Material.ROCK || block == BlocksGNS.dream_grass_block || block == BlocksGNS.dream_dirt || block == Blocks.DIRT || block == BlocksGNS.nightmare_grass_block;
		}

		return block == BlocksGNS.dream_grass_block || block == BlocksGNS.dream_dirt || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == BlocksGNS.nightmare_grass_block;
	}
	
	@Override
	 public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	 {
		 if (state.getBlock() == BlocksGNS.lolipop_bush)
		 {
			 return ItemsGNS.lolipop;
		 }
		 else
		 {
			 return super.getItemDropped(state, worldIn, pos, fortune);
		 }
	 }

	public Block.EnumOffsetType getOffsetType()
	{
		return Block.EnumOffsetType.XZ;
	}
}