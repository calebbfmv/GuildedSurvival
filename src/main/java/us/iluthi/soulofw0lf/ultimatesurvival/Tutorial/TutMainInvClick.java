package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 12/19/13 Time: 7:15 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutMainInvClick implements Listener {
	public TutMainInvClick() {
		Bukkit.getPluginManager().registerEvents(this,
				UltimateSurvival.getInstance());
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!inv.getName().equalsIgnoreCase(ChatUtil.color("&4Tutorial"))) {
			return;
		}
		event.setCancelled(true);
		event.setResult(Event.Result.DENY);
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) event.getWhoClicked();
		if (event.getRawSlot() == 0) {
			Maps.tutorialStages.put(p.getName(), "intro");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			IntroSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 2) {
			Maps.tutorialStages.put(p.getName(), "duels");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			DuelSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 4) {
			Maps.tutorialStages.put(p.getName(), "players");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			PlayerSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 6) {
			Maps.tutorialStages.put(p.getName(), "items");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			ItemSteps.play(p);
			p.closeInventory();
			return;

		}
		if (event.getRawSlot() == 8) {
			Maps.tutorialStages.put(p.getName(), "guilds");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			GuildSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 10) {
			Maps.tutorialStages.put(p.getName(), "chat");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			ChatSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 12) {
			Maps.tutorialStages.put(p.getName(), "commands");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			Commandsteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 14) {
			Maps.tutorialStages.put(p.getName(), "shop");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			ShopSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 16) {
			Maps.tutorialStages.put(p.getName(), "warps");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			WarpSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 20) {
			Maps.tutorialStages.put(p.getName(), "trade");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			TradeSteps.play(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 22) {
			Maps.tutorialStages.put(p.getName(), "party");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			PartySteps.play(p);
			process(p);
			p.closeInventory();
			return;
		}
		if (event.getRawSlot() == 24) {
			Maps.tutorialStages.put(p.getName(), "settings");
			Maps.tutorialLocations.put(p.getName(), p.getLocation());
			Maps.tutorialSteps.put(p.getName(), 0);
			process(p);
			SettingSteps.play(p);
			p.closeInventory();
			return;
		}
	}

	@SuppressWarnings("deprecation")
	private void process(Player p) {
		List<String> players = new ArrayList<>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.hidePlayer(p);
			p.hidePlayer(player);
			players.add(player.getName());
		}
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		cP.setChatBlocked(true);
		hiddenPlayer.put(p.getName(), players);
		if (p.getItemInHand() != null) {
			tutItems.put(p.getName(), p.getItemInHand());
		}
		p.setItemInHand(tutItem());
		p.updateInventory();
	}

	public static Map<String, ItemStack> tutItems = new HashMap<>();

	public static ItemStack tutItem() {
		List<String> lores = new ArrayList<>();
		lores.add(ChatUtil
				.color("&bThis wand will take you through the tutorial stages."));
		return InventoryMaker.itemStackMaker(ChatUtil.color("&6Tutorial Wand"),
				Material.STICK, 1, (short) 0, lores);
	}

	@SuppressWarnings("deprecation")
	public static void removeWand(Player p) {
		for (String players : hiddenPlayer.get(p.getName())) {
			if (Bukkit.getPlayer(players) == null) {
				continue;
			}
			Bukkit.getPlayer(players).showPlayer(p);
			p.showPlayer(Bukkit.getPlayer(players));
		}
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		cP.setChatBlocked(false);
		hiddenPlayer.remove(p.getName());
		p.getInventory().remove(tutItem());
		if (tutItems.containsKey(p.getName())) {
			p.getInventory().addItem(tutItems.get(p.getName()));
			tutItems.remove(p.getName());
		}
		p.updateInventory();
	}

	public static Map<String, List<String>> hiddenPlayer = new HashMap<>();
}
