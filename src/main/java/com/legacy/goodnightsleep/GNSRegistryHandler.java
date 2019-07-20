package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.item.ItemsGNS;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.world.dream.BiomeGoodDreamPlains;
import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.BiomeNightmareHills;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.block.Block;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = "goodnightsleep", bus = Bus.MOD)
public class GNSRegistryHandler
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
		event.getRegistry().register(GNSEntityTypes.UNICORN);
		event.getRegistry().register(GNSEntityTypes.GUMMY_BEAR);
		event.getRegistry().register(GNSEntityTypes.BABY_CREEPER);
		event.getRegistry().register(GNSEntityTypes.TORMENTER);
		event.getRegistry().register(GNSEntityTypes.HEROBRINE);
		event.getRegistry().register(GNSEntityTypes.SPAWNER_ENTITY);

    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.TORMENTER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.HEROBRINE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.BABY_CREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);

    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.UNICORN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);
    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.GUMMY_BEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);
    	EntitySpawnPlacementRegistry.register(GNSEntityTypes.SPAWNER_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::otherSpawnConditions);

    	//EntitySpawnPlacementRegistry.register(EntityType.COW, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);
    	//EntitySpawnPlacementRegistry.register(EntityType.CHICKEN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);
    	//EntitySpawnPlacementRegistry.register(EntityType.PIG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);
    	//EntitySpawnPlacementRegistry.register(EntityType.SHEEP, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::dreamAnimalSpawnConditions);

	}
	
	@SubscribeEvent
	public static void onRegisterTileEntityTypes(Register<TileEntityType<?>> event)
	{
		event.getRegistry().register(GNSTileEntityTypes.LUXURIOUS_BED);
		event.getRegistry().register(GNSTileEntityTypes.WRETCHED_BED);
		event.getRegistry().register(GNSTileEntityTypes.STRANGE_BED);
	}

	@SubscribeEvent
	public static void onRegisterBiomes(Register<Biome> event)
	{
		register(event.getRegistry(), "good_dream_plains", new BiomeGoodDreamPlains());
		register(event.getRegistry(), "nightmare_hills", new BiomeNightmareHills());
	}

	@SubscribeEvent
	public static void onRegisterDimensions(RegistryEvent.Register<ModDimension> event)
	{
		register(event.getRegistry(), "good_dream", new DreamWorldManager());
		register(event.getRegistry(), "nightmare", new NightmareWorldManager());
	}

	private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(VariableConstants.locate(name));
		registry.register(object);
	}
}