package com.legacy.goodnightsleep.item;

import net.minecraft.item.Food;

public class GNSFoods
{

	public static final Food RAINBOW_BERRIES = (new Food.Builder()).nutrition(6).saturationMod(0.6F).build();

	public static final Food CANDY = (new Food.Builder()).nutrition(2).saturationMod(0.2F).fast().build();
	
	public static final Food TELEPORTATION_STEW = (new Food.Builder()).nutrition(0).saturationMod(0.0F).alwaysEat().build();
}