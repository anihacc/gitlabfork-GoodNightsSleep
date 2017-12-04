package com.legacy.goodnightsleep.common.world.nightmare;

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
    	//this.spawnOre(BlocksSkies.everbright_diopside_ore.getDefaultState(), 8, 8, 45);
    	//this.spawnOre(BlocksSkies.everbright_moonstone_ore.getDefaultState(), 16, 20, 55);
    	//this.spawnOre(BlocksSkies.everbright_pyrope_ore.getDefaultState(), 8, 15, 40);
    	//this.spawnOre(BlocksSkies.everbright_turquoise_ore.getDefaultState(), 8, 13, 30);
    	//this.spawnOre(BlocksSkies.everbright_charoite_ore.getDefaultState(), 4, 6, 12);
    	//this.spawnOre(BlocksSkies.turquoise_dirt.getDefaultState(), 32, 25, 128);
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
       return this.shouldSpawn(10) ? new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.blood_log.getDefaultState(), Blocks.AIR.getDefaultState(), false) : new WorldGenGNSTree(false, 4 + rand.nextInt(4), BlocksGNS.dead_log.getDefaultState(), Blocks.AIR.getDefaultState(), false);
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenTallGrass(BlocksGNS.tall_nightmare_grass.getDefaultState());
    }

}