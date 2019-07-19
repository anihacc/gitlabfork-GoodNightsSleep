package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.client.render.models.ModelTormenter;
import com.legacy.goodnightsleep.entity.TormenterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderTormenter extends MobRenderer<TormenterEntity, ModelTormenter<TormenterEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("goodnightsleep", "textures/entities/tormenter.png");

    public RenderTormenter(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelTormenter<>(0.0F, true), 0.5F);
        /*LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelTormenter(0.5F, true);
                this.modelArmor = new ModelTormenter(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);*/
    }

    protected ResourceLocation getEntityTexture(TormenterEntity entity)
    {
        return TEXTURE;
    }
}