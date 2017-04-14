package de.yellowphoenix18.spawnerplus.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MainConfig {
	
	public static File f = new File("plugins/SpawnerPlus", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static boolean creative_drop = false;
	public static boolean silk_touch_break = false;
	public static List<String> breakable_items = new ArrayList<String>();
	
	public static void load() {
		breakable_items.add("257");
		breakable_items.add("274");
		breakable_items.add("278");
		breakable_items.add("285");
		
		creative_drop = setObject("Configuration.Creative-Drop", creative_drop);
		silk_touch_break = setObject("Configuration.Silk-Touch-Break", silk_touch_break);
		breakable_items = setObject("Configuration.Breakable-Items", breakable_items);
	}
	
	public static boolean setObject(String path, boolean obj) {
		if(cfg.contains(path)) {
			return cfg.getBoolean(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static List<String> setObject(String path, List<String> obj) {
		if(cfg.contains(path)) {
			return cfg.getStringList(path);
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
