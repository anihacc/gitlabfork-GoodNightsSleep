package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.entity.BabyCreeperRenderer;
import com.legacy.goodnightsleep.client.render.entity.GummyBearRenderer;
import com.legacy.goodnightsleep.client.render.entity.HerobrineRenderer;
import com.legacy.goodnightsleep.client.render.entity.TormenterRenderer;
import com.legacy.goodnightsleep.client.render.entity.UnicornRenderer;
import com.legacy.goodnightsleep.client.render.models.BabyCreeperModel;
import com.legacy.goodnightsleep.client.render.models.GummyBearModel;
import com.legacy.goodnightsleep.client.render.models.HerobrineModel;
import com.legacy.goodnightsleep.client.render.models.TormenterModel;
import com.legacy.goodnightsleep.client.render.models.UnicornModel;
import com.legacy.goodnightsleep.client.render.tile.GNSBedBlockEntityRenderer;
import com.legacy.goodnightsleep.registry.GNSBlockEntityTypes;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.client.model.geom.builders.CubeDeformation;
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
		event.registerLayerDefinition(GNSRenderRefs.UNICORN, () -> UnicornModel.createBodyLayer());
		event.registerLayerDefinition(GNSRenderRefs.BABY_CREEPER, () -> BabyCreeperModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(GNSRenderRefs.GUMMY_BEAR, () -> GummyBearModel.createBodyLayer(CubeDeformation.NONE));

		event.registerLayerDefinition(GNSRenderRefs.TORMENTER, () -> TormenterModel.createBodyLayer());
		event.registerLayerDefinition(GNSRenderRefs.HEROBRINE, () -> HerobrineModel.createBodyLayer());
	}

	public static void initRenders(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(GNSEntityTypes.UNICORN, UnicornRenderer::new);
		event.registerEntityRenderer(GNSEntityTypes.GUMMY_BEAR, GummyBearRenderer::new);
		event.registerEntityRenderer(GNSEntityTypes.BABY_CREEPER, BabyCreeperRenderer::new);
		event.registerEntityRenderer(GNSEntityTypes.TORMENTER, TormenterRenderer::new);
		event.registerEntityRenderer(GNSEntityTypes.HEROBRINE, HerobrineRenderer::new);
	}

	public static void initBlockEntityRenders()
	{
		BlockEntityRenderers.register(GNSBlockEntityTypes.DREAM_BED, GNSBedBlockEntityRenderer::new);
	}
}