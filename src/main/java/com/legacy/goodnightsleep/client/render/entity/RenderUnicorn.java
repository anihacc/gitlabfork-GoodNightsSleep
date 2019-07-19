package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.client.render.models.ModelUnicorn;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderUnicorn extends MobRenderer<UnicornEntity, ModelUnicorn<UnicornEntity>>
{	
    public RenderUnicorn(EntityRendererManager manager)
    {
        super(manager, new ModelUnicorn<>(), 0.75F);
    }

	protected ResourceLocation getEntityTexture(UnicornEntity entity)
	{
		String type = entity.getUnicornType() == 1 ? "green" : entity.getUnicornType() == 2 ? "yellow" : entity.getUnicornType() == 3 ? "blue" : "pink";
		return new ResourceLocation("goodnightsleep", "textures/entities/unicorn/unicorn_" + type + ".png");
	}
}