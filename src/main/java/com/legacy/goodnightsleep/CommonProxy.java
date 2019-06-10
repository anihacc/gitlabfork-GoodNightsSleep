package com.legacy.goodnightsleep;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy 
{
	
	public static int gnsFlowerRenderID;
	public static int gnsTallgrassRenderID;
	public static int rainbowCropRenderID;
	
	public void preInitialization() { }

	public void initialization() { }

	public EntityPlayer getThePlayer() { return null; }

	public static void registerEvent(Object event)
	{
		MinecraftForge.EVENT_BUS.register(event);
	}
}