package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.registry.GNSDimensions;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class GNSClientEvents
{
	private static final Minecraft mc = Minecraft.getInstance();
	private static final DimensionSpecialEffects DREAM_RENDER_INFO = new DreamRenderInfo();
	private static final DimensionSpecialEffects NIGHTMARE_RENDER_INFO = new NightmareRenderInfo();

	private static final Object2ObjectMap<ResourceLocation, DimensionSpecialEffects> DIMENSION_RENDER_INFO = ObfuscationReflectionHelper.getPrivateValue(DimensionSpecialEffects.class, DREAM_RENDER_INFO, "f_108857_");

	public static void initDimensionRenderInfo()
	{
		DIMENSION_RENDER_INFO.put(GNSDimensions.DREAM_ID, DREAM_RENDER_INFO);
		DIMENSION_RENDER_INFO.put(GNSDimensions.NIGHTMARE_ID, NIGHTMARE_RENDER_INFO);
	}

	@SubscribeEvent
	public void onAtlasStich(TextureStitchEvent.Pre event)
	{
		if (event.getMap().location().equals(Sheets.BED_SHEET))
		{
			event.addSprite(GoodNightSleep.locate("entity/luxurious_bed"));
			event.addSprite(GoodNightSleep.locate("entity/wretched_bed"));
			event.addSprite(GoodNightSleep.locate("entity/strange_bed"));
		}
	}

	@SubscribeEvent
	public void onFogRender(RenderFogEvent event)
	{
		// 0.035F
	}

	@SubscribeEvent
	public void onFogColor(FogColors event)
	{
		/*if (shouldNightmareFogRender())
		{
			event.setRed(0.2F);
			event.setBlue(0.05F);
			event.setGreen(0.05F);
		}*/
	}

	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event)
	{
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		Level world = mc.level;

		if (world == null || !world.isClientSide)
			return;

		if (mc.player != null && DreamPlayer.get(mc.player) != null)
			DreamPlayer.get(mc.player).clientTick();
	}

	private static boolean shouldNightmareFogRender()
	{
		return mc.level.dimension() == GNSDimensions.getDimensionKeys(false) && !mc.player.isEyeInFluid(FluidTags.LAVA) && !mc.player.isEyeInFluid(FluidTags.WATER) && !mc.player.hasEffect(MobEffects.BLINDNESS);
	}

	// i am going insane
	public static float calculateSunAngle(long worldTimeIn, float partialTicks)
	{
		long worldTime;
		Player player = mc.player;

		if (DreamPlayer.get(player) != null)
		{
			worldTime = worldTimeIn - DreamPlayer.get(player).getEnteredDreamTime();
		}
		else
			worldTime = worldTimeIn;

		int j = (int) (worldTime % 48000L);
		float f1 = ((float) j + partialTicks) / 48000.0F - 0.25F;

		if (player.level.dimension() == GNSDimensions.getDimensionKeys(false))
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

	public static class DreamRenderInfo extends DimensionSpecialEffects.OverworldEffects
	{
		public DreamRenderInfo()
		{
			super();
		}

		/*@Override
		public ISkyRenderHandler getSkyRenderHandler()
		{
			return DreamSkyRenderer.INSTANCE;
		}*/
	}

	public static class NightmareRenderInfo extends DimensionSpecialEffects.OverworldEffects
	{
		public NightmareRenderInfo()
		{
			super();
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float fogBrightness)
		{
			return fogColor;
		}

		@Override
		public boolean isFoggyAt(int posX, int posZ)
		{
			return true;
		}

		/*@Override
		public ISkyRenderHandler getSkyRenderHandler()
		{
			return DreamSkyRenderer.INSTANCE;
		}*/
	}
}
