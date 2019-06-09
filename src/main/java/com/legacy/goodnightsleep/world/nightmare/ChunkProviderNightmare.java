package com.legacy.goodnightsleep.world.nightmare;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

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
	private IBlockState oceanBlock = Blocks.lava.getDefaultState();

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
		this.worldObj.setSeaLevel(63);
	}

	public void generateTerrain(int x, int z, ChunkPrimer primer)
	{
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);
		byte b2 = 65;
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
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.stone.getDefaultState());
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
                BiomeGenBase biomegenbase = this.biomesForGeneration[ax + 2 + (az + 2) * 10];

                for (int ox = -two; ox <= two; ++ox)
                {
                    for (int oz = -two; oz <= two; ++oz)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[ax + ox + 2 + (az + oz + 2) * 10];
                        float f5 = biomegenbase1.minHeight;
                        float f6 = biomegenbase1.maxHeight;

                        if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.biomeWeights[ox + 2 + (oz + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)
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

	public void replaceBlocksForBiome(int x, int z, ChunkPrimer chunkPrimer)
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
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();
		this.generateTerrain(x, z, primer);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		replaceBlocksForBiome(x, z, primer);
		this.caveGenerator.generate(this, this.worldObj, x, z, primer);
		this.ravineGenerator.generate(this, this.worldObj, x, z, primer);
		
		Chunk chunk = new Chunk(this.worldObj, primer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)this.biomesForGeneration[i].biomeID;
        }
        
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(IChunkProvider provider, int x, int z)
	{
		BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		this.random.setSeed(this.worldObj.getSeed());
		long k = this.random.nextLong() / 2L * 2L + 1L;
		long l = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed((long) x * k + (long) z * l ^ this.worldObj.getSeed());
		this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16)).decorate(this.worldObj, this.random, new BlockPos(i, 0, j));
		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return this.worldObj.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) 
	{

	}

	@Override
	public boolean func_177460_a(IChunkProvider provider, Chunk chunkIn, int chunkX, int chunkZ)
	{
		return false;
	}
	
	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
	{
		return null;
	}
	
	@Override
	public Chunk provideChunk(BlockPos blockPosIn)
	{
		return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
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
}