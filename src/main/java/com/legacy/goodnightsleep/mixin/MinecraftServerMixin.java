package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.goodnightsleep.registry.GNSDimensions;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.storage.IServerConfiguration;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
	@Shadow
	protected DynamicRegistries.Impl registryHolder;
	@Shadow
	protected IServerConfiguration worldData;

	/*
	 * MinecraftServer#loadLevel
	 */
	@Inject(at = @At("HEAD"), method = "loadLevel()V")
	private void initServer(CallbackInfo callback)
	{
		GNSDimensions.init(this.worldData.worldGenSettings().dimensions(), this.registryHolder.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY), this.registryHolder.registryOrThrow(Registry.BIOME_REGISTRY), this.registryHolder.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), this.worldData.worldGenSettings().seed());
	}
}
