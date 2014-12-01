package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Integers;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/22/13
 * Time: 2:08 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class OnChat implements Listener {
	public OnChat(Plugin pl){
		Bukkit.getPluginManager().registerEvents(this, pl);
	}
	public static String prefix(Player p, String channel){
		if (channel.equalsIgnoreCase("general")){
			channel = channel.replace("general", Strings.generalStub);
		}
		if (channel.equalsIgnoreCase("trade")){
			channel = channel.replace("trade", Strings.tradeStub);
		}
		if (channel.equalsIgnoreCase("local")){
			channel = channel.replace("local", Strings.localStub);
		}
		if (channel.equalsIgnoreCase("officer")){
			channel = channel.replace("officer", Strings.officerStub);
		}
		if (channel.equalsIgnoreCase("recruitment")){
			channel = channel.replace("recruitment", Strings.recuitmentStub);
		}
		if (channel.equalsIgnoreCase("guild")){
			channel = channel.replace("guild", Strings.guildStub);
		}
		if (channel.equalsIgnoreCase("party")){
			channel = channel.replace("party", Strings.partyStub);
		}
		if (channel.equalsIgnoreCase("hq")){
			channel = channel.replace("hq", Strings.hqStub);
		}
		String prefix = "@c@g@r@p@n@s";
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		prefix = prefix.replace("@c", channel).replace("@n", p.getName());
		if (cP.isInGuild()){
			prefix = prefix.replace("@g", "["+cP.getGuildName()+"]");
		} else {
			prefix = prefix.replace("@g", "");
		}
		if (cP.hasPrefix()){
			prefix = prefix.replace("@p", cP.getPrefix());
		} else {
			prefix = prefix.replace("@p", "");
		}
		if (cP.hasSuffix()){
			prefix = prefix.replace("@s", cP.getSuffix());
		} else {
			prefix = prefix.replace("@s", "");
		}
		prefix = prefix.replace("@r", UltimateSurvival.chat.getPlayerPrefix(p));
		return prefix;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();

		if (Maps.friendChatMap.containsKey(p.getName())){
			event.setCancelled(true);
			return;
		}
		if (event.isCancelled()){
			return;
		}
		CustomPlayer cP = CustomPlayer.getCP(p.getName());
		if (cP.isChatBlocked()){
			return;
		}
		if (!(cP.getLastChat() == -1 || cP.getLastChat() - cP.setLastChat(System.currentTimeMillis()) > -2000)) { //two seconds
			//chat delcine
			event.setCancelled(true);
		p.sendMessage("You are chatting too fast. Slow down");
		}
		String activeChat = cP.getActiveChannel();
		if (!p.hasPermission("chat.colors")){
			event.setMessage(event.getMessage().replace("&", "#"));
		}
		if (activeChat == null ||activeChat.isEmpty()){
			activeChat = "Local";
			cP.setActiveChannel("Local");
		}
		if (activeChat.equalsIgnoreCase("General")){
			event.setCancelled(true);
			if (!p.hasPermission("global.chat")){
				if (cP.getGoldPoints() < 50){
					p.sendMessage(ChatUtil.color(Strings.chatStub + " It costs 50 gold to send a message       to general chat for non donators."));
					return;
				} else {
					CoinChange.add(p, -50, false);
				}
			}
			for (Player player : Bukkit.getOnlinePlayers()){
				CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
				if (cPlayer.isChatBlocked()){
					continue;
				}
				if (cPlayer.getInactiveChannels().contains(activeChat)){
					continue;
				}
				if (cPlayer.getIgnoredPlayers().contains(p.getName())){
					continue;
				}
				player.sendMessage(ChatUtil.color(prefix(p, "general")) + ChatUtil.chatColor("General", event.getMessage(), player));
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("guild")){
			event.setCancelled(true);
			if (!cP.isInGuild()){
				p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
				return;
			}
			Guild g = Maps.allGuilds.get(cP.getGuildName());
			if (g == null){
				p.sendMessage(ChatUtil.color(Strings.guildStub + "Your guild seems to have been abandoned!"));
				cP.setInGuild(false);
				return;
			}
			GuildRanks gR = g.getRanks().get(cP.getGuildRank());
			if (!gR.getRankPermissions().get(RankPerms.CHAT)){
				p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
				return;
			}
			for (String name : g.getPlayers()){
				if (Bukkit.getPlayer(name) != null){
					Player player = Bukkit.getPlayer(name);
					CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
					if (cPlayer.isChatBlocked()){
						continue;
					}
					if (cPlayer.getInactiveChannels().contains(activeChat)){
						continue;
					}
					if (cPlayer.getIgnoredPlayers().contains(p.getName())){
						continue;
					}
					player.sendMessage(ChatUtil.color(prefix(p, "guild")) + ChatUtil.chatColor("Guild", event.getMessage(), player));
				}
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("HQ")){
			event.setCancelled(true);
			if (!cP.isInGuild()){
				p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
				return;
			}
			Guild g = Maps.allGuilds.get(cP.getGuildName());
			GuildRanks gR = g.getRanks().get(cP.getGuildRank());
			if (!gR.getRankPermissions().get(RankPerms.CHAT)){
				p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
				return;
			}
			for (String name : g.getPlayers()){
				if (Bukkit.getPlayer(name) != null){
					Player player = Bukkit.getPlayer(name);
					if (!cP.isInGuildLands()){
						continue;
					}
					if (!cP.getGuildNear().equalsIgnoreCase(g.getName())){
						continue;
					}
					CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
					if (cPlayer.isChatBlocked()){
						continue;
					}
					if (cPlayer.getInactiveChannels().contains(activeChat)){
						continue;
					}
					if (cPlayer.getIgnoredPlayers().contains(p.getName())){
						continue;
					}
					player.sendMessage(ChatUtil.color(prefix(p, "hq")) + ChatUtil.chatColor("HQ", event.getMessage(), player));
				}
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("officer")){
			event.setCancelled(true);
			if (!cP.isInGuild()){
				p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
				return;
			}
			Guild g = Maps.allGuilds.get(cP.getGuildName());
			GuildRanks gR = g.getRanks().get(cP.getGuildRank());
			if (!gR.getRankPermissions().get(RankPerms.OFFICER_CHAT)){
				p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
				return;
			}
			for (String name : g.getPlayers()){
				if (Bukkit.getPlayer(name) != null){
					Player player = Bukkit.getPlayer(name);
					CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
					if (cPlayer.isChatBlocked()){
						continue;
					}
					if (cPlayer.getInactiveChannels().contains(activeChat)){
						continue;
					}
					GuildRanks gRank = g.getRanks().get(cPlayer.getGuildRank());
					if (!gRank.getRankPermissions().get(RankPerms.OFFICER_CHAT)){
						continue;
					}
					if (cPlayer.getIgnoredPlayers().contains(p.getName())){
						continue;
					}
					player.sendMessage(ChatUtil.color(prefix(p, "officer")) + ChatUtil.chatColor("Officer", event.getMessage(), player));
				}
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("recruitment")){
			event.setCancelled(true);
			if (!cP.isInGuild()){
				p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
				return;
			}
			Guild g = Maps.allGuilds.get(cP.getGuildName());
			GuildRanks gR = g.getRanks().get(cP.getGuildRank());
			if (!gR.getRankPermissions().get(RankPerms.RECRUITMENT)){
				p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to recruit for your guild!"));
				return;
			}
			for (Player player : Bukkit.getOnlinePlayers()){
				CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
				if (cPlayer.isChatBlocked()){
					continue;
				}
				if (cPlayer.getInactiveChannels().contains(activeChat)){
					continue;
				}
				if (cPlayer.isInGuild() && !cPlayer.equals(cP)){
					continue;
				}
				if (cPlayer.getIgnoredPlayers().contains(p.getName())){
					continue;
				}
				player.sendMessage(ChatUtil.color(prefix(p, "recruitment")) + ChatUtil.chatColor("Recruitment", event.getMessage(), player));
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("Local")){
			event.setCancelled(true);
			for (Player player : Bukkit.getOnlinePlayers()){
				CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
				if (cPlayer.isChatBlocked()){
					continue;
				}
				if (cPlayer.getInactiveChannels().contains(activeChat)){
					continue;
				}
				if (cPlayer.getIgnoredPlayers().contains(p.getName())){
					continue;
				}
				if (player.getWorld().getName().equals(p.getWorld().getName()) && player.getLocation().distance(p.getLocation()) <= Integers.localDistance){
					player.sendMessage(ChatUtil.color(prefix(p, "local")) + ChatUtil.chatColor("Local", event.getMessage(), player));
				}
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("Party")){
			event.setCancelled(true);
			Party party = null;
			if((party = Util.getParty(p.getName())) != null){
				for (String player : party.getMembers()){
					if (Bukkit.getPlayer(player) != null){
						Player pl = Bukkit.getPlayer(player);
						CustomPlayer cPlayer = CustomPlayer.getCP(pl.getName());
						if (cPlayer.isChatBlocked()){
							continue;
						}
						if (cPlayer.getInactiveChannels().contains(activeChat)){
							continue;
						}
						if (cPlayer.getIgnoredPlayers().contains(p.getName())){
							continue;
						}
						Bukkit.getPlayer(player).sendMessage(ChatUtil.color(prefix(p, "party")) + ChatUtil.chatColor("Party", event.getMessage(), p));
					}
				}
			} else {
				p.sendMessage(ChatUtil.color(Strings.partyStub + "You are not currently in a party"));
				return;
			}
			return;
		}
		if (activeChat.equalsIgnoreCase("trade")){
			event.setCancelled(true);
			for (Player player : Bukkit.getOnlinePlayers()){
				CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
				if (cPlayer.isChatBlocked()){
					continue;
				}
				if (cPlayer.getInactiveChannels().contains(activeChat)){
					continue;
				}
				if (cPlayer.getIgnoredPlayers().contains(p.getName())){
					continue;
				}
				player.sendMessage(ChatUtil.color(prefix(p, "trade")) + ChatUtil.chatColor("Trade", event.getMessage(), player));
			}
		}
	}
}