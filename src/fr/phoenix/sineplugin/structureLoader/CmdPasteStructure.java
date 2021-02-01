package fr.phoenix.sineplugin.structureLoader;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class CmdPasteStructure implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may perform this command.");
			return true;
		} else {
			Player player = (Player) sender;
			if (args.length != 1) {
				player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
				return false;
			} else if (!player.isOp()) {
				player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
				return true;
			} else if (player.getGameMode() != GameMode.CREATIVE) {
				player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
				return true;
			} else {
				API.placeStructure(args[0], player.getLocation());
				return true;
			}
		}
	}
}
