package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.client.GoodNightSleepClient;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("goodnightsleep")
public class GoodNightSleep
{
	public GoodNightSleep()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(GoodNightSleepClient::initialization);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new GNSEvents());
		MinecraftForge.EVENT_BUS.register(new GoodNightSleepClient());
	}
	
	private void initialization(final FMLCommonSetupEvent event)
    {
		//MinecraftForge.EVENT_BUS.register(new GNSEntityEvents());
		//MinecraftForge.EVENT_BUS.register(new GNSPlayerEvents());
		
		for (Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			//if (biome != BiomeNightmareHills.INSTANCE && biome != BiomeGoodDreamPlains.INSTANCE && biome != Biome.NETHER && !biome.getRegistryName().toString().contains("end"))
			{
				//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createCompositeFeature(Feature.BUSH, new BushConfig(BlocksGNS.despair_mushroom), Biome.TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(4)));
				//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createCompositeFeature(Feature.BUSH, new BushConfig(BlocksGNS.hope_mushroom), Biome.TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(8)));
			}
		}
    }

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation("goodnightsleep", name);
	}
}
