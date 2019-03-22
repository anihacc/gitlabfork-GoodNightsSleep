package com.legacy.goodnightsleep.client.renders.blocks;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.renders.GNSEntityRenderingRegistry;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockRendering 
{

	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event)
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
		
		register(BlocksGNS.dream_leaves, "dream_leaves");
		register(BlocksGNS.candy_leaves, "candy_leaves");
		register(BlocksGNS.diamond_leaves, "diamond_leaves");

		register(BlocksGNS.dream_log, "dream_log");
		register(BlocksGNS.white_log, "white_log");
		register(BlocksGNS.dead_log, "dead_log");
		register(BlocksGNS.blood_log, "blood_log");
		
		register(BlocksGNS.hope_mushroom, "hope_mushroom_small");
		register(BlocksGNS.despair_mushroom, "despair_mushroom_small");
		register(BlocksGNS.orange_flower, "orange_flower");
		register(BlocksGNS.cyan_flower, "cyan_flower");
		register(BlocksGNS.dead_flower, "dead_flower");
		register(BlocksGNS.lolipop_bush, "lolipop_bush");
		
		register(BlocksGNS.zitrite_ore, "zitrite_ore");
		register(BlocksGNS.necrum_ore, "necrum_ore");
		register(BlocksGNS.candy_ore, "candy_ore");
		register(BlocksGNS.rainbow_ore, "rainbow_ore");
		register(BlocksGNS.positite_ore, "positite_ore");
		register(BlocksGNS.negatite_ore, "negatite_ore");
		
		register(BlocksGNS.dream_fence, "dream_fence");
		register(BlocksGNS.white_fence, "white_fence");
		register(BlocksGNS.dead_fence, "dead_fence");
		register(BlocksGNS.blood_fence, "blood_fence");
		
		register(BlocksGNS.dream_slab, "dream_slab");
		register(BlocksGNS.white_slab, "white_slab");
		register(BlocksGNS.dead_slab, "dead_slab");
		register(BlocksGNS.blood_slab, "blood_slab");
		
		register(BlocksGNS.dream_stairs, "dream_stairs");
		register(BlocksGNS.white_stairs, "white_stairs");
		register(BlocksGNS.dead_stairs, "dead_stairs");
		register(BlocksGNS.blood_stairs, "blood_stairs");
		
		register(BlocksGNS.candy_block, "candy_block");
		register(BlocksGNS.rainbow_block, "rainbow_block");
		register(BlocksGNS.positite_block, "positite_block");
		register(BlocksGNS.necrum_block, "necrum_block");
		register(BlocksGNS.zitrite_block, "zitrite_block");
		register(BlocksGNS.negatite_block, "negatite_block");
		
		GNSEntityRenderingRegistry.registerTileEntities();
	}

	public static void register(Block block, String model)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(VariableConstants.MODID + ":" + model, "inventory"));
	}
}