package com.legacy.goodnightsleep.world.genfeatures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSponge extends WorldGenerator
{

	private Block spongeBlockId;

	public WorldGenSponge()
	{
		this.spongeBlockId = Blocks.sponge;
	}

	public boolean generate(World par1World, Random par2Random, int x, int y, int z)
	{	
		if (par1World.getBlock(x, y, z).getMaterial() != Material.water)
		{
			return false;
		}
		else
		{
			int varSize = par2Random.nextInt(6);
			int varSpace = 1 + par2Random.nextInt(3);
			this.generateStalk(par1World, x, y, z + varSpace, varSize);
			this.generateStalk(par1World, x + varSpace, y, z, varSize - 1);
			this.generateStalk(par1World, x + varSpace, y, z + varSpace, varSize - 2);
			this.generateStalk(par1World, x, y, z, varSize - 3);
			return true;
		}
	}

	private void generateStalk(World world, int x, int y, int z, int size)
	{
		if (!world.isAirBlock(x, y - 1, z))
		{
			for (int i = 0; i < size; ++i)
			{
				world.setBlock(x, y + i, z, this.spongeBlockId);
			}
		}
	}
}
