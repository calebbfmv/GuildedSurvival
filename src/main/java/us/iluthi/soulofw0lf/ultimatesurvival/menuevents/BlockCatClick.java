package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ShopInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.ShopProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/9/13
 * Time: 5:58 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class BlockCatClick {
    @SuppressWarnings("deprecation")
	public static void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        int i = event.getRawSlot();
        if (event.isRightClick()){
            if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)){
                return;
            }
            ItemStack iS = event.getCurrentItem();
            if (iS.hasItemMeta()){
                if (iS.getItemMeta().hasLore()){
                    return;
                }
            }
            if (iS.getAmount() <= 1){
                inv.setItem(i, null);
                p.updateInventory();
                return;
            }
            iS.setAmount(iS.getAmount() - 1);
            p.updateInventory();
            return;
        }
        if (event.isLeftClick()){
            switch (i){
                case 0:
                case 1:
                case 2:
                case 3:
                case 9:
                case 10:
                case 11:
                case 12:
                case 18:
                case 19:
                case 20:
                case 21:
                case 27:
                case 28:
                case 29:
                case 30:
                case 36:
                case 37:
                case 38:
                case 39:
                    if (event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)){
                        return;
                    }
                    inv.addItem(new ItemStack(event.getCurrentItem().getType(), 1));
                    ShopProcess.onClick(inv);
                    p.updateInventory();
                    return;
                case 44:
                    p.closeInventory();
                    p.openInventory(ShopInventory.inv());
                    return;
                case 35:
                    int total = ShopProcess.onClick(inv);
                    CustomPlayer cP = CustomPlayer.getCP(p.getName());
                    if (total > cP.getGoldPoints()){
                        p.sendMessage(ChatUtil.color(Strings.tradeStub + " You cannot afford this purchase."));
                        return;
                    }
                    int items = 0;
                    for (ItemStack iS : inv){
                        if (iS == null || iS.getType().equals(Material.AIR)){
                            continue;
                        }
                        if (iS.getType().equals(Material.STAINED_GLASS_PANE)){
                            continue;
                        }
                        if (iS.hasItemMeta()){
                            if (iS.getItemMeta().hasLore()){
                                continue;
                            }
                        }
                        items++;
                    }
                    int room = 36;
                    for (ItemStack iS : p.getInventory()){
                        if (iS == null || iS.getType().equals(Material.AIR)){
                         continue;
                        }
                        room--;
                    }
                    if (room < items){
                        p.sendMessage(ChatUtil.color(Strings.tradeStub + " You do not have enough room in your inventory for this purchase!"));
                        return;
                    }
                    CoinChange.add(p, -total, true);
                    for (ItemStack iS : inv){
                        if (iS == null || iS.getType().equals(Material.AIR)){
                            continue;
                        }
                        if (iS.getType().equals(Material.STAINED_GLASS_PANE)){
                            continue;
                        }
                        if (iS.hasItemMeta()){
                            if (iS.getItemMeta().hasLore()){
                                continue;
                            }
                        }
                        p.getInventory().addItem(iS);
                        p.sendMessage(ChatUtil.color(Strings.tradeStub + " you have purchased "+ iS.getAmount() +" "+ iS.getType().name().toLowerCase().replace("_", " ")));
                    }
                    p.closeInventory();
                    p.openInventory(ShopInventory.inv());
                    break;
                default:
                    break;
            }
        }
    }
}
