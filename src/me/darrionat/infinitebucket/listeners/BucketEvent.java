package me.darrionat.infinitebucket.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import me.darrionat.infinitebucket.InfiniteWaterBucket;
import me.darrionat.infinitebucket.utils.CustomBucket;

public class BucketEvent implements Listener {

	private InfiniteWaterBucket plugin;
	private CustomBucket customBucket;

	public BucketEvent(InfiniteWaterBucket plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		customBucket = new CustomBucket(plugin);
	}

	@EventHandler
	public void onPour(PlayerBucketEmptyEvent event) {
		Player p = event.getPlayer();
		@SuppressWarnings("deprecation")
		ItemStack item = p.getItemInHand();
		if (customBucket.isCustomBucket(item)) {
			returnBucket(p, item);
		}
	}

	public void returnBucket(Player p, ItemStack item) {
		plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin,
				() -> p.getInventory().setItem(p.getInventory().getHeldItemSlot(), customBucket.bucket));
	}
}
