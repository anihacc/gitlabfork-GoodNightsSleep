package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.mojang.serialization.Lifecycle;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WorldData;

public class GNSHooks
{
	public static void initDimensions(RegistryAccess.RegistryHolder registryHolder, WorldData worldData)
	{
		GNSDimensions.init(worldData.worldGenSettings().dimensions(), registryHolder.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY), registryHolder.registryOrThrow(Registry.BIOME_REGISTRY), registryHolder.ownedRegistryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), worldData.worldGenSettings().seed());
	}
	
	public static void initBuiltinDimensionTypes(RegistryAccess.RegistryHolder registryHolder)
	{
		/*System.out.println("burger initBuiltinDimensionTypes");
		WritableRegistry<DimensionType> registry = registryHolder.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
		
		registry.register(GNSDimensions.DREAM_TYPE_KEY, GNSDimensions.DREAM_TYPE, Lifecycle.stable());
		registry.register(GNSDimensions.NIGHTMARE_TYPE_KEY, GNSDimensions.NIGHTMARE_TYPE, Lifecycle.stable());*/
	}
}
