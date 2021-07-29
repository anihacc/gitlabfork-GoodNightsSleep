package com.legacy.goodnightsleep.client.render.tile;

import com.legacy.goodnightsleep.client.render.GNSRenderRefs;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSBlockEntityTypes;
import com.legacy.goodnightsleep.tile_entity.DreamBedBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GNSBedBlockEntityRenderer implements BlockEntityRenderer<DreamBedBlockEntity>
{
	private final ModelPart headRoot;
	private final ModelPart footRoot;

	public GNSBedBlockEntityRenderer(BlockEntityRendererProvider.Context context)
	{
		this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
		this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
	}

	@Override
	public void render(DreamBedBlockEntity bed, float p_112206_, PoseStack stack, MultiBufferSource buffer, int p_112209_, int p_112210_)
	{
		Material material = bed.getBlockState().getBlock() == GNSBlocks.luxurious_bed ? GNSRenderRefs.LUXURIOUS_BED_MATERIAL : bed.getBlockState().getBlock() == GNSBlocks.wretched_bed ? GNSRenderRefs.WRETCHED_BED_MATERIAL : GNSRenderRefs.STRANGE_BED_MATERIAL;
		Level level = bed.getLevel();

		if (level != null)
		{
			BlockState blockstate = bed.getBlockState();
			DoubleBlockCombiner.NeighborCombineResult<? extends DreamBedBlockEntity> neighborcombineresult = DoubleBlockCombiner.combineWithNeigbour(GNSBlockEntityTypes.DREAM_BED, BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, level, bed.getBlockPos(), (p_112202_, p_112203_) -> false);
			int i = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).get(p_112209_);
			this.renderPiece(stack, buffer, blockstate.getValue(BedBlock.PART) == BedPart.HEAD ? this.headRoot : this.footRoot, blockstate.getValue(BedBlock.FACING), material, i, p_112210_, false);
		}
		else
		{
			this.renderPiece(stack, buffer, this.headRoot, Direction.SOUTH, material, p_112209_, p_112210_, false);
			this.renderPiece(stack, buffer, this.footRoot, Direction.SOUTH, material, p_112209_, p_112210_, true);
		}

	}

	private void renderPiece(PoseStack stack, MultiBufferSource buffer, ModelPart model, Direction dir, Material material, int p_173547_, int p_173548_, boolean p_173549_)
	{
		stack.pushPose();
		stack.translate(0.0D, 0.5625D, p_173549_ ? -1.0D : 0.0D);
		stack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
		stack.translate(0.5D, 0.5D, 0.5D);
		stack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + dir.toYRot()));
		stack.translate(-0.5D, -0.5D, -0.5D);
		VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entitySolid);
		model.render(stack, vertexconsumer, p_173547_, p_173548_);
		stack.popPose();
	}
}