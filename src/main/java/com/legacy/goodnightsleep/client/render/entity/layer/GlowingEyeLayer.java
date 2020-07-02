package com.legacy.goodnightsleep.client.render.entity.layer;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingEyeLayer<T extends LivingEntity, M extends BipedModel<T>> extends AbstractEyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.getEyes(GoodNightSleep.locate("textures/entity/herobrine_eyes.png"));

	public GlowingEyeLayer(IEntityRenderer<T, M> rendererIn)
	{
		super(rendererIn);
	}

	public RenderType getRenderType()
	{
		return RENDER_TYPE;
	}
}