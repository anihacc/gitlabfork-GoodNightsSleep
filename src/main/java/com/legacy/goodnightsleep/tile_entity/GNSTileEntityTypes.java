package com.legacy.goodnightsleep.tile_entity;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBlocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GoodNightSleep.MODID)
public class GNSTileEntityTypes
{

	public static final TileEntityType<TileEntityLuxuriousBed> LUXURIOUS_BED = null;
	public static final TileEntityType<TileEntityWretchedBed> WRETCHED_BED = null;
	public static final TileEntityType<TileEntityStrangeBed> STRANGE_BED = null;

	public static void init(Register<TileEntityType<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "luxurious_bed", TileEntityType.Builder.create(TileEntityLuxuriousBed::new, GNSBlocks.luxurious_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "wretched_bed", TileEntityType.Builder.create(TileEntityWretchedBed::new, GNSBlocks.wretched_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "strange_bed", TileEntityType.Builder.create(TileEntityStrangeBed::new, GNSBlocks.strange_bed).build(null));
	}
}
