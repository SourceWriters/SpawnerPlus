package de.yellowphoenix18.spawnersplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;

import de.yellowphoenix18.spawnerplus.config.MessagesConfig;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory() instanceof AnvilInventory) {
			if(e.getSlotType() != SlotType.RESULT) {
				if(e.getCurrentItem().hasItemMeta()) {
					if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
						String disp = e.getCurrentItem().getItemMeta().getDisplayName();
						if(disp.contains("§6Mob-Spawner")) {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_rename);
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

}
