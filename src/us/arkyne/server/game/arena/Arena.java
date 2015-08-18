package us.arkyne.server.game.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;

import us.arkyne.server.game.Game;
import us.arkyne.server.loader.Loadable;
import us.arkyne.server.player.ArkynePlayer;
import us.arkyne.server.util.Cuboid;

@SuppressWarnings("deprecation")
public abstract class Arena implements Loadable, ConfigurationSerializable
{
	protected Game game;
	protected Cuboid cuboid;
	
	protected Map<String, Location> spawns = new HashMap<String, Location>();
	
	public Arena(Game game, Cuboid cuboid)
	{
		this.game = game;
		this.cuboid = cuboid;
	}
	
	@Override
	public void onLoad()
	{
		
	}

	@Override
	public void onUnload()
	{
		
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void addSpawn(String team, Location spawn)
	{
		spawns.put(team, spawn);
	}
	
	public Location getSpawn(String team)
	{
		return spawns.get(team);
	}
	
	public abstract Location getSpawn(ArkynePlayer player);
	
	public List<String> getTeams()
	{
		return new ArrayList<String>(spawns.keySet());
	}

	public Cuboid getBounds()
	{
		return cuboid;
	}
	
	public World getWorld()
	{
		return BukkitUtil.toWorld((LocalWorld) cuboid.getWorld());
	}
	
	public void updateWorld(World world)
	{
		for (Location loc : spawns.values())
		{
			loc.setWorld(world);
		}
		
		cuboid.setWorld(BukkitUtil.getLocalWorld(world));
	}
	
	@SuppressWarnings("unchecked")
	public Arena(Map<String, Object> map)
	{
		World world = Bukkit.getWorld(map.get("world").toString());
		
		Location min = ((Vector) map.get("boundry_min")).toLocation(world);
		Location max = ((Vector) map.get("boundry_max")).toLocation(world);
		
		cuboid = new Cuboid(min.getWorld(), BukkitUtil.toVector(min), BukkitUtil.toVector(max));
		spawns = (Map<String, Location>) map.get("spawns");
	}
	
	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("world", getWorld().getName());
		
		map.put("boundry_min", BukkitUtil.toLocation(((BukkitWorld) cuboid.getWorld()).getWorld(), cuboid.getMinimumPoint()).toVector());
		map.put("boundry_max", BukkitUtil.toLocation(((BukkitWorld) cuboid.getWorld()).getWorld(), cuboid.getMaximumPoint()).toVector());
		
		map.put("spawns", spawns);
		
		return map;
	}
}