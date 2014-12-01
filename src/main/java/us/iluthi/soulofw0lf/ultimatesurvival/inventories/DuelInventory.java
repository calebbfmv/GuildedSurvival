package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/26/13
 * Time: 1:40 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DuelInventory implements Listener {
    public DuelInventory(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    public static Inventory duelInv (Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&eDuel Invite"));
        ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta acceptM = accept.getItemMeta();
        acceptM.setDisplayName(ChatUtil.color("&bAccept"));
        List<String> acceptLore = new ArrayList<>();
        acceptLore.add(ChatUtil.color("&6Click here to accept"));
        acceptLore.add(ChatUtil.color("&6a duel from " + p.getName()));
        acceptM.setLore(acceptLore);
        accept.setItemMeta(acceptM);
        inv.setItem(3, accept);

        ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta declineM = decline.getItemMeta();
        declineM.setDisplayName(ChatUtil.color("&bDecline"));
        List<String> declineLore = new ArrayList<>();
        declineLore.add(ChatUtil.color("&6Click here to decline"));
        declineLore.add(ChatUtil.color("&6a duel from " + p.getName()));
        declineM.setLore(declineLore);
        decline.setItemMeta(declineM);
        inv.setItem(5, decline);
        return inv;
    }
    public static Inventory duelMain (Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&bDuel Menu"));

        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isDuelsOn()){
            ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta acceptM = accept.getItemMeta();
            acceptM.setDisplayName(ChatUtil.color("&bAccepting Duel Invites"));
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6You are currently accepting"));
            acceptLore.add(ChatUtil.color("&6duels. Click here to turn"));
            acceptLore.add(ChatUtil.color("&6this feature off."));
            acceptM.setLore(acceptLore);
            accept.setItemMeta(acceptM);
            inv.setItem(3, accept);
        } else {
            ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta declineM = decline.getItemMeta();
            declineM.setDisplayName(ChatUtil.color("&bDeclining Duel Invites"));
            List<String> declineLore = new ArrayList<>();
            declineLore.add(ChatUtil.color("&6You are currently declining"));
            declineLore.add(ChatUtil.color("&6duels. Click here to turn"));
            declineLore.add(ChatUtil.color("&6this feature on."));
            declineM.setLore(declineLore);
            decline.setItemMeta(declineM);
            inv.setItem(3, decline);
        }
        Short dura = 3;
        ItemStack decline = new ItemStack(Material.SKULL_ITEM, 1);
        ItemMeta declineM = decline.getItemMeta();
        declineM.setDisplayName(ChatUtil.color("&bDuel Invite Menu"));
        List<String> declineLore = new ArrayList<>();
        declineLore.add(ChatUtil.color("&6Click here to invite a"));
        declineLore.add(ChatUtil.color("&6nearby player to a duel."));
        declineM.setLore(declineLore);
        decline.setItemMeta(declineM);
        decline.setDurability(dura);
        inv.setItem(5, decline);

        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitM = exit.getItemMeta();
        exitM.setDisplayName(ChatUtil.color("&bExit"));
        List<String> exitLore = new ArrayList<>();
        exitLore.add(ChatUtil.color("&6Click here to exit"));
        exitLore.add(ChatUtil.color("&6back to the main menu."));
        exitM.setLore(exitLore);
        exit.setItemMeta(exitM);
        inv.setItem(8, exit);

        return inv;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void duelMenuClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            if (!event.getInventory().getName().equalsIgnoreCase(ChatUtil.color("&bDuel Menu"))){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            Inventory inv = event.getInventory();
            if (event.getRawSlot() == 3){
                if (cP.isDuelsOn()){
                    cP.setDuelsOn(false);
                } else {
                    cP.setDuelsOn(true);
                }
                if (cP.isDuelsOn()){
                    ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
                    ItemMeta acceptM = accept.getItemMeta();
                    acceptM.setDisplayName(ChatUtil.color("&bAccepting Duel Invites"));
                    List<String> acceptLore = new ArrayList<>();
                    acceptLore.add(ChatUtil.color("&6You are currently accepting"));
                    acceptLore.add(ChatUtil.color("&6duels. Click here to turn"));
                    acceptLore.add(ChatUtil.color("&6this feature off."));
                    acceptM.setLore(acceptLore);
                    accept.setItemMeta(acceptM);
                    inv.setItem(3, accept);
                } else {
                    ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta declineM = decline.getItemMeta();
                    declineM.setDisplayName(ChatUtil.color("&bDeclining Duel Invites"));
                    List<String> declineLore = new ArrayList<>();
                    declineLore.add(ChatUtil.color("&6You are currently declining"));
                    declineLore.add(ChatUtil.color("&6duels. Click here to turn"));
                    declineLore.add(ChatUtil.color("&6this feature on."));
                    declineM.setLore(declineLore);
                    decline.setItemMeta(declineM);
                    inv.setItem(3, decline);
                }
                p.updateInventory();
            }
            if (event.getRawSlot() == 8){
                p.closeInventory();
                p.openInventory(MenuInventory.menu());

                return;
            }
            if (event.getRawSlot() == 5){
                p.closeInventory();
                p.openInventory(nearbyPlayers(p));

                return;
            }
        }
    }
    public static Inventory nearbyPlayers(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Duel Requests"));

        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitM = exit.getItemMeta();
        exitM.setDisplayName(ChatUtil.color("&bExit"));
        List<String> exitLore = new ArrayList<>();
        exitLore.add(ChatUtil.color("&6Click here to exit"));
        exitLore.add(ChatUtil.color("&6back to the main duel menu."));
        exitM.setLore(exitLore);
        exit.setItemMeta(exitM);
        inv.setItem(44, exit);
        Short dura = 3;
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player.getName().equalsIgnoreCase(p.getName())){
                continue;
            }
            if (player.getWorld().getName().equals(p.getWorld().getName()) && player.getLocation().distance(p.getLocation()) <= 30){
                ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
                SkullMeta sM = (SkullMeta)head.getItemMeta();
                sM.setDisplayName(player.getName());
                List<String> skullLore = new ArrayList<>();
                skullLore.add(ChatUtil.color("&bClick here to invite this"));
                skullLore.add(ChatUtil.color("&bplayer to a duel!"));
                sM.setLore(skullLore);
                head.setItemMeta(sM);
                head.setDurability(dura);
                inv.addItem(head);
            }
        }
        return inv;
    }
    @EventHandler
    public void onDuelHeadClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            final Player p = (Player)event.getWhoClicked();
            if (!event.getInventory().getName().equalsIgnoreCase(ChatUtil.color("&6Duel Requests"))){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            if (event.getRawSlot() == 44){
                p.closeInventory();
                p.openInventory(DuelInventory.duelMain(p));
                return;
            }
            if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
                return;
            }
            ItemStack iS = event.getCurrentItem();
            if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
                return;
            }
            String name = iS.getItemMeta().getDisplayName();
            if (Bukkit.getPlayer(name) == null){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player cannot be found!"));
                return;
            }
            if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 180){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &6You cannot start a duel this close to spawn!"));
                return;
            }
            Player duel = Bukkit.getPlayer(name);
            CustomPlayer cP = CustomPlayer.getCP(duel.getName());
            if (cP.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou cannot duel a player that has you on ignore!"));
                return;
            }
            if (!cP.isDuelsOn()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player does not accept duels!"));
                return;
            }
            if (cP.isInDuel()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel."));
                return;
            }
            if (Maps.duelInvited.containsKey(duel.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            if (Maps.duelInvite.containsKey(duel.getName())){
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
            if (Lists.duelWaiting.contains(duel.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            Maps.duelInvite.put(p.getName(), duel.getName());
            Maps.duelInvited.put(duel.getName(), p.getName());
            duel.openInventory(DuelInventory.duelInv(p));
            final String name1 = duel.getName();
            final String name2 = p.getName();
            p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou have sent a duel request to "+duel.getName()+"!"));
            p.closeInventory();
            new BukkitRunnable(){
                @Override
                public void run(){
                    if (Maps.duelInvite.containsKey(name2) && Maps.duelInvited.containsKey(name1)){
                        if (Bukkit.getPlayer(name1) != null){
                            Bukkit.getPlayer(name1).sendMessage(ChatUtil.color("&f[&eDuels&f] &b You have refused a duel from " + name2 + "."));
                        }
                        if (Bukkit.getPlayer(name2) != null){
                            Bukkit.getPlayer(name2).sendMessage(ChatUtil.color("&f[&eDuels&f] &b Your duel request to "+name1+" has timed out."));
                        }
                        Maps.duelInvited.remove(name1);
                        Maps.duelInvite.remove(name2);
                        cancel();
                        return;
                    }
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 300);
        }
    }

    @EventHandler
    public void onDuelClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            final Player p = (Player)event.getWhoClicked();
            if (!event.getInventory().getName().equalsIgnoreCase(ChatUtil.color("&eDuel Invite"))){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            if (event.getRawSlot() == 3){
                ItemStack iS = event.getCurrentItem();
                String[] msg = iS.getItemMeta().getLore().get(1).split(" ");
                final String name = msg[3];
                if (Bukkit.getPlayer(name) == null){
                    p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player has logged offline!"));
                    Maps.duelInvite.remove(name);
                    Maps.duelInvited.remove(p.getName());
                    p.closeInventory();
                    return;
                }
                final CustomPlayer cP = CustomPlayer.getCP(p.getName());
                final Player duel = Bukkit.getPlayer(name);
                final CustomPlayer cD = CustomPlayer.getCP(duel.getName());
                Lists.duelWaiting.add(p.getName());
                Lists.duelWaiting.add(duel.getName());
                p.closeInventory();
                new BukkitRunnable(){
                    int i = 5;
                    @Override
                    public void run(){
                        if (Bukkit.getPlayer(cP.getDuelPartner()) == null || Bukkit.getPlayer(cD.getDuelPartner()) == null){
                            if (duel.isOnline()){
                                duel.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYour duel Partner has logged offline!"));
                            }
                            if (p.isOnline()){
                                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYour duel Partner has logged offline!"));
                            }
                            Maps.duelInvite.remove(name);
                            Maps.duelInvited.remove(p.getName());
                            Lists.duelWaiting.remove(name);
                            Lists.duelWaiting.remove(p.getName());
                            cancel();
                            return;
                        }
                        if (i == 0){
                            cP.setInDuel(true);
                            cP.setDuelPartner(name);
                            cD.setInDuel(true);
                            cD.setDuelPartner(p.getName());
                            cP.setDuelLocation(p.getLocation());
                            cD.setDuelLocation(p.getLocation());
                            Maps.duelInvite.remove(name);
                            Maps.duelInvited.remove(p.getName());
                            Lists.duelWaiting.remove(p.getName());
                            Lists.duelWaiting.remove(duel.getName());
                            p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bLet the Duel begin!!!"));
                            duel.sendMessage(ChatUtil.color("&f[&eDuels&f] &bLet the Duel begin!!!"));
                            cancel();
                            return;
                        }

                        p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThe duel will be starting in "+i+" seconds."));
                        duel.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThe duel will be starting in "+i+" seconds."));
                        i--;
                    }
                }.runTaskTimer(UltimateSurvival.getInstance(), 20, 20);


            }
            if (event.getRawSlot() == 5){
                ItemStack iS = event.getCurrentItem();
                String[] msg = iS.getItemMeta().getLore().get(1).split(" ");
                String name = msg[3];
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou have refused a duel from "+name+"!"));
                if (Bukkit.getPlayer(name) != null){
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color("&f[&eDuels&f] &b"+p.getName()+" has refused your duel request."));
                }
                Maps.duelInvite.remove(name);
                Maps.duelInvited.remove(p.getName());
                p.closeInventory();
                return;
            }
        }

    }
}
