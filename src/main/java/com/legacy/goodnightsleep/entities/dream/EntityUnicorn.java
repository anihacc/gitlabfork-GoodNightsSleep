package com.legacy.goodnightsleep.entities.dream;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityUnicorn extends AbstractHorse
{
	public static final DataParameter<Integer> UNICORN_TYPE = EntityDataManager.<Integer>createKey(EntityUnicorn.class, DataSerializers.VARINT);
	
    public EntityUnicorn(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
 
        this.dataManager.register(UNICORN_TYPE, Integer.valueOf(this.rand.nextInt(4)));
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("unicornType", this.getUnicornType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setUnicornType(compound.getInteger("unicornType"));
    }
    
    public static void registerFixesUnicorn(DataFixer fixer)
    {
        AbstractHorse.registerFixesAbstractHorse(fixer, EntityUnicorn.class);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
    }

    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_HORSE;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        if (flag && itemstack.getItem() == Items.SPAWN_EGG)
        {
            return super.processInteract(player, hand);
        }
        else if (!this.isTame())
        {
            return false;
        }
        else if (this.isChild())
        {
            return super.processInteract(player, hand);
        }
        else if (player.isSneaking())
        {
            this.openGUI(player);
            return true;
        }
        else if (this.isBeingRidden())
        {
            return super.processInteract(player, hand);
        }
        else
        {
            if (flag)
            {
                if (!this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE)
                {
                    this.openGUI(player);
                    return true;
                }

                if (itemstack.interactWithEntity(player, this, hand))
                {
                    return true;
                }
            }

            this.mountTo(player);
            return true;
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