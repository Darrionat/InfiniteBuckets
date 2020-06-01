package me.darrionat.infinitebucket.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darrionat.infinitebucket.InfiniteWaterBucket;
import me.darrionat.infinitebucket.utils.CustomBucket;
import me.darrionat.infinitebucket.utils.Utils;

public class InfiniteBucket implements CommandExecutor {

	private InfiniteWaterBucket plugin;

	public InfiniteBucket(InfiniteWaterBucket plugin) {
		this.plugin = plugin;
		plugin.getCommand("infinitebucket").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(Utils.chat("&cCorrect Usage: /" + label + " [player]"));
			return true;
		}
		Player targetPlayer = Bukkit.getPlayer(args[0]);
		if (targetPlayer == null) {
			sender.sendMessage(Utils.chat("&cThat player is not online!"));
			return true;
		}
		CustomBucket customBucket = new CustomBucket(plugin);
		customBucket.giveBucket(targetPlayer);
		return true;
	}

}