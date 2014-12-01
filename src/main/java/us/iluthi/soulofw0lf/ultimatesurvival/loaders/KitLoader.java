package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Kit;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.AttributeHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KitLoader {

	public void loadAll(){
		File file = new File("plugins/UltimateSurvival/kits.yml");
		YamlConfiguration kits = YamlConfiguration.loadConfiguration(file);
		List<ItemStack> items = new ArrayList<ItemStack>();	
		if(kits.get("kits") != null){
			for(String s : kits.getConfigurationSection("kits").getKeys(false)){
				String kitName = s;
				String name = kits.getString("kits." + s + ".name");
				List<String> lore = kits.getStringList("kits." + s + ".lore");
				String permission = kits.getString("kits. " + s + ".permission");
				int cooldownTime = kits.getInt("kits." + s + ".cooldownTime");
				for(String i : kits.getStringList("kits." + s + ".items")){
					Material mat = Material.getMaterial(i);
					String[] sa = i.split(":");
					if(sa.length == 0){
						ItemStack item = new ItemStack(mat);
						items.add(item);
					} else if(sa.length == 1){
						Integer a = Integer.parseInt(sa[0]);
						ItemStack item = new ItemStack(mat, a);
						items.add(item);
					} else if(sa.length == 2){
						Integer a = Integer.parseInt(sa[0]);
						ItemStack item = new ItemStack(mat, a);
						String wtg = sa[1];
						if(wtg.contains("health")){
							items.add(AttributeHandler.addHealth(item, 1));
						}
						if(wtg.contains("damage")){
							items.add(AttributeHandler.addDamage(item, 1));
						}
						if(wtg.contains("knockback")){
							items.add(AttributeHandler.addKnockBackRes(item, 1));
						}
					} else if(sa.length == 3){
						Integer a = Integer.parseInt(sa[0]);
						ItemStack item = new ItemStack(mat, a);
						String wtg = sa[1];
						Integer ai = 1;
						try {
							ai = Integer.parseInt(sa[2]);
						} catch (NumberFormatException e){
							ai = 1;
							e.printStackTrace();
						}
						if(wtg.contains("health")){
							items.add(AttributeHandler.addHealth(item, ai));
						}
						if(wtg.contains("damage")){
							items.add(AttributeHandler.addDamage(item, ai));
						}
						if(wtg.contains("knockback")){
							items.add(AttributeHandler.addKnockBackRes(item, ai));
						}
					}
				}
				new Kit(kitName, name, lore, permission, cooldownTime, items);
			}
		}
	}
}