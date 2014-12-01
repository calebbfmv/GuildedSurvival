package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ItemPickup implements Listener{
    public ItemPickup(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void itemGrab(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 150 && !p.hasPermission("guild.bypass")){
            event.setCancelled(true);
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInDuel()){
            event.setCancelled(true);
            return;
        }
        if (cP.isInGuildLands()){
            if (!cP.isInGuild()){
                event.setCancelled(true);
                return;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildNear());
            Guild playerGuild = Maps.allGuilds.get(cP.getGuildName());
            if (playerGuild.getName().equalsIgnoreCase(g.getName())){
                String gRank = cP.getGuildRank();
                GuildRanks rank = g.getRanks().get(gRank);
                if (!rank.getRankPermissions().get(RankPerms.PICK_UP)){
                    event.setCancelled(true);
                    return;
                }
                if (g.isHasStoreroom()){
                    if (!rank.getRankPermissions().get(RankPerms.ACCESS_STOREROOM)){
                        if (p.getLocation().getX() < g.getMaxX() && p.getLocation().getY() < g.getMaxY() && p.getLocation().getZ() < g.getMaxZ() && p.getLocation().getX() > g.getMinX() && p.getLocation().getY() > g.getMinY() && p.getLocation().getZ() > g.getMinZ()){
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            } else {
                if (!playerGuild.getWarGuilds().contains(g.getName())){
                    event.setCancelled(true);
                    return;
                }
            }
        }
        Player player = event.getPlayer();
        ItemStack itm = event.getItem().getItemStack();
        if(itm != null){
            if(itm.getItemMeta().hasLore()){
                for(String strlore : itm.getItemMeta().getLore())
                {
                    for(String lores : Lists.loreList){
                        if(strlore.contains(lores.replaceAll("@", ":")))
                        {
                            Party party = null;
                            if((party = Util.getParty(player.getName())) == null)
                                return;
                            party.addItem(itm, strlore);
                            event.getItem().remove();
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
