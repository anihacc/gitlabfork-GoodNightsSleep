package com.legacy.goodnightsleep.entity.dream;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.ItemsGNS;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.HorseArmorType;
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
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityUnicorn extends AbstractHorse
{
	public static final DataParameter<Integer> UNICORN_TYPE = EntityDataManager.<Integer>createKey(EntityUnicorn.class, DataSerializers.VARINT);
	
    public EntityUnicorn(EntityType<?> type, World worldIn)
    {
        super(type, worldIn);
        this.spawnableBlock = BlocksGNS.dream_grass_block;
    }
    
    public EntityUnicorn(World worldIn)
    {
        this(GNSEntityTypes.UNICORN, worldIn);
    }

    @Override
    protected void registerData()
    {
        super.registerData();;
 
        this.dataManager.register(UNICORN_TYPE, Integer.valueOf(this.rand.nextInt(4)));
    }
    
    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);
        compound.putInt("unicornType", this.getUnicornType());
    }

    @Override
    public void read(NBTTagCompound compound)
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

        if (flag && itemstack.getItem() == ItemsGNS.candy_axe)
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

                boolean flag1 = HorseArmorType.getByItemStack(itemstack) != HorseArmorType.NONE;
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