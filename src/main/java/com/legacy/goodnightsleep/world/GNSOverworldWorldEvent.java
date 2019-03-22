package com.legacy.goodnightsleep.world;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSOverworldWorldEvent 
{
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public void onOverworldDecorated(DecorateBiomeEvent.Pre event)
	{
		if (event.getWorld().provider.getDimension() == 0)
		{

	        for(int k2 = 0; k2 < 3; k2++)
	        {
                BlockPos blockpos2 = event.getPos().add(event.getRand().nextInt(16) + 8, event.getRand().nextInt(30) + 4, event.getRand().nextInt(16) + 8);

	            if (event.getRand().nextInt(8) == 0)
	            {
			        new WorldGenBush((BlockBush) BlocksGNS.hope_mushroom).generate(event.getWorld(), event.getRand(), blockpos2);
	            }

	            if (event.getRand().nextInt(8) == 0)
	            {
			        new WorldGenBush((BlockBush) BlocksGNS.despair_mushroom).generate(event.getWorld(), event.getRand(), blockpos2);
	            }
	        }
		}

	}

}