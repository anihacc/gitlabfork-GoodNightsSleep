package com.legacy.goodnightsleep.world.general_features;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.BaseGNSBiome;
import com.legacy.goodnightsleep.world.dream.features.BigHopeMushroomFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeatureConfig;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.nightmare.features.BigDespairMushroomFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
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
	public static final AbstractTreeFeature<TreeFeatureConfig> DREAM_TREE = new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false);
	public static final AbstractTreeFeature<TreeFeatureConfig> CANDY_TREE = new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false, 5, GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState());
	public static final AbstractTreeFeature<TreeFeatureConfig> DIAMOND_TREE = new GNSBigTreeFeature(TreeFeatureConfig::func_227338_a_, false, GNSBlocks.dream_log.getDefaultState(), GNSBlocks.diamond_leaves.getDefaultState());
	public static final AbstractTreeFeature<TreeFeatureConfig> DEAD_TREE = new GNSBigTreeFeature(TreeFeatureConfig::func_227338_a_, false, GNSBlocks.dead_log.getDefaultState(), Blocks.AIR.getDefaultState());
	public static final AbstractTreeFeature<TreeFeatureConfig> BLOOD_TREE = new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false, 5, GNSBlocks.blood_log.getDefaultState(), Blocks.AIR.getDefaultState());

	public static final Feature<NoFeatureConfig> DREAM_SPONGE = new DreamSpongeFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BIG_HOPE_MUSHROOM = new BigHopeMushroomFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_PRESENTS = new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, GNSBlocks.present.getDefaultState());
	public static final Feature<DreamOreFeatureConfig> DREAM_ORES = new DreamOreFeature(DreamOreFeatureConfig::deserialize);

	public static final Feature<NoFeatureConfig> NETHER_SPLASH = new NetherSplashFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BIG_DESPAIR_MUSHROOM = new BigDespairMushroomFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_PUMPKINS = new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, Blocks.PUMPKIN.getDefaultState());

	public static void init(Register<Feature<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "dream_tree", DREAM_TREE);
		GNSRegistry.register(event.getRegistry(), "candy_tree", CANDY_TREE);
		GNSRegistry.register(event.getRegistry(), "diamond_tree", DIAMOND_TREE);
		GNSRegistry.register(event.getRegistry(), "dead_tree", DEAD_TREE);
		GNSRegistry.register(event.getRegistry(), "blood_tree", BLOOD_TREE);

		GNSRegistry.register(event.getRegistry(), "dream_sponge", DREAM_SPONGE);
		GNSRegistry.register(event.getRegistry(), "big_hope_mushroom", BIG_HOPE_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "scattered_presents", SCATTERED_PRESENTS);
		GNSRegistry.register(event.getRegistry(), "dream_ores", DREAM_ORES);

		GNSRegistry.register(event.getRegistry(), "nether_splash", NETHER_SPLASH);
		GNSRegistry.register(event.getRegistry(), "big_despair_mushroom", BIG_DESPAIR_MUSHROOM);
		GNSRegistry.register(event.getRegistry(), "scattered_pumpkins", SCATTERED_PUMPKINS);
	}

	public static void addDreamOres(BaseGNSBiome biomeIn, FillerBlockType delusionConfig)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.dream_dirt.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.coal_ore.getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.candy_ore.getDefaultState(), 15)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.rainbow_ore.getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 64))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.positite_ore.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, GNSBlocks.lapis_ore.getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(delusionConfig, Blocks.GLOWSTONE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 7))));
	}

	public static void addDreamTrees(BaseGNSBiome biomeIn)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DREAM_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.CANDY_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DIAMOND_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.2F, 1))));
	}

	public static void addScatteredDreamFeatures(BaseGNSBiome biomeIn)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.RAW_GENERATION, GNSFeatures.DREAM_SPONGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(20))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.BIG_HOPE_MUSHROOM.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.3F, 1))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.SCATTERED_PRESENTS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(32))));
	}

	public static void addNightmareOres(BaseGNSBiome biomeIn)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 256))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.necrum_ore.getDefaultState(), 15)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 64))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.zitrite_ore.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 0, 0, 32))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.negatite_ore.getDefaultState(), 3)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
	}

	public static void addNightmareTrees(BaseGNSBiome biomeIn)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DEAD_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.5F, 1))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.BLOOD_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
	}

	public static void addScatteredNightmareFeatures(BaseGNSBiome biomeIn)
	{
		biomeIn.addDreamFeature(GenerationStage.Decoration.RAW_GENERATION, GNSFeatures.NETHER_SPLASH.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(20))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.BIG_DESPAIR_MUSHROOM.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		biomeIn.addDreamFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.SCATTERED_PUMPKINS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(10))));
	}
}
