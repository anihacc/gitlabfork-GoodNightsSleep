package com.legacy.goodnightsleep.world;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.dream.GoodDreamPlainsBiome;
import com.legacy.goodnightsleep.world.nightmare.NightmareHillsBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GoodNightSleep.MODID)
public class GNSBiomes
{
	public static final Biome GOOD_DREAM_PLAINS = new GoodDreamPlainsBiome();
	public static final Biome NIGHTMARE_HILLS = new NightmareHillsBiome();

	public static void init(Register<Biome> event)
	{
		register(event.getRegistry(), "good_dream_plains", GOOD_DREAM_PLAINS);
		register(event.getRegistry(), "nightmare_hills", NIGHTMARE_HILLS);
		
		BiomeDictionary.addTypes(GOOD_DREAM_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(NIGHTMARE_HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);
	}

	public static void register(IForgeRegistry<Biome> registry, String key, Biome biome)
	{
		GNSRegistry.register(registry, key, biome);
	}
}
