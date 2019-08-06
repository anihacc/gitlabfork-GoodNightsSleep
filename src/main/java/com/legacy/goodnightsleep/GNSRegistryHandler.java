package com.legacy.goodnightsleep;

import java.util.function.BiFunction;

import com.google.common.base.Preconditions;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.GNSCreativeTabs;
import com.legacy.goodnightsleep.item.ItemsGNS;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.world.dream.BiomeGoodDreamPlains;
import com.legacy.goodnightsleep.world.dream.GoodDreamDimension;
import com.legacy.goodnightsleep.world.nightmare.BiomeNightmareHills;
import com.legacy.goodnightsleep.world.nightmare.NightmareDimension;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.DimensionManager;
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
		ModDimension dreamDim = new ModDimension()
		{
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
			{
				return GoodDreamDimension::new;
			}
		};
		register(event.getRegistry(), "good_dream", dreamDim);
		DimensionManager.registerDimension(VariableConstants.locate("good_dream"), dreamDim, new PacketBuffer(Unpooled.buffer()), true);
		
		// Everdawn
		ModDimension nightmareDim = new ModDimension()
		{
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
			{
				return NightmareDimension::new;
			}
		};
		register(event.getRegistry(), "nightmare", nightmareDim);
		DimensionManager.registerDimension(VariableConstants.locate("nightmare"), nightmareDim, new PacketBuffer(Unpooled.buffer()), true);
	}

	public static DimensionType dreamType()
    {
        DimensionType dimension = DimensionType.byName(new ResourceLocation(VariableConstants.MODID, "good_dream"));
        Preconditions.checkNotNull(dimension, "Dimension hasn't been initialized.");
        return dimension;
    }

    public static DimensionType nightmareType()
    {
        DimensionType dimension = DimensionType.byName(new ResourceLocation(VariableConstants.MODID, "nightmare"));
        Preconditions.checkNotNull(dimension, "Dimension hasn't been initialized.");
        return dimension;
    }

	private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(VariableConstants.locate(name));
		registry.register(object);
	}
}