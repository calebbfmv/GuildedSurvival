package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.runnables.MiscTimers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ██████╗    █████╗    ██╗        █████╗    ██╗      ██╗ ██╗ ███████╗ ███████╗ ███╗   ███╗      ██████╗
 * ██╔════╝   ██╔══██╗ ██║        ██╔══██╗  ╚██╗ ██╔╝ ██║ ██╔════╝ ██╔════╝ ████╗ ████║   ██╔════╝
 * ██║   ███╗  ███████║ ██║        ███████║    ╚███╔╝   ██║ █████╗     ███████╗ ██╔████╔██║  ██║
 * ██║     ██║  ██╔══██║ ██║        ██╔══██║   ██╔██╗   ██║  ██╔══╝    ╚════██║ ██║ ╚██╔╝██║  ██║
 * ╚██████╔╝  ██║  ██║  ███████╗██║   ██║ ██╔╝  ██╗ ██║  ███████╗ ███████║ ██║   ╚═╝  ██║ ╚██████╗
 * ╚═════╝    ╚═╝  ╚═╝  ╚══════╝╚═╝   ╚═╝ ╚═╝    ╚═╝ ╚═╝  ╚══════╝ ╚══════╝ ╚═╝          ╚═╝   ╚═════╝
 * <p/>
 * Created by: soulofw0lf
 * Date: 3/12/14
 * Time: 9:23 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Lottery {
    public static List<String> tickets = new ArrayList<>();
    public static long lotteryTime = 0;
    public static List<String> codes = new ArrayList<>();
    public static List<String> winners = new ArrayList<>();
    public static void process(){
        int i = (int)(Math.random() * tickets.size());
        String name = tickets.get(i);
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("Lottery: " + name + " is todays 150k winner!");
        }
        tickets.remove(name);
        int i1 = (int)(Math.random() * tickets.size());
        String name1 = tickets.get(i1);
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("Lottery: " + name1 + " is todays 500k winner!");
        }
        tickets.remove(name1);
        int i2 = (int)(Math.random() * tickets.size());
        String name2 = tickets.get(i2);
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("Lottery: " + name2 + " is todays 1Mil winner!");
        }
        tickets.remove(name2);
        int r = (int)(Math.random() * tickets.size());
        String namer = tickets.get(r);
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("Lottery: " + namer + " is todays VIP winner!");
        }
        tickets.remove(namer);
        Player p;
        if ((p = Bukkit.getPlayer(namer)) == null){
            winners.add(namer);
            return;
        }
        p.sendMessage("You have won todays VIP Rank code! Please write wodn the following code");
        int code = (int)(Math.random() * codes.size());
        p.sendMessage(codes.get(code));
        p.sendMessage("To use the code type </code " + code + "> to claim your vip rank!");
    }
    public static void load(){
        File f = new File("plugins/UltimateSurvival/Lottery.yml");
        if (f.exists()){
            YamlConfiguration lotto = YamlConfiguration.loadConfiguration(f);
            tickets = lotto.getStringList("Tickets");
            lotteryTime = lotto.getLong("Lotto Time");
            codes = lotto.getStringList("Lottery Winner Codes");
        } else {
            lotteryTime = System.currentTimeMillis();
            codes.add("gh240479f9v01h");
            codes.add("84bfg2t7q2o8f43");
            codes.add("0987098c7daedsc");
            codes.add("1m2n3b4bv56v65kj");
            codes.add("09865471blksbdf");
            codes.add("x2etrx1hgt43");
            codes.add("098709z8df");
            codes.add("bg2x31hgd4");
            codes.add("acikseyik4759");
            codes.add("lsbdfkiuyg8i3452");
            codes.add("qowdin673nol");
            codes.add("pqzlamzpqm");
            codes.add("pkm9o0kmp24m351");
            codes.add("43s56gfd43gdv");
            codes.add("s98d7fg659s87d6f9");
            codes.add("1j23f4cj");
            codes.add("3bl7k56v3h7jgc5j7c5j");
            codes.add("487hgto8475hgo48");
            codes.add("jh3bv6kjh2vk56jhv2kh56");
            codes.add("k3h56kjh3v6k3hvk63hbk");
        }
        MiscTimers.lottery();
    }
    public static void purchaseTickets(Player p){
        CustomPlayer cp = CustomPlayer.getCP(p.getName());
        if (cp.getGoldPoints() > 100000){
            p.sendMessage("You cannot afford a lottery ticket at this time, they cost 100k gold each.");
            return;
        }
        CoinChange.add(p, -100000, false);
        p.sendMessage("You have purchased a lottery ticket. 100,000 gold has been deducted from your account.");
        tickets.add(p.getName());
        return;
    }
    public static void useCode(Player p, String code){
        if (!codes.contains(code)){
            p.sendMessage("That is an invalid lottery code!");
            return;
        }
        codes.remove(code);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "perms player " + p.getName() + " addgroup gvip");
        p.sendMessage("Congratulations on your new VIP rank!");
        return;
    }
    public static void save(){
        if (tickets.isEmpty()){
            return;
        }
        File f = new File("plugins/UltimateSurvival/Lottery.yml");
        YamlConfiguration lotto = YamlConfiguration.loadConfiguration(f);
        lotto.set("Tickets", null);
        lotto.set("Tickets", tickets);
        lotto.set("Lotto Time", lotteryTime);
        lotto.set("Lottery Winner Codes", null);
        lotto.set("Lottery Winner Codes", codes);
        try {
            lotto.save(f);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
