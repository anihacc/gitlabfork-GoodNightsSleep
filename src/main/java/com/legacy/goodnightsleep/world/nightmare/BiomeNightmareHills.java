package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ObjectHolder;

public class BiomeNightmareHills extends Biome
{
	@ObjectHolder("goodnightsleep:nightmare_hills")
	public static final BiomeNightmareHills INSTANCE = null;

	public BiomeNightmareHills()
	{
		super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, new SurfaceBuilderConfig(BlocksGNS.nightmare_grass.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).precipitation(Biome.RainType.NONE).category(Biome.Category.EXTREME_HILLS).depth(0.1F).scale(1.0F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(BlocksGNS.tall_nightmare_grass.getDefaultState()), SURFACE_PLUS_32, new FrequencyConfig(3)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new GNSBigTreeFeature(false, BlocksGNS.dead_log.getDefaultState(), Blocks.AIR.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.5F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new DreamTreeFeature(false, 5, BlocksGNS.blood_log.getDefaultState(), Blocks.AIR.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(new NetherSplashFeature(), IFeatureConfig.NO_FEATURE_CONFIG, SURFACE_PLUS_32, new FrequencyConfig(20)));

		//this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFlowerFeature(new GNSFlowersFeature(), SURFACE_PLUS_32, new FrequencyConfig(5)));

		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.GHAST, 30, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_PIGMAN, 100, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		this.addSpawn(EnumCreatureType.MONSTER, new Biome.SpawnListEntry(EntityType.SILVERFISH, 5, 4, 4));
	}
}