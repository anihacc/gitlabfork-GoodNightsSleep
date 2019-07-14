package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ObjectHolder;

public class BiomeGoodDreamPlains extends Biome
{
	@ObjectHolder("goodnightsleep:good_dream_plains")
	public static final BiomeGoodDreamPlains INSTANCE = null;

	public BiomeGoodDreamPlains()
	{
		super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, new SurfaceBuilderConfig(BlocksGNS.dream_grass.getDefaultState(), BlocksGNS.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(0.1F).scale(0.5F).temperature(0.8F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String) null));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(BlocksGNS.tall_dream_grass.getDefaultState()), TWICE_SURFACE_WITH_NOISE, new NoiseDependant(-0.8D, 5, 10)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new DreamTreeFeature(false), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createCompositeFeature(new DreamTreeFeature(false, 4, BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, AT_SURFACE_WITH_EXTRA, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));

		this.addSpawn(EnumCreatureType.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 3, 5));
	}
}