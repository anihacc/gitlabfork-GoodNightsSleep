package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.util.GNSBiomeGenSettingsBuilder;
import com.legacy.goodnightsleep.world.util.GNSMobSpawnBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeHooks.BiomeCallbackFunction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;

@Mixin(ForgeHooks.class)
public class ForgeHooksMixin
{
	@Inject(at = @At("HEAD"), remap = false, method = "enhanceBiome(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/world/biome/Biome$Climate;Lnet/minecraft/world/biome/Biome$Category;Ljava/lang/Float;Ljava/lang/Float;Lnet/minecraft/world/biome/BiomeAmbience;Lnet/minecraft/world/biome/BiomeGenerationSettings;Lnet/minecraft/world/biome/MobSpawnInfo;Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;Lnet/minecraftforge/common/ForgeHooks$BiomeCallbackFunction;)Lnet/minecraft/world/biome/Biome;", cancellable = true)
	private static void enhanceBiome(final ResourceLocation name, final Biome.Climate climate, final Biome.Category category, final Float depth, final Float scale, final BiomeAmbience effects, final BiomeGenerationSettings gen, final MobSpawnInfo spawns, final RecordCodecBuilder.Instance<Biome> codec, final BiomeCallbackFunction callback, CallbackInfoReturnable<Biome> callbackInfo)
    {
		if (name.getNamespace().equals(GoodNightSleep.MODID))
		{
	        BiomeLoadingEvent event = new BiomeLoadingEvent(name, climate, category, depth, scale, effects, new GNSBiomeGenSettingsBuilder(gen), new GNSMobSpawnBuilder(spawns));
	        MinecraftForge.EVENT_BUS.post(event);
	        callbackInfo.setReturnValue(callback.apply(event.getClimate(), event.getCategory(), event.getDepth(), event.getScale(), event.getEffects(), event.getGeneration().build(), event.getSpawns().copy()));
		}
    }
}
