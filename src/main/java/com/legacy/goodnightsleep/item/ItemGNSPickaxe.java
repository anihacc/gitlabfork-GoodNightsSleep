package com.legacy.goodnightsleep.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ItemGNSPickaxe extends ItemPickaxe
{
	public ItemGNSPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
}
