package com.legacy.goodnightsleep.client.renders.entities.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.legacy.goodnightsleep.client.renders.entities.RenderBabyCreeper;
import com.legacy.goodnightsleep.client.renders.entities.RenderGummyBear;
import com.legacy.goodnightsleep.common.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.common.entities.dream.EntityGummyBear;

public class GummyBearFactory implements IRenderFactory<EntityGummyBear>
{

	@Override
	public Render<EntityGummyBear> createRenderFor(RenderManager manager)
	{
		return new RenderGummyBear(manager);
	}

}