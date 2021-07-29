package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
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
			register(event.getRegistry(), GNSBlocks.gnsBlockList.get(i3).getRegistryName().toString().replace(GoodNightSleep.MODID + ":", ""), new BlockItem(GNSBlocks.gnsBlockList.get(i3), (new Item.Properties().tab(GNSCreativeTabs.blocks))));
		}
	}

	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		GNSEntityTypes.init(event);
	}

	@SubscribeEvent
	public static void registerTileEntityTypes(Register<BlockEntityType<?>> event)
	{
		GNSBlockEntityTypes.init(event);
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
}