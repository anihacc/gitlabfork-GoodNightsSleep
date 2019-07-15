package com.legacy.goodnightsleep.client.audio;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GoodNightSleepClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		//GNSEntityRendering.initialization();
		MinecraftForge.EVENT_BUS.register(new GNSMusicHandler());
	}
}