package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.entities.nightmare.EntityHerobrine;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHerobrine extends RenderBiped<EntityHerobrine>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/herobrine.png");

    public RenderHerobrine(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelBiped(0.5F);
                this.modelArmor = new ModelBiped(1.0F);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    protected ResourceLocation getEntityTexture(EntityHerobrine entity)
    {
        return TEXTURE;
    }
}