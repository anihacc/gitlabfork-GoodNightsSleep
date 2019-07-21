package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.render.GNSEntityRendering;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GoodNightSleepClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		GNSEntityRendering.initialization();
		MinecraftForge.EVENT_BUS.register(new GNSMusicHandler());
		
		GoodNightSleepClient.cheese();
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLuxuriousBed.class, new TileEntityLuxuriousBedRenderer());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWretchedBed.class, new TileEntityWretchedBedRenderer());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStrangeBed.class, new TileEntityStrangeBedRenderer());
	}
	
	public static void onRegisterModels(ModelRegistryEvent event)
	{
		System.out.println("Register tile cheese");

		
		
	}

	@OnlyIn(Dist.CLIENT)
	public static void cheese()
	{
		ClientSpecificStuff.fleep();
	}
}