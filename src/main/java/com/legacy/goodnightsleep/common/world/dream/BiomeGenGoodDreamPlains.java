package com.legacy.goodnightsleep.common.world.dream;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.world.genfeatures.WorldGenTallGrass;

public class BiomeGenGoodDreamPlains extends Biome
{
	public BiomeGenGoodDreamPlains()
	{
		super(new BiomeProperties("Good Dream Plains").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(0.5F).setTemperature(0.8F).setRainfall(0.0F));
		this.spawnableCreatureList.clear(); //.setRainDisabled() .setRainfall(0.0F)
		
		ArrayList<SpawnListEntry> list = new ArrayList<SpawnListEntry>();

		this.addCreatureEntry(list);
 
		this.spawnableCreatureList.addAll(list);

		list.clear();

		this.topBlock = BlocksGNS.dream_grass.getDefaultState();
		this.fillerBlock = BlocksGNS.dream_dirt.getDefaultState();
	}

	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
    	return new WorldGenTallGrass(BlocksGNS.tall_dream_grass.getDefaultState());
    }
	
	private void addCreatureEntry(ArrayList<SpawnListEntry> list)
	{
		//list.add(new SpawnListEntry(EntityStardustSheep.class, 25, 3, 4));
		list.add(new SpawnListEntry(EntityPig.class, 15, 2, 4));
		list.add(new SpawnListEntry(EntityHorse.class, 7, 3, 4));
		//list.add(new SpawnListEntry(EntityAzulfo.class, 27, 3, 5));
	}
	
	public BiomeDecorator createBiomeDecorator()
    {
    	return new GoodDreamBiomeDecorator();
    }
	
	/*@SideOnly(Side.CLIENT)
    public final int setWaterColor()
    {
        return 0xcee7f5;
    }*/
	
	public Biome.TempCategory getTempCategory()
    {
        return Biome.TempCategory.MEDIUM;
    }
	
	public float getSpawningChance()
    {
        return 0.1F;
    }

}