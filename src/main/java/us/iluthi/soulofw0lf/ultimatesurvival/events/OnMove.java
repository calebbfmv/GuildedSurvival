package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.WarZone;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/26/13
 * Time: 2:56 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class OnMove {
	public static void check(Player p){
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		if (cP.isInHub()){
			if (!p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) || p.getLocation().distance(Locations.spawnLoc) >= 150){
				p.sendMessage(ChatUtil.color("&f[&6Region Manager&f] &bYou are now leaving the safe zone!"));
				cP.setInHub(false);
				return;
			}
		} else {
			if (!cP.isInGuildLands() && p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName())){
				if (p.getLocation().distance(Locations.spawnLoc) <= 150){
					p.sendMessage(ChatUtil.color("&f[&6Region Manager&f] &bYou are now Entering the safe zone!"));
					cP.setInHub(true);
					return;
				}
			}
		}
		if (!cP.isInWarZone()){
			if (!Lists.warZones.isEmpty()){
				for (WarZone key : Lists.warZones){
					if (p.getLocation().getX() > key.getMaxX()){
						continue;
					}
					if (p.getLocation().getZ() > key.getMaxZ()){
						continue;
					}
					if (p.getLocation().getX() < key.getMinX()){
						continue;
					}
					if (p.getLocation().getZ() < key.getMinZ()){
						continue;
					}
					p.sendMessage(ChatUtil.color(key.getEntryMessage()));
					cP.setInWarZone(true);
					cP.setWarZone(key.getOwnerName());
					break;
				}
			}
		} else {
			WarZone war = null;
			for (WarZone key : Lists.warZones){
				if (key.getOwnerName().equalsIgnoreCase(cP.getWarZone())){
					war = key;
                    break;
				}
			}
			if (war == null){
				return;
			}
			if (p.getLocation().getX() > war.getMaxX() || p.getLocation().getZ() > war.getMaxZ() || p.getLocation().getX() < war.getMinX() || p.getLocation().getZ() < war.getMinZ()){
				p.sendMessage(ChatUtil.color(war.getExitMessage()));
				cP.setInWarZone(false);
				cP.setWarZone("");
				return;
			}
		}
		if (!cP.isInGuildLands()){
            int x = (int)p.getLocation().getX();
            int y = (int)p.getLocation().getY();
            int z = (int)p.getLocation().getZ();
            Location loc = new Location(p.getWorld(), x, y, z);
            if (Maps.guildsLocMap.containsKey(loc)){
                Guild g = Maps.allGuilds.get(Maps.guildsLocMap.get(loc));
			    if (g.isHasEntry()){
			    	p.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getEntryMessage()));
				    cP.setInGuildLands(true);
    				cP.setGuildNear(g.getName());
	    			return;
		    	} else {
			    	p.sendMessage(ChatUtil.color(Strings.guildStub + " Now entering " + g.getName() + "'s Headquarters."));
				    cP.setInGuildLands(true);
    				cP.setGuildNear(g.getName());
	    			return;
		    	}
            }
		} else {
			Guild g = Maps.allGuilds.get(cP.getGuildNear());
			if (g.hasHq()){
				if (p.getLocation().getX() > g.getHeadQuarters().getMaxX() || p.getLocation().getZ() > g.getHeadQuarters().getMaxZ() || p.getLocation().getX() < g.getHeadQuarters().getMinX() || p.getLocation().getZ() < g.getHeadQuarters().getMinZ()){
					if (g.isHasExit()){
						p.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getExitMessage()));
						cP.setInGuildLands(false);
						cP.setGuildNear("");
					} else {
						p.sendMessage(ChatUtil.color(Strings.guildStub + " Now leaving " + g.getName() + "'s Headquarters."));
						cP.setInGuildLands(false);
						cP.setGuildNear("");
					}
					return;
				}
			}
		}
		if (cP.isInDuel() && cP.getDuelLocation().distance(p.getLocation()) >= 31){
			final String name = cP.getDuelPartner();
			for (Player player : Bukkit.getOnlinePlayers()){
				if (player.getWorld().getName().equals(p.getWorld().getName()) && player.getLocation().distance(p.getLocation()) <= 50){
					player.sendMessage(ChatUtil.color("&f[&eDuels&f] &b" + p.getName() + " Has just run from " + name + " in a duel!"));
				}
			}
			final Player duel = Bukkit.getPlayer(name);
			p.setHealth(p.getMaxHealth());
			if (duel != null){
				duel.setHealth(duel.getMaxHealth());
			}
			Lists.recentlyduelled.add(p.getName());
			if (duel != null){
				Lists.recentlyduelled.add(duel.getName());
			}
			cP.setInDuel(false);
			cP.setDuelPartner("");
			final String name2;
			if (duel != null){
				CustomPlayer cD = CustomPlayer.getCP(duel.getName());
				cD.setDuelPartner("");
				cD.setInDuel(false);
				name2 = cD.getDuelPartner();
			} else {
				name2 = null;
			}
			new BukkitRunnable(){
				@Override
				public void run(){
					Lists.recentlyduelled.remove(name);
					if (duel != null){
						Lists.recentlyduelled.remove(name2);
					}
				}
			}.runTaskLater(UltimateSurvival.getInstance(), 80);
		}
	}
}