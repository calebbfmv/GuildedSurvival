package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Warp;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/25/13
 * Time: 11:20 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class WarpMenu implements Listener {
    public static Map<String, Location> warpPlacing = new HashMap<>();
    public WarpMenu(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    public static Inventory warpInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bWarp Menu"));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!cP.getWarpList().isEmpty()){
            for (Warp warp : cP.getWarpList()){
                ItemStack iS = new ItemStack(Material.NETHER_STAR, 1);
                ItemMeta iM = iS.getItemMeta();
                iM.setDisplayName(warp.getName());
                List<String> warpLore = new ArrayList<>();
                warpLore.add(ChatUtil.color("&2Left click to warp"));
                warpLore.add(ChatUtil.color("&2to this saved location."));
                warpLore.add(ChatUtil.color("&6Shift click this item"));
                warpLore.add(ChatUtil.color("&6to make a warp scroll"));
                warpLore.add(ChatUtil.color("&6for 1,000 coins."));
                warpLore.add(ChatUtil.color("&4Shift Right click to delete"));
                warpLore.add(ChatUtil.color("&4this saved warp."));
                iM.setLore(warpLore);
                iS.setItemMeta(iM);
                inv.addItem(iS);
            }
        }

        ItemStack placeItem = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta placeMeta = placeItem.getItemMeta();
        placeMeta.setDisplayName(ChatUtil.color("&6Place Warp"));
        List<String> placeLore = new ArrayList<>();
        placeLore.add(ChatUtil.color("&bClick here to place a"));
        placeLore.add(ChatUtil.color("&bwarp at your location."));
        placeMeta.setLore(placeLore);
        placeItem.setItemMeta(placeMeta);
        inv.setItem(43, placeItem);
        
        
        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(44, returnItem);
        return inv;
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void warpChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (!warpPlacing.containsKey(p.getName())){
            return;
        }
        event.setCancelled(true);
        if (!StringUtils.isAlphanumeric(event.getMessage().replace(" ",""))){
            p.sendMessage(ChatUtil.color("&f[&bWarp&f] &4 Warp names may only contain letters and numbers."));
            return;
        }
        String name = event.getMessage().replace(" ","");
        new Warp(name, warpPlacing.get(p.getName()), CustomPlayer.getCP(p.getName()));
        warpPlacing.remove(p.getName());
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        cP.setChatBlocked(false);
        p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You have placed a warp named " + name + "."));
        event.setMessage(null);
        return;
    }
    @EventHandler
    public void warpMenu(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            if (!event.getInventory().getName().equalsIgnoreCase(ChatUtil.color("&bWarp Menu"))){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
                return;
            }
            ItemStack item = event.getCurrentItem();
            if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()){
                return;
            }
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getRawSlot() == 43){
                int i = cP.getWarpList().size();
                if (p.getWorld().getName().contains("nether")){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You cannot set a warp in the nether, this world will get reset frequently!"));
                    return;
                }
                if (i >= 18){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You cannot set anymore warps!"));
                    return;
                }
                if (i >= 9 && !p.hasPermission("triple.coins")){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You cannot set anymore warps!"));
                    return;
                }
                if ( i >= 3 && !p.hasPermission("double.coins")){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You cannot set anymore warps!"));
                    return;
                }
                if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 250){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You cannot set any warps this close to spawn!"));
                    return;
                }
                p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You have placed a warp at your location, please type it's name in chat."));
                warpPlacing.put(p.getName(), p.getLocation());
                cP.setChatBlocked(true);
                p.closeInventory();
                return;
            }
            if (event.getRawSlot() == 44){
                System.out.print("slot 44");
                p.openInventory(MenuInventory.menu());
                p.closeInventory();
                return;
            }
            if (event.isShiftClick() && event.isLeftClick()){
                if (!p.hasPermission("double.coins")){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You must be a Fiiishy rank member or higher to make warp scrolls. Please visit &bhttp://SkyKipz.com &6for more information."));
                    return;
                }
                if (cP.getGoldPoints() < 1000){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You do not have enough coins to make a warp scroll at this time."));
                    return;
                }
                Warp warp = new Warp();
                boolean found = false;
                for (Warp test : cP.getWarpList()){
                    if (test.getName().equalsIgnoreCase(item.getItemMeta().getDisplayName())){
                        found = true;
                        warp = test;
                    }
                }
                if (found){
                    int x = (int)warp.getLoc().getX();
                    int y = (int)warp.getLoc().getY();
                    int z = (int)warp.getLoc().getZ();
                    String world = warp.getLoc().getWorld().getName();
                    Location newLoc = new Location(Bukkit.getWorld(world), x, y, z);
                    ItemStack iS = new ItemStack(Material.PAPER, 1);
                    ItemMeta iM = iS.getItemMeta();
                    iM.setDisplayName(ChatUtil.color("&bWarp Scroll"));
                    List<String> warpLore = new ArrayList<>();
                    warpLore.add(ChatUtil.color("&6" + p.getName() + "'s Warp Scroll"));
                    warpLore.add(ChatUtil.color("&6This scroll warps to " + warp.getName()));
                    warpLore.add(ChatUtil.color("&6Location:" + LocationUtil.locToString(newLoc)));
                    iM.setLore(warpLore);
                    iS.setItemMeta(iM);
                    cP.setGoldPoints(cP.getGoldPoints() - 1000);
                    p.getInventory().addItem(iS);
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You have created a warp scroll to "+warp.getName()+"."));
                    p.closeInventory();
                    return;
                }
            }
            if (event.isLeftClick() || event.isRightClick() && !event.isShiftClick()){
                Warp warp = new Warp();
                boolean found = false;
                for (Warp test : cP.getWarpList()){
                    if (test.getName().equalsIgnoreCase(item.getItemMeta().getDisplayName())){
                        found = true;
                        warp = test;
                    }
                }
                if (found){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6Please stand still! Warp commencing!"));
                    p.closeInventory();
                    final Player player = p;
                    final Location loc = warp.getLoc();
                    final Location loc1 = p.getLocation();
                    new BukkitRunnable(){
                        int i = 8;
                        @Override
                        public void run(){
                            if (i == 0){
                                player.teleport(loc);
                                cancel();
                                return;
                            }
                            if (loc1.distance(player.getLocation()) >= 1.5){
                                player.sendMessage(ChatUtil.color("&f[&bWarp&f] &6Warp cancelled! To use this warp please stand still!"));
                                cancel();
                                return;
                            }
                            player.sendMessage(ChatUtil.color("&f[&bWarp&f] &6Warp commencing in " + i + " seconds. Please stand still"));
                            i--;
                        }
                    }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
                }
            }
            if (event.isRightClick() && event.isShiftClick()){
                Warp warp = new Warp();
                boolean found = false;
                for (Warp test : cP.getWarpList()){
                    if (test.getName().equalsIgnoreCase(item.getItemMeta().getDisplayName())){
                        found = true;
                        warp = test;
                    }
                }
                if (found){
                    p.sendMessage(ChatUtil.color("&f[&bWarp&f] &6You have deleted a warp named " + warp.getName() + "."));
                    cP.getWarpList().remove(warp);
                    p.closeInventory();
                }
            }

        }
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void warpClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = p.getItemInHand();
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        if (!iS.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bWarp Scroll"))){
            return;
        }
        List<String> lores = iS.getItemMeta().getLore();
        String[] locString = lores.get(2).split(":");
        final Location loc = LocationUtil.stringToLoc(locString[1]);
        p.getInventory().remove(iS);
        p.updateInventory();
        final Player player = p;
        final Location loc1 = p.getLocation();
        new BukkitRunnable(){
            int i = 8;
            @Override
            public void run(){
                if (i == 0){
                    player.teleport(loc);
                    cancel();
                    return;
                }
                if (loc1.distance(player.getLocation()) >= 1.5){
                    player.sendMessage(ChatUtil.color("&f[&bWarp&f] &6Warp cancelled! To use this warp please stand still!"));
                    cancel();
                    return;
                }
                player.sendMessage(ChatUtil.color("&f[&bWarp&f] &6Warp commencing in " + i + " seconds. Please stand still"));
                i--;
            }
        }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
    }
}
