package com.legacy.goodnightsleep.world.dream.features;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class DreamOreFeatureConfig implements IFeatureConfig
{

	public final DreamOreFeatureConfig.FillerBlockType target;

	public final int size;

	public final BlockState state;

	public DreamOreFeatureConfig(DreamOreFeatureConfig.FillerBlockType target, BlockState state, int size)
	{
		this.size = size;
		this.state = state;
		this.target = target;
	}

	public <T> Dynamic<T> serialize(DynamicOps<T> p_214634_1_)
	{
		return new Dynamic<>(p_214634_1_, p_214634_1_.createMap(ImmutableMap.of(p_214634_1_.createString("size"), p_214634_1_.createInt(this.size), p_214634_1_.createString("target"), p_214634_1_.createString(this.target.func_214737_a()), p_214634_1_.createString("state"), BlockState.serialize(p_214634_1_, this.state).getValue())));
	}

	public static DreamOreFeatureConfig deserialize(Dynamic<?> p_214641_0_)
	{
		int i = p_214641_0_.get("size").asInt(0);
		DreamOreFeatureConfig.FillerBlockType orefeatureconfig$fillerblocktype = DreamOreFeatureConfig.FillerBlockType.func_214736_a(p_214641_0_.get("target").asString(""));
		BlockState blockstate = p_214641_0_.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new DreamOreFeatureConfig(orefeatureconfig$fillerblocktype, blockstate, i);
	}

	public static enum FillerBlockType
	{
		DELUSION("delusion", new BlockMatcher(BlocksGNS.delusion_stone));

		private static final Map<String, DreamOreFeatureConfig.FillerBlockType> field_214741_c = Arrays.stream(values()).collect(Collectors.toMap(DreamOreFeatureConfig.FillerBlockType::func_214737_a, (p_214740_0_) -> {
			return p_214740_0_;
		}));

		private final String field_214742_d;

		private final Predicate<BlockState> field_214743_e;

		private FillerBlockType(String p_i50618_3_, Predicate<BlockState> p_i50618_4_)
		{
			this.field_214742_d = p_i50618_3_;
			this.field_214743_e = p_i50618_4_;
		}

		public String func_214737_a()
		{
			return this.field_214742_d;
		}

		public static DreamOreFeatureConfig.FillerBlockType func_214736_a(String p_214736_0_)
		{
			return field_214741_c.get(p_214736_0_);
		}

		public Predicate<BlockState> func_214738_b()
		{
			return this.field_214743_e;
		}
	}
}