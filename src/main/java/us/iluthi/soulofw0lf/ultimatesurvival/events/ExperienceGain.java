package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 2:51 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ExperienceGain implements Listener {
    public ExperienceGain(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void expChange(PlayerExpChangeEvent event){
        event.setAmount(0);
    }
}
