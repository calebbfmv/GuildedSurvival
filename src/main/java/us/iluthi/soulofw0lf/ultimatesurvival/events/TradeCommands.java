package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.trade.TradeHandler;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 10:53 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TradeCommands implements Listener{
    public TradeCommands(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void onSale(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
//        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "");
        if (cmd.equalsIgnoreCase("trade")){
            event.setCancelled(true);
            if (args.length != 2){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " Please use /trade {playername}"));
                return;
            }
            TradeHandler.requestTrade(p, args[1]);
            return;
        }
    }
}
