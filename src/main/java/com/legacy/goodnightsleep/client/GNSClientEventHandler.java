package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.client.gui.GuiLoadingDreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GNSClientEventHandler
{	
	private final Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event)
	{
		if (mc.thePlayer != null && event.gui instanceof GuiDownloadTerrain) 
		{
			GuiLoadingDreams guiEnterDream = new GuiLoadingDreams(false);
			GuiLoadingDreams guiEnterNightmare = new GuiLoadingDreams(true);
			
			if (mc.thePlayer.dimension == GNSConfig.getNightmareDimensionID())
			{
				event.gui = guiEnterNightmare;
			}
			
			if (mc.thePlayer.dimension == GNSConfig.getDreamDimensionID())
			{
				event.gui = guiEnterDream;
			}
		}
	}
	
}