package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.types.Type;

import net.minecraft.util.Util;

@Mixin(Util.class)
public class UtilMixin
{
	@Inject(at = @At(value = "INVOKE", target = "net/minecraft/util/datafix/DataFixesManager.getDataFixer()Lcom/mojang/datafixers/DataFixer;"), method = "attemptDataFixInternal(Lcom/mojang/datafixers/DSL$TypeReference;Ljava/lang/String;)Lcom/mojang/datafixers/types/Type;", cancellable = true)
	private static void attemptDataFixInternal(TypeReference typeIn, String choiceName, CallbackInfoReturnable<Type<?>> callback)
	{
		if (choiceName.startsWith(GoodNightSleep.MODID))
			callback.setReturnValue(null);
	}
}
