package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.GNSRenderRefs;
import com.legacy.goodnightsleep.client.render.models.BabyCreeperModel;
import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabyCreeperRenderer extends MobRenderer<BabyCreeperEntity, BabyCreeperModel<BabyCreeperEntity>>
{
	private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entity/baby_creeper.png");

	public BabyCreeperRenderer(EntityRendererProvider.Context context)
	{
		super(context, new BabyCreeperModel<>(context.bakeLayer(GNSRenderRefs.BABY_CREEPER)), 0.5F);
	}

	protected void scale(BabyCreeperEntity entitylivingbaseIn, PoseStack matrix, float partialTickTime)
	{
		float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		matrix.scale(f2, f3, f2);
	}

	protected int getColorMultiplier(BabyCreeperEntity entitylivingbaseIn, float lightBrightness, float partialTickTime)
	{
		float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

		if ((int) (f * 10.0F) % 2 == 0)
		{
			return 0;
		}
		else
		{
			int i = (int) (f * 0.2F * 255.0F);
			i = Mth.clamp(i, 0, 255);
			return i << 24 | 822083583;
		}
	}

	@Override
	public ResourceLocation getTextureLocation(BabyCreeperEntity entity)
	{
		return TEXTURE;
	}
}