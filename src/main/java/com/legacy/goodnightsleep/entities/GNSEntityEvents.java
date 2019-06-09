package com.legacy.goodnightsleep.entities;

import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSEntityEvents
{

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityLiving) spawnEvents((EntityLiving) event.getEntity(), event);
	}

	@SubscribeEvent
	public void onEntityUpdate(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntity() instanceof EntityLiving) entityUpdateEvents((EntityLiving) event.getEntity(), event);
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof EntityLiving) entityDeathEvents((EntityLiving) event.getEntity(), event);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void spawnEvents(EntityLiving entity, Event event)
	{
		if (entity instanceof EntityVillager)
		{
			EntityVillager villager = (EntityVillager)entity;
		
			villager.tasks.addTask(1, new EntityAIAvoidEntity(villager, EntityTormenter.class, 8.0F, 0.6D, 0.6D));
		}
		
		if (entity instanceof EntityGiantZombie)
		{
			EntityGiantZombie giant = (EntityGiantZombie)entity;
		
			giant.tasks.addTask(0, new EntityAISwimming(giant));
			giant.tasks.addTask(2, new EntityAIAttackMelee(giant, 1.0D, false));
			giant.tasks.addTask(7, new EntityAIWanderAvoidWater(giant, 1.0D));
			giant.tasks.addTask(8, new EntityAIWatchClosest(giant, EntityPlayer.class, 8.0F));
			giant.tasks.addTask(8, new EntityAILookIdle(giant));
			giant.targetTasks.addTask(2, new EntityAINearestAttackableTarget(giant, EntityPlayer.class, true));
		}
	}
	
	private void entityUpdateEvents(EntityLiving entity, Event event)
	{
	}
	
	private void entityDeathEvents(EntityLiving entity, Event event)
	{
	}
}
