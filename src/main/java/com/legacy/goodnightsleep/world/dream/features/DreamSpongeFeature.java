package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamSpongeFeature extends Feature<NoFeatureConfig>
{

	private BlockState spongeBlockId;

	public DreamSpongeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);

		this.spongeBlockId = Blocks.WET_SPONGE.getDefaultState();
	}

	public boolean place(IWorld par1World, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random par2Random, BlockPos pos, NoFeatureConfig p_212245_5_)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (par1World.getBlockState(new BlockPos(x, y, z)).getMaterial() != Material.WATER)
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

	private void generateStalk(IWorld world, int x, int y, int z, int size)
	{
		if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == GNSBlocks.dream_dirt)
		{
			for (int i = 0; i < size; ++i)
			{
				world.setBlockState(new BlockPos(x, y + i, z), this.spongeBlockId, 0);
			}
		}
	}
}
