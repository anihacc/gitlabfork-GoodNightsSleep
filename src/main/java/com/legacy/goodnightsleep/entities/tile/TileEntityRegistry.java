package com.legacy.goodnightsleep.entities.tile;

import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry
{

	public static void initialization()
	{
		register(TileEntityLuxuriousBed.class, VariableConstants.MODID + ":" + "luxurious_bed");
		register(TileEntityWretchedBed.class, VariableConstants.MODID + ":" + "wretched_bed");
		register(TileEntityStrangeBed.class, VariableConstants.MODID + ":" + "strange_bed");
	}

	@SuppressWarnings("deprecation")
	public static void register(Class<? extends TileEntity> clazz, String name)
	{
		GameRegistry.registerTileEntity(clazz, name);
	}

}