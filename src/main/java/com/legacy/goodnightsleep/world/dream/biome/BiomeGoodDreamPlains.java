package com.legacy.goodnightsleep.world.dream.biome;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entities.EntityGNSSpawner;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityUnicorn;
import com.legacy.goodnightsleep.world.features.WorldGenGNSTallGrass;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.Loader;

public class BiomeGoodDreamPlains extends Biome
{
	public BiomeGoodDreamPlains()
	{
		super(new BiomeProperties("Good Dream Plains").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(0.5F).setTemperature(0.8F).setRainfall(0.0F));
		
		this.topBlock = BlocksGNS.dream_grass.getDefaultState();
		this.fillerBlock = BlocksGNS.dream_dirt.getDefaultState();
		
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityBabyCreeper.class, 1, 1, 1));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGNSSpawner.class, 110, 1, 1));
		
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityUnicorn.class, 70, 1, 3));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_dream_grass.getDefaultState());
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
	
	public Biome.TempCategory getTempCategory()
    {
        return Biome.TempCategory.MEDIUM;
    }
	
	public float getSpawningChance()
    {
		if (Loader.isModLoaded("sponge"))
		{
			return 1.0F;
		}
		else
		{
			return 2.0F;
		}
    }

}