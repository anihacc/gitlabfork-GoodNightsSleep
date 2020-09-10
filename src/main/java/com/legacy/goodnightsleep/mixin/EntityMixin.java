package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;

@Mixin(Entity.class)
public class EntityMixin
{
	@Inject(at = @At("HEAD"), method = "getMountedYOffset()D", cancellable = true)
	private void getMountedYOffset(CallbackInfoReturnable<Double> callback)
	{
		if (getThis() instanceof ZombieHorseEntity)
		{
			callback.setReturnValue(getThis().getType().getSize().height * 0.75D - 0.2D);
		}
	}

	public Entity getThis()
	{
		return (Entity) (Object) this;
	}
}
