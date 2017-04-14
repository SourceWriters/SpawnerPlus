package de.yellowphoenix18.spawnersplus.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.yellowphoenix18.spawnerplus.config.MainConfig;
import de.yellowphoenix18.spawnersplus.utils.SpawnerUtils;

public class BlockListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPermission("spawnerplus.break") || p.hasPermission("spawnerplus.*")) {
			Block b = e.getBlock();
			if(b.getType() == Material.MOB_SPAWNER) {		
				ItemStack i = p.getItemInHand();
				ItemMeta imeta = i.getItemMeta();
				if(MainConfig.creative_drop == false && p.getGameMode() == GameMode.CREATIVE) {
					return;
				}
				if(!MainConfig.breakable_items.contains(i.getTypeId() + "") && !MainConfig.breakable_items.contains(i.getTypeId() + ":" + i.getData().getData())) {
					return;
				}
				if(MainConfig.silk_touch_break == true && (!imeta.hasEnchants() || !imeta.getEnchants().containsKey(Enchantment.SILK_TOUCH))) {
					return;
				}
				CreatureSpawner spawner = (CreatureSpawner) b.getState();
				EntityType et = spawner.getSpawnedType();
				
				b.getLocation().getWorld().dropItemNaturally(b.getLocation(), SpawnerUtils.getSpawner(et));
				e.setExpToDrop(0);
			}
		}
		
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e) {
		Player p = e.getPlayer();		
		ItemStack i = p.getItemInHand();
		ItemMeta imeta = i.getItemMeta();
		if(imeta.hasDisplayName()) {
			if(imeta.getDisplayName().contains("§6Mob-Spawner")) {
				EntityType et = EntityType.valueOf(imeta.getLore().get(0).replace("§8Entity §7: §6", ""));
				e.getBlock().setType(Material.MOB_SPAWNER);
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(et);
			}
		}
	}

}
