package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.GNSConfig;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class NightmareWorldProvider extends WorldProvider
{

	@Override
	protected void registerWorldChunkManager()
	{
		// this.doesWaterVaporize = true;
		this.hasNoSky = false;
		this.worldChunkMgr = new WorldChunkManagerNightmare();
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderNightmare(worldObj, this.getSeed());
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
		return true;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0.5F;
	}

	public int getDimensionId()
    {
        return GNSConfig.getNightmareDimensionID();
    }

	@Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float celestialAngle, float partialTicks)
	{
		 return Vec3.createVectorHelper(0.1D, 0.0D, 0.0D);
	}

	@Override
	public String getDimensionName()
	{
		return "Nightmare";
	}
}