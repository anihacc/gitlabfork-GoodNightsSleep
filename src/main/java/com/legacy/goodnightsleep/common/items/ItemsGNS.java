package com.legacy.goodnightsleep.common.items;

import com.legacy.goodnightsleep.common.items.tools.ItemGNSArmor;
import com.legacy.goodnightsleep.common.items.tools.ItemGNSAxe;
import com.legacy.goodnightsleep.common.items.tools.ItemGNSPickaxe;
import com.legacy.goodnightsleep.common.items.tools.ItemGNSShovel;
import com.legacy.goodnightsleep.common.items.tools.ItemGNSSword;
import com.legacy.goodnightsleep.common.items.util.ItemGNS;
import com.legacy.goodnightsleep.common.items.util.ItemLuxuriousBed;
import com.legacy.goodnightsleep.common.items.util.ItemWretchedBed;
import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.common.registry.VariableConstants;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemsGNS
{
	private static IForgeRegistry<Item> iItemRegistry;

	public static Item positite_gem, zitrite_ingot, negatite_gem, necrum, rainbow_ingot;
	
	public static Item candy_pickaxe, candy_axe, candy_shovel, candy_sword;
	
	public static Item necrum_pickaxe, necrum_axe, necrum_shovel, necrum_sword;

	public static Item zitrite_pickaxe, zitrite_axe, zitrite_shovel, zitrite_sword;

	public static Item rainbow_pickaxe, rainbow_axe, rainbow_shovel, rainbow_sword;

	public static Item positite_pickaxe, positite_axe, positite_shovel, positite_sword;

	public static Item negatite_pickaxe, negatite_axe, negatite_shovel, negatite_sword;
	
	public static Item candy_helmet, candy_chestplate, candy_leggings, candy_boots;

	public static Item rainbow_helmet, rainbow_chestplate, rainbow_leggings, rainbow_boots;

	public static Item positite_helmet, positite_chestplate, positite_leggings, positite_boots;

	public static Item negatite_helmet, negatite_chestplate, negatite_leggings, negatite_boots;
	
	public static Item luxurious_bed_item, wretched_bed_item;

	
	public static ToolMaterial NEGATITE = EnumHelper.addToolMaterial("NEGATITE", 3, 1561, 8.5F, 3.2F, 10);
	
	public static void initialization()
	{
		positite_gem = register("positite_gem", new ItemGNS(GNSCreativeTabs.items));
		zitrite_ingot = register("zitrite", new ItemGNS(GNSCreativeTabs.items));
		negatite_gem = register("negatite_gem", new ItemGNS(GNSCreativeTabs.items));
		necrum = register("necrum", new ItemGNS(GNSCreativeTabs.items));
		rainbow_ingot = register("rainbow_ingot", new ItemGNS(GNSCreativeTabs.items));
		
		candy_pickaxe = register("candy_pickaxe", new ItemGNSPickaxe(ToolMaterial.GOLD));
		candy_axe = register("candy_axe", new ItemGNSAxe(ToolMaterial.GOLD));
		candy_shovel = register("candy_shovel", new ItemGNSShovel(ToolMaterial.GOLD));

		necrum_pickaxe = register("necrum_pickaxe", new ItemGNSPickaxe(ToolMaterial.WOOD));
		necrum_axe = register("necrum_axe", new ItemGNSAxe(ToolMaterial.WOOD));
		necrum_shovel = register("necrum_shovel", new ItemGNSShovel(ToolMaterial.WOOD));

		zitrite_pickaxe = register("zitrite_pickaxe", new ItemGNSPickaxe(ToolMaterial.STONE));
		zitrite_axe = register("zitrite_axe", new ItemGNSAxe(ToolMaterial.STONE));
		zitrite_shovel = register("zitrite_shovel", new ItemGNSShovel(ToolMaterial.STONE));

		rainbow_pickaxe = register("rainbow_pickaxe", new ItemGNSPickaxe(ToolMaterial.IRON));
		rainbow_axe = register("rainbow_axe", new ItemGNSAxe(ToolMaterial.IRON));
		rainbow_shovel = register("rainbow_shovel", new ItemGNSShovel(ToolMaterial.IRON));

		positite_pickaxe = register("positite_pickaxe", new ItemGNSPickaxe(ToolMaterial.DIAMOND));
		positite_axe = register("positite_axe", new ItemGNSAxe(ToolMaterial.DIAMOND));
		positite_shovel = register("positite_shovel", new ItemGNSShovel(ToolMaterial.DIAMOND));

		negatite_pickaxe = register("negatite_pickaxe", new ItemGNSPickaxe(NEGATITE));
		negatite_axe = register("negatite_axe", new ItemGNSAxe(ToolMaterial.DIAMOND));
		negatite_shovel = register("negatite_shovel", new ItemGNSShovel(NEGATITE));
		
		necrum_sword = register("necrum_sword", new ItemGNSSword(ToolMaterial.WOOD));
		zitrite_sword = register("zitrite_sword", new ItemGNSSword(ToolMaterial.STONE));
		rainbow_sword = register("rainbow_sword", new ItemGNSSword(ToolMaterial.IRON));
		positite_sword = register("positite_sword", new ItemGNSSword(ToolMaterial.DIAMOND));
		negatite_sword = register("negatite_sword", new ItemGNSSword(NEGATITE));

		rainbow_helmet = register("rainbow_helmet", new ItemGNSArmor(EntityEquipmentSlot.HEAD, ArmorMaterial.IRON, "rainbow"));
		rainbow_chestplate = register("rainbow_chestplate", new ItemGNSArmor(EntityEquipmentSlot.CHEST, ArmorMaterial.IRON, "rainbow"));
		rainbow_leggings = register("rainbow_leggings", new ItemGNSArmor(EntityEquipmentSlot.LEGS, ArmorMaterial.IRON, "rainbow"));
		rainbow_boots = register("rainbow_boots", new ItemGNSArmor(EntityEquipmentSlot.FEET, ArmorMaterial.IRON, "rainbow"));

		positite_helmet = register("positite_helmet", new ItemGNSArmor(EntityEquipmentSlot.HEAD, ArmorMaterial.DIAMOND, "positite"));
		positite_chestplate = register("positite_chestplate", new ItemGNSArmor(EntityEquipmentSlot.CHEST, ArmorMaterial.DIAMOND, "positite"));
		positite_leggings = register("positite_leggings", new ItemGNSArmor(EntityEquipmentSlot.LEGS, ArmorMaterial.DIAMOND, "positite"));
		positite_boots = register("positite_boots", new ItemGNSArmor(EntityEquipmentSlot.FEET, ArmorMaterial.DIAMOND, "positite"));

		negatite_helmet = register("negatite_helmet", new ItemGNSArmor(EntityEquipmentSlot.HEAD, ArmorMaterial.DIAMOND, "negatite"));
		negatite_chestplate = register("negatite_chestplate", new ItemGNSArmor(EntityEquipmentSlot.CHEST, ArmorMaterial.DIAMOND, "negatite"));
		negatite_leggings = register("negatite_leggings", new ItemGNSArmor(EntityEquipmentSlot.LEGS, ArmorMaterial.DIAMOND, "negatite"));
		negatite_boots = register("negatite_boots", new ItemGNSArmor(EntityEquipmentSlot.FEET, ArmorMaterial.DIAMOND, "negatite"));

		candy_helmet = register("candy_helmet", new ItemGNSArmor(EntityEquipmentSlot.HEAD, ArmorMaterial.CHAIN, "candy"));
		candy_chestplate = register("candy_chestplate", new ItemGNSArmor(EntityEquipmentSlot.CHEST, ArmorMaterial.CHAIN, "candy"));
		candy_leggings = register("candy_leggings", new ItemGNSArmor(EntityEquipmentSlot.LEGS, ArmorMaterial.CHAIN, "candy"));
		candy_boots = register("candy_boots", new ItemGNSArmor(EntityEquipmentSlot.FEET, ArmorMaterial.CHAIN, "candy"));
		
		//Block Items
		luxurious_bed_item = register("luxurious_bed_item", new ItemLuxuriousBed());
		wretched_bed_item = register("wretched_bed_item", new ItemWretchedBed());
	}

	private static Item register(String unlocalizedName, Item item)
	{
		item.setUnlocalizedName(unlocalizedName);

		item.setRegistryName(VariableConstants.locate(unlocalizedName));

		iItemRegistry.register(item);

		return item;
	}
	
	public static void setItemRegistry(IForgeRegistry<Item> iItemRegistry)
	{
		ItemsGNS.iItemRegistry = iItemRegistry;
	}

}