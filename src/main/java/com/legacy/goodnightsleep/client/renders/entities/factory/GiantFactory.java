package com.legacy.goodnightsleep.client.renders.entities.factory;

import com.legacy.goodnightsleep.entities.nightmare.EntityGiant;

import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GiantFactory implements IRenderFactory<EntityGiant>
{

	@Override
	public RenderGiantZombie createRenderFor(RenderManager manager)
	{
		return new RenderGiantZombie(manager, 6.0F);
	}

}