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
	public static final MapCodec<NightmareBiomeProvider> field_235262_e_ = RecordCodecBuilder.mapCodec((p_242602_0_) ->
	{
		return p_242602_0_.group(Codec.LONG.fieldOf("seed").forGetter((p_235286_0_) ->
		{
			return p_235286_0_.seed;
		}), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((p_235282_0_) ->
		{
			return p_235282_0_.group(Biome.Attributes.field_235104_a_.fieldOf("parameters").forGetter(Pair::getFirst), Biome.field_235051_b_.fieldOf("biome").forGetter(Pair::getSecond)).apply(p_235282_0_, Pair::of);
		}).listOf().fieldOf("biomes").forGetter((p_235284_0_) ->
		{
			return p_235284_0_.biomes;
		}), NightmareBiomeProvider.Noise.field_242609_a.fieldOf("temperature_noise").forGetter((p_242608_0_) ->
		{
			return p_242608_0_.temperatureNoise;
		}), NightmareBiomeProvider.Noise.field_242609_a.fieldOf("humidity_noise").forGetter((p_242607_0_) ->
		{
			return p_242607_0_.humidityNoise;
		}), NightmareBiomeProvider.Noise.field_242609_a.fieldOf("altitude_noise").forGetter((p_242606_0_) ->
		{
			return p_242606_0_.altitudeNoise;
		}), NightmareBiomeProvider.Noise.field_242609_a.fieldOf("weirdness_noise").forGetter((p_242604_0_) ->
		{
			return p_242604_0_.weirdnessNoise;
		})).apply(p_242602_0_, NightmareBiomeProvider::new);
	});

	public static final Codec<NightmareBiomeProvider> nightmareProviderCodec = Codec.mapEither(NightmareBiomeProvider.NightmareBuilder.builderCodec, field_235262_e_).xmap((p_235277_0_) ->
	{
		return p_235277_0_.map(NightmareBiomeProvider.NightmareBuilder::func_242635_d, Function.identity());
	}, (p_235275_0_) ->
	{
		return p_235275_0_.createNightmareBuilder().map(Either::<NightmareBiomeProvider.NightmareBuilder, NightmareBiomeProvider>left).orElseGet(() ->
		{
			return Either.right(p_235275_0_);
		});
	}).codec();

	private final NightmareBiomeProvider.Noise temperatureNoise;
	private final NightmareBiomeProvider.Noise humidityNoise;
	private final NightmareBiomeProvider.Noise altitudeNoise;
	private final NightmareBiomeProvider.Noise weirdnessNoise;
	private final MaxMinNoiseMixer field_235264_g_;
	private final MaxMinNoiseMixer field_235265_h_;
	private final MaxMinNoiseMixer field_235266_i_;
	private final MaxMinNoiseMixer field_235267_j_;
	private final List<Pair<Biome.Attributes, Supplier<Biome>>> biomes;
	private final boolean field_235269_l_;
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
		this.temperatureNoise = tempNoiseIn;
		this.humidityNoise = humidityNoiseIn;
		this.altitudeNoise = altitudeNoiseIn;
		this.weirdnessNoise = weirdnessNoiseIn;
		this.field_235264_g_ = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seedIn), tempNoiseIn.func_242612_a(), tempNoiseIn.func_242614_b());
		this.field_235265_h_ = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seedIn + 1L), humidityNoiseIn.func_242612_a(), humidityNoiseIn.func_242614_b());
		this.field_235266_i_ = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seedIn + 2L), altitudeNoiseIn.func_242612_a(), altitudeNoiseIn.func_242614_b());
		this.field_235267_j_ = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seedIn + 3L), weirdnessNoiseIn.func_242612_a(), weirdnessNoiseIn.func_242614_b());
		this.biomes = biomesIn;
		this.field_235269_l_ = false;
	}

	@Override
	protected Codec<? extends BiomeProvider> getBiomeProviderCodec()
	{
		return nightmareProviderCodec;
	}

	@OnlyIn(Dist.CLIENT)
	public BiomeProvider getBiomeProvider(long seed)
	{
		return new NightmareBiomeProvider(seed, this.biomes, this.temperatureNoise, this.humidityNoise, this.altitudeNoise, this.weirdnessNoise, this.biomePreset);
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
		int i = this.field_235269_l_ ? y : 0;
		Biome.Attributes biome$attributes = new Biome.Attributes((float) this.field_235264_g_.func_237211_a_((double) x, (double) i, (double) z), (float) this.field_235265_h_.func_237211_a_((double) x, (double) i, (double) z), (float) this.field_235266_i_.func_237211_a_((double) x, (double) i, (double) z), (float) this.field_235267_j_.func_237211_a_((double) x, (double) i, (double) z), 0.0F);
		return this.biomes.stream().min(Comparator.comparing((p_235272_1_) ->
		{
			return p_235272_1_.getFirst().func_235110_a_(biome$attributes);
		})).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.field_244201_b);
	}

	public boolean func_235280_b_(long p_235280_1_)
	{
		return this.seed == p_235280_1_ && this.biomePreset.isPresent() && Objects.equals(this.biomePreset.get().getSecond(), NightmareBiomeProvider.NightmarePreset.nightmarePreset);
	}

	static final class DreamBuilder
	{
		public static final MapCodec<NightmareBiomeProvider.DreamBuilder> field_242624_a = RecordCodecBuilder.mapCodec((p_242630_0_) ->
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
			}).fieldOf("preset").stable().forGetter(NightmareBiomeProvider.DreamBuilder::func_242628_a), RegistryLookupCodec.func_244331_a(Registry.BIOME_KEY).forGetter(NightmareBiomeProvider.DreamBuilder::func_242632_b), Codec.LONG.fieldOf("seed").stable().forGetter(NightmareBiomeProvider.DreamBuilder::func_242634_c)).apply(p_242630_0_, p_242630_0_.stable(NightmareBiomeProvider.DreamBuilder::new));
		});
		private final NightmareBiomeProvider.NightmarePreset field_242625_b;
		private final Registry<Biome> field_242626_c;
		private final long field_242627_d;

		private DreamBuilder(NightmareBiomeProvider.NightmarePreset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_)
		{
			this.field_242625_b = p_i241956_1_;
			this.field_242626_c = p_i241956_2_;
			this.field_242627_d = p_i241956_3_;
		}

		public NightmareBiomeProvider.NightmarePreset func_242628_a()
		{
			return this.field_242625_b;
		}

		public Registry<Biome> func_242632_b()
		{
			return this.field_242626_c;
		}

		public long func_242634_c()
		{
			return this.field_242627_d;
		}

		public NightmareBiomeProvider func_242635_d()
		{
			return this.field_242625_b.func_242619_a(this.field_242626_c, this.field_242627_d);
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
			}).fieldOf("preset").stable().forGetter(NightmareBiomeProvider.NightmareBuilder::func_242628_a), RegistryLookupCodec.func_244331_a(Registry.BIOME_KEY).forGetter(NightmareBiomeProvider.NightmareBuilder::func_242632_b), Codec.LONG.fieldOf("seed").stable().forGetter(NightmareBiomeProvider.NightmareBuilder::func_242634_c)).apply(p_242630_0_, p_242630_0_.stable(NightmareBiomeProvider.NightmareBuilder::new));
		});
		private final NightmareBiomeProvider.NightmarePreset field_242625_b;
		private final Registry<Biome> field_242626_c;
		private final long field_242627_d;

		private NightmareBuilder(NightmareBiomeProvider.NightmarePreset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_)
		{
			this.field_242625_b = p_i241956_1_;
			this.field_242626_c = p_i241956_2_;
			this.field_242627_d = p_i241956_3_;
		}

		public NightmareBiomeProvider.NightmarePreset func_242628_a()
		{
			return this.field_242625_b;
		}

		public Registry<Biome> func_242632_b()
		{
			return this.field_242626_c;
		}

		public long func_242634_c()
		{
			return this.field_242627_d;
		}

		public NightmareBiomeProvider func_242635_d()
		{
			return this.field_242625_b.func_242619_a(this.field_242626_c, this.field_242627_d);
		}
	}

	static class Noise
	{
		private final int field_242610_b;
		private final DoubleList field_242611_c;
		public static final Codec<NightmareBiomeProvider.Noise> field_242609_a = RecordCodecBuilder.create((p_242613_0_) ->
		{
			return p_242613_0_.group(Codec.INT.fieldOf("firstOctave").forGetter(NightmareBiomeProvider.Noise::func_242612_a), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(NightmareBiomeProvider.Noise::func_242614_b)).apply(p_242613_0_, NightmareBiomeProvider.Noise::new);
		});

		public Noise(int p_i241954_1_, List<Double> p_i241954_2_)
		{
			this.field_242610_b = p_i241954_1_;
			this.field_242611_c = new DoubleArrayList(p_i241954_2_);
		}

		public int func_242612_a()
		{
			return this.field_242610_b;
		}

		public DoubleList func_242614_b()
		{
			return this.field_242611_c;
		}
	}

	public static class NightmarePreset
	{
		private static final Map<ResourceLocation, NightmareBiomeProvider.NightmarePreset> biomeMap = Maps.newHashMap();
		public static final NightmareBiomeProvider.NightmarePreset nightmarePreset = new NightmareBiomeProvider.NightmarePreset(GoodNightSleep.locate("nightmare"), (preset, biomeList, seedIn) ->
		{
			return new NightmareBiomeProvider(seedIn, ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () ->
			{
				return biomeList.func_243576_d(GNSBiomes.Keys.NIGHTMARE_HILLS);
			}), Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.1F), () ->
			{
				return biomeList.func_243576_d(GNSBiomes.Keys.WASTED_FOREST);
			}), Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.1F, 0.0F, 0.0F), () ->
			{
				return biomeList.func_243576_d(GNSBiomes.Keys.SHAMEFUL_PLAINS);
			})), Optional.of(Pair.of(biomeList, preset)));
		});
		private final ResourceLocation field_235290_d_;
		private final Function3<NightmareBiomeProvider.NightmarePreset, Registry<Biome>, Long, NightmareBiomeProvider> field_235291_e_;

		public NightmarePreset(ResourceLocation p_i241955_1_, Function3<NightmareBiomeProvider.NightmarePreset, Registry<Biome>, Long, NightmareBiomeProvider> p_i241955_2_)
		{
			this.field_235290_d_ = p_i241955_1_;
			this.field_235291_e_ = p_i241955_2_;
			biomeMap.put(p_i241955_1_, this);
		}

		public NightmareBiomeProvider func_242619_a(Registry<Biome> p_242619_1_, long p_242619_2_)
		{
			return this.field_235291_e_.apply(this, p_242619_1_, p_242619_2_);
		}

		public ResourceLocation getName()
		{
			return field_235290_d_;
		}
	}
}