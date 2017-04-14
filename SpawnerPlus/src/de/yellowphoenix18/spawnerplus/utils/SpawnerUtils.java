package de.yellowphoenix18.spawnerplus.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerUtils {
	
	public static List<String> entity_types = new ArrayList<String>();
	
	public static void load() {
		for(EntityType et : EntityType.values()) {
			entity_types.add(et.toString());
		}
		entity_types.remove("LIGHTNING");
		entity_types.remove("DROPPED_ITEM");
		entity_types.remove("EXPERIENCE_ORB");
		entity_types.remove("LEASH_HITCH");
		entity_types.remove("PAINTING");
		entity_types.remove("ARROW");
		entity_types.remove("SNOWBALL");
		entity_types.remove("FIREBALL");
		entity_types.remove("SMALL_FIREBALL");
		entity_types.remove("ENDER_PEARL");
		entity_types.remove("ENDER_SIGNAL");
		entity_types.remove("THROWN_EXP_BOTTLE");
		entity_types.remove("ITEM_FRAME");
		entity_types.remove("WITHER_SKULL");
		entity_types.remove("PRIMED_TNT");
		entity_types.remove("FALLING_BLOCK");
		entity_types.remove("FIREWORK");
		entity_types.remove("MINECART_COMMAND");
		entity_types.remove("BOAT");
		entity_types.remove("MINECART");
		entity_types.remove("MINECART_CHEST");
		entity_types.remove("MINECART_FURNACE");
		entity_types.remove("MINECART_TNT");
		entity_types.remove("MINECART_HOPPER");
		entity_types.remove("MINECART_MOB_SPAWNER");
		entity_types.remove("ENDER_CRYSTAL");
		entity_types.remove("SPLASH_POTION");
		entity_types.remove("EGG");
		entity_types.remove("FISHING_HOOK");
		entity_types.remove("WEATHER");
		entity_types.remove("PLAYER");
		entity_types.remove("COMPLEX_PART");
		entity_types.remove("UNKNOWN");
	}
	
	public static EntityType getValidType(String et) {
		et = et.toUpperCase();
		if(entity_types.contains(et)) {
			return EntityType.valueOf(et);
		}
		return null;	
	}
	
	public static EntityType getSpawnerType(String type) {
		type = type.toUpperCase();		
		EntityType e = EntityType.valueOf(type);		
		return e;		
	}
	
	public static void setSpawner(EntityType e, Block b) {
		b.setType(Material.AIR);
		b.setType(Material.MOB_SPAWNER);
		CreatureSpawner cs = (CreatureSpawner) b.getState();
		cs.setSpawnedType(e);
	}
	
	public static ItemStack getSpawner(EntityType e) {
		ItemStack i = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName("§6Mob-Spawner");
		List<String> lore = new ArrayList<String>();
		lore.add("§8Entity §7: §6" + e.toString());
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		i.setAmount(1);
		return i;
	}

}
