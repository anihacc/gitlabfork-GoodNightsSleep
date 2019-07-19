package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHerobrine extends MobRenderer<HerobrineEntity, BipedModel<HerobrineEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/herobrine.png");

    public RenderHerobrine(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new BipedModel<>(), 0.5F);
    }

    protected ResourceLocation getEntityTexture(HerobrineEntity entity)
    {
        return TEXTURE;
    }
}