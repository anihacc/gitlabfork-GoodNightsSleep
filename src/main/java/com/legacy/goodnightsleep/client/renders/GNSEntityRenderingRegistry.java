package com.legacy.goodnightsleep.client.renders;

import com.legacy.goodnightsleep.client.renders.blocks.TileEntityLuxuriousBedRenderer;
import com.legacy.goodnightsleep.client.renders.blocks.TileEntityWretchedBedRenderer;
import com.legacy.goodnightsleep.client.renders.entities.RenderBabyCreeper;
import com.legacy.goodnightsleep.client.renders.entities.RenderGummyBear;
import com.legacy.goodnightsleep.client.renders.entities.RenderHerobrine;
import com.legacy.goodnightsleep.client.renders.entities.RenderTormenter;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityGummyBear;
import com.legacy.goodnightsleep.entities.nightmare.EntityHerobrine;
import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;
import com.legacy.goodnightsleep.entities.tile.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.entities.tile.TileEntityWretchedBed;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GNSEntityRenderingRegistry
{

	public static void initialize()
	{
		register(EntityTormenter.class, RenderTormenter.class);
		register(EntityBabyCreeper.class, RenderBabyCreeper.class);
		register(EntityHerobrine.class, RenderHerobrine.class);
		register(EntityGummyBear.class, RenderGummyBear.class);
	}
	
	public static void registerTileEntities()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLuxuriousBed.class, new TileEntityLuxuriousBedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWretchedBed.class, new TileEntityWretchedBedRenderer());
	}

	private static <T extends Entity> void register(Class<T> clazz, Class<? extends Render<T>> render)
	{
		RenderingRegistry.registerEntityRenderingHandler(clazz, new IRenderFactory<T>()
		{

			@Override
			public Render<T> createRenderFor(RenderManager manager)
			{
				try
				{
					return render.getConstructor(RenderManager.class).newInstance(manager);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}