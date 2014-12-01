package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RollClick {
    public static void onItemClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player))
            return;
        Player player = (Player)event.getWhoClicked();
            ItemStack itm = event.getCurrentItem();{
                if(itm == null)
                    return;}
            if (event.isLeftClick()){
                RollerAPI.doRoll(player);
                event.setResult(Event.Result.DENY);
            }
            if (event.isRightClick()){
                RollerAPI.doPass(player);
                event.setResult(Event.Result.DENY);
            }
            event.setCancelled(true);
    }
}
