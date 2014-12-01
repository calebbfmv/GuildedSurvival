package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Weapon;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 3:50 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditTool {
    public static Inventory inv (Player p){
        Weapon weap = Maps.editItem.get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 36, ChatUtil.color("&bEdit Tool"));

        inv.setItem(4, weap.toItemStack(p.getName()));

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta spaceM = spacer.getItemMeta();
        spaceM.setDisplayName(" ");
        spacer.setItemMeta(spaceM);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(9, spacer);
        inv.setItem(10, spacer);
        inv.setItem(11, spacer);
        inv.setItem(12, spacer);
        inv.setItem(13, spacer);
        inv.setItem(14, spacer);
        inv.setItem(15, spacer);
        inv.setItem(16, spacer);
        inv.setItem(17, spacer);


        List<String> woodLores = new ArrayList<>();
        woodLores.add(ChatUtil.color("&2Click here to add 1 point"));
        woodLores.add(ChatUtil.color("&2of damage to your tool."));
        woodLores.add(ChatUtil.color("&4Right click to remove 1 point"));
        woodLores.add(ChatUtil.color("&4of damage from your tool."));
        ItemStack wood = InventoryMaker.itemStackMaker((ChatUtil.color("&bWeapon Damage")), Material.IRON_SWORD, 1, (short) 0, woodLores);
        inv.setItem(20, wood);

        List<String> knockLores = new ArrayList<>();
        knockLores.add(ChatUtil.color("&2Click here to add .01 point"));
        knockLores.add(ChatUtil.color("&2of knockback resistance"));
        knockLores.add(ChatUtil.color("&2to your tool."));
        knockLores.add(ChatUtil.color("&4Right click to remove .01 point"));
        knockLores.add(ChatUtil.color("&4of knockback resistance"));
        knockLores.add(ChatUtil.color("&4from your tool."));
        ItemStack knock = InventoryMaker.itemStackMaker((ChatUtil.color("&bKnockback resistance")), Material.PISTON_STICKY_BASE, 1, (short) 0, knockLores);
        inv.setItem(24, knock);


        List<String> confirmLores = new ArrayList<>();
        confirmLores.add(ChatUtil.color("&2Click here to confirm"));
        confirmLores.add(ChatUtil.color("&2your purchase of"));
        confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
        confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
        ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
        inv.setItem(26, confirm);


        ItemStack returnI = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnI.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&4Previous Menu"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&6Click here to go back to the"));
        returnLore.add(ChatUtil.color("&6excavation tool menu without"));
        returnLore.add(ChatUtil.color("&6making any purchases."));
        returnMeta.setLore(returnLore);
        returnI.setItemMeta(returnMeta);
        inv.setItem(35, returnI);

        return inv;
    }
}
