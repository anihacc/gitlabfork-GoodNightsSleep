package com.legacy.goodnightsleep.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin
{
	@Inject(at = @At("HEAD"), method = "checkAnimalSpawnRules(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/IWorld;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)Z", cancellable = true)
	private static void checkAnimalSpawnRules(EntityType<? extends AnimalEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> callback)
	{
		if ((world.getBlockState(pos.below()).is(GNSBlocks.dream_grass_block) || world.getBlockState(pos.below()).is(GNSBlocks.nightmare_grass_block)) && world.canSeeSkyFromBelowWater(pos))
			callback.setReturnValue(true);
	}
}