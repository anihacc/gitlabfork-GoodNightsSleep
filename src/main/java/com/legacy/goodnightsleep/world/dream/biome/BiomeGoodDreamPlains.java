package com.legacy.goodnightsleep.world.dream.biome;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGoodDreamPlains extends BiomeGenBase
{
	public BiomeGoodDreamPlains()
	{
		super(GNSConfig.getDreamBiomeID());

		this.topBlock = BlocksGNS.dream_grass;
		this.fillerBlock = BlocksGNS.dream_dirt;
		this.setBiomeName("Good Dream Plains");
		this.setDisableRain();
		this.rootHeight = 0.1F;
		this.heightVariation = 0.5F;
		this.temperature = 0.8F;
		this.rainfall = 0.0F;

		this.spawnableMonsterList.clear();
		//this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityBabyCreeper.class, 1, 1, 1));
		
		this.spawnableCreatureList.clear();
		//this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityGNSSpawner.class, 100, 1, 1));
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 100, 1, 1));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_dream_grass);
    }

	public BiomeDecorator createBiomeDecorator()
    {
    	return new GoodDreamBiomeDecorator();
    }
	
	@Override
	public int getWaterColorMultiplier()
	{
		return 0x00ff97; //0x727272
	}
	
	public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.MEDIUM;
    }
	
	public float getSpawningChance()
    {
		return 2.0F;
    }

}