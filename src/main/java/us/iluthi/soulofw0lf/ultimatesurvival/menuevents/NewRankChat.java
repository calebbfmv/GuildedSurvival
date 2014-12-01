package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.NewRank;
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
 * Time: 11:30 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class NewRankChat implements Listener {
    public NewRankChat(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void snewRank(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (Lists.addingRank.contains(p.getName())){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getMessage().length() >= 17){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Guild rank names may not be more than 16 characters!!!"));
                return;
            }
            if (!StringUtils.isAlphanumeric(event.getMessage())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Guild rank names may only contain letters and numbers."));
                return;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            if (g.getRanks().keySet().contains(event.getMessage())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " A rank with this name already exists!"));
                return;
            }
            final GuildRanks gR = new GuildRanks(event.getMessage(), g.getName());
            Lists.addingRank.remove(p.getName());
            cP.setChatBlocked(false);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have created a new guild rank called " + event.getMessage() + "."));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please Edit this ranks permissions."));
            Maps.rankPerms.put(p.getName(), gR);
            new BukkitRunnable(){
                @Override
                public void run(){
                    p.openInventory(NewRank.inv(gR));
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 30);
        }
    }
}
