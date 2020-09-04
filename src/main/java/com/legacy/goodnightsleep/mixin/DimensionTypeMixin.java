package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;
import com.mojang.serialization.Lifecycle;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

@Mixin(DimensionType.class)
public class DimensionTypeMixin
{
	// func_242718_a
	@Inject(at = @At("RETURN"), method = "func_242718_a(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;J)Lnet/minecraft/util/registry/SimpleRegistry;", cancellable = true)
	private static void func_242718_a(Registry<DimensionType> dimTypeRegistry, Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimSettingsRegistry, long seed, CallbackInfoReturnable<SimpleRegistry<Dimension>> callback)
	{
		SimpleRegistry<Dimension> simpleregistry = callback.getReturnValue();

		simpleregistry.register(GNSDimensions.DREAM_DIM, new Dimension(() -> dimTypeRegistry.func_243576_d(GNSDimensions.DREAM_TYPE), registerDream(biomeRegistry, dimSettingsRegistry, seed)), Lifecycle.stable());
		simpleregistry.register(GNSDimensions.NIGHTMARE_DIM, new Dimension(() -> dimTypeRegistry.func_243576_d(GNSDimensions.NIGHTMARE_TYPE), registerNightmare(biomeRegistry, dimSettingsRegistry, seed)), Lifecycle.stable());

		callback.setReturnValue(simpleregistry);
	}

	// there's probably a better way to do all this, but everything is private
	private static ChunkGenerator registerDream(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseChunkGenerator(DreamBiomeProvider.DreamPreset.dreamPreset.func_242619_a(biomeRegistry, seed), seed, () ->
		{
			return dimSettingsRegistry.func_243576_d(GNSDimensions.DREAM_NOISE_SETTINGS);
		});
	}

	private static ChunkGenerator registerNightmare(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseChunkGenerator(NightmareBiomeProvider.NightmarePreset.nightmarePreset.func_242619_a(biomeRegistry, seed), seed, () ->
		{
			return dimSettingsRegistry.func_243576_d(GNSDimensions.NIGHTMARE_NOISE_SETTINGS);
		});
	}
}
