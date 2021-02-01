package fr.phoenix.sineplugin.playerProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.SqlConnection;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.phases.Countdown;

public class PlayerStats {

	private int id;
	private String uuid, name, date, type, team;
	private int access, blacklist;

	private int voteMVP, totalGame, totalGameNephi, totalGameSeraph, totalGameWin, totalGameWinNephi, totalGameWinSeraph,
			totalGameLost, totalGameLostNephi, totalGameLostSeraph, totalRound, totalRoundNephi, totalRoundSeraph,
			totalRoundWin, totalRoundWinNephi, totalRoundWinSeraph, totalRoundLost, totalRoundLostNephi,
			totalRoundLostSeraph, totalMVP, totalMVPNephi, totalMVPSeraph, totalFlagTaken, totalFlagCapture,
			totalFlagDrop, totalFlagBack, totalPerfect, totalPerfDef, totalBlitz;
	private int totalPlayersKilled, totalDeaths, totalMateKills, maxDealtDmg, totalDealtDmg, avgDealtDmg, maxTakenDmg,
			totalTakenDmg, avgTakenDmg, totalSwordSwing, totalSwordHits, avgSwordSwing, avgSwordHits, totalAxeSwing,
			totalAxeHits, avgAxeSwing, avgAxeHits, totalArrowShots, totalArrowHits, avgArrowShots, avgArrowHits;
	private int totalHealPotUsed, avgHealPotUsed, totalHealMates, avgHealMates, maxTimeNoDeath, totalTimeNoDeath,
			avgTimeNoDeath, maxSneakTime, totalSneakTime, avgSneakTime;

	static Date utilDate = new Date();
	static String DATE_FORMAT = "dd/MM/yyyy - HH-mm-ss";
	static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	public static String time = sdf.format(utilDate);

	public void createProfileSeason(Player p) {

		if (!hasProfile(p)) {
			p.sendMessage("§0[§4SINE§0] §7§oNo profile found !");
			p.sendMessage("§0[§4SINE§0] §7§oYour profile §4" + p.getName() + " §7§owas created on database !");

			try {
				PreparedStatement q = SqlConnection.connection.prepareStatement(
						"INSERT INTO PlayerStatsSeason (uuid, name, date, team, access, blacklist, totalGame, totalGameNephi, totalGameSeraph, totalGameWin, totalGameWinNephi, totalGameWinSeraph, totalGameLost, totalGameLostNephi, totalGameLostSeraph, totalRound, totalRoundNephi, totalRoundSeraph, totalRoundWin, totalRoundWinNephi, totalRoundWinSeraph, totalRoundLost, totalRoundLostNephi, totalRoundLostSeraph, totalMVP, totalMVPNephi, totalMVPSeraph, totalFlagTaken, totalFlagCapture, totalFlagDrop, totalFlagBack, totalPlayersKilled, totalDeaths, totalMateKills, totalPerfect, totalPerfDef, totalBlitz, maxDealtDmg, totalDealtDmg, avgDealtDmg, maxTakenDmg, totalTakenDmg, avgTakenDmg, totalSwordSwing, totalSwordHits, avgSwordSwing, avgSwordHits, totalAxeSwing, totalAxeHits, avgAxeSwing, avgAxeHits, totalArrowShots, totalArrowHits, avgArrowShots, avgArrowHits, totalHealPotUsed, avgHealPotUsed, totalHealMates, avgHealMates, maxTimeNoDeath, totalTimeNoDeath, avgTimeNoDeath, maxSneakTime, totalSneakTime, avgSneakTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				q.setString(1, p.getUniqueId().toString());
				q.setString(2, p.getName());
				q.setString(3, time);
				q.setString(4, "");
				q.setInt(5, 10);
				q.setInt(6, 40);
				for (int i = 7; i < 66; i++) {
					q.setInt(i, 0);
				}
				q.execute();
				q.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			new PlayerStatsManager().loadPlayerStatsSeason(p);
		}
	}

	public void createProfileLastGame(Player p) {

		try {
			PreparedStatement q = SqlConnection.connection.prepareStatement(
					"INSERT INTO PlayerStatsLastGame (uuid, name, date, team, voteMVP, totalGame, totalGameNephi, totalGameSeraph, totalGameWin, totalGameWinNephi, totalGameWinSeraph, totalGameLost, totalGameLostNephi, totalGameLostSeraph, totalRound, totalRoundNephi, totalRoundSeraph, totalRoundWin, totalRoundWinNephi, totalRoundWinSeraph, totalRoundLost, totalRoundLostNephi, totalRoundLostSeraph, totalMVP, totalMVPNephi, totalMVPSeraph, totalFlagTaken, totalFlagCapture, totalFlagDrop, totalFlagBack, totalPlayersKilled, totalDeaths, totalMateKills, totalPerfect, totalPerfDef, totalBlitz, maxDealtDmg, totalDealtDmg, avgDealtDmg, maxTakenDmg, totalTakenDmg, avgTakenDmg, totalSwordSwing, totalSwordHits, avgSwordSwing, avgSwordHits, totalAxeSwing, totalAxeHits, avgAxeSwing, avgAxeHits, totalArrowShots, totalArrowHits, avgArrowShots, avgArrowHits, totalHealPotUsed, avgHealPotUsed, totalHealMates, avgHealMates, maxTimeNoDeath, totalTimeNoDeath, avgTimeNoDeath, maxSneakTime, totalSneakTime, avgSneakTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			q.setString(1, p.getUniqueId().toString());
			q.setString(2, p.getName());
			q.setString(3, time);
			q.setString(4, "");
			for (int i = 5; i < 65; i++) {
				q.setInt(i, 0);
			}
			q.execute();
			q.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		new PlayerStatsManager().loadPlayerStatsLastGame(p);
	}

	public boolean hasProfile(Player p) {

		try {
			PreparedStatement q = SqlConnection.connection
					.prepareStatement("SELECT uuid FROM PlayerStatsSeason WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());
			ResultSet searchUUID = q.executeQuery();
			boolean hasProfile = searchUUID.next();

			if (hasProfile == true) {
				if (hasSameName(p).equals(p.getName())) {
					p.sendMessage("§0[§4SINE§0] §7§oYour profile §4" + p.getName() + " §7§owas found on database !");
				} else {
					updateName(p);
					p.sendMessage("§0[§4SINE§0] §7§oYour profile was found on database but name is different...");
					p.sendMessage("§0[§4SINE§0] §7§oYour profile name was updated to : §4" + p.getName() + " §7§o!");
				}
				new PlayerStatsManager().loadPlayerStatsSeason(p);

			}			
			q.close();
			return hasProfile;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String hasSameName(Player p) {

		String pName = p.getName();

		try {
			PreparedStatement sn = SqlConnection.connection
					.prepareStatement("SELECT name FROM PlayerStatsSeason WHERE uuid = ?");
			sn.setString(1, p.getUniqueId().toString());

			ResultSet rssn = sn.executeQuery();

			while (rssn.next()) {
				pName = rssn.getString("name");
			}
			sn.close();
			return pName;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateName(Player p) {

		try {
			PreparedStatement un = SqlConnection.connection
					.prepareStatement("UPDATE PlayerStatsSeason SET name = ? WHERE uuid = ?");
			un.setString(1, p.getName());
			un.setString(2, p.getUniqueId().toString());
			un.executeUpdate();
			un.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GAME PLAYED
	public void updateTotalGame(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGame = dataPS.getTotalGame();
		totalGame++;
		dataPS.setTotalGame(totalGame);
	}

	// TOTAL GAME NEPHI
	public void updateTotalGameNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameNephi = dataPS.getTotalGameNephi();
		totalGameNephi++;
		dataPS.setTotalGameNephi(totalGameNephi);
	}

	// TOTAL GAME SERAPH
	public void updateTotalGameSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameSeraph = dataPS.getTotalGameSeraph();
		totalGameSeraph++;
		dataPS.setTotalGameSeraph(totalGameSeraph);
	}

	// TOTAL GAME WIN
	public void updateTotalGameWin(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameWin = dataPS.getTotalGameWin();
		totalGameWin++;
		dataPS.setTotalGameWin(totalGameWin);
	}

	// TOTAL GAME WIN NEPHI
	public void updateTotalGameWinNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameWinNephi = dataPS.getTotalGameWinNephi();
		totalGameWinNephi++;
		dataPS.setTotalGameWinNephi(totalGameWinNephi);
	}

	// TOTAL GAME WIN SERAPH
	public void updateTotalGameWinSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameWinSeraph = dataPS.getTotalGameWinSeraph();
		totalGameWinSeraph++;
		dataPS.setTotalGameWinSeraph(totalGameWinSeraph);
	}

	// TOTAL GAME LOST
	public void updateTotalGameLost(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameLost = dataPS.getTotalGameLost();
		totalGameLost++;
		dataPS.setTotalGameLost(totalGameLost);
	}

	// TOTAL GAME LOST NEPHI
	public void updateTotalGameLostNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameLostNephi = dataPS.getTotalGameLostNephi();
		totalGameLostNephi++;
		dataPS.setTotalGameLostNephi(totalGameLostNephi);
	}

	// TOTAL GAME LOST SERAPH
	public void updateTotalGameLostSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalGameLostSeraph = dataPS.getTotalGameLostSeraph();
		totalGameLostSeraph++;
		dataPS.setTotalGameLostSeraph(totalGameLostSeraph);
	}

	// TOTAL ROUND
	public void updateTotalRound(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRound = dataPS.getTotalRound();
		totalRound += Countdown.round;
		dataPS.setTotalRound(totalRound);
	}

	// TOTAL ROUND NEPHI
	public void updateTotalRoundNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundNephi = dataPS.getTotalRoundNephi();
		totalRoundNephi++;
		dataPS.setTotalRoundNephi(totalRoundNephi);
	}

	// TOTAL ROUND SERAPH
	public void updateTotalRoundSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundSeraph = dataPS.getTotalRoundSeraph();
		totalRoundSeraph++;
		dataPS.setTotalRoundSeraph(totalRoundSeraph);
	}

	// TOTAL ROUND WIN
	public void updateTotalRoundWin(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		if (Vars.pNameTeamOne.containsKey(p.getName())) {
			int totalRoundWin = dataPS.getTotalRoundWin();
			totalRoundWin += Vars.totalOneWinRd;
			dataPS.setTotalRoundWin(totalRoundWin);
		}
		if (Vars.pNameTeamTwo.containsKey(p.getName())) {
			int totalRoundWin = dataPS.getTotalRoundWin();
			totalRoundWin += Vars.totalTwoWinRd;
			dataPS.setTotalRoundWin(totalRoundWin);
		}
	}

	// TOTAL ROUND WIN NEPHI
	public void updateTotalRoundWinNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundWinNephi = dataPS.getTotalRoundWinNephi();
		totalRoundWinNephi += Vars.totalOneWinRd;
		dataPS.setTotalRoundWinNephi(totalRoundWinNephi);
	}

	// TOTAL ROUND WIN SERAPH
	public void updateTotalRoundWinSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundWinSeraph = dataPS.getTotalRoundWinSeraph();
		totalRoundWinSeraph += Vars.totalTwoWinRd;
		dataPS.setTotalRoundWinSeraph(totalRoundWinSeraph);
	}

	// TOTAL ROUND LOST
	public void updateTotalRoundLost(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		if (Vars.pNameTeamOne.containsKey(p.getName())) {
			int totalRoundLost = dataPS.getTotalRoundLost();
			totalRoundLost += Vars.totalOneLoseRd;
			dataPS.setTotalRoundLost(totalRoundLost);
		}
		if (Vars.pNameTeamTwo.containsKey(p.getName())) {
			int totalRoundLost = dataPS.getTotalRoundLost();
			totalRoundLost += Vars.totalTwoLoseRd;
			dataPS.setTotalRoundLost(totalRoundLost);
		}
	}

	// TOTAL ROUND LOST NEPHI
	public void updateTotalRoundLostNephi(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundLostNephi = dataPS.getTotalRoundLostNephi();
		totalRoundLostNephi += Vars.totalOneLoseRd;
		dataPS.setTotalRoundLostNephi(totalRoundLostNephi);
	}

	// TOTAL ROUND LOST SERAPH
	public void updateTotalRoundLostSeraph(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalRoundLostSeraph = dataPS.getTotalRoundLostSeraph();
		totalRoundLostSeraph += Vars.totalTwoLoseRd;
		dataPS.setTotalRoundLostSeraph(totalRoundLostSeraph);
	}

	// TOTAL MVP
	public void updateTotalMVP(Player p) {

	}

	// TOTAL MVP NEPHI
	public void updateTotalMVPNephi(Player p) {

	}

	// TOTAL MVP SERAPH
	public void updateTotalMVPSeraph(Player p) {

	}

	// TOTAL FLAG TAKEN
	public void updateTotalFlagTaken(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalFlagTaken = dataPS.getTotalFlagTaken();
		totalFlagTaken++;
		dataPS.setTotalFlagTaken(totalFlagTaken);
	}

	// TOTAL FLAG CAPTURE
	public void updateTotalFlagCapture(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalFlagCapture = dataPS.getTotalFlagCapture();
		totalFlagCapture++;
		dataPS.setTotalFlagCapture(totalFlagCapture);
	}

	// TOTAL FLAG DROP
	public void updateTotalFlagDrop(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalFlagDrop = dataPS.getTotalFlagDrop();
		totalFlagDrop++;
		dataPS.setTotalFlagDrop(totalFlagDrop);
	}

	// TOTAL FLAG BACK
	public void updateTotalFlagBack(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalFlagBack = dataPS.getTotalFlagBack();
		totalFlagBack++;
		dataPS.setTotalFlagBack(totalFlagBack);
	}

	// TOTAL PLAYERS KILLED
	public void updateTotalPlayersKilled(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalPlayersKilled = dataPS.getTotalPlayersKilled();
		totalPlayersKilled++;
		dataPS.setTotalPlayersKilled(totalPlayersKilled);
	}

	// TOTAL DEATHS
	public void updateTotalDeaths(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalDeaths = dataPS.getTotalDeaths();
		totalDeaths++;
		dataPS.setTotalDeaths(totalDeaths);
	}

	// TOTAL MATE KILLS
	public void updateTotalMateKills(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalMateKills = dataPS.getTotalMateKills();
		totalMateKills++;
		dataPS.setTotalMateKills(totalMateKills);
	}

	// TOTAL PERFECT
	public void updateTotalPerfect(Player p, int team) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalPerfect = dataPS.getTotalPerfect();
		totalPerfect += team;
		dataPS.setTotalPerfect(totalPerfect);
	}

	// TOTAL PERF DEF
	public void updateTotalPerfDef(Player p, int team) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalPerfDef = dataPS.getTotalPerfDef();
		totalPerfDef += team;
		dataPS.setTotalPerfDef(totalPerfDef);
	}

	// TOTAL BLITZ
	public void updateTotalBlitz(Player p, int team) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalBlitz = dataPS.getTotalBlitz();
		totalBlitz += team;
		dataPS.setTotalBlitz(totalBlitz);
	}

	// MAX DEALT DMG
	public void updateMaxDealtDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int dealtDmgRd = p.getStatistic(Statistic.DAMAGE_DEALT);
		int maxDealtDmg = dataPS.getMaxDealtDmg();
		if (dealtDmgRd > maxDealtDmg) {
			dataPS.setMaxDealtDmg(dealtDmgRd);
		}
	}

	// TOTAL DEALT DMG
	public void updateTotalDealtDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int dealtDmgRd = p.getStatistic(Statistic.DAMAGE_DEALT);
		int totalDealtDmg = dataPS.getTotalDealtDmg();
		totalDealtDmg += dealtDmgRd;
		dataPS.setTotalDealtDmg(totalDealtDmg);

		p.setStatistic(Statistic.DAMAGE_DEALT, 0);
	}

	// AVG DEALT DMG
	public void updateAvgDealtDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalDealtDmg = dataPS.getTotalDealtDmg();
		int totalGame = dataPS.getTotalGame();
		int avgDealtDmg = totalDealtDmg / totalGame;
		dataPS.setAvgDealtDmg(avgDealtDmg);
	}

	// MAX TAKEN DMG
	public void updateMaxTakenDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int takenDmgRd = p.getStatistic(Statistic.DAMAGE_TAKEN);
		int maxTakenDmg = dataPS.getMaxTakenDmg();
		if (takenDmgRd > maxTakenDmg) {
			dataPS.setMaxTakenDmg(takenDmgRd);
		}
	}

	// TOTAL TAKEN DMG
	public void updateTotalTakenDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int takenDmgRd = p.getStatistic(Statistic.DAMAGE_TAKEN);
		int totalTakenDmg = dataPS.getTotalTakenDmg();
		totalTakenDmg += takenDmgRd;
		dataPS.setTotalTakenDmg(totalTakenDmg);

		p.setStatistic(Statistic.DAMAGE_TAKEN, 0);
	}

	// AVG TAKEN DMG
	public void updateAvgTakenDmg(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalTakenDmg = dataPS.getTotalTakenDmg();
		int totalDeaths = dataPS.getTotalDeaths();
		int avgTakenDmg = totalTakenDmg / totalDeaths;
		dataPS.setAvgTakenDmg(avgTakenDmg);
	}

	// TOTAL SWORD SWING
	public void updateTotalSwordSwing(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalSwordSwing = dataPS.getTotalSwordSwing();
		totalSwordSwing++;
		dataPS.setTotalSwordSwing(totalSwordSwing);
	}

	// TOTAL SWORD HITS
	public void updateTotalSwordHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalSwordHits = dataPS.getTotalSwordHits();
		totalSwordHits++;
		dataPS.setTotalSwordHits(totalSwordHits);
	}

	// AVG SWORD SWING
	public void updateAvgSwordSwing(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalSwordSwing = dataPS.getTotalSwordSwing();
		int totalGame = dataPS.getTotalGame();
		int avgSwordSwing = totalSwordSwing / totalGame;
		dataPS.setAvgSwordSwing(avgSwordSwing);
	}

	// AVG SWORD HITS
	public void updateAvgSwordHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalSwordHits = dataPS.getTotalSwordHits();
		int totalSwordSwing = dataPS.getTotalSwordSwing();
		int avgSwordHits = (totalSwordHits / totalSwordSwing) * 100;
		dataPS.setAvgSwordHits(avgSwordHits);
	}

	// TOTAL AXE SWING
	public void updateTotalAxeSwing(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalAxeSwing = dataPS.getTotalAxeSwing();
		totalAxeSwing++;
		dataPS.setTotalAxeSwing(totalAxeSwing);
	}

	// TOTAL AXE HITS
	public void updateTotalAxeHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalAxeHits = dataPS.getTotalAxeHits();
		totalAxeHits++;
		dataPS.setTotalAxeHits(totalAxeHits);
	}

	// AVG AXE SWING
	public void updateAvgAxeSwing(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalAxeSwing = dataPS.getTotalAxeSwing();
		int totalGame = dataPS.getTotalGame();
		int avgAxeSwing = totalAxeSwing / totalGame;
		dataPS.setAvgAxeSwing(avgAxeSwing);
	}

	// AVG AXE HITS
	public void updateAvgAxeHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalAxeHits = dataPS.getTotalAxeHits();
		int totalAxeSwing = dataPS.getTotalAxeSwing();
		int avgAxeHits = (totalAxeHits / totalAxeSwing) * 100;
		dataPS.setAvgAxeHits(avgAxeHits);
	}

	// TOTAL ARROW SHOTS
	public void updateTotalArrowShots(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalArrowShots = dataPS.getTotalArrowShots();
		totalArrowShots++;
		dataPS.setTotalArrowShots(totalArrowShots);
	}

	// TOTAL ARROW HITS
	public void updateTotalArrowHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalArrowHits = dataPS.getTotalArrowHits();
		totalArrowHits++;
		dataPS.setTotalArrowHits(totalArrowHits);
	}

	// AVG ARROW SHOTS
	public void updateAvgArrowShots(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalArrowShots = dataPS.getTotalArrowShots();
		int totalGame = dataPS.getTotalGame();
		int avgArrowShots = totalArrowShots / totalGame;
		dataPS.setAvgArrowShots(avgArrowShots);
	}

	// AVG ARROW HITS
	public void updateAvgArrowHits(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalArrowHits = dataPS.getTotalArrowHits();
		int totalArrowShots = dataPS.getTotalArrowShots();
		int avgArrowHits = (totalArrowHits / totalArrowShots) * 100;
		dataPS.setAvgArrowHits(avgArrowHits);
	}

	// TOTAL HEAL POT USED
	public void updateTotalHealPotUsed(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalHealPotUsed = dataPS.getTotalHealPotUsed();
		totalHealPotUsed++;
		dataPS.setTotalHealPotUsed(totalHealPotUsed);
	}

	// AVG HEAL POT USED
	public void updateAvgHealPotUsed(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalHealPotUsed = dataPS.getTotalHealPotUsed();
		int totalGame = dataPS.getTotalGame();
		int avgHealPotUsed = totalHealPotUsed / totalGame;
		dataPS.setAvgHealPotUsed(avgHealPotUsed);

	}

	// TOTAL HEAL MATES
	public void updateTotalHealMates(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalHealMates = dataPS.getTotalHealMates();
		totalHealMates++;
		dataPS.setTotalHealMates(totalHealMates);

	}

	// AVG HEAL MATES
	public void updateAvgHealMates(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalHealMates = dataPS.getTotalHealMates();
		int totalGame = dataPS.getTotalGame();
		int avgHealMates = totalHealMates / totalGame;
		dataPS.setAvgHealMates(avgHealMates);
	}

	// MAX TIME NO DEATH
	public void updateMaxTimeNoDeath(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int maxTimeNoDeathRd = p.getStatistic(Statistic.TIME_SINCE_DEATH);
		int maxTimeNoDeath = dataPS.getMaxTimeNoDeath();
		if (maxTimeNoDeathRd > maxTimeNoDeath) {
			dataPS.setMaxTimeNoDeath(maxTimeNoDeathRd);
		}
	}

	// TOTAL TIME NO DEATH
	public void updateTotalTimeNoDeath(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int timeNoDeath = p.getStatistic(Statistic.TIME_SINCE_DEATH);
		int totalTimeNoDeath = dataPS.getTotalTimeNoDeath();
		totalTimeNoDeath += timeNoDeath;
		dataPS.setTotalTimeNoDeath(totalTimeNoDeath);

	}

	// AVG TIME NO DEATH
	public void updateAvgTimeNoDeath(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalTimeNoDeath = dataPS.getTotalTimeNoDeath();
		int totalDeaths = dataPS.getTotalDeaths();
		int avgTimeNoDeath = totalTimeNoDeath / totalDeaths;
		dataPS.setAvgTimeNoDeath(avgTimeNoDeath);
	}

	// MAX SNEAK TIME
	public void updateMaxSneakTime(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int sneakTime = p.getStatistic(Statistic.SNEAK_TIME);
		int maxSneakTime = dataPS.getMaxSneakTime();
		if (sneakTime > maxSneakTime) {
			dataPS.setMaxSneakTime(sneakTime);
		}
	}

	// TOTAL SNEAK TIME
	public void updateTotalSneakTime(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int sneakTime = p.getStatistic(Statistic.SNEAK_TIME);
		int totalSneakTime = dataPS.getTotalSneakTime();
		totalSneakTime += sneakTime;
		dataPS.setTotalSneakTime(totalSneakTime);
	}

	// AVG SNEAK TIME
	public void updateAvgSneakTime(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalSneakTime = dataPS.getTotalSneakTime();
		int totalGame = dataPS.getTotalGame();
		int avgSneakTime = totalSneakTime / totalGame;
		dataPS.setAvgSneakTime(avgSneakTime);
	}

	// GETTERS AND SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public int getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}

	public int getVoteMVP() {
		return voteMVP;
	}

	public void setVoteMVP(int voteMVP) {
		this.voteMVP = voteMVP;
	}

	public int getTotalGame() {
		return totalGame;
	}

	public void setTotalGame(int totalGame) {
		this.totalGame = totalGame;
	}

	public int getTotalGameNephi() {
		return totalGameNephi;
	}

	public void setTotalGameNephi(int totalGameNephi) {
		this.totalGameNephi = totalGameNephi;
	}

	public int getTotalGameSeraph() {
		return totalGameSeraph;
	}

	public void setTotalGameSeraph(int totalGameSeraph) {
		this.totalGameSeraph = totalGameSeraph;
	}

	public int getTotalGameWin() {
		return totalGameWin;
	}

	public void setTotalGameWin(int totalGameWin) {
		this.totalGameWin = totalGameWin;
	}

	public int getTotalGameWinNephi() {
		return totalGameWinNephi;
	}

	public void setTotalGameWinNephi(int totalGameWinNephi) {
		this.totalGameWinNephi = totalGameWinNephi;
	}

	public int getTotalGameWinSeraph() {
		return totalGameWinSeraph;
	}

	public void setTotalGameWinSeraph(int totalGameWinSeraph) {
		this.totalGameWinSeraph = totalGameWinSeraph;
	}

	public int getTotalGameLost() {
		return totalGameLost;
	}

	public void setTotalGameLost(int totalGameLost) {
		this.totalGameLost = totalGameLost;
	}

	public int getTotalGameLostNephi() {
		return totalGameLostNephi;
	}

	public void setTotalGameLostNephi(int totalGameLostNephi) {
		this.totalGameLostNephi = totalGameLostNephi;
	}

	public int getTotalGameLostSeraph() {
		return totalGameLostSeraph;
	}

	public void setTotalGameLostSeraph(int totalGameLostSeraph) {
		this.totalGameLostSeraph = totalGameLostSeraph;
	}

	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

	public int getTotalRoundNephi() {
		return totalRoundNephi;
	}

	public void setTotalRoundNephi(int totalRoundNephi) {
		this.totalRoundNephi = totalRoundNephi;
	}

	public int getTotalRoundSeraph() {
		return totalRoundSeraph;
	}

	public void setTotalRoundSeraph(int totalRoundSeraph) {
		this.totalRoundSeraph = totalRoundSeraph;
	}

	public int getTotalRoundWin() {
		return totalRoundWin;
	}

	public void setTotalRoundWin(int totalRoundWin) {
		this.totalRoundWin = totalRoundWin;
	}

	public int getTotalRoundWinNephi() {
		return totalRoundWinNephi;
	}

	public void setTotalRoundWinNephi(int totalRoundWinNephi) {
		this.totalRoundWinNephi = totalRoundWinNephi;
	}

	public int getTotalRoundWinSeraph() {
		return totalRoundWinSeraph;
	}

	public void setTotalRoundWinSeraph(int totalRoundWinSeraph) {
		this.totalRoundWinSeraph = totalRoundWinSeraph;
	}

	public int getTotalRoundLost() {
		return totalRoundLost;
	}

	public void setTotalRoundLost(int totalRoundLost) {
		this.totalRoundLost = totalRoundLost;
	}

	public int getTotalRoundLostNephi() {
		return totalRoundLostNephi;
	}

	public void setTotalRoundLostNephi(int totalRoundLostNephi) {
		this.totalRoundLostNephi = totalRoundLostNephi;
	}

	public int getTotalRoundLostSeraph() {
		return totalRoundLostSeraph;
	}

	public void setTotalRoundLostSeraph(int totalRoundLostSeraph) {
		this.totalRoundLostSeraph = totalRoundLostSeraph;
	}

	public int getTotalMVP() {
		return totalMVP;
	}

	public void setTotalMVP(int totalMVP) {
		this.totalMVP = totalMVP;
	}

	public int getTotalMVPNephi() {
		return totalMVPNephi;
	}

	public void setTotalMVPNephi(int totalMVPNephi) {
		this.totalMVPNephi = totalMVPNephi;
	}

	public int getTotalMVPSeraph() {
		return totalMVPSeraph;
	}

	public void setTotalMVPSeraph(int totalMVPSeraph) {
		this.totalMVPSeraph = totalMVPSeraph;
	}

	public int getTotalFlagTaken() {
		return totalFlagTaken;
	}

	public void setTotalFlagTaken(int totalFlagTaken) {
		this.totalFlagTaken = totalFlagTaken;
	}

	public int getTotalFlagCapture() {
		return totalFlagCapture;
	}

	public void setTotalFlagCapture(int totalFlagCapture) {
		this.totalFlagCapture = totalFlagCapture;
	}

	public int getTotalFlagDrop() {
		return totalFlagDrop;
	}

	public void setTotalFlagDrop(int totalFlagDrop) {
		this.totalFlagDrop = totalFlagDrop;
	}

	public int getTotalFlagBack() {
		return totalFlagBack;
	}

	public void setTotalFlagBack(int totalFlagBack) {
		this.totalFlagBack = totalFlagBack;
	}

	public int getTotalPerfect() {
		return totalPerfect;
	}

	public void setTotalPerfect(int totalPerfect) {
		this.totalPerfect = totalPerfect;
	}

	public int getTotalPerfDef() {
		return totalPerfDef;
	}

	public void setTotalPerfDef(int totalPerfDef) {
		this.totalPerfDef = totalPerfDef;
	}

	public int getTotalBlitz() {
		return totalBlitz;
	}

	public void setTotalBlitz(int totalBlitz) {
		this.totalBlitz = totalBlitz;
	}

	public int getTotalPlayersKilled() {
		return totalPlayersKilled;
	}

	public void setTotalPlayersKilled(int totalPlayersKilled) {
		this.totalPlayersKilled = totalPlayersKilled;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public int getTotalMateKills() {
		return totalMateKills;
	}

	public void setTotalMateKills(int totalMateKills) {
		this.totalMateKills = totalMateKills;
	}

	public int getMaxDealtDmg() {
		return maxDealtDmg;
	}

	public void setMaxDealtDmg(int maxDealtDmg) {
		this.maxDealtDmg = maxDealtDmg;
	}

	public int getTotalDealtDmg() {
		return totalDealtDmg;
	}

	public void setTotalDealtDmg(int totalDealtDmg) {
		this.totalDealtDmg = totalDealtDmg;
	}

	public int getAvgDealtDmg() {
		return avgDealtDmg;
	}

	public void setAvgDealtDmg(int avgDealtDmg) {
		this.avgDealtDmg = avgDealtDmg;
	}

	public int getMaxTakenDmg() {
		return maxTakenDmg;
	}

	public void setMaxTakenDmg(int maxTakenDmg) {
		this.maxTakenDmg = maxTakenDmg;
	}

	public int getTotalTakenDmg() {
		return totalTakenDmg;
	}

	public void setTotalTakenDmg(int totalTakenDmg) {
		this.totalTakenDmg = totalTakenDmg;
	}

	public int getAvgTakenDmg() {
		return avgTakenDmg;
	}

	public void setAvgTakenDmg(int avgTakenDmg) {
		this.avgTakenDmg = avgTakenDmg;
	}

	public int getTotalSwordSwing() {
		return totalSwordSwing;
	}

	public void setTotalSwordSwing(int totalSwordSwing) {
		this.totalSwordSwing = totalSwordSwing;
	}

	public int getTotalSwordHits() {
		return totalSwordHits;
	}

	public void setTotalSwordHits(int totalSwordHits) {
		this.totalSwordHits = totalSwordHits;
	}

	public int getAvgSwordSwing() {
		return avgSwordSwing;
	}

	public void setAvgSwordSwing(int avgSwordSwing) {
		this.avgSwordSwing = avgSwordSwing;
	}

	public int getAvgSwordHits() {
		return avgSwordHits;
	}

	public void setAvgSwordHits(int avgSwordHits) {
		this.avgSwordHits = avgSwordHits;
	}

	public int getTotalAxeSwing() {
		return totalAxeSwing;
	}

	public void setTotalAxeSwing(int totalAxeSwing) {
		this.totalAxeSwing = totalAxeSwing;
	}

	public int getTotalAxeHits() {
		return totalAxeHits;
	}

	public void setTotalAxeHits(int totalAxeHits) {
		this.totalAxeHits = totalAxeHits;
	}

	public int getAvgAxeSwing() {
		return avgAxeSwing;
	}

	public void setAvgAxeSwing(int avgAxeSwing) {
		this.avgAxeSwing = avgAxeSwing;
	}

	public int getAvgAxeHits() {
		return avgAxeHits;
	}

	public void setAvgAxeHits(int avgAxeHits) {
		this.avgAxeHits = avgAxeHits;
	}

	public int getTotalArrowShots() {
		return totalArrowShots;
	}

	public void setTotalArrowShots(int totalArrowShots) {
		this.totalArrowShots = totalArrowShots;
	}

	public int getTotalArrowHits() {
		return totalArrowHits;
	}

	public void setTotalArrowHits(int totalArrowHits) {
		this.totalArrowHits = totalArrowHits;
	}

	public int getAvgArrowShots() {
		return avgArrowShots;
	}

	public void setAvgArrowShots(int avgArrowShots) {
		this.avgArrowShots = avgArrowShots;
	}

	public int getAvgArrowHits() {
		return avgArrowHits;
	}

	public void setAvgArrowHits(int avgArrowHits) {
		this.avgArrowHits = avgArrowHits;
	}

	public int getTotalHealPotUsed() {
		return totalHealPotUsed;
	}

	public void setTotalHealPotUsed(int totalHealPotUsed) {
		this.totalHealPotUsed = totalHealPotUsed;
	}

	public int getAvgHealPotUsed() {
		return avgHealPotUsed;
	}

	public void setAvgHealPotUsed(int avgHealPotUsed) {
		this.avgHealPotUsed = avgHealPotUsed;
	}

	public int getTotalHealMates() {
		return totalHealMates;
	}

	public void setTotalHealMates(int totalHealMates) {
		this.totalHealMates = totalHealMates;
	}

	public int getAvgHealMates() {
		return avgHealMates;
	}

	public void setAvgHealMates(int avgHealMates) {
		this.avgHealMates = avgHealMates;
	}

	public int getMaxTimeNoDeath() {
		return maxTimeNoDeath;
	}

	public void setMaxTimeNoDeath(int maxTimeNoDeath) {
		this.maxTimeNoDeath = maxTimeNoDeath;
	}

	public int getTotalTimeNoDeath() {
		return totalTimeNoDeath;
	}

	public void setTotalTimeNoDeath(int totalTimeNoDeath) {
		this.totalTimeNoDeath = totalTimeNoDeath;
	}

	public int getAvgTimeNoDeath() {
		return avgTimeNoDeath;
	}

	public void setAvgTimeNoDeath(int avgTimeNoDeath) {
		this.avgTimeNoDeath = avgTimeNoDeath;
	}

	public int getMaxSneakTime() {
		return maxSneakTime;
	}

	public void setMaxSneakTime(int maxSneakTime) {
		this.maxSneakTime = maxSneakTime;
	}

	public int getTotalSneakTime() {
		return totalSneakTime;
	}

	public void setTotalSneakTime(int totalSneakTime) {
		this.totalSneakTime = totalSneakTime;
	}

	public int getAvgSneakTime() {
		return avgSneakTime;
	}

	public void setAvgSneakTime(int avgSneakTime) {
		this.avgSneakTime = avgSneakTime;
	}
}
