package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NetherSplashFeature extends Feature<NoFeatureConfig>
{

	public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random random, BlockPos pos, NoFeatureConfig p_212245_5_)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < random.nextInt(13); ++i)
		{
			int xOffs = random.nextInt(7) - 3;
			int yOffs = random.nextInt(7) - 3;
			
			if (!world.isAirBlock(new BlockPos(x + xOffs, y - 1, z + yOffs)) && world.getBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs)).getBlock() != Blocks.LAVA)
			{
				world.setBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs), Blocks.NETHERRACK.getDefaultState(), 0);
				
				if (world.isAirBlock(new BlockPos(x + xOffs, y, z + yOffs)) && random.nextBoolean())
				{
					world.setBlockState(new BlockPos(x + xOffs, y, z + yOffs), Blocks.FIRE.getDefaultState(), 0);
				}
			}
		}
		return true;
	}
}
