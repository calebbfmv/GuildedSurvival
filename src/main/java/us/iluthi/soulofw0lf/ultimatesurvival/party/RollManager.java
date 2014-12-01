package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 12/20/13 Time: 10:31 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RollManager {
	private static List<Roll> rList = new ArrayList<>();

	public static void addRoll(Roll a) {
		if (!inRollList(a))
			rList.add(a);
	}

	public static void remRoll(Roll a) {
		if (inRollList(a))
			rList.remove(a);
	}

	public static void remRoll(int a) {
		if (rList.size() > a)
			rList.remove(a);
	}

	public static Roll getRollFromList(int a) {
		return rList.get(a);
	}

	public static Roll getRollFromList(Player p) {
		for (Roll r : RollManager.getRollList())
			for (String l : r.getPlayers())
				if (l.equals(p.getName()))
					return r;
		return null;
	}

	public static List<Roll> getRollList() {
		return rList;
	}

	private static boolean inRollList(Roll a) {
		return rList.contains(a);
	}

}
