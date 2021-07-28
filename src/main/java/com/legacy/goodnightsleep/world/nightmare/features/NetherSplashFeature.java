package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NetherSplashFeature extends Feature<NoneFeatureConfiguration>
{
	public NetherSplashFeature(Codec<NoneFeatureConfiguration> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = context.random();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < rand.nextInt(13); ++i)
		{
			int xOffs = rand.nextInt(7) - 3;
			int yOffs = rand.nextInt(7) - 3;

			if (!level.isEmptyBlock(new BlockPos(x + xOffs, y - 1, z + yOffs)) && level.getBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs)).getBlock() != Blocks.LAVA)
			{
				level.setBlock(new BlockPos(x + xOffs, y - 1, z + yOffs), Blocks.NETHERRACK.defaultBlockState(), 0);

				if (level.isEmptyBlock(new BlockPos(x + xOffs, y, z + yOffs)) && rand.nextBoolean())
					level.setBlock(new BlockPos(x + xOffs, y, z + yOffs), Blocks.FIRE.defaultBlockState(), 0);
			}
		}
		return true;
	}
}
