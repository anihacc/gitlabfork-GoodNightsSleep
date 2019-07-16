package com.legacy.goodnightsleep.world.dream;

import java.util.function.Predicate;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.GNSCanyonWorldCarver;
import com.legacy.goodnightsleep.world.GNSCaveWorldCarver;
import com.legacy.goodnightsleep.world.dream.features.DreamSpongeFeature;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSBigTreeFeature;
import com.legacy.goodnightsleep.world.dream.features.GNSFlowersFeature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ObjectHolder;

public class BiomeGoodDreamPlains extends Biome
{
	@ObjectHolder("goodnightsleep:good_dream_plains")
	public static final BiomeGoodDreamPlains INSTANCE = null;

	public static final Predicate<IBlockState> IS_ROCK = (p_210462_0_) -> {
		if (p_210462_0_ == null)
		{
			return false;
		}
		else
		{
			Block block = p_210462_0_.getBlock();
			return block == BlocksGNS.delusion_stone;
		}
	};
	   
	public BiomeGoodDreamPlains()
	{
		super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, new SurfaceBuilderConfig(BlocksGNS.dream_grass_block.getDefaultState(), BlocksGNS.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(BlocksGNS.tall_dream_grass.getDefaultState()), TWICE_SURFACE_WITH_NOISE, new NoiseDependant(-0.8D, 5, 10)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new DreamTreeFeature(false), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.6F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new DreamTreeFeature(false, 5, BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new GNSBigTreeFeature(false, BlocksGNS.dream_log.getDefaultState(), BlocksGNS.diamond_leaves.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.RAW_GENERATION, createCompositeFeature(new DreamSpongeFeature(), IFeatureConfig.NO_FEATURE_CONFIG, SURFACE_PLUS_32, new FrequencyConfig(20)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFlowerFeature(new GNSFlowersFeature(), SURFACE_PLUS_32, new FrequencyConfig(5)));

		this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(new GNSCaveWorldCarver(), new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, createWorldCarverWrapper(new GNSCanyonWorldCarver(), new ProbabilityConfig(0.02F)));

		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.dream_dirt.getDefaultState(), 33), COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.coal_ore.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.candy_ore.getDefaultState(), 14), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.rainbow_ore.getDefaultState(), 9), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.positite_ore.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, BlocksGNS.lapis_ore.getDefaultState(), 7), DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(BiomeGoodDreamPlains.IS_ROCK, Blocks.GLOWSTONE.getDefaultState(), 8), COUNT_RANGE, new CountRangeConfig(1, 0, 0, 7)));

		this.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 3, 5));
	}
}