package com.legacy.goodnightsleep.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class VariableConstants 
{

	public static final String NAME = "Good Night's Sleep";

	public static final String VERSION = "0.0.2";

	public static final String MODID = "goodnightsleep";

	public static final String CLIENT_PROXY_LOCATION = "com.legacy.goodnightsleep.client.ClientProxy";

	public static final String COMMON_PROXY_LOCATION = "com.legacy.goodnightsleep.CommonProxy";

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}
	
	public static String find(String location)
	{
		return MODID + ":" + location;
	}

	public static void registerEvent(Object obj)
	{
		MinecraftForge.EVENT_BUS.register(obj);
	}
}