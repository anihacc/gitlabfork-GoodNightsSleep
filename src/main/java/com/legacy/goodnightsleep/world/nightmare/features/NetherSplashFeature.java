package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NetherSplashFeature extends Feature<NoFeatureConfig>
{
	public NetherSplashFeature(Codec<NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random randIn, BlockPos pos, NoFeatureConfig config)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < randIn.nextInt(13); ++i)
		{
			int xOffs = randIn.nextInt(7) - 3;
			int yOffs = randIn.nextInt(7) - 3;

			if (!worldIn.isAirBlock(new BlockPos(x + xOffs, y - 1, z + yOffs)) && worldIn.getBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs)).getBlock() != Blocks.LAVA)
			{
				worldIn.setBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs), Blocks.NETHERRACK.getDefaultState(), 0);

				if (worldIn.isAirBlock(new BlockPos(x + xOffs, y, z + yOffs)) && randIn.nextBoolean())
				{
					worldIn.setBlockState(new BlockPos(x + xOffs, y, z + yOffs), Blocks.FIRE.getDefaultState(), 0);
				}
			}
		}
		return true;
	}
}
