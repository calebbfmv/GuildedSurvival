package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Weapon;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.WeaponSubCat;
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
 * Date: 12/18/13
 * Time: 2:47 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditWeaponClick {
    @SuppressWarnings("deprecation")
	public static void invClick (InventoryClickEvent event){
        Inventory inv = event.getInventory();

            if (!(event.getWhoClicked() instanceof Player)){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            Player p = (Player)event.getWhoClicked();
            if (event.getRawSlot() == 35){
                Maps.editItem.remove(p.getName());
                p.closeInventory();
                p.openInventory(WeaponSubCat.inv());
                return;
            }
            Weapon weap = Maps.editItem.get(p.getName());
            if (event.isLeftClick()){
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
                        p.sendMessage(ChatUtil.color(Strings.tradeStub + " Please try removing some stats, or come back to the shop later."));
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
                        Maps.editItem.remove(p.getName());
                        return;
                    }
                }
            }
            if (event.isRightClick()){
                if (event.getRawSlot() == 20){
                    if (weap.getDamage() <= 1){
                        weap.setDamage(0);
                        return;
                    }
                    weap.setDamage(weap.getDamage()-1);
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
                        return;
                    }
                    weap.setKnockBack(weap.getKnockBack()-0.01);
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
