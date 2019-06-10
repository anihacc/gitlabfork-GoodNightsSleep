package com.legacy.goodnightsleep.client.renders.entities;

import org.lwjgl.opengl.GL11;

import com.legacy.goodnightsleep.client.models.entities.ModelBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBabyCreeper extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/baby_creeper.png");

    public RenderBabyCreeper(RenderManager renderManagerIn)
    {
        super(new ModelBabyCreeper(), 0.5F);
        //this.addLayer(new LayerCreeperCharge(this));
    }

    protected void preRenderCallback(Entity entitylivingbaseIn, float partialTickTime)
    {
        float f = ((EntityBabyCreeper)entitylivingbaseIn).getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GL11.glScalef(f2, f3, f2);
    }

    protected int getColorMultiplier(Entity entitylivingbaseIn, float lightBrightness, float partialTickTime)
    {
        float f = ((EntityBabyCreeper)entitylivingbaseIn).getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}