package com.legacy.goodnightsleep.world.nightmare;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;

public class ChunkProviderNightmare implements IChunkProvider
{

	public World worldObj;

	public Random random;

	private NoiseGeneratorOctaves minLimitPerlinNoise;

	private NoiseGeneratorOctaves maxLimitPerlinNoise;

	private NoiseGeneratorOctaves mainPerlinNoise;

	private NoiseGeneratorPerlin surfaceNoise;

	public NoiseGeneratorOctaves scaleNoise;

	public NoiseGeneratorOctaves depthNoise;

	// private double[] noiseArray;
	private double[] stoneNoise = new double[256];

	// private ChunkGeneratorSettings settings;
	private Block oceanBlock = Blocks.lava;

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

	private BiomeGenBase[] biomesForGeneration;

	double[] mainNoiseRegion;

	double[] minLimitRegion;

	double[] maxLimitRegion;

	double[] depthRegion;

	private MapGenBase ravineGenerator = new MapGenRavine();

	private MapGenBase caveGenerator = new MapGenCaves();

	protected boolean mapFeaturesEnabled = true;

	public ChunkProviderNightmare(World world, long seed)
	{
		this.random = new Random(seed);
		this.worldObj = world;
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.random, 8);
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
				float f = 10.0F / MathHelper.sqrt_double((float) (i * i + j * j) + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}
	}

	public void generateTerrain(int x, int z, Block[] p_147424_3_)
    {
		byte b0 = 63;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int k = 0; k < 4; ++k)
        {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[k1 + k2];
                    double d2 = this.heightMap[l1 + k2];
                    double d3 = this.heightMap[i2 + k2];
                    double d4 = this.heightMap[j2 + k2];
                    double d5 = (this.heightMap[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3)
                        {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int k3 = 0; k3 < 4; ++k3)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
                                    p_147424_3_[j3 += short1] = Blocks.stone;
                                }
                                else if (k2 * 8 + l2 < b0)
                                {
                                    p_147424_3_[j3 += short1] = Blocks.water;
                                }
                                else
                                {
                                    p_147424_3_[j3 += short1] = null;
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
                BiomeGenBase biomegenbase = this.biomesForGeneration[ax + 2 + (az + 2) * 10];

                for (int ox = -two; ox <= two; ++ox)
                {
                    for (int oz = -two; oz <= two; ++oz)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[ax + ox + 2 + (az + oz + 2) * 10];
                        float f5 = biomegenbase1.rootHeight;
                        float f6 = biomegenbase1.heightVariation;

                        if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.biomeWeights[ox + 2 + (oz + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
                        {
                            f7 /= 2.0F;
                        }

                        totalVariation += f6 * f7;
                        totalHeight += f5 * f7;
                        totalFactor += f7;
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
                    double terrainCalc = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

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

	/*public void replaceBlocksForBiome(int x, int z, ChunkPrimer chunkPrimer)
	{
		this.stoneNoise = this.surfaceNoise.func_151599_a(this.stoneNoise, (double) (x * 16), (double) (z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);
		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
				int var12 = (int) (this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int var13 = -1;
				IBlockState topBlock = BlocksGNS.nightmare_grass.getDefaultState();
				IBlockState fillerBlock = Blocks.dirt.getDefaultState();
				for (int var16 = 200; var16 >= 0; var16--)
				{
					if (var16 <= 0 + this.random.nextInt(5))
					{
						chunkPrimer.setBlockState(var8, var16, var9, Blocks.bedrock.getDefaultState());
					}
					else
					{
						IBlockState var18 = chunkPrimer.getBlockState(var8, var16, var9);
						if (var18.getBlock() == Blocks.air)
						{
							var13 = -1;
							continue;
						}
						if (var18.getBlock() != Blocks.stone)
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
	}*/
	
	public void replaceBlocksForBiome(int x, int z, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, x, z, p_147422_3_, p_147422_4_, p_147422_5_, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        double d0 = 0.03125D;
        this.stoneNoise = this.surfaceNoise.func_151599_a(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = p_147422_5_[l + k * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.random, p_147422_3_, p_147422_4_, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

	@Override
	public Chunk provideChunk(int x, int z)
	{
		this.random.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
		this.generateTerrain(x, z, ablock);

        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.replaceBlocksForBiome(x, z, ablock, abyte, this.biomesForGeneration);
        this.caveGenerator.func_151539_a(this, this.worldObj, x, z, ablock);
        this.ravineGenerator.func_151539_a(this, this.worldObj, x, z, ablock);

        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, x, z);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(IChunkProvider provider, int x, int z)
	{
		int i = x * 16;
		int j = z * 16;
		this.random.setSeed(this.worldObj.getSeed());
		long k = this.random.nextLong() / 2L * 2L + 1L;
		long l = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed((long) x * k + (long) z * l ^ this.worldObj.getSeed());
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(i + 16, j + 16);
        
        biomegenbase.decorate(this.worldObj, this.random, i, j);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) 
	{
		return this.worldObj.getBiomeGenForCoords(x, z).getSpawnableList(creatureType);
	}

	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback) 
	{
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() 
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString() 
	{
		return "RandomNightmareLevelSource";
	}

	@Override
	public int getLoadedChunkCount() 
	{
		return 0;
	}

	@Override
	public void saveExtraData()
	{

	}


	@Override
	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		return this.provideChunk(p_73158_1_, p_73158_2_);
	}


	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void recreateStructures(int p_82695_1_, int p_82695_2_)
	{
		// TODO Auto-generated method stub
		
	}
}