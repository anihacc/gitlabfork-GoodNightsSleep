package com.legacy.goodnightsleep.world.general_features;

import java.util.Random;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamScatteredPlantFeature extends Feature<NoFeatureConfig>
{
	protected final BlockState plant;

	public DreamScatteredPlantFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, BlockState plantIn)
	{
		super(configFactoryIn);
		this.plant = plantIn;
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		int i = 0;


		if (this.plant.getBlock() == GNSBlocks.present && worldIn.isAirBlock(pos) && worldIn.getBlockState(pos.down()).getBlock() == GNSBlocks.dream_grass_block)
		{
			worldIn.setBlockState(pos, this.plant, 2);
			++i;
			return i > 0;
		}

		for (int j = 0; j < 64; ++j)
		{
			BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (worldIn.isAirBlock(blockpos) && (worldIn.getBlockState(blockpos.down()).getBlock() == GNSBlocks.dream_grass_block || worldIn.getBlockState(blockpos.down()).getBlock() == GNSBlocks.nightmare_grass_block))
			{
				worldIn.setBlockState(blockpos, this.plant, 2);
				++i;
			}
		}

		return i > 0;
	}
}