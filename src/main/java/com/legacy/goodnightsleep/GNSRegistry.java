package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.item.ItemsGNS;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.dream.GoodDreamPlainsBiome;
import com.legacy.goodnightsleep.world.nightmare.NightmareHillsBiome;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = GoodNightSleep.MODID, bus = Bus.MOD)
public class GNSRegistry
{

	@SubscribeEvent
	public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
	{
		GNSSounds.soundRegistry = event.getRegistry();
		GNSSounds.initialization();
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
	{
		BlocksGNS.setBlockRegistry(event.getRegistry());
		BlocksGNS.initialization();
	}

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event)
	{
		ItemsGNS.setItemRegistry(event.getRegistry());
		ItemsGNS.initialization();
		
		for (int i3 = 0; i3 < BlocksGNS.gnsBlockList.size(); ++i3)
		{
			register(event.getRegistry(), BlocksGNS.gnsBlockList.get(i3).getRegistryName().toString().replace("goodnightsleep:", ""), new BlockItem(BlocksGNS.gnsBlockList.get(i3), (new Item.Properties().group(GNSCreativeTabs.blocks))));
		}
	}
	
	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		GNSEntityTypes.init(event);
	}
	
	@SubscribeEvent
	public static void registerTileEntityTypes(Register<TileEntityType<?>> event)
	{
		GNSTileEntityTypes.init(event);
	}

	@SubscribeEvent
	public static void onRegisterBiomes(Register<Biome> event)
	{
		register(event.getRegistry(), "good_dream_plains", new GoodDreamPlainsBiome());
		register(event.getRegistry(), "nightmare_hills", new NightmareHillsBiome());
	}

	@SubscribeEvent
	public static void onRegisterModDimensions(RegistryEvent.Register<ModDimension> event)
	{
		System.out.println("initModDimensions");
		GNSDimensions.initModDimensions(event.getRegistry());
	}

	public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(GoodNightSleep.locate(name));
		registry.register(object);
	}
}