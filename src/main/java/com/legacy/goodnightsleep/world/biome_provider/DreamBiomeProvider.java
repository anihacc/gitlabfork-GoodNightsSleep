package com.legacy.goodnightsleep.world.biome_provider;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBiomes;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.data.worldgen.biome.Biomes;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DreamBiomeProvider extends BiomeSource
{
	private static final DreamBiomeProvider.Noise defaultNoise = new DreamBiomeProvider.Noise(-7, ImmutableList.of(1.0D, 1.0D));
	public static final MapCodec<DreamBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((p_242602_0_) ->
	{
		return p_242602_0_.group(Codec.LONG.fieldOf("seed").forGetter((p_235286_0_) ->
		{
			return p_235286_0_.seed;
		}), RecordCodecBuilder.<Pair<Biome.ClimateParameters, Supplier<Biome>>>create((p_235282_0_) ->
		{
			return p_235282_0_.group(Biome.ClimateParameters.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(p_235282_0_, Pair::of);
		}).listOf().fieldOf("biomes").forGetter((p_235284_0_) ->
		{
			return p_235284_0_.biomes;
		}), DreamBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((p_242608_0_) ->
		{
			return p_242608_0_.temperatureParams;
		}), DreamBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((p_242607_0_) ->
		{
			return p_242607_0_.humidityParams;
		}), DreamBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((p_242606_0_) ->
		{
			return p_242606_0_.altitudeParams;
		}), DreamBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((p_242604_0_) ->
		{
			return p_242604_0_.weirdnessParams;
		})).apply(p_242602_0_, DreamBiomeProvider::new);
	});
	public static final Codec<DreamBiomeProvider> dreamProviderCodec = Codec.mapEither(DreamBiomeProvider.DreamBuilder.CODEC, DIRECT_CODEC).xmap((p_235277_0_) ->
	{
		return p_235277_0_.map(DreamBiomeProvider.DreamBuilder::biomeSource, Function.identity());
	}, (p_235275_0_) ->
	{
		return p_235275_0_.createDreamBuilder().map(Either::<DreamBiomeProvider.DreamBuilder, DreamBiomeProvider>left).orElseGet(() ->
		{
			return Either.right(p_235275_0_);
		});
	}).codec();

	private final DreamBiomeProvider.Noise temperatureParams;
	private final DreamBiomeProvider.Noise humidityParams;
	private final DreamBiomeProvider.Noise altitudeParams;
	private final DreamBiomeProvider.Noise weirdnessParams;
	private final NormalNoise temperatureNoise;
	private final NormalNoise humidityNoise;
	private final NormalNoise altitudeNoise;
	private final NormalNoise weirdnessNoise;
	private final List<Pair<Biome.ClimateParameters, Supplier<Biome>>> biomes;
	private final boolean useY;
	private final long seed;
	private final Optional<Pair<Registry<Biome>, DreamBiomeProvider.DreamPreset>> biomePreset;

	public DreamBiomeProvider(long seedIn, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> biomesIn, Optional<Pair<Registry<Biome>, DreamBiomeProvider.DreamPreset>> presetIn)
	{
		this(seedIn, biomesIn, defaultNoise, defaultNoise, defaultNoise, defaultNoise, presetIn);
	}

	public DreamBiomeProvider(long seedIn, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> biomesIn, DreamBiomeProvider.Noise tempNoiseIn, DreamBiomeProvider.Noise humidityNoiseIn, DreamBiomeProvider.Noise altitudeNoiseIn, DreamBiomeProvider.Noise weirdnessNoiseIn)
	{
		this(seedIn, biomesIn, tempNoiseIn, humidityNoiseIn, altitudeNoiseIn, weirdnessNoiseIn, Optional.empty());
	}

	public DreamBiomeProvider(long seedIn, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> biomesIn, DreamBiomeProvider.Noise tempNoiseIn, DreamBiomeProvider.Noise humidityNoiseIn, DreamBiomeProvider.Noise altitudeNoiseIn, DreamBiomeProvider.Noise weirdnessNoiseIn, Optional<Pair<Registry<Biome>, DreamBiomeProvider.DreamPreset>> presetIn)
	{
		super(biomesIn.stream().map(Pair::getSecond));
		this.seed = seedIn;
		this.biomePreset = presetIn;
		this.temperatureParams = tempNoiseIn;
		this.humidityParams = humidityNoiseIn;
		this.altitudeParams = altitudeNoiseIn;
		this.weirdnessParams = weirdnessNoiseIn;
		this.temperatureNoise = NormalNoise.create(new WorldgenRandom(seedIn), tempNoiseIn.firstOctave(), tempNoiseIn.amplitudes());
		this.humidityNoise = NormalNoise.create(new WorldgenRandom(seedIn + 1L), humidityNoiseIn.firstOctave(), humidityNoiseIn.amplitudes());
		this.altitudeNoise = NormalNoise.create(new WorldgenRandom(seedIn + 2L), altitudeNoiseIn.firstOctave(), altitudeNoiseIn.amplitudes());
		this.weirdnessNoise = NormalNoise.create(new WorldgenRandom(seedIn + 3L), weirdnessNoiseIn.firstOctave(), weirdnessNoiseIn.amplitudes());
		this.biomes = biomesIn;
		this.useY = false;
	}

	protected Codec<? extends BiomeSource> codec()
	{
		return dreamProviderCodec;
	}

	@OnlyIn(Dist.CLIENT)
	public BiomeSource withSeed(long seed)
	{
		return new DreamBiomeProvider(seed, this.biomes, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.biomePreset);
	}

	private Optional<DreamBiomeProvider.DreamBuilder> createDreamBuilder()
	{
		return this.biomePreset.map((p_242601_1_) ->
		{
			return new DreamBiomeProvider.DreamBuilder(p_242601_1_.getSecond(), p_242601_1_.getFirst(), this.seed);
		});
	}

	public Biome getNoiseBiome(int x, int y, int z)
	{
		int i = this.useY ? y : 0;
		Biome.ClimateParameters biome$attributes = new Biome.ClimateParameters((float) this.temperatureNoise.getValue((double) x, (double) i, (double) z), (float) this.humidityNoise.getValue((double) x, (double) i, (double) z), (float) this.altitudeNoise.getValue((double) x, (double) i, (double) z), (float) this.weirdnessNoise.getValue((double) x, (double) i, (double) z), 0.0F);
		return this.biomes.stream().min(Comparator.comparing((attributeBiomePair) ->
		{
			return attributeBiomePair.getFirst().fitness(biome$attributes);
		})).map(Pair::getSecond).map(Supplier::get).orElse(Biomes.THE_VOID);
	}

	public boolean stable(long p_235280_1_)
	{
		return this.seed == p_235280_1_ && this.biomePreset.isPresent() && Objects.equals(this.biomePreset.get().getSecond(), DreamBiomeProvider.DreamPreset.dreamPreset);
	}

	static final class DreamBuilder
	{
		public static final MapCodec<DreamBiomeProvider.DreamBuilder> CODEC = RecordCodecBuilder.mapCodec((p_242630_0_) ->
		{
			return p_242630_0_.group(ResourceLocation.CODEC.flatXmap((location) ->
			{
				return Optional.ofNullable(DreamBiomeProvider.DreamPreset.biomeMap.get(location)).map(DataResult::success).orElseGet(() ->
				{
					return DataResult.error("Unknown preset: " + location);
				});
			}, (p_242629_0_) ->
			{
				return DataResult.success(p_242629_0_.getName());
			}).fieldOf("preset").stable().forGetter(DreamBiomeProvider.DreamBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(DreamBiomeProvider.DreamBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(DreamBiomeProvider.DreamBuilder::seed)).apply(p_242630_0_, p_242630_0_.stable(DreamBiomeProvider.DreamBuilder::new));
		});
		private final DreamBiomeProvider.DreamPreset preset;
		private final Registry<Biome> biomes;
		private final long seed;

		private DreamBuilder(DreamBiomeProvider.DreamPreset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_)
		{
			this.preset = p_i241956_1_;
			this.biomes = p_i241956_2_;
			this.seed = p_i241956_3_;
		}

		public DreamBiomeProvider.DreamPreset preset()
		{
			return this.preset;
		}

		public Registry<Biome> biomes()
		{
			return this.biomes;
		}

		public long seed()
		{
			return this.seed;
		}

		public DreamBiomeProvider biomeSource()
		{
			return this.preset.biomeSource(this.biomes, this.seed);
		}
	}

	static class Noise
	{
		private final int firstOctave;
		private final DoubleList amplitudes;
		public static final Codec<DreamBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((p_242613_0_) ->
		{
			return p_242613_0_.group(Codec.INT.fieldOf("firstOctave").forGetter(DreamBiomeProvider.Noise::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(DreamBiomeProvider.Noise::amplitudes)).apply(p_242613_0_, DreamBiomeProvider.Noise::new);
		});

		public Noise(int p_i241954_1_, List<Double> p_i241954_2_)
		{
			this.firstOctave = p_i241954_1_;
			this.amplitudes = new DoubleArrayList(p_i241954_2_);
		}

		public int firstOctave()
		{
			return this.firstOctave;
		}

		public DoubleList amplitudes()
		{
			return this.amplitudes;
		}
	}

	public static class DreamPreset
	{
		private static final Map<ResourceLocation, DreamBiomeProvider.DreamPreset> biomeMap = Maps.newHashMap();
		public static final DreamBiomeProvider.DreamPreset dreamPreset = new DreamBiomeProvider.DreamPreset(GoodNightSleep.locate("good_dream"), (preset, biomeList, seedIn) ->
		{
			return new DreamBiomeProvider(seedIn, ImmutableList.of(
				Pair.of(new Biome.ClimateParameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.SLEEPY_HILLS);
			}), Pair.of(new Biome.ClimateParameters(0.0F, 0.0F, 0.0F, 0.1F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.DREAMY_FOREST);
			}), Pair.of(new Biome.ClimateParameters(0.0F, 0.1F, 0.0F, 0.0F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.GOOD_DREAM_PLAINS);
			}), Pair.of(new Biome.ClimateParameters(0.0F, 0.0F, 0.1F, 0.0F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.LOLLIPOP_LANDS);
			})), Optional.of(Pair.of(biomeList, preset)));
		});
		private final ResourceLocation name;
		private final Function3<DreamBiomeProvider.DreamPreset, Registry<Biome>, Long, DreamBiomeProvider> biomeSource;

		public DreamPreset(ResourceLocation p_i241955_1_, Function3<DreamBiomeProvider.DreamPreset, Registry<Biome>, Long, DreamBiomeProvider> p_i241955_2_)
		{
			this.name = p_i241955_1_;
			this.biomeSource = p_i241955_2_;
			biomeMap.put(p_i241955_1_, this);
		}

		public DreamBiomeProvider biomeSource(Registry<Biome> p_242619_1_, long p_242619_2_)
		{
			return this.biomeSource.apply(this, p_242619_1_, p_242619_2_);
		}

		public ResourceLocation getName()
		{
			return name;
		}
	}
}