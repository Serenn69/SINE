package fr.phoenix.sineplugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class SqlConnection {

	public static Connection connection;
	private String urlbase, host, database, user, pass;
	private int port;

	public SqlConnection(String urlbase, String host, int port, String database, String user, String pass) {

		this.urlbase = urlbase;
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.pass = pass;
	}

	public void connect() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection(urlbase + host + ":" + port + "/" + database, user, pass);
				System.out.println("Connected to SQL");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("Disconnected to SQL");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isConnected() {
		return connection != null;
	}

	public static PlayerStats loadPlayerStatsSeasonFromDatabase(Player p) {

		if (!Vars.pStatsSeason.containsKey(p)) {
			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"SELECT team, access, blacklist, totalGame, totalGameNephi, totalGameSeraph, totalGameWin, totalGameWinNephi, totalGameWinSeraph, totalGameLost, totalGameLostNephi, totalGameLostSeraph, totalRound, totalRoundNephi, totalRoundSeraph, totalRoundWin, totalRoundWinNephi, totalRoundWinSeraph, totalRoundLost, totalRoundLostNephi, totalRoundLostSeraph, totalMVP, totalMVPNephi, totalMVPSeraph, totalFlagTaken, totalFlagCapture, totalFlagDrop, totalFlagBack, totalPlayersKilled, totalDeaths, totalMateKills, totalPerfect, totalPerfDef, totalBlitz, maxDealtDmg, totalDealtDmg, avgDealtDmg, maxTakenDmg, totalTakenDmg, avgTakenDmg, totalSwordSwing, totalSwordHits, avgSwordSwing, avgSwordHits, totalAxeSwing, totalAxeHits, avgAxeSwing, avgAxeHits, totalArrowShots, totalArrowHits, avgArrowShots, avgArrowHits, totalHealPotUsed, avgHealPotUsed, totalHealMates, avgHealMates, maxTimeNoDeath, totalTimeNoDeath, avgTimeNoDeath, maxSneakTime, totalSneakTime, avgSneakTime FROM PlayerStatsSeason WHERE uuid = ?");
				rs.setString(1, p.getUniqueId().toString());

				ResultSet data = rs.executeQuery();

				String team = null;
				int access = 10, blacklist = 40;
				int totalGame = 0, totalGameNephi = 0, totalGameSeraph = 0, totalGameWin = 0, totalGameWinNephi = 0,
						totalGameWinSeraph = 0, totalGameLost = 0, totalGameLostNephi = 0, totalGameLostSeraph = 0,
						totalRound = 0, totalRoundNephi = 0, totalRoundSeraph = 0, totalRoundWin = 0,
						totalRoundWinNephi = 0, totalRoundWinSeraph = 0, totalRoundLost = 0, totalRoundLostNephi = 0,
						totalRoundLostSeraph = 0, totalMVP = 0, totalMVPNephi = 0, totalMVPSeraph = 0,
						totalFlagTaken = 0, totalFlagCapture = 0, totalFlagDrop = 0, totalFlagBack = 0,
						totalPlayersKilled = 0, totalDeaths = 0, totalMateKills = 0, totalPerfect = 0, totalPerfDef = 0,
						totalBlitz = 0, maxDealtDmg = 0, totalDealtDmg = 0, avgDealtDmg = 0, maxTakenDmg = 0,
						totalTakenDmg = 0, avgTakenDmg = 0, totalSwordSwing = 0, totalSwordHits = 0, avgSwordSwing = 0,
						avgSwordHits = 0, totalAxeSwing = 0, totalAxeHits = 0, avgAxeSwing = 0, avgAxeHits = 0,
						totalArrowShots = 0, totalArrowHits = 0, avgArrowShots = 0, avgArrowHits = 0,
						totalHealPotUsed = 0, avgHealPotUsed = 0, totalHealMates = 0, avgHealMates = 0,
						maxTimeNoDeath = 0, totalTimeNoDeath = 0, avgTimeNoDeath = 0, maxSneakTime = 0, totalSneakTime = 0, avgSneakTime = 0;

				while (data.next()) {

					team = data.getString("team");
					access = data.getInt("access");
					blacklist = data.getInt("blacklist");
					totalGame = data.getInt("totalGame");
					totalGameNephi = data.getInt("totalGameNephi");
					totalGameSeraph = data.getInt("totalGameSeraph");
					totalGameWin = data.getInt("totalGameWin");
					totalGameWinNephi = data.getInt("totalGameWinNephi");
					totalGameWinSeraph = data.getInt("totalGameWinSeraph");
					totalGameLost = data.getInt("totalGameLost");
					totalGameLostNephi = data.getInt("totalGameLostNephi");
					totalGameLostSeraph = data.getInt("totalGameLostSeraph");
					totalRound = data.getInt("totalRound");
					totalRoundNephi = data.getInt("totalRoundNephi");
					totalRoundSeraph = data.getInt("totalRoundSeraph");
					totalRoundWin = data.getInt("totalRoundWin");
					totalRoundWinNephi = data.getInt("totalRoundWinNephi");
					totalRoundWinSeraph = data.getInt("totalRoundWinSeraph");
					totalRoundLost = data.getInt("totalRoundLost");
					totalRoundLostNephi = data.getInt("totalRoundLostNephi");
					totalRoundLostSeraph = data.getInt("totalRoundLostSeraph");
					totalMVP = data.getInt("totalMVP");
					totalMVPNephi = data.getInt("totalMVPNephi");
					totalMVPSeraph = data.getInt("totalMVPSeraph");
					totalFlagTaken = data.getInt("totalFlagTaken");
					totalFlagCapture = data.getInt("totalFlagCapture");
					totalFlagDrop = data.getInt("totalFlagDrop");
					totalFlagBack = data.getInt("totalFlagBack");
					totalPlayersKilled = data.getInt("totalPlayersKilled");
					totalDeaths = data.getInt("totalDeaths");
					totalMateKills = data.getInt("totalMateKills");
					totalPerfect = data.getInt("totalPerfect");
					totalPerfDef = data.getInt("totalPerfDef");
					totalBlitz = data.getInt("totalBlitz");
					maxDealtDmg = data.getInt("maxDealtDmg");
					totalDealtDmg = data.getInt("totalDealtDmg");
					avgDealtDmg = data.getInt("avgDealtDmg");
					maxTakenDmg = data.getInt("maxTakenDmg");
					totalTakenDmg = data.getInt("totalTakenDmg");
					avgTakenDmg = data.getInt("avgTakenDmg");
					totalSwordSwing = data.getInt("totalSwordSwing");
					totalSwordHits = data.getInt("totalSwordHits");
					avgSwordSwing = data.getInt("avgSwordSwing");
					avgSwordHits = data.getInt("avgSwordHits");
					totalAxeSwing = data.getInt("totalAxeSwing");
					totalAxeHits = data.getInt("totalAxeHits");
					avgAxeSwing = data.getInt("avgAxeSwing");
					avgAxeHits = data.getInt("avgAxeHits");
					totalArrowShots = data.getInt("totalArrowShots");
					totalArrowHits = data.getInt("totalArrowHits");
					avgArrowShots = data.getInt("avgArrowShots");
					avgArrowHits = data.getInt("avgArrowHits");
					totalHealPotUsed = data.getInt("totalHealPotUsed");
					avgHealPotUsed = data.getInt("avgHealPotUsed");
					totalHealMates = data.getInt("totalHealMates");
					avgHealMates = data.getInt("avgHealMates");
					maxTimeNoDeath = data.getInt("maxTimeNoDeath");
					totalTimeNoDeath = data.getInt("totalTimeNoDeath");
					avgTimeNoDeath = data.getInt("avgTimeNoDeath");
					maxSneakTime = data.getInt("maxSneakTime");
					totalSneakTime = data.getInt("totalSneakTime");
					avgSneakTime = data.getInt("avgSneakTime");
				}

				PlayerStats dataPS = new PlayerStats();

				dataPS.setTeam(team);
				dataPS.setAccess(access);
				dataPS.setBlacklist(blacklist);
				dataPS.setTotalGame(totalGame);
				dataPS.setTotalGameNephi(totalGameNephi);
				dataPS.setTotalGameSeraph(totalGameSeraph);
				dataPS.setTotalGameWin(totalGameWin);
				dataPS.setTotalGameWinNephi(totalGameWinNephi);
				dataPS.setTotalGameWinSeraph(totalGameWinSeraph);
				dataPS.setTotalGameLost(totalGameLost);
				dataPS.setTotalGameLostNephi(totalGameLostNephi);
				dataPS.setTotalGameLostSeraph(totalGameLostSeraph);
				dataPS.setTotalRound(totalRound);
				dataPS.setTotalRoundNephi(totalRoundNephi);
				dataPS.setTotalRoundSeraph(totalRoundSeraph);
				dataPS.setTotalRoundWin(totalRoundWin);
				dataPS.setTotalRoundWinNephi(totalRoundWinNephi);
				dataPS.setTotalRoundWinSeraph(totalRoundWinSeraph);
				dataPS.setTotalRoundLost(totalRoundLost);
				dataPS.setTotalRoundLostNephi(totalRoundLostNephi);
				dataPS.setTotalRoundLostSeraph(totalRoundLostSeraph);
				dataPS.setTotalMVP(totalMVP);
				dataPS.setTotalMVPNephi(totalMVPNephi);
				dataPS.setTotalMVPSeraph(totalMVPSeraph);
				dataPS.setTotalFlagTaken(totalFlagTaken);
				dataPS.setTotalFlagCapture(totalFlagCapture);
				dataPS.setTotalFlagDrop(totalFlagDrop);
				dataPS.setTotalFlagBack(totalFlagBack);
				dataPS.setTotalPlayersKilled(totalPlayersKilled);
				dataPS.setTotalDeaths(totalDeaths);
				dataPS.setTotalMateKills(totalMateKills);
				dataPS.setTotalPerfect(totalPerfect);
				dataPS.setTotalPerfDef(totalPerfDef);
				dataPS.setTotalBlitz(totalBlitz);
				dataPS.setMaxDealtDmg(maxDealtDmg);
				dataPS.setTotalDealtDmg(totalDealtDmg);
				dataPS.setAvgDealtDmg(avgDealtDmg);
				dataPS.setMaxTakenDmg(maxTakenDmg);
				dataPS.setTotalTakenDmg(totalTakenDmg);
				dataPS.setAvgTakenDmg(avgTakenDmg);
				dataPS.setTotalSwordSwing(totalSwordSwing);
				dataPS.setTotalSwordHits(totalSwordHits);
				dataPS.setAvgSwordSwing(avgSwordSwing);
				dataPS.setAvgSwordHits(avgSwordHits);
				dataPS.setTotalAxeSwing(totalAxeSwing);
				dataPS.setTotalAxeHits(totalAxeHits);
				dataPS.setAvgAxeSwing(avgAxeSwing);
				dataPS.setAvgAxeHits(avgAxeHits);
				dataPS.setTotalArrowShots(totalArrowShots);
				dataPS.setTotalArrowHits(totalArrowHits);
				dataPS.setAvgArrowShots(avgArrowShots);
				dataPS.setAvgArrowHits(avgArrowHits);
				dataPS.setTotalHealPotUsed(totalHealPotUsed);
				dataPS.setAvgHealPotUsed(avgHealPotUsed);
				dataPS.setTotalHealMates(totalHealMates);
				dataPS.setAvgHealMates(avgHealMates);
				dataPS.setMaxTimeNoDeath(maxTimeNoDeath);
				dataPS.setTotalTimeNoDeath(totalTimeNoDeath);
				dataPS.setAvgTimeNoDeath(avgTimeNoDeath);
				dataPS.setMaxSneakTime(maxSneakTime);
				dataPS.setTotalSneakTime(totalSneakTime);
				dataPS.setAvgSneakTime(avgSneakTime);

				return dataPS;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new PlayerStats();
	}
	
	public static PlayerStats loadPlayerStatsLastGameFromDatabase(Player p) {

		if (!Vars.pStatsLastGame.containsKey(p)) {
			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"SELECT team, voteMVP, totalGame, totalGameNephi, totalGameSeraph, totalGameWin, totalGameWinNephi, totalGameWinSeraph, totalGameLost, totalGameLostNephi, totalGameLostSeraph, totalRound, totalRoundNephi, totalRoundSeraph, totalRoundWin, totalRoundWinNephi, totalRoundWinSeraph, totalRoundLost, totalRoundLostNephi, totalRoundLostSeraph, totalMVP, totalMVPNephi, totalMVPSeraph, totalFlagTaken, totalFlagCapture, totalFlagDrop, totalFlagBack, totalPlayersKilled, totalDeaths, totalMateKills, totalPerfect, totalPerfDef, totalBlitz, maxDealtDmg, totalDealtDmg, avgDealtDmg, maxTakenDmg, totalTakenDmg, avgTakenDmg, totalSwordSwing, totalSwordHits, avgSwordSwing, avgSwordHits, totalAxeSwing, totalAxeHits, avgAxeSwing, avgAxeHits, totalArrowShots, totalArrowHits, avgArrowShots, avgArrowHits, totalHealPotUsed, avgHealPotUsed, totalHealMates, avgHealMates, maxTimeNoDeath, totalTimeNoDeath, avgTimeNoDeath, maxSneakTime, totalSneakTime, avgSneakTime FROM PlayerStatsLastGame WHERE uuid = ?");
				rs.setString(1, p.getUniqueId().toString());

				ResultSet data = rs.executeQuery();

				String team = null;
				int voteMVP = 0, totalGame = 0, totalGameNephi = 0, totalGameSeraph = 0, totalGameWin = 0, totalGameWinNephi = 0,
						totalGameWinSeraph = 0, totalGameLost = 0, totalGameLostNephi = 0, totalGameLostSeraph = 0,
						totalRound = 0, totalRoundNephi = 0, totalRoundSeraph = 0, totalRoundWin = 0,
						totalRoundWinNephi = 0, totalRoundWinSeraph = 0, totalRoundLost = 0, totalRoundLostNephi = 0,
						totalRoundLostSeraph = 0, totalMVP = 0, totalMVPNephi = 0, totalMVPSeraph = 0,
						totalFlagTaken = 0, totalFlagCapture = 0, totalFlagDrop = 0, totalFlagBack = 0,
						totalPlayersKilled = 0, totalDeaths = 0, totalMateKills = 0, totalPerfect = 0, totalPerfDef = 0,
						totalBlitz = 0, maxDealtDmg = 0, totalDealtDmg = 0, avgDealtDmg = 0, maxTakenDmg = 0,
						totalTakenDmg = 0, avgTakenDmg = 0, totalSwordSwing = 0, totalSwordHits = 0, avgSwordSwing = 0,
						avgSwordHits = 0, totalAxeSwing = 0, totalAxeHits = 0, avgAxeSwing = 0, avgAxeHits = 0,
						totalArrowShots = 0, totalArrowHits = 0, avgArrowShots = 0, avgArrowHits = 0,
						totalHealPotUsed = 0, avgHealPotUsed = 0, totalHealMates = 0, avgHealMates = 0,
						maxTimeNoDeath = 0, totalTimeNoDeath = 0, avgTimeNoDeath = 0, maxSneakTime = 0, totalSneakTime = 0, avgSneakTime = 0;

				while (data.next()) {

					team = data.getString("team");
					voteMVP = data.getInt("voteMVP");
					totalGame = data.getInt("totalGame");
					totalGameNephi = data.getInt("totalGameNephi");
					totalGameSeraph = data.getInt("totalGameSeraph");
					totalGameWin = data.getInt("totalGameWin");
					totalGameWinNephi = data.getInt("totalGameWinNephi");
					totalGameWinSeraph = data.getInt("totalGameWinSeraph");
					totalGameLost = data.getInt("totalGameLost");
					totalGameLostNephi = data.getInt("totalGameLostNephi");
					totalGameLostSeraph = data.getInt("totalGameLostSeraph");
					totalRound = data.getInt("totalRound");
					totalRoundNephi = data.getInt("totalRoundNephi");
					totalRoundSeraph = data.getInt("totalRoundSeraph");
					totalRoundWin = data.getInt("totalRoundWin");
					totalRoundWinNephi = data.getInt("totalRoundWinNephi");
					totalRoundWinSeraph = data.getInt("totalRoundWinSeraph");
					totalRoundLost = data.getInt("totalRoundLost");
					totalRoundLostNephi = data.getInt("totalRoundLostNephi");
					totalRoundLostSeraph = data.getInt("totalRoundLostSeraph");
					totalMVP = data.getInt("totalMVP");
					totalMVPNephi = data.getInt("totalMVPNephi");
					totalMVPSeraph = data.getInt("totalMVPSeraph");
					totalFlagTaken = data.getInt("totalFlagTaken");
					totalFlagCapture = data.getInt("totalFlagCapture");
					totalFlagDrop = data.getInt("totalFlagDrop");
					totalFlagBack = data.getInt("totalFlagBack");
					totalPlayersKilled = data.getInt("totalPlayersKilled");
					totalDeaths = data.getInt("totalDeaths");
					totalMateKills = data.getInt("totalMateKills");
					totalPerfect = data.getInt("totalPerfect");
					totalPerfDef = data.getInt("totalPerfDef");
					totalBlitz = data.getInt("totalBlitz");
					maxDealtDmg = data.getInt("maxDealtDmg");
					totalDealtDmg = data.getInt("totalDealtDmg");
					avgDealtDmg = data.getInt("avgDealtDmg");
					maxTakenDmg = data.getInt("maxTakenDmg");
					totalTakenDmg = data.getInt("totalTakenDmg");
					avgTakenDmg = data.getInt("avgTakenDmg");
					totalSwordSwing = data.getInt("totalSwordSwing");
					totalSwordHits = data.getInt("totalSwordHits");
					avgSwordSwing = data.getInt("avgSwordSwing");
					avgSwordHits = data.getInt("avgSwordHits");
					totalAxeSwing = data.getInt("totalAxeSwing");
					totalAxeHits = data.getInt("totalAxeHits");
					avgAxeSwing = data.getInt("avgAxeSwing");
					avgAxeHits = data.getInt("avgAxeHits");
					totalArrowShots = data.getInt("totalArrowShots");
					totalArrowHits = data.getInt("totalArrowHits");
					avgArrowShots = data.getInt("avgArrowShots");
					avgArrowHits = data.getInt("avgArrowHits");
					totalHealPotUsed = data.getInt("totalHealPotUsed");
					avgHealPotUsed = data.getInt("avgHealPotUsed");
					totalHealMates = data.getInt("totalHealMates");
					avgHealMates = data.getInt("avgHealMates");
					maxTimeNoDeath = data.getInt("maxTimeNoDeath");
					totalTimeNoDeath = data.getInt("totalTimeNoDeath");
					avgTimeNoDeath = data.getInt("avgTimeNoDeath");
					maxSneakTime = data.getInt("maxSneakTime");
					totalSneakTime = data.getInt("totalSneakTime");
					avgSneakTime = data.getInt("avgSneakTime");
				}

				PlayerStats dataPS = new PlayerStats();

				dataPS.setTeam(team);
				dataPS.setVoteMVP(voteMVP);
				dataPS.setTotalGame(totalGame);
				dataPS.setTotalGameNephi(totalGameNephi);
				dataPS.setTotalGameSeraph(totalGameSeraph);
				dataPS.setTotalGameWin(totalGameWin);
				dataPS.setTotalGameWinNephi(totalGameWinNephi);
				dataPS.setTotalGameWinSeraph(totalGameWinSeraph);
				dataPS.setTotalGameLost(totalGameLost);
				dataPS.setTotalGameLostNephi(totalGameLostNephi);
				dataPS.setTotalGameLostSeraph(totalGameLostSeraph);
				dataPS.setTotalRound(totalRound);
				dataPS.setTotalRoundNephi(totalRoundNephi);
				dataPS.setTotalRoundSeraph(totalRoundSeraph);
				dataPS.setTotalRoundWin(totalRoundWin);
				dataPS.setTotalRoundWinNephi(totalRoundWinNephi);
				dataPS.setTotalRoundWinSeraph(totalRoundWinSeraph);
				dataPS.setTotalRoundLost(totalRoundLost);
				dataPS.setTotalRoundLostNephi(totalRoundLostNephi);
				dataPS.setTotalRoundLostSeraph(totalRoundLostSeraph);
				dataPS.setTotalMVP(totalMVP);
				dataPS.setTotalMVPNephi(totalMVPNephi);
				dataPS.setTotalMVPSeraph(totalMVPSeraph);
				dataPS.setTotalFlagTaken(totalFlagTaken);
				dataPS.setTotalFlagCapture(totalFlagCapture);
				dataPS.setTotalFlagDrop(totalFlagDrop);
				dataPS.setTotalFlagBack(totalFlagBack);
				dataPS.setTotalPlayersKilled(totalPlayersKilled);
				dataPS.setTotalDeaths(totalDeaths);
				dataPS.setTotalMateKills(totalMateKills);
				dataPS.setTotalPerfect(totalPerfect);
				dataPS.setTotalPerfDef(totalPerfDef);
				dataPS.setTotalBlitz(totalBlitz);
				dataPS.setMaxDealtDmg(maxDealtDmg);
				dataPS.setTotalDealtDmg(totalDealtDmg);
				dataPS.setAvgDealtDmg(avgDealtDmg);
				dataPS.setMaxTakenDmg(maxTakenDmg);
				dataPS.setTotalTakenDmg(totalTakenDmg);
				dataPS.setAvgTakenDmg(avgTakenDmg);
				dataPS.setTotalSwordSwing(totalSwordSwing);
				dataPS.setTotalSwordHits(totalSwordHits);
				dataPS.setAvgSwordSwing(avgSwordSwing);
				dataPS.setAvgSwordHits(avgSwordHits);
				dataPS.setTotalAxeSwing(totalAxeSwing);
				dataPS.setTotalAxeHits(totalAxeHits);
				dataPS.setAvgAxeSwing(avgAxeSwing);
				dataPS.setAvgAxeHits(avgAxeHits);
				dataPS.setTotalArrowShots(totalArrowShots);
				dataPS.setTotalArrowHits(totalArrowHits);
				dataPS.setAvgArrowShots(avgArrowShots);
				dataPS.setAvgArrowHits(avgArrowHits);
				dataPS.setTotalHealPotUsed(totalHealPotUsed);
				dataPS.setAvgHealPotUsed(avgHealPotUsed);
				dataPS.setTotalHealMates(totalHealMates);
				dataPS.setAvgHealMates(avgHealMates);
				dataPS.setMaxTimeNoDeath(maxTimeNoDeath);
				dataPS.setTotalTimeNoDeath(totalTimeNoDeath);
				dataPS.setAvgTimeNoDeath(avgTimeNoDeath);
				dataPS.setMaxSneakTime(maxSneakTime);
				dataPS.setTotalSneakTime(totalSneakTime);
				dataPS.setAvgSneakTime(avgSneakTime);

				Bukkit.broadcastMessage("pStatsSeason load = "+dataPS);
				return dataPS;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new PlayerStats();
	}

	public static void updatePlayerStatsLastGameToDatabase(Player p) {

		if (Vars.pStatsLastGame.containsKey(p)) {

			PlayerStats dataPS = Vars.pStatsLastGame.get(p);

			int totalGame = dataPS.getTotalGame();
			int totalGameNephi = dataPS.getTotalGameNephi();
			int totalGameSeraph = dataPS.getTotalGameSeraph();
			int totalGameWin = dataPS.getTotalGameWin();
			int totalGameWinNephi = dataPS.getTotalGameWinNephi();
			int totalGameWinSeraph = dataPS.getTotalGameWinSeraph();
			int totalGameLost = dataPS.getTotalGameLost();
			int totalGameLostNephi = dataPS.getTotalGameLostNephi();
			int totalGameLostSeraph = dataPS.getTotalGameLostSeraph();
			int totalRound = dataPS.getTotalRound();
			int totalRoundNephi = dataPS.getTotalRoundNephi();
			int totalRoundSeraph = dataPS.getTotalRoundSeraph();
			int totalRoundWin = dataPS.getTotalRoundWin();
			int totalRoundWinNephi = dataPS.getTotalRoundWinNephi();
			int totalRoundWinSeraph = dataPS.getTotalRoundWinSeraph();
			int totalRoundLost = dataPS.getTotalRoundLost();
			int totalRoundLostNephi = dataPS.getTotalRoundLostNephi();
			int totalRoundLostSeraph = dataPS.getTotalRoundLostSeraph();
			int totalMVP = dataPS.getTotalMVP();
			int totalMVPNephi = dataPS.getTotalMVPNephi();
			int totalMVPSeraph = dataPS.getTotalMVPSeraph();
			int totalFlagTaken = dataPS.getTotalFlagTaken();
			int totalFlagCapture = dataPS.getTotalFlagCapture();
			int totalFlagDrop = dataPS.getTotalFlagDrop();
			int totalFlagBack = dataPS.getTotalFlagBack();
			int totalPlayerKilled = dataPS.getTotalPlayersKilled();
			int totalDeaths = dataPS.getTotalDeaths();
			int totalMateKills = dataPS.getTotalMateKills();
			int totalPerfect = dataPS.getTotalPerfect();
			int totalPerfDef = dataPS.getTotalPerfDef();
			int totalBlitz = dataPS.getTotalBlitz();
			int maxDealtDmg = dataPS.getMaxDealtDmg();
			int totalDealtDmg = dataPS.getTotalDealtDmg();
			int avgDeatlDmg = dataPS.getAvgDealtDmg();
			int maxTakenDmg = dataPS.getMaxTakenDmg();
			int totalTakenDmg = dataPS.getTotalTakenDmg();
			int avgTakenDmg = dataPS.getAvgTakenDmg();
			int totalSwordSwing = dataPS.getTotalSwordSwing();
			int totalSwordHits = dataPS.getTotalSwordHits();
			int avgSwordSwing = dataPS.getAvgSwordSwing();
			int avgSwordHits = dataPS.getAvgSwordHits();
			int totalAxeSwing = dataPS.getTotalAxeSwing();
			int totalAxeHits = dataPS.getTotalAxeHits();
			int avgAxeSwing = dataPS.getAvgAxeSwing();
			int avgAxeHits = dataPS.getAvgAxeHits();
			int totalArrowShots = dataPS.getTotalArrowShots();
			int totalArrowHits = dataPS.getTotalArrowHits();
			int avgArrowShots = dataPS.getAvgArrowShots();
			int avgArrowHits = dataPS.getAvgArrowHits();
			int totalHealPotUsed = dataPS.getTotalHealPotUsed();
			int avgHealPotUsed = dataPS.getAvgHealPotUsed();
			int totalHealMates = dataPS.getTotalHealMates();
			int avgHealMates = dataPS.getAvgHealMates();
			int maxTimeNoDeath = dataPS.getMaxTimeNoDeath();
			int totalTimeNoDeath = dataPS.getTotalTimeNoDeath();
			int avgTimeNoDeath = dataPS.getAvgTimeNoDeath();
			int maxSneakTime = dataPS.getMaxSneakTime();
			int totalSneakTime = dataPS.getTotalSneakTime();
			int avgSneakTime = dataPS.getAvgSneakTime();

			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"UPDATE PlayerStatsLastGame SET totalGame = ?, totalGameNephi = ?, totalGameSeraph = ?, totalGameWin = ?, totalGameWinNephi = ?, totalGameWinSeraph = ?, totalGameLost = ?, totalGameLostNephi = ?, totalGameLostSeraph = ?, totalRound = ?, totalRoundNephi = ?, totalRoundSeraph = ?, totalRoundWin = ?, totalRoundWinNephi = ?, totalRoundWinSeraph = ?, totalRoundLost = ?, totalRoundLostNephi = ?, totalRoundLostSeraph = ?, totalMVP = ?, totalMVPNephi = ?, totalMVPSeraph = ?, totalFlagTaken = ?, totalFlagCapture = ?, totalFlagDrop = ?, totalFlagBack = ?, totalPlayersKilled = ?, totalDeaths = ?, totalMateKills = ?, totalPerfect = ?, totalPerfDef = ?, totalBlitz = ?, maxDealtDmg = ?, totalDealtDmg = ?, avgDealtDmg = ?, maxTakenDmg = ?, totalTakenDmg = ?, avgTakenDmg = ?, totalSwordSwing = ?, totalSwordHits = ?, avgSwordSwing = ?, avgSwordHits = ?, totalAxeSwing = ?, totalAxeHits = ?, avgAxeSwing = ?, avgAxeHits = ?, totalArrowShots = ?, totalArrowHits = ?, avgArrowShots = ?, avgArrowHits = ?, totalHealPotUsed = ?, avgHealPotUsed = ?, totalHealMates = ?, avgHealMates = ?, maxTimeNoDeath = ?, totalTimeNoDeath = ?, avgTimeNoDeath = ?, maxSneakTime = ?, totalSneakTime = ?, avgSneakTime = ? WHERE uuid = ?");
				rs.setInt(1, totalGame);
				rs.setInt(2, totalGameNephi);
				rs.setInt(3, totalGameSeraph);
				rs.setInt(4, totalGameWin);
				rs.setInt(5, totalGameWinNephi);
				rs.setInt(6, totalGameWinSeraph);
				rs.setInt(7, totalGameLost);
				rs.setInt(8, totalGameLostNephi);
				rs.setInt(9, totalGameLostSeraph);
				rs.setInt(10, totalRound);
				rs.setInt(11, totalRoundNephi);
				rs.setInt(12, totalRoundSeraph);
				rs.setInt(13, totalRoundWin);
				rs.setInt(14, totalRoundWinNephi);
				rs.setInt(15, totalRoundWinSeraph);
				rs.setInt(16, totalRoundLost);
				rs.setInt(17, totalRoundLostNephi);
				rs.setInt(18, totalRoundLostSeraph);
				rs.setInt(19, totalMVP);
				rs.setInt(20, totalMVPNephi);
				rs.setInt(21, totalMVPSeraph);
				rs.setInt(22, totalFlagTaken);
				rs.setInt(23, totalFlagCapture);
				rs.setInt(24, totalFlagDrop);
				rs.setInt(25, totalFlagBack);
				rs.setInt(26, totalPlayerKilled);
				rs.setInt(27, totalDeaths);
				rs.setInt(28, totalMateKills);
				rs.setInt(29, totalPerfect);
				rs.setInt(30, totalPerfDef);
				rs.setInt(31, totalBlitz);
				rs.setInt(32, maxDealtDmg);
				rs.setInt(33, totalDealtDmg);
				rs.setInt(34, avgDeatlDmg);
				rs.setInt(35, maxTakenDmg);
				rs.setInt(36, totalTakenDmg);
				rs.setInt(37, avgTakenDmg);
				rs.setInt(38, totalSwordSwing);
				rs.setInt(39, totalSwordHits);
				rs.setInt(40, avgSwordSwing);
				rs.setInt(41, avgSwordHits);
				rs.setInt(42, totalAxeSwing);
				rs.setInt(43, totalAxeHits);
				rs.setInt(44, avgAxeSwing);
				rs.setInt(45, avgAxeHits);
				rs.setInt(46, totalArrowShots);
				rs.setInt(47, totalArrowHits);
				rs.setInt(48, avgArrowShots);
				rs.setInt(49, avgArrowHits);
				rs.setInt(50, totalHealPotUsed);
				rs.setInt(51, avgHealPotUsed);
				rs.setInt(52, totalHealMates);
				rs.setInt(53, avgHealMates);				
				rs.setInt(54, maxTimeNoDeath);
				rs.setInt(55, totalTimeNoDeath);
				rs.setInt(56, avgTimeNoDeath);
				rs.setInt(57, maxSneakTime);
				rs.setInt(58, totalSneakTime);
				rs.setInt(59, avgSneakTime);
				rs.setString(60, p.getUniqueId().toString());
				rs.executeUpdate();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updatePlayerStatsSeasonToDatabase(Player p) {

		if (Vars.pStatsSeason.containsKey(p)) {

			PlayerStats dataPSLastGame = Vars.pStatsSeason.get(p);

			int totalGameLastGame = dataPSLastGame.getTotalGame();
			int totalGameNephiLastGame = dataPSLastGame.getTotalGameNephi();
			int totalGameSeraphLastGame = dataPSLastGame.getTotalGameSeraph();
			int totalGameWinLastGame = dataPSLastGame.getTotalGameWin();
			int totalGameWinNephiLastGame = dataPSLastGame.getTotalGameWinNephi();
			int totalGameWinSeraphLastGame = dataPSLastGame.getTotalGameWinSeraph();
			int totalGameLostLastGame = dataPSLastGame.getTotalGameLost();
			int totalGameLostNephiLastGame = dataPSLastGame.getTotalGameLostNephi();
			int totalGameLostSeraphLastGame = dataPSLastGame.getTotalGameLostSeraph();
			int totalRoundLastGame = dataPSLastGame.getTotalRound();
			int totalRoundNephiLastGame = dataPSLastGame.getTotalRoundNephi();
			int totalRoundSeraphLastGame = dataPSLastGame.getTotalRoundSeraph();
			int totalRoundWinLastGame = dataPSLastGame.getTotalRoundWin();
			int totalRoundWinNephiLastGame = dataPSLastGame.getTotalRoundWinNephi();
			int totalRoundWinSeraphLastGame = dataPSLastGame.getTotalRoundWinSeraph();
			int totalRoundLostLastGame = dataPSLastGame.getTotalRoundLost();
			int totalRoundLostNephiLastGame = dataPSLastGame.getTotalRoundLostNephi();
			int totalRoundLostSeraphLastGame = dataPSLastGame.getTotalRoundLostSeraph();
			int totalMVPLastGame = dataPSLastGame.getTotalMVP();
			int totalMVPNephiLastGame = dataPSLastGame.getTotalMVPNephi();
			int totalMVPSeraphLastGame = dataPSLastGame.getTotalMVPSeraph();
			int totalFlagTakenLastGame = dataPSLastGame.getTotalFlagTaken();
			int totalFlagCaptureLastGame = dataPSLastGame.getTotalFlagCapture();
			int totalFlagDropLastGame = dataPSLastGame.getTotalFlagDrop();
			int totalFlagBackLastGame = dataPSLastGame.getTotalFlagBack();
			int totalPlayerKilledLastGame = dataPSLastGame.getTotalPlayersKilled();
			int totalDeathsLastGame = dataPSLastGame.getTotalDeaths();
			int totalMateKillsLastGame = dataPSLastGame.getTotalMateKills();
			int totalPerfectLastGame = dataPSLastGame.getTotalPerfect();
			int totalPerfDefLastGame = dataPSLastGame.getTotalPerfDef();
			int totalBlitzLastGame = dataPSLastGame.getTotalBlitz();
			int maxDealtDmgLastGame = dataPSLastGame.getMaxDealtDmg();
			int totalDealtDmgLastGame = dataPSLastGame.getTotalDealtDmg();
			int maxTakenDmgLastGame = dataPSLastGame.getMaxTakenDmg();
			int totalTakenDmgLastGame = dataPSLastGame.getTotalTakenDmg();
			int totalSwordSwingLastGame = dataPSLastGame.getTotalSwordSwing();
			int totalSwordHitsLastGame = dataPSLastGame.getTotalSwordHits();
			int totalAxeSwingLastGame = dataPSLastGame.getTotalAxeSwing();
			int totalAxeHitsLastGame = dataPSLastGame.getTotalAxeHits();
			int totalArrowShotsLastGame = dataPSLastGame.getTotalArrowShots();
			int totalArrowHitsLastGame = dataPSLastGame.getTotalArrowHits();
			int totalHealPotUsedLastGame = dataPSLastGame.getTotalHealPotUsed();
			int totalHealMatesLastGame = dataPSLastGame.getTotalHealMates();
			int maxTimeNoDeathLastGame = dataPSLastGame.getMaxTimeNoDeath();
			int totalTimeNoDeathLastGame = dataPSLastGame.getTotalTimeNoDeath();
			int maxSneakTimeLastGame = dataPSLastGame.getMaxSneakTime();
			int totalSneakTimeLastGame = dataPSLastGame.getTotalSneakTime();
			
			PlayerStats dataPSSeason = Vars.pStatsSeason.get(p);

			int totalGameSeason = dataPSSeason.getTotalGame();
			int totalGameNephiSeason = dataPSSeason.getTotalGameNephi();
			int totalGameSeraphSeason = dataPSSeason.getTotalGameSeraph();
			int totalGameWinSeason = dataPSSeason.getTotalGameWin();
			int totalGameWinNephiSeason = dataPSSeason.getTotalGameWinNephi();
			int totalGameWinSeraphSeason = dataPSSeason.getTotalGameWinSeraph();
			int totalGameLostSeason = dataPSSeason.getTotalGameLost();
			int totalGameLostNephiSeason = dataPSSeason.getTotalGameLostNephi();
			int totalGameLostSeraphSeason = dataPSSeason.getTotalGameLostSeraph();
			int totalRoundSeason = dataPSSeason.getTotalRound();
			int totalRoundNephiSeason = dataPSSeason.getTotalRoundNephi();
			int totalRoundSeraphSeason = dataPSSeason.getTotalRoundSeraph();
			int totalRoundWinSeason = dataPSSeason.getTotalRoundWin();
			int totalRoundWinNephiSeason = dataPSSeason.getTotalRoundWinNephi();
			int totalRoundWinSeraphSeason = dataPSSeason.getTotalRoundWinSeraph();
			int totalRoundLostSeason = dataPSSeason.getTotalRoundLost();
			int totalRoundLostNephiSeason = dataPSSeason.getTotalRoundLostNephi();
			int totalRoundLostSeraphSeason = dataPSSeason.getTotalRoundLostSeraph();
			int totalMVPSeason = dataPSSeason.getTotalMVP();
			int totalMVPNephiSeason = dataPSSeason.getTotalMVPNephi();
			int totalMVPSeraphSeason = dataPSSeason.getTotalMVPSeraph();
			int totalFlagTakenSeason = dataPSSeason.getTotalFlagTaken();
			int totalFlagCaptureSeason = dataPSSeason.getTotalFlagCapture();
			int totalFlagDropSeason = dataPSSeason.getTotalFlagDrop();
			int totalFlagBackSeason = dataPSSeason.getTotalFlagBack();
			int totalPlayerKilledSeason = dataPSSeason.getTotalPlayersKilled();
			int totalDeathsSeason = dataPSSeason.getTotalDeaths();
			int totalMateKillsSeason = dataPSSeason.getTotalMateKills();
			int totalPerfectSeason = dataPSSeason.getTotalPerfect();
			int totalPerfDefSeason = dataPSSeason.getTotalPerfDef();
			int totalBlitzSeason = dataPSSeason.getTotalBlitz();
			int maxDealtDmgSeason = dataPSSeason.getMaxDealtDmg();
			int totalDealtDmgSeason = dataPSSeason.getTotalDealtDmg();
			int maxTakenDmgSeason = dataPSSeason.getMaxTakenDmg();
			int totalTakenDmgSeason = dataPSSeason.getTotalTakenDmg();
			int totalSwordSwingSeason = dataPSSeason.getTotalSwordSwing();
			int totalSwordHitsSeason = dataPSSeason.getTotalSwordHits();
			int totalAxeSwingSeason = dataPSSeason.getTotalAxeSwing();
			int totalAxeHitsSeason = dataPSSeason.getTotalAxeHits();
			int totalArrowShotsSeason = dataPSSeason.getTotalArrowShots();
			int totalArrowHitsSeason = dataPSSeason.getTotalArrowHits();
			int totalHealPotUsedSeason = dataPSSeason.getTotalHealPotUsed();
			int totalHealMatesSeason = dataPSSeason.getTotalHealMates();
			int maxTimeNoDeathSeason = dataPSSeason.getMaxTimeNoDeath();
			int totalTimeNoDeathSeason = dataPSSeason.getTotalTimeNoDeath();
			int maxSneakTimeSeason = dataPSSeason.getMaxSneakTime();
			int totalSneakTimeSeason = dataPSSeason.getTotalSneakTime();

			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"UPDATE PlayerStatsSeason SET totalGame = ?, totalGameNephi = ?, totalGameSeraph = ?, totalGameWin = ?, totalGameWinNephi = ?, totalGameWinSeraph = ?, totalGameLost = ?, totalGameLostNephi = ?, totalGameLostSeraph = ?, totalRound = ?, totalRoundNephi = ?, totalRoundSeraph = ?, totalRoundWin = ?, totalRoundWinNephi = ?, totalRoundWinSeraph = ?, totalRoundLost = ?, totalRoundLostNephi = ?, totalRoundLostSeraph = ?, totalMVP = ?, totalMVPNephi = ?, totalMVPSeraph = ?, totalFlagTaken = ?, totalFlagCapture = ?, totalFlagDrop = ?, totalFlagBack = ?, totalPlayersKilled = ?, totalDeaths = ?, totalMateKills = ?, totalPerfect = ?, totalPerfDef = ?, totalBlitz = ?, maxDealtDmg = ?, totalDealtDmg = ?, avgDealtDmg = ?, maxTakenDmg = ?, totalTakenDmg = ?, avgTakenDmg = ?, totalSwordSwing = ?, totalSwordHits = ?, avgSwordSwing = ?, avgSwordHits = ?, totalAxeSwing = ?, totalAxeHits = ?, avgAxeSwing = ?, avgAxeHits = ?, totalArrowShots = ?, totalArrowHits = ?, avgArrowShots = ?, avgArrowHits = ?, totalHealPotUsed = ?, avgHealPotUsed = ?, totalHealMates = ?, avgHealMates = ?, maxTimeNoDeath = ?, totalTimeNoDeath = ?, avgTimeNoDeath = ?, maxSneakTime = ?, totalSneakTime = ?, avgSneakTime = ? WHERE uuid = ?");
				rs.setInt(1, totalGameSeason + totalGameLastGame);
				rs.setInt(2, totalGameNephiSeason + totalGameNephiLastGame);
				rs.setInt(3, totalGameSeraphSeason + totalGameSeraphLastGame);
				rs.setInt(4, totalGameWinSeason + totalGameWinLastGame);
				rs.setInt(5, totalGameWinNephiSeason + totalGameWinNephiLastGame);
				rs.setInt(6, totalGameWinSeraphSeason + totalGameWinSeraphLastGame);
				rs.setInt(7, totalGameLostSeason + totalGameLostLastGame);
				rs.setInt(8, totalGameLostNephiSeason + totalGameLostNephiLastGame);
				rs.setInt(9, totalGameLostSeraphSeason + totalGameLostSeraphLastGame);
				rs.setInt(10, totalRoundSeason + totalRoundLastGame);
				rs.setInt(11, totalRoundNephiSeason + totalRoundNephiLastGame);
				rs.setInt(12, totalRoundSeraphSeason + totalRoundSeraphLastGame);
				rs.setInt(13, totalRoundWinSeason + totalRoundWinLastGame);
				rs.setInt(14, totalRoundWinNephiSeason + totalRoundWinNephiLastGame);
				rs.setInt(15, totalRoundWinSeraphSeason + totalRoundWinSeraphLastGame);
				rs.setInt(16, totalRoundLostSeason + totalRoundLostLastGame);
				rs.setInt(17, totalRoundLostNephiSeason + totalRoundLostNephiLastGame);
				rs.setInt(18, totalRoundLostSeraphSeason + totalRoundLostSeraphLastGame);
				rs.setInt(19, totalMVPSeason + totalMVPLastGame);
				rs.setInt(20, totalMVPNephiSeason + totalMVPNephiLastGame);
				rs.setInt(21, totalMVPSeraphSeason + totalMVPSeraphLastGame);
				rs.setInt(22, totalFlagTakenSeason + totalFlagTakenLastGame);
				rs.setInt(23, totalFlagCaptureSeason + totalFlagCaptureLastGame);
				rs.setInt(24, totalFlagDropSeason + totalFlagDropLastGame);
				rs.setInt(25, totalFlagBackSeason + totalFlagBackLastGame);
				rs.setInt(26, totalPlayerKilledSeason + totalPlayerKilledLastGame);
				rs.setInt(27, totalDeathsSeason + totalDeathsLastGame);
				rs.setInt(28, totalMateKillsSeason + totalMateKillsLastGame);
				rs.setInt(29, totalPerfectSeason + totalPerfectLastGame);
				rs.setInt(30, totalPerfDefSeason + totalPerfDefLastGame);
				rs.setInt(31, totalBlitzSeason + totalBlitzLastGame);
				rs.setInt(32, maxDealtDmgSeason + maxDealtDmgLastGame);
				rs.setInt(33, totalDealtDmgSeason + totalDealtDmgLastGame);
				rs.setInt(34, (totalDealtDmgSeason + totalDealtDmgLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(35, maxTakenDmgSeason + maxTakenDmgLastGame);
				rs.setInt(36, totalTakenDmgSeason + totalTakenDmgLastGame);
				rs.setInt(37, (totalTakenDmgSeason + totalTakenDmgLastGame) / (totalDeathsSeason + totalDeathsLastGame));
				rs.setInt(38, totalSwordSwingSeason + totalSwordSwingLastGame);
				rs.setInt(39, totalSwordHitsSeason + totalSwordHitsLastGame);
				rs.setInt(40, (totalSwordSwingSeason + totalSwordSwingLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(41, ((totalSwordHitsSeason + totalSwordHitsLastGame) / (totalSwordSwingSeason + totalSwordSwingLastGame))*100);
				rs.setInt(42, totalAxeSwingSeason + totalAxeSwingLastGame);
				rs.setInt(43, totalAxeHitsSeason + totalAxeHitsLastGame);
				rs.setInt(44, (totalAxeSwingSeason + totalAxeSwingLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(45, ((totalAxeHitsSeason + totalAxeHitsLastGame) / (totalAxeSwingSeason + totalAxeSwingLastGame))*100 );
				rs.setInt(46, totalArrowShotsSeason + totalArrowShotsLastGame);
				rs.setInt(47, totalArrowHitsSeason + totalArrowHitsLastGame);
				rs.setInt(48, (totalArrowShotsSeason + totalArrowShotsLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(49, ((totalArrowHitsSeason + totalArrowHitsLastGame) / (totalArrowShotsSeason + totalArrowShotsLastGame))*100);
				rs.setInt(50, totalHealPotUsedSeason + totalHealPotUsedLastGame);
				rs.setInt(51, (totalHealPotUsedSeason + totalHealPotUsedLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(52, totalHealMatesSeason + totalHealMatesLastGame);
				rs.setInt(53, (totalHealMatesSeason + totalHealMatesLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setInt(54, maxTimeNoDeathSeason + maxTimeNoDeathLastGame);
				rs.setInt(55, totalTimeNoDeathSeason + totalTimeNoDeathLastGame);
				rs.setInt(56, (totalTimeNoDeathSeason + totalTimeNoDeathLastGame) / (totalDeathsSeason + totalDeathsLastGame));
				rs.setInt(57, maxSneakTimeSeason + maxSneakTimeLastGame);
				rs.setInt(58, totalSneakTimeSeason + totalSneakTimeLastGame);
				rs.setInt(59, (totalSneakTimeSeason + totalSneakTimeLastGame) / (totalGameSeason + totalGameLastGame));
				rs.setString(60, p.getUniqueId().toString());
				rs.executeUpdate();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updatePlayerStatsAccessToDatabase(Player p) {

		if (Vars.pStatsSeason.containsKey(p)) {

			PlayerStats dataPS = Vars.pStatsSeason.get(p);

			int access = dataPS.getAccess();			

			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"UPDATE PlayerStatsSeason SET access = ? WHERE uuid = ?");
				rs.setInt(1, access);
				rs.setString(2, p.getUniqueId().toString());
				rs.executeUpdate();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updatePlayerStatsBlacklistToDatabase(Player p) {

		if (Vars.pStatsSeason.containsKey(p)) {

			PlayerStats dataPS = Vars.pStatsSeason.get(p);

			int blacklist = dataPS.getBlacklist();			

			try {
				PreparedStatement rs = SqlConnection.connection.prepareStatement(
						"UPDATE PlayerStatsSeason SET blacklist = ? WHERE uuid = ?");
				rs.setInt(1, blacklist);
				rs.setString(2, p.getUniqueId().toString());
				rs.executeUpdate();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
