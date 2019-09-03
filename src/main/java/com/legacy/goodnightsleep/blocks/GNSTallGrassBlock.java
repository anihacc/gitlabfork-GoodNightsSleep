package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GNSTallGrassBlock extends BushBlock
{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	 public GNSTallGrassBlock(Properties properties)
	 {
		 super(properties);
	 }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.down();
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		
		if (this == BlocksGNS.tall_dream_grass)
		{
			return block == BlocksGNS.dream_grass_block || block == BlocksGNS.dream_dirt;
		}
		if (this == BlocksGNS.tall_nightmare_grass || this == BlocksGNS.prickly_nightmare_grass)
		{
			return block == BlocksGNS.nightmare_grass_block || block == Blocks.DIRT;
		}
		else
		{
			return block == Blocks.GRASS;
		}
	}
	 
	 @Override
	 public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
	 {
		 return true;
	 }
	 
	 @Override
	 public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	 {
		 if (this == BlocksGNS.prickly_nightmare_grass && !(entityIn instanceof IMob || entityIn instanceof ItemEntity || entityIn instanceof ZombieHorseEntity || entityIn instanceof SkeletonHorseEntity))
		 {
			 entityIn.attackEntityFrom(new DamageSource("nightmare_grass"), 1.0F);
		 }
	 }

	 @Override
	 @OnlyIn(Dist.CLIENT)
	 public OffsetType getOffsetType()
	 {
		 return Block.OffsetType.XYZ;
	 }

}