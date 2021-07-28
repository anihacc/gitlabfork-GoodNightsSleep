package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.item.GNSArmorMaterial;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.item.GNSFoods;
import com.legacy.goodnightsleep.item.GNSItemTier;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSItems
{
	private static IForgeRegistry<Item> iItemRegistry;

	public static Item positite, zitrite_ingot, negatite, necrum, rainbow_ingot, candy_ingot;

	public static Item candy_pickaxe, candy_axe, candy_shovel, candy_hoe, candy_sword;

	public static Item necrum_pickaxe, necrum_axe, necrum_shovel, necrum_hoe, necrum_sword;

	public static Item zitrite_pickaxe, zitrite_axe, zitrite_shovel, zitrite_hoe, zitrite_sword;

	public static Item rainbow_pickaxe, rainbow_axe, rainbow_shovel, rainbow_hoe, rainbow_sword;

	public static Item positite_pickaxe, positite_axe, positite_shovel, positite_hoe, positite_sword;

	public static Item negatite_pickaxe, negatite_axe, negatite_shovel, negatite_hoe, negatite_sword;

	public static Item candy_helmet, candy_chestplate, candy_leggings, candy_boots;

	public static Item rainbow_helmet, rainbow_chestplate, rainbow_leggings, rainbow_boots;

	public static Item positite_helmet, positite_chestplate, positite_leggings, positite_boots;

	public static Item zitrite_helmet, zitrite_chestplate, zitrite_leggings, zitrite_boots;

	public static Item negatite_helmet, negatite_chestplate, negatite_leggings, negatite_boots;

	public static Item candy, lolipop, rainbow_berries, luxurious_soup, wretched_soup;

	public static Item powdered_sugar, necrotic_extract, rainbow_seeds;

	public static Item luxurious_bed, wretched_bed, strange_bed;

	public static Item dream_door, white_door, dead_door, blood_door;

	public static Item unicorn_spawn_egg, gummy_bear_spawn_egg, baby_creeper_spawn_egg, tormenter_spawn_egg,
			herobrine_spawn_egg, giant_spawn_egg;

	public static void init(RegistryEvent.Register<Item> event)
	{
		iItemRegistry = event.getRegistry();

		// Block Items
		luxurious_bed = register("luxurious_bed", new BedItem(GNSBlocks.luxurious_bed, (new Item.Properties()).stacksTo(1).tab(GNSCreativeTabs.blocks)));
		wretched_bed = register("wretched_bed", new BedItem(GNSBlocks.wretched_bed, (new Item.Properties()).stacksTo(1).tab(GNSCreativeTabs.blocks)));
		strange_bed = register("strange_bed", new BedItem(GNSBlocks.strange_bed, (new Item.Properties()).stacksTo(1).tab(GNSCreativeTabs.blocks)));

		dream_door = register("dream_door", new DoubleHighBlockItem(GNSBlocks.dream_door, (new Item.Properties()).tab(GNSCreativeTabs.blocks)));
		white_door = register("white_door", new DoubleHighBlockItem(GNSBlocks.white_door, (new Item.Properties()).tab(GNSCreativeTabs.blocks)));
		dead_door = register("dead_door", new DoubleHighBlockItem(GNSBlocks.dead_door, (new Item.Properties()).tab(GNSCreativeTabs.blocks)));
		blood_door = register("blood_door", new DoubleHighBlockItem(GNSBlocks.blood_door, (new Item.Properties()).tab(GNSCreativeTabs.blocks)));

		unicorn_spawn_egg = register("unicorn_spawn_egg", new SpawnEggItem(GNSEntityTypes.UNICORN, 0xffffff, 0xdf8cf8, new Item.Properties().tab(GNSCreativeTabs.items)));
		gummy_bear_spawn_egg = register("gummy_bear_spawn_egg", new SpawnEggItem(GNSEntityTypes.GUMMY_BEAR, 0xffffff, 0xffffff, new Item.Properties()));
		baby_creeper_spawn_egg = register("baby_creeper_spawn_egg", new SpawnEggItem(GNSEntityTypes.BABY_CREEPER, 45079, 16711901, new Item.Properties().tab(GNSCreativeTabs.items)));
		tormenter_spawn_egg = register("tormenter_spawn_egg", new SpawnEggItem(GNSEntityTypes.TORMENTER, 10516796, 5525034, new Item.Properties().tab(GNSCreativeTabs.items)));
		herobrine_spawn_egg = register("herobrine_spawn_egg", new SpawnEggItem(GNSEntityTypes.HEROBRINE, 0xffffff, 0xffffff, new Item.Properties()));
		giant_spawn_egg = register("giant_spawn_egg", new SpawnEggItem(EntityType.GIANT, 1598464, 30652, new Item.Properties().tab(GNSCreativeTabs.items)));

		candy = register("candy", new Item(new Item.Properties().tab(GNSCreativeTabs.items).food(GNSFoods.CANDY)));
		lolipop = register("lolipop", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		candy_ingot = register("candy_ingot", new Item(new Item.Properties()));
		rainbow_ingot = register("rainbow_ingot", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		positite = register("positite_gem", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		necrum = register("necrum", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		zitrite_ingot = register("zitrite_ingot", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		negatite = register("negatite_gem", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		necrotic_extract = register("necrotic_extract", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));
		powdered_sugar = register("powdered_sugar", new Item(new Item.Properties().tab(GNSCreativeTabs.items)));

		rainbow_seeds = register("rainbow_seeds", new ItemNameBlockItem(GNSBlocks.rainbow_berries, new Item.Properties().tab(GNSCreativeTabs.items)));
		rainbow_berries = register("rainbow_berries", new Item(new Item.Properties().tab(GNSCreativeTabs.items).food(GNSFoods.RAINBOW_BERRIES)));

		luxurious_soup = register("luxurious_soup", new Item(new Item.Properties().stacksTo(1).tab(GNSCreativeTabs.items).food(GNSFoods.TELEPORTATION_STEW)));
		wretched_soup = register("wretched_soup", new Item(new Item.Properties().stacksTo(1).tab(GNSCreativeTabs.items).food(GNSFoods.TELEPORTATION_STEW)));

		candy_sword = register("candy_sword", new SwordItem(GNSItemTier.CANDY, 3, -2.4F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		candy_pickaxe = register("candy_pickaxe", new PickaxeItem(GNSItemTier.CANDY, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		candy_axe = register("candy_axe", new AxeItem(GNSItemTier.CANDY, 7.0F, -3.2F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		candy_shovel = register("candy_shovel", new ShovelItem(GNSItemTier.CANDY, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		candy_hoe = register("candy_hoe", new HoeItem(GNSItemTier.CANDY, 0, -2.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		necrum_sword = register("necrum_sword", new SwordItem(GNSItemTier.NECRUM, 3, -2.4F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		necrum_pickaxe = register("necrum_pickaxe", new PickaxeItem(GNSItemTier.NECRUM, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		necrum_axe = register("necrum_axe", new AxeItem(GNSItemTier.NECRUM, 7.0F, -3.2F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		necrum_shovel = register("necrum_shovel", new ShovelItem(GNSItemTier.NECRUM, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		necrum_hoe = register("necrum_hoe", new HoeItem(GNSItemTier.NECRUM, 0, -2.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		zitrite_sword = register("zitrite_sword", new SwordItem(GNSItemTier.ZITRITE, 3, -2.4F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		zitrite_pickaxe = register("zitrite_pickaxe", new PickaxeItem(GNSItemTier.ZITRITE, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		zitrite_axe = register("zitrite_axe", new AxeItem(GNSItemTier.ZITRITE, 6.0F, -3.1F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		zitrite_shovel = register("zitrite_shovel", new ShovelItem(GNSItemTier.ZITRITE, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		zitrite_hoe = register("zitrite_hoe", new HoeItem(GNSItemTier.ZITRITE, -2, -1.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		rainbow_sword = register("rainbow_sword", new SwordItem(GNSItemTier.RAINBOW, 3, -2.4F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		rainbow_pickaxe = register("rainbow_pickaxe", new PickaxeItem(GNSItemTier.RAINBOW, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		rainbow_axe = register("rainbow_axe", new AxeItem(GNSItemTier.RAINBOW, 6.0F, -3.1F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		rainbow_shovel = register("rainbow_shovel", new ShovelItem(GNSItemTier.RAINBOW, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		rainbow_hoe = register("rainbow_hoe", new HoeItem(GNSItemTier.RAINBOW, -2, -1.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		positite_sword = register("positite_sword", new SwordItem(GNSItemTier.POSITITE, 3, -2.4F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		positite_pickaxe = register("positite_pickaxe", new PickaxeItem(GNSItemTier.POSITITE, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		positite_axe = register("positite_axe", new AxeItem(GNSItemTier.POSITITE, 5.0F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		positite_shovel = register("positite_shovel", new ShovelItem(GNSItemTier.POSITITE, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		positite_hoe = register("positite_hoe", new HoeItem(GNSItemTier.POSITITE, -3, 0.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		negatite_sword = register("negatite_sword", new SwordItem(GNSItemTier.NEGATITE, 4, -2.5F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		negatite_pickaxe = register("negatite_pickaxe", new PickaxeItem(GNSItemTier.NEGATITE, 1, -2.8F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		negatite_axe = register("negatite_axe", new AxeItem(GNSItemTier.NEGATITE, 5.0F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		negatite_shovel = register("negatite_shovel", new ShovelItem(GNSItemTier.NEGATITE, 1.5F, -3.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));
		negatite_hoe = register("negatite_hoe", new HoeItem(GNSItemTier.NEGATITE, -4, 0.0F, (new Item.Properties()).tab(GNSCreativeTabs.tools)));

		candy_helmet = register("candy_helmet", new ArmorItem(GNSArmorMaterial.CANDY, EquipmentSlot.HEAD, new Item.Properties().tab(GNSCreativeTabs.armor)));
		candy_chestplate = register("candy_chestplate", new ArmorItem(GNSArmorMaterial.CANDY, EquipmentSlot.CHEST, new Item.Properties().tab(GNSCreativeTabs.armor)));
		candy_leggings = register("candy_leggings", new ArmorItem(GNSArmorMaterial.CANDY, EquipmentSlot.LEGS, new Item.Properties().tab(GNSCreativeTabs.armor)));
		candy_boots = register("candy_boots", new ArmorItem(GNSArmorMaterial.CANDY, EquipmentSlot.FEET, new Item.Properties().tab(GNSCreativeTabs.armor)));

		rainbow_helmet = register("rainbow_helmet", new ArmorItem(GNSArmorMaterial.RAINBOW, EquipmentSlot.HEAD, new Item.Properties().tab(GNSCreativeTabs.armor)));
		rainbow_chestplate = register("rainbow_chestplate", new ArmorItem(GNSArmorMaterial.RAINBOW, EquipmentSlot.CHEST, new Item.Properties().tab(GNSCreativeTabs.armor)));
		rainbow_leggings = register("rainbow_leggings", new ArmorItem(GNSArmorMaterial.RAINBOW, EquipmentSlot.LEGS, new Item.Properties().tab(GNSCreativeTabs.armor)));
		rainbow_boots = register("rainbow_boots", new ArmorItem(GNSArmorMaterial.RAINBOW, EquipmentSlot.FEET, new Item.Properties().tab(GNSCreativeTabs.armor)));

		positite_helmet = register("positite_helmet", new ArmorItem(GNSArmorMaterial.POSITITE, EquipmentSlot.HEAD, new Item.Properties().tab(GNSCreativeTabs.armor)));
		positite_chestplate = register("positite_chestplate", new ArmorItem(GNSArmorMaterial.POSITITE, EquipmentSlot.CHEST, new Item.Properties().tab(GNSCreativeTabs.armor)));
		positite_leggings = register("positite_leggings", new ArmorItem(GNSArmorMaterial.POSITITE, EquipmentSlot.LEGS, new Item.Properties().tab(GNSCreativeTabs.armor)));
		positite_boots = register("positite_boots", new ArmorItem(GNSArmorMaterial.POSITITE, EquipmentSlot.FEET, new Item.Properties().tab(GNSCreativeTabs.armor)));

		zitrite_helmet = register("zitrite_helmet", new ArmorItem(GNSArmorMaterial.ZITRITE, EquipmentSlot.HEAD, new Item.Properties().tab(GNSCreativeTabs.armor)));
		zitrite_chestplate = register("zitrite_chestplate", new ArmorItem(GNSArmorMaterial.ZITRITE, EquipmentSlot.CHEST, new Item.Properties().tab(GNSCreativeTabs.armor)));
		zitrite_leggings = register("zitrite_leggings", new ArmorItem(GNSArmorMaterial.ZITRITE, EquipmentSlot.LEGS, new Item.Properties().tab(GNSCreativeTabs.armor)));
		zitrite_boots = register("zitrite_boots", new ArmorItem(GNSArmorMaterial.ZITRITE, EquipmentSlot.FEET, new Item.Properties().tab(GNSCreativeTabs.armor)));

		negatite_helmet = register("negatite_helmet", new ArmorItem(GNSArmorMaterial.NEGATITE, EquipmentSlot.HEAD, new Item.Properties().tab(GNSCreativeTabs.armor)));
		negatite_chestplate = register("negatite_chestplate", new ArmorItem(GNSArmorMaterial.NEGATITE, EquipmentSlot.CHEST, new Item.Properties().tab(GNSCreativeTabs.armor)));
		negatite_leggings = register("negatite_leggings", new ArmorItem(GNSArmorMaterial.NEGATITE, EquipmentSlot.LEGS, new Item.Properties().tab(GNSCreativeTabs.armor)));
		negatite_boots = register("negatite_boots", new ArmorItem(GNSArmorMaterial.NEGATITE, EquipmentSlot.FEET, new Item.Properties().tab(GNSCreativeTabs.armor)));
	}

	private static Item register(String unlocalizedName, Item item)
	{
		item.setRegistryName(GoodNightSleep.locate(unlocalizedName));
		iItemRegistry.register(item);
		return item;
	}
}