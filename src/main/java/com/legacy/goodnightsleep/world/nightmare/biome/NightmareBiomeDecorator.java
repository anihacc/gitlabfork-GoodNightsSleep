package com.legacy.goodnightsleep.world.nightmare.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.GNSGenMinable;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigMushroomGNS;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigTreeNightmare;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenFoilage;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTree;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenNetherSplash;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class NightmareBiomeDecorator extends BiomeDecorator 
{
	public BiomeGenBase nightmarebiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();

	public NightmareBiomeDecorator()
	{
		super();
		
		this.bigMushroomGen = new WorldGenBigMushroomGNS(0);
	}

	@Override
    public void decorateChunk(World worldIn, Random random, BiomeGenBase biome, int x, int z)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
        	this.currentWorld = worldIn;
			this.randomGenerator = random;
			this.chunk_X = x;
			this.chunk_Z = z;
			this.nightmarebiome = biome;
			this.genDecorations(biome);
			this.currentWorld = null;
			this.randomGenerator = null;
        }
    }

	@Override
    protected void genDecorations(BiomeGenBase biomeGenBaseIn)
    {
		for (int amount = 0; amount < 2; ++amount)
        {
			if (this.shouldSpawn(3))
			{
				this.getTree().generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.nextInt(8) + 8, this.currentWorld.getPrecipitationHeight(this.chunk_X + this.nextInt(8) + 8, this.chunk_Z + this.nextInt(8) + 8), this.chunk_Z + this.nextInt(8) + 8);
			}
        }
		
		if (TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM) && this.randomGenerator.nextInt(5) == 0)
		{
	        for (int j = 0; j < this.bigMushroomsPerChunk; ++j)
	        {
	            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
	            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
	            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
	        }
		}
		
		if (this.randomGenerator.nextInt(32) == 0)
        {
            int j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

		for (int j = 0; j < 1; ++j)
        {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            WorldGenerator worldgenerator = biomeGenBaseIn.getRandomWorldGenForGrass(this.randomGenerator);
            worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }
        
        for (int amount = 0; amount < 8; ++amount)
        {
        	int j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            (new WorldGenNetherSplash()).generate(this.currentWorld, this.randomGenerator, j, l, k);

        }

		this.spawnOres();

    	this.generateFoilage(BlocksGNS.dead_flower);
    }

	public int nextInt(int max)
    {
    	return this.randomGenerator.nextInt(max);
    }

    public boolean shouldSpawn(int chance)
    {
    	return this.nextInt(chance) == 0;
    }
    
    public void spawnOres()
    {
    	this.spawnOre(Blocks.dirt, 32, 20, 128);
    	this.spawnOre(Blocks.gravel, 32, 10, 128);
    	
    	this.spawnOre(Blocks.coal_ore, 16, 20, 128);
    	//this.spawnOre(Blocks.iron_ore.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.gold_ore, 8, 2, 32);
    	this.spawnOre(Blocks.redstone_ore, 7, 8, 16);
    	this.spawnOre(Blocks.lapis_ore, 6, 1, 16);
    	//this.spawnOre(Blocks.diamond_ore.getDefaultState(), 7, 1, 16);
    	
    	this.spawnOre(BlocksGNS.necrum_ore, 8, 20, 128);
    	this.spawnOre(BlocksGNS.zitrite_ore, 8, 10, 32);
    	this.spawnOre(BlocksGNS.negatite_ore, 7, 1, 16);
    }

    public void spawnOre(Block state, int size, int chance, int y)
	{
		this.ores.setSize(size);
		this.ores.setBlock(state);

		for (int chances = 0; chances < chance; chances++)
		{
			this.ores.generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.nextInt(16), this.nextInt(y), this.chunk_Z + this.nextInt(16));
		}
	}
    
	public void generateFoilage(Block block)
	{
		this.foilage.setPlantBlock(block);

		for (int n = 0; n < 2; n++)
		{
			this.foilage.generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.nextInt(16) + 8, this.nextInt(128), this.chunk_Z + this.nextInt(16) + 8);
		}
	}
    
    public WorldGenerator getTree()
    {
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + randomGenerator.nextInt(4), BlocksGNS.blood_log, 0, Blocks.air, 0, false) : new WorldGenBigTreeNightmare(false); //new DeadTreeGenerator(false)
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass);
    }

}