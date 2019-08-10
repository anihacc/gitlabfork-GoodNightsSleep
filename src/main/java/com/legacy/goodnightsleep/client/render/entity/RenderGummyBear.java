package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.client.render.models.GummyBearModel;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderGummyBear extends MobRenderer<GummyBearEntity, GummyBearModel<GummyBearEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/gummy_bear.png");

    public RenderGummyBear(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new GummyBearModel<>(), 0.5F);
    }
    
    protected ResourceLocation getEntityTexture(GummyBearEntity entity)
    {
        return TEXTURE;
    }
}