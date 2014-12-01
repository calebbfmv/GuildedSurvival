package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.events.ChatCommands;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
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
 * Date: 11/26/13
 * Time: 11:14 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SettingsInventory implements Listener {
    public SettingsInventory(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    public static Inventory settingsMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("&eSettings Menu"));

        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isPvp()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bPvP Active"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn your"));
            acceptLore.add(ChatUtil.color("&6PvP status off."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(1, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bPvP Disabled"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn your"));
            declineLore.add(ChatUtil.color("&6PvP status on"));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(1, decline);
        }
        if (!cP.isdNd()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bDo Not Disturb"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn your"));
            acceptLore.add(ChatUtil.color("&6DnD status on."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(3, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bDo Not Disturb"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn your"));
            declineLore.add(ChatUtil.color("&6DnD status off"));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(3, decline);
        }
        if (cP.isAutoLoot()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAuto Loot Active"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn your"));
            acceptLore.add(ChatUtil.color("&6auto loot off."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(5, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bAuto Loot Disabled"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn your"));
            declineLore.add(ChatUtil.color("&6auto loot on"));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(5, decline);
        }
        if (cP.isAcceptingTP()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAccepting TP"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn"));
            acceptLore.add(ChatUtil.color("&6off teleport requests."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(15, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bTP Requests denied"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to start"));
            declineLore.add(ChatUtil.color("&6accepting teleport requests"));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(15, decline);
        }
        if (cP.isPartyOn()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAccepting party invites"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn off"));
            acceptLore.add(ChatUtil.color("&6party invites."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(11, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bParty invites disabled"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn on"));
            declineLore.add(ChatUtil.color("&6party invites."));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(11, decline);
        }
        if (cP.isAutoRoll()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAuto Roll Menu Enabled"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn off"));
            acceptLore.add(ChatUtil.color("&6roll menu auto pop-ups."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(9, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bAuto Roll Menu Disabled"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn on"));
            declineLore.add(ChatUtil.color("&6roll menu auto pop-ups."));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(9, decline);
        }
        if (cP.isGuildOn()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAccepting Guild Invtes"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn off"));
            acceptLore.add(ChatUtil.color("&6guild invites."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(13, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bGuild Invites Declined"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn on"));
            declineLore.add(ChatUtil.color("&6guild invites."));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(13, decline);
        }
        if (cP.isTradeOn()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAccepting trade invites"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Click here to turn off"));
            acceptLore.add(ChatUtil.color("&6trade invites."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(7, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bTrade invites disabled"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6Click here to turn on"));
            declineLore.add(ChatUtil.color("&6trade invites."));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(7, decline);
        }

        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(17, returnItem);

        return inv;
    }

    @EventHandler
    public void dndChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (!Lists.dndMessage.contains(p.getName())){
            return;
        }
        event.setCancelled(true);
        ChatCommands.dNdMap.put(p.getName(), event.getMessage());
        Lists.dndMessage.remove(p.getName());
        p.sendMessage(ChatUtil.color(Strings.chatStub + " You have set your DND Message to: " + event.getMessage()));
        return;
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void settingsClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            final Player p = (Player) event.getWhoClicked();
            if (!event.getInventory().getName().equalsIgnoreCase(ChatUtil.color("&eSettings Menu"))){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            Inventory inv = event.getInventory();
            final CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getRawSlot() == 3){
                if (cP.isdNd()){
                    cP.setdNd(false);
                    ChatCommands.dNdMap.remove(p.getName());
                    p.sendMessage(ChatUtil.color(Strings.chatStub + " You are no longer set to Do Not Disturb"));
                    p.closeInventory();
                } else {
                    Lists.dndMessage.add(p.getName());
                    cP.setdNd(true);
                    p.sendMessage(ChatUtil.color(Strings.chatStub + " You are now set to Do Not Disturb"));
                    p.sendMessage(ChatUtil.color(Strings.chatStub + " Please type your dnd message into chat"));
                    ChatCommands.dNdMap.put(p.getName(), "This Player is AFK");
                    p.closeInventory();
                }
                return;
            }
            if (event.getRawSlot() == 1){
                if (cP.isInGuild()){
                    Guild g = Maps.allGuilds.get(cP.getGuildName());
                    if (!g.isPeaceful()){
                        p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYou cannot change your pvp status if your guild is not peaceful!!!"));
                        return;
                    }
                }
                if (!cP.isPvp()){
                    p.closeInventory();
                    final Location loc = p.getLocation();
                    new BukkitRunnable(){
                        int i = 30;
                        @Override
                        public void run(){
                            if (p.getLocation().distance(loc) >= 3){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYou moved before your PvP was finished toggling!"));
                                cancel();
                                return;
                            }
                            if (i == 0){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYou are now flagged as active for PvP!"));
                                cP.setPvp(true);
                                cancel();
                                return;
                            }
                            if (i == 30 || i == 20 || i == 15 || i == 10 || i <= 5){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYour pvp status will be toggled on after "+i+" seconds! Please don't move!"));
                            }
                            i--;
                        }
                    }.runTaskTimer(UltimateSurvival.getInstance(), 10, 20);
                    return;
                } else {
                    p.closeInventory();
                    final Location loc = p.getLocation();
                    new BukkitRunnable(){
                        int i = 30;
                        @Override
                        public void run(){
                            if (p.getLocation().distance(loc) >= 3){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYou moved before your PvP was finished toggling!"));
                                cancel();
                                return;
                            }
                            if (i == 0){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYou are no longer flagged for PvP!"));
                                cP.setPvp(false);
                                cancel();
                                return;
                            }
                            if (i == 30 || i == 20 || i == 15 || i == 10 || i <= 5){
                                p.sendMessage(ChatUtil.color("&f[&4PvP&f] &bYour pvp status will be toggled off after "+i+" seconds! Please don't move!"));
                            }
                            i--;
                        }
                    }.runTaskTimer(UltimateSurvival.getInstance(), 10, 20);

                    return;
                }
            }
            if (event.getRawSlot() == 5){
                if (cP.isAutoLoot()){
                    cP.setAutoLoot(false);
                } else {
                    cP.setAutoLoot(true);
                }
                if (cP.isAutoLoot()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAuto Loot Active"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn your"));
                    acceptLore.add(ChatUtil.color("&6auto loot off."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(5, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bAuto Loot Disabled"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to turn your"));
                    declineLore.add(ChatUtil.color("&6auto loot on"));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(5, decline);
                }
                p.updateInventory();
            }
            if (event.getRawSlot() == 7){
                if (cP.isTradeOn()){
                    cP.setTradeOn(false);
                } else {
                    cP.setTradeOn(true);
                }
                if (cP.isTradeOn()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAccepting trade invites"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn off"));
                    acceptLore.add(ChatUtil.color("&6trade invites."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(7, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bTrade invites disabled"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to turn on"));
                    declineLore.add(ChatUtil.color("&6trade invites."));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(7, decline);
                }
                p.updateInventory();
            }
            if (event.getRawSlot() == 17){
                p.openInventory(MenuInventory.menu());
                p.closeInventory();
                return;
            }
            if (event.getRawSlot() == 15){
                if (cP.isAcceptingTP()){
                    cP.setAcceptingTP(false);
                } else {
                    cP.setAcceptingTP(true);
                }
                if (cP.isAcceptingTP()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAccepting TP"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn"));
                    acceptLore.add(ChatUtil.color("&6off teleport requests."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(15, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bTP Requests denied"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to start"));
                    declineLore.add(ChatUtil.color("&6accepting teleport requests"));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(15, decline);
                }
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 9){
                if (cP.isAutoRoll()){
                    cP.setAutoRoll(false);
                } else {
                    cP.setAutoRoll(true);
                }
                if (cP.isAutoRoll()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAuto Roll Menu Enabled"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn off"));
                    acceptLore.add(ChatUtil.color("&6roll menu auto pop-ups."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(9, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bAuto Roll Menu Disabled"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to turn on"));
                    declineLore.add(ChatUtil.color("&6roll menu auto pop-ups."));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(9, decline);
                }
            }
            if (event.getRawSlot() == 11){
                if (cP.isPartyOn()){
                    cP.setPartyOn(false);
                } else {
                    cP.setPartyOn(true);
                }
                if (cP.isPartyOn()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAccepting party invites"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn off"));
                    acceptLore.add(ChatUtil.color("&6party invites."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(11, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bParty invites disabled"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to turn on"));
                    declineLore.add(ChatUtil.color("&6party invites."));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(11, decline);
                }
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 13){
                if (cP.isGuildOn()){
                    cP.setGuildOn(false);
                } else {
                    cP.setGuildOn(true);
                }
                if (cP.isGuildOn()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAccepting Guild Invtes"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6Click here to turn off"));
                    acceptLore.add(ChatUtil.color("&6guild invites."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(13, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bGuild Invites Declined"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6Click here to turn on"));
                    declineLore.add(ChatUtil.color("&6guild invites."));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(13, decline);
                }
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 7){
                p.sendMessage("Achievements will be fully implemented in a future patch, but will be retroactive!");
                return;
            }
        }
    }
}
