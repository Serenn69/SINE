package fr.phoenix.sineplugin.structureLoader;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class CmdSaveSpecialStructure implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may perform this command.");
			return true;
		} else {
			Player player = (Player) sender;
			Material baseMaterial = null;
			Byte baseData = null;
			Boolean ignoreBaseBlocks = null;
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
				return true;
			} else if (player.getGameMode() != GameMode.CREATIVE) {
				player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
				return true;
			} else if (args.length != 4) {
				player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
				return false;
			} else {
				baseMaterial = Material.getMaterial(args[1].toUpperCase());
				if (baseMaterial == null) {
					player.sendMessage("No such Material \'" + args[1] + "\'.");
					return false;
				} else {
					try {
						baseData = Byte.valueOf(Byte.parseByte(args[2]));
					} catch (NumberFormatException arg11) {
						player.sendMessage(ChatColor.RED + "Wrong command format.");
						return false;
					}

					ignoreBaseBlocks = Boolean.valueOf(Boolean.parseBoolean(args[3]));
					Selection selection = Main.getInstance().getSelectionManager().getSelection(player);
					selection.saveCurrentSelectionOnlyAbove(baseMaterial, baseData.byteValue(),
							ignoreBaseBlocks.booleanValue());

					try {
						API.writeToFile(args[0], selection);
					} catch (IOException arg10) {
						arg10.printStackTrace();
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
}
