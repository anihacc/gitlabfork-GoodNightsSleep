package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.render.GNSEntityRendering;
import com.legacy.goodnightsleep.client.render.GNSTileEntityRendering;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GoodNightSleepClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		GNSTileEntityRendering.initialization();
		GNSEntityRendering.initialization();
		MinecraftForge.EVENT_BUS.register(new GNSMusicHandler());
		
	}
}