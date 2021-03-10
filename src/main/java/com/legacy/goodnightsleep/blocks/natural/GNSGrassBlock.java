package com.legacy.goodnightsleep.blocks.natural;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

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
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

public class GNSGrassBlock extends GrassBlock
{
	public GNSGrassBlock()
	{
		super(Properties.copy(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL));
	}

	private static boolean shouldDecay(IWorldReader worldIn, BlockPos posIn)
	{
		BlockPos blockpos = posIn.above();
		return worldIn.getMaxLocalRawBrightness(blockpos) >= 4 || worldIn.getBlockState(blockpos).getLightBlock(worldIn, blockpos) < worldIn.getMaxLightLevel();
	}

	private static boolean shouldDecayAndNotWaterlogged(IWorldReader worldIn, BlockPos posIn)
	{
		BlockPos blockpos = posIn.above();
		return worldIn.getMaxLocalRawBrightness(blockpos) >= 4 && worldIn.getBlockState(blockpos).getLightBlock(worldIn, blockpos) < worldIn.getMaxLightLevel() && !worldIn.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		if (!worldIn.isClientSide)
		{
			if (!worldIn.isAreaLoaded(pos, 3))
				return;

			if (!shouldDecay(worldIn, pos))
			{
				if (this == GNSBlocks.dream_grass_block)
					worldIn.setBlockAndUpdate(pos, GNSBlocks.dream_dirt.defaultBlockState());
				else
					worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			}
			else
			{
				if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9)
				{
					for (int i = 0; i < 4; ++i)
					{
						BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

						if (!worldIn.isLoaded(blockpos))
							return;

						if ((this == GNSBlocks.dream_grass_block && worldIn.getBlockState(blockpos).getBlock() == GNSBlocks.dream_dirt || this == GNSBlocks.nightmare_grass_block && (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT || worldIn.getBlockState(blockpos).getBlock() == Blocks.GRASS_BLOCK)) && shouldDecayAndNotWaterlogged(worldIn, blockpos))
							worldIn.setBlockAndUpdate(blockpos, this.defaultBlockState());

					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
	{
		BlockPos blockpos = pos.above();
		BlockState blockstate = state.getBlock() == GNSBlocks.nightmare_grass_block ? GNSBlocks.nightmare_grass.defaultBlockState() : GNSBlocks.dream_grass.defaultBlockState();

		label48: for (int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j)
			{
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1))
				{
					continue label48;
				}
			}

			BlockState blockstate2 = worldIn.getBlockState(blockpos1);
			if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0)
			{
				((IGrowable) blockstate.getBlock()).performBonemeal(worldIn, rand, blockpos1, blockstate2);
			}

			if (blockstate2.isAir())
			{
				BlockState blockstate1;
				if (rand.nextInt(8) == 0)
				{
					List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty())
					{
						continue;
					}

					ConfiguredFeature<?, ?> configuredfeature = list.get(0);
					FlowersFeature flowersfeature = (FlowersFeature) configuredfeature.feature;
					blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
				}
				else
				{
					blockstate1 = blockstate;
				}

				if (blockstate1.canSurvive(worldIn, blockpos1))
				{
					worldIn.setBlock(blockpos1, blockstate1, 3);
				}
			}
		}
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
	{
		if (plantable instanceof SaplingBlock)
			return true;

		PlantType plantType = plantable.getPlantType(world, pos.relative(facing));

		if (plantType.equals(PlantType.PLAINS))
			return true;
		else if (plantType.equals(PlantType.BEACH))
			return (world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER);
		else
			return super.canSustainPlant(state, world, pos, facing, plantable);
	}

	@Override
	public void onPlantGrow(BlockState state, IWorld world, BlockPos pos, BlockPos source)
	{
		BlockState dirtState = this == GNSBlocks.dream_grass_block ? GNSBlocks.dream_dirt.defaultBlockState() : Blocks.DIRT.defaultBlockState();
		world.setBlock(pos, dirtState, 2);
	}
}