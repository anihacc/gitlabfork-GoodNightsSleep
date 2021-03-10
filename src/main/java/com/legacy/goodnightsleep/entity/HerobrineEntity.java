package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class HerobrineEntity extends MonsterEntity
{
	public HerobrineEntity(EntityType<? extends HerobrineEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.noCulling = true;
	}

	public HerobrineEntity(World worldIn)
	{
		this(GNSEntityTypes.HEROBRINE, worldIn);
	}

	@Override
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

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 64.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.MAX_HEALTH, 40.0D);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GNSSounds.ENTITY_HEROBRINE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GNSSounds.ENTITY_HEROBRINE_DEATH;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (!(source instanceof IndirectEntityDamageSource))
		{
			boolean flag = super.hurt(source, amount);
			if (source.isBypassArmor() && this.random.nextInt(10) != 0)
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
		double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
		double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
		return this.teleportToPos(d0, d1, d2);
	}

	@SuppressWarnings("unused")
	private boolean teleportToEntity(Entity p_70816_1_)
	{
		Vector3d Vector3d = new Vector3d(this.getX() - p_70816_1_.getX(), this.getBoundingBox().minY + (double) (this.getBbHeight() / 2.0F) - p_70816_1_.getY() + (double) p_70816_1_.getEyeHeight(), this.getZ() - p_70816_1_.getZ());
		Vector3d = Vector3d.normalize();
		double d0 = 16.0D;
		double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - Vector3d.x * 16.0D;
		double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - Vector3d.y * 16.0D;
		double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - Vector3d.z * 16.0D;
		return this.teleportToPos(d1, d2, d3);
	}

	private boolean teleportToPos(double x, double y, double z)
	{
		BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable(x, y, z);

		while (blockpos$mutableblockpos.getY() > 0 && !this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion())
		{
			blockpos$mutableblockpos.move(Direction.DOWN);
		}

		if (!this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion())
		{
			return false;
		}
		else
		{
			EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0);
			if (MinecraftForge.EVENT_BUS.post(event))
				return false;
			boolean flag = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			if (flag)
			{
				this.level.playSound((PlayerEntity) null, this.xo, this.yo, this.zo, SoundEvents.CHORUS_FRUIT_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
				this.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
			}

			return flag;
		}
	}

	private boolean shouldAttackPlayer(PlayerEntity player)
	{
		return player.canSee(this);
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
		private final EntityPredicate startAggroTargetConditions;
		private final EntityPredicate continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();

		public FindPlayerGoal(HerobrineEntity p_i45842_1_)
		{
			super(p_i45842_1_, PlayerEntity.class, false);
			this.enderman = p_i45842_1_;
			this.startAggroTargetConditions = (new EntityPredicate()).range(this.getFollowDistance()).selector((p_220790_1_) ->
			{
				return p_i45842_1_.shouldAttackPlayer((PlayerEntity) p_220790_1_);
			});
		}

		public boolean canUse()
		{
			this.player = this.enderman.level.getNearestPlayer(this.startAggroTargetConditions, this.enderman);
			return this.player != null;
		}

		public void start()
		{
			this.aggroTime = 5;
			this.teleportTime = 0;
		}

		public void stop()
		{
			this.player = null;
			super.stop();
		}

		public boolean canContinueToUse()
		{
			if (this.player != null)
			{
				if (!this.enderman.shouldAttackPlayer(this.player))
				{
					return false;
				}
				else
				{
					this.enderman.lookAt(this.player, 10.0F, 10.0F);
					return true;
				}
			}
			else
			{
				return this.target != null && this.continueAggroTargetConditions.test(this.enderman, this.target) ? true : super.canContinueToUse();
			}
		}

		public void tick()
		{
			if (this.player != null)
			{
				if (--this.aggroTime <= 0)
				{
					this.target = this.player;
					this.player = null;
					super.start();
				}
			}
			else
			{
				if (this.target != null && !this.enderman.isPassenger())
				{
					if (this.enderman.shouldAttackPlayer((PlayerEntity) this.target))
					{
						if (this.target.distanceToSqr(this.enderman) < 16.0D)
						{
							this.enderman.teleportRandomly();
						}

						this.teleportTime = 0;
					}
					else if (this.target.distanceToSqr(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.target))
					{
						this.teleportTime = 0;
					}
				}

				super.tick();
			}

		}
	}
}
