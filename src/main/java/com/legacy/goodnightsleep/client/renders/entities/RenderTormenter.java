package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.client.models.entities.ModelTormenter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTormenter extends RenderBiped
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/tormenter.png");

    public RenderTormenter()
    {
        super(new ModelTormenter(0.0F, true), 0.5F);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}