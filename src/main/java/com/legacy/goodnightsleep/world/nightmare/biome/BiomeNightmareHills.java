package com.legacy.goodnightsleep.world.nightmare.biome;

import java.util.ArrayList;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;
import com.legacy.goodnightsleep.world.features.WorldGenGNSTallGrass;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeNightmareHills extends Biome
{
	public BiomeNightmareHills()
	{
		super(new BiomeProperties("Nightmare Hills").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(1.0F).setTemperature(2.0F).setRainfall(0.0F));
		this.spawnableCreatureList.clear();
		
		ArrayList<SpawnListEntry> list = new ArrayList<SpawnListEntry>();
 
		list.clear();
		
		//this.topBlock = BlocksGNS.nightmare_grass.getDefaultState();
		//this.fillerBlock = Blocks.DIRT.getDefaultState();
		
		 //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 95, 4, 4));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 5, 1, 1));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 100, 4, 4));
	     //this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityBlaze.class, 10, 3, 3));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 5, 1, 2));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitherSkeleton.class, 10, 5, 5));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 10, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySilverfish.class, 10, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 10, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTormenter.class, 50, 10, 10));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGiantZombie.class, 95, 1, 1));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass.getDefaultState());
    }
	
	public BiomeDecorator createBiomeDecorator()
    {
    	return new NightmareBiomeDecorator();
    }
	
	public float getSpawningChance()
    {
        return 0.4F;
    }

}