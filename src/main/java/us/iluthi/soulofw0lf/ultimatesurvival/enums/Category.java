package us.iluthi.soulofw0lf.ultimatesurvival.enums;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/24/13
 * Time: 3:46 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public enum Category{
    BLOCKS("Blocks", "&b"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.BEDROCK.name());
            mats.add(Material.AIR.name());
            mats.add(Material.STONE.name());
            mats.add(Material.GRASS.name());
            mats.add(Material.GLOWSTONE.name());
            mats.add(Material.DIRT.name());
            mats.add(Material.COBBLESTONE.name());
            mats.add(Material.SAND.name());
            mats.add(Material.GRAVEL.name());
            mats.add(Material.GOLD_ORE.name());
            mats.add(Material.IRON_ORE.name());
            mats.add(Material.COAL_ORE.name());
            mats.add(Material.LOG.name());
            mats.add(Material.LOG_2.name());
            mats.add(Material.SPONGE.name());
            mats.add(Material.GLASS.name());
            mats.add(Material.LAPIS_ORE.name());
            mats.add(Material.LAPIS_BLOCK.name());
            mats.add(Material.SANDSTONE.name());
            mats.add(Material.WOOL.name());
            mats.add(Material.STAINED_GLASS.name());
            mats.add(Material.GOLD_BLOCK.name());
            mats.add(Material.IRON_BLOCK.name());
            mats.add(Material.BRICK.name());
            mats.add(Material.MOSSY_COBBLESTONE.name());
            mats.add(Material.OBSIDIAN.name());
            mats.add(Material.DIAMOND_BLOCK.name());
            mats.add(Material.ICE.name());
            mats.add(Material.SNOW_BLOCK.name());
            mats.add(Material.HARD_CLAY.name());
            mats.add(Material.STAINED_CLAY.name());
            mats.add(Material.QUARTZ_BLOCK.name());
            mats.add(Material.ENDER_STONE.name());
            mats.add(Material.NETHER_BRICK.name());
            mats.add(Material.NETHERRACK.name());
            mats.add(Material.EMERALD_BLOCK.name());
            mats.add(Material.EMERALD_ORE.name());
            mats.add(Material.CLAY.name());
            mats.add(Material.COAL_BLOCK.name());
            mats.add(Material.DIAMOND_ORE.name());
            mats.add(Material.ENDER_PORTAL_FRAME.name());
            mats.add(Material.HAY_BLOCK.name());
            mats.add(Material.HUGE_MUSHROOM_1.name());
            mats.add(Material.HUGE_MUSHROOM_2.name());
            mats.add(Material.MYCEL.name());
            mats.add(Material.QUARTZ_ORE.name());
            mats.add(Material.SMOOTH_BRICK.name());
            mats.add(Material.SOUL_SAND.name());

            return mats;
        }
    },
    MATERIALS("Materials", "&6"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.BLAZE_POWDER.name());
            mats.add(Material.BLAZE_ROD.name());
            mats.add(Material.BONE.name());
            mats.add(Material.BOOK.name());
            mats.add(Material.BOOK_AND_QUILL.name());
            mats.add(Material.CLAY_BALL.name());
            mats.add(Material.CLAY_BRICK.name());
            mats.add(Material.COAL.name());
            mats.add(Material.DIAMOND.name());
            mats.add(Material.CROPS.name());
            mats.add(Material.EGG.name());
            mats.add(Material.ENCHANTED_BOOK.name());
            mats.add(Material.EYE_OF_ENDER.name());
            mats.add(Material.FEATHER.name());
            mats.add(Material.FLINT.name());
            mats.add(Material.GLOWSTONE_DUST.name());
            mats.add(Material.GOLD_INGOT.name());
            mats.add(Material.GOLD_NUGGET.name());
            mats.add(Material.IRON_INGOT.name());
            mats.add(Material.LEATHER.name());
            mats.add(Material.MAGMA_CREAM.name());
            mats.add(Material.NETHER_STALK.name());
            mats.add(Material.NETHER_STAR.name());
            mats.add(Material.NETHER_WARTS.name());
            mats.add(Material.PAPER.name());
            mats.add(Material.QUARTZ.name());
            mats.add(Material.SEEDS.name());
            mats.add(Material.SLIME_BALL.name());
            mats.add(Material.STICK.name());
            mats.add(Material.STRING.name());
            mats.add(Material.SUGAR.name());
            mats.add(Material.SUGAR_CANE.name());
            mats.add(Material.SUGAR_CANE_BLOCK.name());
            mats.add(Material.SULPHUR.name());
            mats.add(Material.WATER.name());
            mats.add(Material.WHEAT.name());
            return mats;
        }
    },
    TOOLS("Tools", "&f"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.BOWL.name());
            mats.add(Material.BUCKET.name());
            mats.add(Material.COMPASS.name());
            mats.add(Material.EMPTY_MAP.name());
            mats.add(Material.FIREWORK.name());
            mats.add(Material.FIREWORK_CHARGE.name());
            mats.add(Material.FISHING_ROD.name());
            mats.add(Material.FLINT_AND_STEEL.name());
            mats.add(Material.LAVA_BUCKET.name());
            mats.add(Material.SHEARS.name());
            mats.add(Material.WATER_BUCKET.name());
            mats.add(Material.WATCH.name());
            return mats;
        }
    },
    DECORATIONS("Decorations", "&e"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.ACACIA_STAIRS.name());
            mats.add(Material.ANVIL.name());
            mats.add(Material.BEACON.name());
            mats.add(Material.BED.name());
            mats.add(Material.BED_BLOCK.name());
            mats.add(Material.BIRCH_WOOD_STAIRS.name());
            mats.add(Material.BOOKSHELF.name());
            mats.add(Material.BRICK_STAIRS.name());
            mats.add(Material.BURNING_FURNACE.name());
            mats.add(Material.CACTUS.name());
            mats.add(Material.CARPET.name());
            mats.add(Material.CHEST.name());
            mats.add(Material.COBBLE_WALL.name());
            mats.add(Material.COBBLESTONE_STAIRS.name());
            mats.add(Material.DARK_OAK_STAIRS.name());
            mats.add(Material.DEAD_BUSH.name());
            mats.add(Material.BROWN_MUSHROOM.name());
            mats.add(Material.DOUBLE_PLANT.name());
            mats.add(Material.DOUBLE_STEP.name());
            mats.add(Material.DRAGON_EGG.name());
            mats.add(Material.ENCHANTMENT_TABLE.name());
            mats.add(Material.ENDER_CHEST.name());
            mats.add(Material.ENDER_PORTAL.name());
            mats.add(Material.FENCE.name());
            mats.add(Material.FENCE_GATE.name());
            mats.add(Material.FLOWER_POT.name());
            mats.add(Material.FLOWER_POT_ITEM.name());
            mats.add(Material.FURNACE.name());
            mats.add(Material.IRON_DOOR.name());
            mats.add(Material.IRON_DOOR_BLOCK.name());
            mats.add(Material.IRON_FENCE.name());
            mats.add(Material.ITEM_FRAME.name());
            mats.add(Material.JACK_O_LANTERN.name());
            mats.add(Material.JUKEBOX.name());
            mats.add(Material.JUNGLE_WOOD_STAIRS.name());
            mats.add(Material.LADDER.name());
            mats.add(Material.LEAVES.name());
            mats.add(Material.LEAVES_2.name());
            mats.add(Material.LONG_GRASS.name());
            mats.add(Material.MAP.name());
            mats.add(Material.NETHER_BRICK_ITEM.name());
            mats.add(Material.NETHER_BRICK_STAIRS.name());
            mats.add(Material.NETHER_FENCE.name());
            mats.add(Material.NOTE_BLOCK.name());
            mats.add(Material.PACKED_ICE.name());
            mats.add(Material.PAINTING.name());
            mats.add(Material.QUARTZ_STAIRS.name());
            mats.add(Material.RED_MUSHROOM.name());
            mats.add(Material.RED_ROSE.name());
            mats.add(Material.SANDSTONE_STAIRS.name());
            mats.add(Material.SAPLING.name());
            mats.add(Material.SIGN.name());
            mats.add(Material.SIGN_POST.name());
            mats.add(Material.SKULL.name());
            mats.add(Material.SKULL_ITEM.name());
            mats.add(Material.SMOOTH_STAIRS.name());
            mats.add(Material.SNOW.name());
            mats.add(Material.SOIL.name());
            mats.add(Material.SPRUCE_WOOD_STAIRS.name());
            mats.add(Material.STAINED_GLASS_PANE.name());
            mats.add(Material.STEP.name());
            mats.add(Material.THIN_GLASS.name());
            mats.add(Material.TORCH.name());
            mats.add(Material.VINE.name());
            mats.add(Material.WALL_SIGN.name());
            mats.add(Material.WATER_LILY.name());
            mats.add(Material.WEB.name());
            mats.add(Material.WOOD.name());
            mats.add(Material.WOOD_DOOR.name());
            mats.add(Material.WOOD_DOUBLE_STEP.name());
            mats.add(Material.WOOD_STAIRS.name());
            mats.add(Material.WOOD_STEP.name());
            mats.add(Material.WOODEN_DOOR.name());
            mats.add(Material.WORKBENCH.name());
            mats.add(Material.WRITTEN_BOOK.name());
            mats.add(Material.YELLOW_FLOWER.name());
            return mats;
        }
    },
    FOOD("Food", "&7"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.APPLE.name());
            mats.add(Material.BAKED_POTATO.name());
            mats.add(Material.BREAD.name());
            mats.add(Material.CAKE.name());
            mats.add(Material.CAKE_BLOCK.name());
            mats.add(Material.CARROT.name());
            mats.add(Material.CARROT_ITEM.name());
            mats.add(Material.COCOA.name());
            mats.add(Material.COOKED_BEEF.name());
            mats.add(Material.COOKED_CHICKEN.name());
            mats.add(Material.COOKED_FISH.name());
            mats.add(Material.COOKIE.name());
            mats.add(Material.GOLDEN_APPLE.name());
            mats.add(Material.GOLDEN_CARROT.name());
            mats.add(Material.GRILLED_PORK.name());
            mats.add(Material.MELON.name());
            mats.add(Material.MELON_BLOCK.name());
            mats.add(Material.MELON_SEEDS.name());
            mats.add(Material.MELON_STEM.name());
            mats.add(Material.MILK_BUCKET.name());
            mats.add(Material.MUSHROOM_SOUP.name());
            mats.add(Material.POISONOUS_POTATO.name());
            mats.add(Material.PORK.name());
            mats.add(Material.POTATO.name());
            mats.add(Material.POTATO_ITEM.name());
            mats.add(Material.PUMPKIN.name());
            mats.add(Material.PUMPKIN_PIE.name());
            mats.add(Material.PUMPKIN_SEEDS.name());
            mats.add(Material.PUMPKIN_STEM.name());
            mats.add(Material.RAW_BEEF.name());
            mats.add(Material.RAW_CHICKEN.name());
            mats.add(Material.RAW_FISH.name());
            mats.add(Material.SPECKLED_MELON.name());

            return mats;
        }
    },
    RED_STONE("Red Stone", "&4"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.REDSTONE.name());
            mats.add(Material.REDSTONE_BLOCK.name());
            mats.add(Material.REDSTONE_COMPARATOR.name());
            mats.add(Material.REDSTONE_COMPARATOR_OFF.name());
            mats.add(Material.REDSTONE_COMPARATOR_ON.name());
            mats.add(Material.REDSTONE_LAMP_OFF.name());
            mats.add(Material.REDSTONE_LAMP_ON.name());
            mats.add(Material.REDSTONE_ORE.name());
            mats.add(Material.REDSTONE_TORCH_OFF.name());
            mats.add(Material.REDSTONE_TORCH_ON.name());
            mats.add(Material.REDSTONE_WIRE.name());
            mats.add(Material.DAYLIGHT_DETECTOR.name());
            mats.add(Material.DIODE.name());
            mats.add(Material.DIODE_BLOCK_OFF.name());
            mats.add(Material.DIODE_BLOCK_ON.name());
            mats.add(Material.DISPENSER.name());
            mats.add(Material.DROPPER.name());
            mats.add(Material.HOPPER.name());
            mats.add(Material.IRON_PLATE.name());
            mats.add(Material.LEVER.name());
            mats.add(Material.PISTON_BASE.name());
            mats.add(Material.PISTON_EXTENSION.name());
            mats.add(Material.PISTON_MOVING_PIECE.name());
            mats.add(Material.PISTON_STICKY_BASE.name());
            mats.add(Material.STONE_BUTTON.name());
            mats.add(Material.STONE_PLATE.name());
            mats.add(Material.TNT.name());
            mats.add(Material.TRAP_DOOR.name());
            mats.add(Material.TRAPPED_CHEST.name());
            mats.add(Material.TRIPWIRE.name());
            mats.add(Material.TRIPWIRE_HOOK.name());
            mats.add(Material.WOOD_BUTTON.name());
            mats.add(Material.WOOD_PLATE.name());
            mats.add(Material.GLOWING_REDSTONE_ORE.name());
            mats.add(Material.GOLD_PLATE.name());
            return mats;
        }
    },
    HELMET("Helmet", "&a"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.CHAINMAIL_HELMET.name());
            mats.add(Material.DIAMOND_HELMET.name());
            mats.add(Material.GOLD_HELMET.name());
            mats.add(Material.IRON_HELMET.name());
            mats.add(Material.LEATHER_HELMET.name());
            return mats;
        }
    },
    CHEST_PLATES("Chest Plates", "&a"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.CHAINMAIL_CHESTPLATE.name());
            mats.add(Material.DIAMOND_CHESTPLATE.name());
            mats.add(Material.GOLD_CHESTPLATE.name());
            mats.add(Material.IRON_CHESTPLATE.name());
            mats.add(Material.LEATHER_CHESTPLATE.name());
            return mats;
        }
    },
    LEGGINGS("Leggings", "&a"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.CHAINMAIL_LEGGINGS.name());
            mats.add(Material.DIAMOND_LEGGINGS.name());
            mats.add(Material.GOLD_LEGGINGS.name());
            mats.add(Material.IRON_LEGGINGS.name());
            mats.add(Material.LEATHER_LEGGINGS.name());
            return mats;
        }
    },
    BOOTS("Boots", "&a"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.CHAINMAIL_BOOTS.name());
            mats.add(Material.DIAMOND_BOOTS.name());
            mats.add(Material.GOLD_BOOTS.name());
            mats.add(Material.IRON_BOOTS.name());
            mats.add(Material.LEATHER_BOOTS.name());
            return mats;
        }
    },
    SWORDS("Swords", "&b"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.DIAMOND_SWORD.name());
            mats.add(Material.GOLD_SWORD.name());
            mats.add(Material.IRON_SWORD.name());
            mats.add(Material.STONE_SWORD.name());
            mats.add(Material.WOOD_SWORD.name());
            return mats;
        }
    },
    BOWS("Bows", "&2"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.BOW.name());

            return mats;
        }
    },
    SHOVELS("Shovels", "&5"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.DIAMOND_SPADE.name());
            mats.add(Material.GOLD_SPADE.name());
            mats.add(Material.IRON_SPADE.name());
            mats.add(Material.STONE_SPADE.name());
            mats.add(Material.WOOD_SPADE.name());
            return mats;
        }
    },
    HOES("Hoes", "&3"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.WOOD_HOE.name());
            mats.add(Material.STONE_HOE.name());
            mats.add(Material.DIAMOND_HOE.name());
            mats.add(Material.GOLD_HOE.name());
            mats.add(Material.IRON_HOE.name());

            return mats;
        }
    },
    AXES("Axes", "&6"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.DIAMOND_AXE.name());
            mats.add(Material.GOLD_AXE.name());
            mats.add(Material.IRON_AXE.name());
            mats.add(Material.STONE_AXE.name());
            mats.add(Material.WOOD_AXE.name());
            return mats;
        }
    },
    PICKAXES("Pickaxes", "&a"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.DIAMOND_PICKAXE.name());
            mats.add(Material.GOLD_PICKAXE.name());
            mats.add(Material.IRON_PICKAXE.name());
            mats.add(Material.STONE_PICKAXE.name());
            mats.add(Material.WOOD_PICKAXE.name());

            return mats;
        }
    },
    AMMO("Ammo", "&e"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.ARROW.name());
            mats.add(Material.EMERALD.name());
            mats.add(Material.FIREBALL.name());
            mats.add(Material.ENDER_PEARL.name());
            mats.add(Material.MOB_SPAWNER.name());
            mats.add(Material.SNOW_BALL.name());
            return mats;
        }
    },
    TRANSPORTATION("Transportation", "&4"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.ACTIVATOR_RAIL.name());
            mats.add(Material.BOAT.name());
            mats.add(Material.DETECTOR_RAIL.name());
            mats.add(Material.EXPLOSIVE_MINECART.name());
            mats.add(Material.HOPPER_MINECART.name());
            mats.add(Material.MINECART.name());
            mats.add(Material.POWERED_MINECART.name());
            mats.add(Material.POWERED_RAIL.name());
            mats.add(Material.RAILS.name());
            mats.add(Material.STORAGE_MINECART.name());

            return mats;
        }
    },
    PETS("Pets", "&6"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.DIAMOND_BARDING.name());
            mats.add(Material.GOLD_BARDING.name());
            mats.add(Material.IRON_BARDING.name());
            mats.add(Material.NAME_TAG.name());
            mats.add(Material.CARROT_STICK.name());
            mats.add(Material.MONSTER_EGG.name());
            mats.add(Material.MONSTER_EGGS.name());
            mats.add(Material.SADDLE.name());
            mats.add(Material.LEASH.name());
            return mats;
        }
    },
    POTIONS("Potions", "&b"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.POTION.name());
            mats.add(Material.EXP_BOTTLE.name());
            mats.add(Material.BREWING_STAND.name());
            mats.add(Material.BREWING_STAND_ITEM.name());
            mats.add(Material.CAULDRON.name());
            mats.add(Material.CAULDRON_ITEM.name());
            return mats;
        }
    },
    MISCELLANEOUS("Miscellaneous", "&f"){
        @Override
        public List<String> blockMats(){
            List<String> mats = new ArrayList<>();
            mats.add(Material.COMMAND.name());
            mats.add(Material.COMMAND_MINECART.name());
            mats.add(Material.FERMENTED_SPIDER_EYE.name());
            mats.add(Material.FIRE.name());
            mats.add(Material.GHAST_TEAR.name());
            mats.add(Material.GLASS_BOTTLE.name());
            mats.add(Material.GREEN_RECORD.name());
            mats.add(Material.GOLD_RECORD.name());
            mats.add(Material.RECORD_10.name());
            mats.add(Material.RECORD_11.name());
            mats.add(Material.RECORD_12.name());
            mats.add(Material.RECORD_3.name());
            mats.add(Material.RECORD_4.name());
            mats.add(Material.RECORD_5.name());
            mats.add(Material.RECORD_6.name());
            mats.add(Material.RECORD_7.name());
            mats.add(Material.RECORD_8.name());
            mats.add(Material.RECORD_9.name());
            mats.add(Material.INK_SACK.name());
            mats.add(Material.LAVA.name());
            mats.add(Material.PORTAL.name());
            mats.add(Material.ROTTEN_FLESH.name());
            mats.add(Material.SPIDER_EYE.name());
            mats.add(Material.STATIONARY_LAVA.name());
            mats.add(Material.STATIONARY_WATER.name());

            return mats;
        }
    };

    private String name = "";
    private String color = "";
    private static final Map<String, Category> NAME_MAP = new HashMap<>();
    private static final Map<String, Category> MAT_MAP = new HashMap<>();

    static {
        for (Category cat : Category.values()){
            NAME_MAP.put(cat.getName(), cat);
            for (String mat : cat.blockMats()){
                MAT_MAP.put(mat, cat);
            }
        }
    }
    public static Category getFromMat(Material mat){
        if (mat == null || MAT_MAP.get(mat.name()) == null){
            return Category.MISCELLANEOUS;
        }
        return MAT_MAP.get(mat.name());
    }
    public static Category fromName(String name) {
        if (name == null) {
            return null;
        }
        for (Map.Entry<String, Category> e : NAME_MAP.entrySet()) {
            if (e.getKey().equalsIgnoreCase(name)) {
                return e.getValue();
            }
        }
        return null;
    }

    public abstract List<String> blockMats();

    Category(String name, String color){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
