package com.legacy.goodnightsleep.world.dream;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.world.gen.ChunkGenSettings;

public class DreamGenSettings extends ChunkGenSettings
{

	public DreamGenSettings()
	{
		this.setDefautBlock(BlocksGNS.delusion_stone.getDefaultState());
	}

}