package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/27/13
 * Time: 12:08 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class AchievementInventory implements Listener {
    public AchievementInventory(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
}
