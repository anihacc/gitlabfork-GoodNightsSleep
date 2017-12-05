package com.legacy.goodnightsleep.client.renders.entities.factory;

import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.legacy.goodnightsleep.common.entities.nightmare.EntityGiant;

public class GiantFactory implements IRenderFactory<EntityGiant>
{

	@Override
	public RenderGiantZombie createRenderFor(RenderManager manager)
	{
		return new RenderGiantZombie(manager, 6.0F);
	}

}