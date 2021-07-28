package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTeleportEvent;

public class HerobrineEntity extends Monster
{
	public HerobrineEntity(EntityType<? extends HerobrineEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.noCulling = true;
	}

	public HerobrineEntity(Level worldIn)
	{
		this(GNSEntityTypes.HEROBRINE, worldIn);
	}

	@Override
	protected void registerGoals()
	{
		super.registerGoals();

		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 127.0F, 99999.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
	}

	public static AttributeSupplier.Builder registerAttributes()
	{
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 64.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.MAX_HEALTH, 40.0D);
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
		Vec3 Vector3d = new Vec3(this.getX() - p_70816_1_.getX(), this.getBoundingBox().minY + (double) (this.getBbHeight() / 2.0F) - p_70816_1_.getY() + (double) p_70816_1_.getEyeHeight(), this.getZ() - p_70816_1_.getZ());
		Vector3d = Vector3d.normalize();
		double d0 = 16.0D;
		double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - Vector3d.x * 16.0D;
		double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - Vector3d.y * 16.0D;
		double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - Vector3d.z * 16.0D;
		return this.teleportToPos(d1, d2, d3);
	}

	private boolean teleportToPos(double x, double y, double z)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);

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
			EntityTeleportEvent.EnderEntity event = new EntityTeleportEvent.EnderEntity(this, x, y, z);
			if (MinecraftForge.EVENT_BUS.post(event))
				return false;
			boolean flag = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			if (flag)
			{
				this.level.playSound((Player) null, this.xo, this.yo, this.zo, SoundEvents.CHORUS_FRUIT_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
				this.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
			}

			return flag;
		}
	}

	private boolean shouldAttackPlayer(Player player)
	{
		//TODO
		return true; //player.canSee(this);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 1.75F;
	}

	/*static class FindPlayerGoal extends NearestAttackableTargetGoal<Player>
	{
		private final HerobrineEntity enderman;
		private Player player;
		private int aggroTime;
		private int teleportTime;
		private final TargetingConditions startAggroTargetConditions;
		private final TargetingConditions continueAggroTargetConditions = (new TargetingConditions()).allowUnseeable();
	
		public FindPlayerGoal(HerobrineEntity p_i45842_1_)
		{
			super(p_i45842_1_, Player.class, false);
			this.enderman = p_i45842_1_;
			this.startAggroTargetConditions = (new TargetingConditions()).range(this.getFollowDistance()).selector((p_220790_1_) ->
			{
				return p_i45842_1_.shouldAttackPlayer((Player) p_220790_1_);
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
					if (this.enderman.shouldAttackPlayer((Player) this.target))
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
	}*/
}
