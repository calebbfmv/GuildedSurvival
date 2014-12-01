package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class Kit {

	private List<ItemStack> misc;
	private String name;
	private String kitName;
	private List<String> lore;
	private String permission;
	private int cooldownTime = 24;
	private static HashMap<String, Kit> kits = new HashMap<String, Kit>();

	/**
	 * Creates a new Kit.
	 * 
	 * @param kitName
	 * @param name
	 * @param lore
	 * @param permission
	 * @param cooldownTime
	 * @param items
	 * @param attributes
	 */
	public Kit(String kitName, String name, List<String> lore,
			String permission, int cooldownTime, List<ItemStack> misc) {
		this.name = name;
		this.lore = lore;
		this.permission = permission;
		this.cooldownTime = cooldownTime *60*60;
		this.misc = misc;
		if (!kits.containsKey(kitName)) {
			kits.put(kitName, this);
		}
	}

	public List<ItemStack> getMisc() {
		return misc;
	}

	public void setMisc(List<ItemStack> misc) {
		this.misc = misc;
	}

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public static Kit getKit(String kitName) {
		return kits.get(kitName) == null ? null : kits.get(kitName);
	}

	public static HashMap<String, Kit> getKits() {
		return kits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void give(Player player){
		for(ItemStack item : misc){
			ItemMeta meta = item.getItemMeta();
			String name = getName();
			List<String> lore = getLore();
			meta.setDisplayName(name);
			meta.setLore(lore);
			item.setItemMeta(meta);
			player.getInventory().addItem(item);
		}
	}
	
	public static void give(Kit kit, Player player){
		for(ItemStack item : kit.getMisc()){
			ItemMeta meta = item.getItemMeta();
			String name = kit.getName();
			List<String> lore = kit.getLore();
			meta.setDisplayName(name);
			meta.setLore(lore);
			item.setItemMeta(meta);
			player.getInventory().addItem(item);
		}
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}
}