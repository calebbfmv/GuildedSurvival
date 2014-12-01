package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.AttributeHandler;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 2:23 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Armor {
    private String preName = "";
    private String postName = "";
    private String name = "";
    private String type = "";
    private String material = "";
    private double health = 0;
    private double knockBack = 0;
    private double damage = 0;
    private double cost = 0;

    public Armor(ItemStack iS){
        Material mat = iS.getType();
        String matName = mat.name().toLowerCase();
        String[] args = matName.split("_");
        if (args.length == 2){
            this.type = args[0];
            this.material = args[1];
        } else {
            this.material = args[0];
        }
        makeName();
    }

    public double assessCost(String mat){
        Material material = Material.getMaterial(mat);
        double cost = Maps.values.get(material) * 3;
        double knockCost = ((this.knockBack * 100) * (this.knockBack * 100) * (this.knockBack * 100)) * 100;
        double damageCost = (this.damage * this.damage * this.damage) * 100;
        double healthCost = ((this.health / 2) * (this.health / 2) * this.health / 2 ) * 100;
        this.cost = cost + knockCost + damageCost + healthCost;
        return this.cost;
    }

    public ItemStack toItemStack(String name){
        makeName();
        String matName;
        if (!this.type.isEmpty()){
            matName = this.type + "_" + this.material;
        } else {
            matName = this.material;
        }
        matName = matName.toUpperCase();
        ItemStack iS = new ItemStack(Material.valueOf(matName), 1);
        ItemMeta iM = iS.getItemMeta();
        int color = (int)(Math.random() * 10);
        String colorStub;
        switch(color){
            case 0:
                colorStub = "&2";
                break;
            case 1:
                colorStub = "&4";
                break;
            case 2:
                colorStub = "&6";
                break;
            case 3:
                colorStub = "&7";
                break;
            case 4:
                colorStub = "&b";
                break;
            case 5:
                colorStub = "&e";
                break;
            case 6:
                colorStub = "&3";
                break;
            case 7:
                colorStub = "&a";
                break;
            case 8:
                colorStub = "&c";
                break;
            case 9:
                colorStub = "&d";
                break;
            default:
                colorStub = "&f";
                break;
        }
        iM.setDisplayName(ChatUtil.color(colorStub + this.preName + " " + this.name + " " + this.postName));
        List<String> lores = new ArrayList<>();
        lores.add(ChatUtil.color(colorStub + "This armor was crafted by:"));
        lores.add(ChatUtil.color("&f--=== "+ colorStub + name + "&f==--"));
        lores.add(ChatUtil.color("Value: " + assessCost(matName)));
        iM.setLore(lores);
        iS.setItemMeta(iM);
        if (this.damage != 0){
            iS = AttributeHandler.addDamage(iS, this.damage);
        }
        if (this.knockBack != 0){
            iS = AttributeHandler.addKnockBackRes(iS, this.knockBack);
        }
        if (this.health != 0){
            iS = AttributeHandler.addHealth(iS, this.health);
        }
        return iS;
    }
    public ItemStack toMobItemStack(){
        makeName();
        String matName;
        if (!this.type.isEmpty()){
            matName = this.type + "_" + this.material;
        } else {
            matName = this.material;
        }
        matName = matName.toUpperCase();
        ItemStack iS = new ItemStack(Material.valueOf(matName), 1);
        ItemMeta iM = iS.getItemMeta();
        int color = (int)(Math.random() * 10);
        String colorStub;
        switch(color){
            case 0:
                colorStub = "&2";
                break;
            case 1:
                colorStub = "&4";
                break;
            case 2:
                colorStub = "&6";
                break;
            case 3:
                colorStub = "&7";
                break;
            case 4:
                colorStub = "&b";
                break;
            case 5:
                colorStub = "&e";
                break;
            case 6:
                colorStub = "&3";
                break;
            case 7:
                colorStub = "&a";
                break;
            case 8:
                colorStub = "&c";
                break;
            case 9:
                colorStub = "&d";
                break;
            default:
                colorStub = "&f";
                break;
        }
        iM.setDisplayName(ChatUtil.color(colorStub + this.preName + " " + this.name + " " + this.postName));
        List<String> lores = new ArrayList<>();
        lores.add(ChatUtil.color(colorStub + "Ancient Armor:"));
        lores.add(ChatUtil.color("Roll"));
        iM.setLore(lores);
        iS.setItemMeta(iM);
        if (this.damage != 0){
            iS = AttributeHandler.addDamage(iS, this.damage);
        }
        if (this.knockBack != 0){
            iS = AttributeHandler.addKnockBackRes(iS, this.knockBack);
        }
        if (this.health != 0){
            iS = AttributeHandler.addHealth(iS, this.health);
        }
        return iS;
    }

    public void makeName(){
        int matRand = (int)(Math.random() * 10);
        switch(this.type){
            case "leather":
                switch (matRand){
                    case 0:
                        this.preName = "Tattered";
                        break;
                    case 1:
                        this.preName = "Stitched";
                        break;
                    case 2:
                        this.preName = "Old";
                        break;
                    case 3:
                        this.preName = "Worn";
                        break;
                    case 4:
                        this.preName = "Leather";
                        break;
                    case 5:
                        this.preName = "Rugged";
                        break;
                    case 6:
                        this.preName = "Fur";
                        break;
                    case 7:
                        this.preName = "Hide";
                        break;
                    case 8:
                        this.preName = "Cloth";
                        break;
                    case 9:
                        this.preName = "Tanned";
                        break;
                    default:
                        break;
                }
                break;
            case "iron":
                switch (matRand){
                    case 0:
                        this.preName = "Studded";
                        break;
                    case 1:
                        this.preName = "Metal";
                        break;
                    case 2:
                        this.preName = "Iron";
                        break;
                    case 3:
                        this.preName = "Rusty";
                        break;
                    case 4:
                        this.preName = "Reinforced";
                        break;
                    case 5:
                        this.preName = "Bronze";
                        break;
                    case 6:
                        this.preName = "Stalwart";
                        break;
                    case 7:
                        this.preName = "Sturdy";
                        break;
                    case 8:
                        this.preName = "Plated";
                        break;
                    case 9:
                        this.preName = "Legendary";
                        break;
                    default:
                        break;
                }
                break;
            case "gold":
                switch (matRand){
                    case 0:
                        this.preName = "Shiny";
                        break;
                    case 1:
                        this.preName = "Golden";
                        break;
                    case 2:
                        this.preName = "Soft";
                        break;
                    case 3:
                        this.preName = "Dazzling";
                        break;
                    case 4:
                        this.preName = "Sparkling";
                        break;
                    case 5:
                        this.preName = "Sky's";
                        break;
                    case 6:
                        this.preName = "Enchanted";
                        break;
                    case 7:
                        this.preName = "Charismatic";
                        break;
                    case 8:
                        this.preName = "Reflective";
                        break;
                    case 9:
                        this.preName = "Enriching";
                        break;
                    default:
                        break;
                }
                break;
            case "diamond":
                switch (matRand){
                    case 0:
                        this.preName = "Fortified";
                        break;
                    case 1:
                        this.preName = "Diamond";
                        break;
                    case 2:
                        this.preName = "Glass";
                        break;
                    case 3:
                        this.preName = "Unbreakable";
                        break;
                    case 4:
                        this.preName = "Battle Ready";
                        break;
                    case 5:
                        this.preName = "Withstanding";
                        break;
                    case 6:
                        this.preName = "Epic";
                        break;
                    case 7:
                        this.preName = "Husky's";
                        break;
                    case 8:
                        this.preName = "Majestic";
                        break;
                    case 9:
                        this.preName = "Elegant";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        int typeRand = (int)(Math.random() * 10);
        switch(this.material){
            case "helmet":
                switch (typeRand){
                    case 0:
                        this.name = "Hat";
                        break;
                    case 1:
                        this.name = "Helm";
                        break;
                    case 2:
                        this.name = "Helmet";
                        break;
                    case 3:
                        this.name = "Cap";
                        break;
                    case 4:
                        this.name = "Head Gear";
                        break;
                    case 5:
                        this.name = "Visor";
                        break;
                    case 6:
                        this.name = "Cowl";
                        break;
                    case 7:
                        this.name = "Face Guard";
                        break;
                    case 8:
                        this.name = "Hood";
                        break;
                    case 9:
                        this.name = "Mask";
                        break;
                    default:
                        break;
                }
                break;
            case "chestplate":
                switch (typeRand){
                    case 0:
                        this.name = "Breast Plate";
                        break;
                    case 1:
                        this.name = "Armor";
                        break;
                    case 2:
                        this.name = "Shirt";
                        break;
                    case 3:
                        this.name = "Vest";
                        break;
                    case 4:
                        this.name = "Jacket";
                        break;
                    case 5:
                        this.name = "Coat";
                        break;
                    case 6:
                        this.name = "Cuirass";
                        break;
                    case 7:
                        this.name = "Brigadine";
                        break;
                    case 8:
                        this.name = "Chest Plate";
                        break;
                    case 9:
                        this.name = "Plackart";
                        break;
                    default:
                        break;
                }
                break;
            case "leggings":
                switch (typeRand){
                    case 0:
                        this.name = "Leggings";
                        break;
                    case 1:
                        this.name = "Pants";
                        break;
                    case 2:
                        this.name = "Britches";
                        break;
                    case 3:
                        this.name = "Legs";
                        break;
                    case 4:
                        this.name = "Chaps";
                        break;
                    case 5:
                        this.name = "Slacks";
                        break;
                    case 6:
                        this.name = "Pantaloons";
                        break;
                    case 7:
                        this.name = "Trousers";
                        break;
                    case 8:
                        this.name = "Greaves";
                        break;
                    case 9:
                        this.name = "Fatigues";
                        break;
                    default:
                        break;
                }
                break;
            case "boots":
                switch (typeRand){
                    case 0:
                        this.name = "Boots";
                        break;
                    case 1:
                        this.name = "Shoes";
                        break;
                    case 2:
                        this.name = "Foot Covers";
                        break;
                    case 3:
                        this.name = "Cleats";
                        break;
                    case 4:
                        this.name = "Clogs";
                        break;
                    case 5:
                        this.name = "Slippers";
                        break;
                    case 6:
                        this.name = "Sandals";
                        break;
                    case 7:
                        this.name = "Sabatons";
                        break;
                    case 8:
                        this.name = "Boots";
                        break;
                    case 9:
                        this.name = "Boots";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        double highStat = this.damage;
        String highName = "damage";
        if (this.knockBack * 100 > highStat){
            highStat = this.knockBack * 100;
            highName = "knockback";
        }
        if (this.health / 2 > highStat){
            highName = "health";
        }
        if (this.damage == 0 && this.knockBack == 0 && this.health == 0){
            highName = "none";
        }
        int statRand = (int)(Math.random() * 10);
        switch(highName){
            case "health":
                switch (statRand){
                    case 0:
                        this.postName = "of Fortitude";
                        break;
                    case 1:
                        this.postName = "of Endurance";
                        break;
                    case 2:
                        this.postName = "of Resilience";
                        break;
                    case 3:
                        this.postName = "of Health";
                        break;
                    case 4:
                        this.postName = "of Constitution";
                        break;
                    case 5:
                        this.postName = "of Vitality";
                        break;
                    case 6:
                        this.postName = "of Life";
                        break;
                    case 7:
                        this.postName = "of Stamina";
                        break;
                    case 8:
                        this.postName = "of Resistance";
                        break;
                    case 9:
                        this.postName = "of Vigor";
                        break;
                    default:
                        break;
                }
                break;
            case "damage":
                switch (statRand){
                    case 0:
                        this.postName = "of Damage";
                        break;
                    case 1:
                        this.postName = "of Wounding";
                        break;
                    case 2:
                        this.postName = "of Harming";
                        break;
                    case 3:
                        this.postName = "of Vengeance";
                        break;
                    case 4:
                        this.postName = "of Pain";
                        break;
                    case 5:
                        this.postName = "of Destruction";
                        break;
                    case 6:
                        this.postName = "of Killing";
                        break;
                    case 7:
                        this.postName = "of Impaling";
                        break;
                    case 8:
                        this.postName = "of Death";
                        break;
                    case 9:
                        this.postName = "of Fluffy Bunnies";
                        break;
                    default:
                        break;
                }
                break;
            case "knockback":
                switch (statRand){
                    case 0:
                        this.postName = "of Fortitude";
                        break;
                    case 1:
                        this.postName = "of Resilience";
                        break;
                    case 2:
                        this.postName = "of Steadfastness";
                        break;
                    case 3:
                        this.postName = "of Grounding";
                        break;
                    case 4:
                        this.postName = "of SureFeet";
                        break;
                    case 5:
                        this.postName = "of Standing";
                        break;
                    case 6:
                        this.postName = "of Stability";
                        break;
                    case 7:
                        this.postName = "of Constitution";
                        break;
                    case 8:
                        this.postName = "of Immobility";
                        break;
                    case 9:
                        this.postName = "of Weight";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getKnockBack() {
        return knockBack;
    }

    public void setKnockBack(double knockBack) {
        this.knockBack = knockBack;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
