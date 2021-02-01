package fr.phoenix.sineplugin.MVP;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class MvpHologram {

	double loc = 0;
	ArmorStand asMVP1, asMVP2, asMVP3, asMVP4, asMVP5, asMVP6, asMVP7, asMVP8, asMVP9, asMVP10, asMVP11;

	public void mvpStats(Player p) {

		PlayerStats dataPS = Vars.pStatsLastGame.get(p);

		String totalTimeNoDeath = new Countdown().secToMin(dataPS.getTotalTimeNoDeath());

		createMvpStatsHolo(asMVP1, loc, "§5\u2B1B §6§lMost Valuable Player Statistics §f: §5\u2B1B");
		loc -= 0.25;
		createMvpStatsHolo(asMVP2, loc, "-----  §d§l" + p.getName() + "  -----");
		loc -= 0.25;
		createMvpStatsHolo(asMVP3, loc, "§6Total Flag Taken §f: §5§o" + dataPS.getTotalFlagTaken());
		loc -= 0.25;
		createMvpStatsHolo(asMVP4, loc, "§6Total Flag Captured §f: §5§o" + dataPS.getTotalFlagCapture());
		loc -= 0.25;
		createMvpStatsHolo(asMVP5, loc, "§6Total Flag Dropped §f: §5§o" + dataPS.getTotalFlagDrop());
		loc -= 0.25;
		createMvpStatsHolo(asMVP6, loc, "§6Total Flag Back to base §f: §5§o" + dataPS.getTotalFlagBack());
		loc -= 0.25;
		createMvpStatsHolo(asMVP7, loc, "§6Total Players Killed §f: §5§o" + dataPS.getTotalPlayersKilled());
		loc -= 0.25;
		createMvpStatsHolo(asMVP8, loc, "§6Total Deaths §f: §5§o" + dataPS.getTotalDeaths());
		loc -= 0.25;
		createMvpStatsHolo(asMVP9, loc, "§6Total Time with no Death §f: §5§o" + totalTimeNoDeath);
		loc -= 0.25;
		createMvpStatsHolo(asMVP10, loc, "-----  §d§lSpec and Caster Votes  -----");
		loc -= 0.25;
		createMvpStatsHolo(asMVP11, loc, "§6Total Votes §f: §5§o" + (dataPS.getVoteMVP() / 50));

	}

	public void createMvpStatsHolo(ArmorStand asMVP, double loc, String mvpName) {

		Location mvpStats = new Location(Tp.mvpStats.getWorld(), Tp.mvpStats.getX(), Tp.mvpStats.getY() + loc,
				Tp.mvpStats.getZ());

		asMVP = (ArmorStand) mvpStats.getWorld().spawnEntity(mvpStats, EntityType.ARMOR_STAND);

		asMVP.setCustomName(mvpName);
		asMVP.setCustomNameVisible(true);
		asMVP.setVisible(false);
		asMVP.setGravity(false);
	}
}
