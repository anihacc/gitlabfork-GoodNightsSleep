package com.legacy.goodnightsleep.item;

import com.legacy.goodnightsleep.VariableConstants;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemsGNS
{
	private static IForgeRegistry<Item> iItemRegistry;

	public static Item positite_gem, zitrite_ingot, negatite_gem, necrum, rainbow_ingot, candy_ingot;
	
	public static Item candy_pickaxe, candy_axe, candy_shovel, candy_hoe, candy_sword;
	
	public static Item necrum_pickaxe, necrum_axe, necrum_shovel, necrum_hoe, necrum_sword;

	public static Item zitrite_pickaxe, zitrite_axe, zitrite_shovel, zitrite_hoe, zitrite_sword;

	public static Item rainbow_pickaxe, rainbow_axe, rainbow_shovel, rainbow_hoe, rainbow_sword;

	public static Item positite_pickaxe, positite_axe, positite_shovel, positite_hoe, positite_sword;

	public static Item negatite_pickaxe, negatite_axe, negatite_shovel, negatite_hoe, negatite_sword;
	
	public static Item candy_helmet, candy_chestplate, candy_leggings, candy_boots;

	public static Item rainbow_helmet, rainbow_chestplate, rainbow_leggings, rainbow_boots;

	public static Item positite_helmet, positite_chestplate, positite_leggings, positite_boots;

	public static Item negatite_helmet, negatite_chestplate, negatite_leggings, negatite_boots;
	
	public static Item candy, lolipop, rainbow_berries, luxurious_soup, wretched_soup;
	
	public static Item powdered_sugar, necrotic_extract, rainbow_seeds;
	
	public static Item luxurious_bed_item, wretched_bed_item, strange_bed_item;
	
	//public static EnumRarity NIGHTMARE = EnumHelper.addRarity("NIGHTMARE", TextFormatting.DARK_RED, "Nightmare");
	
	//public static ToolMaterial CANDY = EnumHelper.addToolMaterial("CANDY", 1, 196, 4.0F, 1.0F, 5);
	//public static ToolMaterial RAINBOW = EnumHelper.addToolMaterial("RAINBOW", 2, 375, 6.0F, 2.0F, 14);
	//public static ToolMaterial POSITITE = EnumHelper.addToolMaterial("POSITITE", 3, 2341, 8.0F, 3.0F, 10);
	//public static ToolMaterial NECRUM = EnumHelper.addToolMaterial("NECRUM", 1, 131, 6.0F, 2.0F, 0);
	//public static ToolMaterial ZITRITE = EnumHelper.addToolMaterial("ZITRITE", 2, 250, 8.0F, 3.0F, 0);
	//public static ToolMaterial NEGATITE = EnumHelper.addToolMaterial("NEGATITE", 4, 1561, 10.0F, 4.0F, 0);
	
	//public static ArmorMaterial CANDY_ARMOR = EnumHelper.addArmorMaterial("CANDY_ARMOR", "candy", 7, new int[]{1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
	//public static ArmorMaterial RAINBOW_ARMOR = EnumHelper.addArmorMaterial("RAINBOW_ARMOR", "rainbow", 22, new int[]{2, 6, 5, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	//public static ArmorMaterial POSITITE_ARMOR = EnumHelper.addArmorMaterial("POSITITE_ARMOR", "positite", 49, new int[]{3, 8, 6, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	//public static ArmorMaterial NECRUM_ARMOR = EnumHelper.addArmorMaterial("NECRUM_ARMOR", "necrum", 5, new int[]{2, 3, 3, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
	//public static ArmorMaterial ZITRITE_ARMOR = EnumHelper.addArmorMaterial("ZITRITE_ARMOR", "zitrite", 15, new int[]{3, 7, 6, 3}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	//public static ArmorMaterial NEGATITE_ARMOR = EnumHelper.addArmorMaterial("NEGATITE_ARMOR", "negatite", 33, new int[]{4, 9, 7, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
	
	public static void initialization()
	{
		// Block Items
		//luxurious_bed_item = register("luxurious_bed_item", new ItemLuxuriousBed());
		//wretched_bed_item = register("wretched_bed_item", new ItemWretchedBed());
		//strange_bed_item = register("strange_bed_item", new ItemStrangeBed());

		positite_gem = register("positite_gem", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		rainbow_ingot = register("rainbow_ingot", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		candy_ingot = register("candy_ingot", new Item(new Item.Properties()));
		zitrite_ingot = register("zitrite_ingot", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		negatite_gem = register("negatite_gem", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		necrum = register("necrum", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		necrotic_extract = register("necrotic_extract", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		powdered_sugar = register("powdered_sugar", new Item(new Item.Properties().group(GNSCreativeTabs.items)));
		lolipop = register("lolipop", new Item(new Item.Properties().group(GNSCreativeTabs.items)));

		candy = register("candy", new ItemFood(2, 0.2F, false, new Item.Properties().group(GNSCreativeTabs.items)));
		
		//rainbow_seeds = register("rainbow_seeds", new ItemGNSSeeds(BlocksGNS.rainbow_crop)).setCreativeTab(GNSCreativeTabs.items);
		rainbow_berries = register("rainbow_berries", new ItemFood(4, 0.6F, true, new Item.Properties().group(GNSCreativeTabs.items)));
		
		luxurious_soup = register("luxurious_soup", new ItemFood(0, 0, false, new Item.Properties().group(GNSCreativeTabs.items)));
		wretched_soup = register("wretched_soup", new ItemFood(0, 0, false, new Item.Properties().group(GNSCreativeTabs.items)));

		candy_sword = register("candy_sword", new ItemSword(GNSItemTier.CANDY, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		candy_pickaxe = register("candy_pickaxe", new ItemGNSPickaxe(GNSItemTier.CANDY, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		candy_axe = register("candy_axe", new ItemGNSAxe(GNSItemTier.CANDY, 7.0F, -3.2F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		candy_shovel = register("candy_shovel", new ItemSpade(GNSItemTier.CANDY, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		candy_hoe = register("candy_hoe", new ItemHoe(GNSItemTier.CANDY, -2.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		
		necrum_sword = register("necrum_sword", new ItemSword(GNSItemTier.NECRUM, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		necrum_pickaxe = register("necrum_pickaxe", new ItemGNSPickaxe(GNSItemTier.NECRUM, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		necrum_axe = register("necrum_axe", new ItemGNSAxe(GNSItemTier.NECRUM, 7.0F, -3.2F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		necrum_shovel = register("necrum_shovel", new ItemSpade(GNSItemTier.NECRUM, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		necrum_hoe = register("necrum_hoe", new ItemHoe(GNSItemTier.NECRUM, -2.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

		zitrite_sword = register("zitrite_sword", new ItemSword(GNSItemTier.ZITRITE, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		zitrite_pickaxe = register("zitrite_pickaxe", new ItemGNSPickaxe(GNSItemTier.ZITRITE, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		zitrite_axe = register("zitrite_axe", new ItemGNSAxe(GNSItemTier.ZITRITE, 6.0F, -3.1F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		zitrite_shovel = register("zitrite_shovel", new ItemSpade(GNSItemTier.ZITRITE, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		zitrite_hoe = register("zitrite_hoe", new ItemHoe(GNSItemTier.ZITRITE, -1.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

		rainbow_sword = register("rainbow_sword", new ItemSword(GNSItemTier.RAINBOW, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		rainbow_pickaxe = register("rainbow_pickaxe", new ItemGNSPickaxe(GNSItemTier.RAINBOW, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		rainbow_axe = register("rainbow_axe", new ItemGNSAxe(GNSItemTier.RAINBOW, 6.0F, -3.1F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		rainbow_shovel = register("rainbow_shovel", new ItemSpade(GNSItemTier.RAINBOW, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		rainbow_hoe = register("rainbow_hoe", new ItemHoe(GNSItemTier.RAINBOW, -1.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

		positite_sword = register("positite_sword", new ItemSword(GNSItemTier.POSITITE, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		positite_pickaxe = register("positite_pickaxe", new ItemGNSPickaxe(GNSItemTier.POSITITE, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		positite_axe = register("positite_axe", new ItemGNSAxe(GNSItemTier.POSITITE, 5.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		positite_shovel = register("positite_shovel", new ItemSpade(GNSItemTier.POSITITE, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		positite_hoe = register("positite_hoe", new ItemHoe(GNSItemTier.POSITITE, 0.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

		negatite_sword = register("negatite_sword", new ItemSword(GNSItemTier.NEGATITE, 4, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)));
		negatite_pickaxe = register("negatite_pickaxe", new ItemGNSPickaxe(GNSItemTier.NEGATITE, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		negatite_axe = register("negatite_axe", new ItemGNSAxe(GNSItemTier.NEGATITE, 5.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		negatite_shovel = register("negatite_shovel", new ItemSpade(GNSItemTier.NEGATITE, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
		negatite_hoe = register("negatite_hoe", new ItemHoe(GNSItemTier.NEGATITE, 0.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

		candy_helmet = register("candy_helmet", new ItemArmor(GNSArmorMaterial.CANDY, EntityEquipmentSlot.HEAD, new Item.Properties().group(GNSCreativeTabs.armor)));
		candy_chestplate = register("candy_chestplate", new ItemArmor(GNSArmorMaterial.CANDY, EntityEquipmentSlot.CHEST, new Item.Properties().group(GNSCreativeTabs.armor)));
		candy_leggings = register("candy_leggings", new ItemArmor(GNSArmorMaterial.CANDY, EntityEquipmentSlot.LEGS, new Item.Properties().group(GNSCreativeTabs.armor)));
		candy_boots = register("candy_boots", new ItemArmor(GNSArmorMaterial.CANDY, EntityEquipmentSlot.FEET, new Item.Properties().group(GNSCreativeTabs.armor)));

		rainbow_helmet = register("rainbow_helmet", new ItemArmor(GNSArmorMaterial.RAINBOW, EntityEquipmentSlot.HEAD, new Item.Properties().group(GNSCreativeTabs.armor)));
		rainbow_chestplate = register("rainbow_chestplate", new ItemArmor(GNSArmorMaterial.RAINBOW, EntityEquipmentSlot.CHEST, new Item.Properties().group(GNSCreativeTabs.armor)));
		rainbow_leggings = register("rainbow_leggings", new ItemArmor(GNSArmorMaterial.RAINBOW, EntityEquipmentSlot.LEGS, new Item.Properties().group(GNSCreativeTabs.armor)));
		rainbow_boots = register("rainbow_boots", new ItemArmor(GNSArmorMaterial.RAINBOW, EntityEquipmentSlot.FEET, new Item.Properties().group(GNSCreativeTabs.armor)));

		positite_helmet = register("positite_helmet", new ItemArmor(GNSArmorMaterial.POSITITE, EntityEquipmentSlot.HEAD, new Item.Properties().group(GNSCreativeTabs.armor)));
		positite_chestplate = register("positite_chestplate", new ItemArmor(GNSArmorMaterial.POSITITE, EntityEquipmentSlot.CHEST, new Item.Properties().group(GNSCreativeTabs.armor)));
		positite_leggings = register("positite_leggings", new ItemArmor(GNSArmorMaterial.POSITITE, EntityEquipmentSlot.LEGS, new Item.Properties().group(GNSCreativeTabs.armor)));
		positite_boots = register("positite_boots", new ItemArmor(GNSArmorMaterial.POSITITE, EntityEquipmentSlot.FEET, new Item.Properties().group(GNSCreativeTabs.armor)));

		negatite_helmet = register("negatite_helmet", new ItemArmor(GNSArmorMaterial.NEGATITE, EntityEquipmentSlot.HEAD, new Item.Properties().group(GNSCreativeTabs.armor)));
		negatite_chestplate = register("negatite_chestplate", new ItemArmor(GNSArmorMaterial.NEGATITE, EntityEquipmentSlot.CHEST, new Item.Properties().group(GNSCreativeTabs.armor)));
		negatite_leggings = register("negatite_leggings", new ItemArmor(GNSArmorMaterial.NEGATITE, EntityEquipmentSlot.LEGS, new Item.Properties().group(GNSCreativeTabs.armor)));
		negatite_boots = register("negatite_boots", new ItemArmor(GNSArmorMaterial.NEGATITE, EntityEquipmentSlot.FEET, new Item.Properties().group(GNSCreativeTabs.armor)));
	}

	private static Item register(String unlocalizedName, Item item)
	{
		//item.setTranslationKey(unlocalizedName);

		item.setRegistryName(VariableConstants.locate(unlocalizedName));

		iItemRegistry.register(item);

		return item;
	}
	
	public static void setItemRegistry(IForgeRegistry<Item> iItemRegistry)
	{
		ItemsGNS.iItemRegistry = iItemRegistry;
	}

}