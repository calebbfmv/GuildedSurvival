package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Weapon;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditTool;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ShopInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 3:53 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ExcavationCatClick {
    public static void invClick (InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        if (event.getRawSlot() == 44){
            p.closeInventory();
            p.openInventory(ShopInventory.inv());
            return;
        }
        if (event.getRawSlot() == 0
                || event.getRawSlot() == 1
                || event.getRawSlot() == 2
                || event.getRawSlot() == 3
                || event.getRawSlot() == 5
                || event.getRawSlot() == 6
                || event.getRawSlot() == 7
                || event.getRawSlot() == 8
                || event.getRawSlot() == 18
                || event.getRawSlot() == 19
                || event.getRawSlot() == 20
                || event.getRawSlot() == 21
                || event.getRawSlot() == 23
                || event.getRawSlot() == 24
                || event.getRawSlot() == 25
                || event.getRawSlot() == 26){
            p.closeInventory();
            Maps.editItem.put(p.getName(), new Weapon(event.getCurrentItem()));
            p.openInventory(EditTool.inv(p));
        }
    }
}
