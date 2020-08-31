package com.legacy.goodnightsleep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.legacy.goodnightsleep.blocks.util.ToolCompat;
import com.legacy.goodnightsleep.client.GoodNightSleepClient;
import com.legacy.goodnightsleep.data.GNSMappingChanges;
import com.legacy.goodnightsleep.registry.GNSFeatures;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(GoodNightSleep.MODID)
public class GoodNightSleep
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String NAME = "Good Night's Sleep";
	public static final String MODID = "good_nights_sleep";
	public static final String OLD_MODID = "goodnightsleep";

	public GoodNightSleep()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GNSConfig.SERVER_SPEC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
		{
			FMLJavaModLoadingContext.get().getModEventBus().addListener(GoodNightSleepClient::initialization);
			MinecraftForge.EVENT_BUS.register(new GoodNightSleepClient());
		});
	}

	private void initialization(final FMLCommonSetupEvent event)
	{
		ForgeRegistries.BIOMES.getValues().forEach(GNSFeatures::addMushrooms);

		MinecraftForge.EVENT_BUS.register(new GNSMappingChanges());
		MinecraftForge.EVENT_BUS.register(new GNSEvents());

		ToolCompat.init();
	}

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
}
