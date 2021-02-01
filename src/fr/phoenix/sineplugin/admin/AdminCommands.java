package fr.phoenix.sineplugin.admin;

import java.net.URL;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.MVP.MvpChoosen;
import fr.phoenix.sineplugin.MVP.MvpVotePoints;
import fr.phoenix.sineplugin.caster.CasterHotbar;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class AdminCommands implements CommandExecutor {

	public static ItemStack open;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String pass, String[] options) {

		if (sender instanceof Player) {

			Player p = (Player) sender;

			new PlayerStats().createProfileSeason(p);

			PlayerStats dataPS = Vars.pStatsSeason.get(p);
			
			if (cmd.getName().equalsIgnoreCase("admin")) {

				if (new AdminAccess().checkBL(p, 40)) {
					if (dataPS.getAccess() == 40) {
						p.sendMessage("§0[§4SINE§0] §7§oWelcome " + p.getName() + " your profile is already auth !");
						if (Vars.pNameSpec.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spectator");
							Vars.pNameSpec.remove(p.getName());
						}
						if (Vars.pNameCaster.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
							Vars.pNameCaster.remove(p.getName());
						}
						p.sendMessage("§0[§4SINE§0] §7§oYou are now §6Admin");
						p.setOp(true);
						p.setGameMode(GameMode.CREATIVE);
						hotbarAdminMenu(p);
						Vars.pNameAdmin.add(p.getName());
						if (!Vars.pNameGamers.contains(p.getName())) {
							Vars.pNameGamers.add(p.getName());
						}
					} else {
						if (options.length == 0) {
							p.sendMessage("§0[§4SINE§0] §7§o/admin < password >");
						}
						if (options.length == 1) {
							if (options[0].equals(Files.gradeConfig.getString("access.admin"))) {
								if (Vars.pNameSpec.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spectator");
									Vars.pNameSpec.remove(p.getName());
								}
								if (Vars.pNameCaster.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
									Vars.pNameCaster.remove(p.getName());
								}
								p.sendMessage("§0[§4SINE§0] §7§oAccess allow, you are now §6Admin");
								p.setOp(true);
								p.setGameMode(GameMode.CREATIVE);
								hotbarAdminMenu(p);
								Vars.pNameAdmin.add(p.getName());
								if (!Vars.pNameGamers.contains(p.getName())) {
									Vars.pNameGamers.add(p.getName());
								}
								new AdminAccess().auth(p, "admin");
							} else {
								new AdminAccess().wrongAuthPass(p, "admin");
							}
						}
					}
				}
			}

			if (cmd.getName().equalsIgnoreCase("caster")) {

				if (new AdminAccess().checkBL(p, 30)) {
					if (dataPS.getAccess() >= 30) {
						p.sendMessage("§0[§4SINE§0] §7§oWelcome " + p.getName() + " your profile is already auth !");
						if (Vars.pNameSpec.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spectator");
							Vars.pNameSpec.remove(p.getName());
						}
						if (Vars.pNameAdmin.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
							deleteAdminMenu(p);
							Vars.pNameAdmin.remove(p.getName());
						}
						p.sendMessage("§0[§4SINE§0] §7§oYou are now §6Caster");
						p.setOp(true);
						p.setGameMode(GameMode.SPECTATOR);
						Vars.pNameCaster.add(p.getName());
						if (!Vars.pNameGamers.contains(p.getName())) {
							Vars.pNameGamers.add(p.getName());
						}
					} else {
						if (options.length == 0) {
							p.sendMessage("§0[§4SINE§0] §7§o/caster < password >");
						}
						if (options.length == 1) {
							if (options[0].equals(Files.gradeConfig.getString("access.caster"))) {
								if (Vars.pNameSpec.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spectator");
									Vars.pNameSpec.remove(p.getName());
								}
								if (Vars.pNameAdmin.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
									deleteAdminMenu(p);
									Vars.pNameAdmin.remove(p.getName());
								}
								p.sendMessage("§0[§4SINE§0] §7§oAccess allow, you are now §6Caster");
								p.setOp(true);
								p.setGameMode(GameMode.SPECTATOR);
								Vars.pNameCaster.add(p.getName());
								new CasterHotbar().casterStuffMenu(p, 1);
								if (!Vars.pNameGamers.contains(p.getName())) {
									Vars.pNameGamers.add(p.getName());
								}
								new AdminAccess().auth(p, "caster");
							} else {
								new AdminAccess().wrongAuthPass(p, "caster");
							}
						}
					}
				}
			}

			if (cmd.getName().equalsIgnoreCase("spec")) {

				if (new AdminAccess().checkBL(p, 20)) {
					if (dataPS.getAccess() >= 20) {
						p.sendMessage("§0[§4SINE§0] §7§oWelcome " + p.getName() + " your profile is already auth !");
						if (Vars.pNameCaster.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
							Vars.pNameCaster.remove(p.getName());
						}
						if (Vars.pNameAdmin.contains(p.getName())) {
							p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
							deleteAdminMenu(p);
							Vars.pNameAdmin.remove(p.getName());
						}
						p.sendMessage("§0[§4SINE§0] §7§oYou are now §6Spectator");
						p.setOp(false);
						p.setGameMode(GameMode.SPECTATOR);
						Vars.pNameSpec.add(p.getName());
						new CasterHotbar().specStuffMenu(p, 1);
						if (!Vars.pNameGamers.contains(p.getName())) {
							Vars.pNameGamers.add(p.getName());
						}
					} else {
						if (options.length == 0) {
							p.sendMessage("§0[§4SINE§0] §7§o/spec < password >");
						}
						if (options.length == 1) {
							if (options[0].equals(Files.gradeConfig.getString("access.spec"))) {
								if (Vars.pNameCaster.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");

									Vars.pNameCaster.remove(p.getName());
								}
								if (Vars.pNameAdmin.contains(p.getName())) {
									p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
									deleteAdminMenu(p);
									Vars.pNameAdmin.remove(p.getName());
								}
								p.sendMessage("§0[§4SINE§0] §7§oAccess allow, you are now §6Spectator");
								p.setOp(false);
								p.setGameMode(GameMode.SPECTATOR);
								Vars.pNameSpec.add(p.getName());
								if (!Vars.pNameGamers.contains(p.getName())) {
									Vars.pNameGamers.add(p.getName());
								}
								new AdminAccess().auth(p, "spec");
							} else {
								new AdminAccess().wrongAuthPass(p, "spec");
							}
						}
					}
				}
			}

			if (cmd.getName().equalsIgnoreCase("player")) {

				if (options.length == 0) {
					if (new AdminAccess().checkBL(p, 10)) {
						if (dataPS.getAccess() >= 10) {
							if (Vars.pNameAdmin.contains(p.getName())) {
								p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
								Vars.pNameAdmin.remove(p.getName());
								deleteAdminMenu(p);
							}
							if (Vars.pNameCaster.contains(p.getName())) {
								p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
								Vars.pNameCaster.remove(p.getName());
							}
							if (Vars.pNameSpec.contains(p.getName())) {
								p.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spectator");
								Vars.pNameSpec.remove(p.getName());
							}
							if (p.isOp())
								p.setOp(false);
							p.sendMessage(
									"§0[§4SINE§0] §7§oNow you are just a lambda §6Player§7§o... don't cry baby !");
							p.setGameMode(GameMode.ADVENTURE);
						}
					}
				}
			}

			if (cmd.getName().equalsIgnoreCase("unblack")) {

				if (Vars.pNameAdmin.contains(p.getName())) {
					if (options.length == 0) {
						p.sendMessage("§0[§4SINE§0] §7§o/unblack < player | spec | caster > < playername >");
					}
					if (options.length == 1) {
						p.sendMessage("§0[§4SINE§0] §7§o/unblack < player | spec | caster > < playername >");
					}
					if (options.length == 2) {
						Player pl = Bukkit.getServer().getPlayer(getUUID(options[1]));
						if (pl == null) {
							p.sendMessage("§0[§4SINE§0] §7§oThis player is not online !");
						}
						String rank = options[0];
						
						new AdminAccess().pardon(pl, rank);
						p.sendMessage("§0[§4SINE§0] §7§o" + pl.getName() + " has been unblacklist");
						pl.sendMessage("§0[§4SINE§0] §7§oYou have been unblacklist !");
					}
				} else {
					p.sendMessage("§0[§4SINE§0] §7§oOnly §6Admin §7§ocan unblack !");
				}
			}
			if (cmd.getName().equalsIgnoreCase("vote")) {

				if (options.length == 0) {
					if (MvpChoosen.voteOpened != 0) {
						if (Vars.oneScore < 2 || Vars.twoScore < 2) {
							p.sendMessage("§0[§4SINE§0] §7§oGame is not enough engaged !");
							p.sendMessage("§0[§4SINE§0] §7§oAt least one score of 2 is required to begin to vote !");
						} else if (Vars.oneScore >= 2 || Vars.twoScore >= 2) {
							MvpVotePoints menu = new MvpVotePoints(45);
							menu.createVotePlayerMenu(p);
						}
					} else {
						p.sendMessage("§0[§4SINE§0] §7§oVotes ares closed !");
					}
				}
			}
		}
		return false;

	}

	public void hotbarAdminMenu(Player p) {

		open = new ItemStack(Material.COMMAND_BLOCK, 1);
		ItemMeta tpM = open.getItemMeta();
		tpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tpM.setDisplayName("§6§lAdmin Menu");
		open.setItemMeta(tpM);
		p.getInventory().setItem(8, open);
	}

	public void deleteAdminMenu(Player p) {

		if (p.getInventory().contains(Material.COMMAND_BLOCK)) {
			p.getInventory().clear(8);
		}
	}
	
	public static UUID getUUID(String playerName) {
        try {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;

            String UUIDJson = IOUtils.toString(new URL(url));

            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

            UUID uuid = (UUID) UUIDObject.get("id");
            
            return uuid;
        } catch (Exception e) {
        	return null;
        }
    }

}
