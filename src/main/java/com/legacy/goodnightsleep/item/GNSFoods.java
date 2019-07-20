package com.legacy.goodnightsleep.item;

import net.minecraft.item.Food;

public class GNSFoods
{

	public static final Food CANDY = (new Food.Builder()).hunger(4).saturation(0.6F).fastToEat().build();

	public static final Food RAINBOW_BERRIES = (new Food.Builder()).hunger(2).saturation(0.2F).fastToEat().build();
	
	public static final Food TELEPORTATION_STEW = (new Food.Builder()).hunger(0).saturation(0.0F).setAlwaysEdible().build();
}