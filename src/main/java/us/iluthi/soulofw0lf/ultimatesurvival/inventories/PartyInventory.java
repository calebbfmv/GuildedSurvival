package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:16 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PartyInventory {
    public static Inventory inv(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Party Menu"));
        
        Party party;
        if ((party = Party.getParty(p)) == null){
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.equals(p)){
                    continue;
                }
                if (p.getWorld().getName().equalsIgnoreCase(player.getWorld().getName())){
                    if (player.getLocation().distance(p.getLocation()) <= 50){
                        ItemStack iS = new ItemStack(Material.SKULL_ITEM, 1);
                        SkullMeta sM = (SkullMeta)iS.getItemMeta();
                        sM.setOwner(player.getName());
                        sM.setDisplayName(player.getName());
                        List<String> skullLore = new ArrayList<>();
                        skullLore.add(ChatUtil.color("&bClick here to invite this"));
                        skullLore.add(ChatUtil.color("&bplayer to a party."));
                        sM.setLore(skullLore);
                        iS.setItemMeta(sM);
                        iS.setDurability((short)3);
                        inv.addItem(iS);
                    }
                }
            }
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
        inv = Bukkit.createInventory(null, 9, ChatUtil.color("&6Party Menu"));
        for (String name : party.getMembers()){
            ItemStack iS = new ItemStack(Material.SKULL_ITEM, 1);
            SkullMeta sM = (SkullMeta)iS.getItemMeta();
            sM.setOwner(name);
            sM.setDisplayName(name);
            List<String> skullLore = new ArrayList<>();
            skullLore.add(ChatUtil.color("&bLeft click to view this"));
            skullLore.add(ChatUtil.color("&bplayers sub menu."));
            skullLore.add(ChatUtil.color("&6Shift left click to"));
            skullLore.add(ChatUtil.color("&6summon this player."));
            skullLore.add(ChatUtil.color("&4Right click to kick"));
            skullLore.add(ChatUtil.color("&4this player from your party"));
            skullLore.add(ChatUtil.color("&2Shift right click to promote"));
            skullLore.add(ChatUtil.color("&2this player to party leader."));
            sM.setLore(skullLore);
            iS.setItemMeta(sM);
            iS.setDurability((short)3);
            inv.addItem(iS);
        }
        if (p.hasPermission("triple.coins")){
        ItemStack summonItem = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta summonMeta = summonItem.getItemMeta();
        summonMeta.setDisplayName(ChatUtil.color("&6Summon All"));
        List<String> summonLore = new ArrayList<>();
        summonLore.add(ChatUtil.color("&bClick here to summon all"));
        summonLore.add(ChatUtil.color("&bparty members to your location."));
        summonMeta.setLore(summonLore);
        summonItem.setItemMeta(summonMeta);
        inv.setItem(7, summonItem);
        }
        
        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(8, returnItem);
        
        
        return inv;
    }

}
