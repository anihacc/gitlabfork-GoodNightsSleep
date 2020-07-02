package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleportationUtil;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	@SubscribeEvent
	public void onRegisteredDimension(RegisterDimensionsEvent event)
	{
		GNSDimensions.initDimensions();
	}

	@SubscribeEvent
	public void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		PlayerEntity player = event.getPlayer();

		if ((event.getTarget() instanceof ZombieHorseEntity || event.getTarget() instanceof SkeletonHorseEntity) && player.dimension == GNSDimensions.dimensionType(false))
		{
			if (!((AbstractHorseEntity) event.getTarget()).isTame() && player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty())
			{
				event.setResult(Result.ALLOW);
				player.startRiding(event.getTarget());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		PlayerEntity player = event.getPlayer();
		World world = event.getWorld();
		BlockState state = event.getWorld().getBlockState(event.getPos());

		if (!world.isRemote && state.getBlock() instanceof BedBlock && (player.dimension == GNSDimensions.dimensionType(true) || player.dimension == GNSDimensions.dimensionType(false)))
		{
			BlockPos pos = player.getBedLocation(DimensionType.OVERWORLD) != null ? player.getBedLocation(DimensionType.OVERWORLD) : player.world.getSpawnPoint();
			GNSTeleportationUtil.changeDimension(DimensionType.OVERWORLD, player, pos);
		}
	}

	protected static RayTraceResult rayTrace(World worldIn, PlayerEntity player)
	{
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;
		Vec3d vec3d = player.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
		float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();
		Vec3d vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
		return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.SOURCE_ONLY, player));
	}
}