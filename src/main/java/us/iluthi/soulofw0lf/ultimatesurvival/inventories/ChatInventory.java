package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/22/13
 * Time: 4:33 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ChatInventory {
    public static Inventory build(Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&bChat Channels"));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        for (String key : cP.getChannelColors().keySet()){
            String color = cP.getChannelColors().get(key);
            short f = 0;
            switch (color){
                case "&1": f = 11;
                    break;
                case "&2": f = 13;
                    break;
                case "&3": f = 12;
                    break;
                case "&4": f = 14;
                    break;
                case "&5": f = 10;
                    break;
                case "&6": f = 1;
                    break;
                case "&7": f = 8;
                    break;
                case "&8": f = 7;
                    break;
                case "&9": f = 9;
                    break;
                case "&a": f = 5;
                    break;
                case "&b": f = 3;
                    break;
                case "&c": f = 6;
                    break;
                case "&d": f = 2;
                    break;
                case "&e": f = 4;
                    break;
                case "&f": f = 0;
                    break;
            }
            ItemStack iS;
            if (cP.getInactiveChannels().contains(key)){
                iS = new ItemStack(Material.WEB, 1);
            } else {
                iS = new ItemStack(Material.WOOL, 1);
            }
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(key);
            List<String> lores = new ArrayList<>();
            lores.add(ChatUtil.color("&6Left Click to join"));
            lores.add(ChatUtil.color("&6a chat channel or change"));
            lores.add(ChatUtil.color("&6it's Color."));
            lores.add(ChatUtil.color("&bRight click to leave"));
            lores.add(ChatUtil.color("&ba chat channel."));
            lores.add(ChatUtil.color("&eShift Click to set"));
            lores.add(ChatUtil.color("&ea channel as your active"));
            lores.add(ChatUtil.color("&echat channel."));
            iM.setLore(lores);
            iS.setItemMeta(iM);
            iS.setDurability(f);
            inv.addItem(iS);

        }
        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bthe main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(8, returnItem);
        return inv;
    }
}
