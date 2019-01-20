package com.legacy.goodnightsleep.client.renders.entities.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.legacy.goodnightsleep.client.renders.entities.RenderBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;

public class BabyCreeperFactory implements IRenderFactory<EntityBabyCreeper>
{

	@Override
	public Render<EntityBabyCreeper> createRenderFor(RenderManager manager)
	{
		return new RenderBabyCreeper(manager);
	}

}