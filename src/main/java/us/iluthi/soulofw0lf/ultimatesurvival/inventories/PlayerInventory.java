package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.trade.TradeHandler;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 6:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerInventory implements Listener{
    public PlayerInventory(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void playerClick(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player)){
            return;
        }
        if (!p.isSneaking()){
            return;
        }
        Player pl = (Player)event.getRightClicked();
        p.openInventory(playerInventory(pl));
    }
    @EventHandler
    public void playerClickInv(InventoryClickEvent event){
        if (!event.getInventory().getName().contains("Player Menu")){
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        String[] args = event.getInventory().getName().split(":");
        String name = args[1];
        if (Bukkit.getPlayer(name) == null){
            p.closeInventory();
            p.sendMessage(ChatUtil.color("&f[&bPlayer Manager&f] &6The player you were viewing has logged offline."));
            return;
        }
        Player pl = Bukkit.getPlayer(name);
        if (!pl.getWorld().getName().equals(p.getWorld().getName()) || pl.getLocation().distance(p.getLocation()) >= 50 && !p.hasPermission("double.coins")){
            p.closeInventory();
            p.sendMessage(ChatUtil.color("&f[&bPlayer Manager&f] &6The player you were viewing has moved too far away from you!"));
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        CustomPlayer cP1 = CustomPlayer.getCP(pl.getName());
        if (event.getRawSlot() == 1){
            p.performCommand("party invite " + pl.getName());
            p.closeInventory();
            return;
        }

        if (event.getRawSlot() == 3){
            if (cP1.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " &bYou cannot invite a player that has you on ignore!"));
                return;
            }
            if (cP1.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " That player is already in a guild."));
                return;
            }
            if (!cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " We have not yet learned how to let you invite people into imaginary guilds, but our highly trained teams of scuba diving attack unicorns is on it!"));
                return;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (!gR.getRankPermissions().get(RankPerms.INVITE)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to invite people to your guild at this time. Please talk to you guild leader if you think this is in error!"));
                return;
            }
            if (!cP1.isGuildOn()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " That player is not accepting guild invites."));
                return;
            }
            if (guildInvites.containsKey(pl.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " That player already has a pending guild invite."));
                return;
            }
            guildInvites.put(pl.getName(), g);
            pl.openInventory(guildInvite(g, p.getName()));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have invited " + pl.getName() + " to join your guild."));
            p.closeInventory();
            return;

        }
        if (event.getRawSlot() == 5){
            if (cP1.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou cannot duel a player that has you on ignore!"));
                return;
            }
            if (!cP1.isDuelsOn()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player does not accept duels!"));
                return;
            }
            if (cP1.isInDuel()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel."));
                return;
            }
            if (Maps.duelInvited.containsKey(pl.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            if (Maps.duelInvite.containsKey(pl.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            if (Maps.duelInvited.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b You cannot invite someone to a duel while you have a pending duel invite!"));
                return;
            }
            if (Maps.duelInvite.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b You have already invited someone to a duel!"));
                return;
            }
            if (Lists.duelWaiting.contains(pl.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            Maps.duelInvite.put(p.getName(), pl.getName());
            Maps.duelInvited.put(pl.getName(), p.getName());
            pl.openInventory(DuelInventory.duelInv(p));
            final String name1 = pl.getName();
            final String name2 = p.getName();
            p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou have sent a duel request to " + pl.getName() + "!"));
            p.closeInventory();
            new BukkitRunnable(){
                @Override
                public void run(){
                    if (Maps.duelInvite.containsKey(name2) && Maps.duelInvited.containsKey(name1)){
                        if (Bukkit.getPlayer(name1) != null){
                            Bukkit.getPlayer(name1).sendMessage("&f[&eDuels&f] &b You have refused a duel from " + name2 + ".");
                        }
                        if (Bukkit.getPlayer(name2) != null){
                            Bukkit.getPlayer(name2).sendMessage("&f[&eDuels&f] &b Your duel request to "+name1+" has timed out.");
                        }
                        Maps.duelInvited.remove(name1);
                        Maps.duelInvite.remove(name2);
                        cancel();
                        return;
                    }
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 600);
        }
        if (event.getRawSlot() == 7){
            p.openInventory(playerStats(pl));
            return;
        }
        if (event.getRawSlot() == 9){
            TradeHandler.requestTrade(p, pl.getName());
            p.closeInventory();
            return;
        }
        if (event.getRawSlot() == 13){
            if (pl.hasPermission("mod") || pl.hasPermission("admin") || pl.hasPermission("youtube") || pl.hasPermission("builder") || pl.hasPermission("owner")){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You cannot ignore this player"));
                return;
            }
            cP.getIgnoredPlayers().add(pl.getName());
            p.sendMessage(ChatUtil.color(Strings.chatStub + " you have added " + pl.getName() + " to your ignore list."));
            return;
        }
    }
    @EventHandler
    public void statsMenuClick(InventoryClickEvent event){
        if (!event.getInventory().getName().contains("Player Stats:")){
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        String[] args = event.getInventory().getName().split(":");
        String name = args[1].replace(" ", "");
        if (Bukkit.getPlayer(name) == null){
            p.closeInventory();
            p.sendMessage(ChatUtil.color("&f[&bPlayer Manager&f] &6The player you were viewing has logged offline."));
            return;
        }
        Player pl = Bukkit.getPlayer(name);
        if (!pl.getWorld().getName().equals(p.getWorld().getName()) || pl.getLocation().distance(p.getLocation()) >= 50){
            p.closeInventory();
            p.sendMessage(ChatUtil.color("&f[&bPlayer Manager&f] &6The player you were viewing has moved too far away from you!"));
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(pl.getName());
        if (event.getRawSlot() == 2){
            if (!cP.getBlockBreaks().isEmpty()){
                int i = 0;
                for (String key : cP.getBlockBreaks().keySet()){
                    i = i + cP.getBlockBreaks().get(key);
                }
                p.sendMessage(ChatUtil.color("&b-=@@==================@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@== &6Block Breaks &b==@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@        &4"+i+"&b          @@=-"));
            }
            if (!cP.getBlockPlacement().isEmpty()){
                int r = 0;
                for (String key : cP.getBlockPlacement().keySet()){
                    r = r + cP.getBlockPlacement().get(key);
                }
                p.sendMessage(ChatUtil.color("&b-=@@== &6Blocks Placed &b==@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@        &4"+r+"&b          @@=-"));
            }
            if (!cP.getPlayerKills().isEmpty()){
                int x = 0;
                for (String key : cP.getPlayerKills().keySet()){
                    x = x + cP.getPlayerKills().get(key);
                }
                p.sendMessage(ChatUtil.color("&b-=@@== &6Player Kills &b==@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@        &4"+x+"&b          @@=-"));
            }
            if (!cP.getMobKills().isEmpty()){
                int y = 0;
                for (String key : cP.getMobKills().keySet()){
                    y = y + cP.getMobKills().get(key);
                }
                p.sendMessage(ChatUtil.color("&b-=@@== &6Monster Kills &b==@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@        &4"+y+"&b          @@=-"));
            }
            p.sendMessage(ChatUtil.color("&b-=@@== &6Coins &b==@@=-"));
            p.sendMessage(ChatUtil.color("&b-=@@        &4"+cP.getGoldPoints()+"&b          @@=-"));
            if (cP.isInGuild()){
                p.sendMessage(ChatUtil.color("&b-=@@== &6Guild &b==@@=-"));
                p.sendMessage(ChatUtil.color("&b-=@@ &2"+cP.getGuildName()+"&b @@=-"));
            }
            p.sendMessage(ChatUtil.color("&b-=@@== &6Time Played &b==@@=-"));
            p.sendMessage(ChatUtil.color("&b-=@@ "+"&2Hours: "+ TimeUnit.MILLISECONDS.toHours(cP.getTimePlayed()) + "&b @@=-"));
            return;
        }
        if (event.getRawSlot() == 4){
            if (!cP.getPlayerKills().isEmpty()){
                for (String key : cP.getPlayerKills().keySet()){
                    p.sendMessage(ChatUtil.color("&f[&bPlayer Kills&f] &6" + key + " &f: &4" + cP.getPlayerKills().get(key)));
                }
            } else {
                p.sendMessage(ChatUtil.color("&4This player has not killed anyone."));
            }
            return;
        }
        if (event.getRawSlot() == 6){
            if (!cP.getMobKills().isEmpty()){
                for (String key : cP.getMobKills().keySet()){
                    p.sendMessage(ChatUtil.color("&f[&bMonster Kills&f] &6" + key + " &f: &4" + cP.getMobKills().get(key)));
                }
            } else {
                p.sendMessage(ChatUtil.color("&4This player has not killed any monsters."));
            }
            return;
        }
        if (event.getRawSlot() == 12){
            if (!cP.getBlockBreaks().isEmpty()){
                for (String key : cP.getBlockBreaks().keySet()){
                    p.sendMessage(ChatUtil.color("&f[&bBlock Breaks&f] &6" + key + " &f: &4" + cP.getBlockBreaks().get(key)));
                }
            } else {
                p.sendMessage(ChatUtil.color("&4This player has not broken any blocks."));
            }
            return;
        }
        if (event.getRawSlot() == 14){
            if (!cP.getBlockPlacement().isEmpty()){
                for (String key : cP.getBlockPlacement().keySet()){
                    p.sendMessage(ChatUtil.color("&f[&bBlocks Placed&f] &6" + key + " &f: &4" + cP.getBlockPlacement().get(key)));
                }
            } else {
                p.sendMessage(ChatUtil.color("&4This player has not placed any blocks."));
            }
            return;
        }
        if (event.getRawSlot() == 17){
            p.openInventory(playerInventory(pl));
            p.closeInventory();
            return;
        }
    }
    public static Inventory playerStats(Player p){
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("&6Player Stats: " + p.getName()));

        ItemStack generalStats = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta generalM = generalStats.getItemMeta();
        generalM.setDisplayName(ChatUtil.color("&bGeneral Stats"));
        List<String> generalLore = new ArrayList<>();
        generalLore.add(ChatUtil.color("&2Click here to view this"));
        generalLore.add(ChatUtil.color("&2players general stats."));
        generalM.setLore(generalLore);
        generalStats.setItemMeta(generalM);
        inv.setItem(2, generalStats);

        ItemStack pkStats = new ItemStack(Material.SKULL, 1);
        ItemMeta pkM = pkStats.getItemMeta();
        pkM.setDisplayName(ChatUtil.color("&bPlayer Kill Stats"));
        List<String> pkLore = new ArrayList<>();
        pkLore.add(ChatUtil.color("&2Click here to view this"));
        pkLore.add(ChatUtil.color("&2players P.K. stats."));
        pkM.setLore(pkLore);
        pkStats.setItemMeta(pkM);
        inv.setItem(4, pkStats);

        ItemStack mkStats = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta mkM = mkStats.getItemMeta();
        mkM.setDisplayName(ChatUtil.color("&bMob Kill Stats"));
        List<String> mkLore = new ArrayList<>();
        mkLore.add(ChatUtil.color("&2Click here to view this"));
        mkLore.add(ChatUtil.color("&2players M.K. stats."));
        mkM.setLore(mkLore);
        mkStats.setItemMeta(mkM);
        inv.setItem(6, mkStats);

        ItemStack blockbreakStats = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta blockbreakM = blockbreakStats.getItemMeta();
        blockbreakM.setDisplayName(ChatUtil.color("&bBlock Break Stats"));
        List<String> blockbreakLore = new ArrayList<>();
        blockbreakLore.add(ChatUtil.color("&2Click here to view this"));
        blockbreakLore.add(ChatUtil.color("&2players Block Break stats."));
        blockbreakM.setLore(blockbreakLore);
        blockbreakStats.setItemMeta(blockbreakM);
        inv.setItem(12, blockbreakStats);

        ItemStack blockplaceStats = new ItemStack(Material.WOOD, 1);
        ItemMeta blockplaceM = blockplaceStats.getItemMeta();
        blockplaceM.setDisplayName(ChatUtil.color("&bBlock Place Stats"));
        List<String> blockplaceLore = new ArrayList<>();
        blockplaceLore.add(ChatUtil.color("&2Click here to view this"));
        blockplaceLore.add(ChatUtil.color("&2players Block Place stats."));
        blockplaceM.setLore(blockplaceLore);
        blockplaceStats.setItemMeta(blockplaceM);
        inv.setItem(14, blockplaceStats);

        ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta declineM = decline.getItemMeta();
        declineM.setDisplayName(ChatUtil.color("&bPrevious Menu"));
        List<String> declineLore = new ArrayList<>();
        declineLore.add(ChatUtil.color("&6Click here to return to"));
        declineLore.add(ChatUtil.color("&6the player overview menu."));
        declineM.setLore(declineLore);
        decline.setItemMeta(declineM);
        inv.setItem(17, decline);

        return inv;
    }
    public static Map<String, Guild> guildInvites = new HashMap<>();
    @EventHandler
    public void playerGuildAcceptCommand(PlayerCommandPreprocessEvent event){
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "");
        Player p = event.getPlayer();
        if (cmd.equalsIgnoreCase("gaccept")){
            if (!guildInvites.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have any pending guild invites!"));
                return;
            }
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You already joined a guild.. don't be greedy!"));
                return;
            }
            Guild g = guildInvites.get(p.getName());
            for (String player : g.getPlayers()){
                if (Bukkit.getPlayer(player) != null){
                    Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has joined the guild!"));
                }
            }
            g.getPlayers().add(p.getName());
            cP.setInGuild(true);
            cP.setGuildName(g.getName());
            cP.setGuildRank(g.getDefaultRank().getName());
            guildInvites.remove(p.getName());
            return;
        }
        if (cmd.equalsIgnoreCase("gdecline")){
            if (!guildInvites.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have any pending guild invites!"));
                return;
            }
            guildInvites.remove(p.getName());
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have refused a guild invite."));
        }
    }
    @EventHandler
    public void gInviteClose(InventoryCloseEvent event){
        if (!event.getInventory().getName().contains("Guild Invite:")){
            return;
        }
        Player p = (Player)event.getPlayer();
        if (guildInvites.containsKey(p.getName())){
            guildInvites.remove(p.getName());
        }
    }
    @EventHandler
    public void gInviteClick(InventoryClickEvent event){
        if (!event.getInventory().getName().contains("Guild Invite:")){
            return;
        }
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            if (!guildInvites.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have any pending guild invites!"));
                p.closeInventory();
                return;
            }
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You already joined a guild.. don't be greedy!"));
                p.closeInventory();
                return;
            }
            if (event.getRawSlot() == 3){
                ItemStack iS = event.getCurrentItem();
                List<String> lores = iS.getItemMeta().getLore();
                String[] args = lores.get(1).split(":");
                String name = args[1];
                if (Bukkit.getPlayer(name) != null){
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has accepted your guild invite!"));
                }
                Guild g = guildInvites.get(p.getName());
                for (String player : g.getPlayers()){
                    if (Bukkit.getPlayer(player) != null){
                        Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has joined the guild!"));
                    }
                }
                g.getPlayers().add(p.getName());
                cP.setInGuild(true);
                cP.setGuildName(g.getName());
                cP.setGuildRank(g.getDefaultRank().getName());
                p.closeInventory();
                return;
            }
            if (event.getRawSlot() == 5){
                ItemStack iS = event.getCurrentItem();
                List<String> lores = iS.getItemMeta().getLore();
                String[] args = lores.get(1).split(":");
                String name = args[1];
                if (Bukkit.getPlayer(name) != null){
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color(Strings.guildStub + "&4 " + p.getName() + " has declined your guild invite."));
                }
                guildInvites.remove(p.getName());
                p.closeInventory();
                return;
            }
        }
    }
    public static Inventory guildInvite(Guild g, String name){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&bGuild Invite:" + g.getName()));

        ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta acceptM = accept.getItemMeta();
        acceptM.setDisplayName(ChatUtil.color("&bAccept Invite"));
        List<String> acceptLore = new ArrayList<>();
        acceptLore.add(ChatUtil.color("&6Click here to accept"));
        acceptLore.add(ChatUtil.color("&6a guild invite from:" + name));
        acceptLore.add(ChatUtil.color("&6to join " + g.getName()));
        acceptM.setLore(acceptLore);
        accept.setItemMeta(acceptM);
        inv.setItem(3, accept);

        ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta declineM = decline.getItemMeta();
        declineM.setDisplayName(ChatUtil.color("&bDecline Invite"));
        List<String> declineLore = new ArrayList<>();
        declineLore.add(ChatUtil.color("&6Click here to decline this"));
        declineLore.add(ChatUtil.color("&6guild invite from:" + name));
        declineM.setLore(declineLore);
        decline.setItemMeta(declineM);
        inv.setItem(5, decline);

        return inv;
    }
    public static Inventory playerInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bPlayer Menu:") + p.getName());

        ItemStack party = new ItemStack(Material.CAKE, 1);
        ItemMeta partyM = party.getItemMeta();
        partyM.setDisplayName(ChatUtil.color("&bParty Invite"));
        List<String> partyLore = new ArrayList<>();
        partyLore.add(ChatUtil.color("&6Click here to invite this"));
        partyLore.add(ChatUtil.color("&6player to your party"));
        partyM.setLore(partyLore);
        party.setItemMeta(partyM);
        inv.setItem(1, party);
        
        ItemStack guild = new ItemStack(Material.ANVIL, 1);
        ItemMeta guildM = guild.getItemMeta();
        guildM.setDisplayName(ChatUtil.color("&bGuild Invite"));
        List<String> guildLore = new ArrayList<>();
        guildLore.add(ChatUtil.color("&6Click here to invite this"));
        guildLore.add(ChatUtil.color("&6player to your guild"));
        guildM.setLore(guildLore);
        guild.setItemMeta(guildM);
        inv.setItem(3, guild);

        ItemStack duel = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta duelM = duel.getItemMeta();
        duelM.setDisplayName(ChatUtil.color("&bDuel Invite"));
        List<String> duelLore = new ArrayList<>();
        duelLore.add(ChatUtil.color("&6Click here to invite this"));
        duelLore.add(ChatUtil.color("&6player to duel you."));
        duelM.setLore(duelLore);
        duel.setItemMeta(duelM);
        inv.setItem(5, duel);

        ItemStack stats = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta statsM = stats.getItemMeta();
        statsM.setDisplayName(ChatUtil.color("&bPlayer stats"));
        List<String> statsLore = new ArrayList<>();
        statsLore.add(ChatUtil.color("&6Click here to view this"));
        statsLore.add(ChatUtil.color("&6player's stats"));
        statsM.setLore(statsLore);
        stats.setItemMeta(statsM);
        inv.setItem(7, stats);
        
        ItemStack trade = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta tradeM = trade.getItemMeta();
        tradeM.setDisplayName(ChatUtil.color("&bInvite to trade"));
        List<String> tradeLore = new ArrayList<>();
        tradeLore.add(ChatUtil.color("&6Click here to invite this"));
        tradeLore.add(ChatUtil.color("&6player to trade"));
        tradeM.setLore(tradeLore);
        trade.setItemMeta(tradeM);
        inv.setItem(9, trade);

        ItemStack ignore = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta ignoreM = ignore.getItemMeta();
        ignoreM.setDisplayName(ChatUtil.color("&bIgnore Player"));
        List<String> ignoreLore = new ArrayList<>();
        ignoreLore.add(ChatUtil.color("&6Click here to ignore this"));
        ignoreLore.add(ChatUtil.color("&6player."));
        ignoreM.setLore(ignoreLore);
        ignore.setItemMeta(ignoreM);
        inv.setItem(13, ignore);

        return inv;
    }



















    public static Inventory deathInv(List<ItemStack> drops, Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&4Death Chest"));
        for (ItemStack iS : drops){
            if (iS == null || iS.getType().equals(Material.AIR)){
                continue;
            }
            if (iS.hasItemMeta() && iS.getItemMeta().hasDisplayName() && iS.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bBaconMC &6Quick &bMenu"))){
                continue;
            }
            if (iS.hasItemMeta() && iS.getItemMeta().hasLore()){
                boolean rollItem = false;
                for(String lores : Lists.loreList){
                    if(iS.getItemMeta().getLore().contains(lores))
                    {
                        Party party = null;
                        if((party = Util.getParty(p.getName())) == null)
                            continue;
                        party.addItem(iS, lores);
                        rollItem = true;
                    }
                }
                if (rollItem){
                    continue;
                }
            }
            if (cP.isAutoLoot()){
                p.getInventory().addItem(iS);

            }else {
                inv.addItem(iS);
            }
        }
        if (cP.isAutoLoot()){
            return null;
        }
        return inv;
    }
}
