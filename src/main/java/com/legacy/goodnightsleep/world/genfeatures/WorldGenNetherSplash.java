package com.legacy.goodnightsleep.world.genfeatures;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherSplash extends WorldGenerator
{

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int i = 0; i < random.nextInt(13); ++i)
		{
			int xOffs = random.nextInt(7) - 3;
			int yOffs = random.nextInt(7) - 3;
			
			if (!world.isAirBlock(x + xOffs, y - 1, z + yOffs))
			{
				world.setBlock(x + xOffs, y - 1, z + yOffs, Blocks.netherrack);
				
				if (world.isAirBlock(x + xOffs, y, z + yOffs) && random.nextBoolean())
				{
					world.setBlock(x + xOffs, y, z + yOffs, Blocks.fire);
				}
			}
		}
		return true;
	}
}
