package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Armor;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Weapon;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.PlayerInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.Coin;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 11/26/13 Time: 12:53 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DeathEvents implements Listener {
	public DeathEvents(Plugin pl) {
		Bukkit.getPluginManager().registerEvents(this, pl);
	}

	private Map<String, List<ItemStack>> deathItems = new HashMap<>();
	public static Map<String, Integer> playerLevels = new HashMap<>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player p = (Player) event.getEntity();
			if (!p.hasPermission("deathmessage.show")) {
				if (event instanceof PlayerDeathEvent) {
					PlayerDeathEvent e = (PlayerDeathEvent) event;
					e.setDeathMessage(null);
				}
			}
			playerLevels.put(p.getName(), p.getLevel());

			if (event.getEntity().getKiller() == null) {
				List<ItemStack> items = new ArrayList<>();
				for (ItemStack iS : event.getDrops()) {
					if (iS != null && !iS.getType().equals(Material.AIR)) {
						int i = (int) (Math.random() * 100);
						if (i > 10) {
							items.add(iS);
						} else {
							p.sendMessage(ChatUtil.color("&f[&4Death Effect&f] &bYou have lost " + iS.getAmount() + " " + iS.getType().name().toLowerCase().replace("_", "") + " from dying."));
						}
					}
				}

				CustomPlayer cP = CustomPlayer.getCP(p.getName());
				CoinChange.add(p, -((int) (cP.getGoldPoints() * .1)), true);
				SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
				p.sendMessage(ChatUtil.color("&f[&4Death Effect&f] &bYou have lost " + (int) (cP.getGoldPoints() * 0.1) + " Coins due to death."));
				deathItems.put(p.getName(), items);
				new BukkitRunnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						for (ItemStack iS : deathItems.get(p.getName())) {
							if (iS == null || iS.getType().equals(Material.AIR)) {
								continue;
							}
							if (iS.hasItemMeta() && iS.getItemMeta().hasDisplayName() && iS.getItemMeta().getDisplayName().equals(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))) {
								continue;
							}
							p.getInventory().addItem(iS);
						}
						p.updateInventory();
						deathItems.remove(p.getName());
						Coin.update(p);
					}
				}.runTaskLater(UltimateSurvival.getInstance(), 10);
				event.getDrops().clear();
				return;
			}
		}
		event.setDroppedExp(0);
		if (event.getEntity().getKiller() != null) {
			Player p = event.getEntity().getKiller();
			CustomPlayer cP = CustomPlayer.getCP(p.getName());

			String name = event.getEntity().getType().name();
			if (event.getEntity() instanceof Player) {
				final Player dead = (Player) event.getEntity();
				List<ItemStack> items = new ArrayList<>();
				for (ItemStack iS : event.getDrops()) {
					if (iS != null && !iS.getType().equals(Material.AIR)) {
						int i = (int) (Math.random() * 100);
						if (i > 10) {
							items.add(iS);
						} else {
							dead.sendMessage(ChatUtil.color("&f[&4Death Effect&f] &bYou have lost " + iS.getAmount() + " " + iS.getType().name().toLowerCase().replace("_", "") + " from dying."));
						}
					}
				}
				deathItems.put(dead.getName(), items);
				new BukkitRunnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						for (ItemStack iS : deathItems.get(dead.getName())) {
							if (iS == null || iS.getType().equals(Material.AIR)) {
								continue;
							}
							if (iS.hasItemMeta() && iS.getItemMeta().hasDisplayName() && iS.getItemMeta().getDisplayName().equals(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))) {
								continue;
							}
							dead.getInventory().addItem(iS);
						}
						dead.updateInventory();
						deathItems.remove(dead.getName());
						Coin.update(dead);
					}
				}.runTaskLater(UltimateSurvival.getInstance(), 10);
				event.getDrops().clear();

				if (cP.getPlayerKills().containsKey(dead.getName())) {
					int i = cP.getPlayerKills().get(dead.getName());
					i++;
					cP.getPlayerKills().put(dead.getName(), i);
				} else {
					cP.getPlayerKills().put(dead.getName(), 1);
				}
				CustomPlayer killed = CustomPlayer.getCP(dead.getName());
				int i = killed.getGoldPoints() / 100;
				if (i <= 5) {
					i = 5;
				}
				if (p.hasPermission("coins.multiplier.5")) {
					CoinChange.add(p, (i * 5), true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received " + (i * 5) + " coins for killing " + dead.getName()));
				} else if (p.hasPermission("coins.multiplier.4")) {
					CoinChange.add(p, (i * 4), true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received " + (i * 4) + " coins for killing " + dead.getName()));
				}else if (p.hasPermission("coins.multiplier.3")) {
					CoinChange.add(p, (i * 3), true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received " + (i * 3) + " coins for killing " + dead.getName()));
				} else if (p.hasPermission("coins.multiplier.2")) {
					CoinChange.add(p, (i * 2), true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received " + (i * 2) + " coins for killing " + dead.getName()));
				} else {
					CoinChange.add(p, i, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received " + i + " coins for killing " + dead.getName()));
				}
			} else {
				if (event.getEntity() instanceof Snowman) {
					event.getDrops().clear();
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou killed Frosty!! He was so young! " + name));
				}
				if (cP.getMobKills().containsKey(name)) {
					int i = cP.getMobKills().get(name);
					i++ ;
					cP.getMobKills().put(name, i);
				} else {
					cP.getMobKills().put(name, 1);
				}
				if (p.hasPermission("coins.killing.18")) {
					CoinChange.add(p, 18, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received 18 coins for killing " + name));
				} else if (p.hasPermission("coins.killing.15")) {
					CoinChange.add(p, 15, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received 15 coins for killing " + name));
				} else if(p.hasPermission("coins.killing.12")){
					CoinChange.add(p, 12, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received 12 coins for killing " + name));
				} else if(p.hasPermission("coins.killing.9")){
					CoinChange.add(p, 9, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received 9 coins for killing " + name));
				} else {
					CoinChange.add(p, 6, true);
					SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
					p.sendMessage(ChatUtil.color("&f[&6UltimateSurvival&f] &bYou have received 6 coins for killing " + name));
				}
			}
			double rand = Math.random() * 100;
			if (rand <= 4) {
				int number = (int) (Math.random() * 3);
				switch (number) {
				case 0:
					double armor = Math.random() * 100;
					if (armor <= 50) {
						Armor arm = new Armor(armorstack());
						double healthRand = Math.random() * 100;
						if (healthRand <= 30) {
							double healthAmount = Math.random() * 20;
							arm.setHealth(healthAmount);
						}
						double damageRand = Math.random() * 100;
						if (damageRand <= 30) {
							double damageAmount = Math.random() * 20;
							arm.setDamage(damageAmount);
						}
						double knockRand = Math.random() * 100;
						if (knockRand <= 30) {
							double knockAmount = Math.random() * 1;
							arm.setKnockBack(knockAmount);
						}
						event.getDrops().add(arm.toMobItemStack());
					} else {
						Weapon arm = new Weapon(weaponstack());
						double damageRand = Math.random() * 100;
						if (damageRand <= 30) {
							double damageAmount = Math.random() * 20;
							arm.setDamage(damageAmount);
						}
						double knockRand = Math.random() * 100;
						if (knockRand <= 30) {
							double knockAmount = Math.random() * 1;
							arm.setKnockBack(knockAmount);
						}
						event.getDrops().add(arm.toMobItemStack());
					}
					break;
				case 1:
					int i = 2;
					while (i > 0) {
						double armor1 = Math.random() * 100;
						if (armor1 <= 50) {
							Armor arm = new Armor(armorstack());
							double healthRand = Math.random() * 100;
							if (healthRand <= 30) {
								double healthAmount = Math.random() * 20;
								arm.setHealth(healthAmount);
							}
							double damageRand = Math.random() * 100;
							if (damageRand <= 30) {
								double damageAmount = Math.random() * 20;
								arm.setDamage(damageAmount);
							}
							double knockRand = Math.random() * 100;
							if (knockRand <= 30) {
								double knockAmount = Math.random() * 1;
								arm.setKnockBack(knockAmount);
							}
							event.getDrops().add(arm.toMobItemStack());
						} else {
							Weapon arm = new Weapon(weaponstack());
							double damageRand = Math.random() * 100;
							if (damageRand <= 30) {
								double damageAmount = Math.random() * 20;
								arm.setDamage(damageAmount);
							}
							double knockRand = Math.random() * 100;
							if (knockRand <= 30) {
								double knockAmount = Math.random() * 1;
								arm.setKnockBack(knockAmount);
							}
							event.getDrops().add(arm.toMobItemStack());
						}
						i--;
					}
					break;
				case 2:
					int x = 3;
					while (x > 0) {
						double armor1 = Math.random() * 100;
						if (armor1 <= 50) {
							Armor arm = new Armor(armorstack());
							double healthRand = Math.random() * 100;
							if (healthRand <= 30) {
								double healthAmount = Math.random() * 20;
								arm.setHealth(healthAmount);
							}
							double damageRand = Math.random() * 100;
							if (damageRand <= 30) {
								double damageAmount = Math.random() * 20;
								arm.setDamage(damageAmount);
							}
							double knockRand = Math.random() * 100;
							if (knockRand <= 30) {
								double knockAmount = Math.random() * 1;
								arm.setKnockBack(knockAmount);
							}
							event.getDrops().add(arm.toMobItemStack());
						} else {
							Weapon arm = new Weapon(weaponstack());
							double damageRand = Math.random() * 100;
							if (damageRand <= 30) {
								double damageAmount = Math.random() * 20;
								arm.setDamage(damageAmount);
							}
							double knockRand = Math.random() * 100;
							if (knockRand <= 30) {
								double knockAmount = Math.random() * 1;
								arm.setKnockBack(knockAmount);
							}
							event.getDrops().add(arm.toMobItemStack());
						}
						x--;
					}
					break;
				}
			}
			if (!event.getDrops().isEmpty()) {
				Inventory inv = PlayerInventory.deathInv(event.getDrops(), p);
				if (inv == null) {
					event.getDrops().clear();
					return;
				}
				p.openInventory(inv);
				event.getDrops().clear();
			}
		}
		event.getDrops().clear();
	}

	private ItemStack armorstack() {
		ItemStack iS = new ItemStack(Material.LEATHER_BOOTS, 1);
		int typeRand = (int) (Math.random() * 5);
		String type = "";
		switch (typeRand) {
		case 0:
			type = "LEATHER";
			break;
		case 1:
			type = "CHAINMAIL";
			break;
		case 2:
			type = "IRON";
			break;
		case 3:
			type = "GOLD";
			break;
		case 4:
			type = "DIAMOND";
			break;
		default:
			type = "LEATHER";
			break;
		}
		int slotRand = (int) (Math.random() * 4);
		String slot = "";
		switch (slotRand) {
		case 0:
			slot = "HELMET";
			break;
		case 1:
			slot = "CHESTPLATE";
			break;
		case 2:
			slot = "LEGGINGS";
			break;
		case 3:
			slot = "BOOTS";
			break;
		default:
			slot = "HELMET";
			break;

		}
		String item = type + "_" + slot;
		iS.setType(Material.valueOf(item));
		return iS;
	}

	private ItemStack weaponstack() {
		ItemStack iS = new ItemStack(Material.WOOD_SWORD, 1);
		int typeRand = (int) (Math.random() * 5);
		String type = "";
		switch (typeRand) {
		case 0:
			type = "WOOD";
			break;
		case 1:
			type = "STONE";
			break;
		case 2:
			type = "IRON";
			break;
		case 3:
			type = "GOLD";
			break;
		case 4:
			type = "DIAMOND";
			break;
		default:
			type = "WOOD";
			break;
		}
		int slotRand = (int) (Math.random() * 6);
		String slot = "";
		switch (slotRand) {
		case 0:
			slot = "SWORD";
			break;
		case 1:
			slot = "SPADE";
			break;
		case 2:
			slot = "HOE";
			break;
		case 3:
			slot = "PICKAXE";
			break;
		case 4:
			slot = "AXE";
			break;
		case 5:
			slot = "BOW";
			break;
		default:
			slot = "SWORD";
			break;
		}
		String item;
		if (slot.equals("BOW")) {
			item = slot;
		} else {
			item = type + "_" + slot;
		}
		iS.setType(Material.valueOf(item));
		return iS;
	}
}
