package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
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
	public static final RegistryKey<Registry<Biome>> GNS_BIOME_KEY = createKey("worldgen/biome");

	@SubscribeEvent
	public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
	{
		GNSSounds.init(event);
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
	{
		GNSBlocks.init(event);
	}

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event)
	{
		GNSItems.init(event);

		for (int i3 = 0; i3 < GNSBlocks.gnsBlockList.size(); ++i3)
		{
			register(event.getRegistry(), GNSBlocks.gnsBlockList.get(i3).getRegistryName().toString().replace(GoodNightSleep.MODID + ":", ""), new BlockItem(GNSBlocks.gnsBlockList.get(i3), (new Item.Properties().group(GNSCreativeTabs.blocks))));
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
		GNSBiomes.init(event);
	}

	@SubscribeEvent
	public static void registerFeatures(Register<Feature<?>> event)
	{
		GNSFeatures.init(event);
	}

	@SubscribeEvent
	public static void onRegisterWorldCarvers(Register<WorldCarver<?>> event)
	{
		GNSFeatures.Carvers.init(event);
	}

	public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(GoodNightSleep.locate(name));
		registry.register(object);
	}

	public static <T> RegistryKey<Registry<T>> createKey(String name)
	{
		return RegistryKey.func_240904_a_(GoodNightSleep.locate(name));
	}
}