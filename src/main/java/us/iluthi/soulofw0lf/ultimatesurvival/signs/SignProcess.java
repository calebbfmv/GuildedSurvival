package us.iluthi.soulofw0lf.ultimatesurvival.signs;

import org.bukkit.Bukkit;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Integers;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/24/13
 * Time: 1:35 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SignProcess {
    public static void update(){
        for (String key : Maps.allGuilds.keySet()){
            Guild g = Maps.allGuilds.get(key);
            SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
        }
        SignBlocks.playerLeader.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.playerLeader.setLine(1, ChatUtil.color("&6Lead Player"));
        SignBlocks.playerLeader.setLine(2, ChatUtil.color("&4" + Strings.leadPlayer));
        SignBlocks.playerLeader.setLine(3, ChatUtil.color("&6" + Integers.richestPlayerPoints));
        SignBlocks.playerLeader.update();

        SignBlocks.display.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.display.setLine(1, ChatUtil.color("&6SkyKipz"));
        SignBlocks.display.setLine(2, ChatUtil.color("&6Guilds"));
        SignBlocks.display.setLine(3, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.display.update();

        SignBlocks.tutorial.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.tutorial.setLine(1, ChatUtil.color("&6Click here to"));
        SignBlocks.tutorial.setLine(2, ChatUtil.color("&4take the"));
        SignBlocks.tutorial.setLine(3, ChatUtil.color("&6tutorial"));
        SignBlocks.tutorial.update();

        SignBlocks.currentGuilds.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.currentGuilds.setLine(1, ChatUtil.color("Current Guilds"));
        SignBlocks.currentGuilds.setLine(2, ChatUtil.color("&6" + Maps.allGuilds.keySet().size()));
        SignBlocks.currentGuilds.setLine(3, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.currentGuilds.update();

        SignBlocks.currentPlayers.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.currentPlayers.setLine(1, ChatUtil.color("Current Players"));
        SignBlocks.currentPlayers.setLine(2, ChatUtil.color("&6" + Bukkit.getOnlinePlayers().length));
        SignBlocks.currentPlayers.setLine(3, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.currentPlayers.update();

        SignBlocks.guildLeader.setLine(0, ChatUtil.color("&4-=@@=====@@=-"));
        SignBlocks.guildLeader.setLine(1, ChatUtil.color("&6Lead Guild"));
        SignBlocks.guildLeader.setLine(2, ChatUtil.color("&4" + Strings.leadGuild));
        SignBlocks.guildLeader.setLine(3, ChatUtil.color("&6" + Integers.strongestGuildPoints));
        SignBlocks.guildLeader.update();
    }
    public static void playerCheck(String name, int points){
        if (Strings.leadPlayer.equalsIgnoreCase(name)){
            Integers.richestPlayerPoints = points;
        }
        if (points > Integers.richestPlayerPoints){
            Integers.richestPlayerPoints = points;
            Strings.leadPlayer = name;
        }
    }
    public static void guildCheck(String name, int points){
        if (Strings.leadGuild.equalsIgnoreCase(name)){
            Integers.strongestGuildPoints = points;
        }
        if (points > Integers.strongestGuildPoints){
            Integers.strongestGuildPoints = points;
            Strings.leadGuild = name;
        }
    }
}
