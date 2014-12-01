package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
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
 * Time: 12:25 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DeleteRank {
    public static Inventory inv (GuildRanks g){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&4Delete Rank Confirm"));
        ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta acceptM = accept.getItemMeta();
        acceptM.setDisplayName(ChatUtil.color("&bAccept"));
        List<String> acceptLore = new ArrayList<>();
        acceptLore.add(ChatUtil.color("&6Click here to delete"));
        acceptLore.add(ChatUtil.color("&6the Guild Rank:" + g.getName()));
        acceptM.setLore(acceptLore);
        accept.setItemMeta(acceptM);
        inv.setItem(3, accept);

        ItemStack decline = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta declineM = decline.getItemMeta();
        declineM.setDisplayName(ChatUtil.color("&bDecline"));
        List<String> declineLore = new ArrayList<>();
        declineLore.add(ChatUtil.color("&6Click here to NOT"));
        declineLore.add(ChatUtil.color("&6delete " + g.getName()));
        declineM.setLore(declineLore);
        decline.setItemMeta(declineM);
        inv.setItem(5, decline);
        return inv;
    }
}
