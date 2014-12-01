package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.*;
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
 * Date: 12/1/13
 * Time: 11:54 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildManagementClick {
    @SuppressWarnings("deprecation")
	public static void appClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        if (event.getRawSlot() > 44){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (iS == null || iS.getType().equals(Material.AIR)){
            return;
        }
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        Player app;
        if ((app = Bukkit.getPlayer(iS.getItemMeta().getDisplayName())) == null){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " That player appears to have logged offline."));
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        CustomPlayer cApp = CustomPlayer.getCP(app.getName());
        if (event.isLeftClick()){
            cApp.setGuildName(g.getName());
            cApp.setInGuild(true);
            cApp.setApplying(false);
            cApp.setGuildRank(g.getDefaultRank().getName());
            g.getApplicants().remove(app.getName());
            for (String key : g.getPlayers()){
                if (Bukkit.getPlayer(key) != null){
                    Bukkit.getPlayer(key).sendMessage(ChatUtil.color(Strings.guildStub + " " + app.getName() + " has joined your guild!"));
                }
            }
            app.sendMessage(ChatUtil.color(Strings.guildStub + " you are now a member of " + g.getName() + "."));
            app.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getMotd()));
            g.getPlayers().add(app.getName());
            inv.setItem(event.getRawSlot(), null);
            p.updateInventory();
            return;
        }
        if (event.isRightClick()){
            g.getApplicants().remove(app.getName());
            cApp.setApplying(false);
            app.sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getName() + " has denied your guild application"));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have denied " + app.getName() + "'s application."));
            inv.setItem(event.getRawSlot(), null);
            p.updateInventory();
            return;
        }
    }
    public static void gManagementClick(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!cP.isInGuild()){
            p.closeInventory();
            p.sendMessage(ChatUtil.color(Strings.guildStub + " I have no clue how you got in this menu but only people in guilds should be in here!"));
            return;
        }
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        if (event.getRawSlot() == 2){
            p.closeInventory();
            p.openInventory(RankInventory.inv(g));
            return;
        }
        if (event.getRawSlot() == 4){
            p.closeInventory();
            p.openInventory(GuildPlayersEdit.inv(g));
            return;
        }
        if (event.getRawSlot() == 6){
            p.closeInventory();
            p.openInventory(EditHQ.inv(p));
            return;
        }
        if (event.getRawSlot() == 10){
            if (event.isLeftClick()){
                if (event.isShiftClick()){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type your new recruitment message."));
                    p.closeInventory();
                    Lists.addingRecruitmentMessage.add(p.getName());
                    cP.setChatBlocked(true);
                    return;
                } else {
                    g.setRecruiting(true);
                    for (String key : g.getPlayers()){
                        if (Bukkit.getPlayer(key) != null){
                            Bukkit.getPlayer(key).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild is now recruiting new players."));
                        }
                    }
                    p.closeInventory();
                    return;
                }
            } else {
                if (event.isShiftClick()){
                    if (g.getApplicants().isEmpty()){
                        p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not currently have any pending guild applications"));
                        return;
                    } else {
                        Inventory invApp = Bukkit.createInventory(null, 45, ChatUtil.color("&bOnline Applicants"));
                        for (String key : g.getApplicants().keySet()){
                            if (Bukkit.getPlayer(key) != null){
                                List<String> appLore = new ArrayList<>();
                                appLore.add(ChatUtil.color("&2Left click to accept this app,"));
                                appLore.add(ChatUtil.color("&4Right click to deny it."));
                                appLore.add(ChatUtil.color("&6Applicant Message"));
                                String[] args = g.getApplicants().get(key).split(" ");
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
                                    appLore.add(ChatUtil.color("&b" + mess.toString()));
                                    tot = i;
                                }
                                invApp.addItem(InventoryMaker.itemStackMaker(key, Material.SKULL_ITEM, 1, (short)0, appLore));
                            }

                        }
                        p.closeInventory();
                        p.openInventory(invApp);
                        return;
                    }
                } else {
                    g.setRecruiting(false);
                    for (String key : g.getPlayers()){
                        if (Bukkit.getPlayer(key) != null){
                            Bukkit.getPlayer(key).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild is no longer recruiting new players."));
                        }
                    }
                    p.closeInventory();
                    return;
                }
            }
        }
        if (event.getRawSlot() == 12){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type your new Guild Motd."));
            p.closeInventory();
            Lists.addingMotd.add(p.getName());
            cP.setChatBlocked(true);
            return;
        }
        if (event.getRawSlot() == 14){
            p.closeInventory();
            p.openInventory(DisbandInventory.inv(g));
            return;
        }
        if (event.getRawSlot() == 17){
            p.closeInventory();
            p.openInventory(MainGuildMenu.inv(g, p));
            return;
        }
    }
}
