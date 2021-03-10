package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.block.AbstractBlock.OffsetType;
import net.minecraft.block.AbstractBlock.Properties;

public class GNSTallGrassBlock extends BushBlock implements IGrowable
{

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

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
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
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
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext)
	{
		return true;
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (this == GNSBlocks.prickly_nightmare_grass && !(entityIn instanceof IMob || entityIn instanceof ItemEntity || entityIn instanceof ZombieHorseEntity || entityIn instanceof SkeletonHorseEntity))
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
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return false;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
	{
		return false;
	}

	@Override
	public void performBonemeal(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_)
	{
	}
}