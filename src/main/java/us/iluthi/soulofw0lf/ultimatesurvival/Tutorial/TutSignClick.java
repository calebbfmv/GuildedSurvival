package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignBlocks;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 6:48 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutSignClick implements Listener {
    public TutSignClick(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void signClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (Maps.tutorialStages.containsKey(p.getName())){
            return;
        }
//        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Block b = event.getClickedBlock();
            if (b.getState() instanceof Sign){
                Sign sign = (Sign)b.getState();
                if (!sign.equals(SignBlocks.tutorial)){
                    return;
                }
                p.openInventory(TutMainInv.inv());
                return;
            }
        }
    }
}
