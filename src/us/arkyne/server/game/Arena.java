package us.arkyne.server.game;

import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import us.arkyne.server.loader.Loadable;

public class Arena implements Loadable, ConfigurationSerializable
{
	protected String mapName = "Heaven & Hell";
	
	@Override
	public void onLoad()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnload()
	{
		// TODO Auto-generated method stub
		
	}
	
	public String getMapName()
	{
		return mapName;
	}
	
	public Arena(Map<String, Object> map)
	{
		
	}

	@Override
	public Map<String, Object> serialize()
	{
		// TODO Auto-generated method stub
		return null;
	}
}