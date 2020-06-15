package me.darrionat.infinitebucket;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.infinitebucket.Files.FileManager;
import me.darrionat.infinitebucket.commands.InfiniteBucket;
import me.darrionat.infinitebucket.listeners.BucketEvent;
import me.darrionat.infinitebucket.utils.Utils;

public class InfiniteWaterBucket extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		FileManager fileManager = new FileManager(this);
		if (fileManager.matchConfig("config")) {
			systemLog("Updated config.yml");
		}
		new InfiniteBucket(this);
		new BucketEvent(this);
	}

	public void systemLog(String s) {
		String prefix = getName() + " " + getDescription().getVersion();
		System.out.println(prefix + " " + Utils.chat(s));
	}
}