package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.mojang.serialization.Lifecycle;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WorldData;

/**
 * Coremod hooks for the mod.
 */
public class GNSHooks
{
	/**
	 * Called in {@link MinecraftServer#loadLevel}
	 * 
	 * @param registryHolder
	 * @param worldData
	 */
	public static void initDimensions(RegistryAccess.RegistryHolder registryHolder, WorldData worldData)
	{
		GNSDimensions.init(worldData.worldGenSettings().dimensions(), registryHolder.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY), registryHolder.registryOrThrow(Registry.BIOME_REGISTRY), registryHolder.ownedRegistryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), worldData.worldGenSettings().seed());
	}

	/**
	 * Called in
	 * {@link DimensionType#registerBuiltin(RegistryAccess.RegistryHolder)}
	 * 
	 * @param registryHolder
	 */
	public static void initBuiltinDimensionTypes(RegistryAccess.RegistryHolder registryHolder)
	{
		WritableRegistry<DimensionType> registry = registryHolder.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);

		registry.register(GNSDimensions.DREAM_TYPE_KEY, GNSDimensions.DREAM_TYPE, Lifecycle.stable());
		registry.register(GNSDimensions.NIGHTMARE_TYPE_KEY, GNSDimensions.NIGHTMARE_TYPE, Lifecycle.stable());
	}
}