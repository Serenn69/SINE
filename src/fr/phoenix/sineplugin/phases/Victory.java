package fr.phoenix.sineplugin.phases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.MVP.MvpChoosen;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import fr.phoenix.sineplugin.playerProfile.PlayerStatsManager;
import fr.phoenix.sineplugin.recapGame.GameStatsFile;
import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class Victory {

	public PlayerStats ps;

	public static int announceScore;
	public static String teamWin;

	public void updateScore(String team) {
		
		teamWin = team;

		for (String pNO : Vars.pNameTeamOne.keySet()) {
			Player pO = Bukkit.getPlayerExact(pNO);

			ps.updateMaxDealtDmg(pO);
			ps.updateTotalDealtDmg(pO);
			ps.updateMaxTakenDmg(pO);
			ps.updateTotalTakenDmg(pO);
		}
		for (String pNT : Vars.pNameTeamOne.keySet()) {
			Player pT = Bukkit.getPlayerExact(pNT);

			ps.updateMaxDealtDmg(pT);
			ps.updateTotalDealtDmg(pT);
			ps.updateMaxTakenDmg(pT);
			ps.updateTotalTakenDmg(pT);
		}

		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			Main.getInstance().getServer().getScheduler().cancelTask(Countdown.fight);
		}

		Vars.freeze = 1;

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.isDead() && p.getHealth() < 20 && p.getHealth() >= 1) {
				p.setHealth(20);
			}
		}

		Tp.oneScRoomLamp.getBlock().setType(Material.OBSIDIAN);
		Tp.twoScRoomLamp.getBlock().setType(Material.OBSIDIAN);
		Tp.casterOneScRoomLamp.getBlock().setType(Material.OBSIDIAN);
		Tp.casterTwoScRoomLamp.getBlock().setType(Material.OBSIDIAN);

		if (team.equals("one")) {
			if (Vars.oneScore == 0 || Vars.oneScore == 1) {
				API.placeStructure("score_" + Vars.oneScore + "_one", Tp.oneScoreNW);
				API.placeStructure("score_" + Vars.oneScore + "_one", Tp.oneScoreSW);
				API.placeStructure("score_" + Vars.oneScore + "_one", Tp.oneScoreNE);
				API.placeStructure("score_" + Vars.oneScore + "_one", Tp.oneScoreSE);
			}
			if (Vars.oneScore == 2 || Vars.oneScore == 3) {
				API.placeStructure("score_" + Vars.oneScore + "_one_N", Tp.oneScoreNW);
				API.placeStructure("score_" + Vars.oneScore + "_one_S", Tp.oneScoreSW);
				API.placeStructure("score_" + Vars.oneScore + "_one_N", Tp.oneScoreNE);
				API.placeStructure("score_" + Vars.oneScore + "_one_S", Tp.oneScoreSE);
			}
		}
		if (team.equals("two")) {
			if (Vars.twoScore == 0 || Vars.twoScore == 1) {
				API.placeStructure("score_" + Vars.twoScore + "_two", Tp.twoScoreNW);
				API.placeStructure("score_" + Vars.twoScore + "_two", Tp.twoScoreSW);
				API.placeStructure("score_" + Vars.twoScore + "_two", Tp.twoScoreNE);
				API.placeStructure("score_" + Vars.twoScore + "_two", Tp.twoScoreSE);
			}
			if (Vars.twoScore == 2 || Vars.twoScore == 3) {
				API.placeStructure("score_" + Vars.twoScore + "_two_N", Tp.twoScoreNW);
				API.placeStructure("score_" + Vars.twoScore + "_two_S", Tp.twoScoreSW);
				API.placeStructure("score_" + Vars.twoScore + "_two_N", Tp.twoScoreNE);
				API.placeStructure("score_" + Vars.twoScore + "_two_S", Tp.twoScoreSE);
			}
		}

		announceScore = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			int n = 0;

			public void run() {

				switch (n) {

				case 0:
					for (Player p : Bukkit.getOnlinePlayers()) {
						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						if (team.equals("one")) {
							subTitle = ChatSerializer.a("{\"text\":\""
									+ ChatColor.translateAlternateColorCodes('&', "&c§oRED §f§oTeam wins this round !")
									+ "\"}");
						}
						if (team.equals("two")) {
							subTitle = ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
									"&a§oGREEN §f§oTeam wins this round !") + "\"}");
						}
						if (team.equals("draw")) {
							subTitle = ChatSerializer.a(
									"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&d§oDRAW !") + "\"}");
						}
						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);
					}
					break;
					
				case 3:
					for (Player p : Bukkit.getOnlinePlayers()) {
						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						if (team.equals("one")) {
							subTitle = ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
									"&c§o" + Vars.oneScore + " §f§l- §a§o" + Vars.twoScore) + "\"}");
						}
						if (team.equals("two")) {
							subTitle = ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
									"&a§o" + Vars.twoScore + " §f§l- §c§o" + Vars.oneScore) + "\"}");
						}
						if (Vars.whoBegin.equals("OaTd")) {
							subTitle = ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
									"&c§o" + Vars.oneScore + " §f§l- §a§o" + Vars.twoScore) + "\"}");
						}
						if (Vars.whoBegin.equals("OdTa")) {
							subTitle = ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
									"&a§o" + Vars.twoScore + " §f§l- §c§o" + Vars.oneScore) + "\"}");
						}
						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);
					}
					break;
					
				case 5:
					if (Vars.oneScore < 3 && Vars.twoScore < 3) {
						new ScoreRoom().tpScoreRoom();
						if (Countdown.cdFightBar.isVisible()) {
							Countdown.cdFightBar.removeAll();
						}
					} 
					else if (Vars.oneScore >= 3) {
						endGame("one");
					}
					else if (Vars.twoScore >= 3) {
						endGame("two");
					}
					break;
					
				case 7:
					Tp.oneScRoomLamp.getBlock().setType(Material.SEA_LANTERN);
					Tp.twoScRoomLamp.getBlock().setType(Material.SEA_LANTERN);
					Tp.casterOneScRoomLamp.getBlock().setType(Material.SEA_LANTERN);
					Tp.casterTwoScRoomLamp.getBlock().setType(Material.SEA_LANTERN);
					break;
					
				case 8:
					Main.getInstance().getServer().getScheduler().cancelTask(announceScore);
					new ScoreRoom().holoScore();					
					break;
				}
				n++;
			}
		}, 20, 20);
	}

	int n = 1;
	int s = 1;

	public static int wings, crown, victory;
	private Location origin;
	public static Particle crownParticle, wingsParticle, victoryParticle;
	public static int crownPoints;
	private Player pWin1, pWin2, pWin3, pLose1, pLose2, pLose3;
	private IChatBaseComponent subTitle;
	private ArrayList<String> pCrown = new ArrayList<String>();

	public static ArrayList<Double> line1 = new ArrayList<Double>(
			Arrays.asList(-1.1, -0.9, -0.7, -0.5, -0.3, 1.3, 1.5, 1.7, 1.9, 2.1));
	public static ArrayList<Double> line2 = new ArrayList<Double>(
			Arrays.asList(-0.2, 0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2));
	public static ArrayList<Double> line3 = new ArrayList<Double>(Arrays.asList(-1.9, -1.7, -1.5, -1.3, -1.1, -0.9,
			-0.7, -0.5, -0.3, -0.1, 0.1, 0.3, 0.5, 0.7, 0.9, 1.1, 1.3, 1.5, 1.7, 1.9, 2.1, 2.3, 2.5, 2.7, 2.9));
	public static ArrayList<Double> line4 = new ArrayList<Double>(
			Arrays.asList(-2.4, -2.2, -2.0, -1.8, -1.6, -1.4, -1.2, -1.0, -0.8, -0.6, -0.4, -0.2, 0.0, 0.2, 0.4, 0.6,
					0.8, 1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0, 3.2, 3.4));
	public static ArrayList<Double> line5 = new ArrayList<Double>(
			Arrays.asList(-0.9, -0.7, -0.5, -0.3, -0.1, 0.1, 0.3, 0.5, 0.7, 0.9, 1.1, 1.3, 1.5, 1.7, 1.9));
	public static ArrayList<Double> line6 = new ArrayList<Double>(
			Arrays.asList(-3.2, -3.0, -2.8, -2.6, -2.4, -2.2, -2.0, -1.8, -1.6, -1.4, -1.2, -1.0, -0.8, -0.6, -0.4,
					-0.2, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0, 3.2, 3.4, 3.6, 3.8, 4.0, 4.2));
	public static ArrayList<Double> line7 = new ArrayList<Double>(Arrays.asList(-3.5, -3.3, -3.1, -1.5, -1.3, -1.1,
			-0.9, -0.7, -0.5, -0.3, 1.3, 1.5, 1.7, 1.9, 2.1, 2.3, 2.5, 4.1, 4.3, 4.5));
	public static ArrayList<Double> line8 = new ArrayList<Double>(Arrays.asList(-2.6, -2.4, -2.2, -2.0, -1.8, -1.6,
			-1.4, -1.2, -1.0, -0.8, -0.6, -0.4, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0, 3.2, 3.4, 3.6));
	public static ArrayList<Double> line9 = new ArrayList<Double>(
			Arrays.asList(-3.5, -3.3, -3.1, -2.9, -2.7, -2.5, -2.3, -2.1, -1.3, -1.1, -0.9, -0.7, -0.5, -0.3, 1.3, 1.5,
					1.7, 1.9, 2.1, 2.3, 3.1, 3.3, 3.5, 3.7, 3.9, 4.1, 4.3, 4.5));
	public static ArrayList<Double> line10 = new ArrayList<Double>(
			Arrays.asList(-4.0, -3.8, -3.6, -3.4, -3.2, -3.0, -2.8, -1.8, -1.6, -1.4, -1.2, -1.0, -0.8, -0.6, -0.4, 1.2,
					1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 3.6, 3.8, 4.0, 4.2, 4.4, 4.6, 4.8));
	public static ArrayList<Double> line11 = new ArrayList<Double>(
			Arrays.asList(-4.1, -3.9, -3.7, -3.5, -3.3, -2.3, -2.1, -1.9, -1.7, -1.5, -1.3, -1.1, -0.9, -0.7, -0.5,
					-0.3, 1.1, 1.3, 1.5, 1.7, 1.9, 2.1, 2.3, 2.5, 2.7, 2.9, 3.1, 4.1, 4.3, 4.5, 4.7, 4.9));
	public static ArrayList<Double> line12 = new ArrayList<Double>(Arrays.asList(-4.2, -4.0, -3.0, -2.8, -2.6, -2.4,
			-2.2, -2.0, -1.4, -1.2, -1.0, -0.8, -0.6, 1.4, 1.6, 1.8, 2.0, 2.2, 2.8, 3.0, 3.2, 3.4, 3.6, 3.8, 4.8, 5.0));
	public static ArrayList<Double> line13 = new ArrayList<Double>(Arrays.asList(-3.3, -3.1, -2.9, -2.7, -2.5, -1.7,
			-1.5, -1.3, -1.1, -0.9, 1.7, 1.9, 2.1, 2.3, 2.5, 3.3, 3.5, 3.7, 3.9, 4.1));
	public static ArrayList<Double> line14 = new ArrayList<Double>(Arrays.asList(-3.6, -3.4, -3.2, -3.0, -2.8, -2.2,
			-2.0, -1.8, -1.6, -1.4, -1.2, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0, 3.6, 3.8, 4.0, 4.2, 4.4));
	public static ArrayList<Double> line15 = new ArrayList<Double>(Arrays.asList(-3.9, -3.7, -3.5, -3.3, -3.1, -2.9,
			-2.3, -2.1, -1.9, -1.7, -1.5, 2.3, 2.5, 2.7, 2.9, 3.1, 3.3, 3.9, 4.1, 4.3, 4.5, 4.7));
	public static ArrayList<Double> line16 = new ArrayList<Double>(Arrays.asList(-4.0, -3.8, -3.6, -3.4, -2.8, -2.6,
			-2.4, -2.2, -2.0, -1.8, 2.6, 2.8, 3.0, 3.2, 3.4, 3.6, 4.2, 4.4, 4.6, 4.8));
	public static ArrayList<Double> line17 = new ArrayList<Double>(Arrays.asList(-4.1, -3.9, -3.7, -3.1, -2.9, -2.7,
			-2.5, -2.3, -2.1, 2.9, 3.1, 3.3, 3.5, 3.7, 3.9, 4.5, 4.7, 4.9));
	public static ArrayList<Double> line18 = new ArrayList<Double>(
			Arrays.asList(-4.2, -4.0, -3.2, -3.0, -2.8, -2.6, -2.4, 3.2, 3.4, 3.6, 3.8, 4.0, 4.8, 5.0));
	public static ArrayList<Double> line19 = new ArrayList<Double>(
			Arrays.asList(-4.3, -3.5, -3.3, -3.1, -2.9, -2.7, -2.5, 3.3, 3.5, 3.7, 3.9, 4.1, 4.3, 5.1));
	public static ArrayList<Double> line20 = new ArrayList<Double>(
			Arrays.asList(-3.6, -3.4, -3.2, -3.0, -2.8, 3.6, 3.8, 4.0, 4.2, 4.4));
	public static ArrayList<Double> line21 = new ArrayList<Double>(
			Arrays.asList(-3.7, -3.5, -3.3, -3.1, -2.9, 3.7, 3.9, 4.1, 4.3, 4.5));
	public static ArrayList<Double> line22 = new ArrayList<Double>(
			Arrays.asList(-3.8, -3.6, -3.4, -3.2, -3.0, 3.8, 4.0, 4.2, 4.4, 4.6));
	public static ArrayList<Double> line23 = new ArrayList<Double>(
			Arrays.asList(-3.9, -3.7, -3.5, -3.3, 4.1, 4.3, 4.5, 4.7));
	public static ArrayList<Double> line24 = new ArrayList<Double>(
			Arrays.asList(-4.0, -3.8, -3.6, -3.4, 4.2, 4.4, 4.6, 4.8));
	public static ArrayList<Double> line25 = new ArrayList<Double>(Arrays.asList(-3.9, -3.7, -3.5, 4.3, 4.5, 4.7));
	public static ArrayList<Double> line26 = new ArrayList<Double>(Arrays.asList(-3.8, -3.6, 4.4, 4.6));
	public static ArrayList<Double> line27 = new ArrayList<Double>(Arrays.asList(-3.7, 4.5));

	public static ArrayList<Double> vic22 = new ArrayList<Double>(
			Arrays.asList(-1.7, -0.9, -0.5, -0.3, -0.1, 0.5, 1.1, 1.3, 1.5, 1.9, 2.3, 2.9));
	public static ArrayList<Double> vic23 = new ArrayList<Double>(
			Arrays.asList(-1.8, -1.6, -0.9, -0.5, 0.5, 1.1, 1.5, 1.9, 2.1, 2.9));
	public static ArrayList<Double> vic24 = new ArrayList<Double>(
			Arrays.asList(-1.9, -1.5, -0.9, -0.5, 0.5, 1.1, 1.5, 1.9, 2.3, 2.8, 3.0));
	public static ArrayList<Double> vic25 = new ArrayList<Double>(
			Arrays.asList(-2.0, -1.4, -0.9, -0.5, -0.3, -0.1, 0.3, 0.5, 0.7, 1.1, 1.3, 1.5, 1.9, 2.1, 2.3, 2.7, 3.1));
	public static ArrayList<Double> vic26 = new ArrayList<Double>(Arrays.asList(-2.1, -1.3));

	public void endGame(String team) {

		Main.getInstance().getServer().getScheduler().cancelTask(announceScore);

		if (Countdown.cdFightBar.isVisible()) {
			Countdown.cdFightBar.removeAll();
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			IChatBaseComponent Title = ChatSerializer
					.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "§f§l" + "VICTORY §6§l!") + "\"}");
			if (team.equalsIgnoreCase("one")) {
				subTitle = ChatSerializer.a("{\"text\":\""
						+ ChatColor.translateAlternateColorCodes('&', "&c§oRED §f§lTeam wins the game §6§l!") + "\"}");
			} else if (team.equalsIgnoreCase("two")) {
				subTitle = ChatSerializer.a("{\"text\":\""
						+ ChatColor.translateAlternateColorCodes('&', "&a§oGREEN §f§lTeam wins the game §6§l!")
						+ "\"}");
			}
			PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
			PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
			connection.sendPacket(title);
			connection.sendPacket(subtitle);

			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				if (n == 1) {
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.podiumSlot1);
						pWin1 = p;
					}
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.loserSlot1);
						pLose1 = p;
					}
					n++;
				} else if (n == 2) {
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.podiumSlot2);
						pWin2 = p;
					}
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.loserSlot2);
						pLose2 = p;
					}
					n++;
				} else if (n == 3) {
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.podiumSlot3);
						pWin3 = p;
					}
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.loserSlot3);
						pLose3 = p;
					}
					n = 1;
				}
			}
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				if (n == 1) {
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.podiumSlot1);
						pWin1 = p;
					}
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.loserSlot1);
						pLose1 = p;
					}
					n++;
				} else if (n == 2) {
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.podiumSlot2);
						pWin2 = p;
					}
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.loserSlot2);
						pLose2 = p;
					}
					n++;
				} else if (n == 3) {
					if (team.equalsIgnoreCase("two")) {
						p.teleport(Tp.podiumSlot3);
						pWin3 = p;
					}
					if (team.equalsIgnoreCase("one")) {
						p.teleport(Tp.loserSlot3);
						pLose3 = p;
					}
					n = 1;
				}
			}
			if (Vars.pNameCaster.contains(p.getName())) {
				p.teleport(Tp.casterSlot);
			}
			if (Vars.pNameSpec.contains(p.getName())) {
				if (s == 1) {
					p.teleport(Tp.specSlot1);
					s = 2;
				}
				if (s == 2) {
					p.teleport(Tp.specSlot2);
					s = 1;
				}
			}
		}

		if (team.equalsIgnoreCase("one")) {
			for (String pNO : Vars.pNameTeamOne.keySet()) {
				pCrown.add(pNO);
			}
			createVictoryHolo("one");
		}
		if (team.equalsIgnoreCase("two")) {
			for (String pNT : Vars.pNameTeamTwo.keySet()) {
				pCrown.add(pNT);
			}
			createVictoryHolo("two");
		}
		crown();
		victory();
		wings();
		victoryStats(team);
		new MvpChoosen().checkVotes();

	}

	public void transferStatsToDatabase(String team) {

		// UPDATE PLAYERS STATS ON DATABASE
		for (String pNO : Vars.pNameTeamOne.keySet()) {
			Player pO = Bukkit.getPlayerExact(pNO);

			ps.updateTotalGame(pO);
			ps.updateTotalGameNephi(pO);
			if (team.equalsIgnoreCase("one")) {
				ps.updateTotalGameWin(pO);
				ps.updateTotalGameWinNephi(pO);
			} else {
				ps.updateTotalGameLost(pO);
				ps.updateTotalGameLostNephi(pO);
			}
			ps.updateTotalRound(pO);
			ps.updateTotalRoundNephi(pO);
			ps.updateTotalRoundLost(pO);
			ps.updateTotalRoundLostNephi(pO);
			ps.updateTotalPerfect(pO, Vars.totalOnePerfect);
			ps.updateTotalPerfDef(pO, Vars.totalOnePerfDef);
			ps.updateTotalBlitz(pO, Vars.totalOneBlitz);
			ps.updateAvgDealtDmg(pO);
			ps.updateAvgTakenDmg(pO);
			ps.updateAvgSwordSwing(pO);
			ps.updateAvgSwordHits(pO);
			ps.updateAvgAxeSwing(pO);
			ps.updateAvgAxeHits(pO);
			ps.updateAvgArrowShots(pO);
			ps.updateAvgArrowHits(pO);
			ps.updateAvgHealPotUsed(pO);
			ps.updateAvgHealMates(pO);
			ps.updateAvgTimeNoDeath(pO);
			ps.updateAvgSneakTime(pO);
			new PlayerStatsManager().savePlayerStats(pO);
		}
		for (String pNT : Vars.pNameTeamOne.keySet()) {
			Player pT = Bukkit.getPlayerExact(pNT);

			ps.updateTotalGame(pT);
			ps.updateTotalGameSeraph(pT);
			if (team.equalsIgnoreCase("two")) {
				ps.updateTotalGameWin(pT);
				ps.updateTotalGameWinSeraph(pT);
			} else {
				ps.updateTotalGameLost(pT);
				ps.updateTotalGameLostSeraph(pT);
			}
			ps.updateTotalRound(pT);
			ps.updateTotalRoundSeraph(pT);
			ps.updateTotalRoundLost(pT);
			ps.updateTotalRoundLostSeraph(pT);
			ps.updateTotalPerfect(pT, Vars.totalTwoPerfect);
			ps.updateTotalPerfDef(pT, Vars.totalTwoPerfDef);
			ps.updateTotalBlitz(pT, Vars.totalTwoBlitz);
			ps.updateAvgDealtDmg(pT);
			ps.updateAvgTakenDmg(pT);
			ps.updateAvgSwordSwing(pT);
			ps.updateAvgSwordHits(pT);
			ps.updateAvgAxeSwing(pT);
			ps.updateAvgAxeHits(pT);
			ps.updateAvgArrowShots(pT);
			ps.updateAvgArrowHits(pT);
			ps.updateAvgHealPotUsed(pT);
			ps.updateAvgHealMates(pT);
			ps.updateAvgTimeNoDeath(pT);
			ps.updateAvgSneakTime(pT);
			new PlayerStatsManager().savePlayerStats(pT);
		}
		Bukkit.broadcastMessage("§0[§4SINE§0] §7§oPlayers stats are transfered to database !");
	}

	public void crown() {

		crownParticle = Particle.TOTEM;
		crownPoints = 24;

		crown = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {

				for (String pN : pCrown) {
					Player p = Bukkit.getPlayerExact(pN);
					origin = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2,
							p.getLocation().getZ());

					double radius = 0.352d;

					for (int i = 0; i < crownPoints; i++) {
						double angle = 2 * Math.PI * i / crownPoints;
						Location point = origin.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
						p.getWorld().spawnParticle(crownParticle, point, 6, 0, 0, 0, 0);
					}
				}
			}
		}, 1, 1);
	}

	public void victory() {

		victoryParticle = Particle.REDSTONE;

		victory = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				for (double x = -5.2; x < 5.2; x += 0.1) {

					DecimalFormat df = new DecimalFormat("#.#");
					String s = df.format(x);
					s = s.replaceAll(",", ".");
					Double i = new Double(s);

					if (i == 0)
						i = 0.0;

					if (vic22.contains(i)) {
						Location v22 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(victoryParticle,
								v22, 6, 0, 0, 0, 0);
					}
					if (vic23.contains(i)) {
						Location v23 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(victoryParticle,
								v23, 6, 0, 0, 0, 0);
					}
					if (vic24.contains(i)) {
						Location v24 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(victoryParticle,
								v24, 6, 0, 0, 0, 0);
					}
					if (vic25.contains(i)) {
						Location v25 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(victoryParticle,
								v25, 6, 0, 0, 0, 0);
					}
					if (vic26.contains(i)) {
						Location v26 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(victoryParticle,
								v26, 6, 0, 0, 0, 0);
					}
				}
			}
		}, 1, 1);
	}

	public void wings() {

		wingsParticle = Particle.FLAME;

		wings = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				for (double x = -5.2; x < 5.2; x += 0.1) {

					DecimalFormat df = new DecimalFormat("#.#");
					String s = df.format(x);
					s = s.replaceAll(",", ".");
					Double i = new Double(s);

					if (line1.contains(i)) {
						Location l1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								131.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l1,
								6, 0, 0, 0, 0);
					}
					if (line2.contains(i)) {
						Location l2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								131.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l2,
								6, 0, 0, 0, 0);
					}
					if (line3.contains(i)) {
						Location l3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l3,
								6, 0, 0, 0, 0);
					}
					if (line4.contains(i)) {
						Location l4 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l4,
								6, 0, 0, 0, 0);
					}
					if (line5.contains(i)) {
						Location l5 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l5,
								6, 0, 0, 0, 0);
					}
					if (line6.contains(i)) {
						Location l6 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l6,
								6, 0, 0, 0, 0);
					}
					if (line7.contains(i)) {
						Location l7 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l7,
								6, 0, 0, 0, 0);
					}
					if (line8.contains(i)) {
						Location l8 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l8,
								6, 0, 0, 0, 0);
					}
					if (line9.contains(i)) {
						Location l9 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l9,
								6, 0, 0, 0, 0);
					}
					if (line10.contains(i)) {
						Location l10 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l10,
								6, 0, 0, 0, 0);
					}
					if (line11.contains(i)) {
						Location l11 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l11,
								6, 0, 0, 0, 0);
					}
					if (line12.contains(i)) {
						Location l12 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l12,
								6, 0, 0, 0, 0);
					}
					if (line13.contains(i)) {
						Location l13 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l13,
								6, 0, 0, 0, 0);
					}
					if (line14.contains(i)) {
						Location l14 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l14,
								6, 0, 0, 0, 0);
					}
					if (line15.contains(i)) {
						Location l15 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l15,
								6, 0, 0, 0, 0);
					}
					if (line16.contains(i)) {
						Location l16 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l16,
								6, 0, 0, 0, 0);
					}
					if (line17.contains(i)) {
						Location l17 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l17,
								6, 0, 0, 0, 0);
					}
					if (line18.contains(i)) {
						Location l18 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l18,
								6, 0, 0, 0, 0);
					}
					if (line19.contains(i)) {
						Location l19 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l19,
								6, 0, 0, 0, 0);
					}
					if (line20.contains(i)) {
						Location l20 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l20,
								6, 0, 0, 0, 0);
					}
					if (line21.contains(i)) {
						Location l21 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l21,
								6, 0, 0, 0, 0);
					}
					if (line22.contains(i)) {
						Location l22 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l22,
								6, 0, 0, 0, 0);
					}
					if (line23.contains(i)) {
						Location l23 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l23,
								6, 0, 0, 0, 0);
					}
					if (line24.contains(i)) {
						Location l24 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l24,
								6, 0, 0, 0, 0);
					}
					if (line25.contains(i)) {
						Location l25 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l25,
								6, 0, 0, 0, 0);
					}
					if (line26.contains(i)) {
						Location l26 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l26,
								6, 0, 0, 0, 0);
					}
					if (line27.contains(i)) {
						Location l27 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								138, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).spawnParticle(wingsParticle, l27,
								6, 0, 0, 0, 0);
					}
				}
			}
		}, 15, 15);
	}

	public void createVictoryHolo(String team) {

		ArmorStand as = (ArmorStand) Tp.teamWin.getWorld().spawnEntity(Tp.teamWin, EntityType.ARMOR_STAND);
		ArmorStand asp1 = (ArmorStand) Tp.pWin1.getWorld().spawnEntity(Tp.pWin1, EntityType.ARMOR_STAND);
		ArmorStand asp2 = (ArmorStand) Tp.pWin2.getWorld().spawnEntity(Tp.pWin2, EntityType.ARMOR_STAND);
		ArmorStand asp3 = (ArmorStand) Tp.pWin3.getWorld().spawnEntity(Tp.pWin3, EntityType.ARMOR_STAND);

		if (team.equalsIgnoreCase("one")) {
			as.setCustomName("§c§lRED §f§lTeam wins §6§l!");
		}
		if (team.equalsIgnoreCase("two")) {
			as.setCustomName("§a§lGREEN §f§lTeam wins §6§l!");
		}

		as.setCustomNameVisible(true);
		as.setVisible(false);
		as.setGravity(false);

		asp1.setCustomName(pWin1.getName());
		asp1.setCustomNameVisible(true);
		asp1.setVisible(false);
		asp1.setGravity(false);

		asp2.setCustomName(pWin2.getName());
		asp2.setCustomNameVisible(true);
		asp2.setVisible(false);
		asp2.setGravity(false);

		asp3.setCustomName(pWin3.getName());
		asp3.setCustomNameVisible(true);
		asp3.setVisible(false);
		asp3.setGravity(false);
	}

	double loc = 0;
	private ArmorStand asSet1, asSet2, asSet3, asSet4, asSet5, asSet6, asSet7, asSet8, asSet9, asSet10, asSet11,
			asSet12, asSet13, asSet14, asSet15, asSet16, asSet17, asSet18, asSet19, asSet20, asSet21, asSet22, asSet23,
			asSet24;
	private ArmorStand asStat1, asStat2, asStat3, asStat4, asStat5, asStat6, asStat7, asStat8, asStat9, asStat10,
			asStat11, asStat12, asStat13, asStat14, asStat15, asStat16, asStat17, asStat18, asStat19, asStat20,
			asStat21, asStat22, asStat23, asStat24;
	String winTeam;
	String one;
	String two;

	public void victoryStats(String team) {

		if (team.equalsIgnoreCase("one"))
			winTeam = "§c§oRED";
		if (team.equalsIgnoreCase("two"))
			winTeam = "§a§oGREEN";
		if (GameStatsFile.recapConfig.getString("options.begin").equalsIgnoreCase("RED attack / GREEN defense")) {
			one = "ATTACK";
			two = "DEFENSE";
		} else if (GameStatsFile.recapConfig.getString("options.begin")
				.equalsIgnoreCase("RED attack / GREEN defense")) {
			one = "DEFENSE";
			two = "ATTACK";
		}

		createVictoryStatsHolo(asSet1, asStat1, loc, "§5\u2B1B §6§lGame Debrief §f: §5\u2B1B",
				"§5\u2B1B §6§lGame Statistics §f: §5\u2B1B");
		loc -= 0.25;
		createVictoryStatsHolo(asSet2, asStat2, loc, "-----                -----", "-----    §5§lRounds §f:    -----");
		loc -= 0.25;
		createVictoryStatsHolo(asSet3, asStat3, loc,
				"§6Game Date §f: §5§o" + GameStatsFile.recapConfig.getString("game.date"),
				"§6Rounds §f: §5§o" + Countdown.round);
		loc -= 0.25;
		createVictoryStatsHolo(asSet4, asStat4, loc, "-----                -----", "§6Draw §f: §5§o" + Vars.totalDraw);
		loc -= 0.25;
		createVictoryStatsHolo(asSet5, asStat5, loc, "§6Victorious Team §f: " + winTeam + " §f§oTeam",
				"§6Rounds won §f: §c§oRED §f= §5§o" + Vars.totalOneWinRd + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoWinRd);
		loc -= 0.25;
		createVictoryStatsHolo(asSet6, asStat6, loc, "§6Victorious Players §f: §5§o" + pWin1.getName(),
				"§6Rounds lost §f: §c§oRED §f = §5§o" + Vars.totalOneLoseRd + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoLoseRd);
		loc -= 0.25;
		createVictoryStatsHolo(asSet7, asStat7, loc, "                     §5§o" + pWin2.getName(),
				"-----     §5§lPvP §f:      -----");
		loc -= 0.25;
		createVictoryStatsHolo(asSet8, asStat8, loc, "                     §5§o" + pWin3.getName(),
				"§6Kills §f: §c§oRED §f= §5§o" + Vars.totalOneKills + " §f& §a§oGREEN §f= §5§o" + Vars.totalTwoKills);
		loc -= 0.25;
		createVictoryStatsHolo(asSet9, asStat9, loc, "§6Defeated Team §f: " + winTeam + " §f§oTeam",
				"§6Deaths §f: §c§oRED §f= §5§o" + Vars.totalOneDeaths + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoDeaths);
		loc -= 0.25;
		createVictoryStatsHolo(asSet10, asStat10, loc, "§6Defeated Players §f: §5§o" + pLose1.getName(),
				"§6Mates Kills §f: §c§oRED §f= §5§o" + Vars.totalOneMates + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoMates);
		loc -= 0.25;
		createVictoryStatsHolo(asSet11, asStat11, loc, "                   §5§o" + pLose2.getName(),
				"-----    §5§lFlags §f:     -----");
		loc -= 0.25;
		createVictoryStatsHolo(asSet12, asStat12, loc, "                   §5§o" + pLose3.getName(),
				"§6Taken §f: §c§oRED §f= §5§o" + Vars.totalOneTaken + " §f& §a§oGREEN §f= §5§o" + Vars.totalTwoTaken);
		loc -= 0.25;
		createVictoryStatsHolo(asSet13, asStat13, loc, "-----                -----", "§6Captured §f: §c§oRED §f = §5§o"
				+ Vars.totalOneCaptur + " §f& §a§oGREEN §f= §5§o" + Vars.totalTwoCaptur);
		loc -= 0.25;
		createVictoryStatsHolo(asSet14, asStat14, loc,
				"§6Game Mode §f: §(§o" + GameStatsFile.recapConfig.getString("options.rank"),
				"§6Dropped §f: §c§oRED §f= §5§o" + Vars.totalOneDrop + " §f& §a§oGREEN §f= §5§o" + Vars.totalTwoDrop);
		loc -= 0.25;
		createVictoryStatsHolo(asSet15, asStat15, loc,
				"§6Time §f: §5§o" + GameStatsFile.recapConfig.getString("options.time") + " §f| §6Weather §f: §5§o"
						+ GameStatsFile.recapConfig.getString("options.weather"),
				"§6Back §f: §c§oRED §f= §5§o" + Vars.totalOneBack + " §f& §a§oGREEN §f= §5§o" + Vars.totalTwoBack);
		loc -= 0.25;
		createVictoryStatsHolo(asSet16, asStat16, loc,
				"§6Preparation Time §f: §5§o" + GameStatsFile.recapConfig.getString("options.prep"),
				"-----  §5§lObjectives §f:  -----");
		loc -= 0.25;
		createVictoryStatsHolo(asSet17, asStat17, loc,
				"§6Defense Time §f: §5§o" + GameStatsFile.recapConfig.getString("options.def"),
				"§6Perfect §f: §c§oRED §f= §5§o" + Vars.totalOnePerfect + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoPerfect);
		loc -= 0.25;
		createVictoryStatsHolo(asSet18, asStat18, loc,
				"§6Fight Time §f: §5§o" + GameStatsFile.recapConfig.getString("options.fight"),
				"§6Perf. Defense §f: §c§oRED §f= §5§o" + Vars.totalOnePerfDef + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoPerfDef);
		loc -= 0.25;
		createVictoryStatsHolo(asSet19, asStat19, loc, "§6Begin §f: §c§oRED §f= §5§o" + one,
				"§6Blitzkrieg §f: §c§oRED §f= §5§o" + Vars.totalOneBlitz + " §f& §a§oGREEN §f= §5§o"
						+ Vars.totalTwoBlitz);
		loc -= 0.25;
		createVictoryStatsHolo(asSet20, asStat20, loc, "§6        §a§oGREEN §f= §5§o" + two,
				"-----    §5§lGolds §f:     -----");
		loc -= 0.25;
		createVictoryStatsHolo(asSet21, asStat21, loc,
				"§6Golds Multiplicator §f: §5§o" + GameStatsFile.recapConfig.getString("options.golds"),
				"§6Total Golds Won §f: §c§oRED §f= §5§o" + Vars.totalOneWinGolds);
		loc -= 0.25;
		createVictoryStatsHolo(asSet22, asStat22, loc,
				"§6Map §f: §5§o" + GameStatsFile.recapConfig.getString("options.map") + " §f& §6Size §f: §5§o"
						+ GameStatsFile.recapConfig.getString("options.size"),
				"                  §a§oGREEN §f= §5§o" + Vars.totalTwoWinGolds);
		loc -= 0.25;
		createVictoryStatsHolo(asSet23, asStat23, loc,
				"§6Friendly Fire §f: §5§o" + GameStatsFile.recapConfig.getString("options.ff"),
				"§6Total End Golds §f: §c§oRED §f= §5§o" + Vars.oneGoldsTotal);
		loc -= 0.25;
		createVictoryStatsHolo(asSet24, asStat24, loc,
				"§6Invisible Mates §f: §5§o" + GameStatsFile.recapConfig.getString("options.invis"),
				"                  §a§oGREEN §f= §5§o" + Vars.twoGoldsTotal);

	}

	public void createVictoryStatsHolo(ArmorStand asSettings, ArmorStand asStats, double loc, String nameSettings,
			String nameStats) {

		Location settingsGame = new Location(Tp.settingsGame.getWorld(), Tp.settingsGame.getX(),
				Tp.settingsGame.getY() + loc, Tp.settingsGame.getZ());
		Location statsGame = new Location(Tp.statsGame.getWorld(), Tp.statsGame.getX(), Tp.statsGame.getY() + loc,
				Tp.statsGame.getZ());

		asSettings = (ArmorStand) settingsGame.getWorld().spawnEntity(settingsGame, EntityType.ARMOR_STAND);
		asStats = (ArmorStand) statsGame.getWorld().spawnEntity(statsGame, EntityType.ARMOR_STAND);

		asSettings.setCustomName(nameSettings);
		asSettings.setCustomNameVisible(true);
		asSettings.setVisible(false);
		asSettings.setGravity(false);

		asStats.setCustomName(nameStats);
		asStats.setCustomNameVisible(true);
		asStats.setVisible(false);
		asStats.setGravity(false);
	}
}
