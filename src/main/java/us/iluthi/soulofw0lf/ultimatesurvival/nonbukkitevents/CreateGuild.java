package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/27/13
 * Time: 12:57 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class CreateGuild implements Listener {
    public CreateGuild(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    public static List<String> settingGuildName = new ArrayList<>();
    public static List<String> settingLeaderRank = new ArrayList<>();
    public static List<String> settingDefaultRank = new ArrayList<>();
    public static List<String> defaultPerms = new ArrayList<>();
    public static void stepOne(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getRawSlot() == 8){
                p.closeInventory();
                p.openInventory(MenuInventory.menu());
                return;
            }
            if (event.getRawSlot() == 3){
                if (cP.isApplying()){
                    if (!Maps.allGuilds.containsKey(cP.getApplyGuild())){
                        cP.setApplying(false);
                        p.sendMessage(ChatUtil.color(Strings.guildStub + "The guild you were applying for no longer exists!"));
                        return;
                    }
                    Guild g = Maps.allGuilds.get(cP.getApplyGuild());
                    if (event.isLeftClick()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " You are already applying to join " + g.getName()));
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " This guild currently has " + g.getPlayers().size() + " players in it."));
                        if (g.hasHq()){
                            p.sendMessage(ChatUtil.color(Strings.guildStub + " and has a Head Quarters set."));
                        } else {
                            p.sendMessage(ChatUtil.color(Strings.guildStub + " and does not have a Head Quarters set."));
                        }
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " right click to quit applying to this guild."));
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " Recruitment Message:"));
                        p.sendMessage(ChatUtil.color(g.getRecruitMessage()));
                        return;
                    } else {
                        g.getApplicants().remove(p.getName());
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " You are no longer applying to join " + g.getName()));
                        cP.setApplying(false);
                        return;
                    }
                } else {
                    p.closeInventory();
                    Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Recruiting Guilds"));
                    for (Guild g : Maps.allGuilds.values()){
                        if (g.isRecruiting()){
                            List<String> recruitLore = new ArrayList<>();
                            recruitLore.add(ChatUtil.color("&bThis guild currently has"));
                            recruitLore.add(ChatUtil.color("&6" + g.getPlayers().size() + " &bplayers, and"));
                            if (g.hasHq()){
                                recruitLore.add(ChatUtil.color("&bhas a Head Quarters set."));
                            } else {
                                recruitLore.add(ChatUtil.color("&bhas no Head Quarters."));
                            }
                            recruitLore.add(ChatUtil.color("&2Recruitment Message:"));
                            if (g.getRecruitMessage() == null){
                                g.setRecruitMessage("We are recruiting.");
                            }
                            String[] args = g.getRecruitMessage().split(" ");
                            int i = 0;
                            int tot = 0;
                            while (tot < args.length){
                                int len = 0;
                                StringBuilder mess = new StringBuilder();
                                while (len < 5 && i < args.length){
                                    mess.append(args[i]).append(" ");
                                    i++;
                                    len++;
                                }
                                recruitLore.add(ChatUtil.color("&b" + mess.toString()));
                                tot = i;
                            }
                            inv.addItem(InventoryMaker.itemStackMaker(g.getName(), Material.BOOK, 1, (short)0, recruitLore));
                        }
                    }
                    p.openInventory(inv);
                    return;
                }
            }
            if (event.getRawSlot() == 5){
              /*  if (!p.hasPermission("double.coins")){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You must be a vip to create a guild!"));
                    return;
                }
                */
                if (cP.getGoldPoints() <= 1999){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot afford to purchase a guild at this time. You have " + cP.getGoldPoints() + " out of 2000 coins needed."));
                    p.closeInventory();
                    return;
                }
                settingGuildName.add(p.getName());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You are now creating a guild! Please type your guild name into chat to continue!"));
                cP.setChatBlocked(true);
                p.closeInventory();
                return;
            }
        }
    }
    @SuppressWarnings("deprecation")
	public static void appClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();

        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        ItemStack iS = event.getCurrentItem();
        if (iS == null || iS.getType().equals(Material.AIR) || !iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        if (event.getRawSlot() > 44){
            return;
        }
        String key = iS.getItemMeta().getDisplayName();
        if (!Maps.allGuilds.containsKey(key)){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " That guild appears to no longer exist!"));
            inv.setItem(event.getRawSlot(), null);
            p.updateInventory();
            return;
        }
        Guild g = Maps.allGuilds.get(key);
        if (!g.isRecruiting()){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " That guild appears to no longer be recruiting."));
            inv.setItem(event.getRawSlot(), null);
            p.updateInventory();
            return;
        }
        Lists.addingApplicantMessage.add(p.getName());
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        cP.setApplying(true);
        cP.setApplyGuild(g.getName());
        p.sendMessage(ChatUtil.color(Strings.guildStub + "Please type a message to the guild you are applying to!"));
        cP.setChatBlocked(true);
        p.closeInventory();
        return;
    }
    @EventHandler
    public void applicantChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (Lists.addingApplicantMessage.contains(p.getName())){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (!Maps.allGuilds.containsKey(cP.getApplyGuild())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " That guild appears to no longer exist!"));
                cP.setApplying(false);
                return;
            }
            Guild g = Maps.allGuilds.get(cP.getApplyGuild());
            g.getApplicants().put(p.getName(), event.getMessage());
            for (String key : g.getPlayers()){
                Player gMember;
                if ((gMember = Bukkit.getPlayer(key)) == null){
                    continue;
                }
                CustomPlayer gP = CustomPlayer.getCP(gMember.getName());
                GuildRanks gR = g.getRanks().get(gP.getGuildRank());
                if (gR == null){
                    continue;
                }
                if (!gR.getRankPermissions().get(RankPerms.RECRUITMENT)){
                    continue;
                }
                gMember.sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has just applied to your guild with the following message."));
                gMember.sendMessage(ChatUtil.color(event.getMessage()));
            }
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have successfully applied to " + g.getName() + ". You may only have one guild application at a time. "));
            cP.setChatBlocked(false);
            Lists.addingApplicantMessage.remove(p.getName());
            return;
        }
    }

    @EventHandler
    public void step2(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (settingGuildName.contains(p.getName())){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getMessage().length() >= 17){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Guild names may not be more than 16 characters!!!"));
                return;
            }
            if (!StringUtils.isAlphanumeric(event.getMessage())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Guild names may only contain letters and numbers."));
                return;
            }
            DBCollection table = UltimateSurvival.db.getCollection("guilds");
            BasicDBObject entry = new BasicDBObject();
            entry.put("name", event.getMessage());
            if (table.find(entry).hasNext()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " A guild with that name already exists."));
                return;
            }
            for (String key : Maps.allGuilds.keySet()){
                if (key.equalsIgnoreCase(event.getMessage())){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " A guild with that name already exists."));
                    return;
                }
            }
            cP.setGuildName(event.getMessage());
            settingGuildName.remove(p.getName());
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have created a new guild called " + event.getMessage() + "."));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please Type in the name of the Guild Leader Rank."));
            new BukkitRunnable(){
                @Override
                public void run(){
                    settingLeaderRank.add(p.getName());
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 2);

            return;
        }
    }
    @EventHandler
    public void step3(AsyncPlayerChatEvent event){
       final  Player p = event.getPlayer();
        if (settingLeaderRank.contains(p.getName())){
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
            cP.setGuildRank(event.getMessage());
            Guild g = new Guild(cP.getGuildName(), p.getName(), event.getMessage());
            settingLeaderRank.remove(p.getName());
            cP.setInGuild(true);
            GuildRanks gR = g.getRanks().get(event.getMessage());
            gR.setWeight(100);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have created a new guild rank called " + event.getMessage() + "."));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please Type in the name of the Default Guild Rank."));
            new BukkitRunnable(){
                @Override
                public void run(){
                    settingDefaultRank.add(p.getName());
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 2);

            return;
        }
    }
    @EventHandler
    public void step4(AsyncPlayerChatEvent event){
        final Player p = event.getPlayer();
        if (settingDefaultRank.contains(p.getName())){
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
            if (g.getRanks().containsKey(event.getMessage())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You must select a unique rank name."));
                return;
            }
            final GuildRanks gR = new GuildRanks(event.getMessage(), g.getName());
            g.setDefaultRank(gR);
            settingDefaultRank.remove(p.getName());
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have created a new guild rank called " + event.getMessage() + "."));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please Edit this ranks permissions."));
            defaultPerms.add(p.getName());
            new BukkitRunnable(){
                @Override
                public void run(){
                    p.openInventory(step5(gR));
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 30);
        }
    }
    public static Inventory step5(GuildRanks g){
        Inventory inv = Bukkit.createInventory(null, 45, g.getName());
        for (RankPerms rank : g.getRankPermissions().keySet()){
            ItemStack iS = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(rank.name().toLowerCase().replaceAll("_", " "));
            List<String> rankLore = new ArrayList<>();
            rankLore.add(ChatUtil.color("&bClick to set"));
            rankLore.add(ChatUtil.color("&bthis permission to false"));
            rankLore.add(ChatUtil.color("&bfor this rank."));
            iM.setLore(rankLore);
            iS.setItemMeta(iM);
            inv.addItem(iS);
        }
        return inv;
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void step5Click(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();

            if (!defaultPerms.contains(p.getName())){
                 return;
            }
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            GuildRanks rank = Maps.allGuilds.get(cP.getGuildName()).getDefaultRank();
            if (rank == null){
                rank = new GuildRanks("default", cP.getGuildName());
            }
            Inventory inv = event.getInventory();
            if (!inv.getName().equalsIgnoreCase(rank.getName())){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR) || !event.getCurrentItem().hasItemMeta() || !event.getCurrentItem().getItemMeta().hasDisplayName()){
                return;
            }
            ItemStack iS = event.getCurrentItem();
            String permName = iS.getItemMeta().getDisplayName().replace(" ", "_").toUpperCase();
            RankPerms gRank = RankPerms.valueOf(permName);
            if (rank.getRankPermissions().get(gRank)){
                rank.getRankPermissions().remove(gRank);
                iS.setType(Material.REDSTONE_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to true"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
                rank.getRankPermissions().put(gRank, false);
            } else {
                rank.getRankPermissions().remove(gRank);
                iS.setType(Material.EMERALD_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to false"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
                rank.getRankPermissions().put(gRank, true);
            }
            p.updateInventory();

        }
    }
    @EventHandler
    public void step5Close(InventoryCloseEvent event){
        if (event.getPlayer() instanceof Player){
            Player p = (Player)event.getPlayer();
            if (!defaultPerms.contains(p.getName())){
                return;
            }
            Inventory inv = event.getInventory();
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            GuildRanks rank = Maps.allGuilds.get(cP.getGuildName()).getDefaultRank();
            if (!inv.getName().equalsIgnoreCase(rank.getName())){
                return;
            }
            CoinChange.add(p, -2000, true);
            SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
            defaultPerms.remove(p.getName());
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have finished creating your guild!"));
            cP.setChatBlocked(false);
        }
    }
}
