package com.legacy.goodnightsleep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.dream.DreamSkyRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin
{
	// func_228424_a_
	@Inject(at = @At("HEAD"), method = "renderSky(Lcom/mojang/blaze3d/matrix/MatrixStack;F)V", cancellable = true)
	private void renderSky(MatrixStack matrixStackIn, float partialTicks, CallbackInfo callback)
	{
		if (Minecraft.getInstance().world.getDimensionKey() == GNSDimensions.getDimensionKeys(true))
		{
			//Minecraft.getInstance().player.ticksExisted
			DreamSkyRenderer.INSTANCE.render(Minecraft.getInstance().player.ticksExisted, partialTicks, matrixStackIn, Minecraft.getInstance().world, Minecraft.getInstance());			
			callback.cancel();
		}
	}
}
