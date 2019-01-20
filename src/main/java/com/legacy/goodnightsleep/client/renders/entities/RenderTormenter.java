package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.client.models.entities.ModelTormenter;
import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTormenter extends RenderBiped<EntityTormenter>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/tormenter.png");

    public RenderTormenter(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelTormenter(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    protected ResourceLocation getEntityTexture(EntityTormenter entity)
    {
        return TEXTURE;
    }
}