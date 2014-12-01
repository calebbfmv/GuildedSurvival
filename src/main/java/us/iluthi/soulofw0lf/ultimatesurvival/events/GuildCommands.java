package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 10:53 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildCommands implements Listener{
    public GuildCommands(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @SuppressWarnings("unused")
    @EventHandler
    public void guildComm(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        String command = args[0].replace("/", "");
    }
}
