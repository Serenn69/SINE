package fr.phoenix.sineplugin.structureLoader;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class CmdSaveStructure implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may perform this command.");
			return true;
		} else {
			Player player = (Player) sender;
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
				return true;
			} else if (player.getGameMode() != GameMode.CREATIVE) {
				player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
				return true;
			} else if (args.length != 1) {
				player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
				return false;
			} else {
				Selection selection = Main.getInstance().getSelectionManager().getSelection(player);
				selection.saveCurrentSelection();

				try {
					API.writeToFile(args[0], selection);
				} catch (IOException arg7) {
					arg7.printStackTrace();
					player.sendMessage(
							ChatColor.RED + "Something went wrong whe we tried to write your structure to a file.");
				}

				player.sendMessage(ChatColor.DARK_PURPLE
						+ "Successfully saved your structure in the directory \'plugins/structures/" + args[0]
						+ ".json\'.");
				return true;
			}
		}
	}
}
