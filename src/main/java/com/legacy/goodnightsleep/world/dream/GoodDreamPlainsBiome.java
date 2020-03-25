package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.general_features.GNSFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GoodDreamPlainsBiome extends Biome
{
	private static final SurfaceBuilderConfig DREAM_SURFACE_BUILDER = new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());

	public GoodDreamPlainsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, DREAM_SURFACE_BUILDER).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(GNSBlocks.tall_dream_grass.getDefaultState()), Placement.NOISE_HEIGHTMAP_DOUBLE, new NoiseDependant(-0.8D, 5, 10)));

		GNSFeatures.addDreamTrees(this);
		GNSFeatures.addScatteredDreamFeatures(this);
		GNSFeatures.addDreamOres(this);

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCaveWorldCarver(ProbabilityConfig::deserialize, 256), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCanyonWorldCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.02F)));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.UNICORN, 90, 1, 4));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));
	}
}