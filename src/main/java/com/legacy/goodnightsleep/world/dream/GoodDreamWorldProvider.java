package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.GNSConfig;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GoodDreamWorldProvider extends WorldProvider
{

	@Override
	protected void registerWorldChunkManager()
	{
		this.hasNoSky = false;
		this.worldChunkMgr = new WorldChunkManagerGoodDream();
	}
	
	
	@Override
    public IChunkProvider createChunkGenerator()
    {
    	return new ChunkProviderGoodDream(worldObj, this.getSeed());
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

	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float brightness) 
	{
		return 1.0F;
	}
	 
	public int getDimensionId()
    {
        return GNSConfig.getDreamDimensionID();
    }
	
	@SideOnly(Side.CLIENT)
   @Override
   public Vec3 getFogColor(float par1, float par2) 
   {
      return new Vec3(0.843172549D, 1.0D, 1.0D);
   }

   @Override
	public String getDimensionName() 
	{
		return "GoodDream";
	}

	@Override
	public String getInternalNameSuffix()
	{
		return "_GoodDream";
	}
}