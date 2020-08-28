package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.render.GNSEntityRendering;
import com.legacy.goodnightsleep.client.render.GNSTileEntityRendering;
import com.legacy.goodnightsleep.registry.GNSBlocks;

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
		MinecraftForge.EVENT_BUS.register(new ClientEvents());

		renderCutout(GNSBlocks.candy_sapling);
		renderCutout(GNSBlocks.dream_sapling);
		renderCutout(GNSBlocks.cyan_flower);
		renderCutout(GNSBlocks.dead_flower);
		renderCutout(GNSBlocks.orange_flower);
		renderCutout(GNSBlocks.lolipop_bush);
		renderCutout(GNSBlocks.dream_grass);
		renderCutout(GNSBlocks.nightmare_grass);
		renderCutout(GNSBlocks.prickly_nightmare_grass);
		renderCutout(GNSBlocks.hope_mushroom);
		renderCutout(GNSBlocks.despair_mushroom);
		renderCutout(GNSBlocks.rainbow_berries);
		renderCutout(GNSBlocks.potted_candy_sapling);
		renderCutout(GNSBlocks.potted_dream_sapling);
		renderCutout(GNSBlocks.potted_cyan_flower);
		renderCutout(GNSBlocks.potted_dead_flower);
		renderCutout(GNSBlocks.potted_orange_flower);
		renderCutout(GNSBlocks.potted_hope_mushroom);
		renderCutout(GNSBlocks.potted_despair_mushroom);

		renderCutout(GNSBlocks.dream_door);
		renderCutout(GNSBlocks.white_door);
		renderCutout(GNSBlocks.dead_door);
		renderCutout(GNSBlocks.blood_door);
		renderCutout(GNSBlocks.dream_trapdoor);
		renderCutout(GNSBlocks.white_trapdoor);
		renderCutout(GNSBlocks.dead_trapdoor);
		renderCutout(GNSBlocks.blood_trapdoor);

		RenderTypeLookup.setRenderLayer(GNSBlocks.rainbow, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(GNSBlocks.dream_leaves, RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(GNSBlocks.candy_leaves, RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(GNSBlocks.diamond_leaves, RenderType.getCutoutMipped());
	}

	private static void renderCutout(Block block)
	{
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
}