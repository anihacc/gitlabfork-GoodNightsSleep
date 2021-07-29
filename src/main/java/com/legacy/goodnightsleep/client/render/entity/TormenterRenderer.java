package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.GNSRenderRefs;
import com.legacy.goodnightsleep.client.render.models.TormenterModel;
import com.legacy.goodnightsleep.entity.TormenterEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TormenterRenderer extends MobRenderer<TormenterEntity, TormenterModel<TormenterEntity>>
{
	private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entity/tormenter.png");

	public TormenterRenderer(EntityRendererProvider.Context context)
	{
		// super(renderManagerIn, new TormenterModel<>(), 0.5F);
		super(context, new TormenterModel<>(context.bakeLayer(GNSRenderRefs.TORMENTER)), 0.5F);
		//this.addLayer(new HumanoidArmorLayer<>(this, new TormenterModel(p_174463_.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_INNER_ARMOR)), new TormenterModel(p_174463_.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_OUTER_ARMOR))));
	}

	public ResourceLocation getTextureLocation(TormenterEntity entity)
	{
		return TEXTURE;
	}
}