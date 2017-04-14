package de.yellowphoenix18.spawnersplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.yellowphoenix18.spawnerplus.SpawnerPlus;
import de.yellowphoenix18.spawnerplus.commands.SpawnerPlusCommand;
import de.yellowphoenix18.spawnerplus.config.MainConfig;
import de.yellowphoenix18.spawnerplus.config.MessagesConfig;
import de.yellowphoenix18.spawnersplus.listener.BlockListener;
import de.yellowphoenix18.spawnersplus.listener.InventoryListener;

public class PluginUtils {
	
	public static void setUp() {
		MainConfig.load();
		MessagesConfig.load();
		SpawnerUtils.load();
		loadListener();
		loadCommands();
	}
	
	public static void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockListener(), SpawnerPlus.m);
		pm.registerEvents(new InventoryListener(), SpawnerPlus.m);
	}
	
	public static void loadCommands() {
		SpawnerPlus.m.getCommand("sp").setExecutor(new SpawnerPlusCommand());
		SpawnerPlus.m.getCommand("spawnerplus").setExecutor(new SpawnerPlusCommand());
	}

}
