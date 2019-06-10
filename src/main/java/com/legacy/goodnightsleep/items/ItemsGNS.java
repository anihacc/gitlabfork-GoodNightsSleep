package com.legacy.goodnightsleep.items;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.tools.ItemGNSArmor;
import com.legacy.goodnightsleep.items.tools.ItemGNSAxe;
import com.legacy.goodnightsleep.items.tools.ItemGNSHoe;
import com.legacy.goodnightsleep.items.tools.ItemGNSPickaxe;
import com.legacy.goodnightsleep.items.tools.ItemGNSShovel;
import com.legacy.goodnightsleep.items.tools.ItemGNSSword;
import com.legacy.goodnightsleep.items.util.ItemGNS;
import com.legacy.goodnightsleep.items.util.ItemLuxuriousBed;
import com.legacy.goodnightsleep.items.util.ItemWretchedBed;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.registry.VariableConstants;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsGNS
{

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
	
	public static Item luxurious_bed_item, wretched_bed_item;
	
	public static ToolMaterial CANDY = EnumHelper.addToolMaterial("CANDY", 1, 196, 4.0F, 1.0F, 5);
	public static ToolMaterial RAINBOW = EnumHelper.addToolMaterial("RAINBOW", 2, 375, 6.0F, 2.0F, 14);
	public static ToolMaterial POSITITE = EnumHelper.addToolMaterial("POSITITE", 3, 2341, 8.0F, 3.0F, 10);
	public static ToolMaterial NECRUM = EnumHelper.addToolMaterial("NECRUM", 1, 131, 6.0F, 2.0F, 0);
	public static ToolMaterial ZITRITE = EnumHelper.addToolMaterial("ZITRITE", 2, 250, 8.0F, 3.0F, 0);
	public static ToolMaterial NEGATITE = EnumHelper.addToolMaterial("NEGATITE", 4, 1561, 10.0F, 4.0F, 0);
	
	public static ArmorMaterial CANDY_ARMOR = EnumHelper.addArmorMaterial("CANDY_ARMOR", 7, new int[]{1, 2, 2, 1}, 5);
	public static ArmorMaterial RAINBOW_ARMOR = EnumHelper.addArmorMaterial("RAINBOW_ARMOR", 22, new int[]{2, 6, 5, 2}, 14);
	public static ArmorMaterial POSITITE_ARMOR = EnumHelper.addArmorMaterial("POSITITE_ARMOR", 49, new int[]{3, 8, 6, 3}, 10);
	public static ArmorMaterial NECRUM_ARMOR = EnumHelper.addArmorMaterial("NECRUM_ARMOR", 5, new int[]{2, 3, 3, 2}, 0);
	public static ArmorMaterial ZITRITE_ARMOR = EnumHelper.addArmorMaterial("ZITRITE_ARMOR", 15, new int[]{3, 7, 6, 3}, 0);
	public static ArmorMaterial NEGATITE_ARMOR = EnumHelper.addArmorMaterial("NEGATITE_ARMOR", 33, new int[]{4, 9, 7, 4}, 0);
	
	public static void initialization()
	{
		// Block Items
		luxurious_bed_item = register("luxurious_bed_item", new ItemLuxuriousBed()).setMaxStackSize(1);
		wretched_bed_item = register("wretched_bed_item", new ItemWretchedBed()).setMaxStackSize(1);
				
		positite_gem = register("positite_gem", new ItemGNS(GNSCreativeTabs.items));
		rainbow_ingot = register("rainbow_ingot", new ItemGNS(GNSCreativeTabs.items));
		candy_ingot = register("candy_ingot", new ItemGNS(null));
		zitrite_ingot = register("zitrite_ingot", new ItemGNS(GNSCreativeTabs.items));
		negatite_gem = register("negatite_gem", new ItemGNS(GNSCreativeTabs.items));
		necrum = register("necrum", new ItemGNS(GNSCreativeTabs.items));
		necrotic_extract = register("necrotic_extract", new ItemGNS(GNSCreativeTabs.items));
		powdered_sugar = register("powdered_sugar", new ItemGNS(GNSCreativeTabs.items));
		lolipop = register("lolipop", new ItemGNS(GNSCreativeTabs.items));
		
		candy = register("candy", new ItemFood(2, false));
		
		rainbow_seeds = register("rainbow_seeds", new ItemGNSSeeds(BlocksGNS.rainbow_crop)).setCreativeTab(GNSCreativeTabs.items);
		rainbow_berries = register("rainbow_berries", new ItemFood(4, 0.6F, true));
		
		luxurious_soup = register("luxurious_soup", new ItemFood(0, false).setMaxStackSize(0));
		wretched_soup = register("wretched_soup", new ItemFood(0, false).setMaxStackSize(0));
		
		candy_sword = register("candy_sword", new ItemGNSSword(CANDY));
		candy_pickaxe = register("candy_pickaxe", new ItemGNSPickaxe(CANDY));
		candy_axe = register("candy_axe", new ItemGNSAxe(ToolMaterial.STONE));
		candy_shovel = register("candy_shovel", new ItemGNSShovel(CANDY));
		candy_hoe = register("candy_hoe", new ItemGNSHoe(CANDY));
		
		necrum_sword = register("necrum_sword", new ItemGNSSword(NECRUM));
		necrum_pickaxe = register("necrum_pickaxe", new ItemGNSPickaxe(NECRUM));
		necrum_axe = register("necrum_axe", new ItemGNSAxe(ToolMaterial.STONE));
		necrum_shovel = register("necrum_shovel", new ItemGNSShovel(NECRUM));
		necrum_hoe = register("necrum_hoe", new ItemGNSHoe(NECRUM));

		zitrite_sword = register("zitrite_sword", new ItemGNSSword(ZITRITE));
		zitrite_pickaxe = register("zitrite_pickaxe", new ItemGNSPickaxe(ZITRITE));
		zitrite_axe = register("zitrite_axe", new ItemGNSAxe(ToolMaterial.IRON));
		zitrite_shovel = register("zitrite_shovel", new ItemGNSShovel(ZITRITE));
		zitrite_hoe = register("zitrite_hoe", new ItemGNSHoe(ZITRITE));

		rainbow_sword = register("rainbow_sword", new ItemGNSSword(RAINBOW));
		rainbow_pickaxe = register("rainbow_pickaxe", new ItemGNSPickaxe(RAINBOW));
		rainbow_axe = register("rainbow_axe", new ItemGNSAxe(ToolMaterial.IRON));
		rainbow_shovel = register("rainbow_shovel", new ItemGNSShovel(RAINBOW));
		rainbow_hoe = register("rainbow_hoe", new ItemGNSHoe(RAINBOW));

		positite_sword = register("positite_sword", new ItemGNSSword(POSITITE));
		positite_pickaxe = register("positite_pickaxe", new ItemGNSPickaxe(POSITITE));
		positite_axe = register("positite_axe", new ItemGNSAxe(ToolMaterial.EMERALD));
		positite_shovel = register("positite_shovel", new ItemGNSShovel(POSITITE));
		positite_hoe = register("positite_hoe", new ItemGNSHoe(POSITITE));

		negatite_sword = register("negatite_sword", new ItemGNSSword(NEGATITE));
		negatite_pickaxe = register("negatite_pickaxe", new ItemGNSPickaxe(NEGATITE));
		negatite_axe = register("negatite_axe", new ItemGNSAxe(ToolMaterial.EMERALD));
		negatite_shovel = register("negatite_shovel", new ItemGNSShovel(NEGATITE));
		negatite_hoe = register("negatite_hoe", new ItemGNSHoe(NEGATITE));

		rainbow_helmet = register("rainbow_helmet", new ItemGNSArmor(0, RAINBOW_ARMOR, "rainbow"));
		rainbow_chestplate = register("rainbow_chestplate", new ItemGNSArmor(1, RAINBOW_ARMOR, "rainbow"));
		rainbow_leggings = register("rainbow_leggings", new ItemGNSArmor(2, RAINBOW_ARMOR, "rainbow"));
		rainbow_boots = register("rainbow_boots", new ItemGNSArmor(3, RAINBOW_ARMOR, "rainbow"));

		positite_helmet = register("positite_helmet", new ItemGNSArmor(0, ArmorMaterial.DIAMOND, "positite"));
		positite_chestplate = register("positite_chestplate", new ItemGNSArmor(1, ArmorMaterial.DIAMOND, "positite"));
		positite_leggings = register("positite_leggings", new ItemGNSArmor(2, ArmorMaterial.DIAMOND, "positite"));
		positite_boots = register("positite_boots", new ItemGNSArmor(3, ArmorMaterial.DIAMOND, "positite"));

		negatite_helmet = register("negatite_helmet", new ItemGNSArmor(0, ArmorMaterial.DIAMOND, "negatite"));
		negatite_chestplate = register("negatite_chestplate", new ItemGNSArmor(1, ArmorMaterial.DIAMOND, "negatite"));
		negatite_leggings = register("negatite_leggings", new ItemGNSArmor(2, ArmorMaterial.DIAMOND, "negatite"));
		negatite_boots = register("negatite_boots", new ItemGNSArmor(3, ArmorMaterial.DIAMOND, "negatite"));

		candy_helmet = register("candy_helmet", new ItemGNSArmor(0, CANDY_ARMOR, "candy"));
		candy_chestplate = register("candy_chestplate", new ItemGNSArmor(1, CANDY_ARMOR, "candy"));
		candy_leggings = register("candy_leggings", new ItemGNSArmor(2, CANDY_ARMOR, "candy"));
		candy_boots = register("candy_boots", new ItemGNSArmor(3, CANDY_ARMOR, "candy"));
	}

	private static Item register(String unlocalizedName, Item item)
	{
		item.setUnlocalizedName(unlocalizedName);

		//item.setRegistryName(VariableConstants.locate(unlocalizedName));
		item.setTextureName(VariableConstants.find(unlocalizedName));
		GameRegistry.registerItem(item, unlocalizedName, VariableConstants.MODID);


		return item;
	}

}