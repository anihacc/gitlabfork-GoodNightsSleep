package com.legacy.goodnightsleep.client.render.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class GummyBearModel<T extends Entity> extends EntityModel<T>
{

	RendererModel head;

	RendererModel Snout;

	RendererModel body;

	RendererModel rightarm;

	RendererModel leftarm;

	RendererModel leftleg;

	RendererModel rightleg;

	RendererModel leftear;

	RendererModel rightear;

	RendererModel belly;

	public GummyBearModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new RendererModel(this, 0, 0);
		this.head.addBox(-4.0F, -7.0F, -3.0F, 8, 7, 6);
		this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotationPoint(this.head, 0.0F, 0.0F, 0.0F);
		this.Snout = new RendererModel(this, 28, 0);
		this.Snout.addBox(-2.0F, -4.0F, -5.0F, 4, 4, 2);
		this.Snout.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.Snout.setTextureSize(64, 32);
		this.Snout.mirror = true;
		this.setRotationPoint(this.Snout, 0.0F, 0.0F, 0.0F);
		this.body = new RendererModel(this, 0, 13);
		this.body.addBox(-4.0F, -4.0F, -2.0F, 8, 9, 4);
		this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotationPoint(this.body, 0.0F, 0.0F, 0.0F);
		this.rightarm = new RendererModel(this, 24, 13);
		this.rightarm.addBox(-3.0F, -1.0F, -2.0F, 3, 5, 4);
		this.rightarm.setRotationPoint(-4.0F, 13.0F, 0.0F);
		this.rightarm.setTextureSize(64, 32);
		this.rightarm.mirror = true;
		this.setRotationPoint(this.rightarm, 0.0F, 0.0F, 0.0F);
		this.leftarm = new RendererModel(this, 24, 13);
		this.leftarm.addBox(0.0F, -1.0F, -2.0F, 3, 5, 4);
		this.leftarm.setRotationPoint(4.0F, 13.0F, 0.0F);
		this.leftarm.setTextureSize(64, 32);
		this.leftarm.mirror = true;
		this.setRotationPoint(this.leftarm, 0.0F, 0.0F, 0.0F);
		this.leftleg = new RendererModel(this, 24, 22);
		this.leftleg.addBox(-1.0F, 0.0F, -3.0F, 3, 3, 5);
		this.leftleg.setRotationPoint(2.0F, 21.0F, 0.0F);
		this.leftleg.setTextureSize(64, 32);
		this.leftleg.mirror = true;
		this.setRotationPoint(this.leftleg, 0.0F, 0.0F, 0.0F);
		this.rightleg = new RendererModel(this, 24, 22);
		this.rightleg.addBox(-2.0F, 0.0F, -3.0F, 3, 3, 5);
		this.rightleg.setRotationPoint(-2.0F, 21.0F, 0.0F);
		this.rightleg.setTextureSize(64, 32);
		this.rightleg.mirror = true;
		this.setRotationPoint(this.rightleg, 0.0F, 0.0F, 0.0F);
		this.leftear = new RendererModel(this, 0, 26);
		this.leftear.addBox(1.0F, -9.0F, -1.0F, 3, 2, 1);
		this.leftear.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.leftear.setTextureSize(64, 32);
		this.leftear.mirror = true;
		this.setRotationPoint(this.leftear, 0.0F, 0.0F, 0.0F);
		this.rightear = new RendererModel(this, 0, 26);
		this.rightear.addBox(-4.0F, -9.0F, -1.0F, 3, 2, 1);
		this.rightear.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.rightear.setTextureSize(64, 32);
		this.rightear.mirror = true;
		this.setRotationPoint(this.rightear, 0.0F, 0.0F, 0.0F);
		this.belly = new RendererModel(this, 28, 6);
		this.belly.addBox(-3.0F, -1.0F, -3.0F, 6, 5, 1);
		this.belly.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.belly.setTextureSize(64, 32);
		this.belly.mirror = true;
		this.setRotationPoint(this.belly, 0.0F, 0.0F, 0.0F);
	}

	public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		this.head.render(f5);
		this.Snout.render(f5);
		this.body.render(f5);
		this.rightarm.render(f5);
		this.leftarm.render(f5);
		this.leftleg.render(f5);
		this.rightleg.render(f5);
		this.leftear.render(f5);
		this.rightear.render(f5);
		this.belly.render(f5);
	}

	private void setRotationPoint(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(T par7Entity, float par1, float par2, float par3, float par4, float par5, float par6)
	{
		// super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}
