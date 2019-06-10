package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.tools.ItemGNSHoe;
import com.legacy.goodnightsleep.player.PlayerGNS;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation("goodnightsleep", "gns_player");

	//private final Minecraft mc = FMLClientHandler.instance().getClient();
	
	/*@SubscribeEvent
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
	}*/

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			//PlayerGNS.get((EntityPlayer) event.entityLiving).onUpdate();
		}
	}
	
	@SubscribeEvent
	public void haveDream(PlayerInteractEvent event)
	{					
		if (event.action != Action.RIGHT_CLICK_BLOCK)
		{
			return;
		}
		
		/*try
		{
			if (event.world != null && event.world.getBlock(event.x, event.y, event.z) == BlocksGNS.luxurious_bed)
			{
				if (event.entityPlayer != null && event.entityPlayer instanceof EntityPlayer)
				{
					PlayerGNS.get(event.entityPlayer).teleportPlayerDream(true);
					// System.out.println("Entering your Dreams");
				}
			}
			if (event.world != null && event.world.getBlock(event.x, event.y, event.z) == BlocksGNS.wretched_bed)
			{
				if (event.entityPlayer != null && event.entityPlayer instanceof EntityPlayer)
				{
					PlayerGNS.get(event.entityPlayer).teleportPlayerNightmare(true);
					// System.out.println("Entering your Nightmares");
				}
			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}*/
		
		World world = event.world;
		Block state = world.getBlock(event.x, event.y, event.z);
		EntityPlayer player = event.entityPlayer;
		ItemStack stack = event.entityPlayer.getHeldItem();
		
		
		if (stack != null && (stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemGNSHoe) && world.isAirBlock(event.x, event.y + 1, event.z))
		{
			Block block = state;

            if (block == BlocksGNS.dream_grass || block == BlocksGNS.dream_dirt)
            {
            	hoeDirt(stack, player, world, event.x, event.y, event.z, BlocksGNS.dream_farmland, event);
            }
		}
	}
	
	protected void hoeDirt(ItemStack stack, EntityPlayer player, World worldIn, int x, int y, int z, Block state, PlayerInteractEvent event)
    {
        if (!worldIn.isRemote)
        {
            worldIn.setBlock(event.x, event.y, event.z, state, 0, 11);
            stack.damageItem(1, player);
        }
        
        player.swingItem();;
    }
	
	/*protected void plantSeed(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			if (!player.capabilities.isCreativeMode)
				--stack.stackSize;
		}
	}*/
            
        
}