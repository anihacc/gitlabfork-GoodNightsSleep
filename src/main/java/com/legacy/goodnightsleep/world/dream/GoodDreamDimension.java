package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.GNSRegistryHandler;
import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GoodDreamDimension extends Dimension
{

	private final DimensionType type;

	public GoodDreamDimension(DimensionType type)
	{
		this.type = type;
	}

	@Override
	protected void init()
	{
		this.hasSkyLight = true;
	}

	@Override
	public IChunkGenerator<?> createChunkGenerator()
	{
		OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();
		genSettings.setDefautBlock(BlocksGNS.delusion_stone.getDefaultState());
		
		return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.FIXED.createSettings().setBiome(BiomeGoodDreamPlains.INSTANCE)), genSettings);
	}

	@Override
    public boolean canRespawnHere()
    {
        return false;
    }

	@Override
	@OnlyIn(Dist.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }
	
	public float getCurrentMoonPhaseFactor()
    {
        return 2.0F;
    }
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0;
	}

	@OnlyIn(Dist.CLIENT)
	public float getStarBrightness(float brightness) 
	{
		return 10.0F;
	}
	
	@OnlyIn(Dist.CLIENT)
    public float getCloudHeight()
    {
        return 100F;
    }
	
	@Override
	public boolean shouldMapSpin(String entity, double x, double z, double rotation)
    {
        return true;
    }

	@OnlyIn(Dist.CLIENT)
	public String getWelcomeMessage()
	{
		return "You dream of peaceful lands...";
	}
	 
	
	@Override
	public DimensionType getType()
	{
		return this.type;
	}
	
	@Override
	public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid)
	{
		return null;
	}

	@Override
	public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid)
	{
		return null;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
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

	@OnlyIn(Dist.CLIENT)
	@Override
	public Vec3d getFogColor(float par1, float par2)
	{
		return new Vec3d(0.843172549D, 1.0D, 1.0D);
	}

}