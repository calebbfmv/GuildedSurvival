package us.iluthi.soulofw0lf.ultimatesurvival.variables;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.*;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:09 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Maps {
    public static Map<String, CustomPlayer> customPlayers = new HashMap<String, CustomPlayer>();
    public static Map<String, Guild> allGuilds = new HashMap<>();
    public static Map<String, String> tempRanks = new HashMap<>();
    public static Map<String, String> commands = new HashMap<>();
    public static Map<String, String> shortChat = new HashMap<>();
    public static Map<String, String> duelInvite = new HashMap<>();
    public static Map<String, String> duelInvited = new HashMap<>();
    public static Map<String, GuildRanks> deletingRank = new HashMap<>();
    public static Map<String, GuildRanks> rankPerms = new HashMap<>();
    public static Map<String, List<String>> noAttack = new HashMap<>();
    public static Map<Material, Integer> blockCosts = new HashMap<>();
    public static Map<Material, Integer> values = new HashMap<>();
    public static Map<String, Weapon> editItem = new HashMap<>();
    public static Map<String, Armor> editArmor = new HashMap<>();
    public static Map<String, String> tutorialStages = new HashMap<>();
    public static Map<String, Integer> tutorialSteps = new HashMap<>();
    public static Map<String, Location> tutorialLocations = new HashMap<>();
    public static Map<String, ItemStack> tutorialItems = new HashMap<>();
    public static Map<String, Integer> tutorialStageNumbers = new HashMap<>();
    public static Map<Category, AuctionCategory> categoryMap = new HashMap<>();
    public static Map<String, String> friendChatMap = new HashMap<>();
    public static Map<Location, String> guildsLocMap = new HashMap<>();

}
