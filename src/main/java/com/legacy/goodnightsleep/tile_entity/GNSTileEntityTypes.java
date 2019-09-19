package com.legacy.goodnightsleep.tile_entity;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSTileEntityTypes
{

	public static final TileEntityType<TileEntityLuxuriousBed> LUXURIOUS_BED = null;
	public static final TileEntityType<TileEntityWretchedBed> WRETCHED_BED = null;
	public static final TileEntityType<TileEntityStrangeBed> STRANGE_BED = null;
	
	public static void init(Register<TileEntityType<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "luxurious_bed", TileEntityType.Builder.create(TileEntityLuxuriousBed::new, BlocksGNS.luxurious_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "wretched_bed", TileEntityType.Builder.create(TileEntityWretchedBed::new, BlocksGNS.wretched_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "strange_bed", TileEntityType.Builder.create(TileEntityStrangeBed::new, BlocksGNS.strange_bed).build(null));
	}
}
