package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.world.BaseGNSBiome;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.general_features.GNSFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GoodDreamPlainsBiome extends BaseGNSBiome
{
	private static final FillerBlockType DELUSION_CONFIG = OreFeatureConfig.FillerBlockType.create("dream_stone", "dream_stone", new BlockMatcher(GNSBlocks.delusion_stone));
	public static final BlockClusterFeatureConfig TALL_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dream_grass.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
	public static final BlockClusterFeatureConfig DREAM_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(GNSBlocks.cyan_flower.getDefaultState(), 1).addWeightedBlockstate(GNSBlocks.orange_flower.getDefaultState(), 1).addWeightedBlockstate(GNSBlocks.lolipop_bush.getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).build();
	public static final SurfaceBuilderConfig DREAM_SURFACE_BUILDER = new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());

	public GoodDreamPlainsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, DREAM_SURFACE_BUILDER).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_GRASS).withPlacement(Placement.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseDependant(-0.8D, 5, 10))));
		this.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(DREAM_FLOWER_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(5))));

		GNSFeatures.addDreamTrees(this);
		GNSFeatures.addScatteredDreamFeatures(this);
		GNSFeatures.addDreamOres(this, DELUSION_CONFIG);

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCaveWorldCarver(ProbabilityConfig::deserialize, 256), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCanyonWorldCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.02F)));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.UNICORN, 90, 1, 4));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));
	}
}