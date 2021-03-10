package com.legacy.goodnightsleep.entity.dream;

import java.util.Collection;

import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BabyCreeperEntity extends MonsterEntity
{
	private static final DataParameter<Integer> STATE = EntityDataManager.defineId(BabyCreeperEntity.class, DataSerializers.INT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.defineId(BabyCreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.defineId(BabyCreeperEntity.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	/**
	 * The amount of time since the creeper was close enough to the player to ignite
	 */
	private int timeSinceIgnited;
	private int fuseTime = 30;
	/** Explosion radius for this creeper. */
	private int droppedSkulls;

	private int explosionRadius = 0;

	public BabyCreeperEntity(EntityType<? extends BabyCreeperEntity> type, World worldIn)
	{
		super(type, worldIn);
		// this.setSize(0.6F, 1.3F);
	}

	public BabyCreeperEntity(World worldIn)
	{
		this(GNSEntityTypes.BABY_CREEPER, worldIn);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new BabyCreeperSwellGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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

	public boolean causeFallDamage(float distance, float damageMultiplier)
	{
		this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + distance * 1.5F);
		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}
		return super.causeFallDamage(distance, damageMultiplier);
	}

	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(STATE, -1);
		this.entityData.define(POWERED, false);
		this.entityData.define(IGNITED, false);
	}

	public void addAdditionalSaveData(CompoundNBT compound)
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
	public void readAdditionalSaveData(CompoundNBT compound)
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
		if (entity != this && entity instanceof CreeperEntity)
		{
			CreeperEntity creeperentity = (CreeperEntity) entity;
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
		return MathHelper.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
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

	protected ActionResultType mobInteract(PlayerEntity player, Hand hand)
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
				return ActionResultType.SUCCESS;
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
			Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			float f = this.getPowered() ? 2.0F : 1.0F;
			this.dead = true;
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
			this.remove();
			this.spawnLingeringCloud();
		}
	}

	private void spawnLingeringCloud()
	{
		Collection<EffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty())
		{
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
			areaeffectcloudentity.setRadius(2.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
			for (EffectInstance effectinstance : collection)
			{
				areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
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
