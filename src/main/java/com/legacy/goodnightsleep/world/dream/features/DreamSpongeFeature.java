package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamSpongeFeature extends Feature<NoFeatureConfig>
{
	private BlockState spongeBlockId;

	public DreamSpongeFeature(Codec<NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);

		this.spongeBlockId = Blocks.WET_SPONGE.getDefaultState();
	}

	@Override
	public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (worldIn.getBlockState(new BlockPos(x, y, z)).getMaterial() != Material.WATER)
		{
			return false;
		}
		else
		{
			int varSize = rand.nextInt(6);
			int varSpace = 1 + rand.nextInt(3);
			this.generateStalk(worldIn, x, y, z + varSpace, varSize);
			this.generateStalk(worldIn, x + varSpace, y, z, varSize - 1);
			this.generateStalk(worldIn, x + varSpace, y, z + varSpace, varSize - 2);
			this.generateStalk(worldIn, x, y, z, varSize - 3);
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
