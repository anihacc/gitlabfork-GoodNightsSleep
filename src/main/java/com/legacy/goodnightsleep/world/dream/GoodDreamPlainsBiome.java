package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.BigHopeMushroomFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamOreFeatureConfig;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSFlowersFeature;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ObjectHolder;

public class GoodDreamPlainsBiome extends Biome
{
	@ObjectHolder("goodnightsleep:good_dream_plains")
	public static final GoodDreamPlainsBiome INSTANCE = null;
	   
	public GoodDreamPlainsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlocksGNS.dream_grass_block.getDefaultState(), BlocksGNS.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState())).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(BlocksGNS.tall_dream_grass.getDefaultState()), Placement.NOISE_HEIGHTMAP_DOUBLE, new NoiseDependant(-0.8D, 5, 10)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(new DreamTreeFeature(NoFeatureConfig::deserialize, false), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.6F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(new DreamTreeFeature(NoFeatureConfig::deserialize, false, 5, BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(new GNSBigTreeFeature(NoFeatureConfig::deserialize, false, BlocksGNS.dream_log.getDefaultState(), BlocksGNS.diamond_leaves.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createDecoratedFeature(new DreamSpongeFeature(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(20)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(new GNSFlowersFeature(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(new BigHopeMushroomFeature(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));

		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCaveWorldCarver(ProbabilityConfig::deserialize, 256), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new GNSCanyonWorldCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.02F)));
		
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.dream_dirt.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.coal_ore.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.candy_ore.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.rainbow_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.positite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, BlocksGNS.lapis_ore.getDefaultState(), 7), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createDecoratedFeature(new DreamOreFeature(DreamOreFeatureConfig::deserialize), new DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType.DELUSION, Blocks.GLOWSTONE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 7)));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.SPAWNER_ENTITY, 80, 1, 1));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(GNSEntityTypes.UNICORN, 80, 1, 4));

		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));
	}
}