package com.legacy.goodnightsleep.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.legacy.goodnightsleep.client.renders.entities.factory.TormenterFactory;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityTormenter;

public class GNSEntityRenderingRegistry 
{

	public static void initialize()
	{
		register(EntityTormenter.class, new TormenterFactory());
	}

	public static <ENTITY extends Entity> void register(Class<ENTITY> classes, IRenderFactory<? super ENTITY> factory)
	{
		RenderingRegistry.registerEntityRenderingHandler(classes, factory);
	}

}