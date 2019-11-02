package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.client.GoodNightSleepClient;
import com.legacy.goodnightsleep.world.dream.GoodDreamPlainsBiome;
import com.legacy.goodnightsleep.world.nightmare.NightmareHillsBiome;

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
import net.minecraftforge.fml.common.Mod;
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
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(GoodNightSleepClient::initialization);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new GNSEvents());
		//MinecraftForge.EVENT_BUS.register(new GoodNightSleepClient());
		
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
			if (biome != NightmareHillsBiome.INSTANCE && biome != GoodDreamPlainsBiome.INSTANCE && biome != Biomes.NETHER && !biome.getRegistryName().toString().contains("end"))
			{
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(GNSBlocks.despair_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(4)));
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(GNSBlocks.hope_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(8)));
			}
		}
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
