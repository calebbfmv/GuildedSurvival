package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * User: links
 * Date: 1/5/14
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 */
public class FriendPMChat implements Listener {
    public FriendPMChat(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void friendChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (!Maps.friendChatMap.containsKey(p.getName())){
            return;
        }
        event.setCancelled(true);
        Player p2 = Bukkit.getPlayer(Maps.friendChatMap.get(p.getName()));
        if (p2 != null)
        {
            CustomPlayer cp2 = CustomPlayer.getCP(p2.getName());
            p2.sendMessage(ChatUtil.color("&f[&bPM From&f: &6" + p.getName() + "&f] &b "+event.getMessage()));
            p.sendMessage(ChatUtil.color("&f[&6PM To&f: &b" + p2.getName() + "&f] &6" + event.getMessage()));
            cp2.setLastFriendPM(p.getName());
        }
        Maps.friendChatMap.remove(p.getName());
    }
}
