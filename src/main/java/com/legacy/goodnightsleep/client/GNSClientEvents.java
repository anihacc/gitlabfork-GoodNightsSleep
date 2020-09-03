package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSClientEvents
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
	public void onFogColor(FogColors event)
	{
		if (shouldNightmareFogRender())
		{
			event.setRed(0.2F);
			event.setBlue(0.05F);
			event.setGreen(0.05F);
		}
	}

	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event)
	{
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		World world = mc.world;

		if (world == null || !world.isRemote)
			return;

		if (mc.player != null && DreamPlayer.get(mc.player) != null)
			DreamPlayer.get(mc.player).clientTick();
	}

	private static boolean shouldNightmareFogRender()
	{
		return mc.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false) && !mc.player.areEyesInFluid(FluidTags.LAVA) && !mc.player.areEyesInFluid(FluidTags.WATER) && !mc.player.isPotionActive(Effects.BLINDNESS);
	}

	// i am going insane
	public static float calculateSunAngle(long worldTimeIn, float partialTicks)
	{
		long worldTime;
		PlayerEntity player = mc.player;

		if (DreamPlayer.get(player) != null)
		{
			worldTime = worldTimeIn - DreamPlayer.get(player).getEnteredDreamTime();
		}
		else
			worldTime = worldTimeIn;

		int j = (int) (worldTime % 48000L);
		float f1 = ((float) j + partialTicks) / 48000.0F - 0.25F;

		if (player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false))
			f1 += 0.5F;

		if (f1 < 0.0F)
		{
			++f1;
		}

		if (f1 > 1.0F)
		{
			--f1;
		}

		float f2 = f1;
		f1 = 1.0F - (float) ((Math.cos((double) f1 * Math.PI) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;

		return f1;
	}
}
