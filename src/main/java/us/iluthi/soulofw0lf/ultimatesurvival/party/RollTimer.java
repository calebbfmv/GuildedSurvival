package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 12/20/13 Time: 10:30 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RollTimer extends BukkitRunnable {

	private final Roll roll;

	public RollTimer(Roll roll) {
		this.roll = roll;
	}

	@Override
	public void run() {
		roll.RollDone();
	}

}
