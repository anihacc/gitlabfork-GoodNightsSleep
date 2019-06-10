package com.legacy.goodnightsleep.world.nightmare.biome;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.GNSGenMinable;
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
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class NightmareBiomeDecorator extends BiomeDecorator 
{
	public BiomeGenBase nightmarebiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();

	public NightmareBiomeDecorator()
	{
		super();
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

	@SuppressWarnings("deprecation")
	@Override
    protected void genDecorations(BiomeGenBase biomeGenBaseIn)
    {
		if (this.shouldSpawn(3))
		{
			this.getTree().generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.nextInt(8) + 8, this.currentWorld.getPrecipitationHeight(this.chunk_X + this.nextInt(8) + 8, this.chunk_Z + this.nextInt(8) + 8), this.chunk_Z + this.nextInt(8) + 8);
		}
		
		if(this.randomGenerator.nextInt(5) == 0)
		{
			if(TerrainGen.decorate(this.currentWorld, this.randomGenerator, chunk_X, chunk_Z, EventType.BIG_SHROOM))
			for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2)
		    {
		        int l6 = this.randomGenerator.nextInt(16) + 8;
		        int k10 = this.randomGenerator.nextInt(16) + 8;
	            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, l6, this.currentWorld.getPrecipitationHeight(l6, k10), k10);
		    }
		}
		
		if (this.randomGenerator.nextInt(32) == 0)
        {
            int i5 = this.randomGenerator.nextInt(16) + 8;
            int k9 = this.randomGenerator.nextInt(16) + 8;
            int j13 = nextInt(this.currentWorld.getHeightValue(i5, k9) * 2);

            if (j13 > 0)
            {
                int k16 = this.randomGenerator.nextInt(j13);
                (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, i5, k16, k9);
            }
        }
		
			
           

        for (int i3 = 0; i3 < 1; ++i3)
        {
            int j7 = this.randomGenerator.nextInt(16) + 8;
            int i11 = this.randomGenerator.nextInt(16) + 8;
            int k14 = nextInt(this.currentWorld.getHeightValue(j7, i11) * 2);

            if (k14 > 0)
            {
                int l17 = this.randomGenerator.nextInt(k14);
                biomeGenBaseIn.getRandomWorldGenForGrass(this.randomGenerator).generate(this.currentWorld, this.randomGenerator, j7, l17, i11);
            }
        }
        
        for (int amount = 0; amount < 8; ++amount)
        {
	        int j7 = this.randomGenerator.nextInt(8) + 8;
	        int i11 = this.randomGenerator.nextInt(8) + 8;
            int k14 = nextInt(this.currentWorld.getHeightValue(j7, i11) * 2);
	        
	        if (k14 > 0)
	        {
	            int l17 = this.randomGenerator.nextInt(k14);
	            new WorldGenNetherSplash().generate(this.currentWorld, this.randomGenerator, j7, l17 -2 , i11);
	        }
        }

		this.spawnOres();

    	this.generateFoilage(BlocksGNS.dead_flower);
    }

	public int nextInt(int max)
    {
    	return max < 2 ? this.randomGenerator.nextInt(max + 1) : this.randomGenerator.nextInt(max);
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
        	this.ores.generate(this.currentWorld, this.randomGenerator, this.nextInt(16), this.nextInt(y), this.nextInt(16));
    	}
    }
    
	public void generateFoilage(Block block)
	{
		this.foilage.setPlantBlock(block);

        for(int n = 0; n < 2; n++)
        {
        	foilage.generate(this.currentWorld, this.randomGenerator, this.nextInt(16) + 8, this.nextInt(100) + 60, this.nextInt(16) + 8);
        }
	}
    
    public WorldGenerator getTree()
    {
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + randomGenerator.nextInt(4), BlocksGNS.blood_log, Blocks.air, false) : new WorldGenBigTreeNightmare(false); //new DeadTreeGenerator(false)
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass);
    }

}