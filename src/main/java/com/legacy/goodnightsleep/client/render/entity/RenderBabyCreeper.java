package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.client.render.models.ModelBabyCreeper;
import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBabyCreeper extends MobRenderer<BabyCreeperEntity, ModelBabyCreeper<BabyCreeperEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/baby_creeper.png");

    public RenderBabyCreeper(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBabyCreeper<>(), 0.5F);
        //this.addLayer(new LayerCreeperCharge(this));
    }

    protected void preRenderCallback(BabyCreeperEntity entitylivingbaseIn, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scalef(f2, f3, f2);
    }

    protected int getColorMultiplier(BabyCreeperEntity entitylivingbaseIn, float lightBrightness, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    protected ResourceLocation getEntityTexture(BabyCreeperEntity entity)
    {
        return TEXTURE;
    }
}