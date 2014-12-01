package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 7:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutMoveChecker implements Listener{
    public TutMoveChecker(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void moveCancel(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if (Maps.tutorialLocations.containsKey(p.getName())){
            if (p.getLocation().distance(Maps.tutorialLocations.get(p.getName())) >= 1){
                p.teleport(Maps.tutorialLocations.get(p.getName()));
                p.sendMessage(ChatUtil.color("&4Please do not move during the tutorials unless specifically asked to!"));
            }
        }
    }
}
