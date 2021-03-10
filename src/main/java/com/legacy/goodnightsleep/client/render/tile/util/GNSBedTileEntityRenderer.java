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
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GNSBedTileEntityRenderer extends TileEntityRenderer<TileEntity>
{
	private final ModelRenderer headPiece;
	private final ModelRenderer footPiece;
	private final ModelRenderer[] legs = new ModelRenderer[4];

	public GNSBedTileEntityRenderer(TileEntityRendererDispatcher dispatcher)
	{
		super(dispatcher);
		this.headPiece = new ModelRenderer(64, 64, 0, 0);
		this.headPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.footPiece = new ModelRenderer(64, 64, 0, 22);
		this.footPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.legs[0] = new ModelRenderer(64, 64, 50, 0);
		this.legs[1] = new ModelRenderer(64, 64, 50, 6);
		this.legs[2] = new ModelRenderer(64, 64, 50, 12);
		this.legs[3] = new ModelRenderer(64, 64, 50, 18);
		this.legs[0].addBox(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.legs[1].addBox(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		this.legs[2].addBox(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.legs[3].addBox(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		this.legs[0].xRot = ((float) Math.PI / 2F);
		this.legs[1].xRot = ((float) Math.PI / 2F);
		this.legs[2].xRot = ((float) Math.PI / 2F);
		this.legs[3].xRot = ((float) Math.PI / 2F);
		this.legs[0].zRot = 0.0F;
		this.legs[1].zRot = ((float) Math.PI / 2F);
		this.legs[2].zRot = ((float) Math.PI * 1.5F);
		this.legs[3].zRot = (float) Math.PI;
	}

	@Override
	public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entitySolid(this.getBedTexture()));

		World world = tileEntityIn.getLevel();
		if (world != null)
		{
			BlockState blockstate = tileEntityIn.getBlockState();
			TileEntityMerger.ICallbackWrapper<? extends TileEntityLuxuriousBed> icallbackwrapper = TileEntityMerger.combineWithNeigbour(GNSTileEntityTypes.LUXURIOUS_BED, GNSBedBlock::getBlockType, GNSBedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, world, tileEntityIn.getBlockPos(), (p_228846_0_, p_228846_1_) ->
			{
				return false;
			});
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).get(combinedLightIn);
			this.renderPiece(matrixStackIn, bufferIn, blockstate.getValue(GNSBedBlock.PART) == BedPart.HEAD, blockstate.getValue(GNSBedBlock.FACING), ivertexbuilder, i, combinedOverlayIn, false);
		}
		else
		{
			this.renderPiece(matrixStackIn, bufferIn, true, Direction.SOUTH, ivertexbuilder, combinedLightIn, combinedOverlayIn, false);
			this.renderPiece(matrixStackIn, bufferIn, false, Direction.SOUTH, ivertexbuilder, combinedLightIn, combinedOverlayIn, true);
		}

	}

	private void renderPiece(MatrixStack p_228847_1_, IRenderTypeBuffer p_228847_2_, boolean p_228847_3_, Direction p_228847_4_, IVertexBuilder ivertexbuilder, int p_228847_6_, int p_228847_7_, boolean p_228847_8_)
	{
		this.headPiece.visible = p_228847_3_;
		this.footPiece.visible = !p_228847_3_;
		this.legs[0].visible = !p_228847_3_;
		this.legs[1].visible = p_228847_3_;
		this.legs[2].visible = !p_228847_3_;
		this.legs[3].visible = p_228847_3_;
		p_228847_1_.pushPose();
		p_228847_1_.translate(0.0D, 0.5625D, p_228847_8_ ? -1.0D : 0.0D);
		p_228847_1_.mulPose(Vector3f.XP.rotationDegrees(90.0F));
		p_228847_1_.translate(0.5D, 0.5D, 0.5D);
		p_228847_1_.mulPose(Vector3f.ZP.rotationDegrees(180.0F + p_228847_4_.toYRot()));
		p_228847_1_.translate(-0.5D, -0.5D, -0.5D);
		this.headPiece.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.footPiece.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[0].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[1].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[2].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[3].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		p_228847_1_.popPose();
	}

	protected ResourceLocation getBedTexture()
	{
		return GoodNightSleep.locate("textures/entities/luxurious_bed.png");
	}
}