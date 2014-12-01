package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.Coin;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

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
public class ItemDrop implements Listener{
    public ItemDrop(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void itemDrop(PlayerDropItemEvent event){
        final Player p = event.getPlayer();
        ItemStack iS = event.getItemDrop().getItemStack();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInHub()){
            event.setCancelled(true);
            return;
        }
        if (iS.hasItemMeta() && iS.getItemMeta().hasLore()){
            if (iS.getItemMeta().getLore().contains(ChatUtil.color("&bSoulbound"))){
                event.setCancelled(true);
            }
        }
        if (!iS.getItemMeta().hasDisplayName()){
            return;
        }
        if (iS.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))){
            event.setCancelled(true);
            new BukkitRunnable(){
                @Override
                public void run(){
                    Coin.update(p);
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 2);
        }
    }
}
