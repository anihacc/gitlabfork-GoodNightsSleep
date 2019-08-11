package com.legacy.goodnightsleep.world.nightmare;

import java.util.function.Function;

import com.legacy.goodnightsleep.VariableConstants;

import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

public class NightmareWorldManager extends ModDimension
{

	@ObjectHolder("goodnightsleep:nightmare")
	public static final NightmareWorldManager INSTANCE = null;

	public static DimensionType getDimensionType()
	{
		return DimensionType.byName(VariableConstants.locate("nightmare"));
	}

	@Override
	public Function<DimensionType, ? extends Dimension> getFactory()
	{
		return NightmareDimension::new;
	}

}