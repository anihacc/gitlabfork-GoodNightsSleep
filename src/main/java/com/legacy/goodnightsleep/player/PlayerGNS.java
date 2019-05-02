package com.legacy.goodnightsleep.player;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.player.capability.GNSManager;
import com.legacy.goodnightsleep.world.TeleporterGNS;

public class PlayerGNS 
{
	public EntityPlayer player;
	
	public Random rand;
	
	public Minecraft mc;
	
	public BlockPos lastBedPos;

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