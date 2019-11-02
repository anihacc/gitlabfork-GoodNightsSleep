package com.legacy.goodnightsleep.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class HerobrineEntity extends MonsterEntity
{

	public HerobrineEntity(EntityType<? extends HerobrineEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.ignoreFrustumCheck = true;
	}

	public HerobrineEntity(World worldIn)
	{
		this(GNSEntityTypes.HEROBRINE, worldIn);
	}

	protected void registerGoals()
	{
		super.registerGoals();

		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 127.0F, 99999.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
	}

	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
	}

	protected SoundEvent getAmbientSound()
	{
		return null;
	}

	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_WITHER_DEATH;
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (!(source instanceof IndirectEntityDamageSource) && source != DamageSource.FIREWORKS)
		{
			boolean flag = super.attackEntityFrom(source, amount);
			if (source.isUnblockable() && this.rand.nextInt(10) != 0)
			{
				this.teleportRandomly();
			}

			return flag;
		}
		else
		{
			for (int i = 0; i < 64; ++i)
			{
				if (this.teleportRandomly())
				{
					return true;
				}
			}

			return false;
		}
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		return this.teleportTo(d0, d1, d2);
	}

	@SuppressWarnings("unused")
	private boolean teleportToEntity(Entity p_70816_1_)
	{
		Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX, this.getBoundingBox().minY + (double) (this.getHeight() / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
		vec3d = vec3d.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
		double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3d.y * 16.0D;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
		return this.teleportTo(d1, d2, d3);
	}

	private boolean teleportTo(double x, double y, double z)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);

		while (blockpos$mutableblockpos.getY() > 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement())
		{
			blockpos$mutableblockpos.move(Direction.DOWN);
		}

		if (!this.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement())
		{
			return false;
		}
		else
		{
			EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0);
			if (MinecraftForge.EVENT_BUS.post(event))
				return false;
			boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			if (flag)
			{
				this.world.playSound((PlayerEntity) null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
				this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
			}

			return flag;
		}
	}

	private boolean shouldAttackPlayer(PlayerEntity player)
	{
		return player.canEntityBeSeen(this);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.75F;
	}

	static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity>
	{
		private final HerobrineEntity enderman;
		private PlayerEntity player;
		private int aggroTime;
		private int teleportTime;
		private final EntityPredicate field_220791_m;
		private final EntityPredicate field_220792_n = (new EntityPredicate()).setLineOfSiteRequired();

		public FindPlayerGoal(HerobrineEntity p_i45842_1_)
		{
			super(p_i45842_1_, PlayerEntity.class, false);
			this.enderman = p_i45842_1_;
			this.field_220791_m = (new EntityPredicate()).setDistance(this.getTargetDistance()).setCustomPredicate((p_220790_1_) ->
			{
				return p_i45842_1_.shouldAttackPlayer((PlayerEntity) p_220790_1_);
			});
		}

		public boolean shouldExecute()
		{
			this.player = this.enderman.world.getClosestPlayer(this.field_220791_m, this.enderman);
			return this.player != null;
		}

		public void startExecuting()
		{
			this.aggroTime = 5;
			this.teleportTime = 0;
		}

		public void resetTask()
		{
			this.player = null;
			super.resetTask();
		}

		public boolean shouldContinueExecuting()
		{
			if (this.player != null)
			{
				if (!this.enderman.shouldAttackPlayer(this.player))
				{
					return false;
				}
				else
				{
					this.enderman.faceEntity(this.player, 10.0F, 10.0F);
					return true;
				}
			}
			else
			{
				return this.nearestTarget != null && this.field_220792_n.canTarget(this.enderman, this.nearestTarget) ? true : super.shouldContinueExecuting();
			}
		}

		public void tick()
		{
			if (this.player != null)
			{
				if (--this.aggroTime <= 0)
				{
					this.nearestTarget = this.player;
					this.player = null;
					super.startExecuting();
				}
			}
			else
			{
				if (this.nearestTarget != null && !this.enderman.isPassenger())
				{
					if (this.enderman.shouldAttackPlayer((PlayerEntity) this.nearestTarget))
					{
						if (this.nearestTarget.getDistanceSq(this.enderman) < 16.0D)
						{
							this.enderman.teleportRandomly();
						}

						this.teleportTime = 0;
					}
					else if (this.nearestTarget.getDistanceSq(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.nearestTarget))
					{
						this.teleportTime = 0;
					}
				}

				super.tick();
			}

		}
	}
}
