package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.AuctionItemComparator;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.Category;

import java.util.*;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/24/13
 * Time: 3:25 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class AuctionCategory {
    private Category catName;
    private Map<Integer, AuctionItem> catItems = new HashMap<>();
    private ItemStack displayItem;

    public AuctionCategory(Category cat, ItemStack item){
        this.catName = cat;
        this.displayItem = item;
    }
    public Map<Integer, AuctionItem> getCatItems(){
        return this.catItems;
    }
    public List<AuctionItem> getCatItems(String sorting) {
        List<AuctionItem> items = new ArrayList<>();
        if (sorting == null){
            for (int key : catItems.keySet()){
                items.add(catItems.get(key));
            }
            return items;
        }
        switch(sorting){
            case "type":
                while (items.size() < this.catItems.size()){
                    for (Material mat : Material.values()){
                        for (AuctionItem aI : this.catItems.values()){
                            if (aI.getiS().getType().equals(mat)){
                                items.add(aI);
                            }
                        }
                    }

                }
                return items;
            case "buyout":
                items = new ArrayList<>(catItems.values());
                Collections.sort(items, AuctionItemComparator.getComparator(
                        AuctionItemComparator.BUYOUT_SORT));
                return items;
            case "minimum":
                items = new ArrayList<>(catItems.values());
                Collections.sort(items, AuctionItemComparator.getComparator(
                                                AuctionItemComparator.MINIMUM_SORT));
                return items;
            case "size":
                items = new ArrayList<>(catItems.values());
                Collections.sort(items, AuctionItemComparator.decending(
                                            AuctionItemComparator.getComparator(
                                                    AuctionItemComparator.SIZE_SORT)));
                return items;
            case "time":
                items = new ArrayList<>(catItems.values());
                Collections.sort(items, AuctionItemComparator.getComparator(
                                                    AuctionItemComparator.TIME_SORT));
                return items;
            default:
                return items;
        }
    }

    public void setCatItems(Map<Integer, AuctionItem> catItems) {
        this.catItems = catItems;
    }

    public Category getCatName() {
        return catName;
    }

    public void setCatName(Category catName) {
        this.catName = catName;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }
}
