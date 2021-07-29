package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.GNSRenderRefs;
import com.legacy.goodnightsleep.client.render.entity.layer.GlowingEyeLayer;
import com.legacy.goodnightsleep.client.render.models.HerobrineModel;
import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HerobrineRenderer extends MobRenderer<HerobrineEntity, HerobrineModel<HerobrineEntity>>
{
    private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entity/herobrine.png");

	public HerobrineRenderer(EntityRendererProvider.Context context)
    {
		super(context, new HerobrineModel<>(context.bakeLayer(GNSRenderRefs.HEROBRINE)), 0.5F);
		this.addLayer(new GlowingEyeLayer<>(this));
    }

	@Override
    public ResourceLocation getTextureLocation(HerobrineEntity entity)
    {
        return TEXTURE;
    }
}