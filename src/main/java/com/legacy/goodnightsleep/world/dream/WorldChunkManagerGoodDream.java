package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerGoodDream extends WorldChunkManagerHell
{

	public WorldChunkManagerGoodDream()
	{
		super(GNSWorld.good_dream_plains, 0.0F);
	}
	
	public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_)
    {
        return p_76939_1_;
    }

}