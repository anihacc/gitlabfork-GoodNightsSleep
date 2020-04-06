package com.legacy.goodnightsleep.blocks.natural;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.GNSBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

public class GNSGrassBlock extends GrassBlock
{

	public GNSGrassBlock()
	{
		super(Properties.from(Blocks.GRASS_BLOCK));
	}

	private static boolean func_196383_a(IWorldReader p_196383_0_, BlockPos p_196383_1_)
	{
		BlockPos blockpos = p_196383_1_.up();
		return p_196383_0_.getLight(blockpos) >= 4 || p_196383_0_.getBlockState(blockpos).getOpacity(p_196383_0_, blockpos) < p_196383_0_.getMaxLightLevel();
	}

	private static boolean func_196384_b(IWorldReader p_196384_0_, BlockPos p_196384_1_)
	{
		BlockPos blockpos = p_196384_1_.up();
		return p_196384_0_.getLight(blockpos) >= 4 && p_196384_0_.getBlockState(blockpos).getOpacity(p_196384_0_, blockpos) < p_196384_0_.getMaxLightLevel() && !p_196384_0_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		if (!worldIn.isRemote)
		{
			if (!worldIn.isAreaLoaded(pos, 3))
				return;
			if (!func_196383_a(worldIn, pos) && this == GNSBlocks.dream_grass_block)
			{
				worldIn.setBlockState(pos, GNSBlocks.dream_dirt.getDefaultState());
			}
			else if (!func_196383_a(worldIn, pos) && this == GNSBlocks.nightmare_grass_block)
			{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			else
			{
				if (worldIn.getLight(pos.up()) >= 9)
				{
					for (int i = 0; i < 4; ++i)
					{
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (!worldIn.isBlockPresent(blockpos))
						{
							return;
						}
						if (worldIn.getBlockState(blockpos).getBlock() == GNSBlocks.dream_dirt && func_196384_b(worldIn, blockpos) && this == GNSBlocks.dream_grass_block)
						{
							worldIn.setBlockState(blockpos, this.getDefaultState());
						}
						else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_196384_b(worldIn, blockpos) && this == GNSBlocks.nightmare_grass_block)
						{
							worldIn.setBlockState(blockpos, this.getDefaultState());
						}
					}
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		BlockPos blockpos = pos.up();
		BlockState blockstate = state.getBlock() == GNSBlocks.nightmare_grass_block ? GNSBlocks.tall_nightmare_grass.getDefaultState() : GNSBlocks.tall_dream_grass.getDefaultState();

		for (int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;
			int j = 0;

			while (true)
			{
				if (j >= i / 16)
				{
					BlockState blockstate2 = world.getBlockState(blockpos1);
					if (blockstate2.getBlock() == blockstate.getBlock() && random.nextInt(10) == 0)
					{
						((IGrowable) blockstate.getBlock()).grow(world, random, blockpos1, blockstate2);
					}

					if (!blockstate2.isAir())
					{
						break;
					}

					BlockState blockstate1;
					if (random.nextInt(8) == 0)
					{
						List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).getFlowers();
						if (list.isEmpty())
						{
							break;
						}

						ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig) (list.get(0)).config).feature;
						blockstate1 = ((FlowersFeature) configuredfeature.feature).getFlowerToPlace(random, blockpos1, configuredfeature.config);
					}
					else
					{
						blockstate1 = blockstate;
					}

					if (blockstate1.isValidPosition(world, blockpos1))
					{
						world.setBlockState(blockpos1, blockstate1, 3);
					}
					break;
				}

				blockpos1 = blockpos1.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (world.getBlockState(blockpos1.down()).getBlock() != this || world.getBlockState(blockpos1).isCollisionShapeOpaque(world, blockpos1))
				{
					break;
				}

				++j;
			}
		}

	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
	{
		if (plantable instanceof SaplingBlock)
			return true;

		PlantType plantType = plantable.getPlantType(world, pos.offset(facing));
		switch (plantType)
		{
		case Plains:
			return true;
		case Beach:
			boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER);
			return hasWater;
		default:
			break;
		}
		return super.canSustainPlant(state, world, pos, facing, plantable);
	}

	@Override
	public void onPlantGrow(BlockState state, IWorld world, BlockPos pos, BlockPos source)
	{
		if (this == GNSBlocks.dream_grass_block)
			world.setBlockState(pos, GNSBlocks.dream_dirt.getDefaultState(), 2);
		else
			world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 2);
	}
}