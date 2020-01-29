package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.render.GNSEntityRendering;
import com.legacy.goodnightsleep.client.render.GNSTileEntityRendering;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GoodNightSleepClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		GNSTileEntityRendering.initialization();
		GNSEntityRendering.initialization();
		MinecraftForge.EVENT_BUS.register(new GNSMusicHandler());

		renderCutout(GNSBlocks.candy_sapling);
		renderCutout(GNSBlocks.dream_sapling);
		renderCutout(GNSBlocks.cyan_flower);
		renderCutout(GNSBlocks.dead_flower);
		renderCutout(GNSBlocks.orange_flower);
		renderCutout(GNSBlocks.lolipop_bush);
		renderCutout(GNSBlocks.tall_dream_grass);
		renderCutout(GNSBlocks.tall_nightmare_grass);
		renderCutout(GNSBlocks.prickly_nightmare_grass);
		renderCutout(GNSBlocks.hope_mushroom);
		renderCutout(GNSBlocks.despair_mushroom);
		renderCutout(GNSBlocks.rainbow_crop);
		renderCutout(GNSBlocks.potted_candy_sapling);
		renderCutout(GNSBlocks.potted_dream_sapling);
		renderCutout(GNSBlocks.potted_cyan_flower);
		renderCutout(GNSBlocks.potted_dead_flower);
		renderCutout(GNSBlocks.potted_orange_flower);
		renderCutout(GNSBlocks.potted_hope_mushroom);
		renderCutout(GNSBlocks.potted_despair_mushroom);

		RenderTypeLookup.setRenderLayer(GNSBlocks.rainbow, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(GNSBlocks.dream_leaves, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(GNSBlocks.candy_leaves, RenderType.cutoutMipped());
		RenderTypeLookup.setRenderLayer(GNSBlocks.diamond_leaves, RenderType.cutoutMipped());
	}

	private static void renderCutout(Block block)
	{
		RenderTypeLookup.setRenderLayer(block, RenderType.cutout());
	}
}