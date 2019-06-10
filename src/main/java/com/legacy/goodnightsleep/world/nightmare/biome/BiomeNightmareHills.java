package com.legacy.goodnightsleep.world.nightmare.biome;

import java.util.ArrayList;
import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTallGrass;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeNightmareHills extends BiomeGenBase
{
	@SuppressWarnings("unchecked")
	public BiomeNightmareHills()
	{
		super(GNSConfig.getNightmareBiomeID());
		this.spawnableCreatureList.clear();
		
		this.setBiomeName("Nightmare Hills");
		this.setDisableRain();
		this.rootHeight = 0.1F;
		this.heightVariation = 1.0F;
		this.temperature = 2.0F;
		this.rainfall = 0.0F;
		
		ArrayList<SpawnListEntry> list = new ArrayList<SpawnListEntry>();
 
		list.clear();
		
		this.topBlock = BlocksGNS.nightmare_grass;
		this.fillerBlock = Blocks.dirt;

	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 3, 3));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 1, 2));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 50, 4, 4));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 10, 4, 4));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySilverfish.class, 10, 4, 4));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 10, 4, 4));
	     //this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTormenter.class, 50, 10, 10));
	     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGiantZombie.class, 95, 1, 1));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenGNSTallGrass(BlocksGNS.tall_nightmare_grass);
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