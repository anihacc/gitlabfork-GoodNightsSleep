package com.legacy.goodnightsleep.common.world.nightmare;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderNightmare implements IChunkGenerator 
{

	public World worldObj;

	public Random random;

	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;

	private double[] noiseArray;
	private double[] stoneNoise = new double[256];

	private Biome[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	float[] parabolicField;
	int[][] field_73219_j = new int[32][32];
	
	private MapGenBase ravineGenerator = new MapGenRavine();
	private MapGenBase caveGenerator = new MapGenCaves();

	public ChunkProviderNightmare(World world, long seed)
	{
		this.random = new Random(seed);
		this.worldObj = world;
		this.noiseGen1 = new NoiseGeneratorOctaves(this.random, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.random, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.random, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.random, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.random, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.random, 16);
		
        ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
		caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
	}


	public void generateTerrain(int x, int z, ChunkPrimer chunkPrimer)
	{
		byte b0 = 4;
		byte b1 = 16;
		byte b2 = 65;
		int k = b0 + 1;
		byte b3 = 17;
		int l = b0 + 1;

		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);

		this.noiseArray = this.initializeNoiseField(this.noiseArray, x * b0, 0, z * b0, k, b3, l);

		for (int i1 = 0; i1 < b0; ++i1)
		{
			for (int j1 = 0; j1 < b0; ++j1)
			{
				for (int k1 = 0; k1 < b1; ++k1)
				{
					 double d0 = 0.15D;
					 double d1 = this.noiseArray[((i1) * l + j1) * b3 + k1];
					 double d2 = this.noiseArray[((i1) * l + j1 + 1) * b3 + k1];
					 double d3 = this.noiseArray[((i1 + 1) * l + j1) * b3 + k1];
					 double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1];
					 double d5 = (this.noiseArray[((i1) * l + j1) * b3 + k1 + 1] - d1) * d0;
					 double d6 = (this.noiseArray[((i1) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
					 double d7 = (this.noiseArray[((i1 + 1) * l + j1) * b3 + k1 + 1] - d3) * d0;
					 double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

					 for (int l1 = 0; l1 < 8; ++l1)
					 {
						 double d9 = 0.25D;
						 double d10 = d1;
						 double d11 = d2;
						 double d12 = (d3 - d1) * d9;
						 double d13 = (d4 - d2) * d9;

						 for (int i2 = 0; i2 < 4; ++i2)
						 {
							 double d14 = 0.25D;
							 double d15 = (d11 - d10) * d14;
							 double d16 = d10 - d15;

							 for (int k2 = 0; k2 < 4; ++k2)
							 {
								 if ((d16 += d15) > 0.0D)
								 {
									 chunkPrimer.setBlockState(i1 * 4 + i2, k1 * 8 + l1, j1 * 4 + k2, Blocks.STONE.getDefaultState());
								 }
								 else if (k1 * 8 + l1 < b2)
								 {
									 chunkPrimer.setBlockState(i1 * 4 + i2, k1 * 8 + l1, j1 * 4 + k2, Blocks.LAVA.getDefaultState());
								 }
								 else
								 {
									 chunkPrimer.setBlockState(i1 * 4 + i2, k1 * 8 + l1, j1 * 4 + k2, Blocks.AIR.getDefaultState());
								 }
							 }

							 d10 += d12;
							 d11 += d13;
						 }
						 d1 += d5;
						 d2 += d6;
						 d3 += d7;
						 d4 += d8;
					 }
				}
			}
		}
	}
	public void replaceBlocksForBiome(int x, int z, ChunkPrimer chunkPrimer)
	{
		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, x * 16, z * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
				int var12 = (int)(this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int var13 = -1;

				IBlockState topBlock = BlocksGNS.nightmare_grass.getDefaultState();
				IBlockState fillerBlock = Blocks.DIRT.getDefaultState();

				for (int var16 = 127; var16 >= 0; var16--)
				{
					 if (var16 <= 0 + this.random.nextInt(5))
					 {
						 chunkPrimer.setBlockState(var8, var16, var9, Blocks.BEDROCK.getDefaultState());
					 }
					 else
					 {
						 IBlockState var18 = chunkPrimer.getBlockState(var8, var16, var9);

						 if (var18.getBlock() == Blocks.AIR)
						 {
							 var13 = -1;
							 continue;
						 }

						 if (var18.getBlock() != Blocks.STONE)
						 {
							 continue;
						 }

						 if (var13 == -1)
						 {
							 var13 = var12;

							 chunkPrimer.setBlockState(var8, var16 + 1, var9, topBlock);
							 chunkPrimer.setBlockState(var8, var16, var9, fillerBlock);
						 }

						 if (var13 <= 0)
						 {
							 continue;
						 }

						 chunkPrimer.setBlockState(var8, var16, var9, fillerBlock);
						 var13--;
					 }
				}
			}
		}
	}

	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
	{
		if (par1ArrayOfDouble == null)
		{
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}

		if (this.parabolicField == null)
		{
			this.parabolicField = new float[25];
			for (int var8 = -2; var8 <= 2; var8++)
			{
				for (int var9 = -2; var9 <= 2; var9++)
				{
					float var10 = 10.0F / MathHelper.sqrt(var8 * var8 + var9 * var9 + 0.2F);
					this.parabolicField[(var8 + 2 + (var9 + 2) * 5)] = var10;
				}
			}
		}

		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, 8.55515D, 4.277575D, 8.55515D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, 684.412D, 684.412D, 684.412D);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, 684.412D, 684.412D, 684.412D);

		int var12 = 0;
		int var13 = 0;

		for (int var14 = 0; var14 < par5; var14++)
		{
			for (int var15 = 0; var15 < par7; var15++)
			{
				float var16 = 0.0F;
				float var17 = 0.0F;
				float var18 = 0.0F;

				Biome biome = this.biomesForGeneration[(var14 + 2 + (var15 + 2) * (par5 + 5))];

				for (int var21 = -2; var21 <= 2; var21++)
				{
					for (int var22 = -2; var22 <= 2; var22++)
					{
						Biome currentBiome = this.biomesForGeneration[(var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5))];

						float var24 = this.parabolicField[(var21 + 2 + (var22 + 2) * 5)] / (currentBiome.getHeightVariation() + 2.0F);

						if (currentBiome.getHeightVariation() > biome.getHeightVariation())
						{
							var24 /= 2.0F;
						}

						var16 += currentBiome.getHeightVariation() * var24;
						var17 += currentBiome.getHeightVariation() * var24;
						var18 += var24;
					}
				}

				var16 /= var18;
				var17 /= var18;
				var16 = var16 * 0.9F + 0.1F;
				var17 = (var17 * 4.0F - 1.0F) / 8.0F;

				double var47 = this.noise6[var13] / 8000.0D;

				if (var47 < 0.0D)
				{
					var47 = -var47 * 0.3D;
				}

				var47 = var47 * 3.0D - 2.0D;

				if (var47 < 0.0D)
				{
					var47 /= 2.0D;

					if (var47 < -1.0D)
					{
						var47 = -1.0D;
					}
					var47 /= 1.4D;

					var47 /= 2.0D;
				}
				else
				{
					if (var47 > 1.0D)
					{
						var47 = 1.0D;
					}
					var47 /= 8.0D;
				}

				var13++;

				for (int var46 = 0; var46 < par6; var46++)
				{
					double var48 = var17;
					double var26 = var16;
					var48 += var47 * 0.2D;
					var48 = var48 * par6 / 16.0D;
					double var28 = par6 / 2.0D + var48 * 4.0D;
					double var30 = 0.0D;
					double var32 = (var46 - var28) * 12.0D * 128.0D / 128.0D / var26;

					if (var32 < 0.0D)
					{
						var32 *= 4.0D;
					}

					double var34 = this.noise1[var12] / 512.0D;
					double var36 = this.noise2[var12] / 512.0D;
					double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;

					if (var38 < 0.0D)
					{
						var30 = var34;
					}
					else if (var38 > 1.0D)
					{
						var30 = var36;
					}
					else
					{
						var30 = var34 + (var36 - var34) * var38;
					}

					var30 -= var32;

					if (var46 > par6 - 4)
					{
						double var40 = (var46 - (par6 - 4)) / 3.0F;
						var30 = var30 * (1.0D - var40) + -10.0D * var40;
					}

					par1ArrayOfDouble[var12] = var30;
					var12++;
				}
			}
		}

		return par1ArrayOfDouble;
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();

		this.generateTerrain(x, z, primer);
		this.replaceBlocksForBiome(x, z, primer);
		
		this.caveGenerator.generate(this.worldObj, x, z, primer);
		this.ravineGenerator.generate(this.worldObj, x, z, primer);
		
		Chunk chunk  = new Chunk(this.worldObj, primer, x, z);

		chunk.generateSkylightMap(); // Light, that's all

		return chunk;
	}

	@Override
	public void populate(int x, int z) 
	{
        BlockFalling.fallInstantly = true;

        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        
        boolean flag = false;
		
		 if (!flag && this.random.nextInt(80 / 10) == 0)
		 {
		        if (TerrainGen.populate(this, this.worldObj, this.random, x, z, flag, EventType.LAVA))
		        {
		            int i2 = this.random.nextInt(16) + 8;
		            int l2 = this.random.nextInt(this.random.nextInt(248) + 8);
		            int k3 = this.random.nextInt(16) + 8;

		            if (l2 < this.worldObj.getSeaLevel() || this.random.nextInt(80 / 8) == 0)
		            {
		                (new WorldGenLakes(Blocks.LAVA)).generate(this.worldObj, this.random, blockpos.add(i2, l2, k3));
		            }
		        }
		 }
		 
		 
		 //if (this.random.nextInt(13) == 0)
	        //{
	        	if (TerrainGen.populate(this, this.worldObj, this.random, x, z, flag, EventType.LAKE))
	        	{
	        		int i1 = this.random.nextInt(16) + 8;
	        		int j1 = this.random.nextInt(this.random.nextInt(this.random.nextInt(112) + 8) + 8);
	        		int k1 = this.random.nextInt(16) + 8;
	        		(new WorldGenLakes(Blocks.LAVA)).generate(this.worldObj, this.random, blockpos.add(i1, j1, k1));
	        	}
	        //}
	        	
	        	int var12;
	            int var13;
	            int var14;
	            if(!flag && this.random.nextInt(4) == 0) 
	            {
	            if (TerrainGen.populate(this, this.worldObj, this.random, x, z, flag, EventType.LAKE))
	            	
	            {
	               var12 = i + this.random.nextInt(16) + 8;
	               var13 = this.random.nextInt(128);
	               var14 = j + this.random.nextInt(16) + 8;
	               (new WorldGenLakes(Blocks.LAVA)).generate(this.worldObj, this.random, blockpos.add(var12, var13, var14));
	            }
	            }

	            if(!flag && this.random.nextInt(8) == 0) 
	            {
	            	if (TerrainGen.populate(this, this.worldObj, this.random, x, z, flag, EventType.LAKE))
		        	{
	               var12 = i + this.random.nextInt(16) + 8;
	               var13 = this.random.nextInt(this.random.nextInt(120) + 8);
	               var14 = j + this.random.nextInt(16) + 8;
	               if(var13 < 63 || this.random.nextInt(10) == 0) 
	               {
	                  (new WorldGenLakes(Blocks.LAVA)).generate(this.worldObj, this.random, blockpos.add(var12, var13, var14));
	               }
	            }       
	            }

        this.random.setSeed(this.worldObj.getSeed());
        long k = this.random.nextLong() / 2L * 2L + 1L;
        long l = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)x * k + (long)z * l ^ this.worldObj.getSeed());

		this.worldObj.getBiome(blockpos.add(16, 0, 16)).decorate(this.worldObj, this.random, new BlockPos(i, 0, j));

        BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return true;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) 
	{
		return this.worldObj.getBiome(pos).getSpawnableList(creatureType);
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) 
	{

	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) 
	{
		return null;
	}

}