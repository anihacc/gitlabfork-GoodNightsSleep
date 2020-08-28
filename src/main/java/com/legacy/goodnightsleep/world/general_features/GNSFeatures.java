package com.legacy.goodnightsleep.world.general_features;

import java.util.OptionalInt;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSRegistry;
import com.legacy.goodnightsleep.world.carver.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.carver.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent.Register;

public class GNSFeatures
{
	public static final Feature<NoFeatureConfig> DREAM_SPONGE = new DreamSpongeFeature(NoFeatureConfig.field_236558_a_);
	public static final Feature<NoFeatureConfig> SCATTERED_PRESENTS = new DreamScatteredPlantFeature(NoFeatureConfig.field_236558_a_, GNSBlocks.present.getDefaultState());

	public static final Feature<NoFeatureConfig> NETHER_SPLASH = new NetherSplashFeature(NoFeatureConfig.field_236558_a_);
	public static final Feature<NoFeatureConfig> SCATTERED_PUMPKINS = new DreamScatteredPlantFeature(NoFeatureConfig.field_236558_a_, Blocks.PUMPKIN.getDefaultState());

	public static final RuleTest DELUSION_RULE_TEST = new BlockMatchRuleTest(GNSBlocks.delusion_stone);
	public static final BlockClusterFeatureConfig DREAM_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dream_grass.getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
	public static final BlockClusterFeatureConfig NIGHTMARE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.nightmare_grass.getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
	public static final BlockClusterFeatureConfig DREAM_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(GNSBlocks.cyan_flower.getDefaultState(), 1).addWeightedBlockstate(GNSBlocks.orange_flower.getDefaultState(), 1).addWeightedBlockstate(GNSBlocks.lolipop_bush.getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig TALL_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.nightmare_grass.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
	public static final BlockClusterFeatureConfig PRICKLY_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.prickly_nightmare_grass.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
	public static final BlockClusterFeatureConfig DEAD_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(GNSBlocks.dead_flower.getDefaultState(), 1), new SimpleBlockPlacer())).tries(64).build();

	public static final ConfiguredFeature<?, ?> DREAM_TREE = createBasicTree(GNSBlocks.dream_log.getDefaultState(), GNSBlocks.dream_leaves.getDefaultState(), 3);
	public static final ConfiguredFeature<?, ?> CANDY_TREE = createBasicTree(GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState(), 5);
	public static final ConfiguredFeature<?, ?> DIAMOND_TREE = Feature.field_236291_c_.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dream_log.getDefaultState()), new SimpleBlockStateProvider(GNSBlocks.diamond_leaves.getDefaultState()), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
	public static final ConfiguredFeature<?, ?> DEAD_TREE = Feature.field_236291_c_.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dead_log.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
	public static final ConfiguredFeature<?, ?> BLOOD_TREE = createBasicTree(GNSBlocks.blood_log.getDefaultState(), Blocks.AIR.getDefaultState(), 5);

	public static final ConfiguredFeature<?, ?> HUGE_HOPE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom_block.getDefaultState().with(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2));
	public static final ConfiguredFeature<?, ?> HUGE_DESPAIR_MUSHROOM = Feature.HUGE_BROWN_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.despair_mushroom_block.getDefaultState().with(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 3));

	public static void init(Register<Feature<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "dream_sponge", DREAM_SPONGE);
		GNSRegistry.register(event.getRegistry(), "scattered_presents", SCATTERED_PRESENTS);

		GNSRegistry.register(event.getRegistry(), "nether_splash", NETHER_SPLASH);
		GNSRegistry.register(event.getRegistry(), "scattered_pumpkins", SCATTERED_PUMPKINS);
	}

	public static void addDreamOres(BiomeGenerationSettings.Builder biomeIn)
	{
		RuleTest delusionConfig = DELUSION_RULE_TEST;
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.dream_dirt.getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.coal_ore.getDefaultState(), 17)).func_242733_d(128).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.candy_ore.getDefaultState(), 15)).func_242733_d(64).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.rainbow_ore.getDefaultState(), 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.positite_ore.getDefaultState(), 8)).func_242733_d(16).func_242728_a().func_242731_b(1));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.lapis_ore.getDefaultState(), 7)).withPlacement(Placement.field_242910_o.configure(new DepthAverageConfig(16, 16))).func_242728_a());
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, Blocks.GLOWSTONE.getDefaultState(), 8)).func_242733_d(7).func_242728_a().func_242731_b(1));
	}

	public static void addDreamTrees(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DREAM_TREE.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.CANDY_TREE.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DIAMOND_TREE.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.2F, 1))));
	}

	public static void addScatteredDreamFeatures(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.RAW_GENERATION, GNSFeatures.DREAM_SPONGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.HUGE_HOPE_MUSHROOM.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.3F, 1))));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.SCATTERED_PRESENTS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.field_244002_m).func_242729_a(32));
	}

	public static void addNightmareOres(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Blocks.DIRT.getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(10));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Blocks.GRAVEL.getDefaultState(), 33)).func_242733_d(256).func_242728_a().func_242731_b(8));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Blocks.COAL_ORE.getDefaultState(), 17)).func_242733_d(128).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, GNSBlocks.necrum_ore.getDefaultState(), 15)).func_242733_d(128).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Blocks.IRON_ORE.getDefaultState(), 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, GNSBlocks.zitrite_ore.getDefaultState(), 8)).func_242733_d(32).func_242728_a().func_242731_b(5));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, GNSBlocks.negatite_ore.getDefaultState(), 5)).func_242733_d(16).func_242728_a().func_242731_b(1));
		biomeIn.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Blocks.LAPIS_ORE.getDefaultState(), 7)).withPlacement(Placement.field_242910_o.configure(new DepthAverageConfig(16, 16))).func_242728_a());
	}

	public static void addNightmareTrees(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DEAD_TREE.withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.5F, 1))));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.BLOOD_TREE.withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
	}

	public static void addScatteredNightmareFeatures(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.RAW_GENERATION, GNSFeatures.NETHER_SPLASH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(20));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.HUGE_DESPAIR_MUSHROOM.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.SCATTERED_PUMPKINS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.field_244002_m).func_242729_a(10));
	}

	public static void addNoiseBasedGrass(BiomeGenerationSettings.Builder biomeIn, BlockClusterFeatureConfig config)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Features.Placements.field_244002_m).withPlacement(Placement.field_242900_d.configure(new NoiseDependant(-0.8D, 5, 10))));
	}

	public static void addGrass(BiomeGenerationSettings.Builder biomeIn, BlockClusterFeatureConfig config, int frequency)
	{
		biomeIn.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Features.Placements.field_244002_m).func_242731_b(frequency));
	}

	public static void addMushrooms(Biome biomeIn)
	{
		/*final BlockClusterFeatureConfig HOPE_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
		final BlockClusterFeatureConfig DESPAIR_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.despair_mushroom.getDefaultState()), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
		
		if (GenUtil.doesBiomeMatch(biomeIn, Biomes.NETHER_WASTES))
		{
			biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(HOPE_MUSHROOM_CONFIG).withPlacement(Placement.field_242898_b.configure(new ChanceRangeConfig(0.3F, 0, 0, 128))));
			biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DESPAIR_MUSHROOM_CONFIG).withPlacement(Placement.field_242898_b.configure(new ChanceRangeConfig(0.3F, 0, 0, 128))));
		}
		else
		{
			GenUtil.addFeature(biomeIn, GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(HOPE_MUSHROOM_CONFIG).withPlacement(Placement.field_242898_b.configure(new ChanceConfig(8))));
			GenUtil.addFeature(biomeIn, GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DESPAIR_MUSHROOM_CONFIG).withPlacement(Placement.field_242898_b.configure(new ChanceConfig(4))));
		}*/
	}

	public static ConfiguredFeature<?, ?> createBasicTree(BlockState log, BlockState leaves, int height)
	{
		return Feature.field_236291_c_.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(height, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
	}

	public static class Carvers
	{
		public static final WorldCarver<ProbabilityConfig> DELUSION_CAVE_CARVER = new GNSCaveWorldCarver(ProbabilityConfig.field_236576_b_, 256);
		public static final WorldCarver<ProbabilityConfig> DELUSION_CANYON_CARVER = new GNSCanyonWorldCarver(ProbabilityConfig.field_236576_b_);

		public static void init(Register<WorldCarver<?>> event)
		{
			GNSRegistry.register(event.getRegistry(), "delusion_cave_carver", DELUSION_CAVE_CARVER);
			GNSRegistry.register(event.getRegistry(), "delusion_canyon_carver", DELUSION_CANYON_CARVER);
		}
	}
}
