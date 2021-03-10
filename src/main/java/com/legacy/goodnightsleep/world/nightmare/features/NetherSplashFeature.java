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
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random randIn, BlockPos pos, NoFeatureConfig config)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < randIn.nextInt(13); ++i)
		{
			int xOffs = randIn.nextInt(7) - 3;
			int yOffs = randIn.nextInt(7) - 3;

			if (!worldIn.isEmptyBlock(new BlockPos(x + xOffs, y - 1, z + yOffs)) && worldIn.getBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs)).getBlock() != Blocks.LAVA)
			{
				worldIn.setBlock(new BlockPos(x + xOffs, y - 1, z + yOffs), Blocks.NETHERRACK.defaultBlockState(), 0);

				if (worldIn.isEmptyBlock(new BlockPos(x + xOffs, y, z + yOffs)) && randIn.nextBoolean())
				{
					worldIn.setBlock(new BlockPos(x + xOffs, y, z + yOffs), Blocks.FIRE.defaultBlockState(), 0);
				}
			}
		}
		return true;
	}
}
