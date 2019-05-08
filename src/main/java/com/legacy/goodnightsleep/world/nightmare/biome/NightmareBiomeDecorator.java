package com.legacy.goodnightsleep.world.nightmare.biome;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.features.GNSGenMinable;
import com.legacy.goodnightsleep.world.features.WorldGenBigMushroomGNS;
import com.legacy.goodnightsleep.world.features.WorldGenBigTreeNightmare;
import com.legacy.goodnightsleep.world.features.WorldGenFoilage;
import com.legacy.goodnightsleep.world.features.WorldGenGNSTallGrass;
import com.legacy.goodnightsleep.world.features.WorldGenGNSTree;
import com.legacy.goodnightsleep.world.features.WorldGenNetherSplash;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class NightmareBiomeDecorator extends BiomeDecorator 
{
	public World world;

	public Random rand;

	public Biome nightmarebiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();

	public NightmareBiomeDecorator()
	{
		super();
	}

	@Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.chunkPos = pos;
            this.world = worldIn;
            this.rand = random;
            this.nightmarebiome = biome;
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
            
            this.bigMushroomsPerChunk = 1;
    		this.bigMushroomGen = new WorldGenBigMushroomGNS(BlocksGNS.despair_mushroom_cap);
        }
    }

	@SuppressWarnings("deprecation")
	@Override
    protected void genDecorations(Biome biomeGenBaseIn, World worldIn, Random random)
    {    	
		ChunkPos pos = new ChunkPos(this.chunkPos);

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldIn, random, pos));

		if (this.shouldSpawn(3))
		{
			this.getTree().generate(this.world, this.rand, this.world.getHeight(this.chunkPos.add(this.nextInt(16) + 8, 0, this.nextInt(16) + 8)));
		}
		
		if(random.nextInt(5) == 0)
		{
			if(TerrainGen.decorate(worldIn, random, chunkPos, EventType.BIG_SHROOM))
			for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2)
		    {
		        int l6 = random.nextInt(16) + 8;
		        int k10 = random.nextInt(16) + 8;
		        this.bigMushroomGen.generate(worldIn, random, worldIn.getHeight(this.chunkPos.add(l6, 0, k10)));
		    }
		}
		
		if (random.nextInt(32) == 0)
        {
            int i5 = random.nextInt(16) + 8;
            int k9 = random.nextInt(16) + 8;
            int j13 = worldIn.getHeight(this.chunkPos.add(i5, 0, k9)).getY() * 2;

            if (j13 > 0)
            {
                int k16 = random.nextInt(j13);
                (new WorldGenPumpkin()).generate(worldIn, random, this.chunkPos.add(i5, k16, k9));
            }
        }
		
			
           

        for (int i3 = 0; i3 < 1; ++i3)
        {
            int j7 = random.nextInt(16) + 8;
            int i11 = random.nextInt(16) + 8;
            int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;

            if (k14 > 0)
            {
                int l17 = random.nextInt(k14);
                biomeGenBaseIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
            }
        }
        
        for (int amount = 0; amount < 8; ++amount)
        {
	        int j7 = random.nextInt(8) + 8;
	        int i11 = random.nextInt(8) + 8;
	        int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;
	        
	        if (k14 > 0)
	        {
	            int l17 = random.nextInt(k14);
	            new WorldGenNetherSplash().generate(worldIn, random, this.chunkPos.add(j7, l17 -2 , i11));
	        }
        }

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, this.chunkPos));
		this.spawnOres();
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, this.chunkPos));

    	this.generateFoilage(BlocksGNS.dead_flower.getDefaultState());
    	
    	MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldIn, random, pos));
    }

	public int nextInt(int max)
    {
    	return this.rand.nextInt(max);
    }

    public boolean shouldSpawn(int chance)
    {
    	return this.nextInt(chance) == 0;
    }
    
    public void spawnOres()
    {
    	this.spawnOre(Blocks.DIRT.getDefaultState(), 32, 20, 128);
    	this.spawnOre(Blocks.GRAVEL.getDefaultState(), 32, 10, 128);
    	
    	this.spawnOre(Blocks.COAL_ORE.getDefaultState(), 16, 20, 128);
    	//this.spawnOre(Blocks.IRON_ORE.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.GOLD_ORE.getDefaultState(), 8, 2, 32);
    	//this.spawnOre(Blocks.REDSTONE_ORE.getDefaultState(), 7, 8, 16);
    	this.spawnOre(Blocks.LAPIS_ORE.getDefaultState(), 6, 1, 16);
    	//this.spawnOre(Blocks.DIAMOND_ORE.getDefaultState(), 7, 1, 16);
    	
    	this.spawnOre(BlocksGNS.necrum_ore.getDefaultState(), 8, 20, 128);
    	this.spawnOre(BlocksGNS.zitrite_ore.getDefaultState(), 8, 10, 32);
    	this.spawnOre(BlocksGNS.negatite_ore.getDefaultState(), 7, 1, 16);
    }

    public void spawnOre(IBlockState state, int size, int chance, int y)
    {
		this.ores.setSize(size);
		this.ores.setBlock(state);

    	for (int chances = 0; chances < chance; chances++)
    	{
        	this.ores.generate(this.world, this.rand, this.chunkPos.add(this.nextInt(16), this.nextInt(y), this.nextInt(16)));
    	}
    }
    
	public void generateFoilage(IBlockState block)
	{
		this.foilage.setPlantBlock(block);

        for(int n = 0; n < 2; n++)
        {
        	foilage.generate(this.world, this.rand, this.chunkPos.add(this.nextInt(16) + 8, this.nextInt(100) + 60, this.nextInt(16) + 8));
        }
	}
    
    public WorldGenerator getTree()
    {
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.blood_log.getDefaultState(), Blocks.AIR.getDefaultState(), false) : new WorldGenBigTreeNightmare(false); //new DeadTreeGenerator(false)
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass.getDefaultState());
    }

}