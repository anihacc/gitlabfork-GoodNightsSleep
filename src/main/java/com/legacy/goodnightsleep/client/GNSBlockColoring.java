package com.legacy.goodnightsleep.client;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.registry.GNSBiomes;
import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GNSBlockColoring
{
	public static void init()
	{
		Minecraft.getInstance().getBlockColors().register(new BlockColor()
		{
			@Override
			public int getColor(BlockState state, BlockAndTintGetter worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				Minecraft mc = Minecraft.getInstance();
				if (mc.level != null && GNSBiomes.Keys.DREAM_BIOMES.contains(GNSBiomes.Keys.getKeyFromBiome(mc.level, mc.level.getBiome(pos))))
					return worldIn != null && pos != null ? BiomeColors.getAverageGrassColor(worldIn, pos) : 0xffffff;
				else
					return 0xffffff;
			}
		}, GNSBlocks.dream_grass_block, GNSBlocks.dream_grass);

		/*Minecraft.getInstance().getBlockColors().register(new IBlockColor()
		{
			@Override
			public int getColor(BlockState state, IBlockDisplayReader worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				Minecraft mc = Minecraft.getInstance();
				if (mc.world != null && GNSBiomes.Keys.NIGHTMARE_BIOMES.contains(GNSBiomes.Keys.getKeyFromBiome(mc.world, mc.world.getBiome(pos))))
					return worldIn != null && pos != null ? BiomeColors.getGrassColor(worldIn, pos) : 0xe27eff;
				else
					return 0xe27eff;
			}
		}, GNSBlocks.nightmare_grass_block, GNSBlocks.nightmare_grass);*/
	}
}