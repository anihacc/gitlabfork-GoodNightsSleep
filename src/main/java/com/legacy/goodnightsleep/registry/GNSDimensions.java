package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.DimensionSettings;

public class GNSDimensions
{
	public static final RegistryKey<World> DREAM = RegistryKey.func_240903_a_(Registry.WORLD_KEY, GoodNightSleep.locate("good_dream"));
	public static final RegistryKey<World> NIGHTMARE = RegistryKey.func_240903_a_(Registry.WORLD_KEY, GoodNightSleep.locate("nightmare"));

	public static final RegistryKey<DimensionSettings> DREAM_NOISE_SETTINGS = RegistryKey.func_240903_a_(Registry.field_243549_ar, GoodNightSleep.locate("dream"));
	public static final RegistryKey<DimensionSettings> NIGHTMARE_NOISE_SETTINGS = RegistryKey.func_240903_a_(Registry.field_243549_ar, GoodNightSleep.locate("nightmare"));

	public static final RegistryKey<DimensionType> DREAM_TYPE = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, GoodNightSleep.locate("dream"));
	public static final RegistryKey<DimensionType> NIGHTMARE_TYPE = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, GoodNightSleep.locate("nightmare"));
	
	public static final RegistryKey<Dimension> DREAM_DIM = RegistryKey.func_240903_a_(Registry.DIMENSION_KEY, GoodNightSleep.locate("good_dream"));
	public static final RegistryKey<Dimension> NIGHTMARE_DIM = RegistryKey.func_240903_a_(Registry.DIMENSION_KEY, GoodNightSleep.locate("nightmare"));

	public static ResourceLocation getDimensionLocations(boolean dream)
	{
		return getDimensionKeys(dream).func_240901_a_();
	}

	public static RegistryKey<World> getDimensionKeys(boolean dream)
	{
		return dream ? DREAM : NIGHTMARE;
	}
}
