package com.legacy.goodnightsleep.world.general_features;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DreamScatteredPlantFeature extends Feature<NoneFeatureConfiguration>
{
	protected final BlockState plant;

	public DreamScatteredPlantFeature(Codec<NoneFeatureConfiguration> configFactoryIn, BlockState plantIn)
	{
		super(configFactoryIn);
		this.plant = plantIn;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = context.random();

		int i = 0;

		if (this.plant.getBlock() == GNSBlocks.present && level.isEmptyBlock(pos) && level.getBlockState(pos.below()).getBlock() == GNSBlocks.dream_grass_block)
		{
			level.setBlock(pos, this.plant, 2);
			++i;
			return i > 0;
		}

		for (int j = 0; j < 64; ++j)
		{
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (level.isEmptyBlock(blockpos) && (level.getBlockState(blockpos.below()).getBlock() == GNSBlocks.dream_grass_block || level.getBlockState(blockpos.below()).getBlock() == GNSBlocks.nightmare_grass_block))
			{
				level.setBlock(blockpos, this.plant, 2);
				++i;
			}
		}

		return i > 0;
	}
}