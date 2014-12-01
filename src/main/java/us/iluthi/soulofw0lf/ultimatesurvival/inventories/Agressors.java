package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:22 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Agressors {
    public static Inventory inv(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Guild Aggressors"));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        for (String aggr : g.getAggressors()){
            if (!Maps.allGuilds.containsKey(aggr)){
                continue;
            }
            Guild attack = Maps.allGuilds.get(aggr);
            int i = (int)(attack.getGuildFortifications() * 0.1);
            ItemStack iS = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&bGuild: " + aggr));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&bClick here to buy peace"));
            lore.add(ChatUtil.color("&bfrom this guild for " + i));
            lore.add(ChatUtil.color("&bfortification points"));
            iM.setLore(lore);
            iS.setItemMeta(iM);
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
        inv.setItem(44, returnItem);
        return inv;
    }
}
