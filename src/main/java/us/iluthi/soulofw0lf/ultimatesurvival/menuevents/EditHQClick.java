package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.*;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.Agressors;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditHQ;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ExpandInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildManagement;
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
 * Date: 12/1/13
 * Time: 11:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditHQClick {
    public static void hqClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        if (event.getRawSlot() == 17){
            p.closeInventory();
            p.openInventory(GuildManagement.inv(g, p));
            return;
        }
        if (event.getRawSlot() == 1 && !g.hasHq() && gR.getRankPermissions().get(RankPerms.SET_HQ) && gR.getRankPermissions().get(RankPerms.USE_POINTS)){
            for (String key : Maps.allGuilds.keySet()){
                Guild setG = Maps.allGuilds.get(key);
                if (setG.hasHq()){
                    HQ setHQ = setG.getHeadQuarters();
                    if (p.getLocation().getX() <= setHQ.getMaxX() + 51
                            &&
                            p.getLocation().getZ() <= setHQ.getMaxZ() + 51
                            &&
                            p.getLocation().getX() >= setHQ.getMinX() - 51
                            &&
                            p.getLocation().getX() >= setHQ.getMinZ() - 51){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " You are too close to another guilds headquarters!"));
                        return;
                    }

                }
            }
            if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 350){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You must go farther away from spawn before setting your guilds HQ."));
                return;
            }
            if (g.getGuildFortifications() < 10000){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild needs a total of 10,000 contribution points to purchase a HQ."));
                return;
            }
            g.setHeadQuarters(new HQ(p));
            g.setHq(true);
            for (WarZone war : Lists.warZones){
                if (war.overLap(g.getHeadQuarters())){
                    g.setHeadQuarters(null);
                    g.setHq(false);
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot set your guild headquarters this close to a war zone!"));
                    break;
                }
            }
            g.setGuildFortifications(g.getGuildFortifications() - 10000);
            for (String player : g.getPlayers()){
                if (Bukkit.getPlayer(player) != null){
                    Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " The guild Headquarters has been set!"));
                }
            }
            int minX = g.getHeadQuarters().getMinX();
            int minZ = g.getHeadQuarters().getMinZ();
            int maxX = g.getHeadQuarters().getMaxX();
            int maxZ = g.getHeadQuarters().getMaxZ();
            int i = 0;
            String world = Locations.spawnLoc.getWorld().getName();
            while (i < 255){
                int x = minX;
                while (x <= maxX){
                    int z = minZ;
                    while (z <= maxZ){
                        Location loc = new Location(Bukkit.getWorld(world), x, i, z);
                        Maps.guildsLocMap.put(loc, g.getName());
                        z++;
                    }
                    x++;
                }
                i++;
            }
            p.closeInventory();
            p.openInventory(EditHQ.inv(p));
            return;
        }
        if (event.getRawSlot() == 1 && g.hasHq() && gR.getRankPermissions().get(RankPerms.REMOVE_HQ)){
            g.setHq(false);
            for (String player : g.getPlayers()){
                if (Bukkit.getPlayer(player) != null){
                    Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild HQ has been removed!"));
                }
            }
            return;
        }
        if (event.getRawSlot() == 3 && !g.getAggressors().isEmpty()){
            p.closeInventory();
            p.openInventory(Agressors.inv(p));
            return;
        }
        if (event.getRawSlot() == 5 && gR.getRankPermissions().get(RankPerms.SET_HOME) && gR.getRankPermissions().get(RankPerms.USE_POINTS)){
            if (!g.hasHq()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You must have a headquarters to set a guild home location."));
                return;
            }
            if (!cP.isInGuildLands() && !cP.getGuildNear().equalsIgnoreCase(g.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You must be within your guild HQ in order to set a home location."));
                return;
            }
            if (g.getGuildFortifications() <= 4999){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot currently afford a Home location. Setting a home costs 5,000 Guild Fortification points."));
                return;
            }
            g.setGuildFortifications(g.getGuildFortifications() - 5000);
            g.setHome(true);
            g.setHomeLoc(p.getLocation());
            for (String player : g.getPlayers()){
                if (Bukkit.getPlayer(player) != null){
                    Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild Home location has been set"));
                }
            }
        }
        if (event.getRawSlot() == 7 && gR.getRankPermissions().get(RankPerms.SET_PEACEFUL)){
            if (Lists.guildStatus.contains(g.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild has recently switched it's peaceful status on or off! This can only be done once per 24 hours!"));
                return;
            }
            if (g.isPeaceful()){
                g.setPeaceful(false);
                Lists.guildStatus.add(g.getName());
                final String gName = g.getName();
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        Lists.guildStatus.remove(gName);
                    }
                }.runTaskLater(UltimateSurvival.getInstance(), (20*60*60*24));
                for (String player : g.getPlayers()){
                    if (Bukkit.getPlayer(player) != null){
                        Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild is no longer flagged as peaceful! Your guild cannot turn peaceful back on for 24 hours."));
                        CustomPlayer cP1 = CustomPlayer.getCP(player);
                        if (!cP1.isPvp()){
                            cP1.setPvp(true);
                            Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your pvp status has been turned on!"));
                        }
                    }
                }
                return;
            } else {
                g.setPeaceful(true);
                Lists.guildStatus.add(g.getName());
                final String gName = g.getName();
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        Lists.guildStatus.remove(gName);
                    }
                }.runTaskLater(UltimateSurvival.getInstance(), (20*60*60*24));
                for (String player : g.getPlayers()){
                    if (Bukkit.getPlayer(player) != null){
                        Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild is now set as peaceful. You may not be attacked, but you cannot strengthen your guild at this time either. You may now turn your pvp status off if you wish to. Your guild cannot turn peaceful off for 24 hours!"));
                    }
                }
                return;
            }
        }
        if (event.getRawSlot() == 9 && gR.getRankPermissions().get(RankPerms.USE_POINTS) && g.hasHq()){
            p.closeInventory();
            p.openInventory(ExpandInventory.inv(g));
            return;
        }
        if (event.getRawSlot() == 13 && gR.getRankPermissions().get(RankPerms.SET_STOREROOM)){
            if (!g.hasHq()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot set a storeroom without a guild headquarters!"));
                return;
            }
            if (!cP.isInGuildLands() && !cP.getGuildNear().equalsIgnoreCase(g.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub) + " you must be closer to the center of your guild HQ to place a storeroom!");
                return;
            }
            if (!gR.getRankPermissions().get(RankPerms.USE_POINTS)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to spend guild fortification points!"));
                return;
            }
            if (g.getGuildFortifications() <= 99999){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot afford a storeroom at this time. Storerooms cost 100,000 Guild Fortification Points."));
                return;
            }
            g.setGuildFortifications(g.getGuildFortifications() - 100000);
            for (String player : g.getPlayers()){
                if (Bukkit.getPlayer(player) != null){
                    Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild now has a storeroom set!"));
                }
            }
            g.setMaxZ((int)(p.getLocation().getZ() + 10));
            g.setMaxY((int)(p.getLocation().getY() + 10));
            g.setMaxX((int)(p.getLocation().getX() + 10));
            g.setMinX((int)(p.getLocation().getX() - 10));
            g.setMinY((int)(p.getLocation().getY() - 10));
            g.setMinZ((int)(p.getLocation().getZ() - 10));
            g.setHasStoreroom(true);
            return;
        }
        if (event.getRawSlot() == 11 && gR.getRankPermissions().get(RankPerms.SET_ENTRY)){
            if (!g.hasHq()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild must have a Headquarters location to set an entry message."));
                return;
            }
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type in your guilds new Entry message."));
            Lists.settingEntry.add(p.getName());
            cP.setChatBlocked(true);
            p.closeInventory();
            return;
        }
        if (event.getRawSlot() == 15 && gR.getRankPermissions().get(RankPerms.SET_EXIT)){
            if (!g.hasHq()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild must have a Headquarters location to set an exit message."));
                return;
            }
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type in your guilds new Exit message."));
            Lists.settingExit.add(p.getName());
            cP.setChatBlocked(true);
            p.closeInventory();
            return;
        }
    }
}
