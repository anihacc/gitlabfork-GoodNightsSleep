package com.legacy.goodnightsleep.player;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.player.capability.GNSManager;
import com.legacy.goodnightsleep.world.TeleporterGNS;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerGNS implements INBT
{
	public EntityPlayer player;
	
	public Random rand;
	
	public Minecraft mc;
	
	public BlockPos lastBedPos;
	
	private int bedX, bedY, bedZ;

	public PlayerGNS(EntityPlayer player)
	{
		this.player = player;
		this.lastBedPos = new BlockPos(bedX, bedY, bedZ);
	}

	public static PlayerGNS get(EntityPlayer player)
	{
		return player.getCapability(GNSManager.PLAYER, null);
	}

	public void onUpdate()
	{	
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("bedX", this.bedX);
		compound.setInteger("bedY", this.bedY);
		compound.setInteger("bedZ", this.bedZ);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.bedX = compound.getInteger("bedX");
		this.bedY = compound.getInteger("bedY");
		this.bedZ = compound.getInteger("bedZ");
	}

	public void teleportPlayer(boolean shouldSpawnPortal) 
	{
		this.player.dismountRidingEntity();
		this.player.removePassengers();

		if (this.player instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) this.player;
			PlayerList scm = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			int transferToID = player.dimension == GNSConfig.getDreamDimensionID() ? 0 : GNSConfig.getDreamDimensionID();

			scm.transferPlayerToDimension(player, transferToID, new TeleporterGNS(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(transferToID)));
		}
	}
	
	public void teleportPlayerNightmare(boolean shouldSpawnPortal) 
	{
		this.player.dismountRidingEntity();
		this.player.removePassengers();

		if (this.player instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) this.player;
			PlayerList scm = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			int transferToID = player.dimension == GNSConfig.getNightmareDimensionID() ? 0 : GNSConfig.getNightmareDimensionID();

			scm.transferPlayerToDimension(player, transferToID, new TeleporterGNS(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(transferToID)));
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
}