package com.legacy.goodnightsleep.client.render.tile.util;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBedBlock;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GNSBedTileEntityRenderer extends TileEntityRenderer<TileEntity>
{
	private final ModelRenderer field_228843_a_;
	private final ModelRenderer field_228844_c_;
	private final ModelRenderer[] field_228845_d_ = new ModelRenderer[4];

	public GNSBedTileEntityRenderer(TileEntityRendererDispatcher dispatcher)
	{
		super(dispatcher);
		this.field_228843_a_ = new ModelRenderer(64, 64, 0, 0);
		this.field_228843_a_.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.field_228844_c_ = new ModelRenderer(64, 64, 0, 22);
		this.field_228844_c_.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.field_228845_d_[0] = new ModelRenderer(64, 64, 50, 0);
		this.field_228845_d_[1] = new ModelRenderer(64, 64, 50, 6);
		this.field_228845_d_[2] = new ModelRenderer(64, 64, 50, 12);
		this.field_228845_d_[3] = new ModelRenderer(64, 64, 50, 18);
		this.field_228845_d_[0].addBox(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.field_228845_d_[1].addBox(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		this.field_228845_d_[2].addBox(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.field_228845_d_[3].addBox(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		this.field_228845_d_[0].rotateAngleX = ((float) Math.PI / 2F);
		this.field_228845_d_[1].rotateAngleX = ((float) Math.PI / 2F);
		this.field_228845_d_[2].rotateAngleX = ((float) Math.PI / 2F);
		this.field_228845_d_[3].rotateAngleX = ((float) Math.PI / 2F);
		this.field_228845_d_[0].rotateAngleZ = 0.0F;
		this.field_228845_d_[1].rotateAngleZ = ((float) Math.PI / 2F);
		this.field_228845_d_[2].rotateAngleZ = ((float) Math.PI * 1.5F);
		this.field_228845_d_[3].rotateAngleZ = (float) Math.PI;
	}

	@Override
	public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntitySolid(this.getBedTexture()));

		World world = tileEntityIn.getWorld();
		if (world != null)
		{
			BlockState blockstate = tileEntityIn.getBlockState();
			TileEntityMerger.ICallbackWrapper<? extends TileEntityLuxuriousBed> icallbackwrapper = TileEntityMerger.func_226924_a_(GNSTileEntityTypes.LUXURIOUS_BED, GNSBedBlock::func_226863_i_, GNSBedBlock::func_226862_h_, ChestBlock.FACING, blockstate, world, tileEntityIn.getPos(), (p_228846_0_, p_228846_1_) ->
			{
				return false;
			});
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).get(combinedLightIn);
			this.func_228847_a_(matrixStackIn, bufferIn, blockstate.get(GNSBedBlock.PART) == BedPart.HEAD, blockstate.get(GNSBedBlock.HORIZONTAL_FACING), ivertexbuilder, i, combinedOverlayIn, false);
		}
		else
		{
			this.func_228847_a_(matrixStackIn, bufferIn, true, Direction.SOUTH, ivertexbuilder, combinedLightIn, combinedOverlayIn, false);
			this.func_228847_a_(matrixStackIn, bufferIn, false, Direction.SOUTH, ivertexbuilder, combinedLightIn, combinedOverlayIn, true);
		}

	}

	private void func_228847_a_(MatrixStack p_228847_1_, IRenderTypeBuffer p_228847_2_, boolean p_228847_3_, Direction p_228847_4_, IVertexBuilder ivertexbuilder, int p_228847_6_, int p_228847_7_, boolean p_228847_8_)
	{
		this.field_228843_a_.showModel = p_228847_3_;
		this.field_228844_c_.showModel = !p_228847_3_;
		this.field_228845_d_[0].showModel = !p_228847_3_;
		this.field_228845_d_[1].showModel = p_228847_3_;
		this.field_228845_d_[2].showModel = !p_228847_3_;
		this.field_228845_d_[3].showModel = p_228847_3_;
		p_228847_1_.push();
		p_228847_1_.translate(0.0D, 0.5625D, p_228847_8_ ? -1.0D : 0.0D);
		p_228847_1_.rotate(Vector3f.XP.rotationDegrees(90.0F));
		p_228847_1_.translate(0.5D, 0.5D, 0.5D);
		p_228847_1_.rotate(Vector3f.ZP.rotationDegrees(180.0F + p_228847_4_.getHorizontalAngle()));
		p_228847_1_.translate(-0.5D, -0.5D, -0.5D);
		this.field_228843_a_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.field_228844_c_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.field_228845_d_[0].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.field_228845_d_[1].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.field_228845_d_[2].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.field_228845_d_[3].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		p_228847_1_.pop();
	}

	protected ResourceLocation getBedTexture()
	{
		return GoodNightSleep.locate("textures/entities/luxurious_bed.png");
	}
}