package com.legacy.goodnightsleep.world.dream;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entities.EntityGNSSpawner;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenTallGrass;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.Loader;

public class BiomeGenGoodDreamPlains extends Biome
{
	public BiomeGenGoodDreamPlains()
	{
		super(new BiomeProperties("Good Dream Plains").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(0.5F).setTemperature(0.8F).setRainfall(0.0F));
		this.spawnableMonsterList.clear();

		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGNSSpawner.class, 100, 1, 1));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenTallGrass(BlocksGNS.tall_dream_grass.getDefaultState());
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