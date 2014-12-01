package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.*;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/8/13
 * Time: 11:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MainShoppingCartClick {
    public static void invClick(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getRawSlot() == 44){
            p.closeInventory();
            p.openInventory(MenuInventory.menu());
            return;
        }
        if (event.getRawSlot() == 19){
            p.closeInventory();
            p.openInventory(BlockCat.inv());
            return;
        }
        if (event.getRawSlot() == 21){
            p.closeInventory();
            p.openInventory(FoodCat.inv());
            return;
        }
        if (event.getRawSlot() == 23){
            p.closeInventory();
            p.openInventory(MaterialsCat.inv());
            return;
        }
        if (event.getRawSlot() == 25){
            p.closeInventory();
            p.openInventory(RedstoneCat.inv());
            return;
        }
        if (event.getRawSlot() == 28){
           // p.sendMessage(ChatUtil.color(Strings.tradeStub + " This menu is not yet enabled."));
            p.closeInventory();
            p.openInventory(ToolsCat.inv());
            return;
        }
        if (event.getRawSlot() == 30){
            p.closeInventory();
            p.openInventory(WeaponsCat.inv());
            return;
        }
        if (event.getRawSlot() == 32){
            p.closeInventory();
            p.openInventory(ArmorCat.inv());
            return;
        }
        if (event.getRawSlot() == 34){
            p.closeInventory();
            p.openInventory(TransportationCat.inv());
            return;
        }
        if (event.getRawSlot() == 40){
            p.closeInventory();
            p.openInventory(NetherCat.inv());
        }
    }
}
