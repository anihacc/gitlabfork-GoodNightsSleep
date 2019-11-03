package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.client.GoodNightSleepClient;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
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
			if (biome.getRegistryName().getNamespace().equalsIgnoreCase("minecraft") && !biome.getRegistryName().toString().contains("end") && biome != Biomes.NETHER && biome != Biomes.THE_VOID)
			{
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(GNSBlocks.despair_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(4)));
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(GNSBlocks.hope_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(8)));
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
