package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 7:29 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutChatCancel implements Listener {
    public TutChatCancel(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void chatCancel(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (Maps.tutorialStages.containsKey(p.getName())){
            event.setCancelled(true);
            if (event.getMessage().equalsIgnoreCase("exit")){
                p.teleport(Locations.spawnLoc);
                p.sendMessage(ChatUtil.color("&bYou have exited the tutorial!"));
                Maps.tutorialStages.remove(p.getName());
                if (Maps.tutorialItems.containsKey(p.getName())){
                    p.getInventory().remove(Maps.tutorialItems.get(p.getName()));
                }
                Maps.tutorialItems.remove(p.getName());
                Maps.tutorialLocations.remove(p.getName());
                Maps.tutorialSteps.remove(p.getName());
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        TutMainInvClick.removeWand(p);
                    }
                }.runTaskLater(UltimateSurvival.getInstance(), 1);
            }
        }
    }
}
