package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.general_features.DreamScatteredPlantFeature;
import com.legacy.goodnightsleep.world.nightmare.features.BigDespairMushroomFeature;
import com.legacy.goodnightsleep.world.nightmare.features.NetherSplashFeature;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
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
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class NightmareHillsBiome extends Biome
{
	public static final BlockClusterFeatureConfig TALL_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.tall_nightmare_grass.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
	public static final BlockClusterFeatureConfig PRICKLY_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.prickly_nightmare_grass.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(32).func_227322_d_();
	public static final BlockClusterFeatureConfig DEAD_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(GNSBlocks.dead_flower.getDefaultState(), 1), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();

	public NightmareHillsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(GNSBlocks.nightmare_grass_block.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState())).precipitation(Biome.RainType.NONE).category(Biome.Category.EXTREME_HILLS).depth(0.1F).scale(1.0F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(TALL_GRASS).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(5))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(PRICKLY_GRASS).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(1))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.withConfiguration(DEAD_FLOWER_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(5))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new GNSBigTreeFeature(TreeFeatureConfig::func_227338_a_, false, GNSBlocks.dead_log.getDefaultState(), Blocks.AIR.getDefaultState()).withConfiguration(DefaultBiomeFeatures.field_226810_e_).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.5F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, false, 5, GNSBlocks.blood_log.getDefaultState(), Blocks.AIR.getDefaultState()).withConfiguration(DefaultBiomeFeatures.field_226810_e_).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, new NetherSplashFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(20))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new BigDespairMushroomFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(0, 0.4F, 1))));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new DreamScatteredPlantFeature(NoFeatureConfig::deserialize, Blocks.PUMPKIN.getDefaultState()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP_DOUBLE.func_227446_a_(new ChanceConfig(10))));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCaveWorldCarver(ProbabilityConfig::deserialize, 256), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCanyonWorldCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.02F)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.necrum_ore.getDefaultState(), 15)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 64))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.zitrite_ore.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GNSBlocks.negatite_ore.getDefaultState(), 7)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(1, 0, 0, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 16, 16))));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.SPAWNER_ENTITY, 1, 1, 1));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.TORMENTER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.HEROBRINE, 10, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.GHAST, 30, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_PIGMAN, 90, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SILVERFISH, 5, 4, 4));
	}
}