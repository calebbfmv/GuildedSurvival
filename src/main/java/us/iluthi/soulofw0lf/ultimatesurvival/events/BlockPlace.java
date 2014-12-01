package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.WarZone;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Booleans;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
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
public class BlockPlace implements Listener{
    public BlockPlace(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void blockPlaced(BlockPlaceEvent event){
        Player p = event.getPlayer();
        if (Booleans.inSpawn(p) && !p.hasPermission("guild.bypass")){
            event.setCancelled(true);
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInDuel()){
            event.setCancelled(true);
            return;
        }
        Block b = event.getBlock();
        final Location loc = b.getLocation();
        for (WarZone war : Lists.warZones){
            if (loc.getX() > war.getMaxX()){
                continue;
            }
            if (loc.getZ() > war.getMaxZ()){
                continue;
            }
            if (loc.getX() < war.getMinX()){
                continue;
            }
            if (loc.getZ() < war.getMinZ()){
                continue;
            }
            if (!p.getName().equalsIgnoreCase(war.getOwner())){
                event.setCancelled(true);
                return;
            }
        }
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
            if (!cP.isInGuild()){
                event.setCancelled(true);
                return;
            }
            Guild playerGuild = Maps.allGuilds.get(cP.getGuildName());
            if (playerGuild.getName().equalsIgnoreCase(g.getName())){
                String gRank = cP.getGuildRank();
                GuildRanks rank = g.getRanks().get(gRank);
                if (!rank.getRankPermissions().get(RankPerms.BUILD)){
                    event.setCancelled(true);
                    return;
                }
                if (g.isHasStoreroom()){
                    if (!rank.getRankPermissions().get(RankPerms.ACCESS_STOREROOM)){
                        if (b.getLocation().getX() < g.getMaxX() && b.getLocation().getY() < g.getMaxY() && b.getLocation().getZ() < g.getMaxZ() && b.getLocation().getX() > g.getMinX() && b.getLocation().getY() > g.getMinY() && b.getLocation().getZ() > g.getMinZ()){
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
        int i = 0;
        if (cP.getBlockPlacement().containsKey(b.getType().name())){
            i = cP.getBlockPlacement().get(b.getType().name());
        }
        cP.getBlockPlacement().put(b.getType().name(), i + 1);
        if (b.getType() == (Material.MOB_SPAWNER)){
            b.setType(Material.AIR);
            new BukkitRunnable(){
                @Override
                public void run(){
                    loc.getWorld().createExplosion(loc, 3, true);
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 80);

        }
    }
}
