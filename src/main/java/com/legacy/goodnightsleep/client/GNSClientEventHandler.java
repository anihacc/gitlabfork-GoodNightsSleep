package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.client.gui.GuiLoadingDreams;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSClientEventHandler
{	
	private final Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event)
	{
		if (mc.thePlayer != null && event.getGui() instanceof GuiDownloadTerrain) 
		{
			GuiLoadingDreams guiEnterDream = new GuiLoadingDreams(false);
			GuiLoadingDreams guiEnterNightmare = new GuiLoadingDreams(true);
			
			if (mc.thePlayer.dimension == GNSConfig.getNightmareDimensionID())
			{
				event.setGui(guiEnterNightmare);
			}
			
			if (mc.thePlayer.dimension == GNSConfig.getDreamDimensionID())
			{
				event.setGui(guiEnterDream);
			}
		}
	}
	
}