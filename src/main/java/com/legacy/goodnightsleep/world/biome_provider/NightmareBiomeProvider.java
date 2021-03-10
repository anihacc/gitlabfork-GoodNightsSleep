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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.MaxMinNoiseMixer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NightmareBiomeProvider extends BiomeProvider
{
	private static final NightmareBiomeProvider.Noise defaultNoise = new NightmareBiomeProvider.Noise(-7, ImmutableList.of(1.0D, 1.0D));
	public static final MapCodec<NightmareBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((p_242602_0_) ->
	{
		return p_242602_0_.group(Codec.LONG.fieldOf("seed").forGetter((p_235286_0_) ->
		{
			return p_235286_0_.seed;
		}), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((p_235282_0_) ->
		{
			return p_235282_0_.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(p_235282_0_, Pair::of);
		}).listOf().fieldOf("biomes").forGetter((p_235284_0_) ->
		{
			return p_235284_0_.biomes;
		}), NightmareBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((p_242608_0_) ->
		{
			return p_242608_0_.temperatureParams;
		}), NightmareBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((p_242607_0_) ->
		{
			return p_242607_0_.humidityParams;
		}), NightmareBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((p_242606_0_) ->
		{
			return p_242606_0_.altitudeParams;
		}), NightmareBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((p_242604_0_) ->
		{
			return p_242604_0_.weirdnessParams;
		})).apply(p_242602_0_, NightmareBiomeProvider::new);
	});

	public static final Codec<NightmareBiomeProvider> nightmareProviderCodec = Codec.mapEither(NightmareBiomeProvider.NightmareBuilder.builderCodec, DIRECT_CODEC).xmap((p_235277_0_) ->
	{
		return p_235277_0_.map(NightmareBiomeProvider.NightmareBuilder::biomeSource, Function.identity());
	}, (p_235275_0_) ->
	{
		return p_235275_0_.createNightmareBuilder().map(Either::<NightmareBiomeProvider.NightmareBuilder, NightmareBiomeProvider>left).orElseGet(() ->
		{
			return Either.right(p_235275_0_);
		});
	}).codec();

	private final NightmareBiomeProvider.Noise temperatureParams;
	private final NightmareBiomeProvider.Noise humidityParams;
	private final NightmareBiomeProvider.Noise altitudeParams;
	private final NightmareBiomeProvider.Noise weirdnessParams;
	private final MaxMinNoiseMixer temperatureNoise;
	private final MaxMinNoiseMixer humidityNoise;
	private final MaxMinNoiseMixer altitudeNoise;
	private final MaxMinNoiseMixer weirdnessNoise;
	private final List<Pair<Biome.Attributes, Supplier<Biome>>> biomes;
	private final boolean useY;
	private final long seed;
	private final Optional<Pair<Registry<Biome>, NightmareBiomeProvider.NightmarePreset>> biomePreset;

	public NightmareBiomeProvider(long seedIn, List<Pair<Biome.Attributes, Supplier<Biome>>> biomesIn, Optional<Pair<Registry<Biome>, NightmareBiomeProvider.NightmarePreset>> presetIn)
	{
		this(seedIn, biomesIn, defaultNoise, defaultNoise, defaultNoise, defaultNoise, presetIn);
	}

	public NightmareBiomeProvider(long seedIn, List<Pair<Biome.Attributes, Supplier<Biome>>> biomesIn, NightmareBiomeProvider.Noise tempNoiseIn, NightmareBiomeProvider.Noise humidityNoiseIn, NightmareBiomeProvider.Noise altitudeNoiseIn, NightmareBiomeProvider.Noise weirdnessNoiseIn)
	{
		this(seedIn, biomesIn, tempNoiseIn, humidityNoiseIn, altitudeNoiseIn, weirdnessNoiseIn, Optional.empty());
	}

	public NightmareBiomeProvider(long seedIn, List<Pair<Biome.Attributes, Supplier<Biome>>> biomesIn, NightmareBiomeProvider.Noise tempNoiseIn, NightmareBiomeProvider.Noise humidityNoiseIn, NightmareBiomeProvider.Noise altitudeNoiseIn, NightmareBiomeProvider.Noise weirdnessNoiseIn, Optional<Pair<Registry<Biome>, NightmareBiomeProvider.NightmarePreset>> presetIn)
	{
		super(biomesIn.stream().map(Pair::getSecond));
		this.seed = seedIn;
		this.biomePreset = presetIn;
		this.temperatureParams = tempNoiseIn;
		this.humidityParams = humidityNoiseIn;
		this.altitudeParams = altitudeNoiseIn;
		this.weirdnessParams = weirdnessNoiseIn;
		this.temperatureNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seedIn), tempNoiseIn.firstOctave(), tempNoiseIn.amplitudes());
		this.humidityNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seedIn + 1L), humidityNoiseIn.firstOctave(), humidityNoiseIn.amplitudes());
		this.altitudeNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seedIn + 2L), altitudeNoiseIn.firstOctave(), altitudeNoiseIn.amplitudes());
		this.weirdnessNoise = MaxMinNoiseMixer.create(new SharedSeedRandom(seedIn + 3L), weirdnessNoiseIn.firstOctave(), weirdnessNoiseIn.amplitudes());
		this.biomes = biomesIn;
		this.useY = false;
	}

	@Override
	protected Codec<? extends BiomeProvider> codec()
	{
		return nightmareProviderCodec;
	}

	@OnlyIn(Dist.CLIENT)
	public BiomeProvider withSeed(long seed)
	{
		return new NightmareBiomeProvider(seed, this.biomes, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.biomePreset);
	}

	private Optional<NightmareBiomeProvider.NightmareBuilder> createNightmareBuilder()
	{
		return this.biomePreset.map((p_242601_1_) ->
		{
			return new NightmareBiomeProvider.NightmareBuilder(p_242601_1_.getSecond(), p_242601_1_.getFirst(), this.seed);
		});
	}

	public Biome getNoiseBiome(int x, int y, int z)
	{
		int i = this.useY ? y : 0;
		Biome.Attributes biome$attributes = new Biome.Attributes((float) this.temperatureNoise.getValue((double) x, (double) i, (double) z), (float) this.humidityNoise.getValue((double) x, (double) i, (double) z), (float) this.altitudeNoise.getValue((double) x, (double) i, (double) z), (float) this.weirdnessNoise.getValue((double) x, (double) i, (double) z), 0.0F);
		return this.biomes.stream().min(Comparator.comparing((attributeBiomePair) ->
		{
			return attributeBiomePair.getFirst().fitness(biome$attributes);
		})).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
	}

	public boolean stable(long p_235280_1_)
	{
		return this.seed == p_235280_1_ && this.biomePreset.isPresent() && Objects.equals(this.biomePreset.get().getSecond(), NightmareBiomeProvider.NightmarePreset.nightmarePreset);
	}

	static final class DreamBuilder
	{
		public static final MapCodec<NightmareBiomeProvider.DreamBuilder> CODEC = RecordCodecBuilder.mapCodec((p_242630_0_) ->
		{
			return p_242630_0_.group(ResourceLocation.CODEC.flatXmap((location) ->
			{
				return Optional.ofNullable(NightmareBiomeProvider.NightmarePreset.biomeMap.get(location)).map(DataResult::success).orElseGet(() ->
				{
					return DataResult.error("Unknown preset: " + location);
				});
			}, (p_242629_0_) ->
			{
				return DataResult.success(p_242629_0_.getName());
			}).fieldOf("preset").stable().forGetter(NightmareBiomeProvider.DreamBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(NightmareBiomeProvider.DreamBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(NightmareBiomeProvider.DreamBuilder::seed)).apply(p_242630_0_, p_242630_0_.stable(NightmareBiomeProvider.DreamBuilder::new));
		});
		private final NightmareBiomeProvider.NightmarePreset preset;
		private final Registry<Biome> biomes;
		private final long seed;

		private DreamBuilder(NightmareBiomeProvider.NightmarePreset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_)
		{
			this.preset = p_i241956_1_;
			this.biomes = p_i241956_2_;
			this.seed = p_i241956_3_;
		}

		public NightmareBiomeProvider.NightmarePreset preset()
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

		public NightmareBiomeProvider biomeSource()
		{
			return this.preset.biomeSource(this.biomes, this.seed);
		}
	}

	static final class NightmareBuilder
	{
		public static final MapCodec<NightmareBiomeProvider.NightmareBuilder> builderCodec = RecordCodecBuilder.mapCodec((p_242630_0_) ->
		{
			return p_242630_0_.group(ResourceLocation.CODEC.flatXmap((location) ->
			{
				return Optional.ofNullable(NightmareBiomeProvider.NightmarePreset.biomeMap.get(location)).map(DataResult::success).orElseGet(() ->
				{
					return DataResult.error("Unknown preset: " + location);
				});
			}, (p_242629_0_) ->
			{
				return DataResult.success(p_242629_0_.getName());
			}).fieldOf("preset").stable().forGetter(NightmareBiomeProvider.NightmareBuilder::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(NightmareBiomeProvider.NightmareBuilder::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(NightmareBiomeProvider.NightmareBuilder::seed)).apply(p_242630_0_, p_242630_0_.stable(NightmareBiomeProvider.NightmareBuilder::new));
		});
		private final NightmareBiomeProvider.NightmarePreset preset;
		private final Registry<Biome> biomes;
		private final long seed;

		private NightmareBuilder(NightmareBiomeProvider.NightmarePreset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_)
		{
			this.preset = p_i241956_1_;
			this.biomes = p_i241956_2_;
			this.seed = p_i241956_3_;
		}

		public NightmareBiomeProvider.NightmarePreset preset()
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

		public NightmareBiomeProvider biomeSource()
		{
			return this.preset.biomeSource(this.biomes, this.seed);
		}
	}

	static class Noise
	{
		private final int firstOctave;
		private final DoubleList amplitudes;
		public static final Codec<NightmareBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((p_242613_0_) ->
		{
			return p_242613_0_.group(Codec.INT.fieldOf("firstOctave").forGetter(NightmareBiomeProvider.Noise::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(NightmareBiomeProvider.Noise::amplitudes)).apply(p_242613_0_, NightmareBiomeProvider.Noise::new);
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

	public static class NightmarePreset
	{
		private static final Map<ResourceLocation, NightmareBiomeProvider.NightmarePreset> biomeMap = Maps.newHashMap();
		public static final NightmareBiomeProvider.NightmarePreset nightmarePreset = new NightmareBiomeProvider.NightmarePreset(GoodNightSleep.locate("nightmare"), (preset, biomeList, seedIn) ->
		{
			return new NightmareBiomeProvider(seedIn, ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.NIGHTMARE_HILLS);
			}), Pair.of(new Biome.Attributes(0.1F, 0.0F, 0.0F, 0.0F, 0.1F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.WASTED_FOREST);
			}), Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.1F, 0.0F, 0.0F), () ->
			{
				return biomeList.getOrThrow(GNSBiomes.Keys.SHAMEFUL_PLAINS);
			})), Optional.of(Pair.of(biomeList, preset)));
		});
		private final ResourceLocation name;
		private final Function3<NightmareBiomeProvider.NightmarePreset, Registry<Biome>, Long, NightmareBiomeProvider> biomeSource;

		public NightmarePreset(ResourceLocation p_i241955_1_, Function3<NightmareBiomeProvider.NightmarePreset, Registry<Biome>, Long, NightmareBiomeProvider> p_i241955_2_)
		{
			this.name = p_i241955_1_;
			this.biomeSource = p_i241955_2_;
			biomeMap.put(p_i241955_1_, this);
		}

		public NightmareBiomeProvider biomeSource(Registry<Biome> p_242619_1_, long p_242619_2_)
		{
			return this.biomeSource.apply(this, p_242619_1_, p_242619_2_);
		}

		public ResourceLocation getName()
		{
			return name;
		}
	}
}