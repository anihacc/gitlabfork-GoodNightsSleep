package com.legacy.goodnightsleep.world.nightmare.biome;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.GNSGenMinable;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigTreeNightmare;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenFoilage;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTree;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenNetherSplash;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class NightmareBiomeDecorator extends BiomeDecorator 
{
	public World world;

	public Random rand;

	public BiomeGenBase nightmarebiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();

	public NightmareBiomeDecorator()
	{
		super();
	}

	@Override
	public void decorate(World worldIn, Random random, BiomeGenBase biome, BlockPos pos)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.field_180294_c = pos;
            this.world = worldIn;
            this.rand = random;
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
			this.getTree().generate(this.world, this.rand, this.world.getHeight(this.field_180294_c.add(this.nextInt(16) + 8, 0, this.nextInt(16) + 8)));
		}
		
		if(this.rand.nextInt(5) == 0)
		{
			if(TerrainGen.decorate(this.world, this.rand, field_180294_c, EventType.BIG_SHROOM))
			for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2)
		    {
		        int l6 = this.rand.nextInt(16) + 8;
		        int k10 = this.rand.nextInt(16) + 8;
		        this.bigMushroomGen.generate(this.world, this.rand, this.world.getHeight(this.field_180294_c.add(l6, 0, k10)));
		    }
		}
		
		if (this.rand.nextInt(32) == 0)
        {
            int i5 = this.rand.nextInt(16) + 8;
            int k9 = this.rand.nextInt(16) + 8;
            int j13 = this.world.getHeight(this.field_180294_c.add(i5, 0, k9)).getY() * 2;

            if (j13 > 0)
            {
                int k16 = this.rand.nextInt(j13);
                (new WorldGenPumpkin()).generate(this.world, this.rand, this.field_180294_c.add(i5, k16, k9));
            }
        }
		
			
           

        for (int i3 = 0; i3 < 1; ++i3)
        {
            int j7 = this.rand.nextInt(16) + 8;
            int i11 = this.rand.nextInt(16) + 8;
            int k14 = this.world.getHeight(this.field_180294_c.add(j7, 0, i11)).getY() * 2;

            if (k14 > 0)
            {
                int l17 = this.rand.nextInt(k14);
                biomeGenBaseIn.getRandomWorldGenForGrass(this.rand).generate(this.world, this.rand, this.field_180294_c.add(j7, l17, i11));
            }
        }
        
        for (int amount = 0; amount < 8; ++amount)
        {
	        int j7 = this.rand.nextInt(8) + 8;
	        int i11 = this.rand.nextInt(8) + 8;
	        int k14 = this.world.getHeight(this.field_180294_c.add(j7, 0, i11)).getY() * 2;
	        
	        if (k14 > 0)
	        {
	            int l17 = this.rand.nextInt(k14);
	            new WorldGenNetherSplash().generate(this.world, this.rand, this.field_180294_c.add(j7, l17 -2 , i11));
	        }
        }

		this.spawnOres();

    	this.generateFoilage(BlocksGNS.dead_flower.getDefaultState());
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
    	this.spawnOre(Blocks.dirt.getDefaultState(), 32, 20, 128);
    	this.spawnOre(Blocks.gravel.getDefaultState(), 32, 10, 128);
    	
    	this.spawnOre(Blocks.coal_ore.getDefaultState(), 16, 20, 128);
    	this.spawnOre(Blocks.iron_ore.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.gold_ore.getDefaultState(), 8, 2, 32);
    	this.spawnOre(Blocks.redstone_ore.getDefaultState(), 7, 8, 16);
    	this.spawnOre(Blocks.lapis_ore.getDefaultState(), 6, 1, 16);
    	this.spawnOre(Blocks.diamond_ore.getDefaultState(), 7, 1, 16);
    	
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
        	this.ores.generate(this.world, this.rand, this.field_180294_c.add(this.nextInt(16), this.nextInt(y), this.nextInt(16)));
    	}
    }
    
	public void generateFoilage(IBlockState block)
	{
		this.foilage.setPlantBlock(block);

        for(int n = 0; n < 2; n++)
        {
        	foilage.generate(this.world, this.rand, this.field_180294_c.add(this.nextInt(16) + 8, this.nextInt(100) + 60, this.nextInt(16) + 8));
        }
	}
    
    public WorldGenerator getTree()
    {
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.blood_log.getDefaultState(), Blocks.air.getDefaultState(), false) : new WorldGenBigTreeNightmare(false); //new DeadTreeGenerator(false)
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass.getDefaultState());
    }

}