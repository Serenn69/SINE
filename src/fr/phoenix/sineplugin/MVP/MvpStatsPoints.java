package fr.phoenix.sineplugin.MVP;

import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class MvpStatsPoints {

	public static int totalMvpPoints, totalFlagCapturePoints, totalFlagBackPoints, totalFlagTakenPoints, totalFlagDropPoints,
	totalKillsPoints, totalHealMatesPoints, totalTakenDmgPoints, totalTimeNoDeathPoints, totalSneakTimePoints, totalVoteMvpPoints;
	
	public int totalPlayerStatsPoints(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		int totalFlagCapture = dataPS.getTotalFlagCapture();
		totalFlagCapturePoints = totalFlagCapture * 15;
		if (totalFlagCapturePoints < 0)
			totalFlagCapturePoints = 0;

		int totalFlagBack = dataPS.getTotalFlagBack();
		totalFlagBackPoints = totalFlagBack * 8;
		if (totalFlagBackPoints < 0)
			totalFlagBackPoints = 0;

		int totalFlagTaken = dataPS.getTotalFlagTaken();
		totalFlagTakenPoints = totalFlagTaken * 12;
		if (totalFlagTakenPoints < 0)
			totalFlagTakenPoints = 0;

		int totalFlagDrop = dataPS.getTotalFlagDrop();
		totalFlagDropPoints = totalFlagDrop * 8;
		if (totalFlagDropPoints < 0)
			totalFlagDropPoints = 0;

		int totalKills = dataPS.getTotalPlayersKilled();
		int totalDeaths = dataPS.getTotalDeaths();
		totalKillsPoints = (totalKills * 2) - (totalDeaths * 1);
		if (totalKillsPoints < 0)
			totalKillsPoints = 0;

		int totalHealMates = dataPS.getTotalHealMates();
		totalHealMatesPoints = totalHealMates * 2;
		if (totalHealMatesPoints < 0)
			totalHealMatesPoints = 0;

		int totalTakenDmg = dataPS.getTotalTakenDmg();
		totalTakenDmgPoints = totalTakenDmg / 5;
		if (totalTakenDmgPoints < 0)
			totalTakenDmgPoints = 0;

		int totalTimeNoDeath = dataPS.getTotalTimeNoDeath();
		totalTimeNoDeathPoints = totalTimeNoDeath / 10;
		if (totalTimeNoDeathPoints < 0)
			totalTimeNoDeathPoints = 0;

		int totalSneakTime = dataPS.getTotalSneakTime();
		totalSneakTimePoints = totalSneakTime / 20;
		if (totalSneakTimePoints < 0)
			totalSneakTimePoints = 0;
		
		totalVoteMvpPoints = dataPS.getVoteMVP();

		totalMvpPoints = totalFlagCapturePoints + totalFlagBackPoints + totalFlagTakenPoints + totalFlagDropPoints
				+ totalKillsPoints + totalHealMatesPoints + totalTakenDmgPoints + totalTimeNoDeathPoints
				+ totalSneakTimePoints + totalVoteMvpPoints;
		
		return totalMvpPoints;
	}
	
}
