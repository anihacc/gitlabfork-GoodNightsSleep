package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.client.GoodNightSleepClient;
import com.legacy.goodnightsleep.world.dream.features.GNSFeatures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
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

	public static final String MODID = "goodnightsleep";

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
		for (Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			if (biome.getRegistryName().getNamespace().equalsIgnoreCase("minecraft") && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.END) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID))
			{
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(GNSFeatures.HOPE_MUSHROOM_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(8))));
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(GNSFeatures.DESPAIR_MUSHROOM_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(4))));
			}
		}

		MinecraftForge.EVENT_BUS.register(new GNSEvents());
	}

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}

	public static String find(String name)
	{
		return MODID + ":" + name;
	}
}
