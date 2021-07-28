package com.legacy.goodnightsleep.item;

import net.minecraft.world.food.FoodProperties;

public class GNSFoods
{

	public static final FoodProperties RAINBOW_BERRIES = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();

	public static final FoodProperties CANDY = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).fast().build();
	
	public static final FoodProperties TELEPORTATION_STEW = (new FoodProperties.Builder()).nutrition(0).saturationMod(0.0F).alwaysEat().build();
}