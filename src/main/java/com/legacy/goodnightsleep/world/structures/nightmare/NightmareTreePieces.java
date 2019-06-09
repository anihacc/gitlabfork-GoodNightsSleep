package com.legacy.goodnightsleep.world.structures.nightmare;

import java.util.Random;

import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class NightmareTreePieces 
{
	WorldServer worldServer;
	MinecraftServer minecraftServer;
	TemplateManager templateManager;
	public Template tree1;
	public Template tree2;
	public Template tree3;
	public Template tree4;
	
	public NightmareTreePieces(World world, Random rand)
	{
		this.init(world, rand);
	}
	
	private void init(World world, Random rand)
	{
		worldServer = (WorldServer) world;
		minecraftServer = world.getMinecraftServer();
		templateManager = worldServer.getStructureTemplateManager();
		
		tree1 = register("dead_tree1");
		tree2 = register("dead_tree2");
		tree3 = register("dead_tree3");
		tree4 = register("dead_tree4");
	}
	
	private Template register(String file)
	{
		return templateManager.getTemplate(minecraftServer, new ResourceLocation(VariableConstants.MODID + ":trees/" + file));
	}
}
