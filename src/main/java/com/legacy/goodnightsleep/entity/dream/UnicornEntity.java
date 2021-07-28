package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class UnicornEntity extends AbstractHorse
{
	public static final EntityDataAccessor<Integer> UNICORN_TYPE = SynchedEntityData.<Integer>defineId(UnicornEntity.class, EntityDataSerializers.INT);

	public UnicornEntity(EntityType<? extends UnicornEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(UNICORN_TYPE, this.random.nextInt(4));
	}

	@Override
	public void tick()
	{
		super.tick();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("unicornType", this.getUnicornType());
	}

	@Override
	public void load(CompoundTag compound)
	{
		super.load(compound);
		this.setUnicornType(compound.getInt("unicornType"));
	}

	@Override
	protected void randomizeAttributes()
	{
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		super.getAmbientSound();
		return GNSSounds.ENTITY_UNICORN_IDLE;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		super.getHurtSound(damageSourceIn);
		return GNSSounds.ENTITY_UNICORN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		super.getDeathSound();
		return GNSSounds.ENTITY_UNICORN_DEATH;
	}

	@Override
	protected SoundEvent getAngrySound()
	{
		super.getAngrySound();
		return SoundEvents.HORSE_ANGRY;
	}

	@Override
	public double getPassengersRidingOffset()
	{
		return super.getPassengersRidingOffset() - 0.2D;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);
		boolean flag = !itemstack.isEmpty();

		if (flag && itemstack.getItem() == GNSItems.unicorn_spawn_egg)
		{
			return super.mobInteract(player, hand);
		}
		else
		{
			if (!this.isBaby())
			{
				if (this.isTamed() && player.isCrouching())
				{
					this.openInventory(player);
					return InteractionResult.SUCCESS;
				}

				if (this.isVehicle())
				{
					return super.mobInteract(player, hand);
				}
			}

			if (flag)
			{
				if (this.handleEating(player, itemstack))
				{
					if (!player.isCreative())
					{
						itemstack.shrink(1);
					}

					return InteractionResult.SUCCESS;
				}

				if (itemstack.interactLivingEntity(player, this, hand).consumesAction())
				{
					return itemstack.interactLivingEntity(player, this, hand);
				}

				if (!this.isTamed())
				{
					this.makeMad();
					return InteractionResult.SUCCESS;
				}

				boolean flag1 = false; // HorseArmorItem.getByItemStack(itemstack) != HorseArmorType.NONE;
				boolean flag2 = !this.isBaby() && !this.isSaddled() && itemstack.getItem() == Items.SADDLE;

				if (flag1 || flag2)
				{
					this.openInventory(player);
					return InteractionResult.SUCCESS;
				}
			}

			if (this.isBaby())
			{
				return super.mobInteract(player, hand);
			}
			else
			{
				this.doPlayerRide(player);
				return InteractionResult.SUCCESS;
			}
		}
	}

	public void setUnicornType(int type)
	{
		this.entityData.set(UNICORN_TYPE, type);
	}

	public int getUnicornType()
	{
		return this.entityData.get(UNICORN_TYPE);
	}
}