package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 *
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
public class BlockUse implements Listener{
    public BlockUse(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void blockUse(PlayerInteractEvent event){
        Player p = event.getPlayer();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInHub()){
            event.setCancelled(true);
            if (p.getItemInHand() != null && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()){
                if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))){
                    event.setUseItemInHand(Event.Result.DENY);
                }
            }
        }
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            return;
        }
        Block b = event.getClickedBlock();
        final Location loc = b.getLocation();
        boolean inGuildLands = false;
        Guild g = null;
        for (Guild gl : Maps.allGuilds.values()){
            if (!gl.hasHq()){
                continue;
            }
            if (loc.getX() > gl.getHeadQuarters().getMaxX()){
                continue;
            }
            if (loc.getZ() > gl.getHeadQuarters().getMaxZ()){
                continue;
            }
            if (loc.getX() < gl.getHeadQuarters().getMinX()){
                continue;
            }
            if (loc.getZ() < gl.getHeadQuarters().getMinZ()){
                continue;
            }
            inGuildLands = true;
            g = gl;
            break;
        }
        if (inGuildLands){
            if (cP.isInGuild()){
                if (cP.getGuildName().equalsIgnoreCase(g.getName())){
                    GuildRanks gR = g.getRanks().get(cP.getGuildRank());
                    if (!gR.getRankPermissions().get(RankPerms.INTERACT)){
                        event.setCancelled(true);
                        return;
                    }
                    if (g.isHasStoreroom()){
                        if (!gR.getRankPermissions().get(RankPerms.ACCESS_STOREROOM)){
                            if (p.getLocation().getX() < g.getMaxX() && p.getLocation().getY() < g.getMaxY() && p.getLocation().getZ() < g.getMaxZ() && p.getLocation().getX() > g.getMinX() && p.getLocation().getY() > g.getMinY() && p.getLocation().getZ() > g.getMinZ()){
                                event.setCancelled(true);
                                return;
                            }
                        }
                    }
                } else {
                    Guild gl = Maps.allGuilds.get(cP.getGuildName());
                    if (!gl.getWarGuilds().contains(g.getName())){
                        event.setCancelled(true);
                        return;
                    }
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
