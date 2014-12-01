package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 1:58 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class CoinChange {
	public static void add(Player p, int i, boolean vipBonus){
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		if ((long)cP.getGoldPoints() + (long)i > Integer.MAX_VALUE) //int overflow check
		{
			p.sendMessage("You have too many coins for our system to process.");
			cP.setGoldPoints(Integer.MAX_VALUE);
			return;
		}
		double randCoin = Math.random() * 100;
		if (!p.hasPermission("double.coins") && randCoin <= 1.5){
			p.sendMessage(ChatUtil.color("&f[&4Announcement&f] &6Want to earn coins faster? Visit &bhttp://galaxiesmc.com &6or type /buy in game to check out our shop!"));
		}
		if (cP.getGoldPoints() + i >= 2000000 && !p.hasPermission("coins.cap.million.2")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " Normal members are capped at 2 Million gold. Your transaction has gone through but your gold cap has been reached!"));
			cP.setGoldPoints(2000000);
		} else if (cP.getGoldPoints() + i >= 5000000 && !p.hasPermission("coins.cap.million.5")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " Vip members at capped at 5 Million gold. Your transaction has gone through but your gold cap has been reached!"));
			cP.setGoldPoints(5000000);
		} else if (cP.getGoldPoints() + i >= 10000000 && !p.hasPermission("coins.cap.million.10")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " Vip+ members at capped at 10 Million gold. Your transaction has gone through but your gold cap has been reached!"));
			cP.setGoldPoints(10000000);
		} else if (cP.getGoldPoints() + i >= 15000000 && !p.hasPermission("coins.cap.million.15")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " Pro members at capped at 15 Million gold. Your transaction has gone through but your gold cap has been reached!"));
			cP.setGoldPoints(15000000);
		} else if (cP.getGoldPoints() + i >= 200000000 && !p.hasPermission("coins.cap.million.20")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " Pro+ members at capped at 20 Million gold. Your transaction has gone through but your gold cap has been reached!"));
			cP.setGoldPoints(200000000);
		} else if(p.hasPermission("coins.cap.none")){
			p.sendMessage(ChatUtil.color(Strings.tradeStub + "Congrats, you have no cap!"));
			cP.setGoldPoints(cP.getGoldPoints() + i);
		} else {
			if (i < 0 && vipBonus){
				int save;
				if (p.hasPermission("coins.save.1")){
					save = (int)((i*-1) * 0.1);
					p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have saved " + save  + " coins from your rank bonus!"));
					i = i + save;
				} else if (p.hasPermission("coins.save.2")){
					save = (int)((i*-1) * 0.2);
					p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have saved " + save  + " coins from your rank bonus!"));
					i = i + save;
				}
			}
			cP.setGoldPoints(cP.getGoldPoints() + i);
		}
		SignProcess.playerCheck(p.getName(), cP.getGoldPoints());
		Coin.update(p);
	}
}
