package com.legacy.goodnightsleep.registry;

import net.minecraft.util.ResourceLocation;

public class GNSSounds 
{
	public static ResourceLocation MUSIC_GOOD_DREAM;
	public static ResourceLocation MUSIC_NIGHTMARE;

	public static void initialization()
	{
		MUSIC_GOOD_DREAM = register("music.good_dream");
		MUSIC_NIGHTMARE = register("music.tfarcenim");
	}

	public static ResourceLocation register(String location)
	{
		return new ResourceLocation(VariableConstants.MODID + ":" + location);
	}

}