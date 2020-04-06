package com.legacy.goodnightsleep.item;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.audio.GNSSounds;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum GNSArmorMaterial implements IArmorMaterial
{
	CANDY("candy", 7, new int[] { 1, 2, 2, 1 }, 5, GNSSounds.ITEM_ARMOR_EQUIP_CANDY, 0.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.candy);
	}), RAINBOW("rainbow", 22, new int[] { 2, 6, 5, 2 }, 14, GNSSounds.ITEM_ARMOR_EQUIP_RAINBOW, 0.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.rainbow_ingot);
	}), POSITITE("positite", 49, new int[] { 3, 8, 6, 3 }, 10, GNSSounds.ITEM_ARMOR_EQUIP_POSITITE, 2.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.positite_gem);
	}), NECRUM("necrum", 5, new int[] { 2, 3, 3, 2 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.necrum);
	}), ZITRITE("zitrite", 15, new int[] { 3, 7, 6, 3 }, 0, GNSSounds.ITEM_ARMOR_EQUIP_ZITRITE, 0.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.zitrite_ingot);
	}), NEGATITE("negatite", 29, new int[] { 4, 9, 7, 4 }, 0, GNSSounds.ITEM_ARMOR_EQUIP_NEGATITE, 4.0F, () ->
	{
		return Ingredient.fromItems(GNSItems.negatite_gem);
	});

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };

	private final String name;

	private final int maxDamageFactor;

	private final int[] damageReductionAmountArray;

	private final int enchantability;

	private final SoundEvent soundEvent;

	private final float toughness;

	private final LazyValue<Ingredient> repairMaterial;

	private GNSArmorMaterial(String nameIn, int p_i48533_4_, int[] p_i48533_5_, int p_i48533_6_, SoundEvent p_i48533_7_, float p_i48533_8_, Supplier<Ingredient> p_i48533_9_)
	{
		this.name = GoodNightSleep.find(nameIn);
		this.maxDamageFactor = p_i48533_4_;
		this.damageReductionAmountArray = p_i48533_5_;
		this.enchantability = p_i48533_6_;
		this.soundEvent = p_i48533_7_;
		this.toughness = p_i48533_8_;
		this.repairMaterial = new LazyValue<>(p_i48533_9_);
	}

	public int getDurability(EquipmentSlotType slotIn)
	{
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn)
	{
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability()
	{
		return this.enchantability;
	}

	public SoundEvent getSoundEvent()
	{
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial()
	{
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName()
	{
		return this.name;
	}

	public float getToughness()
	{
		return this.toughness;
	}
}