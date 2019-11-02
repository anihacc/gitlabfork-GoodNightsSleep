package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.entity.layer.GlowingEyeLayer;
import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HerobrineRenderer extends MobRenderer<HerobrineEntity, BipedModel<HerobrineEntity>>
{
    private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entities/herobrine.png");

    public HerobrineRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new BipedModel<>(), 0.5F);
		this.addLayer(new GlowingEyeLayer<>(this, GoodNightSleep.locate("textures/entities/herobrine_eyes.png")));
    }

    protected ResourceLocation getEntityTexture(HerobrineEntity entity)
    {
        return TEXTURE;
    }
}