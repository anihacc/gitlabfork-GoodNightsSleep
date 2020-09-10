package com.legacy.goodnightsleep.client.resource_pack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackFileNotFoundException;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;

public class GNSResourcePack extends ResourcePack
{
	private static ModFile modFile = ModList.get().getModFileById(GoodNightSleep.MODID).getFile();
	private static String subFolder = "";
	private String name = "";

	// based on ModFileResourcePack
	public GNSResourcePack(String subFolderIn, String nameIn)
	{
		super(modFile.getFilePath().toFile());

		GNSResourcePack.subFolder = subFolderIn;
		this.name = nameIn;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	private static String getSubFolders()
	{
		return subFolder;
	}

	@Override
	public void close()
	{
	}

	@Override
	protected InputStream getInputStream(String name) throws IOException
	{
		final Path path = modFile.getLocator().findPath(modFile, getSubFolders() + name);
		if (!Files.exists(path))
			throw new ResourcePackFileNotFoundException(path.toFile(), getSubFolders() + name);

		return Files.newInputStream(path, StandardOpenOption.READ);
	}

	@Override
	protected boolean resourceExists(String name)
	{
		return Files.exists(modFile.getLocator().findPath(modFile, getSubFolders() + name));
	}

	@Override
	public Collection<ResourceLocation> getAllResourceLocations(ResourcePackType type, String resourceNamespace, String pathIn, int maxDepth, Predicate<String> filter)
	{
		try
		{
			Path root = modFile.getLocator().findPath(modFile, getSubFolders() + type.getDirectoryName()).toAbsolutePath();
			Path inputPath = root.getFileSystem().getPath(pathIn);
			return Files.walk(root).map(path -> root.relativize(path.toAbsolutePath())).filter(path -> path.getNameCount() > 1 && path.getNameCount() - 1 <= maxDepth).filter(path -> !path.toString().endsWith(".mcmeta")).filter(path -> path.subpath(1, path.getNameCount()).startsWith(inputPath)).filter(path -> filter.test(path.getFileName().toString())).map(path -> new ResourceLocation(path.getName(0).toString(), Joiner.on('/').join(path.subpath(1, Math.min(maxDepth, path.getNameCount()))))).collect(Collectors.toList());
		}
		catch (IOException e)
		{
			return Collections.emptyList();
		}
	}

	@Override
	public Set<String> getResourceNamespaces(ResourcePackType type)
	{
		try
		{
			Path root = modFile.getLocator().findPath(modFile, getSubFolders() + type.getDirectoryName()).toAbsolutePath();
			return Files.walk(root, 1).map(path -> root.relativize(path.toAbsolutePath())).filter(path -> path.getNameCount() > 0).map(p -> p.toString().replaceAll("/$", "")).filter(s -> !s.isEmpty()).collect(Collectors.toSet());
		}
		catch (IOException e)
		{
			return Collections.emptySet();
		}
	}

}
