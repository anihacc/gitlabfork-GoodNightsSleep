package com.legacy.goodnightsleep.registry;

import java.lang.reflect.Constructor;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.gen.settings.ScalingSettings;
import net.minecraft.world.gen.settings.SlideSettings;

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

	public static void initNoiseSettings()
	{
		registerNoiseSettings(DREAM_NOISE_SETTINGS, createNoiseSettings(new DimensionStructuresSettings(false), false, GNSBlocks.delusion_stone.getDefaultState(), Blocks.WATER.getDefaultState(), DREAM_NOISE_SETTINGS.func_240901_a_()));
		registerNoiseSettings(NIGHTMARE_NOISE_SETTINGS, createNoiseSettings(new DimensionStructuresSettings(false), false, Blocks.STONE.getDefaultState(), Blocks.LAVA.getDefaultState(), NIGHTMARE_NOISE_SETTINGS.func_240901_a_()));
	}

	public static DimensionSettings createNoiseSettings(DimensionStructuresSettings structureSettingsIn, boolean flag1, BlockState fillerBlockIn, BlockState fluidBlockIn, ResourceLocation settingsLocationIn)
	{
		try
		{
			System.out.println("burger creating noise settings");
			Constructor<DimensionSettings> constructor = DimensionSettings.class.getDeclaredConstructor(DimensionStructuresSettings.class, NoiseSettings.class, BlockState.class, BlockState.class, int.class, int.class, int.class, boolean.class);
			constructor.setAccessible(true);
			return constructor.newInstance(structureSettingsIn, new NoiseSettings(256, new ScalingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new SlideSettings(-10, 3, 0), new SlideSettings(-30, 0, 0), 1, 2, 1.0D, -0.46875D, true, true, false, flag1), fillerBlockIn, fluidBlockIn, -10, 0, 63, false);
		}
		catch (Exception e)
		{
			System.out.println("burger failed to create noise settings");
			e.printStackTrace();
		}

		return null;
	}

	private static DimensionSettings registerNoiseSettings(RegistryKey<DimensionSettings> settingsKeyIn, DimensionSettings dimSettingsIn)
	{
		WorldGenRegistries.func_243664_a(WorldGenRegistries.field_243658_j, settingsKeyIn.func_240901_a_(), dimSettingsIn);
		return dimSettingsIn;
	}
}
