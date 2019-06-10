package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.renders.GNSEntityRenderingRegistry;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInitialization()
	{
		GNSEntityRenderingRegistry.initialize();

		registerEvent(new GNSMusicHandler());
		MinecraftForge.EVENT_BUS.register(new GNSClientEventHandler());
	}

	@Override
	public void initialization()
	{
		gnsFlowerRenderID = RenderingRegistry.getNextAvailableRenderId();
		gnsTallgrassRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		//ItemRendering.init();
		//BlockRendering.init();
	}

	@Override
	public EntityPlayer getThePlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}