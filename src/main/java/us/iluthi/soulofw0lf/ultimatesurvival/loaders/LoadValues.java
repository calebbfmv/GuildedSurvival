package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.io.File;
import java.io.IOException;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/25/13
 * Time: 2:45 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class LoadValues {
    public static void yml(){
        File f = new File("plugins/UltimateSurvival/values.yml");
        YamlConfiguration value = YamlConfiguration.loadConfiguration(f);
        if (value.get("Items") == null || value.get("Updated") == null){
            value.set("Updated", true);
            value.set("Items.STONE", 32);
            value.set("Items.GRASS", 1);
            value.set("Items.DIRT", 1);
            value.set("Items.COBBLESTONE", 1);
            value.set("Items.WOOD", 8);
            value.set("Items.SAPLING", 32);
            value.set("Items.SAND", 1);
            value.set("Items.GRAVEL", 4);
            value.set("Items.LOG", 32);
            value.set("Items.LEAVES", 1);
            value.set("Items.GLASS", 1);
            value.set("Items.LAPIS_BLOCK", 864);
            value.set("Items.DISPENSER", 119);
            value.set("Items.SANDSTONE", 4);
            value.set("Items.NOTE_BLOCK", 128);
            value.set("Items.BED_BLOCK", 144);
            value.set("Items.POWERED_RAIL", 2048);
            value.set("Items.DETECTOR_RAIL", 256);
            value.set("Items.PISTON_STICKY_BASE", 372);
            value.set("Items.WEB", 12);
            value.set("Items.PISTON_BASE", 348);
            value.set("Items.WOOL", 48);
            value.set("Items.YELLOW_FLOWER", 8);
            value.set("Items.RED_ROSE", 16);
            value.set("Items.BROWN_MUSHROOM", 32);
            value.set("Items.RED_MUSHROOM", 32);
            value.set("Items.GOLD_BLOCK", 18432);
            value.set("Items.IRON_BLOCK", 2304);
            value.set("Items.STEP", 1);
            value.set("Items.BRICK", 16);
            value.set("Items.TNT", 964);
            value.set("Items.BOOKSHELF", 336);
            value.set("Items.MOSSY_COBBLESTONE", 1);
            value.set("Items.OBSIDIAN", 64);
            value.set("Items.TORCH", 9);
            value.set("Items.MOB_SPAWNER", 100000);
            value.set("Items.WOOD_STAIRS", 12);
            value.set("Items.CHEST", 64);
            value.set("Items.REDSTONE_WIRE", 64);
            value.set("Items.DIAMOND_BLOCK", 73728);
            value.set("Items.WORKBENCH", 32);
            value.set("Items.CROPS", 16);
            value.set("Items.SOIL", 1);
            value.set("Items.FURNACE", 8);
            value.set("Items.BURNING_FURNACE", 8);
            value.set("Items.SIGN_POST", 52);
            value.set("Items.WOODEN_DOOR", 48);
            value.set("Items.LADDER", 14);
            value.set("Items.RAILS", 96);
            value.set("Items.COBBLESTONE_STAIRS", 1);
            value.set("Items.WALL_SIGN", 52);
            value.set("Items.LEVER", 5);
            value.set("Items.STONE_PLATE", 2);
            value.set("Items.IRON_DOOR_BLOCK", 1536);
            value.set("Items.WOOD_PLATE", 16);
            value.set("Items.REDSTONE_ORE", 64);
            value.set("Items.GLOWING_REDSTONE_ORE", 64);
            value.set("Items.REDSTONE_TORCH_OFF", 68);
            value.set("Items.REDSTONE_TORCH_ON", 68);
            value.set("Items.STONE_BUTTON", 2);
            value.set("Items.ICE", 1);
            value.set("Items.SNOW_BLOCK", 1);
            value.set("Items.CACTUS", 8);
            value.set("Items.CLAY", 16);
            value.set("Items.SUGAR_CANE_BLOCK", 32);
            value.set("Items.JUKEBOX", 8256);
            value.set("Items.FENCE", 12);
            value.set("Items.PUMPKIN", 144);
            value.set("Items.NETHERRACK", 1);
            value.set("Items.SOUL_SAND", 49);
            value.set("Items.GLOWSTONE", 1536);
            value.set("Items.JACK_O_LANTERN", 144);
            value.set("Items.CAKE_BLOCK", 363);
            value.set("Items.DIODE_BLOCK_OFF", 203);
            value.set("Items.DIODE_BLOCK_ON", 203);
            value.set("Items.LOCKED_CHEST", 64);
            value.set("Items.STAINED_GLASS", 16);
            value.set("Items.TRAP_DOOR", 24);
            value.set("Items.SMOOTH_BRICK", 32);
            value.set("Items.IRON_FENCE", 96);
            value.set("Items.MELON_BLOCK", 144);
            value.set("Items.PUMPKIN_STEM", 36);
            value.set("Items.MELON_STEM", 16);
            value.set("Items.VINE", 8);
            value.set("Items.FENCE_GATE", 32);
            value.set("Items.BRICK_STAIRS", 384);
            value.set("Items.SMOOTH_STAIRS", 1);
            value.set("Items.MYCEL", 1);
            value.set("Items.WATER_LILY", 16);
            value.set("Items.NETHER_BRICK", 4);
            value.set("Items.NETHER_FENCE", 4);
            value.set("Items.NETHER_BRICK_STAIRS", 6);
            value.set("Items.NETHER_WARTS", 24);
            value.set("Items.ENCHANTMENT_TABLE", 16736);
            value.set("Items.BREWING_STAND", 1539);
            value.set("Items.ENDER_STONE", 1);
            value.set("Items.DRAGON_EGG", 139264);
            value.set("Items.REDSTONE_LAMP_OFF", 1792);
            value.set("Items.REDSTONE_LAMP_ON", 1792);
            value.set("Items.WOOD_STEP", 4);
            value.set("Items.COCOA", 128);
            value.set("Items.SANDSTONE_STAIRS", 2);
            value.set("Items.EMERALD_ORE", 4800);
            value.set("Items.TRIPWIRE_HOOK", 116);
            value.set("Items.TRIPWIRE", 12);
            value.set("Items.EMERALD_BLOCK", 43200);
            value.set("Items.SPRUCE_WOOD_STAIRS", 12);
            value.set("Items.BIRCH_WOOD_STAIRS", 12);
            value.set("Items.JUNGLE_WOOD_STAIRS", 12);
            value.set("Items.BEACON", 3896);
            value.set("Items.COBBLE_WALL", 1);
            value.set("Items.FLOWER_POT", 48);
            value.set("Items.CARROT", 128);
            value.set("Items.POTATO", 128);
            value.set("Items.WOOD_BUTTON", 1);
            value.set("Items.SKULL", 640);
            value.set("Items.ANVIL", 7936);
            value.set("Items.TRAPPED_CHEST", 64);
            value.set("Items.GOLD_PLATE", 4096);
            value.set("Items.IRON_PLATE", 512);
            value.set("Items.REDSTONE_COMPARATOR_OFF", 463);
            value.set("Items.REDSTONE_COMPARATOR_ON", 463);
            value.set("Items.DAYLIGHT_DETECTOR", 789);
            value.set("Items.REDSTONE_BLOCK", 576);
            value.set("Items.QUARTZ_ORE", 256);
            value.set("Items.HOPPER", 1344);
            value.set("Items.QUARTZ_BLOCK", 1024);
            value.set("Items.QUARTZ_STAIRS", 1536);
            value.set("Items.ACTIVATOR_RAIL", 256);
            value.set("Items.DROPPER", 71);
            value.set("Items.STAINED_CLAY", 1);
            value.set("Items.STAINED_GLASS_PANE", 57);
            value.set("Items.LEAVES_2", 1);
            value.set("Items.LOG_2", 32);
            value.set("Items.ACACIA_STAIRS", 12);
            value.set("Items.DARK_OAK_STAIRS", 12);
            value.set("Items.HAY_BLOCK", 216);
            value.set("Items.CARPET", 96);
            value.set("Items.HARD_CLAY", 64);
            value.set("Items.COAL_BLOCK", 1152);
            value.set("Items.PACKED_ICE", 1);
            value.set("Items.DOUBLE_PLANT", 1);
            value.set("Items.IRON_SPADE", 264);
            value.set("Items.IRON_PICKAXE", 776);
            value.set("Items.IRON_AXE", 776);
            value.set("Items.FLINT_AND_STEEL", 260);
            value.set("Items.APPLE", 128);
            value.set("Items.BOW", 48);
            value.set("Items.ARROW", 14);
            value.set("Items.COAL", 128);
            value.set("Items.DIAMOND", 8192);
            value.set("Items.IRON_INGOT", 256);
            value.set("Items.GOLD_INGOT", 2048);
            value.set("Items.IRON_SWORD", 516);
            value.set("Items.WOOD_SWORD", 20);
            value.set("Items.WOOD_SPADE", 16);
            value.set("Items.WOOD_PICKAXE", 32);
            value.set("Items.WOOD_AXE", 32);
            value.set("Items.STONE_SWORD", 6);
            value.set("Items.STONE_SPADE", 9);
            value.set("Items.STONE_PICKAXE", 11);
            value.set("Items.STONE_AXE", 14);
            value.set("Items.DIAMOND_SWORD", 16388);
            value.set("Items.DIAMOND_SPADE", 8200);
            value.set("Items.DIAMOND_PICKAXE", 24584);
            value.set("Items.DIAMOND_AXE", 24584);
            value.set("Items.STICK", 1);
            value.set("Items.BOWL", 6);
            value.set("Items.MUSHROOM_SOUP", 70);
            value.set("Items.GOLD_SWORD", 4100);
            value.set("Items.GOLD_SPADE", 2056);
            value.set("Items.GOLD_PICKAXE", 6152);
            value.set("Items.GOLD_AXE", 6152);
            value.set("Items.STRING", 12);
            value.set("Items.FEATHER", 48);
            value.set("Items.SULPHUR", 192);
            value.set("Items.WOOD_HOE", 24);
            value.set("Items.STONE_HOE", 10);
            value.set("Items.IRON_HOE", 520);
            value.set("Items.DIAMOND_HOE", 16392);
            value.set("Items.GOLD_HOE", 4104);
            value.set("Items.SEEDS", 16);
            value.set("Items.WHEAT", 24);
            value.set("Items.BREAD", 72);
            value.set("Items.LEATHER_HELMET", 320);
            value.set("Items.LEATHER_CHESTPLATE", 512);
            value.set("Items.LEATHER_LEGGINGS", 448);
            value.set("Items.LEATHER_BOOTS", 264);
            value.set("Items.IRON_HELMET", 1280);
            value.set("Items.IRON_CHESTPLATE", 2048);
            value.set("Items.IRON_LEGGINGS", 1792);
            value.set("Items.IRON_BOOTS", 1024);
            value.set("Items.DIAMOND_HELMET", 40960);
            value.set("Items.DIAMOND_CHESTPLATE", 65536);
            value.set("Items.DIAMOND_LEGGINGS", 57344);
            value.set("Items.DIAMOND_BOOTS", 32768);
            value.set("Items.GOLD_HELMET", 10240);
            value.set("Items.GOLD_CHESTPLATE", 16384);
            value.set("Items.GOLD_LEGGINGS", 14336);
            value.set("Items.GOLD_BOOTS", 8192);
            value.set("Items.FLINT", 4);
            value.set("Items.PORK", 64);
            value.set("Items.GRILLED_PORK", 64);
            value.set("Items.PAINTING", 80);
            value.set("Items.GOLDEN_APPLE", 1944);
            value.set("Items.SIGN", 52);
            value.set("Items.WOOD_DOOR", 48);
            value.set("Items.BUCKET", 768);
            value.set("Items.WATER_BUCKET", 769);
            value.set("Items.LAVA_BUCKET", 832);
            value.set("Items.MINECART", 1280);
            value.set("Items.SADDLE", 192);
            value.set("Items.IRON_DOOR", 1536);
            value.set("Items.REDSTONE", 64);
            value.set("Items.SNOW_BALL", 120);
            value.set("Items.BOAT", 40);
            value.set("Items.LEATHER", 64);
            value.set("Items.MILK_BUCKET", 849);
            value.set("Items.CLAY_BRICK", 16);
            value.set("Items.CLAY_BALL", 16);
            value.set("Items.SUGAR_CANE", 32);
            value.set("Items.PAPER", 32);
            value.set("Items.BOOK", 96);
            value.set("Items.SLIME_BALL", 24);
            value.set("Items.STORAGE_MINECART", 1344);
            value.set("Items.POWERED_MINECART", 1288);
            value.set("Items.EGG", 32);
            value.set("Items.COMPASS", 1088);
            value.set("Items.FISHING_ROD", 12);
            value.set("Items.WATCH", 8256);
            value.set("Items.GLOWSTONE_DUST", 384);
            value.set("Items.RAW_FISH", 64);
            value.set("Items.COOKED_FISH", 64);
            value.set("Items.INK_SACK", 8);
            value.set("Items.BONE", 144);
            value.set("Items.SUGAR", 32);
            value.set("Items.CAKE", 363);
            value.set("Items.BED", 144);
            value.set("Items.DIODE", 203);
            value.set("Items.COOKIE", 22);
            value.set("Items.MAP", 1344);
            value.set("Items.SHEARS", 512);
            value.set("Items.MELON", 16);
            value.set("Items.PUMPKIN_SEEDS", 36);
            value.set("Items.MELON_SEEDS", 16);
            value.set("Items.RAW_BEEF", 64);
            value.set("Items.COOKED_BEEF", 64);
            value.set("Items.RAW_CHICKEN", 64);
            value.set("Items.COOKED_CHICKEN", 64);
            value.set("Items.ROTTEN_FLESH", 24);
            value.set("Items.ENDER_PEARL", 1024);
            value.set("Items.BLAZE_ROD", 1536);
            value.set("Items.GHAST_TEAR", 4096);
            value.set("Items.GOLD_NUGGET", 227);
            value.set("Items.NETHER_STALK", 24);
            value.set("Items.POTION", 128);
            value.set("Items.GLASS_BOTTLE", 1);
            value.set("Items.SPIDER_EYE", 128);
            value.set("Items.FERMENTED_SPIDER_EYE", 192);
            value.set("Items.BLAZE_POWDER", 768);
            value.set("Items.MAGMA_CREAM", 792);
            value.set("Items.BREWING_STAND_ITEM", 1539);
            value.set("Items.EYE_OF_ENDER", 1792);
            value.set("Items.SPECKLED_MELON", 243);
            value.set("Items.FIREBALL", 298);
            value.set("Items.BOOK_AND_QUILL", 152);
            value.set("Items.WRITTEN_BOOK", 96);
            value.set("Items.EMERALD", 4200);
            value.set("Items.ITEM_FRAME", 72);
            value.set("Items.FLOWER_POT_ITEM", 48);
            value.set("Items.CARROT_ITEM", 128);
            value.set("Items.POTATO_ITEM", 128);
            value.set("Items.BAKED_POTATO", 128);
            value.set("Items.POISONOUS_POTATO", 128);
            value.set("Items.EMPTY_MAP", 1344);
            value.set("Items.GOLDEN_CARROT", 1944);
            value.set("Items.SKULL_ITEM", 640);
            value.set("Items.CARROT_STICK", 140);
            value.set("Items.NETHER_STAR", 2000);
            value.set("Items.PUMPKIN_PIE", 208);
            value.set("Items.FIREWORK", 224);
            value.set("Items.FIREWORK_CHARGE", 298);
            value.set("Items.REDSTONE_COMPARATOR", 463);
            value.set("Items.NETHER_BRICK_ITEM", 4);
            value.set("Items.QUARTZ", 256);
            value.set("Items.EXPLOSIVE_MINECART", 2244);
            value.set("Items.HOPPER_MINECART", 2624);
            value.set("Items.IRON_BARDING", 15000);
            value.set("Items.GOLD_BARDING", 30000);
            value.set("Items.DIAMOND_BARDING", 45000);
            value.set("Items.LEASH", 72);
            value.set("Items.NAME_TAG", 15000);
            try{

                value.save(f);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        for (String key : value.getConfigurationSection("Items").getKeys(false)){
            Integer cost = value.getInt("Items." + key) * 5;
            Maps.blockCosts.put(Material.valueOf(key), cost);
            Maps.values.put(Material.valueOf(key), value.getInt("Items." + key));
        }

    }
}
