package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.client.render.models.ModelGummyBear;
import com.legacy.goodnightsleep.entity.dream.EntityGummyBear;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderGummyBear extends RenderLiving<EntityGummyBear>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/gummy_bear.png");

    public RenderGummyBear(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGummyBear(), 0.5F);
        //this.addLayer(new LayerCreeperCharge(this));
    }
    
    protected ResourceLocation getEntityTexture(EntityGummyBear entity)
    {
        return TEXTURE;
    }
}