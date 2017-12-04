package com.legacy.goodnightsleep.common.world.nightmare;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityTormenter;
import com.legacy.goodnightsleep.common.world.genfeatures.WorldGenTallGrass;

public class BiomeGenNightmareHills extends Biome
{
	public BiomeGenNightmareHills()
	{
		super(new BiomeProperties("Nightmare Hills").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(2.0F).setTemperature(2.0F).setRainfall(0.0F));
		this.spawnableCreatureList.clear();
		
		ArrayList<SpawnListEntry> list = new ArrayList<SpawnListEntry>();
 
		list.clear();
		
		 this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 95, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 5, 1, 1));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityBlaze.class, 100, 2, 3));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitherSkeleton.class, 10, 5, 5));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 5, 5));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTormenter.class, 100, 4, 4));
	     this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGiantZombie.class, 95, 1, 1));
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenTallGrass(BlocksGNS.tall_nightmare_grass.getDefaultState());
    }
	
	public BiomeDecorator createBiomeDecorator()
    {
    	return new NightmareBiomeDecorator();
    }
	
	/*@SideOnly(Side.CLIENT)
    public final int setWaterColor()
    {
        return 0xcee7f5;
    }*/
	
	public float getSpawningChance()
    {
        return 0.1F;
    }

}