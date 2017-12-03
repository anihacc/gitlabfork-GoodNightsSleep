package com.legacy.goodnightsleep.common.world.dream;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderGoodDreamOld implements IChunkGenerator 
{

	private Random random;
	public final long seed;
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();
	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	private MapGenVillage villageGenerator = new MapGenVillage();
	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
	private MapGenBase ravineGenerator = new MapGenRavine();
	private Biome[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	float[] parabolicField;
	int[][] field_73219_j = new int[32][32];

	public ChunkProviderGoodDreamOld(World par1, long l, boolean flag) 
	{
		this.seed = l;
		this.random = new Random(this.seed);
		this.worldObj = par1;
		this.mapFeaturesEnabled = flag;
		this.rand = new Random(l);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
	}

	public void generateTerrain(int par1, int par2, ChunkPrimer primer) 
	{
		byte var4 = 4;
		byte var5 = 16;
		byte var6 = 63;
		int var7 = var4 + 1;
		byte var8 = 17;
		int var9 = var4 + 1;
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2,par2 * 4 - 2, var7 + 5, var9 + 5);
		this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * var4, 0, par2 * var4, var7, var8, var9);

		for (int var10 = 0; var10 < var4; ++var10) 
		{
			for (int var11 = 0; var11 < var4; ++var11) 
			{
				for (int var12 = 0; var12 < var5; ++var12) 
				{
					double var13 = 0.125D;
					double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
					double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
					double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
					double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
					double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
					double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
					double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
					double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

					for (int var31 = 0; var31 < 8; ++var31)
					{
						double var34 = var15;
						double var36 = var17;
						double var38 = (var19 - var15) * 0.25D;
						double var40 = (var21 - var17) * 0.25D;

						for (int var42 = 0; var42 < 4; ++var42) 
						{
							double var49 = (var36 - var34) * 0.25D;
							double var47 = var34 - var49;

							for (int var51 = 0; var51 < 4; ++var51) 
							{
								int x = var10 * 4 + var42;
								int y = var11 * 8 + var31;
								int z = var11 * 4 + var51;

								if ((var47 += var49) > 0.0D) 
								{
									primer.setBlockState(x, y, z,Blocks.STONE.getDefaultState());
								}
								else if (var12 * 8 + var31 < var6) 
								{
									primer.setBlockState(x, y, z, Blocks.WATER.getDefaultState());
								} 
								else 
								{
									primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
								}
							}

							var34 += var38;
							var36 += var40;
						}

						var15 += var23;
						var17 += var25;
						var19 += var27;
						var21 += var29;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int x, int z, ChunkPrimer primer, Biome[] biomesIn) 
	{
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.worldObj)) return;

		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, x * 16, z * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
                Biome biome = this.biomesForGeneration[var9 + var8 * 16];
				int var12 = (int)(this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int var13 = -1;

				IBlockState topBlock = biome.topBlock;
				IBlockState fillerBlock = biome.fillerBlock;

				for (int var16 = 127; var16 >= 0; var16--)
				{
					 if (var16 <= 0 + this.random.nextInt(5))
					 {
						 primer.setBlockState(var8, var16, var9, Blocks.BEDROCK.getDefaultState());
					 }
					 else
					 {
						 IBlockState var18 = primer.getBlockState(var8, var16, var9);

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

							 primer.setBlockState(var8, var16 + 1, var9, topBlock);
							 primer.setBlockState(var8, var16, var9, fillerBlock);
						 }

						 if (var13 <= 0)
						 {
							 continue;
						 }

						 primer.setBlockState(var8, var16, var9, fillerBlock);
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

			for (int var44 = -2; var44 <= 2; ++var44)
			{
				for (int var9 = -2; var9 <= 2; ++var9)
				{
					float var45 = 10.0F / MathHelper.sqrt((float) (var44 * var44 + var9 * var9) + 0.2F);
					this.parabolicField[var44 + 2 + (var9 + 2) * 5] = var45;
				}
			}
		}

		double var441 = 684.412D;
		double var451 = 684.412D;
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var441 / 80.0D, var451 / 160.0D, var441 / 80.0D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var441, var451, var441);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var441, var451, var441);
		int var12 = 0;
		int var13 = 0;

		for (int var14 = 0; var14 < par5; ++var14) 
		{
			for (int var15 = 0; var15 < par7; ++var15)
			{
				float var16 = 0.0F;
				float var17 = 0.0F;
				float var18 = 0.0F;
				byte var19 = 2;
				Biome biome = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];

				for (int var47 = -var19; var47 <= var19; ++var47)
				{
					for (int var22 = -var19; var22 <= var19; ++var22)
					{
						Biome biome1 = this.biomesForGeneration[var14 + var47 + 2 + (var15 + var22 + 2) * (par5 + 5)];
						float heightVariation = this.parabolicField[var47 + 2 + (var22 + 2) * 5] / (biome1.getBaseHeight() + 2.0F);

						if (biome1.getHeightVariation() > biome.getBaseHeight())
						{
							heightVariation /= 2.0F;
						}

						var16 += biome1.getHeightVariation() * heightVariation;
						var17 += biome1.getBaseHeight() * heightVariation;
						var18 += heightVariation;
					}
				}

				var16 /= var18;
				var17 /= var18;
				var16 = var16 * 0.9F + 0.1F;
				var17 = (var17 * 4.0F - 1.0F) / 8.0F;
				double var461 = this.noise6[var13] / 8000.0D;

				if (var461 < 0.0D) 
				{
					var461 = -var461 * 0.3D;
				}

				var461 = var461 * 3.0D - 2.0D;

				if (var461 < 0.0D)
				{
					var461 /= 2.0D;

					if (var461 < -1.0D) 
					{
						var461 = -1.0D;
					}

					var461 /= 1.4D;
					var461 /= 2.0D;
				}
				else
				{
					if (var461 > 1.0D)
					{
						var461 = 1.0D;
					}

					var461 /= 8.0D;
				}

				++var13;

				for (int var471 = 0; var471 < par6; ++var471) 
				{
					double var481 = (double) var17;
					double var26 = (double) var16;
					var481 += var461 * 0.2D;
					var481 = var481 * (double) par6 / 16.0D;
					double var28 = (double) par6 / 2.0D + var481 * 4.0D;
					double var30 = 0.0D;
					double var32 = ((double) var471 - var28) * 12.0D * 128.0D / 128.0D / var26;

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

					if (var471 > par6 - 4)
					{
						double var40 = (double) ((float) (var471 - (par6 - 4)) / 3.0F);
						var30 = var30 * (1.0D - var40) + -10.0D * var40;
					}

					par1ArrayOfDouble[var12] = var30;
					++var12;
				}
			}
		}

		return par1ArrayOfDouble;
	}

	public Chunk provideChunk(int par1, int par2) 
	{
		this.rand.setSeed((long) par1 * 341873128712L + (long) par2 * 132897987541L);
		ChunkPrimer chunkPrimer = new ChunkPrimer();
		this.generateTerrain(par1, par2, chunkPrimer);
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		this.replaceBlocksForBiome(par1, par2, chunkPrimer, this.biomesForGeneration);
		this.caveGenerator.generate(this.worldObj, par1, par2, chunkPrimer);
		this.ravineGenerator.generate(this.worldObj, par1, par2, chunkPrimer);

		if (this.mapFeaturesEnabled)
		{
			this.villageGenerator.generate(this.worldObj, par1, par2, chunkPrimer);
			this.scatteredFeatureGenerator.generate(this.worldObj, par1, par2, chunkPrimer);
		}

		Chunk chunk = new Chunk(this.worldObj, chunkPrimer, par1, par2);
		byte[] var5 = chunk.getBiomeArray();

		for (int var6 = 0; var6 < var5.length; ++var6)
		{
			var5[var6] = (byte) Biome.getIdForBiome(this.biomesForGeneration[var6]);
		}

		chunk.generateSkylightMap();

		return chunk;
	}

	public void populate(int x, int z)
	{
		BlockSand.fallInstantly = true;

		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.worldObj.getBiome(blockpos.add(16, 0, 16));

		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) x * var7 + (long) z * var9 ^ this.worldObj.getSeed());
		boolean var11 = false;
		ChunkPos chunkpos = new ChunkPos(x, z);

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, this.rand, x, z, var11);

        if (this.mapFeaturesEnabled)
        {
        	var11 = this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkpos);
			this.scatteredFeatureGenerator.generateStructure(this.worldObj, this.rand, chunkpos);
		}

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, var11, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
        {
            int i1 = this.rand.nextInt(16) + 8;
            int j1 = this.rand.nextInt(256);
            int k1 = this.rand.nextInt(16) + 8;

			(new WorldGenLakes(Blocks.WATER)).generate(this.worldObj, this.rand, blockpos.add(i1, j1, k1));
		}

		biome.decorate(this.worldObj, this.rand, new BlockPos(i, 0, j));

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, var11, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
			WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, i + 8, j + 8, 16, 16, this.rand);

		blockpos = blockpos.add(8, 0, 8);

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, var11, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE))
        {
			for (int k2 = 0; k2 < 16; ++k2) 
			{
				for (int j3 = 0; j3 < 16; ++j3) 
				{
	                BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(blockpos.add(k2, 0, j3));
	                BlockPos blockpos2 = blockpos1.down();

	                if (this.worldObj.canBlockFreezeWater(blockpos2))
	                {
	                    this.worldObj.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
	                }

	                if (this.worldObj.canSnowAt(blockpos1, true))
	                {
	                    this.worldObj.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
	                }
				}
			}
		}

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.worldObj, this.rand, x, z, var11);

		BlockSand.fallInstantly = false;
	}

	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		Biome biome = this.worldObj.getBiome(pos);

		return biome == null ? null : biome.getSpawnableList(creatureType);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) 
	{
		return false;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) 
	{
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) 
	{

	}

}