package com.legacy.goodnightsleep.client.render.entity.layer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingEyeLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{

	private ResourceLocation GLOW;

	private boolean hasMultiple;

	public GlowingEyeLayer(IEntityRenderer<T, M> renderer, ResourceLocation glowLocation)
	{
		this(renderer, false);

		this.GLOW = glowLocation;
	}

	public GlowingEyeLayer(IEntityRenderer<T, M> renderer, boolean hasMultipleSkins)
	{
		super(renderer);
		this.hasMultiple = hasMultipleSkins;
	}

	public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_)
	{
		if (!this.hasMultiple)
		{
			this.bindTexture(GLOW);
		}

		GlStateManager.enableBlend();
		GlStateManager.disableAlphaTest();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

		if (entityIn.isInvisible())
		{
			GlStateManager.depthMask(false);
		}
		else
		{
			GlStateManager.depthMask(true);
		}

		int i = 61680;
		int j = i % 65536;
		int k = i / 65536;
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
		GlStateManager.color4f(2.0F, 2.0F, 2.0F, 1.0F);
		GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
		gamerenderer.setupFogColor(true);
		this.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
		gamerenderer.setupFogColor(false);
		i = entityIn.getBrightnessForRender();
		j = i % 65536;
		k = i / 65536;
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
		this.func_215334_a(entityIn);
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableAlphaTest();
	}

	public boolean shouldCombineTextures()
	{
		return false;
	}
}