package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents
{
	private static final Minecraft mc = Minecraft.getInstance();

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public void onFogRender(RenderFogEvent event)
	{
		if (shouldNightmareFogRender())
		{
			RenderSystem.fogMode(GlStateManager.FogMode.EXP);
			RenderSystem.fogDensity(0.035F * 1);
			RenderSystem.enableFog();
		}
	}

	@SubscribeEvent
	public void onFogRender(FogColors event)
	{
		if (shouldNightmareFogRender())
		{
			event.setRed(0.2F);
			event.setBlue(0.05F);
			event.setGreen(0.05F);
		}
	}

	private static boolean shouldNightmareFogRender()
	{
		return mc.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false) && !mc.player.areEyesInFluid(FluidTags.LAVA) && !mc.player.areEyesInFluid(FluidTags.WATER) && !mc.player.isPotionActive(Effects.BLINDNESS);
	}
}
