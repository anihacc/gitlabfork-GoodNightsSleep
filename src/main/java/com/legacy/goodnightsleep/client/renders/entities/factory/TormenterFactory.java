package com.legacy.goodnightsleep.client.renders.entities.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.legacy.goodnightsleep.client.renders.entities.RenderTormenter;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityTormenter;

public class TormenterFactory implements IRenderFactory<EntityTormenter>
{

	@Override
	public Render<EntityTormenter> createRenderFor(RenderManager manager)
	{
		return new RenderTormenter(manager);
	}

}