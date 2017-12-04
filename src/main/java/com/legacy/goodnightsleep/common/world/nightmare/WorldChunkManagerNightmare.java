package com.legacy.goodnightsleep.common.world.nightmare;

import com.legacy.goodnightsleep.common.world.GNSWorld;

import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldChunkManagerNightmare extends BiomeProviderSingle
{

	public WorldChunkManagerNightmare()
	{
		super(GNSWorld.nightmare_hills);
	}

}