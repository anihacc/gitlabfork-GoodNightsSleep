package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldChunkManagerGoodDream extends BiomeProviderSingle
{

	public WorldChunkManagerGoodDream()
	{
		super(GNSWorld.good_dream_plains);
	}
	
	public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_)
    {
        return p_76939_1_;
    }

}