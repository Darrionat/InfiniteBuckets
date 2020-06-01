package me.darrionat.infinitebucket.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.darrionat.infinitebucket.InfiniteWaterBucket;

public class CustomBucket {

	private FileConfiguration config;
	public Material type;
	public String name;
	public List<String> lore;
	public boolean customModelDataEnabled;
	public int customModelData;
	public ItemStack bucket;

	public CustomBucket(InfiniteWaterBucket plugin) {
		config = plugin.getConfig();
		type = Material.WATER_BUCKET;
		name = Utils.chat(config.getString("name"));
		lore = config.getStringList("lore");
		customModelDataEnabled = config.getBoolean("customModelData.enabled");
		customModelData = config.getInt("customModelData.data");
		bucket = getBucket();
	}

	private ItemStack getBucket() {
		ItemStack bucket = new ItemStack(type, 1);
		ItemMeta meta = bucket.getItemMeta();

		meta.setDisplayName(name);

		List<String> lore = new ArrayList<>();
		List<String> loreList = this.lore;
		for (String s : loreList) {
			lore.add(Utils.chat(s));
		}
		meta.setLore(lore);

		if (customModelDataEnabled) {
			meta.setCustomModelData(customModelData);
		}
		bucket.setItemMeta(meta);
		return bucket;
	}

	public void giveBucket(Player p) {
		if (hasAvaliableSlot(p)) {
			p.getInventory().addItem(bucket);
			return;
		}
		Location loc = p.getLocation();
		loc.getWorld().dropItemNaturally(loc, bucket);
	}

	public boolean hasAvaliableSlot(Player p) {
		Inventory inv = p.getInventory();
		Boolean check = false;
		for (ItemStack item : inv.getContents()) {
			if (item == null) {
				check = true;
				break;
			}
		}
		return check;
	}

	public boolean isCustomBucket(ItemStack item) {
		if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(name))
			return false;
		return true;
	}

}
