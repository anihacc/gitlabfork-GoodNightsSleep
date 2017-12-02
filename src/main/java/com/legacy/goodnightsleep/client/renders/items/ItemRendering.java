package com.legacy.goodnightsleep.client.renders.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.legacy.goodnightsleep.common.GoodNightSleep;

public class ItemRendering 
{

	public static void initialize()
	{
		//register(ItemsSkies.charoite_fragment, "charoite_fragment");
	}

	public static void register(Item item, int meta, String model)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(GoodNightSleep.locate(model).toString(), "inventory"));
	}

	public static void register(Item item, String model)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(GoodNightSleep.locate(model).toString(), "inventory"));
	}

	public static void registerMetaModel(Item item, String... model)
	{
		for (String name : model)
		{
			ModelBakery.registerItemVariants(item, new ResourceLocation("goodnightsleep", name));
		}
	}

}