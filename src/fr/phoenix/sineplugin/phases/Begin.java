package fr.phoenix.sineplugin.phases;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.colors.JavaColors;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;

public class Begin {
	
	public static int iO, iT;

	// Création des équipes
	public void createTeams() {

		if (Vars.sineBoardOne.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")) != null) {
		} else {
			Vars.oneTeamOne = Vars.sineBoardOne.registerNewTeam(Files.setsNameConfig.getString("teams.name.one"));
			Vars.oneTeamOne.setPrefix(Files.setsNameConfig.getString("teams.prefix.one").replace("&", "§"));
			Vars.oneTeamOne.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.one").replace("&", "§"));
		}
		if (Vars.sineBoardOne.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")) != null) {
		} else {
			Vars.oneTeamTwo = Vars.sineBoardOne.registerNewTeam(Files.setsNameConfig.getString("teams.name.two"));
			Vars.oneTeamTwo.setPrefix(Files.setsNameConfig.getString("teams.prefix.two").replace("&", "§"));
			Vars.oneTeamTwo.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.two").replace("&", "§"));
		}
		if (Vars.sineBoardTwo.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")) != null) {
		} else {
			Vars.twoTeamOne = Vars.sineBoardTwo.registerNewTeam(Files.setsNameConfig.getString("teams.name.one"));
			Vars.twoTeamOne.setPrefix(Files.setsNameConfig.getString("teams.prefix.one").replace("&", "§"));
			Vars.twoTeamOne.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.one").replace("&", "§"));
		}
		if (Vars.sineBoardTwo.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")) != null) {
		} else {
			Vars.twoTeamTwo = Vars.sineBoardTwo.registerNewTeam(Files.setsNameConfig.getString("teams.name.two"));
			Vars.twoTeamTwo.setPrefix(Files.setsNameConfig.getString("teams.prefix.two").replace("&", "§"));
			Vars.twoTeamTwo.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.two").replace("&", "§"));
		}
		if (Vars.sineBoardCaster.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")) != null) {
		} else {
			Vars.casterTeamOne = Vars.sineBoardCaster.registerNewTeam(Files.setsNameConfig.getString("teams.name.one"));
			Vars.casterTeamOne.setPrefix(Files.setsNameConfig.getString("teams.prefix.one").replace("&", "§"));
			Vars.casterTeamOne.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.one").replace("&", "§"));
		}
		if (Vars.sineBoardCaster.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")) != null) {
		} else {
			Vars.casterTeamTwo = Vars.sineBoardCaster.registerNewTeam(Files.setsNameConfig.getString("teams.name.two"));
			Vars.casterTeamTwo.setPrefix(Files.setsNameConfig.getString("teams.prefix.two").replace("&", "§"));
			Vars.casterTeamTwo.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.two").replace("&", "§"));
		}
		if (Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")) != null) {
		} else {
			Vars.teamOne = Vars.sineBoard.registerNewTeam(Files.setsNameConfig.getString("teams.name.one"));
			Vars.teamOne.setPrefix(Files.setsNameConfig.getString("teams.prefix.one").replace("&", "§"));
			Vars.teamOne.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.one").replace("&", "§"));
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Red+"Team One "+JavaColors.Magenta+"Creation          "+JavaColors.White+"|"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
		}
		if (Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")) != null) {
		} else {
			Vars.teamTwo = Vars.sineBoard.registerNewTeam(Files.setsNameConfig.getString("teams.name.two"));
			Vars.teamTwo.setPrefix(Files.setsNameConfig.getString("teams.prefix.two").replace("&", "§"));
			Vars.teamTwo.setDisplayName("§l" + Files.setsNameConfig.getString("teams.display.two").replace("&", "§"));
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Green+"Team Two "+JavaColors.Magenta+"Creation          "+JavaColors.White+"|"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
		}
	}

	// Objectif du nombre de joueurs avant le début de la partie
	@SuppressWarnings("deprecation")
	public void createNbrPlayers() {

		Vars.teamsNbrP.put(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"), 0);
		Vars.teamsNbrP.put(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"), 0);

		if (Vars.sineBoard.getObjective("nbrP") != null) {
			Vars.nbrP = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("nbrP");
			Vars.nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
			Score scNbrPO = Vars.nbrP
					.getScore(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
			scNbrPO.setScore(
					Vars.teamsNbrP.get(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§")));
			Score scNbrPT = Vars.nbrP
					.getScore(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
			scNbrPT.setScore(
					Vars.teamsNbrP.get(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§")));
			Vars.nbrP.setDisplaySlot(DisplaySlot.SIDEBAR);
		} else {
			Vars.nbrP = Vars.sineBoard.registerNewObjective("nbrP", "dummy");
			Vars.nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
			Score scNbrPO = Vars.nbrP
					.getScore(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
			scNbrPO.setScore(
					Vars.teamsNbrP.get(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§")));
			Score scNbrPT = Vars.nbrP
					.getScore(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
			scNbrPT.setScore(
					Vars.teamsNbrP.get(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§")));
			Vars.nbrP.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
	}

	@SuppressWarnings("deprecation")
	public void createTrackerTeams() {

		for (Player p : Bukkit.getOnlinePlayers()) {

			if (Vars.pNameCaster.contains(p.getName()) || Vars.pNameSpec.contains(p.getName())) {
				p.setScoreboard(Vars.sineBoardCaster);
				
				if (Vars.sineBoardCaster.getObjective("trackercaster") == null) {
					Vars.trackerCaster = Vars.sineBoardCaster.registerNewObjective("trackercaster", "dummy");
				}
				Vars.trackerCaster = Vars.sineBoardCaster.getObjective("trackercaster");
				Vars.trackerCaster.setDisplayName("   §6Teams Tracker :   ");
				Vars.trackerCaster
						.getScore("§f--- "
								+ Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"))
						.setScore(12);
				Vars.trackerCaster
						.getScore("§f--- "
								+ Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"))
						.setScore(6);
				Vars.trackerCaster.getScore("§f--- ").setScore(0);
				Vars.trackerCaster.getScore(" §6S.I.N.E Alpha v0.3 ").setScore(-1);

				for (Player pl : Bukkit.getOnlinePlayers()) {

					if (Vars.pNameTeamOne.containsKey(pl.getName())) {
						iO = 11;
						for (String name : Vars.pNameTeamOne.keySet()) {
						
							Vars.sineBoardCaster.resetScores("§7§o§m" + name);
							Vars.trackerCaster.getScore(name).setScore(iO);
							iO--;
							if (iO < 6) {
								for (Player pOp : Bukkit.getOnlinePlayers()) {
									if (pOp.isOp()) {
										pOp.sendMessage("§cATTENTION : slot joueur du tracker inférieur ou égale au maximum ! Sûrement trop de joueurs dans l'équipe !");
									}
								}
							}
						}
					}
					if (Vars.pNameTeamTwo.containsKey(pl.getName())) {
						iT = 5;
						for (String name : Vars.pNameTeamTwo.keySet()) {
						
							Vars.sineBoardCaster.resetScores("§7§o§m" + name);
							Vars.trackerCaster.getScore(name).setScore(iT);
							iT--;
							if (iT < 0) {
								for (Player pOp : Bukkit.getOnlinePlayers()) {
									if (pOp.isOp()) {
										pOp.sendMessage("§cATTENTION : slot joueur du tracker inférieur ou égale au maximum ! Sûrement trop de joueurs dans l'équipe !");
									}
								}
							}
						}
					}
				}
				Vars.trackerCaster.setDisplaySlot(DisplaySlot.SIDEBAR);
			}
			
			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				p.setScoreboard(Vars.sineBoardOne);
				
				if (Vars.sineBoardOne.getObjective("showHealth") == null) {
					Vars.oneHealth = Vars.sineBoardOne.registerNewObjective("showHealth", "health");
				}
				Vars.oneHealth = Vars.sineBoardOne.getObjective("showHealth");
				Vars.oneHealth.setDisplayName(" §c§l\u2764");
				Vars.oneHealth.setDisplaySlot(DisplaySlot.BELOW_NAME);


				if (Vars.sineBoardOne.getObjective("trackerOne") == null) {
					Vars.trackerOne = Vars.sineBoardOne.registerNewObjective("trackerOne", "dummy");
				}
				Vars.trackerOne = Vars.sineBoardOne.getObjective("trackerOne");
				Vars.trackerOne.setDisplayName("    §4RED §6Tracker :    ");
				Vars.trackerOne.getScore("§f---").setScore(12);
				Vars.trackerOne.getScore("§f--- ").setScore(6);
				Vars.trackerOne.getScore(" §6S.I.N.E Alpha v0.3 ").setScore(-1);

				iO = 11;
				for (String name : Vars.pNameTeamOne.keySet()) {
				
					Vars.sineBoardOne.resetScores("§7§o§m" + name);
					Vars.trackerOne.getScore(name).setScore(iO);
					iO--;
					if (iO < 6) {
						for (Player pOp : Bukkit.getOnlinePlayers()) {
							if (pOp.isOp()) {
								pOp.sendMessage("§cATTENTION : slot joueur du tracker inférieur ou égale au maximum ! Sûrement trop de joueurs dans l'équipe !");
							}
						}
					}
				}
				Vars.trackerOne.setDisplaySlot(DisplaySlot.SIDEBAR);
			}
			
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				p.setScoreboard(Vars.sineBoardTwo);
				
				if (Vars.sineBoardTwo.getObjective("showHealth") == null) {
					Vars.twoHealth = Vars.sineBoardTwo.registerNewObjective("showHealth", "health");
				}
				Vars.twoHealth = Vars.sineBoardTwo.getObjective("showHealth");
				Vars.twoHealth.setDisplayName(" §c§l\u2764");
				Vars.twoHealth.setDisplaySlot(DisplaySlot.BELOW_NAME);

				if (Vars.sineBoardTwo.getObjective("trackerTwo") == null) {
					Vars.trackerTwo = Vars.sineBoardTwo.registerNewObjective("trackerTwo", "dummy");
				}
				Vars.trackerTwo = Vars.sineBoardTwo.getObjective("trackerTwo");
				Vars.trackerTwo.setDisplayName("   §aGREEN §6Tracker :   ");
				Vars.trackerTwo.getScore("§f---").setScore(6);
				Vars.trackerTwo.getScore("§f--- ").setScore(0);
				Vars.trackerTwo.getScore(" §6S.I.N.E Alpha v0.3 ").setScore(-1);

				iT = 5;
				for (String name : Vars.pNameTeamTwo.keySet()) {
				
					Vars.sineBoardTwo.resetScores("§7§o§m" + name);
					Vars.trackerTwo.getScore(name).setScore(iT);
					iT--;
					if (iT < 0) {
						for (Player pOp : Bukkit.getOnlinePlayers()) {
							if (pOp.isOp()) {
								pOp.sendMessage("§cATTENTION : slot joueur du tracker inférieur ou égale au maximum ! Sûrement trop de joueurs dans l'équipe !");
							}
						}
					}
				}
				Vars.trackerTwo.setDisplaySlot(DisplaySlot.SIDEBAR);
			}
		}
	}

	public void displayTeamsGolds() {

		Vars.aBarGolds = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					if (Vars.pNameTeamOne.containsKey(p.getName())) {
						IChatBaseComponent goldsBarOne = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&4&lRED &6&lGolds &f&l: &0&l" + Vars.oneGoldsTotal)
								+ "\"}");
						PacketPlayOutChat barOne = new PacketPlayOutChat(goldsBarOne);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(barOne);
					}
					if (Vars.pNameTeamTwo.containsKey(p.getName())) {
						IChatBaseComponent goldsBarTwo = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
										"&2&lGREEN &6&lGolds &f&l: &0&l" + Vars.twoGoldsTotal) + "\"}");
						PacketPlayOutChat barTwo = new PacketPlayOutChat(goldsBarTwo);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(barTwo);
					}
					if (Vars.pNameCaster.contains(p.getName()) || Vars.pNameSpec.contains(p.getName()) || Vars.pNameCaster.contains(null) || Vars.pNameSpec.contains(null)) {
						IChatBaseComponent goldsBarCaster = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&',
										"&4&lRED &f&l: &0&l" + Vars.oneGoldsTotal + " &0&l- &a&lGREEN &f&l: &0&l" + Vars.twoGoldsTotal)
								+ "\"}");
						PacketPlayOutChat barCaster = new PacketPlayOutChat(goldsBarCaster);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(barCaster);
					}
				}
			}
		}, 5, 5);
	}
	
}
