package com.vauff.icparkour.core;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

public class Util
{
	public static HashMap<String, String> arenaData = new HashMap<String, String>();
	public static HashMap<String, String> checkpointData = new HashMap<String, String>();
	private static Main main;

	public Util(Main plugin)
	{
		main = plugin;
	}

	public static boolean doesArenaExist(String arenaname)
	{
		boolean arenafound = false;

		for (String configarenaname : main.getConfig().getConfigurationSection("arenas").getKeys(false))
		{
			if (arenaname.equalsIgnoreCase(configarenaname))
			{
				arenafound = true;
			}
		}

		return arenafound;
	}

	public static boolean doesCheckPointExist(String arenaname, String checkpointname)
	{
		boolean checkpointfound = false;

		for (String configcheckpointname : main.getConfig().getConfigurationSection("arenas").getConfigurationSection(arenaname).getConfigurationSection("checkpoints").getKeys(false))
		{
			if (checkpointname.equalsIgnoreCase(configcheckpointname))
			{
				checkpointfound = true;
			}
		}

		return checkpointfound;
	}

	public static Location getCenter(Location loc)
	{
		return new Location(loc.getWorld(), getRelativeCoord(loc.getBlockX()), getRelativeCoord(loc.getBlockY()), getRelativeCoord(loc.getBlockZ()));
	}

	private static double getRelativeCoord(int i)
	{
		double d = i;
		d = d < 0 ? d - .5 : d + .5;
		return d;
	}
	
	public static Location getIntelligentSpawnLocation(Location initialTry)
	{
		Location oneUp = new Location(initialTry.getWorld(), initialTry.getX(), initialTry.getY() + 1, initialTry.getZ());
		
		if(initialTry.getBlock().getType() != Material.AIR)
		{
			return getIntelligentSpawnLocation(oneUp);
		}
		else
		{
			if(oneUp.getBlock().getType() != Material.AIR)
			{
				return getIntelligentSpawnLocation(new Location(oneUp.getWorld(), oneUp.getX(), oneUp.getY() + 1, oneUp.getZ()));
			}
			else
			{
				return initialTry;
			}
		}
	}
}
