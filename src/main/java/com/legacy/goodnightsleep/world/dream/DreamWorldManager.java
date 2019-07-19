package com.legacy.goodnightsleep.world.dream;

import java.util.function.BiFunction;

import com.legacy.goodnightsleep.VariableConstants;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

public class DreamWorldManager extends ModDimension
{

	@ObjectHolder("goodnightsleep:good_dream")
	public static final DreamWorldManager INSTANCE = null;

	public static DimensionType getDimensionType()
	{
		return DimensionType.byName(VariableConstants.locate("good_dream"));
	}

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
	{
		return GoodDreamDimension::new;
	}

}