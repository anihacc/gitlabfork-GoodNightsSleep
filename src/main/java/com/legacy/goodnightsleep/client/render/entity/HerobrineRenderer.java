package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.entity.layer.GlowingEyeLayer;
import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HerobrineRenderer<T extends HerobrineEntity, M extends HumanoidModel<T>> extends HumanoidMobRenderer<T, M>
{
    private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entity/herobrine.png");

	@SuppressWarnings("unchecked")
	public HerobrineRenderer(EntityRenderDispatcher renderManagerIn)
    {
        super(renderManagerIn, (M) new HumanoidModel<HerobrineEntity>(0.0F), 0.5F);
		this.addLayer(new GlowingEyeLayer<>(this));
    }

    public ResourceLocation getTextureLocation(HerobrineEntity entity)
    {
        return TEXTURE;
    }
}