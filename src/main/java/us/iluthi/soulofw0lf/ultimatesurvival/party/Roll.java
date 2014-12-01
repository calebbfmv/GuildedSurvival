package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Integers;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:32 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Roll {
    private List<String> pList = new ArrayList<>();
    private List<String> rList = new ArrayList<>();
    private List<ItemStack> pendingList = new ArrayList<>();
    private boolean isRunning = false;
    private BukkitTask task = null;

    private Inventory inv = Bukkit.createInventory(null, 9, "Rolling For:");
    private Player hPlayer = null;
    private int hRoll = 0;
    public Roll(ItemStack itm, List<String> list) {
        pList.clear();
        pList = list;
        addItem(itm);
    }
    public Roll(List<String> list) {
        pList.clear();
        pList = list;

    }
    public void addItem(ItemStack item){
        if(item == null)
            return;
        this.pendingList.add(item);
        loadItems();
        checkRoll();

    }
    public void setPlayers(List<String> list){
        pList.clear();
        for(String name : list){
            pList.add(name);

        }
    }
    public void doRoll(Player p){
        if(!isRunning)
            return;
        if(!pList.contains(p.getName()) && !rList.contains(p.getName()))
            return;
        if(rList.contains(p.getName())){
            p.sendMessage("You've already rolled! Don't be greedy!");
            return;
        }
        rList.add(p.getName());
        p.closeInventory();
        int temp = Util.getRand();
        if(temp > hRoll){
            hPlayer = p;
            hRoll = temp;
        }
        PlayerManager.sendMultiMessageS(pList, p.getName() + " Rolled: " + temp);
        if(pList.size() == rList.size())
            RollDone();
    }
    public void doPass(Player p){
        if(!pList.contains(p.getName()) || rList.contains(p.getName()))
            return;
        PlayerManager.sendMultiMessageS(pList, p.getName() + " Passed ");
        p.closeInventory();
        rList.add(p.getName());
        if(pList.size() == rList.size())
            RollDone();
    }
    public void doInfo(Player p){
        if(!pList.contains(p.getName()))
            return;
        p.openInventory(inv);
    }
    public void RollDone(){
        this.task.cancel();
        PlayerManager.sendMultiMessageS(this.pList, "Rolling Has Ended!!");
        if(hPlayer != null){
            hPlayer.getInventory().addItem(this.pendingList.get(0));
            PlayerManager.sendMultiMessageS(this.pList, hPlayer.getName() + " Has Won!");
        }
        resetRoll();
    }

    public List<String> getPlayers(){return pList;}
    private void checkRoll(){
        if(pendingList.size() > 0 && !isRunning){
            isRunning = true;
            if(task != null)
                task.cancel();
            task = new RollTimer(this).runTaskLater(UltimateSurvival.getInstance(), Integers.rollTimer * 20);
            PlayerManager.sendMultiMessageS(pList, "/party {Info, Roll, or Pass} for " + pendingList.get(0).getType().toString());
            for(String name : pList){
                if (CustomPlayer.getCP(name).isAutoRoll()){
                    if (Bukkit.getPlayer(name).hasPermission("roll.stp")){
                        ActionManager.doRoll(Bukkit.getPlayer(name));
                    } else {
                        ActionManager.doInfo(Bukkit.getPlayer(name));
                    }
                }
            }
        }
    }
    private void resetRoll(){
        this.inv.remove(pendingList.get(0));
        pendingList.remove(0);
        loadItems();
        hPlayer = null;
        hRoll = 0;
        rList.clear();
        task.cancel();
        isRunning = false;
        new BukkitRunnable(){
            public void run(){
                checkRoll();
                cancel();
            }
        }.runTaskLater(UltimateSurvival.getInstance(), 10);
    }
    private void loadItems(){
        inv.clear();
        if(this.pendingList.size() < 9)
            for(ItemStack itm : pendingList)
                inv.addItem(itm);
        else
            for(int i = 0; i<9; i++)
                inv.addItem(pendingList.get(i));
    }
}
