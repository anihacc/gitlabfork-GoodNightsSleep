package com.legacy.goodnightsleep.client;

import java.util.ArrayList;

import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents
{
	private static final Minecraft mc = Minecraft.getInstance();
	public static ArrayList<ServerPlayerEntity> dreamPlayerList = new ArrayList<ServerPlayerEntity>();
	public static float flo = 0F;
	public static long playerTime = 0L;

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
	}

	private static boolean shouldNightmareFogRender()
	{
		return mc.world.getDimensionKey() == GNSDimensions.getDimensionKeys(false) && !mc.player.areEyesInFluid(FluidTags.LAVA) && !mc.player.areEyesInFluid(FluidTags.WATER) && !mc.player.isPotionActive(Effects.BLINDNESS);
	}

	// i am going insane
	public static float getTime(long worldTime, float partialTicks)
	{
		int j = (int) (worldTime % 48000L);
		float f1 = ((float) j + partialTicks) / 48000.0F - 0.25F;

		if (f1 < 0.0F)
		{
			++f1;
		}

		if (f1 > 1.0F)
		{
			--flo;
		}

		float f2 = f1;
		f1 = 1.0F - (float) ((Math.cos((double) f1 * Math.PI) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;

		/*if (worldTime > 25000L)
		{
			Iterator<?> iterator = mc.world.getPlayers().iterator();
		
			ServerPlayerEntity playerMP;
			while (iterator.hasNext())
			{
				Object i = iterator.next();
				if (i instanceof ServerPlayerEntity)
				{
					playerMP = (ServerPlayerEntity) i;
		
					// if (playerMP.dimension == GNSDimensions.dimensionType(true))
					{
						dreamPlayerList.add(playerMP);
					}
				}
			}
		
			for (int var14 = 0; var14 < dreamPlayerList.size(); ++var14)
			{
				System.out.println("time to leave");
				playerMP = (ServerPlayerEntity) dreamPlayerList.get(var14);
				
				BlockPos pos = playerMP.func_241140_K_(); //playerMP.getBedLocation(DimensionType.OVERWORLD) != null ? playerMP.getBedLocation(DimensionType.OVERWORLD) : playerMP.world.getSpawnPoint();
				GNSTeleporter.changeDimension(World.OVERWORLD, playerMP, pos);
			}
		}*/

		return f1;
	}
}
