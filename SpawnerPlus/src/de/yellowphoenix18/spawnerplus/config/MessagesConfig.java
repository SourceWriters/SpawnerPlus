package de.yellowphoenix18.spawnerplus.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessagesConfig {
	
	public static File f = new File("plugins/SpawnerPlus", "messages.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static String prefix = "&8[&6Spawner&7Plus&8] &7";
	public static String no_rename = "&cYou can not rename or modify Mob-Spawners";
	public static String no_permission = "&cYoure not allowed to use this command";
	public static String err_entitytypes = "&7Please use one of the following EntityTypes&8: &7%Types%";
	public static String err_player_offline = "&cThe specified Player is offline";
	public static String err_incorrect_use = "&7Please enter &c%Command%";
	public static String err_incorrect_block = "&7Please look on an &c%Block%";
	public static String add_mobspawner_own = "&7An Mob-Spawner with Type &a%Type% &7has been added to your Inventory";
	public static String add_mobspawner_others = "&7An Mob-Spawner with Type &a%Type% &7has been added to &a%Player%&7s Inventory";
	public static String change_mobspawner = "&7The Mob-Spawner-Type has been changed to &a%Type%";
	
	public static void load() {
		prefix = fixColorCodes(setObject("Global.Prefix", prefix));
		no_rename = fixColorCodes(setObject("Error.Rename", no_rename));
		no_permission = fixColorCodes(setObject("Error.No-Permission", no_permission));
		err_entitytypes = fixColorCodes(setObject("Error.Entity-Types", err_entitytypes));
		err_player_offline = fixColorCodes(setObject("Error.Player-Offline", err_player_offline));
		err_incorrect_use = fixColorCodes(setObject("Error.Command.Incorrect-Use", err_incorrect_use));
		err_incorrect_block = fixColorCodes(setObject("Error.Command.Incorrect-Block", err_incorrect_block));
		
		

		add_mobspawner_own = fixColorCodes(setObject("Commands.Give.MobSpawner.Self", add_mobspawner_own));
		add_mobspawner_others = fixColorCodes(setObject("Commands.Give.MobSpawner.Others", add_mobspawner_others));
		change_mobspawner = fixColorCodes(setObject("Commands.Change.MobSpawner", change_mobspawner));
	}
	
	public static String fixColorCodes(String obj) {
		obj = obj.replace("&", "§");
		return obj;
	}
	
	public static String setObject(String path, String obj) {
		if(cfg.contains(path)) {
			return cfg.getString(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
