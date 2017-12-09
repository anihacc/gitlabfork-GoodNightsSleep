package com.legacy.goodnightsleep.client.renders.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.legacy.goodnightsleep.common.GoodNightSleep;
import com.legacy.goodnightsleep.common.blocks.BlocksGNS;

public class BlockRendering 
{

	public static void initialize()
	{
		register(BlocksGNS.tall_dream_grass, "tall_dream_grass");
		register(BlocksGNS.tall_nightmare_grass, "tall_nightmare_grass");
		register(BlocksGNS.dream_grass, "dream_grass");
		register(BlocksGNS.nightmare_grass, "nightmare_grass");
		register(BlocksGNS.dream_dirt, "dream_dirt");
		
		register(BlocksGNS.hope_mushroom_cap, "hope_mushroom");
		register(BlocksGNS.despair_mushroom_cap, "despair_mushroom");
		
		register(BlocksGNS.dream_plank, "dream_plank");
		register(BlocksGNS.white_plank, "white_plank");
		register(BlocksGNS.dead_plank, "dead_plank");
		register(BlocksGNS.blood_plank, "blood_plank");
		
		register(BlocksGNS.dream_log, "dream_log");
		register(BlocksGNS.white_log, "white_log");
		register(BlocksGNS.dead_log, "dead_log");
		register(BlocksGNS.blood_log, "blood_log");
		
		register(BlocksGNS.orange_flower, "orange_flower");
		register(BlocksGNS.cyan_flower, "cyan_flower");
		register(BlocksGNS.dead_flower, "dead_flower");
		register(BlocksGNS.lolipop_bush, "lolipop_bush");
	}

	public static void register(Block block, String model)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(GoodNightSleep.locate(model).toString(), "inventory"));
	}

	public static void register(Block block, int meta, String model)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(GoodNightSleep.locate(model).toString(), "inventory"));
	}

	public static void registerModels(Block block, String... model)
	{
		for (String name : model)
		{
			ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation("goodnightsleep", name));
		}
		}
}