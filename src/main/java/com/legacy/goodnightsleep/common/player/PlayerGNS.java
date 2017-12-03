package com.legacy.goodnightsleep.common.player;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

import com.legacy.goodnightsleep.common.GNSConfig;
import com.legacy.goodnightsleep.common.player.capability.GNSManager;
import com.legacy.goodnightsleep.common.world.TeleporterHeck;

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
		this.prevPortalAnimTime = this.portalAnimTime;
		
		if (this.player.world.isRemote)
		{
			this.prevPortalAnimTime = this.portalAnimTime;

			if (this.inPortal)
			{
				this.portalAnimTime += 0.0125F;
				this.inPortal = false;
			}
			else
			{
				if (this.portalAnimTime > 0.0F)
				{
					this.portalAnimTime -= 0.05F;
				}

				if (this.portalAnimTime < 0.0F)
				{
					this.portalAnimTime = 0.0F;
				}
			}
		}
		else
		{
			if (this.inPortal)
			{
				int limit = this.player.getMaxInPortalTime();

				if (this.timeInPortal++ >= limit)
				{
					this.timeInPortal = limit;
					this.portalCooldown = this.player.getPortalCooldown();
					this.teleportPlayer(true);
				}

				this.inPortal = false;
			}
			else
			{
                if (this.timeInPortal > 0)
                {
                    this.timeInPortal -= 4;
                }

                if (this.timeInPortal < 0)
                {
                    this.timeInPortal = 0;
                }

                if (this.portalCooldown > 0)
                {
                    --this.portalCooldown;
                }
			}
		}
		
	}

	
	//if (this.isInBlock(BlocksMoolands.mooland_portal))
	
	public void teleportPlayer(boolean shouldSpawnPortal) 
	{
		this.player.dismountRidingEntity();
		this.player.removePassengers();

		if (this.player instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) this.player;
			PlayerList scm = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			int transferToID = player.dimension == GNSConfig.getDreamDimensionID() ? 0 : GNSConfig.getDreamDimensionID();

			scm.transferPlayerToDimension(player, transferToID, new TeleporterHeck(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(transferToID)));
		}
	}

	public boolean isInBlock(Block blockID)
	{
		int x = MathHelper.floor(this.player.posX);
		int y = MathHelper.floor(this.player.posY);
		int z = MathHelper.floor(this.player.posZ);
		BlockPos pos = new BlockPos(x, y, z);

		return this.player.world.getBlockState(pos).getBlock() == blockID || this.player.world.getBlockState(pos.up()).getBlock() == blockID || this.player.world.getBlockState(pos.down()).getBlock() == blockID;
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