package com.legacy.goodnightsleep.client.renders.entities.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.legacy.goodnightsleep.client.renders.entities.RenderHerobrine;
import com.legacy.goodnightsleep.entities.nightmare.EntityHerobrine;

public class HerobrineFactory implements IRenderFactory<EntityHerobrine>
{

	@Override
	public Render<EntityHerobrine> createRenderFor(RenderManager manager)
	{
		return new RenderHerobrine(manager);
	}

}