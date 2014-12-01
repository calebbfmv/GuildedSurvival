package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Armor;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ArmorCat;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
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
 * Date: 12/19/13
 * Time: 3:22 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditArmorClick {
    @SuppressWarnings("deprecation")
	public static void invClick (InventoryClickEvent event){
        Inventory inv = event.getInventory();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        if (event.getRawSlot() == 35){
            Maps.editArmor.remove(p.getName());
            p.closeInventory();
            p.openInventory(ArmorCat.inv());
            return;
        }
        Armor weap = Maps.editArmor.get(p.getName());

        if (event.isLeftClick()){
            if (event.getRawSlot() == 18){
                weap.setHealth(weap.getHealth()+1);
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 20){
                weap.setDamage(weap.getDamage()+1);
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 24){
                weap.setKnockBack(weap.getKnockBack()+0.01);
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 26){
                CustomPlayer cP = CustomPlayer.getCP(p.getName());
                if (weap.getCost() > cP.getGoldPoints()){
                    p.sendMessage(ChatUtil.color(Strings.tradeStub + " You cannot currently afford this purchase."));
                    p.sendMessage(ChatUtil.color(Strings.tradeStub + " Please try removing some armor stats, or come back to the shop later."));
                    return;
                }
                if (weap.getCost() > 0){
                    CoinChange.add(p, (int)-weap.getCost(), true);
                    ItemStack iS = inv.getItem(4);
                    ItemMeta iM = iS.getItemMeta();
                    iM.getLore().remove(2);
                    iS.setItemMeta(iM);
                    p.getInventory().addItem(iS);
                    p.closeInventory();
                    p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have purchased "+weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()+" for " + weap.getCost() + "."));
                    Maps.editArmor.remove(p.getName());
                    return;
                }
            }
        }
        if (event.isRightClick()){
            if (event.getRawSlot() == 18){
                if (weap.getHealth() <= 1){
                    weap.setHealth(0);
                } else {
                    weap.setHealth(weap.getHealth()-1);
                }
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 20){
                if (weap.getDamage() <= 1){
                    weap.setDamage(0);
                } else {
                    weap.setDamage(weap.getDamage()-1);
                }
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
            if (event.getRawSlot() == 24){
                if (weap.getKnockBack() <= 0.01){
                    weap.setKnockBack(0);
                } else {
                    weap.setKnockBack(weap.getKnockBack()-0.01);
                }
                inv.setItem(4, weap.toItemStack(p.getName()));
                List<String> confirmLores = new ArrayList<>();
                confirmLores.add(ChatUtil.color("&2Click here to confirm"));
                confirmLores.add(ChatUtil.color("&2your purchase of"));
                confirmLores.add(ChatUtil.color("&2" + weap.getPreName() + " " + weap.getName() + " " + weap.getPostName()));
                confirmLores.add(ChatUtil.color("&4Cost: &b" + weap.getCost()));
                ItemStack confirm = InventoryMaker.itemStackMaker((ChatUtil.color("&bConfirm Purchase")), Material.EMERALD_BLOCK, 1, (short) 0, confirmLores);
                inv.setItem(26, confirm);
                p.updateInventory();
                return;
            }
        }
    }

}
