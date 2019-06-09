package com.legacy.goodnightsleep.world.dream.biome;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.GNSGenMinable;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigMushroomGNS;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenBigTreeDream;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenFoilage;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTree;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenSponge;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class GoodDreamBiomeDecorator extends BiomeDecorator 
{
	public World world;

	public Random rand;

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
		this.bigMushroomGen = new WorldGenBigMushroomGNS(BlocksGNS.hope_mushroom_cap);
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
            this.dreambiome = biome;
            this.genDecorations(biome);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

	@SuppressWarnings("deprecation")
	@Override
    protected void genDecorations(BiomeGenBase biomeGenBaseIn)
    {    	
		if (this.shouldSpawn(2))
		{
			this.getTree().generate(this.world, this.rand, this.world.getHeight(this.field_180294_c.add(this.nextInt(8) + 8, 0, this.nextInt(8) + 8)));
		}
		
		for (int i3 = 0; i3 < 5; ++i3)
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
        
        if(TerrainGen.decorate(this.world, this.rand, field_180294_c, EventType.BIG_SHROOM) && rand.nextInt(7) == 0)
        {
	        for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2)
	        {
	            int l6 = this.rand.nextInt(8) + 8;
	            int k10 = this.rand.nextInt(8) + 8;
	            this.bigMushroomGen.generate(this.world, this.rand, this.world.getHeight(this.field_180294_c.add(l6, 0, k10)));
	        }
        }
        
        if(TerrainGen.decorate(this.world, this.rand, field_180294_c, EventType.LAKE_WATER))
        {
	        for (int k5 = 0; k5 < 50; ++k5)
	        {
	            int i10 = this.rand.nextInt(16) + 8;
	            int l13 = this.rand.nextInt(16) + 8;
	            int i17 = this.rand.nextInt(248) + 8;
	
	            if (i17 > 0)
	            {
	                int k19 = this.rand.nextInt(i17);
	                BlockPos blockpos6 = this.field_180294_c.add(i10, k19, l13);
	                (new WorldGenLiquids(Blocks.flowing_water)).generate(this.world, this.rand, blockpos6);
	            }
	        }
        }

        for (int amount = 0; amount < 5; ++amount)
        {
	        int j7 = this.rand.nextInt(8) + 8;
	        int i11 = this.rand.nextInt(8) + 8;
	        int k14 = this.world.getHeight(this.field_180294_c.add(j7, 0, i11)).getY() * 2;
	        
	        if (k14 > 0)
	        {
	            int l17 = this.rand.nextInt(k14);
	            this.spongeGen.generate(this.world, this.rand, this.field_180294_c.add(j7, l17 -2 , i11));
	        }
        }
        
		this.spawnOres();

    	this.generateFoilage(BlocksGNS.orange_flower.getDefaultState());
    	this.generateFoilage(BlocksGNS.cyan_flower.getDefaultState());
    	this.generateFoilage(BlocksGNS.lolipop_bush.getDefaultState());
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
    	this.spawnOre(BlocksGNS.dream_dirt.getDefaultState(), 32, 20, 128);
    	this.spawnOre(Blocks.gravel.getDefaultState(), 32, 10, 128);
    	
    	this.spawnOre(Blocks.coal_ore.getDefaultState(), 16, 20, 128);
    	this.spawnOre(Blocks.iron_ore.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.gold_ore.getDefaultState(), 8, 2, 32);
    	this.spawnOre(Blocks.redstone_ore.getDefaultState(), 7, 8, 16);
    	this.spawnOre(Blocks.lapis_ore.getDefaultState(), 6, 1, 16);
    	this.spawnOre(Blocks.diamond_ore.getDefaultState(), 7, 1, 16);
    	this.spawnOre(Blocks.glowstone.getDefaultState(), 8, 15, 3);
    	
    	this.spawnOre(BlocksGNS.candy_ore.getDefaultState(), 8, 20, 128);
    	this.spawnOre(BlocksGNS.rainbow_ore.getDefaultState(), 8, 10, 32);
    	this.spawnOre(BlocksGNS.positite_ore.getDefaultState(), 7, 1, 16);
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
       return this.shouldSpawn(3) ? new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState(), false) : this.shouldSpawn(5) ? new WorldGenBigTreeDream(true) : new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.dream_log.getDefaultState(), BlocksGNS.dream_leaves.getDefaultState(), false);
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_dream_grass.getDefaultState());
    }

}