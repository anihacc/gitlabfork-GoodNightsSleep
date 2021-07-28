package com.legacy.goodnightsleep.item;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;

public enum GNSItemTier implements Tier
{
	// @formatter:off
	CANDY(1, 196, 4.0F, 1.0F, 5, () -> {return Ingredient.of(GNSBlocks.candy_block);}),
	NECRUM(1, 131, 6.0F, 2.0F, 0, () -> {return Ingredient.of(GNSItems.necrum);}),
	RAINBOW(2, 375, 6.0F, 2.0F, 14, () -> {return Ingredient.of(GNSItems.rainbow_ingot);}),
	ZITRITE(3, 975, 8.0F, 2.5F, 10, () -> {return Ingredient.of(GNSItems.zitrite_ingot);}),
	POSITITE(3, 2341, 8.0F, 3.0F, 10, () -> {return Ingredient.of(GNSItems.positite);}),
	NEGATITE(4, 1100, 10.0F, 3.0F, 1, () -> {return Ingredient.of(GNSItems.negatite);});
	// @formatter:on

	private final int harvestLevel;

	private final int maxUses;

	private final float efficiency;

	private final float attackDamage;

	private final int enchantability;

	private final LazyLoadedValue<Ingredient> repairMaterial;

	private GNSItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn)
	{
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyLoadedValue<>(repairMaterialIn);
	}

	public int getUses()
	{
		return this.maxUses;
	}

	public float getSpeed()
	{
		return this.efficiency;
	}

	public float getAttackDamageBonus()
	{
		return this.attackDamage;
	}

	public int getLevel()
	{
		return this.harvestLevel;
	}

	public int getEnchantmentValue()
	{
		return this.enchantability;
	}

	public Ingredient getRepairIngredient()
	{
		return this.repairMaterial.get();
	}
}