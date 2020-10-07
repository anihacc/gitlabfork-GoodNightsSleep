package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.world.DreamSkyRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class GNSClientEvents
{
	private static final Minecraft mc = Minecraft.getInstance();
	private static final DimensionRenderInfo DREAM_RENDER_INFO = new DreamRenderInfo();
	private static final DimensionRenderInfo NIGHTMARE_RENDER_INFO = new NightmareRenderInfo();

	private static final Object2ObjectMap<ResourceLocation, DimensionRenderInfo> DIMENSION_RENDER_INFO = ObfuscationReflectionHelper.getPrivateValue(DimensionRenderInfo.class, DREAM_RENDER_INFO, "field_239208_a_");

	public static void initDimensionRenderInfo()
	{
		DIMENSION_RENDER_INFO.put(GNSDimensions.DREAM_ID, DREAM_RENDER_INFO);
		DIMENSION_RENDER_INFO.put(GNSDimensions.NIGHTMARE_ID, NIGHTMARE_RENDER_INFO);
	}

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

	public static class DreamRenderInfo extends DimensionRenderInfo.Overworld
	{
		public DreamRenderInfo()
		{
			super();
		}

		@Override
		public ISkyRenderHandler getSkyRenderHandler()
		{
			return DreamSkyRenderer.INSTANCE;
		}
	}

	public static class NightmareRenderInfo extends DimensionRenderInfo.Overworld
	{
		public NightmareRenderInfo()
		{
			super();
		}

		@Override
		public Vector3d func_230494_a_(Vector3d fogColor, float fogBrightness)
		{
			return fogColor;
		}

		@Override
		public boolean func_230493_a_(int posX, int posZ)
		{
			return true;
		}

		@Override
		public ISkyRenderHandler getSkyRenderHandler()
		{
			return DreamSkyRenderer.INSTANCE;
		}
	}
}
