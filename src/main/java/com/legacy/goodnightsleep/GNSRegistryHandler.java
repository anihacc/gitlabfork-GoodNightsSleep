package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.item.ItemsGNS;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.world.dream.BiomeGoodDreamPlains;
import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.BiomeNightmareHills;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
			register(event.getRegistry(), BlocksGNS.gnsBlockList.get(i3).getRegistryName().toString().replace("goodnightsleep:", ""), new ItemBlock(BlocksGNS.gnsBlockList.get(i3), (new Item.Properties().group(GNSCreativeTabs.blocks))));
		}
	}
	
	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		//event.getRegistry().register(GNSEntityTypes.TORMENTOR);

    	//EntitySpawnPlacementRegistry.register(SkiesEntityTypes.TORMENTOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
   
	}
	
	@SubscribeEvent
	public static void onRegisterTileEntityTypes(Register<TileEntityType<?>> event)
	{
		event.getRegistry().register(GNSTileEntityTypes.LUXURIOUS_BED);
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
		/*ModDimension dream = new ModDimension()
		{
			@Override
			public Function<DimensionType, ? extends Dimension> getFactory()
			{
				return GoodDreamDimension::new;
			}
		};*/
		register(event.getRegistry(), "good_dream", new DreamWorldManager());
		register(event.getRegistry(), "nightmare", new NightmareWorldManager());

		//DimensionManager.registerDimension(VariableConstants.locate("good_dream"), dream, new PacketBuffer(Unpooled.buffer()));
		
		/*ModDimension nightmare = new ModDimension()
		{
			@Override
			public Function<DimensionType, ? extends Dimension> getFactory()
			{
				return NightmareDimension::new;
			}
		};
		register(event.getRegistry(), "nightmare", nightmare);
		DimensionManager.registerDimension(VariableConstants.locate("nightmare"), nightmare, new PacketBuffer(Unpooled.buffer()));*/
		
	}

	private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(VariableConstants.locate(name));
		registry.register(object);
	}
}