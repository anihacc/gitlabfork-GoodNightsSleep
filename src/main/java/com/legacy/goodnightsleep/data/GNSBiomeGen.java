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
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.registry.GNSFeatures;
import com.legacy.goodnightsleep.world.GNSBiomes;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

import net.minecraft.data.BiomeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class GNSBiomeGen extends BiomeProvider
{
	private final DataGenerator generator = ObfuscationReflectionHelper.getPrivateValue(BiomeProvider.class, this, "field_244197_d");
	private static final Gson gsonBuilder = (new GsonBuilder()).setPrettyPrinting().create();
	private static final Logger LOGGER = GoodNightSleep.LOGGER;
	protected static Map<ResourceLocation, Biome> BIOMES = new HashMap<>();

	public GNSBiomeGen(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	@Override
	public void act(DirectoryCache cache)
	{
		Makers.init();
		System.out.println("trying");

		LOGGER.info("burger beginning biome gen");
		Path path = this.generator.getOutputFolder();

		for (Entry<ResourceLocation, Biome> entry : BIOMES.entrySet())
		{
			Path path1 = createPath(path, entry.getKey());
			Biome biome = entry.getValue();
			Function<Supplier<Biome>, DataResult<JsonElement>> function = JsonOps.INSTANCE.withEncoder(Biome.field_235051_b_);

			try
			{
				Optional<JsonElement> optional = function.apply(() ->
				{
					return biome;
				}).result();
				if (optional.isPresent())
				{
					IDataProvider.save(gsonBuilder, cache, optional.get(), path1);
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
		}

	}

	private static Path createPath(Path pathIn, ResourceLocation locationIn)
	{
		Path path = pathIn.resolve("data/" + locationIn.getNamespace() + "/worldgen/biome/" + locationIn.getPath() + ".json");
		System.out.println(path.toString());
		return path;
	}

	public static class Makers
	{
		public static void init()
		{
			BIOMES.put(GoodNightSleep.locate("good_dream_plains"), createDreamPlainsBiome(0.1F, 0.5F, 0.5F, 0.0F, 4159204, 329011)); // 329011
			BIOMES.put(GoodNightSleep.locate("dreamy_forest"), createDreamForestBiome(0.0F, 0.2F, 0.5F, 0.0F, 4159204, 329011)); // 329011
			BIOMES.put(GoodNightSleep.locate("hopeful_fields"), createHopefulFieldsBiome(0.0F, 0.4F, 0.3F, 0.0F, 4159204, 329011)); // 329011

			BIOMES.put(GoodNightSleep.locate("nightmare_hills"), createNightmareBiome(0.1F, 1.0F, 0.8F, 0.0F, 4159204, 329011));
		}

		public static Biome createDreamPlainsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.UNICORN, 90, 1, 4));

			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			/*DefaultBiomeFeatures.func_243735_b(spawns, 95, 5, 100);*/ // default hostiles

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).func_242517_a(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			// DefaultBiomeFeatures.func_243738_d(builder); // default carvers
			/*DefaultBiomeFeatures.func_243742_f(builder); // lakes
			DefaultBiomeFeatures.func_243746_h(builder); // monster rooms/dungeons
			DefaultBiomeFeatures.func_243748_i(builder); // dirt/diorite/granite/andesite
			DefaultBiomeFeatures.func_243750_j(builder); // default ores
			DefaultBiomeFeatures.func_243754_n(builder); // disks
			DefaultBiomeFeatures.func_243707_U(builder); // default flowers
			DefaultBiomeFeatures.func_243712_Z(builder); // mushrooms
			DefaultBiomeFeatures.func_243727_ak(builder); // springs
			DefaultBiomeFeatures.func_243730_an(builder); // surface snow
			*/

			GNSFeatures.addDreamTrees(builder);
			GNSFeatures.addScatteredDreamFeatures(builder);
			GNSFeatures.addDreamOres(builder);
			GNSFeatures.addNoiseBasedGrass(builder, GNSFeatures.DREAM_GRASS_CONFIG);
			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(GNSFeatures.DREAM_FLOWER_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(5)); // COUNT_HEIGHTMAP_32

			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CAVE_CARVER.func_242761_a(new ProbabilityConfig(0.14285715F)));
			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CANYON_CARVER.func_242761_a(new ProbabilityConfig(0.02F)));

			return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(waterColorIn).setWaterFogColor(waterFogColorIn).setFogColor(12638463).func_242539_d(ambienceChance(tempIn)).setMoodSound(MoodSoundAmbience.field_235027_b_).build()).func_242458_a(spawns.func_242577_b()).func_242457_a(builder.func_242508_a()).func_242455_a();
		}

		public static Biome createDreamForestBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
			// spawns.func_242575_a(EntityClassification.CREATURE, new
			// MobSpawnInfo.Spawners(GNSEntityTypes.UNICORN, 90, 1, 4));

			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).func_242517_a(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.DREAM_TREE.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 1.1F, 5))));
			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, GNSFeatures.CANDY_TREE.withPlacement(Features.Placements.field_244001_l).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.8F, 1))));

			GNSFeatures.addDreamSponges(builder);
			GNSFeatures.addDreamOres(builder);
			GNSFeatures.addNoiseBasedGrass(builder, GNSFeatures.DREAM_GRASS_CONFIG);

			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(GNSFeatures.DREAM_FLOWER_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(5)); // COUNT_HEIGHTMAP_32

			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CAVE_CARVER.func_242761_a(new ProbabilityConfig(0.14285715F)));
			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CANYON_CARVER.func_242761_a(new ProbabilityConfig(0.02F)));

			return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(waterColorIn).setWaterFogColor(waterFogColorIn).setFogColor(12638463).func_242539_d(ambienceChance(tempIn)).setMoodSound(MoodSoundAmbience.field_235027_b_).build()).func_242458_a(spawns.func_242577_b()).func_242457_a(builder.func_242508_a()).func_242455_a();
		}

		/**
		 * The Hopeful Fields biome.
		 */
		public static Biome createHopefulFieldsBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 140, 1, 1));
			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.UNICORN, 90, 1, 4));

			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GNSEntityTypes.BABY_CREEPER, 10, 1, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).func_242517_a(GNSBiomes.SurfaceBuilders.DREAM_GRASS_SURFACE_BUILDER);

			GNSFeatures.addHopeMushroomFields(builder);

			GNSFeatures.addDreamSponges(builder);
			GNSFeatures.addDreamOres(builder);
			GNSFeatures.addNoiseBasedGrass(builder, GNSFeatures.DREAM_GRASS_CONFIG);

			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(GNSFeatures.DREAM_FLOWER_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(5)); // COUNT_HEIGHTMAP_32
			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(GNSFeatures.HOPE_MUSHROOM_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(25));

			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CAVE_CARVER.func_242761_a(new ProbabilityConfig(0.14285715F)));
			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CANYON_CARVER.func_242761_a(new ProbabilityConfig(0.02F)));

			return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(waterColorIn).setWaterFogColor(waterFogColorIn).setFogColor(12638463).func_242539_d(ambienceChance(tempIn)).setMoodSound(MoodSoundAmbience.field_235027_b_).build()).func_242458_a(spawns.func_242577_b()).func_242457_a(builder.func_242508_a()).func_242455_a();
		}

		public static Biome createNightmareBiome(float depthIn, float scaleIn, float tempIn, float downfallIn, int waterColorIn, int waterFogColorIn)
		{
			MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

			spawns.func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(GNSEntityTypes.SPAWNER_ENTITY, 1, 1, 1));

			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GNSEntityTypes.TORMENTER, 100, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GNSEntityTypes.HEROBRINE, 10, 1, 1));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.GHAST, 30, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 90, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.MAGMA_CUBE, 2, 4, 4));
			spawns.func_242575_a(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SILVERFISH, 5, 4, 4));

			BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).func_242517_a(GNSBiomes.SurfaceBuilders.NIGHTMARE_GRASS_SURFACE_BUILDER);

			GNSFeatures.addNightmareTrees(builder);
			GNSFeatures.addScatteredNightmareFeatures(builder);
			GNSFeatures.addNightmareOres(builder);
			DefaultBiomeFeatures.func_243746_h(builder); // monster rooms

			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.TALL_GRASS).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(5)); /*.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));*/
			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GNSFeatures.PRICKLY_GRASS).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(1)); // samething
			builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(GNSFeatures.DEAD_FLOWER_CONFIG).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l).func_242731_b(5));

			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CAVE_CARVER.func_242761_a(new ProbabilityConfig(0.14285715F)));
			builder.func_242512_a(GenerationStage.Carving.AIR, GNSFeatures.Carvers.DELUSION_CANYON_CARVER.func_242761_a(new ProbabilityConfig(0.02F)));

			return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.NONE).depth(depthIn).scale(scaleIn).temperature(tempIn).downfall(downfallIn).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(waterColorIn).setWaterFogColor(waterFogColorIn).setFogColor(3344392).func_242539_d(ambienceChance(tempIn)).setMoodSound(MoodSoundAmbience.field_235027_b_).build()).func_242458_a(spawns.func_242577_b()).func_242457_a(builder.func_242508_a()).func_242455_a();
		}

		private static int ambienceChance(float p_244206_0_)
		{
			float lvt_1_1_ = p_244206_0_ / 3.0F;
			lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
			return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
		}
	}

}
