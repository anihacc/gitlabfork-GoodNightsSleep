package com.legacy.goodnightsleep.registry;

import java.util.OptionalInt;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.carver.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.carver.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.FatHopeMushroomFeature;
import com.legacy.goodnightsleep.world.dream.features.TallHopeMushroomFeature;
import com.legacy.goodnightsleep.world.general_features.DreamScatteredPlantFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
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
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class GNSFeatures
{
	public static final Feature<BigMushroomFeatureConfig> TALL_HOPE_MUSHROOM = new TallHopeMushroomFeature(BigMushroomFeatureConfig.CODEC);
	public static final Feature<BigMushroomFeatureConfig> FAT_HOPE_MUSHROOM = new FatHopeMushroomFeature(BigMushroomFeatureConfig.CODEC);

	public static final Feature<NoFeatureConfig> DREAM_SPONGE = new DreamSpongeFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SCATTERED_PRESENTS = new DreamScatteredPlantFeature(NoFeatureConfig.CODEC, GNSBlocks.present.defaultBlockState());

	public static final Feature<NoFeatureConfig> NETHER_SPLASH = new NetherSplashFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SCATTERED_PUMPKINS = new DreamScatteredPlantFeature(NoFeatureConfig.CODEC, Blocks.PUMPKIN.defaultBlockState());

	public static final RuleTest DELUSION_RULE_TEST = new BlockMatchRuleTest(GNSBlocks.delusion_stone);
	public static final BlockClusterFeatureConfig DREAM_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dream_grass.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
	public static final BlockClusterFeatureConfig NIGHTMARE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.nightmare_grass.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
	public static final BlockClusterFeatureConfig DREAM_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(GNSBlocks.cyan_flower.defaultBlockState(), 1).add(GNSBlocks.orange_flower.defaultBlockState(), 1).add(GNSBlocks.lollipop_bush.defaultBlockState(), 2), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig LOLLIPOP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.lollipop_bush.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();
	public static final BlockClusterFeatureConfig PRICKLY_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.prickly_nightmare_grass.defaultBlockState()), new SimpleBlockPlacer())).tries(32).build();
	public static final BlockClusterFeatureConfig DEAD_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(GNSBlocks.dead_flower.defaultBlockState(), 1), new SimpleBlockPlacer())).tries(64).build();
	public static final BlockClusterFeatureConfig HOPE_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom.defaultBlockState()), new SimpleBlockPlacer())).tries(64).noProjection().build();
	public static final BlockClusterFeatureConfig DESPAIR_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.despair_mushroom.defaultBlockState()), new SimpleBlockPlacer())).tries(64).noProjection().build();

	public static void init(Register<Feature<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "tall_hope_mushroom", TALL_HOPE_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "fat_hope_mushroom", FAT_HOPE_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "dream_sponge", DREAM_SPONGE);
		GNSRegistry.register(event.getRegistry(), "scattered_presents", SCATTERED_PRESENTS);

		GNSRegistry.register(event.getRegistry(), "nether_splash", NETHER_SPLASH);
		GNSRegistry.register(event.getRegistry(), "scattered_pumpkins", SCATTERED_PUMPKINS);

		Configured.init();
	}

	public static void addDreamOres(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_DIRT_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_COAL_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_CANDY_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_RAINBOW_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_POSITITE_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_LAPIS_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.DREAM_GLOWSTONE_ORE);
	}

	public static void addDreamTrees(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HILLS_DREAM_TREE);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HILLS_CANDY_TREE);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HILLS_DIAMOND_TREE);
	}

	public static void addScatteredDreamFeatures(BiomeGenerationSettings.Builder biomeIn)
	{
		addDreamSponges(biomeIn);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SCATTERED_PRESENTS);
	}

	public static void addDreamSponges(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.RAW_GENERATION, Configured.SCATTERED_SPONGE);
	}

	public static void addHugeHopeMushrooms(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HUGE_HOPE_MUSHROOM);
	}

	/**
	 * Unused, was originally planned for the Hopeful Fields biome.
	 */
	public static void addHopeMushroomFields(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.FIELDS_HOPE_MUSHROOM);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.FIELDS_LARGER_HOPE_MUSHROOM);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.FIELDS_VERY_LARGE_HOPE_MUSHROOM);
	}

	public static void addNightmareOres(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_COAL_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_GRAVEL_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_COAL_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_NECRUM_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_ZITRITE_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_IRON_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_NEGATITE_ORE);
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.NIGHTMARE_LAPIS_ORE);
	}

	public static void addNightmareTrees(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HILLS_DEAD_TREE);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HILLS_BLOOD_TREE);
	}

	public static void addScatteredNightmareFeatures(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.RAW_GENERATION, Configured.NETHER_SPLASH);
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SCATTERED_PUMPKINS);
	}

	public static void addHugeDespairMushrooms(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HUGE_DESPAIR_MUSHROOM);
	}

	public static void addCarvers(BiomeGenerationSettings.Builder biomeIn)
	{
		biomeIn.addCarver(GenerationStage.Carving.AIR, Configured.DELUSION_CAVE_CARVER);
		biomeIn.addCarver(GenerationStage.Carving.AIR, Configured.DELUSION_CANYON_CARVER);
	}

	public static void addMushrooms(BiomeLoadingEvent eventIn)
	{
		if (eventIn.getName().toString().contains(GoodNightSleep.MODID) || eventIn.getCategory() == Biome.Category.THEEND || eventIn.getCategory() == Biome.Category.NONE)
			return;

		if (eventIn.getCategory() == Biome.Category.NETHER)
		{
			eventIn.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.NETHER_HOPE_MUSHROOM_PATCH);
			eventIn.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.NETHER_DESPAIR_MUSHROOM_PATCH);
		}
		else
		{
			eventIn.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.HOPE_MUSHROOM_PATCH);
			eventIn.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DESPAIR_MUSHROOM_PATCH);
		}
	}

	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> createBasicTree(BlockState log, BlockState leaves, int height)
	{
		return createBasicTree(log, leaves, height, 2);
	}

	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> createBasicTree(BlockState log, BlockState leaves, int height, int randHeight)
	{
		return Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(height, randHeight, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build());
	}

	public static class Carvers
	{
		public static final WorldCarver<ProbabilityConfig> DELUSION_CAVE_CARVER = new GNSCaveWorldCarver(ProbabilityConfig.CODEC, 256);
		public static final WorldCarver<ProbabilityConfig> DELUSION_CANYON_CARVER = new GNSCanyonWorldCarver(ProbabilityConfig.CODEC);

		public static void init(Register<WorldCarver<?>> event)
		{
			GNSRegistry.register(event.getRegistry(), "delusion_cave_carver", DELUSION_CAVE_CARVER);
			GNSRegistry.register(event.getRegistry(), "delusion_canyon_carver", DELUSION_CANYON_CARVER);

			Configured.initCarvers();
		}
	}

	public static class Configured
	{
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_DREAM_TREE = createBasicTree(GNSBlocks.dream_log.defaultBlockState(), GNSBlocks.dream_leaves.defaultBlockState(), 4, 1);
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_CANDY_TREE = createBasicTree(GNSBlocks.white_log.defaultBlockState(), GNSBlocks.candy_leaves.defaultBlockState(), 5);
		// 5, 7
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_LARGE_CANDY_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.white_log.defaultBlockState()), new SimpleBlockStateProvider(GNSBlocks.candy_leaves.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 4), new StraightTrunkPlacer(7, 3, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());

		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_DIAMOND_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dream_log.defaultBlockState()), new SimpleBlockStateProvider(GNSBlocks.diamond_leaves.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_DEAD_TREE = createBasicTree(GNSBlocks.dead_log.defaultBlockState(), Blocks.AIR.defaultBlockState(), 5);
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_BLOOD_TREE = createBasicTree(GNSBlocks.blood_log.defaultBlockState(), Blocks.AIR.defaultBlockState(), 5);
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_LARGE_DEAD_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.dead_log.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
		public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BASE_LARGE_BLOOD_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.blood_log.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(8)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());

		public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> BASE_HUGE_HOPE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2));
		public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> BASE_HUGE_DESPAIR_MUSHROOM = Feature.HUGE_BROWN_MUSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.despair_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 3));

		public static final ConfiguredFeature<?, ?> TALLER_HOPE_MUSHROOM = GNSFeatures.TALL_HOPE_MUSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2));
		public static final ConfiguredFeature<?, ?> SLIGHTLY_LARGER_HOPE_MUSHROOM = GNSFeatures.TALL_HOPE_MUSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 3));
		public static final ConfiguredFeature<?, ?> VERY_LARGE_HOPE_MUSHROOM = GNSFeatures.FAT_HOPE_MUSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 5));

		public static final ConfiguredFeature<?, ?> HOPE_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(HOPE_MUSHROOM_CONFIG).decorated(Placement.CHANCE.configured(new ChanceConfig(8)));
		public static final ConfiguredFeature<?, ?> DESPAIR_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(DESPAIR_MUSHROOM_CONFIG).decorated(Placement.CHANCE.configured(new ChanceConfig(4)));
		public static final ConfiguredFeature<?, ?> NETHER_HOPE_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(HOPE_MUSHROOM_CONFIG).range(128).chance(8);
		public static final ConfiguredFeature<?, ?> NETHER_DESPAIR_MUSHROOM_PATCH = Feature.RANDOM_PATCH.configured(DESPAIR_MUSHROOM_CONFIG).range(128).chance(4);

		public static final ConfiguredFeature<?, ?> HILLS_DREAM_TREE = GNSFeatures.Configured.BASE_DREAM_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		public static final ConfiguredFeature<?, ?> HILLS_CANDY_TREE = GNSFeatures.Configured.BASE_CANDY_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		public static final ConfiguredFeature<?, ?> HILLS_DIAMOND_TREE = GNSFeatures.Configured.BASE_DIAMOND_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.2F, 1)));

		public static final ConfiguredFeature<?, ?> DREAM_DIRT_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.dream_dirt.defaultBlockState(), 33)).range(256).squared().count(10);
		public static final ConfiguredFeature<?, ?> DREAM_CANDY_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.candy_ore.defaultBlockState(), 15)).range(64).squared().count(20);
		public static final ConfiguredFeature<?, ?> DREAM_RAINBOW_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.rainbow_ore.defaultBlockState(), 9)).range(64).squared().count(20);
		public static final ConfiguredFeature<?, ?> DREAM_POSITITE_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.positite_ore.defaultBlockState(), 8)).range(16).squared().count(1);
		public static final ConfiguredFeature<?, ?> DREAM_COAL_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.coal_ore.defaultBlockState(), 17)).range(128).squared().count(20);
		public static final ConfiguredFeature<?, ?> DREAM_LAPIS_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, GNSBlocks.lapis_ore.defaultBlockState(), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared();
		public static final ConfiguredFeature<?, ?> DREAM_GLOWSTONE_ORE = Feature.ORE.configured(new OreFeatureConfig(DELUSION_RULE_TEST, Blocks.GLOWSTONE.defaultBlockState(), 8)).range(7).squared().count(1);

		public static final ConfiguredFeature<?, ?> SCATTERED_PRESENTS = GNSFeatures.SCATTERED_PRESENTS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32);
		public static final ConfiguredFeature<?, ?> SCATTERED_SPONGE = GNSFeatures.DREAM_SPONGE.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(20);
		public static final ConfiguredFeature<?, ?> HUGE_HOPE_MUSHROOM = GNSFeatures.Configured.BASE_HUGE_HOPE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.3F, 1)));

		public static final ConfiguredFeature<?, ?> FIELDS_HOPE_MUSHROOM = GNSFeatures.Configured.BASE_HUGE_HOPE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 1)));
		public static final ConfiguredFeature<?, ?> FIELDS_LARGER_HOPE_MUSHROOM = GNSFeatures.Configured.SLIGHTLY_LARGER_HOPE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 1.0F, 2)));
		public static final ConfiguredFeature<?, ?> FIELDS_VERY_LARGE_HOPE_MUSHROOM = GNSFeatures.Configured.VERY_LARGE_HOPE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));

		public static final ConfiguredFeature<?, ?> NIGHTMARE_DIRT_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.defaultBlockState(), 33)).range(256).squared().count(10);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_GRAVEL_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.defaultBlockState(), 33)).range(256).squared().count(8);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_NECRUM_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.necrum_ore.defaultBlockState(), 15)).range(128).squared().count(20);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_ZITRITE_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.zitrite_ore.defaultBlockState(), 8)).range(32).squared().count(5);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_NEGATITE_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.negatite_ore.defaultBlockState(), 5)).range(16).squared().count(1);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_COAL_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.defaultBlockState(), 17)).range(128).squared().count(20);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_IRON_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 9)).range(64).squared().count(20);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_LAPIS_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.defaultBlockState(), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared();

		public static final ConfiguredFeature<?, ?> HILLS_DEAD_TREE = GNSFeatures.Configured.BASE_LARGE_DEAD_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 1)));
		public static final ConfiguredFeature<?, ?> HILLS_BLOOD_TREE = GNSFeatures.Configured.BASE_BLOOD_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 1)));

		public static final ConfiguredFeature<?, ?> NETHER_SPLASH = GNSFeatures.NETHER_SPLASH.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(20);
		public static final ConfiguredFeature<?, ?> SCATTERED_PUMPKINS = GNSFeatures.SCATTERED_PUMPKINS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(35);
		public static final ConfiguredFeature<?, ?> HUGE_DESPAIR_MUSHROOM = GNSFeatures.Configured.BASE_HUGE_DESPAIR_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 1)));

		public static final ConfiguredFeature<?, ?> PLAINS_DIAMOND_TREE = GNSFeatures.Configured.BASE_DIAMOND_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
		public static final ConfiguredFeature<?, ?> PLAINS_HOPE_MUSHROOM = GNSFeatures.Configured.BASE_HUGE_HOPE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));

		public static final ConfiguredFeature<?, ?> FOREST_DREAM_TREE = GNSFeatures.Configured.BASE_DREAM_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 1.1F, 5)));
		public static final ConfiguredFeature<?, ?> FOREST_CANDY_TREE = GNSFeatures.Configured.BASE_CANDY_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.8F, 1)));

		public static final ConfiguredFeature<?, ?> LANDS_CANDY_TREE = GNSFeatures.Configured.BASE_LARGE_CANDY_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 1.0F, 5)));
		public static final ConfiguredFeature<?, ?> LANDS_DREAM_TREE = GNSFeatures.Configured.BASE_DREAM_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.3F, 1)));

		public static final ConfiguredFeature<?, ?> PLAINS_BLOOD_TREE = GNSFeatures.Configured.BASE_LARGE_BLOOD_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		public static final ConfiguredFeature<?, ?> PLAINS_DESPAIR_MUSHROOM = GNSFeatures.Configured.BASE_HUGE_DESPAIR_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1)));

		public static final ConfiguredFeature<?, ?> FOREST_DEAD_TREE = GNSFeatures.Configured.BASE_DEAD_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 1.1F, 5)));
		public static final ConfiguredFeature<?, ?> FOREST_BLOOD_TREE = GNSFeatures.Configured.BASE_LARGE_BLOOD_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.8F, 1)));

		public static final ConfiguredFeature<?, ?> NOISE_BASED_DREAM_GRASS = Feature.RANDOM_PATCH.configured(GNSFeatures.DREAM_GRASS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10)));
		public static final ConfiguredFeature<?, ?> NOISE_BASED_NIGHTMARE_GRASS = Feature.RANDOM_PATCH.configured(NIGHTMARE_GRASS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10)));
		public static final ConfiguredFeature<?, ?> DREAM_FLOWERS_5 = Feature.FLOWER.configured(GNSFeatures.DREAM_FLOWER_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_FLOWERS_5 = Feature.FLOWER.configured(GNSFeatures.DEAD_FLOWER_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5);
		public static final ConfiguredFeature<?, ?> DREAM_LOLLIPOPS_20 = Feature.FLOWER.configured(GNSFeatures.LOLLIPOP_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(20);
		public static final ConfiguredFeature<?, ?> DREAM_MUSHROOMS_25 = Feature.FLOWER.configured(GNSFeatures.HOPE_MUSHROOM_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(25);
		public static final ConfiguredFeature<?, ?> NIGHTMARE_GRASS_5 = Feature.RANDOM_PATCH.configured(GNSFeatures.NIGHTMARE_GRASS_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5);
		public static final ConfiguredFeature<?, ?> PRICKLY_NIGHTMARE_GRASS_1 = Feature.RANDOM_PATCH.configured(GNSFeatures.PRICKLY_GRASS_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> PRICKLY_NIGHTMARE_GRASS_2 = Feature.RANDOM_PATCH.configured(GNSFeatures.PRICKLY_GRASS_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2);

		public static final ConfiguredCarver<?> DELUSION_CAVE_CARVER = GNSFeatures.Carvers.DELUSION_CAVE_CARVER.configured(new ProbabilityConfig(0.14285715F));
		public static final ConfiguredCarver<?> DELUSION_CANYON_CARVER = GNSFeatures.Carvers.DELUSION_CANYON_CARVER.configured(new ProbabilityConfig(0.02F));

		public static void init()
		{
			register("dream_tree", BASE_DREAM_TREE);
			register("candy_tree", BASE_CANDY_TREE);
			register("large_candy_tree", BASE_LARGE_CANDY_TREE);

			register("diamond_tree", BASE_DIAMOND_TREE);
			register("dead_tree", BASE_DEAD_TREE);
			register("blood_tree", BASE_BLOOD_TREE);
			register("large_dead_tree", BASE_LARGE_DEAD_TREE);
			register("large_blood_tree", BASE_LARGE_BLOOD_TREE);

			register("taller_hope_mushroom", TALLER_HOPE_MUSHROOM);
			register("slightly_larger_hope_mushroom", SLIGHTLY_LARGER_HOPE_MUSHROOM);
			register("very_large_hope_mushroom", VERY_LARGE_HOPE_MUSHROOM);

			register("hope_mushroom_patch", HOPE_MUSHROOM_PATCH);
			register("despair_mushroom_patch", DESPAIR_MUSHROOM_PATCH);
			register("nether_hope_mushroom_patch", NETHER_HOPE_MUSHROOM_PATCH);
			register("nether_despair_mushroom_patch", NETHER_DESPAIR_MUSHROOM_PATCH);

			register("hills_dream_tree", HILLS_DREAM_TREE);
			register("hills_candy_tree", HILLS_CANDY_TREE);
			register("hills_diamond_tree", HILLS_DIAMOND_TREE);

			register("dream_dirt_ore", DREAM_DIRT_ORE);
			register("dream_candy_ore", DREAM_CANDY_ORE);
			register("dream_rainbow_ore", DREAM_RAINBOW_ORE);
			register("dream_positite_ore", DREAM_POSITITE_ORE);
			register("dream_coal_ore", DREAM_COAL_ORE);
			register("dream_lapis_ore", DREAM_LAPIS_ORE);
			register("dream_glowstone_ore", DREAM_GLOWSTONE_ORE);

			register("scattered_presents", SCATTERED_PRESENTS);
			register("scattered_sponge", SCATTERED_SPONGE);
			register("huge_hope_mushroom", HUGE_HOPE_MUSHROOM);

			register("fields_hope_mushroom", FIELDS_HOPE_MUSHROOM);
			register("fields_larger_hope_mushroom", FIELDS_LARGER_HOPE_MUSHROOM);
			register("fields_very_large_hope_mushroom", FIELDS_VERY_LARGE_HOPE_MUSHROOM);

			register("nightmare_dirt_ore", NIGHTMARE_DIRT_ORE);
			register("nightmare_gravel_ore", NIGHTMARE_GRAVEL_ORE);
			register("nightmare_necrum_ore", NIGHTMARE_NECRUM_ORE);
			register("nightmare_zitrite_ore", NIGHTMARE_ZITRITE_ORE);
			register("nightmare_negatite_ore", NIGHTMARE_NEGATITE_ORE);
			register("nightmare_coal_ore", NIGHTMARE_COAL_ORE);
			register("nightmare_iron_ore", NIGHTMARE_IRON_ORE);
			register("nightmare_lapis_ore", NIGHTMARE_LAPIS_ORE);

			register("hills_dead_tree", HILLS_DEAD_TREE);
			register("hills_blood_tree", HILLS_BLOOD_TREE);

			register("nether_splash", NETHER_SPLASH);
			register("scattered_pumpkins", SCATTERED_PUMPKINS);
			register("huge_despair_mushroom", HUGE_DESPAIR_MUSHROOM);

			register("plains_diamond_tree", PLAINS_DIAMOND_TREE);
			register("plains_hope_mushroom", PLAINS_HOPE_MUSHROOM);

			register("forest_dream_tree", FOREST_DREAM_TREE);
			register("forest_candy_tree", FOREST_CANDY_TREE);

			register("lands_candy_tree", LANDS_CANDY_TREE);
			register("lands_dream_tree", LANDS_DREAM_TREE);

			register("plains_blood_tree", PLAINS_BLOOD_TREE);
			register("plains_despair_mushroom", PLAINS_DESPAIR_MUSHROOM);

			register("forest_dead_tree", FOREST_DEAD_TREE);
			register("forest_blood_tree", FOREST_BLOOD_TREE);

			register("noise_based_dream_grass", NOISE_BASED_DREAM_GRASS);
			register("noise_based_nightmare_grass", NOISE_BASED_NIGHTMARE_GRASS);
			register("dream_flowers_5", DREAM_FLOWERS_5);
			register("nightmare_flowers_5", NIGHTMARE_FLOWERS_5);
			register("dream_lollipops_20", DREAM_LOLLIPOPS_20);
			register("dream_mushrooms_25", DREAM_MUSHROOMS_25);
			register("nightmare_grass_5", NIGHTMARE_GRASS_5);
			register("prickly_nightmare_grass_1", PRICKLY_NIGHTMARE_GRASS_1);
			register("prickly_nightmare_grass_2", PRICKLY_NIGHTMARE_GRASS_2);
		}

		public static void initCarvers()
		{
			registerCarver("delusion_cave_carver", DELUSION_CAVE_CARVER);
			registerCarver("delusion_canyon_carver", DELUSION_CANYON_CARVER);
		}

		private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String nameIn, ConfiguredFeature<FC, ?> featureIn)
		{
			return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, nameIn, featureIn);
		}

		private static <C extends ICarverConfig> ConfiguredCarver<C> registerCarver(String nameIn, ConfiguredCarver<C> featureIn)
		{
			return Registry.register(WorldGenRegistries.CONFIGURED_CARVER, nameIn, featureIn);
		}
	}
}
