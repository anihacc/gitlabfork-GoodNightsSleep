package com.legacy.goodnightsleep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.legacy.goodnightsleep.blocks.util.ToolCompat;
import com.legacy.goodnightsleep.capabillity.util.IDreamPlayer;
import com.legacy.goodnightsleep.client.GNSBlockColoring;
import com.legacy.goodnightsleep.client.GNSClientEvents;
import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.render.GNSEntityRendering;
import com.legacy.goodnightsleep.client.resource_pack.GNSResourcePackHandler;
import com.legacy.goodnightsleep.data.GNSBlockTags;
import com.legacy.goodnightsleep.data.GNSItemTags;
import com.legacy.goodnightsleep.data.GNSMappingChanges;
import com.legacy.goodnightsleep.event.GNSEvents;
import com.legacy.goodnightsleep.event.GNSPlayerEvents;
import com.legacy.goodnightsleep.network.PacketHandler;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;
import com.legacy.goodnightsleep.registry.GNSFeatures;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GoodNightSleep.MODID)
public class GoodNightSleep
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String NAME = "Good Night's Sleep";
	public static final String MODID = "good_nights_sleep";
	public static final String OLD_MODID = "goodnightsleep";

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}

	public static ResourceLocation locateOld(String name)
	{
		return new ResourceLocation(OLD_MODID, name);
	}

	public static String find(String name)
	{
		return MODID + ":" + name;
	}

	public GoodNightSleep()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GNSConfig.SERVER_SPEC);

		IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();

		mod.addListener(this::commonInit);
		mod.addListener(GNSEntityTypes::onAttributesRegistered);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
		{
			GNSEntityRendering.init();
			mod.addListener(this::clientInit);
		});
	}

	private void commonInit(final FMLCommonSetupEvent event)
	{
		IEventBus forge = MinecraftForge.EVENT_BUS;

		CapabilityManager.INSTANCE.register(IDreamPlayer.class);

		forge.addListener((BiomeLoadingEvent biomeEvent) -> GNSFeatures.addMushrooms(biomeEvent));

		forge.register(new GNSMappingChanges());
		forge.register(new GNSEvents());
		forge.register(new GNSPlayerEvents());

		PacketHandler.register();
		ToolCompat.init();

		GNSBlockTags.init();
		GNSItemTags.init();
	}

	public void clientInit(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new GNSMusicHandler());
		MinecraftForge.EVENT_BUS.register(new GNSClientEvents());

		GNSEntityRendering.init();
		GNSResourcePackHandler.init();
		GNSBlockColoring.init();
		GNSClientEvents.initDimensionRenderInfo();
		GNSEntityRendering.initBlockEntityRenders();

		renderCutout(GNSBlocks.candy_sapling);
		renderCutout(GNSBlocks.dream_sapling);
		renderCutout(GNSBlocks.cyan_flower);
		renderCutout(GNSBlocks.dead_flower);
		renderCutout(GNSBlocks.orange_flower);
		renderCutout(GNSBlocks.lollipop_bush);
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
		renderCutout(GNSBlocks.potted_lollipop_bush);

		if (ModList.get().isLoaded("quark"))
		{
			renderCutout(GNSBlocks.potted_dream_grass);
			renderCutout(GNSBlocks.potted_nightmare_grass);
			renderCutout(GNSBlocks.potted_prickly_nightmare_grass);
		}

		renderCutout(GNSBlocks.dream_door);
		renderCutout(GNSBlocks.white_door);
		renderCutout(GNSBlocks.dead_door);
		renderCutout(GNSBlocks.blood_door);
		renderCutout(GNSBlocks.dream_trapdoor);
		renderCutout(GNSBlocks.white_trapdoor);
		renderCutout(GNSBlocks.dead_trapdoor);
		renderCutout(GNSBlocks.blood_trapdoor);

		ItemBlockRenderTypes.setRenderLayer(GNSBlocks.dream_grass_block, RenderType.cutoutMipped());

		ItemBlockRenderTypes.setRenderLayer(GNSBlocks.rainbow, RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(GNSBlocks.dream_leaves, RenderType.cutoutMipped());
		ItemBlockRenderTypes.setRenderLayer(GNSBlocks.candy_leaves, RenderType.cutoutMipped());
		ItemBlockRenderTypes.setRenderLayer(GNSBlocks.diamond_leaves, RenderType.cutoutMipped());
	}

	private static void renderCutout(Block block)
	{
		ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
	}
}
