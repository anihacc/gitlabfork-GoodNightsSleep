package com.legacy.goodnightsleep;

import net.minecraft.util.ResourceLocation;

public class VariableConstants 
{

	public static final String NAME = "Good Night's Sleep";

	public static final String VERSION = "0.1.0";

	public static final String MODID = "goodnightsleep";

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}
}