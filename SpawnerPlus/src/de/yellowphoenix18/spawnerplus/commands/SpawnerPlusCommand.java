package de.yellowphoenix18.spawnerplus.commands;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.yellowphoenix18.spawnerplus.config.MessagesConfig;
import de.yellowphoenix18.spawnersplus.utils.SpawnerUtils;

public class SpawnerPlusCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission("spawnerplus.help") || p.hasPermission("spawnerplus.*")) {
					p.sendMessage(MessagesConfig.prefix + "§7Please use §c/sp help §7for help");
				} else {
					p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
				}
			} else {
				if(args[0].equalsIgnoreCase("give")) {
					if(args.length == 2) {
						if(p.hasPermission("spawnerplus.give.self") || p.hasPermission("spawnerplus.give.*") || p.hasPermission("spawnerplus.*")) {
							if(SpawnerUtils.getValidType(args[1]) != null) {
								EntityType t = SpawnerUtils.getValidType(args[1]);
								p.getInventory().addItem(SpawnerUtils.getSpawner(t));
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.add_mobspawner_own.replace("%Type%", t.toString()));
							} else {
								String types = "";
								for(String type : SpawnerUtils.entity_types) {
									types = types + " " + type;
								}
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_entitytypes.replace("%Types%", types));
							}	
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
						}
					} else if(args.length == 3) {
						if(p.hasPermission("spawnerplus.give.others") || p.hasPermission("spawnerplus.give.*") || p.hasPermission("spawnerplus.*")) {
							if(SpawnerUtils.getValidType(args[1]) != null) {
								if(Bukkit.getPlayerExact(args[2]) != null) {
									Player k = Bukkit.getPlayerExact(args[2]);
									EntityType t = SpawnerUtils.getValidType(args[1]);
									k.getInventory().addItem(SpawnerUtils.getSpawner(t));
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.add_mobspawner_others.replace("%Type%", t.toString()).replace("%Player%", k.getName()));
								} else {
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_player_offline);
								}
							} else {
								String types = "";
								for(String type : SpawnerUtils.entity_types) {
									types = types + " " + type;
								}
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_entitytypes.replace("%Types%", types));
							}	
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
						}						
					} else if(args.length == 4) {
						if(p.hasPermission("spawnerplus.give.others") || p.hasPermission("spawnerplus.give.*") || p.hasPermission("spawnerplus.*")) {
							if(SpawnerUtils.getValidType(args[1]) != null) {
								if(Bukkit.getPlayerExact(args[2]) != null) {
									int amount = Integer.valueOf(args[3]);
									Player k = Bukkit.getPlayerExact(args[2]);
									EntityType t = SpawnerUtils.getValidType(args[1]);
									ItemStack i = SpawnerUtils.getSpawner(t);
									i.setAmount(amount);
									k.getInventory().addItem(i);
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.add_mobspawner_others.replace("%Type%", t.toString()).replace("%Player%", k.getName()));
								} else {
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_player_offline);
								}
							} else {
								String types = "";
								for(String type : SpawnerUtils.entity_types) {
									types = types + " " + type;
								}
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_entitytypes.replace("%Types%", types));
							}	
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
						}							
					} else {
						if(p.hasPermission("spawnerplus.help") || p.hasPermission("spawnerplus.give.*") || p.hasPermission("spawnerplus.give.self") || p.hasPermission("spawnerplus.give.others") || p.hasPermission("spawnerplus.*")) {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_incorrect_use.replace("%Command%", "/sp give <Type> [<Player>] [<Amount>]"));
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
						}
					}
				} else if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("spawnerplus.help") || p.hasPermission("spawnerplus.*")) {
						p.sendMessage("  §7» §6SpawnerPlus §7«  ");
						p.sendMessage("§6/sp help §8- §7Shows all Commands");
						p.sendMessage("§6/sp give <Type> [<Player>] [<Amount>] §8- §7Gives an specified Mob-Spawner to an Player");
						p.sendMessage("§6/sp changeType <Type> §8- §7Changes the Type of an Mob-Spawner you view on");
						p.sendMessage("  §7» §6SpawnerPlus §7«  ");
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("changeType")) {
					if(p.hasPermission("spawnerplus.change") || p.hasPermission("spawnerplus.*")) {
						if(args.length == 2) {
							if(SpawnerUtils.getValidType(args[1]) != null) {
								@SuppressWarnings("deprecation")
								Block b = p.getTargetBlock((HashSet<Byte>) null, 20);
								if(b.getType() == Material.MOB_SPAWNER) {
									SpawnerUtils.setSpawner(SpawnerUtils.getValidType(args[1]), b);
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.change_mobspawner.replace("%Type%", SpawnerUtils.getValidType(args[1]).toString()));
								} else {
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_incorrect_block.replace("%Block%", "Mob-Spawner"));
								}
							} else {
								String types = "";
								for(String type : SpawnerUtils.entity_types) {
									types = types + " " + type;
								}
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_entitytypes.replace("%Types%", types));
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.err_incorrect_use.replace("%Command%", "/sp changeType <Type>"));
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				}
			}					
		}	
		return true;
	}

}
