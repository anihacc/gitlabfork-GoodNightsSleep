package com.legacy.goodnightsleep;

import com.google.common.base.Preconditions;
import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
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
		//SkiesSounds.soundRegistry = event.getRegistry();
		//SkiesSounds.initialization();
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
		//ItemsGNS.setItemRegistry(event.getRegistry());
		//ItemsGNS.initialization();
		
		for (int i3 = 0; i3 < BlocksGNS.gnsBlockList.size(); ++i3)
		{
			register(event.getRegistry(), BlocksGNS.gnsBlockList.get(i3).getRegistryName().toString().replace("goodnightsleep:", ""), new ItemBlock(BlocksGNS.gnsBlockList.get(i3), (new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))));
		}
	}
	
	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		//event.getRegistry().register(GNSEntityTypes.TORMENTOR);

    	//EntitySpawnPlacementRegistry.register(SkiesEntityTypes.TORMENTOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
   
	}

	@SubscribeEvent
	public static void onRegisterBiomes(Register<Biome> event)
	{
		//register(event.getRegistry(), "good_dream_plains", new BiomeGoodDreamPlains());
		//register(event.getRegistry(), "nightmare_hills", new BiomeNightmareHills());
	}

	@SubscribeEvent
	public static void onRegisterDimensions(RegistryEvent.Register<ModDimension> event)
	{
		/*ModDimension dream = new ModDimension()
		{
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
			{
				return DreamDimension::new;
			}
		};
		register(event.getRegistry(), "everbright", everbright);
		DimensionManager.registerDimension(VariableConstants.locate("good_dream"), dream, new PacketBuffer(Unpooled.buffer()));
		
		ModDimension nightmare = new ModDimension()
		{
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
			{
				return NightmareDimension::new;
			}
		};
		register(event.getRegistry(), "nightmare", nightmare);
		DimensionManager.registerDimension(VariableConstants.locate("nightmare"), nightmare, new PacketBuffer(Unpooled.buffer()));*/
		
	}
	
	public static DimensionType dreamType()
    {
        DimensionType dimension = DimensionType.byName(new ResourceLocation(VariableConstants.MODID, "good_dream"));
        Preconditions.checkNotNull(dimension, "dimension not yet initialized");
        return dimension;
    }

    public static DimensionType nightmareType()
    {
        DimensionType dimension = DimensionType.byName(new ResourceLocation(VariableConstants.MODID, "nightmare"));
        Preconditions.checkNotNull(dimension, "dimension not yet initialized");
        return dimension;
    }
    
	private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(VariableConstants.locate(name));
		registry.register(object);
	}
}