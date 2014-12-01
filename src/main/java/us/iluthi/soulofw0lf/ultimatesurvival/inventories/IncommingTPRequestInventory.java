package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:35 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class IncommingTPRequestInventory {
    public static Inventory inv(String name){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&bTeleport Request"));
        ItemStack iS = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta iM = iS.getItemMeta();
        iM.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtil.color("&2Left click to accept"));
        lore.add(ChatUtil.color("&2teleport request from"));
        lore.add(ChatUtil.color("&b" + name + "."));
        lore.add(ChatUtil.color("&4Right click to deny it."));
        iM.setLore(lore);
        iS.setItemMeta(iM);
        inv.setItem(4, iS);
        return inv;
    }
}
