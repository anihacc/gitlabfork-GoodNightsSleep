package com.legacy.goodnightsleep.common.world.nightmare;

import com.legacy.goodnightsleep.common.world.GNSWorld;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NightmareWorldProvider extends WorldProvider
{

	@Override
	protected void init()
	{
		this.doesWaterVaporize = true;
		this.hasSkyLight = true;
		this.biomeProvider = new WorldChunkManagerNightmare();
	}
	
	
	@Override
    public IChunkGenerator createChunkGenerator()
    {
    	return new ChunkProviderNightmare(world, this.getSeed());
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
		return 0.5F;
	}

	@Override
	public DimensionType getDimensionType()
	{
		return GNSWorld.nightmare_dimension_type;
	}

}