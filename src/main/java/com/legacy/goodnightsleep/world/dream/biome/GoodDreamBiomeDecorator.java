package com.legacy.goodnightsleep.world.dream.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.GNSGenMinable;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigMushroomGNS;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigTreeDream;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenFoilage;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTree;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenSponge;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class GoodDreamBiomeDecorator extends BiomeDecorator 
{
	public BiomeGenBase dreambiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();
	
	public WorldGenSponge spongeGen = new WorldGenSponge();
	
	public boolean generateLakes = true;
	
	public int bigMushroomsPerChunk = 1;

	public GoodDreamBiomeDecorator()
	{
		super();
		
		this.bigMushroomsPerChunk = 1;
		this.bigMushroomGen = new WorldGenBigMushroomGNS(1); //BlocksGNS.hope_mushroom_cap
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
			this.dreambiome = biome;
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
			if (this.shouldSpawn(2))
			{
				this.getTree().generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.nextInt(8) + 8, this.currentWorld.getPrecipitationHeight(this.chunk_X + this.nextInt(8) + 8, this.chunk_Z + this.nextInt(8) + 8), this.chunk_Z + this.nextInt(8) + 8);
			}
        }

		for (int j = 0; j < 5; ++j)
        {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            WorldGenerator worldgenerator = biomeGenBaseIn.getRandomWorldGenForGrass(this.randomGenerator);
            worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }
        
		if (TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM) && this.randomGenerator.nextInt(7) == 0)
		{
	        for (int j = 0; j < this.bigMushroomsPerChunk; ++j)
	        {
	            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
	            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
	            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
	        }
		}
        
		if (this.shouldSpawn(7))
		{
			(new WorldGenLakes(Blocks.water)).generate(this.currentWorld, this.randomGenerator, this.chunk_X + this.randomGenerator.nextInt(16) + 8, this.randomGenerator.nextInt(256), this.chunk_Z + this.randomGenerator.nextInt(16) + 8);
		}

		for (int amount = 0; amount < 5; ++amount)
        {
        	int j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            (new WorldGenSponge()).generate(this.currentWorld, this.randomGenerator, j, l, k);

        }
        
		this.spawnOres();

    	this.generateFoilage(BlocksGNS.orange_flower);
    	this.generateFoilage(BlocksGNS.cyan_flower);
    	this.generateFoilage(BlocksGNS.lolipop_bush);
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
    	this.spawnOre(BlocksGNS.dream_dirt, 32, 20, 128);
    	this.spawnOre(Blocks.gravel, 32, 10, 128);
    	
    	this.spawnOre(Blocks.coal_ore, 16, 20, 128);
    	//this.spawnOre(Blocks.iron_ore.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.gold_ore, 8, 2, 32);
    	this.spawnOre(Blocks.redstone_ore, 7, 8, 16);
    	this.spawnOre(Blocks.lapis_ore, 6, 1, 16);
    	//this.spawnOre(Blocks.diamond_ore.getDefaultState(), 7, 1, 16);
    	this.spawnOre(Blocks.glowstone, 8, 15, 3);
    	
    	this.spawnOre(BlocksGNS.candy_ore, 8, 20, 128);
    	this.spawnOre(BlocksGNS.rainbow_ore, 8, 10, 32);
    	this.spawnOre(BlocksGNS.positite_ore, 7, 1, 16);
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
       return this.shouldSpawn(3) ? new WorldGenGNSTree(false, 4 + randomGenerator.nextInt(4), BlocksGNS.white_log, 0, BlocksGNS.candy_leaves, 0, false) : this.shouldSpawn(5) ? new WorldGenBigTreeDream(true) : new WorldGenGNSTree(false, 4 + randomGenerator.nextInt(4), BlocksGNS.dream_log, 0, BlocksGNS.dream_leaves, 0, false);
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_dream_grass);
    }

}