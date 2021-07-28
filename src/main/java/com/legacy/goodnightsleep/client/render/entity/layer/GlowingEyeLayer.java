package com.legacy.goodnightsleep.client.render.entity.layer;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingEyeLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends EyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(GoodNightSleep.locate("textures/entity/herobrine_eyes.png"));

	public GlowingEyeLayer(RenderLayerParent<T, M> rendererIn)
	{
		super(rendererIn);
	}

	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}