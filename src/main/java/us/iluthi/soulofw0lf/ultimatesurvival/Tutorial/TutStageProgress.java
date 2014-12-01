package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
 * Time: 7:38 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutStageProgress implements Listener {
    public TutStageProgress(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void stageProgress(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (!Maps.tutorialStages.containsKey(p.getName())){
            return;
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (Maps.tutorialSteps.get(p.getName()) >= Maps.tutorialStageNumbers.get(Maps.tutorialStages.get(p.getName()))){
                p.teleport(Locations.spawnLoc);
                p.sendMessage(ChatUtil.color("&bYou have finished this tutorial!"));
                Maps.tutorialStages.remove(p.getName());
                if (Maps.tutorialItems.containsKey(p.getName())){
                    p.getInventory().remove(Maps.tutorialItems.get(p.getName()));
                }
                Maps.tutorialItems.remove(p.getName());
                Maps.tutorialLocations.remove(p.getName());
                Maps.tutorialSteps.remove(p.getName());
                TutMainInvClick.removeWand(p);
            } else {
                int i = Maps.tutorialSteps.get(p.getName());
                i++;
                Maps.tutorialSteps.remove(p.getName());
                Maps.tutorialSteps.put(p.getName(), i);
                Step.Check(p);
            }
        }
    }
}
