package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: links
 * Date: 1/5/14
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 */
public class FriendInventory {
    public static Inventory inv(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bFriends"));
        List<String> players = new ArrayList<>();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        for (String name : cP.getFriendsList()){
            if (Bukkit.getPlayer(name) == null){
                continue;
            }
            else {
                if (p.hasPermission("triple.coins")) {
                    players.add(name);
                    ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                    SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                    returnMeta.setDisplayName(name);
                    List<String> returnLore = new ArrayList<>();
                    returnLore.add(ChatUtil.color("&bLeft Click to PM this player"));
                    returnLore.add(ChatUtil.color("&6Right Click to teleport to them."));
                    returnLore.add(ChatUtil.color("&4Shift Click to remove them."));
                    returnLore.add(ChatUtil.color("&bStatus: &2Online."));
                    returnMeta.setLore(returnLore);
                    returnItem.setItemMeta(returnMeta);
                    inv.addItem(returnItem);
                } else {
                        players.add(name);
                        ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                        SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                        returnMeta.setDisplayName(name);
                        List<String> returnLore = new ArrayList<>();
                        returnLore.add(ChatUtil.color("&bLeft Click to PM."));
                        returnLore.add(ChatUtil.color("&4Shift Click to remove."));
                        returnLore.add(ChatUtil.color("&bStatus: &2Online."));
                        returnMeta.setLore(returnLore);
                        returnItem.setItemMeta(returnMeta);
                        inv.addItem(returnItem);
                }
            }
        }
        for (String name : cP.getFriendsList()){
            if (players.contains(name)){
                continue;
            }
            players.add(name);
            ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
            SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
            returnMeta.setDisplayName(name);
            List<String> returnLore = new ArrayList<>();
            returnLore.add(ChatUtil.color("&4Shift Right click to remove."));
            returnLore.add(ChatUtil.color("&4This player from your friends list."));
            returnLore.add(ChatUtil.color("&bStatus: &4Offline."));
            returnMeta.setLore(returnLore);
            returnItem.setItemMeta(returnMeta);
            inv.addItem(returnItem);
        }

        return inv;
    }
}
