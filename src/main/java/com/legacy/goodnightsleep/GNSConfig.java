package com.legacy.goodnightsleep;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

public class GNSConfig 
{
	private static int dream_biome_id, dream_dimension_id;
	
	private static int nightmare_biome_id, nightmare_dimension_id;
	
	public static void init(File location)
	{
		File newFile = new File(location + "/goodnightsleep" + "/GoodNightSleep.cfg");

		try
		{
			newFile.createNewFile();
		}
		catch (IOException e)
		{

		}

		Configuration config = new Configuration(newFile);

		config.load();

		dream_dimension_id = config.get("World Identification", "Dream Dimension ID", 44).getInt(4);
		dream_biome_id = config.get("World Identification", "Dream Plains Biome ID", 127).getInt(127);
		
		nightmare_dimension_id = config.get("World Identification", "Nightmare Dimension ID", 45).getInt(5);
		nightmare_biome_id = config.get("World Identification", "Nightmare Hills Biome ID", 128).getInt(128);
		
		config.save();
	}

	public static int getDreamDimensionID()
	{
		return GNSConfig.dream_dimension_id;
	}

	public static int getDreamBiomeID()
	{
		return GNSConfig.dream_biome_id;
	}
	
	public static int getNightmareDimensionID()
	{
		return GNSConfig.nightmare_dimension_id;
	}

	public static int getNightmareBiomeID()
	{
		return GNSConfig.nightmare_biome_id;
	}
	
}