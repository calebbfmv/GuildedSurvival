package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * User: links
 * Date: 1/5/14
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 */
public class FriendInventoryClick {
    public static void friendClick(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        String name = iS.getItemMeta().getDisplayName();
        if (event.isShiftClick())
        {
            cP.delFriend(name);
            p.sendMessage(ChatUtil.color(Strings.friendStub + " Removed "+name+" from friends."));
            return;
        }
        if (event.isLeftClick())
        {
            if (Bukkit.getPlayer(name) == null){
                p.sendMessage(ChatUtil.color(Strings.friendStub + " It seems that player has logged offline!"));
                return;
            }
            //Player player = Bukkit.getPlayer(name);
            p.sendMessage(ChatUtil.color(Strings.friendStub + " Type your message to: "+name));
            p.closeInventory();
            Maps.friendChatMap.put(p.getName(), name);
        }
        else if (event.isRightClick() && p.hasPermission("triple.coins"))
        {
            p.closeInventory();
            final String pName = p.getName();
            final Location loc = p.getLocation();
            if (Bukkit.getPlayer(name) == null){
                p.sendMessage(ChatUtil.color(Strings.friendStub + " It seems that player has logged offline!"));
                return;
            }
            final Player playerdest = Bukkit.getPlayer(name);
            CustomPlayer cDest = CustomPlayer.getCP(name);
            if (!cDest.getFriends().contains(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.friendStub + " That player has removed you from friends so you cannot teleport to them!"));
                return;
            }
            //final Player playerdest = player;
            new BukkitRunnable(){
                int i = 8;
                @Override
                public void run(){
                    if (Bukkit.getPlayer(pName) == null || !playerdest.isOnline()){
                        cancel();
                        return;
                    }
                    Player pl = Bukkit.getPlayer(pName);
                    if (i == 0){
                        pl.teleport(playerdest.getLocation());
                        cancel();
                        return;
                    }
                    if (pl.getLocation().distance(loc) >= 3){
                        pl.sendMessage(ChatUtil.color(Strings.friendStub + " Teleport has been cancelled due to movement!"));
                        cancel();
                        return;
                    }
                    pl.sendMessage(ChatUtil.color(Strings.friendStub + " Friend teleport commencing in " + i + " second."));
                    i--;
                }
            }.runTaskTimer(UltimateSurvival.getInstance(), 0, 20);
        }
    }
}
