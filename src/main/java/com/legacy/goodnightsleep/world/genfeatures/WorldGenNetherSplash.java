package com.legacy.goodnightsleep.world.genfeatures;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherSplash extends WorldGenerator
{

	public boolean generate(World world, Random random, BlockPos pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < random.nextInt(13); ++i)
		{
			int xOffs = random.nextInt(7) - 3;
			int yOffs = random.nextInt(7) - 3;
			
			if (!world.isAirBlock(new BlockPos(x + xOffs, y - 1, z + yOffs)))
			{
				world.setBlockState(new BlockPos(x + xOffs, y - 1, z + yOffs), Blocks.NETHERRACK.getDefaultState());
				
				if (world.isAirBlock(new BlockPos(x + xOffs, y, z + yOffs)) && random.nextBoolean())
				{
					world.setBlockState(new BlockPos(x + xOffs, y, z + yOffs), Blocks.FIRE.getDefaultState());
				}
			}
		}
		return true;
	}
}
