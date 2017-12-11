package com.legacy.goodnightsleep.common.world.dream;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.world.genfeatures.GNSGenMinable;
import com.legacy.goodnightsleep.common.world.genfeatures.WorldGenFoilage;
import com.legacy.goodnightsleep.common.world.genfeatures.WorldGenGNSTree;
import com.legacy.goodnightsleep.common.world.genfeatures.WorldGenTallGrass;

public class GoodDreamBiomeDecorator extends BiomeDecorator 
{
	public World world;

	public Random rand;

	public Biome dreambiome;

	public GNSGenMinable ores = new GNSGenMinable();

	public WorldGenFoilage foilage = new WorldGenFoilage();

	public GoodDreamBiomeDecorator()
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
            this.dreambiome = biome;
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
            this.generateLakes = true;
        }
    }

	@Override
    protected void genDecorations(Biome biomeGenBaseIn, World worldIn, Random random)
    {    	
		if (this.shouldSpawn(2))
		{
			this.getTree().generate(this.world, this.rand, this.world.getHeight(this.chunkPos.add(this.nextInt(16) + 8, 0, this.nextInt(16) + 8)));
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
    	this.spawnOre(Blocks.DIRT.getDefaultState(), 32, 20, 128);
    	this.spawnOre(Blocks.GRAVEL.getDefaultState(), 32, 10, 128);
    	
    	this.spawnOre(Blocks.COAL_ORE.getDefaultState(), 16, 20, 128);
    	this.spawnOre(Blocks.IRON_ORE.getDefaultState(), 8, 20, 64);
    	this.spawnOre(Blocks.GOLD_ORE.getDefaultState(), 8, 2, 32);
    	this.spawnOre(Blocks.REDSTONE_ORE.getDefaultState(), 7, 8, 16);
    	this.spawnOre(Blocks.LAPIS_ORE.getDefaultState(), 6, 1, 16);
    	this.spawnOre(Blocks.DIAMOND_ORE.getDefaultState(), 7, 1, 16);
    	this.spawnOre(Blocks.GLOWSTONE.getDefaultState(), 8, 15, 3);
    	
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
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState(), false) : new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.dream_log.getDefaultState(), BlocksGNS.dream_leaves.getDefaultState(), false);
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenTallGrass(BlocksGNS.tall_dream_grass.getDefaultState());
    }

}