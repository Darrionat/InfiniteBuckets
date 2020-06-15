package me.darrionat.infinitebucket.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darrionat.infinitebucket.InfiniteWaterBucket;
import me.darrionat.infinitebucket.enums.Fluid;
import me.darrionat.infinitebucket.utils.CustomBucket;
import me.darrionat.infinitebucket.utils.Utils;

public class InfiniteBucket implements CommandExecutor {

	private InfiniteWaterBucket plugin;

	public InfiniteBucket(InfiniteWaterBucket plugin) {
		this.plugin = plugin;
		plugin.getCommand("infinitebucket").setExecutor(this);
	}

	CustomBucket customBucket;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!p.hasPermission("infinitewaterbucket.give")) {
				p.sendMessage(Utils.chat("&cYou do not have that permission!"));
				return true;
			}
		}
		if (args.length != 2) {
			sender.sendMessage(Utils.chat("&cCorrect Usage: /" + label + " [player] [water/lava]"));
			return true;
		}
		Player targetPlayer = Bukkit.getPlayer(args[0]);
		if (targetPlayer == null) {
			sender.sendMessage(Utils.chat("&cThat player is not online!"));
			return true;
		}
		if (!args[1].equalsIgnoreCase("lava") && !args[1].equalsIgnoreCase("water")) {
			sender.sendMessage(Utils.chat("&cYou can only give a water or lava bucket"));
			return true;
		}

		if (args[1].equalsIgnoreCase("water")) {
			customBucket = new CustomBucket(plugin, Fluid.WATER);
		} else {
			customBucket = new CustomBucket(plugin, Fluid.LAVA);
		}
		customBucket.giveBucket(targetPlayer);
		return true;
	}

}