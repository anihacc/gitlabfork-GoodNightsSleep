package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.tools.ItemGNSHoe;
import com.legacy.goodnightsleep.player.PlayerGNS;
import com.legacy.goodnightsleep.player.capability.GNSProvider;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation("goodnightsleep", "gns_player");

	//private final Minecraft mc = FMLClientHandler.instance().getClient();
	
	@SubscribeEvent
	public void PlayerConstructingEvent(AttachCapabilitiesEvent.Entity event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getObject();
			GNSProvider provider = new GNSProvider(new PlayerGNS(player));

			if (PlayerGNS.get(player) == null)
			{
				event.addCapability(PLAYER_LOCATION,  provider);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			PlayerGNS.get((EntityPlayer) event.entityLiving).onUpdate();
		}
	}
	
	@SubscribeEvent
	public void haveDream(PlayerInteractEvent event)
	{					
		if (event.action != Action.RIGHT_CLICK_BLOCK)
		{
			return;
		}
		
		if (event.world.getBlockState(event.pos).getBlock() == BlocksGNS.luxurious_bed)
		{
			if (event.entityLiving instanceof EntityPlayer)
			{
				PlayerGNS.get((EntityPlayer) event.entityLiving).teleportPlayerDream(true);
				// System.out.println("Entering your Dreams");
			}
		}

		if (event.world.getBlockState(event.pos).getBlock() == BlocksGNS.wretched_bed)
		{
			if (event.entityLiving instanceof EntityPlayer)
			{
				PlayerGNS.get((EntityPlayer) event.entityLiving).teleportPlayerNightmare(true);
				// System.out.println("Entering your Nightmares");
			}
		}
		
		World world = event.world;
		BlockPos pos = event.pos;
		IBlockState state = world.getBlockState(pos);
		EntityPlayer player = event.entityPlayer;
		ItemStack stack = event.entityPlayer.getHeldItem();
		
		
		if (stack != null && (stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemGNSHoe) && world.isAirBlock(pos.up()))
		{
			Block block = state.getBlock();

            if (block == BlocksGNS.dream_grass || block == BlocksGNS.dream_dirt)
            {
            	hoeDirt(stack, player, world, pos, BlocksGNS.dream_farmland.getDefaultState(), event);
            }
		}
	}
	
	protected void hoeDirt(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, PlayerInteractEvent event)
    {
        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
        
        player.swingItem();;
    }
	
	protected void plantSeed(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			if (!player.capabilities.isCreativeMode)
				--stack.stackSize;
		}
	}
            
        
}