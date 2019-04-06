package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GoodDreamWorldProvider extends WorldProvider
{

	@Override
	protected void init()
	{
		this.hasSkyLight = true;
		this.biomeProvider = new WorldChunkManagerGoodDream();
	}
	
	
	@Override
    public IChunkGenerator createChunkGenerator()
    {
    	return new ChunkProviderGoodDream(world, this.getSeed());
    }

	@Override
    public boolean canRespawnHere()
    {
        return false;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0;
	}

	@Override
	public DimensionType getDimensionType()
	{
		return GNSWorld.dream_dimension_type;
	}

}