package com.legacy.goodnightsleep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.legacy.goodnightsleep.blocks.util.ToolCompat;
import com.legacy.goodnightsleep.client.GoodNightSleepClient;
import com.legacy.goodnightsleep.data.GNSMappingChanges;
import com.legacy.goodnightsleep.world.dream.features.GNSFeatures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeDictionary;
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
	public static final String NAME = "Good Night's Sleep";
	public static final String MODID = "good_nights_sleep";
	public static final String OLD_MODID = "goodnightsleep";
	public static final Logger LOGGER = LogManager.getLogger();

	public GoodNightSleep()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GNSConfig.SERVER_SPEC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () ->
		{
			FMLJavaModLoadingContext.get().getModEventBus().addListener(GoodNightSleepClient::initialization);
			MinecraftForge.EVENT_BUS.register(new GoodNightSleepClient());
		});
	}

	private void initialization(final FMLCommonSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new GNSMappingChanges());

		for (Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			if ((biome.getRegistryName().getNamespace().equalsIgnoreCase("minecraft") || biome.getRegistryName().getNamespace().equalsIgnoreCase("biomesoplenty")) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.END) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID))
			{
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER))
				{
					biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.HOPE_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.3F, 0, 0, 128))));
					biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.DESPAIR_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(0.3F, 0, 0, 128))));
				}
				else
				{
					biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.HOPE_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(8))));
					biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.DESPAIR_MUSHROOM_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(4))));
				}
			}
		}

		ToolCompat.init();
		MinecraftForge.EVENT_BUS.register(new GNSEvents());
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
