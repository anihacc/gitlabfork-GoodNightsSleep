package com.legacy.goodnightsleep.item;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum GNSItemTier implements IItemTier
{
	CANDY(1, 196, 4.0F, 1.0F, 5, () -> {return Ingredient.fromItems(GNSBlocks.candy_block);}),
	NECRUM(1, 131, 6.0F, 2.0F, 0, () -> {return Ingredient.fromItems(GNSItems.necrum);}),
	RAINBOW(2, 375, 6.0F, 2.0F, 14, () -> {return Ingredient.fromItems(GNSItems.rainbow_ingot);}),
	ZITRITE(3, 975, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(GNSItems.zitrite_ingot);}),
	POSITITE(3, 2341, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(GNSItems.positite_gem);}),
	NEGATITE(4, 1100, 10.0F, 3.0F, 1, () -> {return Ingredient.fromItems(GNSItems.negatite_gem);});

	private final int harvestLevel;

	private final int maxUses;

	private final float efficiency;

	private final float attackDamage;

	private final int enchantability;

	private final LazyValue<Ingredient> repairMaterial;

	private GNSItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn)
	{
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	public int getMaxUses()
	{
		return this.maxUses;
	}

	public float getEfficiency()
	{
		return this.efficiency;
	}

	public float getAttackDamage()
	{
		return this.attackDamage;
	}

	public int getHarvestLevel()
	{
		return this.harvestLevel;
	}

	public int getEnchantability()
	{
		return this.enchantability;
	}

	public Ingredient getRepairMaterial()
	{
		return this.repairMaterial.getValue();
	}
}