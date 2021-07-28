package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GNSTallGrassBlock extends BushBlock implements BonemealableBlock
{

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	public GNSTallGrassBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.below();
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (this == GNSBlocks.dream_grass)
		{
			return block == GNSBlocks.dream_grass_block || block == GNSBlocks.dream_dirt;
		}
		if (this == GNSBlocks.nightmare_grass || this == GNSBlocks.prickly_nightmare_grass)
		{
			return block == GNSBlocks.nightmare_grass_block || block == Blocks.DIRT;
		}
		else
		{
			return block == Blocks.GRASS;
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext)
	{
		return true;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
	{
		if (this == GNSBlocks.prickly_nightmare_grass && !(entityIn instanceof Enemy || entityIn instanceof ItemEntity || entityIn instanceof ZombieHorse || entityIn instanceof SkeletonHorse))
		{
			entityIn.hurt(new DamageSource("nightmare_grass_block"), 1.0F);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public OffsetType getOffsetType()
	{
		return Block.OffsetType.XYZ;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return false;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state)
	{
		return false;
	}

	@Override
	public void performBonemeal(ServerLevel p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_)
	{
	}
}