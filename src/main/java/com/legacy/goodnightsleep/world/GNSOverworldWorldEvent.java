package com.legacy.goodnightsleep.world;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSOverworldWorldEvent 
{
	@SubscribeEvent
	public void onOverworldDecorated(DecorateBiomeEvent.Pre event)
	{
		/*if (event.world.provider.getDimensionId() == 0)
		{

	        for(int k2 = 0; k2 < 3; k2++)
	        {
                BlockPos blockpos2 = event.pos.add(event.rand.nextInt(16) + 8, event.rand.nextInt(30) + 4, event.rand.nextInt(16) + 8);

	            if (event.rand.nextInt(8) == 0 && event != null && event.world != null)
	            {
			        new WorldGenBush((BlockBush) BlocksGNS.hope_mushroom).generate(event.world, event.rand, blockpos2);
	            }

	            if (event.rand.nextInt(8) == 0)
	            {
			        new WorldGenBush((BlockBush) BlocksGNS.despair_mushroom).generate(event.world, event.rand, blockpos2);
	            }
	        }
		}*/

	}

}