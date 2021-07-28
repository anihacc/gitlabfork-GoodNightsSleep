package com.legacy.goodnightsleep.mixin;

//@Mixin(Animal.class)
public class AnimalEntityMixin
{
	/*@Inject(at = @At("HEAD"), method = "checkAnimalSpawnRules(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/IWorld;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)Z", cancellable = true)
	private static void checkAnimalSpawnRules(EntityType<? extends Animal> type, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> callback)
	{
		if ((world.getBlockState(pos.below()).is(GNSBlocks.dream_grass_block) || world.getBlockState(pos.below()).is(GNSBlocks.nightmare_grass_block)) && world.canSeeSkyFromBelowWater(pos))
			callback.setReturnValue(true);
	}*/
}