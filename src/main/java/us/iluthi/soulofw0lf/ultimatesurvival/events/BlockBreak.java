package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Booleans;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Integers;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class BlockBreak implements Listener{
    private static int POISON_POTATO_CHANCE = 25;

    public BlockBreak(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void guildBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        CustomPlayer cP = CustomPlayer.getCP(player.getName());
        Block block = event.getBlock();
        if(!cP.canBreak(block)) {
            event.setCancelled(true);
            return;
        }
        event.setExpToDrop(0);

        // Increment broken block type
        Integer typeBreaks = cP.getBlockBreaks().get(block.getType().name());
        if(typeBreaks == null) typeBreaks = 0;
        typeBreaks++;
        cP.getBlockBreaks().put(block.getType().name(), typeBreaks);

        int coins = 0;
        int coinChance = 0;
        int seedRand = (int)(Math.random() * 3) + 1;

        // Special block handling
        Material mat = block.getType();
        switch (mat){
            case LEAVES:
            case DIRT:
            case STONE:
            case GRASS:
            case SAND:
            case GRAVEL:
            case WOOD:
                coins = 1;
                coinChance = 2;
                break;

            case COAL:
            case REDSTONE_ORE:
            case LAPIS_ORE:
                coins = 3;
                coinChance = 5;
                break;

            case IRON_ORE:
            case GOLD_ORE:
            case NETHER_BRICK:
                coins = 5;
                coinChance = 5;
                break;

            case DIAMOND_ORE:
            case EMERALD_ORE:
                coins = 10;
                coinChance = 10;
                break;

            case SNOW:
            case SNOW_BLOCK:
                player.getInventory().addItem(new ItemStack(mat, 1));
                event.setCancelled(true);
                block.setType(Material.AIR);
                break;

            case CROPS: player.getInventory().addItem(new ItemStack(Material.SEEDS, seedRand)); break;
            case MELON_STEM: player.getInventory().addItem(new ItemStack(Material.MELON_SEEDS, seedRand)); break;
            case PUMPKIN_STEM: player.getInventory().addItem(new ItemStack(Material.PUMPKIN_SEEDS, seedRand)); break;
            case CARROT: player.getInventory().addItem(new ItemStack(Material.CARROT_ITEM, seedRand)); break;

            case NETHER_STALK:
            case NETHER_WARTS:
                if (block.getData() == (byte)3){
                    player.getInventory().addItem(new ItemStack(Material.NETHER_STALK, seedRand));
                } else player.getInventory().addItem(new ItemStack(Material.NETHER_STALK, 1));
                break;

            case POTATO:
                if (block.getData() == (byte)7){
                    int poison = (int)(Math.random() * 100);
                    if (poison <= POISON_POTATO_CHANCE){
                        player.getInventory().addItem(new ItemStack(Material.POISONOUS_POTATO, seedRand));
                    } else {
                        player.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, seedRand));
                    }
                }
                break;

            case MOB_SPAWNER:
                if (block.getType().equals(Material.MOB_SPAWNER) && Booleans.hasSilk(player.getItemInHand())){
                    player.getInventory().addItem(new ItemStack(Material.MOB_SPAWNER, 1));
                }
                break;

            default:
                break;
        }

        if (coins != 0){
            if (Math.random() * 100 <= coinChance){
                CoinChange.add(player, coins, true);
                player.sendMessage(ChatUtil.color(Strings.tradeStub + " You have gained " + coins + " coins."));
            }
        }


        boolean onlyOne = false;
        for (ItemStack iS : event.getBlock().getDrops()){
            if (iS != null && !(iS.getType() == (Material.AIR))){
                if (player.getItemInHand() != null && !(player.getItemInHand().getType() == (Material.AIR))){
                    if (Booleans.hasSilk(player.getItemInHand())){
                        iS.setType(block.getType());
                        iS.setAmount(1);
                        onlyOne = true;
                    }
                    int random = (int)(Math.random() * 100);
                    if (block.getType() == (Material.DIAMOND_ORE)
                            || block.getType() == (Material.REDSTONE_ORE)
                            || block.getType() == (Material.LAPIS_ORE)
                            || block.getType() == (Material.EMERALD_ORE)
                            || block.getType() == (Material.COAL_ORE)){
                       int fortune = Integers.hasFortune(player.getItemInHand());
                        switch(fortune){
                            case 1:
                                if (random <= 10){
                                    iS.setAmount(2);
                                }
                                break;
                            case 2:
                                if (random <= 15){
                                    iS.setAmount(3);
                                }
                                break;
                            case 3:
                                if (random <= 20){
                                    iS.setAmount(4);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                player.getInventory().addItem(iS);
                if (onlyOne){
                    break;
                }
            }
        }
        event.setCancelled(true);
        block.setType(Material.AIR);
        int dmg = (int)(Math.random() * 5);
        short dura = player.getItemInHand().getType().getMaxDurability();
        boolean isDura;
        switch (player.getItemInHand().getType()){
            case DIAMOND_SWORD:
            case GOLD_SWORD:
            case IRON_SWORD:
            case STONE_SWORD:
            case WOOD_SWORD:
            case DIAMOND_PICKAXE:
            case GOLD_PICKAXE:
            case IRON_PICKAXE:
            case STONE_PICKAXE:
            case WOOD_PICKAXE:
            case DIAMOND_AXE:
            case GOLD_AXE:
            case IRON_AXE:
            case STONE_AXE:
            case WOOD_AXE:
            case DIAMOND_HOE:
            case GOLD_HOE:
            case IRON_HOE:
            case STONE_HOE:
            case WOOD_HOE:
            case DIAMOND_SPADE:
            case GOLD_SPADE:
            case IRON_SPADE:
            case STONE_SPADE:
            case WOOD_SPADE:
            case FLINT_AND_STEEL:
            case FISHING_ROD:
            case SHEARS:
            case BOW:
                isDura = true;
                break;
            default: isDura = false;
        }
        int breakChance = (int)(Math.random() * 100);
        if (isDura && breakChance <= 20){
            if (player.getItemInHand().containsEnchantment(Enchantment.DURABILITY)){
                dmg = dmg - player.getItemInHand().getEnchantmentLevel(Enchantment.DURABILITY);
                if (dmg <= 0){
                    dmg = 0;
                }
            }
            if ((short)(player.getItemInHand().getDurability() + dmg) > dura){
                player.getInventory().remove(player.getItemInHand());
            } else {
                player.getItemInHand().setDurability((short)(player.getItemInHand().getDurability() + dmg));
            }
        }
        player.updateInventory();
    }
}
