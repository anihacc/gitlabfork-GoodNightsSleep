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
	protected DynamicRegistries.Impl field_240767_f_;
	@Shadow
	protected IServerConfiguration field_240768_i_;

	/*
	 * MinecraftServer#func_240800_l__
	 */
	@Inject(at = @At("HEAD"), method = "func_240800_l__()V")
	private void initServer(CallbackInfo callback)
	{
		GNSDimensions.init(this.field_240768_i_.getDimensionGeneratorSettings().func_236224_e_(), this.field_240767_f_.func_243612_b(Registry.DIMENSION_TYPE_KEY), this.field_240767_f_.func_243612_b(Registry.BIOME_KEY), this.field_240767_f_.func_243612_b(Registry.field_243549_ar), this.field_240768_i_.getDimensionGeneratorSettings().getSeed());
	}
}