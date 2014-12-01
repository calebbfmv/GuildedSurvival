package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.Tutorial.TutMainInvClick;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.LoadGuilds;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.PlayerLoader;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:12 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerJoin implements Listener{
    public PlayerJoin(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.setHealthScaled(true);
        p.setHealthScale(20);
        for (String key : TutMainInvClick.hiddenPlayer.keySet()){
            if (Bukkit.getPlayer(key) == null){
                continue;
            }
            p.hidePlayer(Bukkit.getPlayer(key));
            Bukkit.getPlayer(key).hidePlayer(p);
            TutMainInvClick.hiddenPlayer.get(key).add(p.getName());
        }
        if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 150){
            p.teleport(Locations.spawnLoc);
        }
        CustomPlayer cP = PlayerLoader.loadSettings(p);
        cP.setTimeLoggedIn(System.currentTimeMillis());
        if (cP.isInGuild() && !Maps.allGuilds.containsKey(cP.getGuildName())){
            LoadGuilds.LoadGuild(cP.getGuildName(), p.getName());
        }
        if (cP.isInGuild() && Maps.allGuilds.containsKey(cP.getGuildName())){
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            if (!g.getPlayers().contains(p.getName())){
                cP.setGuildName("");
                cP.setGuildRank("");
                cP.setInGuild(false);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have either been removed from your guild or it no longer exists!"));
            } else {
                for (String name : g.getPlayers()){
                    if (Bukkit.getPlayer(name) == null){
                        continue;
                    }
                    if (name.equalsIgnoreCase(p.getName())){
                        continue;
                    }
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has logged on."));
                }
            }
        }
        event.setJoinMessage(null);
        ItemStack iS1 = new ItemStack(MenuInventory.menuItem.getType());
        iS1.setItemMeta(MenuInventory.menuItem.getItemMeta());
        ItemMeta iM1 = iS1.getItemMeta();
        List<String> lores = iM1.getLore();
        Double d = (double)cP.getGoldPoints();
        String s = lores.get(3).replace("@c", d.toString());
        lores.remove(3);
        lores.add(s);
        iM1.setLore(lores);
        iS1.setItemMeta(iM1);
        iS1.setAmount(1);
        if(p.getInventory().contains(iS1)){
            p.getInventory().remove(iS1);
        }
        p.getInventory().setItem(8, iS1);
        p.updateInventory();
        final String name = p.getName();
        new BukkitRunnable(){
            @Override
            public void run(){
                if (Bukkit.getPlayer(name) == null){
                    cancel();
                    return;
                }
                int mil = 1000000;
                int halfMil = 500000;
                Player p = Bukkit.getPlayer(name);
                CustomPlayer cP = CustomPlayer.getCP(p.getName());
                if (p.hasPermission("join.coins.500000")){
                    if (!UltimateSurvival.vipPlayers.contains(name)){
                        CoinChange.add(p, halfMil, false);
                        UltimateSurvival.vipPlayers.add(p.getName());
                    }
                } else if (p.hasPermission("join.coins.1000000")) {
                    if (!UltimateSurvival.vipPlayers.contains(name)){
                        CoinChange.add(p, mil, false);
                        UltimateSurvival.vipPlayers.add(p.getName());
                    }
                } else if (p.hasPermission("join.coins.1500000")) {
                    if (!UltimateSurvival.vipPlayers.contains(name)){
                        CoinChange.add(p, 1500000, false);
                        UltimateSurvival.vipPlayers.add(p.getName());
                    }
                } else if (p.hasPermission("join.coins.2000000")) {
                    if (!UltimateSurvival.vipPlayers.contains(name)){
                        CoinChange.add(p, 2000000, false);
                        UltimateSurvival.vipPlayers.add(p.getName());
                    }
                }
                if (cP.getGoldPoints() < 0){ cP.setGoldPoints(0);}
                if (cP.isInGuild()){
                    Guild g = Maps.allGuilds.get(cP.getGuildName());
                    if (g.isWarning()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " &4WARNING: &bYour guild is currently being attacked!"));
                    }
                    if (!g.isPeaceful()){
                        cP.setPvp(true);
                    }
                    if (g.isMessageOfTheDay()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " &bMOTD: &e" + g.getMotd()));
                    }
                }
            }
        }.runTaskLater(UltimateSurvival.getInstance(), 40);
    }
}
