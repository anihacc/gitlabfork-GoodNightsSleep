package com.legacy.goodnightsleep.world.dream;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ChunkProviderGoodDream implements IChunkGenerator 
{

	public World worldObj;

	public Random random;
	
	protected ChunkPos chunkPos;

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;

	//private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	
	//private ChunkGeneratorSettings settings;
    private IBlockState oceanBlock = Blocks.WATER.getDefaultState();

	private WorldType terrainType;
	private final double[] heightMap;
	private final float[] biomeWeights;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	float[] parabolicField;
	int[][] field_73219_j = new int[32][32];
	
	private Biome[] biomesForGeneration;
	double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;
			
    private MapGenBase ravineGenerator = new MapGenRavine();
	private MapGenBase caveGenerator = new MapGenCaves();
	
	protected boolean mapFeaturesEnabled = true;

	public ChunkProviderGoodDream(World world, long seed)
	{
		this.random = new Random(seed);
		this.worldObj = world;
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
		this.maxLimitPerlinNoise  = new NoiseGeneratorOctaves(this.random, 16);
		this.mainPerlinNoise  = new NoiseGeneratorOctaves(this.random, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.random, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.random, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);
		this.heightMap = new double[825];
        this.biomeWeights = new float[25];
		
        ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
		caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
		
		mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();
				
		for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }
		
		this.worldObj.setSeaLevel(65);
	}


	public void generateTerrain(int x, int z, ChunkPrimer primer)
    {
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        int b2 = this.worldObj.getSeaLevel();
        
        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.STONE.getDefaultState());
                                }
                                else if (i2 * 8 + j2 < b2)
                                {
                                	primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);                            
                                	
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
	
	public void generateHeightmap(int chunkX, int zero, int chunkZ)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, 5, 5, 200.0D, 200.0D, 0.5D);
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, zero, chunkZ, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, zero, chunkZ, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, zero, chunkZ, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int terrainIndex = 0;
        int noiseIndex = 0;

        for (int ax = 0; ax < 5; ++ax) {
            for (int az = 0; az < 5; ++az) {
                float totalVariation = 0.0F;
                float totalHeight = 0.0F;
                float totalFactor = 0.0F;
                byte two = 2;
                Biome biomegenbase = this.biomesForGeneration[ax + 2 + (az + 2) * 10];

                for (int ox = -two; ox <= two; ++ox) {
                    for (int oz = -two; oz <= two; ++oz) {
                        Biome biomegenbase1 = this.biomesForGeneration[ax + ox + 2 + (az + oz + 2) * 10];
                        float rootHeight = biomegenbase1.getBaseHeight();
                        float heightVariation = biomegenbase1.getHeightVariation();

                        if (this.terrainType == WorldType.AMPLIFIED && rootHeight > 0.0F) {
                            rootHeight = 1.0F + rootHeight * 2.0F;
                            heightVariation = 1.0F + heightVariation * 4.0F;
                        }

                        float heightFactor = this.biomeWeights[ox + 2 + (oz + 2) * 5] / (rootHeight + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
                            heightFactor /= 2.0F;
                        }

                        totalVariation += heightVariation * heightFactor;
                        totalHeight += rootHeight * heightFactor;
                        totalFactor += heightFactor;
                    }
                }

                totalVariation /= totalFactor;
                totalHeight /= totalFactor;
                totalVariation = totalVariation * 0.9F + 0.1F;
                totalHeight = (totalHeight * 4.0F - 1.0F) / 8.0F;
                double terrainNoise = this.depthRegion[noiseIndex] / 8000.0D;

                if (terrainNoise < 0.0D) {
                    terrainNoise = -terrainNoise * 0.3D;
                }

                terrainNoise = terrainNoise * 3.0D - 2.0D;

                if (terrainNoise < 0.0D) {
                    terrainNoise /= 2.0D;

                    if (terrainNoise < -1.0D) {
                        terrainNoise = -1.0D;
                    }

                    terrainNoise /= 1.4D;
                    terrainNoise /= 2.0D;
                } else {
                    if (terrainNoise > 1.0D) {
                        terrainNoise = 1.0D;
                    }

                    terrainNoise /= 8.0D;
                }

                ++noiseIndex;
                double heightCalc = (double) totalHeight;
                double variationCalc = (double) totalVariation;
                heightCalc += terrainNoise * 0.2D;
                heightCalc = heightCalc * 8.5D / 8.0D;
                double d5 = 8.5D + heightCalc * 4.0D;

                for (int ay = 0; ay < 33; ++ay) {
                    double d6 = ((double) ay - d5) * 12.0D * 128.0D / 256.0D / variationCalc;

                    if (d6 < 0.0D) {
                        d6 *= 4.0D;
                    }

                    double d7 = this.minLimitRegion[terrainIndex] / 512.0D;
                    double d8 = this.maxLimitRegion[terrainIndex] / 512.0D;
                    double d9 = (this.mainNoiseRegion[terrainIndex] / 10.0D + 1.0D) / 2.0D;
                    double terrainCalc = MathHelper.clampedLerp(d7, d8, d9) - d6;

                    if (ay > 29) {
                        double d11 = (double) ((float) (ay - 29) / 3.0F);
                        terrainCalc = terrainCalc * (1.0D - d11) + -10.0D * d11;
                    }

                    this.heightMap[terrainIndex] = terrainCalc;
                    ++terrainIndex;
                }
            }
        }
    }
	
	public void replaceBlocksForBiome(int x, int z, ChunkPrimer chunkPrimer)
	{
		this.stoneNoise = this.surfaceNoise.getRegion(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
				int var12 = (int)(this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int var13 = -1;
				
				IBlockState topBlock = BlocksGNS.dream_grass.getDefaultState();
				IBlockState fillerBlock = BlocksGNS.dream_dirt.getDefaultState();
				
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
							 
							 if (var16 < 63)
							 {
								 chunkPrimer.setBlockState(var8, var16 + 1, var9, fillerBlock);
							 }
							 else
							 {
								 chunkPrimer.setBlockState(var8, var16 + 1, var9, topBlock);
							 }
							 
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

	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();

		this.generateTerrain(x, z, primer);
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		replaceBlocksForBiome(x, z, primer);
		
		this.caveGenerator.generate(this.worldObj, x, z, primer);
		this.ravineGenerator.generate(this.worldObj, x, z, primer);
		
		Chunk chunk  = new Chunk(this.worldObj, primer, x, z);
		
		byte[] abyte = chunk.getBiomeArray();
		for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
		
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int x, int z) 
	{
        BlockFalling.fallInstantly = true;

        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        this.random.setSeed(this.worldObj.getSeed());
        long k = this.random.nextLong() / 2L * 2L + 1L;
        long l = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)x * k + (long)z * l ^ this.worldObj.getSeed());
        
        boolean flag = false;

        //ChunkPos chunkpos = new ChunkPos(x, z);
        
		this.worldObj.getBiome(blockpos.add(16, 0, 16)).decorate(this.worldObj, this.random, new BlockPos(i, 0, j));
		
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.worldObj, this.random, x, z, flag);	 
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
	
	 @SuppressWarnings("unused")
	private boolean generateMapFeatures()
	    {
			return false;  
	    }

	@Override
	@Nullable
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
	{
		
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) 
	{
		return false;
		
	}


}