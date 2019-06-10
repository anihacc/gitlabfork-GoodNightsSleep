package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.client.models.entities.ModelGummyBear;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderGummyBear extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/gummy_bear.png");

    public RenderGummyBear()
    {
        super(new ModelGummyBear(), 0.5F);
        //this.addLayer(new LayerCreeperCharge(this));
    }
    
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}