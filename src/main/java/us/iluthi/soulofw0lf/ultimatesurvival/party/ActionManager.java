package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:34 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ActionManager {
    public ActionManager(){}
    public static void doRoll(Player p){
        if(!PlayerManager.inRoll(p))
            return;
        Roll r = null;
        if((r = RollManager.getRollFromList(p)) != null)
            r.doRoll(p);
    }
    public static void doPass(Player p){
        if(!PlayerManager.inRoll(p))
            return;
        Roll r = null;
        if((r = RollManager.getRollFromList(p)) != null)
            r.doPass(p);
    }
    public static void doInfo(Player p){
        if(!PlayerManager.inRoll(p))
            return;
        Roll r = null;
        if((r = RollManager.getRollFromList(p)) != null)
            r.doInfo(p);
    }

    public static void addItem(ItemStack itm, int i){
        Roll r = RollManager.getRollFromList(i);
        if(r != null)
            r.addItem(itm);
    }
}
