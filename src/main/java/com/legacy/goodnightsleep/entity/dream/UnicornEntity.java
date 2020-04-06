package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.GNSItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class UnicornEntity extends AbstractHorseEntity
{
	public static final DataParameter<Integer> UNICORN_TYPE = EntityDataManager.<Integer>createKey(UnicornEntity.class, DataSerializers.VARINT);

	public UnicornEntity(EntityType<? extends UnicornEntity> type, World worldIn)
	{
		super(type, worldIn);
		// this.spawnableBlock = BlocksGNS.dream_grass_block;
	}

	public UnicornEntity(World worldIn)
	{
		this(GNSEntityTypes.UNICORN, worldIn);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		;

		this.dataManager.register(UNICORN_TYPE, Integer.valueOf(this.rand.nextInt(4)));
	}

	@Override
	public void tick()
	{
		super.tick();
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putInt("unicornType", this.getUnicornType());
	}

	@Override
	public void read(CompoundNBT compound)
	{
		super.read(compound);
		this.setUnicornType(compound.getInt("unicornType"));
	}

	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
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
		return SoundEvents.ENTITY_HORSE_ANGRY;
	}

	@Override
	public double getMountedYOffset()
	{
		return super.getMountedYOffset() - 0.07D;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		boolean flag = !itemstack.isEmpty();

		if (flag && itemstack.getItem() == GNSItems.unicorn_spawn_egg)
		{
			return super.processInteract(player, hand);
		}
		else
		{
			if (!this.isChild())
			{
				if (this.isTame() && player.isSneaking())
				{
					this.openGUI(player);
					return true;
				}

				if (this.isBeingRidden())
				{
					return super.processInteract(player, hand);
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

					return true;
				}

				if (itemstack.interactWithEntity(player, this, hand))
				{
					return true;
				}

				if (!this.isTame())
				{
					this.makeMad();
					return true;
				}

				boolean flag1 = false; // HorseArmorItem.getByItemStack(itemstack) != HorseArmorType.NONE;
				boolean flag2 = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE;

				if (flag1 || flag2)
				{
					this.openGUI(player);
					return true;
				}
			}

			if (this.isChild())
			{
				return super.processInteract(player, hand);
			}
			else
			{
				this.mountTo(player);
				return true;
			}
		}
	}

	public void setUnicornType(int type)
	{
		this.dataManager.set(UNICORN_TYPE, type);
	}

	public int getUnicornType()
	{
		return this.dataManager.get(UNICORN_TYPE);
	}
}