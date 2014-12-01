package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Booleans;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/25/13
 * Time: 2:55 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SellCommand implements Listener {
    public SellCommand(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onSale(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "");
        if (cmd.equalsIgnoreCase("bal")){
            event.setCancelled(true);
            p.sendMessage(ChatUtil.color(Strings.tradeStub + " Your current balance is: &6" + cP.getGoldPoints() + "."));
            return;
        }
        if (cmd.equalsIgnoreCase("pay")){
            event.setCancelled(true);
            if (args.length != 3){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " Please use </pay {playername} {amount}> to pay another player."));
                return;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " That player cannot be found."));
                return;
            }
            if (!Booleans.isInteger(args[2])){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " You must enter a valid number."));
                return;
            }
            Integer i = Integer.parseInt(args[2]);
            if (i <= 0){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " You cannot pay someone negative money!"));
                return;
            }
            if (cP.getGoldPoints() < i){
                p.sendMessage(ChatUtil.color(Strings.tradeStub + " You cannot afford to pay someone that much money!"));
                return;
            }
            CoinChange.add(p, -i, false);
            Player pl = Bukkit.getPlayer(args[1]);
            CoinChange.add(pl, i, false);
            p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have paid " + args[1] + " a total of " + i + " coins."));
            pl.sendMessage(ChatUtil.color(Strings.tradeStub + " " + p.getName() + " has paid you " + i + " coins."));
            return;
        }
        if (cmd.equalsIgnoreCase("save")){
            if (p.getItemInHand() != null){
//                ItemStack iS = p.getItemInHand();

            }
        }
        if (cmd.equalsIgnoreCase("sell")){
            if (p.getItemInHand() != null){
                ItemStack iS = p.getItemInHand();
                Material m = iS.getType();
                if (!Maps.values.containsKey(m)){
                    p.sendMessage(ChatUtil.color("&f[&2ShopKeeper&f] &bYou cannot sell this item!"));
                    event.setCancelled(true);
                    return;
                }
                int i = iS.getAmount();
                int amount = i * Maps.values.get(m);
                if (!iS.getEnchantments().isEmpty()){
                    for (Enchantment ench : iS.getEnchantments().keySet()){
                        amount = amount + (10*iS.getEnchantments().get(ench) * 10);
                    }
                }
                CoinChange.add(p, amount, false);
                p.sendMessage(ChatUtil.color("&f[&2ShopKeeper&f] &bYou have sold the item in your hand for "+amount+"!"));
                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
                p.updateInventory();
                event.setCancelled(true);
                return;
            }
        }
    }
}
