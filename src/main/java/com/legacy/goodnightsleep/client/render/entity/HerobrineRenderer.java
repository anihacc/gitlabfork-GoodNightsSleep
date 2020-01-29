package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.entity.layer.GlowingEyeLayer;
import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HerobrineRenderer<T extends HerobrineEntity, M extends BipedModel<T>> extends BipedRenderer<T, M>
{
    private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entities/herobrine.png");

	@SuppressWarnings("unchecked")
	public HerobrineRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, (M) new BipedModel<HerobrineEntity>(0.0F), 0.5F);
		this.addLayer(new GlowingEyeLayer<>(this));
    }

    public ResourceLocation getEntityTexture(HerobrineEntity entity)
    {
        return TEXTURE;
    }
}