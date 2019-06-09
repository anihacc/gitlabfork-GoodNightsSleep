package com.legacy.goodnightsleep.player;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.player.capability.GNSManager;
import com.legacy.goodnightsleep.world.TeleporterGNS;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerGNS 
{	
	public int timeInPortal, portalCooldown;

	public boolean hasTeleported = false, inPortal = false;

	public EntityPlayer player;
	
	public float prevPortalAnimTime, portalAnimTime;
	
	public Random rand;
	
	public Minecraft mc;

	public PlayerGNS(EntityPlayer player)
	{
		this.player = player;
	}

	public static PlayerGNS get(EntityPlayer player)
	{
		return player.getCapability(GNSManager.PLAYER, null);
	}

	public void onUpdate()
	{
	}
	
	public void teleportPlayerDream(boolean shouldSpawnPortal) 
	{
		if (this.player instanceof EntityPlayerMP)
		{
			int previousDimension = this.player.dimension;
			int transferDimension = previousDimension == GNSConfig.getDreamDimensionID() ? 0 : GNSConfig.getDreamDimensionID();

			if (ForgeHooks.onTravelToDimension(this.player, transferDimension))
			{
				MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
				TeleporterGNS teleporter = new TeleporterGNS(server.worldServerForDimension(transferDimension));

				if (this.player.riddenByEntity != null)
				{
					transferEntity(shouldSpawnPortal, this.player.riddenByEntity, server.worldServerForDimension(previousDimension), server.worldServerForDimension(transferDimension));
					this.player.riddenByEntity.mountEntity(null);
				}

				server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) this.player, transferDimension, teleporter);

				if (this.player.ridingEntity != null)
				{
					transferEntity(shouldSpawnPortal, this.player.ridingEntity, server.worldServerForDimension(previousDimension), server.worldServerForDimension(transferDimension));
				}
			}
		}
	}

	public void teleportPlayerNightmare(boolean shouldSpawnPortal) 
	{
		if (this.player instanceof EntityPlayerMP)
		{
			int previousDimension = this.player.dimension;
			int transferDimension = previousDimension == GNSConfig.getNightmareDimensionID() ? 0 : GNSConfig.getNightmareDimensionID();

			if (ForgeHooks.onTravelToDimension(this.player, transferDimension))
			{
				MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
				TeleporterGNS teleporter = new TeleporterGNS(server.worldServerForDimension(transferDimension));

				if (this.player.riddenByEntity != null)
				{
					transferEntity(shouldSpawnPortal, this.player.riddenByEntity, server.worldServerForDimension(previousDimension), server.worldServerForDimension(transferDimension));
					this.player.riddenByEntity.mountEntity(null);
				}

				server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) this.player, transferDimension, teleporter);

				if (this.player.ridingEntity != null)
				{
					transferEntity(shouldSpawnPortal, this.player.ridingEntity, server.worldServerForDimension(previousDimension), server.worldServerForDimension(transferDimension));
				}
			}
		}
	}

	private static void transferEntity(boolean shouldSpawnPortal, Entity entityIn, WorldServer previousWorldIn, WorldServer newWorldIn)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

		entityIn.dimension = newWorldIn.provider.getDimensionId();
		previousWorldIn.removePlayerEntityDangerously(entityIn);
		entityIn.isDead = false;

		server.getConfigurationManager().transferEntityToWorld(entityIn, previousWorldIn.provider.getDimensionId(), previousWorldIn, newWorldIn, new TeleporterGNS(newWorldIn));
	}
	
	public boolean isInBlock(Block blockID)
	{
		int x = MathHelper.floor_double(this.player.posX);
		int y = MathHelper.floor_double(this.player.posY);
		int z = MathHelper.floor_double(this.player.posZ);
		BlockPos pos = new BlockPos(x, y, z);

		return this.player.worldObj.getBlockState(pos).getBlock() == blockID || this.player.worldObj.getBlockState(pos.up()).getBlock() == blockID || this.player.worldObj.getBlockState(pos.down()).getBlock() == blockID;
	}
	
	public void setInPortal()
	{
		if (this.portalCooldown > 0)
		{
			this.portalCooldown = this.player.getPortalCooldown();
		}
		else
		{
			this.inPortal = true;
		}
	}

}