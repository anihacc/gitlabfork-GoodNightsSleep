package com.legacy.goodnightsleep.item;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

public enum GNSItemTier implements IItemTier
{
	CANDY(1, 196, 4.0F, 1.0F, 5, () -> {
		return Ingredient.fromItems(BlocksGNS.candy_block);
	}),
	NECRUM(1, 131, 6.0F, 2.0F, 0, () -> {return Ingredient.fromItems(ItemsGNS.necrum);}),
	RAINBOW(2, 375, 6.0F, 2.0F, 14, () -> {return Ingredient.fromItems(ItemsGNS.rainbow_ingot);}),
	ZITRITE(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemsGNS.zitrite_ingot);}),
	POSITITE(3, 2341, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemsGNS.positite_gem);}),
	NEGATITE(4, 1561, 10.0F, 3.5F, 0, () -> {return Ingredient.fromItems(ItemsGNS.negatite_gem);});

	private final int harvestLevel;

	private final int maxUses;

	private final float efficiency;

	private final float attackDamage;

	private final int enchantability;

	private final LazyLoadBase<Ingredient> repairMaterial;

	private GNSItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn)
	{
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
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