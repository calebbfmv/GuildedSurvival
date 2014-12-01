package us.iluthi.soulofw0lf.ultimatesurvival.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.events.OnMove;
import us.iluthi.soulofw0lf.ultimatesurvival.savers.PlayerSaver;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/30/13
 * Time: 5:30 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Signs {
    private static List<String> messages = new ArrayList<>();
    public static void run(){
        messages.add("&6Please visit &b&lHttp://galaxiesmc.com/shop &6to make in game purchases or to donate to the server!");
        messages.add("&2Please use &6/report {message} &2to report bugs you find!");
        messages.add("&bYou can right click the tutorial sign by spawn to learn how to play!");
        new BukkitRunnable(){
            int i = 180;
            @Override
            public void run(){
                SignProcess.update();
                for (final String player : Lists.savePlayers){
                    if (Bukkit.getPlayer(player) == null){
                        new BukkitRunnable(){
                            @Override
                            public void run(){
                                PlayerSaver.saveSettings(player);
                            }
                        }.runTaskLater(UltimateSurvival.getInstance(), 1);
                    }
                }
                i--;
                if (i == 0){
                    int rand = (int)(Math.random() * messages.size());
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.sendMessage(ChatUtil.color(messages.get(rand)));
                    }
                    i = 180;
                }
                for (Player p : Bukkit.getOnlinePlayers()){
                    OnMove.check(p);
                }
            }
        }.runTaskTimer(UltimateSurvival.getInstance(), 80, 20);
    }
}
