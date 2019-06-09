package com.legacy.goodnightsleep.world.structures;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class StructureGenerator extends WorldGenerator implements IWorldGenerator
{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		
		if (world.provider.getDimension() == 0)
		{
			generateOverworld(world, rand, blockX + 8, blockZ + 8);
		}
		else if (world.provider.getDimension() == GNSConfig.getDreamDimensionID())
		{
			generateDream(world, rand, blockX + 8, blockZ + 8);
		}
		else if (world.provider.getDimension() == GNSConfig.getNightmareDimensionID())
		{
			generateNightmare(world, rand, blockX + 8, blockZ + 8);
		}
	}
	
	private void generateOverworld(World world, Random rand, int blockX, int blockZ)
	{
	}
	
	private void generateDream(World world, Random rand, int blockX, int blockZ)
	{		
	}
	
	private void generateNightmare(World world, Random rand, int blockX, int blockZ)
	{		
	}
	
	public static int getLakeFromAbove(World world, int x, int z)
	{
		int y = 255;
		boolean foundGround = false;
		while(!foundGround && y-- >= 31)
		{
			Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround =  blockAt == Blocks.WATER||blockAt == Blocks.FLOWING_WATER;
		}

		return y;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// TODO Auto-generated method stub
		return false;
	}
}