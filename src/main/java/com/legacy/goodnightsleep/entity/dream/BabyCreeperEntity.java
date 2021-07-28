package com.legacy.goodnightsleep.entity.dream;

import java.util.Collection;

import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BabyCreeperEntity extends Monster
{
	private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BabyCreeperEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> POWERED = SynchedEntityData.defineId(BabyCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IGNITED = SynchedEntityData.defineId(BabyCreeperEntity.class, EntityDataSerializers.BOOLEAN);

	private int lastActiveTime;
	/**
	 * The amount of time since the creeper was close enough to the player to ignite
	 */
	private int timeSinceIgnited;
	private int fuseTime = 30;
	/** Explosion radius for this creeper. */
	private int droppedSkulls;

	private int explosionRadius = 0;

	public BabyCreeperEntity(EntityType<? extends BabyCreeperEntity> type, Level worldIn)
	{
		super(type, worldIn);
		// this.setSize(0.6F, 1.3F);
	}

	public BabyCreeperEntity(Level worldIn)
	{
		this(GNSEntityTypes.BABY_CREEPER, worldIn);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new BabyCreeperSwellGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	/*protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}*/

	public int getMaxFallDistance()
	{
		return this.getTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
	}

	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
	{
		this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + distance * 1.5F);
		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}
		return super.causeFallDamage(distance, damageMultiplier, source);
	}

	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(STATE, -1);
		this.entityData.define(POWERED, false);
		this.entityData.define(IGNITED, false);
	}

	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		if (this.entityData.get(POWERED))
		{
			compound.putBoolean("powered", true);
		}
		compound.putShort("Fuse", (short) this.fuseTime);
		compound.putByte("ExplosionRadius", (byte) this.explosionRadius);
		compound.putBoolean("ignited", this.hasIgnited());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.entityData.set(POWERED, compound.getBoolean("powered"));
		if (compound.contains("Fuse", 99))
		{
			this.fuseTime = compound.getShort("Fuse");
		}
		if (compound.contains("ExplosionRadius", 99))
		{
			this.explosionRadius = compound.getByte("ExplosionRadius");
		}
		if (compound.getBoolean("ignited"))
		{
			this.ignite();
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick()
	{
		if (this.isAlive())
		{
			this.lastActiveTime = this.timeSinceIgnited;
			if (this.hasIgnited())
			{
				this.setCreeperState(1);
			}
			int i = this.getCreeperState();
			if (i > 0 && this.timeSinceIgnited == 0)
			{
				this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
			}
			this.timeSinceIgnited += i;
			if (this.timeSinceIgnited < 0)
			{
				this.timeSinceIgnited = 0;
			}
			if (this.timeSinceIgnited >= this.fuseTime)
			{
				this.timeSinceIgnited = this.fuseTime;
				this.explode();
			}
		}
		super.tick();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.CREEPER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.CREEPER_DEATH;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn)
	{
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		Entity entity = source.getEntity();
		if (entity != this && entity instanceof Creeper)
		{
			Creeper creeperentity = (Creeper) entity;
			if (creeperentity.canDropMobsSkull())
			{
				creeperentity.increaseDroppedSkulls();
				this.spawnAtLocation(Items.CREEPER_HEAD);
			}
		}
	}

	public boolean doHurtTarget(Entity entityIn)
	{
		return true;
	}

	/**
	 * Returns true if the creeper is powered by a lightning bolt.
	 */
	public boolean getPowered()
	{
		return this.entityData.get(POWERED);
	}

	/**
	 * Params: (Float)Render tick. Returns the intensity of the creeper's flash when
	 * it is ignited.
	 */
	@OnlyIn(Dist.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks)
	{
		return Mth.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
	}

	/**
	 * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
	 */
	public int getCreeperState()
	{
		return this.entityData.get(STATE);
	}

	/**
	 * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
	 */
	public void setCreeperState(int state)
	{
		this.entityData.set(STATE, state);
	}

	/**
	 * Called when a lightning bolt hits the entity.
	 */
	/*public void onStruckByLightning(LightningBoltEntity lightningBolt)
	{
		super.onStruckByLightning(lightningBolt);
		this.dataManager.set(POWERED, true);
	}*/

	protected InteractionResult mobInteract(Player player, InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() == Items.FLINT_AND_STEEL)
		{
			this.level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
			player.swing(hand);
			if (!this.level.isClientSide)
			{
				this.ignite();
				itemstack.hurtAndBreak(1, player, (p_213625_1_) ->
				{
					p_213625_1_.broadcastBreakEvent(hand);
				});
				return InteractionResult.SUCCESS;
			}
		}
		return super.mobInteract(player, hand);
	}

	/**
	 * Creates an explosion as determined by this creeper's power and explosion
	 * radius.
	 */
	private void explode()
	{
		if (!this.level.isClientSide)
		{
			Explosion.BlockInteraction explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
			float f = this.getPowered() ? 2.0F : 1.0F;
			this.dead = true;
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
			this.discard();
			this.spawnLingeringCloud();
		}
	}

	private void spawnLingeringCloud()
	{
		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty())
		{
			AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
			areaeffectcloudentity.setRadius(2.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
			for (MobEffectInstance effectinstance : collection)
			{
				areaeffectcloudentity.addEffect(new MobEffectInstance(effectinstance));
			}
			this.level.addFreshEntity(areaeffectcloudentity);
		}
	}

	public boolean hasIgnited()
	{
		return this.entityData.get(IGNITED);
	}

	public void ignite()
	{
		this.entityData.set(IGNITED, true);
	}

	/**
	 * Returns true if an entity is able to drop its skull due to being blown up by
	 * this creeper.
	 * 
	 * Does not test if this creeper is charged; the caller must do that. However,
	 * does test the doMobLoot gamerule.
	 */
	public boolean ableToCauseSkullDrop()
	{
		return this.getPowered() && this.droppedSkulls < 1;
	}

	public void incrementDroppedSkulls()
	{
		++this.droppedSkulls;
	}

}
