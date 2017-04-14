package de.yellowphoenix18.spawnerplus;

import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.spawnerplus.utils.PluginUtils;

public class SpawnerPlus extends JavaPlugin {
	
	public static SpawnerPlus m;
	
	public void onEnable() {
		m = this;
		PluginUtils.setUp();
	}
	
	public void onDisable() {
		
	}

}
