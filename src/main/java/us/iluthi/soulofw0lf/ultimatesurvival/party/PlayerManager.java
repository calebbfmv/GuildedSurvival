package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:32 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerManager {
    private static String stub = ChatColor.GREEN + "Party Roll: " + ChatColor.WHITE;

    public PlayerManager(){}
    public static boolean inRoll(Player p){
        for(Roll r : RollManager.getRollList())
            for(String l : r.getPlayers())
                if(l.equals(p.getName()))
                    return true;
        return false;
    }

    public static void setPlayers(List<String> l, int i){
        Roll r = RollManager.getRollFromList(i);
        if(r != null)
            r.setPlayers(l);
    }

    public static void sendMultiMessageP(List<Player> list, String message){
        for(Player player : list)
            player.sendMessage(stub + message);
    }

    public static void sendMultiMessageP(Player[] list, String message){
        for(Player player : list)
            player.sendMessage(stub + message);
    }

    public static void sendMultiMessageS(List<String> list, String message){
        for(Player player : Bukkit.getOnlinePlayers())
            if(list.contains(player.getName()))
                player.sendMessage(stub + message);
    }

    public static void sendMultiMessageS(String[] list, String message){
        for(String player : list)
            Bukkit.getServer().getPlayerExact(player).sendMessage(stub + message);
    }
}
