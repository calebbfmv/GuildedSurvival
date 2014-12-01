package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
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
 * Date: 12/2/13
 * Time: 12:26 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MotdChat implements Listener {
    public MotdChat(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void motdChat(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (Lists.addingMotd.contains(p.getName())){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            g.setMotd(event.getMessage());
            g.setMessageOfTheDay(true);
            cP.setChatBlocked(false);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have set the guild motd to: " + g.getMotd()));
            new BukkitRunnable(){
                @Override
                public void run(){
                    Lists.addingMotd.remove(p.getName());

                }
            }.runTaskLater(UltimateSurvival.getInstance(), 3);
        }
    }
    @EventHandler
    public void recruitChat(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (Lists.addingRecruitmentMessage.contains(p.getName())){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            g.setRecruitMessage(event.getMessage());
            cP.setChatBlocked(false);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have set the guild recruitment message to: " + g.getMotd()));
            new BukkitRunnable(){
                @Override
                public void run(){
                    Lists.addingRecruitmentMessage.remove(p.getName());

                }
            }.runTaskLater(UltimateSurvival.getInstance(), 3);
        }
    }
}
