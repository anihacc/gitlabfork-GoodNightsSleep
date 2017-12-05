package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.client.models.entities.ModelGummyBear;
import com.legacy.goodnightsleep.common.entities.dream.EntityGummyBear;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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