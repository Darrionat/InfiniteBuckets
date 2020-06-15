package me.darrionat.infinitebucket.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import me.darrionat.infinitebucket.InfiniteWaterBucket;
import me.darrionat.infinitebucket.enums.Fluid;
import me.darrionat.infinitebucket.utils.CustomBucket;

public class BucketEvent implements Listener {

	private InfiniteWaterBucket plugin;
	private CustomBucket waterBucket;
	private CustomBucket lavaBucket;

	public BucketEvent(InfiniteWaterBucket plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		waterBucket = new CustomBucket(plugin, Fluid.WATER);
		lavaBucket = new CustomBucket(plugin, Fluid.LAVA);

	}

	@EventHandler
	public void onPour(PlayerBucketEmptyEvent event) {
		Player p = event.getPlayer();
		@SuppressWarnings("deprecation")
		ItemStack item = p.getItemInHand();
		if (waterBucket.isCustomBucket(item)) {
			returnBucket(p, waterBucket.bucket);
			return;
		}
		if (lavaBucket.isCustomBucket(item)) {
			returnBucket(p, lavaBucket.bucket);
			return;
		}
	}

	public void returnBucket(Player p, ItemStack bucket) {
		plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin,
				() -> p.getInventory().setItem(p.getInventory().getHeldItemSlot(), bucket));
	}
}
