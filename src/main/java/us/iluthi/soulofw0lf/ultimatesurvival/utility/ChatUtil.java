package us.iluthi.soulofw0lf.ultimatesurvival.utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:16 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ChatUtil {
    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String chatColor(String channel, String s, Player player){
        CustomPlayer cP = CustomPlayer.getCP(player.getName());
        String chatColor = cP.getChannelColors().get(channel);
        return color("&f: " + chatColor + s);
    }
}
