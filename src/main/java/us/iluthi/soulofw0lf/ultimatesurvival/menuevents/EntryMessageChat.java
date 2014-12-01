package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/1/13
 * Time: 11:57 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EntryMessageChat implements Listener {
    public EntryMessageChat(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void entryChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (!Lists.settingEntry.contains(p.getName())){
            return;
        }
        event.setCancelled(true);
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        g.setHasEntry(true);
        g.setEntryMessage(event.getMessage());
        p.sendMessage(ChatUtil.color(Strings.guildStub + " You have set your guilds entry message to:"));
        p.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getEntryMessage()));
        Lists.settingEntry.remove(p.getName());
        cP.setChatBlocked(false);
    }
}
