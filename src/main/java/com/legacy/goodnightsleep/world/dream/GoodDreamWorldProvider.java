package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GoodDreamWorldProvider extends WorldProvider
{

	@Override
	protected void createBiomeProvider()
	{
		this.hasNoSky = false;
		this.biomeProvider = new WorldChunkManagerGoodDream();
	}
	
	
	@Override
    public IChunkGenerator createChunkGenerator()
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
	 
	@Override
	public DimensionType getDimensionType()
	{
		return GNSWorld.dream_dimension_type;
	}
	
	/*public float func_76563_a(long par1, float par3) {
	      int j = (int)(par1 % 48000L);
	      float f1 = ((float)j + par3) / 48000.0F - 0.25F;
	      if(f1 < 0.0F) {
	         ++f1;
	      }

	      if(f1 > 1.0F) {
	         --f1;
	      }

	      float f2 = f1;
	      f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
	      f1 = f2 + (f1 - f2) / 3.0F;
	      if(par1 > 25000L) {
	         ArrayList plyMPList = new ArrayList();
	         Iterator iterator = this.field_76579_a.field_73010_i.iterator();

	         EntityPlayerMP playerMP;
	         while(iterator.hasNext()) {
	            Object i = iterator.next();
	            if(i instanceof EntityPlayerMP) {
	               playerMP = (EntityPlayerMP)i;
	               if(playerMP.field_71093_bK == this.field_76574_g) {
	                  plyMPList.add(playerMP);
	               }
	            }
	         }

	         for(int var14 = 0; var14 < plyMPList.size(); ++var14) {
	            playerMP = (EntityPlayerMP)plyMPList.get(var14);
	            int x = playerMP.func_82114_b().field_71574_a;
	            int y = playerMP.func_82114_b().field_71572_b;
	            int z = playerMP.func_82114_b().field_71573_c;
	            TeleporterGNS.getTeleporterGNS().teleport(this.field_76579_a, x, y, z, playerMP, this.field_76574_g, 0);
	         }
	      }

	      return f1;
	   }*/

	   @SideOnly(Side.CLIENT)
	   @Override
	   public Vec3d getFogColor(float par1, float par2) 
	   {
	      return new Vec3d(0.843172549D, 1.0D, 1.0D);
	   }

}