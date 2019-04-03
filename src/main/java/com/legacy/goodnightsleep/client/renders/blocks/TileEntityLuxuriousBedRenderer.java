package com.legacy.goodnightsleep.client.renders.blocks;

import com.legacy.goodnightsleep.entities.tile.TileEntityLuxuriousBed;

import net.minecraft.client.model.ModelBed;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityLuxuriousBedRenderer extends TileEntitySpecialRenderer<TileEntityLuxuriousBed>
{
    private static final ResourceLocation DREAM_BED = new ResourceLocation("goodnightsleep", "textures/entities/luxurious_bed.png");
    private static final ResourceLocation NIGHTMARE_BED = new ResourceLocation("goodnightsleep", "textures/entities/wretched_bed.png");
    private ModelBed model = new ModelBed();
    private int version;

    public TileEntityLuxuriousBedRenderer()
    {
        this.version = this.model.getModelVersion();
    }

    public void render(TileEntityLuxuriousBed te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	try 
    	{
	        if (this.version != this.model.getModelVersion())
	        {
	            this.model = new ModelBed();
	            this.version = this.model.getModelVersion();
	        }
	
	        boolean flag = te != null;
	        boolean flag1 = flag ? te.isHeadPiece() : true;
	        int i = flag ? te.getBlockMetadata() & 3 : 0;
	
	        if (destroyStage >= 0)
	        {
	            this.bindTexture(DESTROY_STAGES[destroyStage]);
	            GlStateManager.matrixMode(5890);
	            GlStateManager.pushMatrix();
	            GlStateManager.scale(4.0F, 4.0F, 1.0F);
	            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
	            GlStateManager.matrixMode(5888);
	        }
	        else
	        {
	        	this.bindTexture(DREAM_BED);
	        }
	
	        if (flag)
	        {
	            this.renderPiece(flag1, x, y, z, i, alpha, false);
	        }
	        else
	        {
	            GlStateManager.pushMatrix();
	            this.renderPiece(true, x, y, z, i, alpha, true);
	            //this.renderPiece(false, x, y, z - 1.0D, i, alpha, true);
	            GlStateManager.popMatrix();
	        }
	
	        if (destroyStage >= 0)
	        {
	            GlStateManager.matrixMode(5890);
	            GlStateManager.popMatrix();
	            GlStateManager.matrixMode(5888);
	        }
    	}
    	catch (NullPointerException e)
    	{
    		e.printStackTrace();
    	}
    }

    private void renderPiece(boolean head, double x, double y, double z, int meta, float alpha, boolean item)
    {
    	if (item)
    	{
    		GlStateManager.pushMatrix();
    		this.model.headPiece.showModel = true;
    		this.model.footPiece.showModel = true;
    		
            this.model.legs[0].showModel = true;
            this.model.legs[1].showModel = true;
            this.model.legs[2].showModel = true;
            this.model.legs[3].showModel = true;
            GlStateManager.popMatrix();
    		//this.model.render();
    	}
    	else
    	{
    		this.model.preparePiece(head);
    	}
        
        GlStateManager.pushMatrix();
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;

        if (meta == EnumFacing.NORTH.getHorizontalIndex())
        {
            f = 0.0F;
        }
        else if (meta == EnumFacing.SOUTH.getHorizontalIndex())
        {
            f = 180.0F;
            f1 = 1.0F;
            f2 = 1.0F;
        }
        else if (meta == EnumFacing.WEST.getHorizontalIndex())
        {
            f = -90.0F;
            f2 = 1.0F;
        }
        else if (meta == EnumFacing.EAST.getHorizontalIndex())
        {
            f = 90.0F;
            f1 = 1.0F;
        }

        GlStateManager.translate((float)x + f1, (float)y + 0.5625F, (float)z + f2);
        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        if (item)
        {
        	this.model.headPiece.render(0.0625F);
        	this.model.legs[1].render(0.0625F);
        	this.model.legs[3].render(0.0625F);
        	GlStateManager.translate(0, 1.0F, 0);
    		this.model.footPiece.render(0.0625F);
    		this.model.legs[0].render(0.0625F);
    		this.model.legs[2].render(0.0625F);
        }
        else
        {
        	this.model.render();
        }
        
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        GlStateManager.popMatrix();
    }
}