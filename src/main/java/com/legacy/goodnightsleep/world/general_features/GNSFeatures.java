package com.legacy.goodnightsleep.world.general_features;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.dream.features.BigHopeMushroomFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeatureConfig;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSFlowersFeature;
import com.legacy.goodnightsleep.world.nightmare.features.BigDespairMushroomFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NightmareFlowersFeature;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GoodNightSleep.MODID)
public class GNSFeatures
{
	public static final AbstractTreeFeature<NoFeatureConfig> DREAM_TREE = new DreamTreeFeature(NoFeatureConfig::deserialize, false);
	public static final AbstractTreeFeature<NoFeatureConfig> CANDY_TREE = new DreamTreeFeature(NoFeatureConfig::deserialize, false, 5, GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState());
	public static final AbstractTreeFeature<NoFeatureConfig> DIAMOND_TREE = new GNSBigTreeFeature(NoFeatureConfig::deserialize, false, GNSBlocks.dream_log.getDefaultState(), GNSBlocks.diamond_leaves.getDefaultState());
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE = new GNSBigTreeFeature(NoFeatureConfig::deserialize, false, GNSBlocks.dead_log.getDefaultState(), Blocks.AIR.getDefaultState());
	public static final AbstractTreeFeature<NoFeatureConfig> BLOOD_TREE = new DreamTreeFeature(NoFeatureConfig::deserialize, false, 5, GNSBlocks.blood_log.getDefaultState(), Blocks.AIR.getDefaultState());

	public static final Feature<NoFeatureConfig> DREAM_SPONGE = new DreamSpongeFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> DREAM_FLOWERS = new GNSFlowersFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BIG_HOPE_MUSHROOM = new BigHopeMushroomFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_PRESENTS = new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, GNSBlocks.present.getDefaultState());
	public static final Feature<DreamOreFeatureConfig> DREAM_ORES = new DreamOreFeature(DreamOreFeatureConfig::deserialize);

	public static final Feature<NoFeatureConfig> NETHER_SPLASH = new NetherSplashFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> NIGHTMARE_FLOWERS = new NightmareFlowersFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> HUGE_DESPAIR_MUSHROOM = new BigDespairMushroomFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_PUMPKINS = new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, Blocks.PUMPKIN.getDefaultState());

	public static void init(Register<Feature<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "dream_tree", DREAM_TREE);
		GNSRegistry.register(event.getRegistry(), "candy_tree", CANDY_TREE);
		GNSRegistry.register(event.getRegistry(), "diamond_tree", DIAMOND_TREE);
		GNSRegistry.register(event.getRegistry(), "dead_tree", DEAD_TREE);
		GNSRegistry.register(event.getRegistry(), "blood_tree", BLOOD_TREE);

		GNSRegistry.register(event.getRegistry(), "dream_sponge", DREAM_SPONGE);
		GNSRegistry.register(event.getRegistry(), "dream_flowers", DREAM_FLOWERS);
		GNSRegistry.register(event.getRegistry(), "big_hope_mushroom", BIG_HOPE_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "scattered_presents", SCATTERED_PRESENTS);
		GNSRegistry.register(event.getRegistry(), "dream_ores", DREAM_ORES);

		GNSRegistry.register(event.getRegistry(), "nether_splash", NETHER_SPLASH);
		GNSRegistry.register(event.getRegistry(), "nightmare_flowers", NIGHTMARE_FLOWERS);
		GNSRegistry.register(event.getRegistry(), "big_despair_mushroom", HUGE_DESPAIR_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "scattered_pumpkins", SCATTERED_PUMPKINS);
	}

	public static void addDreamOres(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.dream_dirt.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.coal_ore.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.candy_ore.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.rainbow_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.positite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, GNSBlocks.lapis_ore.getDefaultState(), 7), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(GNSFeatures.DREAM_ORES, new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, Blocks.GLOWSTONE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 7)));
	}

	public static void addDreamTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.DREAM_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.6F, 1)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.CANDY_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.DIAMOND_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
	}

	public static void addScatteredDreamFeatures(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.RAW_GENERATION, Biome.createDecoratedFeature(GNSFeatures.DREAM_SPONGE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(20)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.DREAM_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.BIG_HOPE_MUSHROOM, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.SCATTERED_PRESENTS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(32)));
	}

	public static void addNightmareOres(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.necrum_ore.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 7), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.zitrite_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(5, 0, 0, 32)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.negatite_ore.getDefaultState(), 3), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
	}

	public static void addNightmareTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.DEAD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.5F, 1)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.BLOOD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
	}

	public static void addScatteredNightmareFeatures(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.RAW_GENERATION, Biome.createDecoratedFeature(GNSFeatures.NETHER_SPLASH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(20)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.NIGHTMARE_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.HUGE_DESPAIR_MUSHROOM, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(GNSFeatures.SCATTERED_PUMPKINS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)));
	}
}
