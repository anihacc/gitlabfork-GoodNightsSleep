package com.legacy.goodnightsleep.client.renders.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHerobrine extends RenderBiped
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/herobrine.png");

    public RenderHerobrine(RenderManager renderManagerIn)
    {
        super(new ModelBiped(), 0.5F);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}