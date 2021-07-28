package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

public class DreamSpongeFeature extends Feature<NoneFeatureConfiguration>
{
	private BlockState spongeBlockId;

	public DreamSpongeFeature(Codec<NoneFeatureConfiguration> configFactoryIn)
	{
		super(configFactoryIn);

		this.spongeBlockId = Blocks.WET_SPONGE.defaultBlockState();
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = context.random();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (level.getBlockState(new BlockPos(x, y, z)).getMaterial() != Material.WATER)
		{
			return false;
		}
		else
		{
			int varSize = rand.nextInt(6);
			int varSpace = 1 + rand.nextInt(3);
			this.generateStalk(level, x, y, z + varSpace, varSize);
			this.generateStalk(level, x + varSpace, y, z, varSize - 1);
			this.generateStalk(level, x + varSpace, y, z + varSpace, varSize - 2);
			this.generateStalk(level, x, y, z, varSize - 3);
			return true;
		}
	}

	private void generateStalk(LevelAccessor world, int x, int y, int z, int size)
	{
		if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == GNSBlocks.dream_dirt)
		{
			for (int i = 0; i < size; ++i)
				world.setBlock(new BlockPos(x, y + i, z), this.spongeBlockId, 0);
		}
	}
}
