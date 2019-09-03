package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.GNSRegistryHandler;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NightmareDimension extends Dimension
{

	public NightmareDimension(World world, DimensionType type)
	{
		super(world, type);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator()
	{
		OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();
		genSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
		genSettings.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.FIXED.createSettings().setBiome(NightmareHillsBiome.INSTANCE)), genSettings);
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
        return true;
    }
	
	public float getCurrentMoonPhaseFactor()
    {
        return 2.0F;
    }
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0.5F;
	}

	@OnlyIn(Dist.CLIENT)
	public String getWelcomeMessage()
	{
		return "You dream of horrible things...";
	}

	@OnlyIn(Dist.CLIENT)
	public float getStarBrightness(float brightness) 
	{
		return 1.0F;
	}
	
	@OnlyIn(Dist.CLIENT)
    public float getCloudHeight()
    {
        return 150F;
    }
	
	@Override
	public boolean shouldMapSpin(String entity, double x, double z, double rotation)
    {
        return true;
    }
	
	@Override
	public DimensionType getType()
	{
		return GNSRegistryHandler.nightmareType();
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
	public Vec3d getFogColor(float celestialAngle, float partialTicks)
	{
		 return new Vec3d(0.1D, 0.0D, 0.0D);
	}

	@Override
	public boolean hasSkyLight()
	{
		return true;
	}
}