package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.BigHopeMushroomFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.general_features.DreamScatteredPlantFeature;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GoodDreamPlainsBiome extends Biome
{
	private FillerBlockType delusionConfig = OreFeatureConfig.FillerBlockType.create("dream_stone", "dream_stone", new BlockMatcher(GNSBlocks.delusion_stone));
	public static final BlockClusterFeatureConfig TALL_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.tall_dream_grass.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
	public static final BlockClusterFeatureConfig DREAM_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(GNSBlocks.cyan_flower.getDefaultState(), 1).func_227407_a_(GNSBlocks.orange_flower.getDefaultState(), 1).func_227407_a_(GNSBlocks.lolipop_bush.getDefaultState(), 2), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();

	public GoodDreamPlainsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState())).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(TALL_GRASS).func_227228_a_(Placement.NOISE_HEIGHTMAP_DOUBLE.func_227446_a_(new NoiseDependant(-0.8D, 5, 10))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.withConfiguration(DREAM_FLOWER_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(5))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false).withConfiguration(DefaultBiomeFeatures.field_226810_e_).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false, 5, GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState()).withConfiguration(DefaultBiomeFeatures.field_226810_e_).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new GNSBigTreeFeature(TreeFeatureConfig::func_227338_a_, false, GNSBlocks.dream_log.getDefaultState(), GNSBlocks.diamond_leaves.getDefaultState()).withConfiguration(DefaultBiomeFeatures.field_226810_e_).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.2F, 1))));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, new DreamSpongeFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(20))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new BigHopeMushroomFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.3F, 1))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, GNSBlocks.present.getDefaultState()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(32))));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCaveWorldCarver(ProbabilityConfig::deserialize, 256), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCanyonWorldCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.02F)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.dream_dirt.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.coal_ore.getDefaultState(), 17)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.candy_ore.getDefaultState(), 15)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.rainbow_ore.getDefaultState(), 9)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 64))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.positite_block.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(1, 0, 0, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.lapis_ore.getDefaultState(), 7)).func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 16, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, Blocks.GLOWSTONE.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(1, 0, 0, 7))));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.UNICORN, 90, 1, 4));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));
	}
}