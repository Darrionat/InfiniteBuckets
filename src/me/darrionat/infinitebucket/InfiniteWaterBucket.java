package me.darrionat.infinitebucket;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.infinitebucket.commands.InfiniteBucket;
import me.darrionat.infinitebucket.listeners.BucketEvent;

public class InfiniteWaterBucket extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new InfiniteBucket(this);
		new BucketEvent(this);
	}
}