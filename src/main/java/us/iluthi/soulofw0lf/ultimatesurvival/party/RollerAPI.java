package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 12/20/13 Time: 10:31 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RollerAPI {
	public RollerAPI() {
	}

	public static void doRoll(Player p) {
		ActionManager.doRoll(p);
	}

	public static void doPass(Player p) {
		ActionManager.doPass(p);
	}

	public static void doInfo(Player p) {
		ActionManager.doInfo(p);
	}

	public static void addRoll(Roll a) {
		RollManager.addRoll(a);
	}

	public static void remRoll(int a) {
		RollManager.remRoll(a);
	}

	public static void addItem(ItemStack itm, int i) {
		ActionManager.addItem(itm, i);
	}

	public static void setPlayers(List<String> l, int i) {
		PlayerManager.setPlayers(l, i);
	}

}
