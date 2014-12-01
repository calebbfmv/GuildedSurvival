package us.iluthi.soulofw0lf.ultimatesurvival;

import com.mongodb.*;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.WarZone;
import us.iluthi.soulofw0lf.ultimatesurvival.events.ChatCommands;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.LoadAllEvents;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.LoadLocale;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.LoadValues;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.DatabaseLoader;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.Lottery;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Invite;
import us.iluthi.soulofw0lf.ultimatesurvival.party.RollerAPI;
import us.iluthi.soulofw0lf.ultimatesurvival.party.TeleInvite;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.savers.GuildSaver;
import us.iluthi.soulofw0lf.ultimatesurvival.savers.PlayerSaver;
import us.iluthi.soulofw0lf.ultimatesurvival.trade.TradeEvents;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 11/21/13 Time: 4:45 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class UltimateSurvival extends JavaPlugin implements Listener {
	private static Plugin plugin;

	public static Plugin getInstance() {
		return plugin;
	}

	public static MongoClient mongo;
	public static DB db;
	public static String dbName = "";
	public static String dbUser = "";
	public static String dbPw = "";
	public static List<String> vipPlayers = new ArrayList<>();
	public static Chat chat = null;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
		dbName = getConfig().getString("Database.Name");
		dbUser = getConfig().getString("Database.User");
		dbPw = getConfig().getString("Database.Password");
		if (getConfig().get("VIP Players") != null) {
			vipPlayers = (List<String>) getConfig().getList("VIP Players");
		}
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB(dbName);
			boolean auth = db.authenticate(dbUser, dbPw.toCharArray());
			if (!auth) {
				System.out.print("This shit didn't connect right");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (getConfig().get("Spawn Location") != null) {
			Locations.spawnLoc = LocationUtil.stringToLoc(getConfig().getString("Spawn Location"));
		}
		for (String key : (List<String>) getConfig().getList("Lores")) {
			Lists.loreList.add(key);
		}
		Lists.chatChannels.add("General");
		Maps.shortChat.put("gen", "General");
		Lists.chatChannels.add("HQ");
		Maps.shortChat.put("hq", "HQ");
		Lists.chatChannels.add("Guild");
		Maps.shortChat.put("g", "Guild");
		Lists.chatChannels.add("Officer");
		Maps.shortChat.put("o", "Officer");
		Lists.chatChannels.add("Recruitment");
		Maps.shortChat.put("rec", "Recruitment");
		Lists.chatChannels.add("Local");
		Maps.shortChat.put("l", "Local");
		Lists.chatChannels.add("Party");
		Maps.shortChat.put("p", "Party");
		Lists.chatChannels.add("Trade");
		Maps.shortChat.put("t", "Trade");
		new LoadLocale();
		new LoadAllEvents();
		new TradeEvents();
		LoadValues.yml();
		DatabaseLoader.loadGuilds();
		DatabaseLoader.loadWarZones();
		setupChat();
        Lottery.load();
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}

		return (chat != null);
	}

	@Override
	public void onDisable() {
		System.out.print("============================");
		System.out.print("============================");
		System.out.print("============================");
		System.out.print("======= Disabling!!! =======");
		System.out.print("============================");
		System.out.print("============================");
		System.out.print("============================");
        Lottery.save();
		if (!vipPlayers.isEmpty()) {
			System.out.print("Saving VIP List");
			getConfig().set("VIP Players", vipPlayers);
			saveConfig();
		}

		for (String key : Maps.allGuilds.keySet()) {
			long time = System.currentTimeMillis();
			System.out.print("Saving a guild named " + key);
			GuildSaver.saveGuild(Maps.allGuilds.get(key));
			long newTime = System.currentTimeMillis() - time;
			System.out.print(key + " successfully saved in " + newTime + "ms.");
		}
		for (WarZone key : Lists.warZones) {
			long time = System.currentTimeMillis();
			System.out.print("Saving a warzone named " + key.getOwnerName());
			GuildSaver.saveWarZone(key);
			long newTime = System.currentTimeMillis() - time;
			System.out.print(key + " successfully saved in " + newTime + "ms.");
		}
		List<String> players = new ArrayList<>();
		for (String key : Maps.customPlayers.keySet()) {
			players.add(key);
		}
		for (String key : players) {
			long time = System.currentTimeMillis();
			System.out.print("Saving a Custom Player named " + key);
			PlayerSaver.saveSettings(key);
			long newTime = System.currentTimeMillis() - time;
			System.out.print(key + " successfully saved in " + newTime + "ms.");
		}
		System.gc();
		mongo.close();

	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (ChatCommands.process(p, label, args)) {
				return true;
			}
		}
		if (sender instanceof Player && (label.equalsIgnoreCase("party") && args.length >= 1)) {
			Player player = (Player) sender;
			// TELEPORT
			// COMMAND-----------------------------------------------------------------
			if (args[0].equalsIgnoreCase("tp")) {
				if (args.length != 2) {
					Util.sendMessage(player.getName(), "/party tp <NAME>");
					return true;
				}
				Party party = null;
				if ((party = Util.getParty(player.getName())) != null) {
					if (!party.getLeader().equals(player.getName())) {
						Util.sendMessage(player.getName(), "You Are Not The Party Leader.");
						return true;
					}
					if (!player.hasPermission("party.teleport")) {
						Util.sendMessage(player.getName(), ChatColor.RED + "You Do Not Have Permission To Use This Command.");
						return true;
					}
				} else {
					Util.sendMessage(player.getName(), "You Are Not In A Party.");
					return true;
				}
				Player b = Bukkit.getPlayer(args[1]);
				if (b == null || player.getName().equals(b.getName())) {
					Util.sendMessage(player.getName(), "Could Not Find Player.");
					return true;
				}
				Party pB = Util.getParty(b.getName());
				if (pB == null || (party != pB)) {
					Util.sendMessage(player.getName(), "You Are Not In The Same Party.");
					return true;
				}
				if (Util.getTeleInvite(b.getName()) != null) {
					Util.sendMessage(player.getName(), "Player Already Has A Teleport Request.");
					return true;
				}
				Lists.tiList.add(new TeleInvite(player, b));
				return true;
			}
			// TELEPORTALL
			// COMMAND-----------------------------------------------------------------
			if (args[0].equalsIgnoreCase("tpall")) {
				if (args.length != 1) {
					Util.sendMessage(player.getName(), "/party tpall");
					return true;
				}
				Party party = null;
				if ((party = Util.getParty(player.getName())) != null) {
					if (!party.getLeader().equals(player.getName())) {
						Util.sendMessage(player.getName(), "You Are Not The Party Leader.");
						return true;
					}
					if (!player.hasPermission("party.teleport.all")) {
						Util.sendMessage(player.getName(), ChatColor.RED + "You Do Not Have Permission To Use This Command.");
						return true;
					}
				} else {
					Util.sendMessage(player.getName(), "You Are Not In A Party.");
					return true;
				}
				for (String play : party.getMembers()) {
					if (Util.getTeleInvite(play) != null)
						continue;
					if (player.getName().equals(play))
						continue;
					Lists.tiList.add(new TeleInvite(player, Bukkit.getPlayer(play)));
				}
				return true;
			}
			// INVITE
			// COMMAND-------------------------------------------------------------------
			if (args[0].equalsIgnoreCase("invite")) {
				if (!player.hasPermission("party.invite")) {
					Util.sendMessage(player.getName(), ChatColor.RED + "You Do Not Have Permission To Use This Command.");
					return true;
				}
				if (args.length != 2) {
					Util.sendMessage(player.getName(), "/party invite <NAME>");
					return true;
				}
				Party party = null;
				// Checks If Player Is Leader
				if ((party = Util.getParty(player.getName())) != null) {
					if (!party.getLeader().equals(player.getName())) {
						Util.sendMessage(player.getName(), "You Are Not The Party Leader.");
						return true;
					}
				} else {
					Lists.pList.add(party = new Party(player.getName()));
				}
				// Checks If Party Is Max Size
				if (party.getMembers().size() == Integers.maxPlayers) {
					Util.sendMessage(player.getName(), "Your Party Is Full.");
					return true;
				}
				Player b = Bukkit.getPlayer(args[1]);
				// Checks If Target Player Was Not Found Or Is The Current
				// Player
				if (b == null || player == b) {
					Util.sendMessage(player.getName(), "Player Not Found.");
					if (party.getMembers().size() == 1 && !Util.hasPlayerInvited(player.getName()))
						party.removePlayer(player.getName());
					return true;
				}
				// Checks If Target Player Already Has An Invitation
				if (Util.getInvite(b.getName()) != null) {
					Util.sendMessage(player.getName(), b.getName() + " Already Has A Pending Invite.");
					return true;
				}
				// Check If Target Player Is Already In A Party
				if (Util.getParty(b.getName()) != null) {
					Util.sendMessage(player.getName(), b.getName() + " Is Already In A Party.");
					return true;
				}
				CustomPlayer cP1 = CustomPlayer.getCP(b.getName());
				if (cP1.getIgnoredPlayers().contains(player.getName())) {
					Util.sendMessage(player.getName(), b.getName() + " has you on ignore!");
					return true;
				}
				Lists.iList.add(new Invite(player, b));
				Util.sendMessage(party.getMembers(), b.getName() + " Invited To Party.");
				return true;
				// ACCEPT
				// COMMAND-----------------------------------------------------------------
			} else if (args[0].equalsIgnoreCase("accept")) {
				Invite invite = null;
				TeleInvite tinvite = null;
				if ((invite = Util.getInvite(player.getName())) == null && ((tinvite = Util.getTeleInvite(player.getName()))) == null) {
					Util.sendMessage(player.getName(), "You Do Not Have Any Pending Invites.");
					return true;
				}
				if (invite != null)
					invite.acceptInvite();
				if (tinvite != null)
					tinvite.accept();
				return true;
				// DENY
				// COMMAND-------------------------------------------------------------------
			} else if (args[0].equalsIgnoreCase("deny")) {
				Invite invite = null;
				TeleInvite tinvite = null;
				if ((invite = Util.getInvite(player.getName())) == null && ((tinvite = Util.getTeleInvite(player.getName()))) == null) {
					Util.sendMessage(player.getName(), "You Do Not Have Any Pending Invites.");
					return true;
				}
				if (invite != null)
					invite.denyInvite();
				if (tinvite != null)
					tinvite.deny();
				return true;
				// KICK
				// COMMAND-------------------------------------------------------------------
			} else if (args[0].equalsIgnoreCase("kick")) {
				if (args.length != 2) {
					Util.sendMessage(player.getName(), "/party kick <NAME>");
					return true;
				}
				Party p = null;
				if ((p = Util.getParty(player.getName())) != null) {
					p = Util.getParty(player.getName());
					if (!p.getLeader().equals(player.getName())) {
						Util.sendMessage(player.getName(), "You Are Not The Party Leader.");
						return true;
					}
					Player b = Bukkit.getPlayer(args[1]);
					if (Util.getPlayerFromList(p.getMembers(), b.getName()) == null) {
						Util.sendMessage(player.getName(), b.getName() + " Is Not In The Party.");
						return true;
					}
					p.removePlayer(b.getName());
					Util.sendMessage(b.getName(), "You Were Kicked From The Party.");
					if (Lists.pcList.contains(b.getName()))
						Lists.pcList.remove(b.getName());
					return true;
				} else {
					Util.sendMessage(player.getName(), "You Are Not In A Party.");
				}
				// PROMOTE
				// COMMAND----------------------------------------------------------------
			} else if (args[0].equalsIgnoreCase("promote")) {
				if (args.length != 2) {
					Util.sendMessage(player.getName(), "/party promote <NAME>");
					return true;
				}
				Party party = null;
				if ((party = Util.getParty(player.getName())) != null) {
					if (!party.getLeader().equals(player.getName())) {
						Util.sendMessage(player.getName(), "You Are Not The Party Leader.");
						return true;
					}
					Player b = Bukkit.getPlayer(args[1]);
					if (Util.getPlayerFromList(party.getMembers(), b.getName()) == null) {
						Util.sendMessage(player.getName(), b.getName() + " Is Not In The Party.");
						return true;
					}
					party.promotePlayer(b.getName());
					return true;
				} else {
					Util.sendMessage(player.getName(), "You Are Not In A Party.");
				}
				// LEAVE
				// COMMAND-----------------------------------------------------------------
			} else if (args[0].equalsIgnoreCase("leave")) {
				Party party = null;
				if ((party = Util.getParty(player.getName())) == null) {
					Util.sendMessage(player.getName(), "You Are Not In A Party.");
					return true;
				}
				party.removePlayer(player.getName());
				Util.sendMessage(player.getName(), "You Left The Party.");
				return true;
			} else if (args[0].equalsIgnoreCase("roll")) {
				RollerAPI.doRoll(player);
				return true;
			} else if (args[0].equalsIgnoreCase("pass")) {
				RollerAPI.doPass(player);
				return true;
			} else if (args[0].equalsIgnoreCase("info")) {
				RollerAPI.doInfo(player);
				return true;
			} else if (args[0].equalsIgnoreCase("drop")) {
				if (!player.isOp()) {
					return false;
				}
				ItemStack iS = new ItemStack(Material.DIAMOND_SWORD, 1);
				ItemMeta iM = iS.getItemMeta();
				List<String> lore = new ArrayList<>();
				lore.add("Roll");
				iM.setLore(lore);
				iS.setItemMeta(iM);
				player.getInventory().addItem(iS);
				return true;
			} else if (args[0].equalsIgnoreCase("war")) {
				if (!player.hasPermission("guilds.declareWar")) {
					return false;
				}
				WarZone war = new WarZone(player);
				for (Guild g : Maps.allGuilds.values()) {
					if (g.hasHq()) {
						if (war.overLap(g.getHeadQuarters())) {
							player.sendMessage(ChatUtil.color("&4ERROR: You are too close to a guilds headquarters!"));
							Lists.warZones.remove(war);
							return true;
						}
					}
				}
				player.sendMessage(ChatUtil.color("&bWarzone created!"));
				return true;
			} else {
				return false;
			}
		}
		if (!(sender instanceof Player) && label.equalsIgnoreCase("addcoins")) {

			if (args.length != 2) {
				System.out.print("Incorrect usage! please use /addCoins playerName amount");
				return true;
			}
			if (!Booleans.isInteger(args[1])) {
				System.out.print("That was not a proper number!");
				return true;
			}
			Player p;
			if ((p = Bukkit.getPlayer(args[0])) == null) {
				DBCollection table = UltimateSurvival.db.getCollection("players");
				BasicDBObject playerFile = new BasicDBObject().append("name", args[0]);
				BasicDBObject mainPlayer = new BasicDBObject().append("name", args[0]);
				BasicDBObject playerGet = new BasicDBObject();
				playerGet.put("name", args[0]);
				DBObject playerStats = table.findOne(playerGet);
				int i = (int) playerStats.get("Gold Points");
				i = i + Integer.parseInt(args[1]);
				playerFile.put("Gold Points", i);
				BasicDBObject updateObj = new BasicDBObject();
				updateObj.put("$set", playerFile);
				table.update(mainPlayer, updateObj);
				System.out.print(i + "coins have been added to " + args[0] + "'s total database gold coins");
				return true;
			}
			CoinChange.add(p, Integer.parseInt(args[1]), false);
			p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have received " + args[1] + " coins from your shop purchase!"));
			return true;
		}
		return false;
	}

	@EventHandler
	public void weather(WeatherChangeEvent event) {
		if (Booleans.weatherCheck) {
			event.setCancelled(true);
		}

	}

	@EventHandler
	public void mobSpawn(CreatureSpawnEvent event) {
		Entity e = event.getEntity();
		if (e != null) {
			if (e.getLocation().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName())) {
				if (e.getLocation().distance(Locations.spawnLoc) <= 180) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void spawnCom(PlayerCommandPreprocessEvent event) {
		String[] args = event.getMessage().split(" ");
		String command = args[0].replace("/", "");
		Player p = event.getPlayer();
		if (command.equalsIgnoreCase("spawnpoint")) {
			if (!p.isOp()) {
				return;
			}
			Locations.spawnLoc = p.getLocation();
			getConfig().set("Spawn Location", LocationUtil.locToString(p.getLocation()));
			saveConfig();
			p.sendMessage("The spawn point has been set!");
			return;
		}
	}
}
