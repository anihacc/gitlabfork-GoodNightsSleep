package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.tile.GNSBedBlockEntityRenderer;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GNSEntityRendering
{
	public static void init()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(GNSEntityRendering::initLayers);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(GNSEntityRendering::initRenders);
	}

	public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		/*event.registerLayerDefinition(createModelName(type), BoatModel::createBodyModel);*/
	}


	public static void initRenders(EntityRenderersEvent.RegisterRenderers event)
	{
		/*register(GNSEntityTypes.UNICORN, UnicornRenderer::new);
		register(GNSEntityTypes.GUMMY_BEAR, GummyBearRenderer::new);
		register(GNSEntityTypes.BABY_CREEPER, BabyCreeperRenderer::new);
		register(GNSEntityTypes.TORMENTER, TormenterRenderer::new);
		register(GNSEntityTypes.HEROBRINE, HerobrineRenderer::new);
		register(GNSEntityTypes.SPAWNER_ENTITY, SpawnerRenderer::new);*/	
	}
	
	public static void initBlockEntityRenders()
	{
		BlockEntityRenderers.register(GNSTileEntityTypes.DREAM_BED, GNSBedBlockEntityRenderer::new);
	}
}