package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;

import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBed;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityWretchedBedRenderer extends TileEntityRenderer<TileEntityWretchedBed>
{
    private static final ResourceLocation NIGHTMARE_BED = new ResourceLocation("goodnightsleep", "textures/entities/wretched_bed.png");
    
    private final ModelBed model = new ModelBed();

	
	public void render(TileEntityWretchedBed tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage)
	{
		if (destroyStage >= 0)
		{
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(4.0F, 4.0F, 1.0F);
			GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		else
		{
			this.bindTexture(NIGHTMARE_BED);
		}
		if (tileEntityIn.hasWorld())
		{
			IBlockState iblockstate = tileEntityIn.getBlockState();
			this.func_199343_a(iblockstate.get(BlockBed.PART) == BedPart.HEAD, x, y, z, iblockstate.get(BlockBed.HORIZONTAL_FACING));
		}
		else
		{
			this.func_199343_a(true, x, y, z, EnumFacing.SOUTH);
			this.func_199343_a(false, x, y, z - 1.0D, EnumFacing.SOUTH);
		}
		if (destroyStage >= 0)
		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	private void func_199343_a(boolean p_199343_1_, double p_199343_2_, double p_199343_4_, double p_199343_6_, EnumFacing p_199343_8_)
	{
		this.model.preparePiece(p_199343_1_);
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) p_199343_2_, (float) p_199343_4_ + 0.5625F, (float) p_199343_6_);
		GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.translatef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotatef(180.0F + p_199343_8_.getHorizontalAngle(), 0.0F, 0.0F, 1.0F);
		GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
		GlStateManager.enableRescaleNormal();
		this.model.render();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}
}