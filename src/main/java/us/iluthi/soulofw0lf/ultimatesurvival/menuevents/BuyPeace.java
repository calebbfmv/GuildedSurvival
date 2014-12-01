package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditHQ;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
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
 * Date: 12/2/13
 * Time: 12:21 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class BuyPeace {
    public static void buyPeace(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 44){
            p.closeInventory();
            p.openInventory(EditHQ.inv(p));
            return;
        }
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        String[] split = iS.getItemMeta().getDisplayName().split(": ");
        String name = split[1];
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (!g.getAggressors().contains(name)){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " This guild is not listed as going against you? Perhaps someone else paid to end the attack?"));
            p.closeInventory();
            return;
        }
        Guild attacker = Maps.allGuilds.get(name);
        int i = (int)(attacker.getGuildFortifications() * 0.1);
        if (g.getGuildFortifications() < i){
            p.closeInventory();
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot currently afford to call off this attack!"));
            return;
        }
        g.setGuildFortifications(g.getGuildFortifications() - i);
        SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
        g.getAggressors().remove(attacker.getName());
        attacker.getWarGuilds().remove(g.getName());
        p.sendMessage(ChatUtil.color(Strings.guildStub + " You have paid " + i + " guild fortification points to call off the attack! This guild may not attack you again for the rest of the day!"));
        attacker.setGuildFortifications(attacker.getGuildFortifications() + i);
        SignProcess.guildCheck(attacker.getName(), attacker.getGuildFortifications());
        p.closeInventory();
        for (String key : attacker.getPlayers()){
            if (Bukkit.getPlayer(key) != null){
                Bukkit.getPlayer(key).sendMessage(ChatUtil.color(Strings.guildStub + " " + g.getName() + " has paid your guild " + i + " fortification points to call off the attack!"));
            }
        }
        for (String key : g.getPlayers()){
            if (Bukkit.getPlayer(key) != null){
                Bukkit.getPlayer(key).sendMessage(ChatUtil.color(Strings.guildStub + " Your guild is no longer being attacked by " + attacker.getName() + "!"));
            }
        }
        if (g.getAggressors().isEmpty()){
            g.setWarning(false);
        }
        if (!Maps.noAttack.containsKey(attacker.getName())){
            List<String> guild = new ArrayList<>();
            guild.add(g.getName());
            Maps.noAttack.put(attacker.getName(), guild);
        } else {
            Maps.noAttack.get(attacker.getName()).add(g.getName());
        }
        final String guildName = g.getName();
        final String attackName = attacker.getName();
        new BukkitRunnable(){
            @Override
            public void run(){
                Maps.noAttack.get(attackName).remove(guildName);
            }
        }.runTaskLater(UltimateSurvival.getInstance(), (20 * 60 * 60 * 24));
    }
}
