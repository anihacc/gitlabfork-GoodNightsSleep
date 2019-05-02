package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.tools.ItemGNSHoe;
import com.legacy.goodnightsleep.player.PlayerGNS;
import com.legacy.goodnightsleep.player.capability.GNSProvider;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation("goodnightsleep", "gns_player");

	//private final Minecraft mc = FMLClientHandler.instance().getClient();
	
	@SubscribeEvent
	public void PlayerConstructingEvent(AttachCapabilitiesEvent<Entity> event)
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
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			PlayerGNS.get((EntityPlayer) event.getEntityLiving()).onUpdate();
		}
	}
	
	@SubscribeEvent
	public void haveDream(RightClickBlock event)
	{					
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlocksGNS.luxurious_bed)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				if (event.getEntityLiving().dimension == 0)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).lastBedPos = event.getEntityLiving().getPosition();
				}
				
				PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayer(true);
				// System.out.println("Entering your Dreams");
			}
		}

		if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlocksGNS.wretched_bed)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				if (event.getEntityLiving().dimension == 0)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).lastBedPos = event.getEntityLiving().getPosition();
				}
				
				PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayerNightmare(true);
				// System.out.println("Entering your Nightmares");
			}
		}
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = event.getItemStack();
		
		
		if ((stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemGNSHoe) && world.isAirBlock(pos.up()))
		{
			Block block = state.getBlock();

            if (block == BlocksGNS.dream_grass || block == BlocksGNS.dream_dirt)
            {
            	hoeDirt(stack, player, world, pos, BlocksGNS.dream_farmland.getDefaultState(), event);
            }
		}
	}
	
	protected void hoeDirt(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, PlayerInteractEvent.RightClickBlock event)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
        
        player.swingArm(event.getHand());
    }
	
	protected void plantSeed(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);
		}
	}
            
        
}