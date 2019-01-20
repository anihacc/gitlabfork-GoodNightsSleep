package com.legacy.goodnightsleep.client.renders.items;

import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemRendering 
{

	@SubscribeEvent
	public void initItems(ModelRegistryEvent event)
	{
		register(ItemsGNS.luxurious_bed_item, "luxurious_bed_item");
		register(ItemsGNS.wretched_bed_item, "wretched_bed_item");
		
		//register(ItemsGNS.candy_sword, "candy_sword");
		register(ItemsGNS.candy_pickaxe, "candy_pickaxe");
		register(ItemsGNS.candy_shovel, "candy_shovel");
		register(ItemsGNS.candy_axe, "candy_axe");
		
		//register(ItemsGNS.zitrite_sword, "zitrite_sword");
		register(ItemsGNS.zitrite_pickaxe, "zitrite_pickaxe");
		register(ItemsGNS.zitrite_shovel, "zitrite_shovel");
		register(ItemsGNS.zitrite_axe, "zitrite_axe");
		
		//register(ItemsGNS.necrum_sword, "necrum_sword");
		register(ItemsGNS.necrum_pickaxe, "necrum_pickaxe");
		register(ItemsGNS.necrum_shovel, "necrum_shovel");
		register(ItemsGNS.necrum_axe, "necrum_axe");
		
		register(ItemsGNS.rainbow_sword, "rainbow_sword");
		register(ItemsGNS.rainbow_pickaxe, "rainbow_pickaxe");
		register(ItemsGNS.rainbow_shovel, "rainbow_shovel");
		register(ItemsGNS.rainbow_axe, "rainbow_axe");
		
		//register(ItemsGNS.positite_sword, "positite_sword");
		register(ItemsGNS.positite_pickaxe, "positite_pickaxe");
		register(ItemsGNS.positite_shovel, "positite_shovel");
		register(ItemsGNS.positite_axe, "positite_axe");
		
		//register(ItemsGNS.negatite_sword, "negatite_sword");
		register(ItemsGNS.negatite_pickaxe, "negatite_pickaxe");
		register(ItemsGNS.negatite_shovel, "negatite_shovel");
		register(ItemsGNS.negatite_axe, "negatite_axe");
		
		register(ItemsGNS.candy_helmet, "candy_helmet");
		register(ItemsGNS.candy_chestplate, "candy_chestplate");
		register(ItemsGNS.candy_leggings, "candy_leggings");
		register(ItemsGNS.candy_boots, "candy_boots");
		
		register(ItemsGNS.rainbow_helmet, "rainbow_helmet");
		register(ItemsGNS.rainbow_chestplate, "rainbow_chestplate");
		register(ItemsGNS.rainbow_leggings, "rainbow_leggings");
		register(ItemsGNS.rainbow_boots, "rainbow_boots");
		
		register(ItemsGNS.positite_helmet, "positite_helmet");
		register(ItemsGNS.positite_chestplate, "positite_chestplate");
		register(ItemsGNS.positite_leggings, "positite_leggings");
		register(ItemsGNS.positite_boots, "positite_boots");
		
		register(ItemsGNS.negatite_helmet, "negatite_helmet");
		register(ItemsGNS.negatite_chestplate, "negatite_chestplate");
		register(ItemsGNS.negatite_leggings, "negatite_leggings");
		register(ItemsGNS.negatite_boots, "negatite_boots");
		
		register(ItemsGNS.rainbow_ingot, "rainbow_ingot");
		register(ItemsGNS.zitrite_ingot, "zitrite_ingot");
		register(ItemsGNS.positite_gem, "positite_gem");
		register(ItemsGNS.negatite_gem, "negatite_gem");
		register(ItemsGNS.necrum, "necrum");
	}

	public static void register(Item item, String model)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(VariableConstants.MODID + ":" + model, "inventory"));
	}

}