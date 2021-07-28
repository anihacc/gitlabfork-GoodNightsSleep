package com.legacy.goodnightsleep.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBiomes;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;
import com.legacy.goodnightsleep.registry.GNSFeatures;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.BiomeReport;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class GNSBiomeProv extends BiomeReport
{
	private final DataGenerator generator = null; //ObfuscationReflectionHelper.getPrivateValue(BiomeReport.class, this, "generator");
	private static final Gson gsonBuilder = (new GsonBuilder()).setPrettyPrinting().create();
	private static final Logger LOGGER = GoodNightSleep.LOGGER;
	protected static Map<ResourceLocation, Biome> BIOMES = new HashMap<>();

	public GNSBiomeProv(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	@Override
	public void run(HashCache cache)
	{
		Makers.init();
		System.out.println("trying");

		LOGGER.info("burger beginning biome gen");
		/*Path path = this.generator.getOutputFolder();
		
		for (Entry<ResourceLocation, Biome> entry : BIOMES.entrySet())
		{
			Path path1 = createPath(path, entry.getKey());
			Biome biome = entry.getValue();
			Function<Supplier<Biome>, DataResult<JsonElement>> function = JsonOps.INSTANCE.withEncoder(Biome.CODEC);
		
			try
			{
				Optional<JsonElement> optional = function.apply(() ->
				{
					return biome;
				}).result();
				if (optional.isPresent())
				{
					DataProvider.save(gsonBuilder, cache, optional.get(), path1);
				}
				else
				{
					LOGGER.error("Couldn't serialize biome {}", (Object) path1);
				}
			}
			catch (IOException ioexception)
			{
				LOGGER.error("Couldn't save biome {}", path1, ioexception);
			}
		}*/

	}

	private static Path createPath(Path pathIn, ResourceLocation locationIn)
	{
		Path path = pathIn.resolve("data/" + locationIn.getNamespace() + "/worldgen/biome/" + locationIn.getPath() + ".json");
		System.out.println(path.toString());
		return path;
	}

	public static class Makers
	{
		public static final Biome SLEEPY_HILLS = createSleepyHillsBiome(0.1F, 0.5F, 0.5F, 0.0F, 4159204, 329011);
		public static final Biome GOOD_DREAM_PLAINS = createGoodDreamPlainsBiome(0.0F, 0.1F, 0.5F, 0.0F, 4159204, 329011);
		public static final Biome DREAMY_FOREST = createDreamyForestBiome(0.0F, 0.2F, 0.5F, 0.0F, 4159204, 329011);
		public static final Biome LOLLIPOP_LANDS = createLollipopLandsBiome(0.0F, 0.2F, 0.5F, 0.0F, 4159204, 329011);

		public static final Biome NIGHTMARE_HILLS = createNightmareBiome(0.1F, 1.0F, 0.8F, 0.0F, 4159204, 329011);
		public static final Biome SHAMEFUL_PLAINS = createShamefulPlainsBiome(0.1F, 0.1F, 0.8F, 0.0F, 4159204, 329011);
		public static final Biome WASTED_FOREST = createWastedForestBiome(0.0F, 0.1F, 0.8F, 0.0F, 4159204, 329011);

		// UNUSED
		public static final Biome HOPEFUL_FIELDS = createHopefulFieldsBiome(0.0F, 0.4F, 0.3F, 0.0F, 4159204, 329011);

		public static void init()
		{
			BIOMES.put(GoodNightSleep.locate("sleepy_hills"), SLEEPY_HILLS); // 329011
			BIOMES.put(GoodNightSleep.locate("good_dream_plains"), GOOD_DREAM_PLAINS);
			BIOMES.put(GoodNightSleep.locate("dreamy_forest"), DREAMY_FOREST);
			BIOMES.put(GoodNightSleep.locate("lollipop_lands"), LOLLIPOP_LANDS);

			// UNUSED
			BIOMES.put(GoodNightSleep.locate("hopeful_fields"), HOPEFUL_FIELDS);

			BIOMES.put(GoodNightSleep.locate("nightmare_hills"), NIGHTMARE_HILLS);
			BIOMES.put(GoodNightSleep.locate("shameful_plains"), SHAMEFUL_PLAINS);
			BIOMES.put(GoodNightSleep.locate("wasted_forest"), WASTED_FOREST);

		}

		/**
		 * The Sleepy Hills biome. The OG biome from the original, renamed to fit its
		 * hilly nature.
		 */
		public static Biome createSleepyHillsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			BiomeDefaultFeatures.farmAnimals(spawns);
			spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(GNSEntityTypes.UNICORN, 5, 1, 4));

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			GNSFeatures.addDreamTrees(builder);
			GNSFeatures.addHugeHopeMushrooms(builder);
			GNSFeatures.addScatteredDreamFeatures(builder);
			GNSFeatures.addDreamOres(builder);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_DREAM_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_FLOWERS_5);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(0xffffff).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(12638463).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		/**
		 * The Good Dream Plains biome.
		 */
		public static Biome createGoodDreamPlainsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			BiomeDefaultFeatures.farmAnimals(spawns);
			spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(GNSEntityTypes.UNICORN, 5, 1, 4));

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PLAINS_DIAMOND_TREE);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PLAINS_HOPE_MUSHROOM);

			GNSFeatures.addScatteredDreamFeatures(builder);
			GNSFeatures.addDreamOres(builder);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_DREAM_GRASS);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_FLOWERS_5);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_LOLLIPOPS_20);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(0xffffff).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(12638463).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		/**
		 * The Dreamy Forest biome.
		 */
		public static Biome createDreamyForestBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			BiomeDefaultFeatures.farmAnimals(spawns);
			// spawns.withSpawner(EntityClassification.CREATURE, new
			// MobSpawnInfo.Spawners(GNSEntityTypes.UNICORN, 90, 1, 4));

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.FOREST_DREAM_TREE);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.FOREST_CANDY_TREE);

			GNSFeatures.addDreamSponges(builder);
			GNSFeatures.addDreamOres(builder);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_DREAM_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_FLOWERS_5);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(0xffffff).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(12638463).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		/**
		 * The Lollipop Lands biome.
		 */
		public static Biome createLollipopLandsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			BiomeDefaultFeatures.farmAnimals(spawns);

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.LANDS_CANDY_TREE);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.LANDS_DREAM_TREE);

			GNSFeatures.addDreamSponges(builder);
			GNSFeatures.addDreamOres(builder);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_DREAM_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_FLOWERS_5);

			GNSFeatures.addCarvers(builder);
			// TODO CCFF99
			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(0xFFFFCC).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(12638463).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		/**
		 * The Hopeful Fields biome. (UNUSED DUE TO UGLY)
		 */
		public static Biome createHopefulFieldsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			BiomeDefaultFeatures.farmAnimals(spawns);
			/*spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));*/
			spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(GNSEntityTypes.UNICORN, 5, 1, 4)); // 90

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			GNSFeatures.addHopeMushroomFields(builder);
			GNSFeatures.addDreamSponges(builder);
			GNSFeatures.addDreamOres(builder);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_DREAM_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_FLOWERS_5);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.DREAM_MUSHROOMS_25);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(0xffffff).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(12638463).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		public static Biome createNightmareBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = getDefaultNightmareSpawns();

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.NIGHTMARE_GRASS_SURFACE_BUILDER);

			GNSFeatures.addNightmareTrees(builder);
			GNSFeatures.addHugeDespairMushrooms(builder);
			GNSFeatures.addScatteredNightmareFeatures(builder);
			GNSFeatures.addNightmareOres(builder);
			BiomeDefaultFeatures.addDefaultMonsterRoom(builder); // monster rooms

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_NIGHTMARE_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PRICKLY_NIGHTMARE_GRASS_1);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NIGHTMARE_FLOWERS_5);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(3344392).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		public static Biome createShamefulPlainsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = getDefaultNightmareSpawns();

			// undead horses
			/*spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 1, 1, 1));*/

			spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_HORSE, 1, 1, 3));
			spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SKELETON_HORSE, 1, 1, 3));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.NIGHTMARE_GRASS_SURFACE_BUILDER);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PLAINS_BLOOD_TREE);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PLAINS_DESPAIR_MUSHROOM);

			GNSFeatures.addScatteredNightmareFeatures(builder);
			GNSFeatures.addNightmareOres(builder);
			BiomeDefaultFeatures.addDefaultMonsterRoom(builder); // monster rooms

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NOISE_BASED_NIGHTMARE_GRASS);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PRICKLY_NIGHTMARE_GRASS_2);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NIGHTMARE_FLOWERS_5);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(3344392).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		public static Biome createWastedForestBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnSettings.Builder spawns = getDefaultNightmareSpawns();

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(GNSBiomes.SurfaceBuilders.NIGHTMARE_GRASS_SURFACE_BUILDER);

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.FOREST_DEAD_TREE);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.FOREST_BLOOD_TREE);

			GNSFeatures.addScatteredNightmareFeatures(builder); // 20
			GNSFeatures.addNightmareOres(builder);
			BiomeDefaultFeatures.addDefaultMonsterRoom(builder); // monster rooms

			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NIGHTMARE_GRASS_5);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.PRICKLY_NIGHTMARE_GRASS_2);
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GNSFeatures.Configured.NIGHTMARE_FLOWERS_5);

			GNSFeatures.addCarvers(builder);

			return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColorIn).waterFogColor(waterFogColorIn).fogColor(3344392).skyColor(calculateSkyColor(tempIn)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(builder.build()).build();
		}

		private static MobSpawnSettings.Builder getDefaultNightmareSpawns()
		{
			MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.TORMENTER, 100, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(GNSEntityTypes.HEROBRINE, 10, 1, 1));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 30, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 90, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 2, 4, 4));
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SILVERFISH, 5, 4, 4));

			return spawns;
		}

		private static int calculateSkyColor(float tempIn)
		{
			float lvt_1_1_ = tempIn / 3.0F;
			lvt_1_1_ = Mth.clamp(lvt_1_1_, -1.0F, 1.0F);
			return Mth.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
		}
	}

}