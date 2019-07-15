package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamSpongeFeature extends Feature<NoFeatureConfig>
{

	private IBlockState spongeBlockId;

	public DreamSpongeFeature()
	{
		this.spongeBlockId = Blocks.WET_SPONGE.getDefaultState();
	}

	public boolean func_212245_a(IWorld par1World, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random par2Random, BlockPos pos, NoFeatureConfig p_212245_5_)
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
		if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == BlocksGNS.dream_dirt)
		{
			for (int i = 0; i < size; ++i)
			{
				world.setBlockState(new BlockPos(x, y + i, z), this.spongeBlockId, 0);
			}
		}
	}
}
