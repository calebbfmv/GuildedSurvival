package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.IncommingTPRequestInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MainGuildMenu;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:31 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildPlayersViewClick {
    public static void playerViewClick(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (event.getRawSlot() == 44){
            p.closeInventory();
            p.openInventory(MainGuildMenu.inv(g, p));
            return;
        }
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName() || !iS.getItemMeta().hasLore()){
            return;
        }
        String online = "online";
        for (String lore : iS.getItemMeta().getLore()){
            if (lore.contains("offline")){
                online = "offline";
            }
        }
        if (online.equalsIgnoreCase("offline")){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You may only perform commands on online players."));
            return;
        }
        String name = iS.getItemMeta().getDisplayName();
        if (Bukkit.getPlayer(name) == null){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " It seems that player has logged offline!"));
            return;
        }
        Player player = Bukkit.getPlayer(name);
        if (player.getName().equalsIgnoreCase(p.getName())){
            if (event.isRightClick()){
                if (g.getOwner().equalsIgnoreCase(p.getName())){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You must either disband your guild or promote someone else to leader before you can leave your guild!"));
                    return;
                }
                g.getPlayers().remove(p.getName());
                for (String players : g.getPlayers()){
                    if (Bukkit.getPlayer(players) == null){
                        continue;
                    }
                    Bukkit.getPlayer(players).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has just left your guild."));
                }

                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have now left your guild!"));
                cP.setGuildName("");
                cP.setGuildRank("");
                cP.setInGuild(false);
                p.closeInventory();
                return;
            }
            if (event.isLeftClick()){
                if (!g.getPlayerContrib().containsKey(p.getName())){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You have not contributed to your guild at all!"));
                    return;
                }
                p.sendMessage(ChatUtil.color(Strings.guildStub + " you have Contributed a total of " + g.getPlayerContrib().get(p.getName())));
                return;
            }

        }
        if (event.isLeftClick()){
            p.performCommand("party invite " + name);
            return;
        }
        if (event.isRightClick()){
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (gR.getRankPermissions().get(RankPerms.GUILD_TP)){
                if (!p.hasPermission("guilds.directTp")){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild allows direct tp's between guild members for your rank, but only VIP and up ranked members and higher may use this feature. A teleport request has been sent on your behalf."));
                    CustomPlayer cp1 = CustomPlayer.getCP(player.getName());
                    if (!cp1.isAcceptingTP()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " This player has teleport requests disabled."));
                        return;
                    }
                    p.closeInventory();
                    final String pName = p.getName();
                    final Location loc = p.getLocation();
                    final Player playerdest = player;
                    new BukkitRunnable(){
                        int i = 8;
                        @Override
                        public void run(){
                            if (Bukkit.getPlayer(pName) == null || !playerdest.isOnline()){
                                cancel();
                                return;
                            }
                            Player pl = Bukkit.getPlayer(pName);
                            if (i == 0){
                                playerdest.openInventory(IncommingTPRequestInventory.inv(pName));
                                pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild teleport request has been sent."));
                                cancel();
                                return;
                            }
                            if (pl.getLocation().distance(loc) >= 3){
                                pl.sendMessage(ChatUtil.color(Strings.guildStub + " Teleport has been cancelled due to movement!"));
                                cancel();
                                return;
                            }
                            pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild teleport request will be sent in " + i + " second."));
                            i--;
                        }
                    }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
                    return;
                }
                p.closeInventory();
                final String pName = p.getName();
                final Location loc = p.getLocation();
                final Player playerdest = player;
                new BukkitRunnable(){
                    int i = 8;
                    @Override
                    public void run(){
                        if (Bukkit.getPlayer(pName) == null || !playerdest.isOnline()){
                            cancel();
                            return;
                        }
                        Player pl = Bukkit.getPlayer(pName);
                        if (i == 0){
                            pl.teleport(playerdest.getLocation());
                            cancel();
                            return;
                        }
                        if (pl.getLocation().distance(loc) >= 3){
                            pl.sendMessage(ChatUtil.color(Strings.guildStub + " Teleport has been cancelled due to movement!"));
                            cancel();
                            return;
                        }
                        pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild teleport commencing in " + i + " second."));
                        i--;
                    }
                }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
                return;
            }
            if (gR.getRankPermissions().get(RankPerms.GUILD_TP_REQUEST)){
                CustomPlayer cp1 = CustomPlayer.getCP(player.getName());
                if (!cp1.isAcceptingTP()){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " This player has teleport requests disabled."));
                    return;
                }
                p.closeInventory();
                final String pName = p.getName();
                final Location loc = p.getLocation();
                final Player playerdest = player;
                new BukkitRunnable(){
                    int i = 8;
                    @Override
                    public void run(){
                        if (Bukkit.getPlayer(pName) == null || !playerdest.isOnline()){
                            cancel();
                            return;
                        }
                        Player pl = Bukkit.getPlayer(pName);
                        if (i == 0){
                            playerdest.openInventory(IncommingTPRequestInventory.inv(pName));
                            pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild teleport request has been sent."));
                            cancel();
                            return;
                        }
                        if (pl.getLocation().distance(loc) >= 3){
                            pl.sendMessage(ChatUtil.color(Strings.guildStub + " Teleport has been cancelled due to movement!"));
                            cancel();
                            return;
                        }
                        pl.sendMessage(ChatUtil.color(Strings.guildStub + " Guild teleport request will be sent in " + i + " second."));
                        i--;
                    }
                }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
                return;
            }
        }
    }
}
