package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class UnicornEntity extends AbstractHorseEntity
{
	public static final DataParameter<Integer> UNICORN_TYPE = EntityDataManager.<Integer>defineId(UnicornEntity.class, DataSerializers.INT);

	public UnicornEntity(EntityType<? extends UnicornEntity> type, World worldIn)
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
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("unicornType", this.getUnicornType());
	}

	@Override
	public void load(CompoundNBT compound)
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
	public ActionResultType mobInteract(PlayerEntity player, Hand hand)
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
					return ActionResultType.SUCCESS;
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

					return ActionResultType.SUCCESS;
				}

				if (itemstack.interactLivingEntity(player, this, hand).consumesAction())
				{
					return itemstack.interactLivingEntity(player, this, hand);
				}

				if (!this.isTamed())
				{
					this.makeMad();
					return ActionResultType.SUCCESS;
				}

				boolean flag1 = false; // HorseArmorItem.getByItemStack(itemstack) != HorseArmorType.NONE;
				boolean flag2 = !this.isBaby() && !this.isSaddled() && itemstack.getItem() == Items.SADDLE;

				if (flag1 || flag2)
				{
					this.openInventory(player);
					return ActionResultType.SUCCESS;
				}
			}

			if (this.isBaby())
			{
				return super.mobInteract(player, hand);
			}
			else
			{
				this.doPlayerRide(player);
				return ActionResultType.SUCCESS;
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