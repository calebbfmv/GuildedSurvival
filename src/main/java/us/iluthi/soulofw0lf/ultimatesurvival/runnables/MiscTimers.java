package us.iluthi.soulofw0lf.ultimatesurvival.runnables;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.Lottery;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Booleans;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * User: links
 * Date: 1/5/14
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 */
public class MiscTimers {
    public static void run(){ //timers
        new BukkitRunnable(){
            @Override
            public void run(){
                if (!Booleans.weatherCheck){
                    World event = Locations.spawnLoc.getWorld();
                    event.setWeatherDuration(0);
                    event.setThunderDuration(0);
                    event.setThundering(false);
                    event.setStorm(false);
                    Booleans.weatherCheck = true;
                }
                for (Player p: Bukkit.getOnlinePlayers())
                    p.setLevel(p.getLevel() + 1);
            }
        }.runTaskTimer(UltimateSurvival.getInstance(), 80, 2400);
    }
    public static void lottery(){
        new BukkitRunnable(){

            @Override
            public void run(){
                if (((((System.currentTimeMillis() - Lottery.lotteryTime) / 1000) / 60) / 60) >= 24){
                    Lottery.process();
                }
            }
        }.runTaskTimer(UltimateSurvival.getInstance(), 0, 1800);
    }
}
