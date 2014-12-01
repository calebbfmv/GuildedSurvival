package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildManagement;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildPlayersView;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:29 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MainGuildMenuClick {
    public static void gMenuClick(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!cP.isInGuild()){
            p.closeInventory();
            p.sendMessage(ChatUtil.color(Strings.guildStub + " I have no clue how you got in this menu but only people in guilds should be in here!"));
            return;
        }
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        if (!g.isPeaceful()){
            if (event.getRawSlot() == 0){
                if (event.isRightClick()){
                    GuildRanks gR = g.getRanks().get(cP.getGuildRank());
                    if (!gR.getRankPermissions().get(RankPerms.USE_HOME)){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild rank does not have permission to go to the guild home."));
                        return;
                    }
                    if (!g.isHome()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild does not have a home set."));
                        return;
                    }
                    final String pName = p.getName();
                    final Location loc = p.getLocation();
                    final Location gLoc = g.getHomeLoc();
                    p.closeInventory();
                    new BukkitRunnable(){
                        int i = 8;
                        @Override
                        public void run(){
                            if (Bukkit.getPlayer(pName) == null){
                                cancel();
                                return;
                            }
                            Player pl = Bukkit.getPlayer(pName);
                            if (i == 0){
                                pl.teleport(gLoc);
                                cancel();
                                return;
                            }
                            if (pl.getLocation().distance(loc) >= 3){
                                pl.sendMessage(ChatUtil.color(Strings.guildStub + " Teleport has been cancelled due to movement!"));
                                cancel();
                                return;
                            }
                            pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild home transport will commence " + i + " second."));
                            i--;
                        }
                    }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
                    return;
                } else {
                    Lists.addingPoints.add(p.getName());
                    cP.setChatBlocked(true);
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type the number of coins you wish to spend on fortification points."));
                    p.closeInventory();
                    return;
                }
            }
        }
        if (event.getRawSlot() == 2){
            p.closeInventory();
            p.openInventory(GuildPlayersView.inv(g, p));
        }
        if (event.getRawSlot() == 4){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getMotd()));
            p.closeInventory();
            return;
        }
        if (event.getRawSlot() == 6){
            p.closeInventory();
            p.openInventory(GuildManagement.inv(g, p));
            return;
        }
        if (event.getRawSlot() == 8){
            p.closeInventory();
            p.openInventory(MenuInventory.menu());
        }
    }
}
