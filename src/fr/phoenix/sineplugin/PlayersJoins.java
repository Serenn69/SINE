package fr.phoenix.sineplugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.phoenix.sineplugin.holograms.Holograms;
import fr.phoenix.sineplugin.news.NewsManager;
import fr.phoenix.sineplugin.phases.Victory;

public class PlayersJoins implements Listener {

	private int holoScoreTest;

	@EventHandler
	public void onJoinServ(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		p.getInventory().clear();
		Vars.sineBoard.resetScores(p.getName());
		if (Vars.teamOne != null) {
			if (Vars.teamOne.getEntries().contains(p.getName())) {
				Vars.teamOne.removeEntry(p.getName());
			}
		}
		if (Vars.teamTwo != null) {
			if (Vars.teamTwo.getEntries().contains(p.getName())) {
				Vars.teamTwo.removeEntry(p.getName());
			}
		}
		if (!p.isOp() && !p.getName().equals("Serenny")) {
			p.setGameMode(GameMode.ADVENTURE);
		}

		NewsManager.newsBar.addPlayer(p);

		// Welcome Hologram
		ArrayList<String> text = new ArrayList<String>(
				Arrays.asList("§f----------------", "§6Welcome on S.I.N.E§f§o\u00A9", "§f---------------------------",
						"§dStrategy and Teams PvP game", "§f---------------------", "§0§oa Serenn / MCPG event"));
		Holograms.holoWelc = new Holograms(text, Tp.welcHolo, Holograms.welcList);
		Holograms.holoWelc.showPlayer(p, Holograms.welcList);

	}

	public static int wings, crown, victory;
	private Location origin;

	public void crownTest() {

		Victory.crownParticle = Particle.TOTEM;
		Victory.crownPoints = 24;

		for (Player p : Bukkit.getOnlinePlayers()) {

			crown = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

				public void run() {
					origin = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2,
							p.getLocation().getZ());

					double radius = 0.352d;

					for (int i = 0; i < Victory.crownPoints; i++) {
						double angle = 2 * Math.PI * i / Victory.crownPoints;
						Location point = origin.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
						p.getWorld().spawnParticle(Victory.crownParticle, point, 6, 0, 0, 0, 0);
					}

				}
			}, 1, 1);
		}
	}

	public void victoryTest() {

		Victory.victoryParticle = Particle.REDSTONE;

		victory = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				for (double x = -5.2; x < 5.2; x += 0.1) {

					DecimalFormat df = new DecimalFormat("#.#");
					String s = df.format(x);
					s = s.replaceAll(",", ".");
					Double i = new Double(s);

					if (i == 0)
						i = 0.0;

					if (Victory.vic22.contains(i)) {
						Location v22 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.victoryParticle, v22, 6, 0, 0, 0, 0);
					}
					if (Victory.vic23.contains(i)) {
						Location v23 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.victoryParticle, v23, 6, 0, 0, 0, 0);
					}
					if (Victory.vic24.contains(i)) {
						Location v24 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.victoryParticle, v24, 6, 0, 0, 0, 0);
					}
					if (Victory.vic25.contains(i)) {
						Location v25 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.victoryParticle, v25, 6, 0, 0, 0, 0);
					}
					if (Victory.vic26.contains(i)) {
						Location v26 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.victoryParticle, v26, 6, 0, 0, 0, 0);
					}
				}
			}
		}, 1, 1);
	}

	public void wingsTest() {

		Victory.wingsParticle = Particle.FLAME;

		wings = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				for (double x = -5.2; x < 5.2; x += 0.1) {

					DecimalFormat df = new DecimalFormat("#.#");
					String s = df.format(x);
					s = s.replaceAll(",", ".");
					Double i = new Double(s);

					if (i == 0)
						i = 0.0;

					if (Victory.line1.contains(i)) {
						Location l1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								131.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l1, 6, 0, 0, 0, 0);
					}
					if (Victory.line2.contains(i)) {
						Location l2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								131.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l2, 6, 0, 0, 0, 0);
					}
					if (Victory.line3.contains(i)) {
						Location l3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l3, 6, 0, 0, 0, 0);
					}
					if (Victory.line4.contains(i)) {
						Location l4 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l4, 6, 0, 0, 0, 0);
					}
					if (Victory.line5.contains(i)) {
						Location l5 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l5, 6, 0, 0, 0, 0);
					}
					if (Victory.line6.contains(i)) {
						Location l6 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								132.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l6, 6, 0, 0, 0, 0);
					}
					if (Victory.line7.contains(i)) {
						Location l7 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l7, 6, 0, 0, 0, 0);
					}
					if (Victory.line8.contains(i)) {
						Location l8 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l8, 6, 0, 0, 0, 0);
					}
					if (Victory.line9.contains(i)) {
						Location l9 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l9, 6, 0, 0, 0, 0);
					}
					if (Victory.line10.contains(i)) {
						Location l10 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								133.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l10, 6, 0, 0, 0, 0);
					}
					if (Victory.line11.contains(i)) {
						Location l11 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l11, 6, 0, 0, 0, 0);
					}
					if (Victory.line12.contains(i)) {
						Location l12 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l12, 6, 0, 0, 0, 0);
					}
					if (Victory.line13.contains(i)) {
						Location l13 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l13, 6, 0, 0, 0, 0);
					}
					if (Victory.line14.contains(i)) {
						Location l14 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								134.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l14, 6, 0, 0, 0, 0);
					}
					if (Victory.line15.contains(i)) {
						Location l15 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l15, 6, 0, 0, 0, 0);
					}
					if (Victory.line16.contains(i)) {
						Location l16 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l16, 6, 0, 0, 0, 0);
					}
					if (Victory.line17.contains(i)) {
						Location l17 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l17, 6, 0, 0, 0, 0);
					}
					if (Victory.line18.contains(i)) {
						Location l18 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								135.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l18, 6, 0, 0, 0, 0);
					}
					if (Victory.line19.contains(i)) {
						Location l19 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l19, 6, 0, 0, 0, 0);
					}
					if (Victory.line20.contains(i)) {
						Location l20 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l20, 6, 0, 0, 0, 0);
					}
					if (Victory.line21.contains(i)) {
						Location l21 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l21, 6, 0, 0, 0, 0);
					}
					if (Victory.line22.contains(i)) {
						Location l22 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								136.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l22, 6, 0, 0, 0, 0);
					}
					if (Victory.line23.contains(i)) {
						Location l23 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l23, 6, 0, 0, 0, 0);
					}
					if (Victory.line24.contains(i)) {
						Location l24 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.25, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l24, 6, 0, 0, 0, 0);
					}
					if (Victory.line25.contains(i)) {
						Location l25 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.5, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l25, 6, 0, 0, 0, 0);
					}
					if (Victory.line26.contains(i)) {
						Location l26 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								137.75, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l26, 6, 0, 0, 0, 0);
					}
					if (Victory.line27.contains(i)) {
						Location l27 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), i,
								138, -80);
						Bukkit.getWorld(Files.setsNameConfig.getString("world.name"))
								.spawnParticle(Victory.wingsParticle, l27, 6, 0, 0, 0, 0);
					}
				}
			}
		}, 15, 15);
	}

	double loc = 0;
	ArmorStand as1, as2, as3, as4, as5, as6, as7, as8, as9, as10, as11, as12;
	ArmorStand asT1, asT2, asT3, asT4, asT5, asT6, asT7, asT8, asT9, asT10, asT11, asT12;

	public void holoScore() {

		holoScoreTest = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			int n = 0;

			public void run() {

				switch (n) {

				case 0:

					createHolo(as1, asT1, loc, "§f---- §4RED §6round golds §f----", "one");
					createHolo(as1, asT1, loc, "§f---- §aGREEN §6round golds §f----", "two");
					loc -= 0.25;
					createHolo(as2, asT2, loc, "§f-----------------------", "one");
					createHolo(as2, asT2, loc, "§f-----------------------", "two");
					loc -= 0.25;

					break;
				case 2:

					Vars.oneLoseRd = 1;
					Vars.twoWinRd = 1;

					if (Vars.oneWinRd == 1) {
						createHolo(as3, asT3, loc,
								"§5\u2B1B §6Round won :       §b" + Files.config.getInt("score.winRd"), "one");
						Vars.oneGoldsRd += Files.config.getInt("score.winRd");
					}
					if (Vars.twoWinRd == 1) {
						createHolo(as3, asT3, loc,
								"§5\u2B1B §6Round won :       §b" + Files.config.getInt("score.winRd"), "two");
						Vars.twoGoldsRd += Files.config.getInt("score.winRd");
					}
					if (Vars.oneLoseRd == 1) {
						createHolo(as3, asT3, loc,
								"§5\u2B1B §6Round lost :            §b" + Files.config.getInt("score.loseRd"), "one");

					}
					if (Vars.twoLoseRd == 1) {
						createHolo(as3, asT3, loc,
								"§5\u2B1B §6Round lost :            §b" + Files.config.getInt("score.loseRd"), "two");

					}
					loc -= 0.25;

					break;
				case 4:

					if (Vars.whoBegin.equals("OaTd")) {
						if (Vars.oneFlagTaken >= 1) {
							createHolo(as4, asT4, loc,
									"§5\u2B1B §6Flag taken :       §b" + Files.config.getInt("score.takeFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.takeFlag");
						}
						if (Vars.oneFlagTaken == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag taken :     §b0", "one");
						}
						if (Vars.twoFlagDrop >= 1) {
							createHolo(as4, asT4, loc,
									"§5\u2B1B §6Flag dropped :     §b" + Files.config.getInt("score.dropFlag"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.dropFlag");
						}
						if (Vars.twoFlagDrop == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag dropped :     §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.oneFlagDrop >= 1) {
							createHolo(as4, asT4, loc,
									"§5\u2B1B §6Flag dropped :     §b" + Files.config.getInt("score.dropFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.dropFlag");
						}
						if (Vars.oneFlagDrop == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag dropped :     §b0", "one");
						}
						if (Vars.twoFlagTaken >= 1) {
							createHolo(as4, asT4, loc,
									"§5\u2B1B §6Flag taken :       §b" + Files.config.getInt("score.takeFlag"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.takeFlag");
						}
						if (Vars.twoFlagTaken == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag taken :     §b0", "two");
						}
					}
					loc -= 0.25;

					break;
				case 6:

					if (Vars.whoBegin.equals("OaTd")) {
						if (Vars.oneFlagCaptur >= 1) {
							createHolo(as5, asT5, loc,
									"§5\u2B1B §6Flag captured :    §b" + Files.config.getInt("score.capturFlag"),
									"one");
							Vars.oneGoldsRd += Files.config.getInt("score.capturFlag");
						}
						if (Vars.oneFlagCaptur == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag captured :  §b0", "one");
						}
						if (Vars.twoFlagBack >= 1) {
							createHolo(as5, asT5, loc,
									"§5\u2B1B §6Flag returned :    §b" + Files.config.getInt("score.backFlag"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.backFlag");
						}
						if (Vars.twoFlagBack == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag returned :    §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.oneFlagBack >= 1) {
							createHolo(as5, asT5, loc,
									"§5\u2B1B §6Flag returned :    §b" + Files.config.getInt("score.backFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.backFlag");
						}
						if (Vars.oneFlagBack == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag returned :    §b0", "one");
						}
						if (Vars.twoFlagCaptur >= 1) {
							createHolo(as5, asT5, loc,
									"§5\u2B1B §6Flag captured :    §b" + Files.config.getInt("score.capturFlag"),
									"two");
							Vars.twoGoldsRd += Files.config.getInt("score.capturFlag");
						}
						if (Vars.twoFlagCaptur == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag captured :  §b0", "two");
						}
					}
					loc -= 0.25;

					break;
				case 8:

					createHolo(as6, asT6, loc, "§5\u2B1B §6Ennemies killed :     §b"
							+ (Files.config.getInt("score.kills") * Vars.oneKills), "one");
					createHolo(as6, asT6, loc, "§5\u2B1B §6Ennemies killed :     §b"
							+ (Files.config.getInt("score.kills") * Vars.twoKills), "two");
					loc -= 0.25;
					Vars.oneGoldsRd += (Files.config.getInt("score.kills") * Vars.oneKills);
					Vars.twoGoldsRd += (Files.config.getInt("score.kills") * Vars.twoKills);

					createHolo(as7, asT7, loc, "§5\u2B1B §6Mates killed :         §b"
							+ (Files.config.getInt("score.mateKills") * Vars.oneMateKills), "one");
					createHolo(as7, asT7, loc, "§5\u2B1B §6Mates killed :         §b"
							+ (Files.config.getInt("score.mateKills") * Vars.twoMateKills), "two");
					loc -= 0.25;
					Vars.oneGoldsRd += (Files.config.getInt("score.mateKills") * Vars.oneMateKills);
					Vars.twoGoldsRd += (Files.config.getInt("score.mateKills") * Vars.twoMateKills);

					break;
				case 10:

					if (Vars.onePerfect == 1) {
						createHolo(as8, asT8, loc,
								"§5\u2B1B §6No deaths :        §b" + Files.config.getInt("score.noDeath"), "one");
						Vars.oneGoldsRd += Files.config.getInt("score.noDeath");
					}
					if (Vars.onePerfect == 0) {
						createHolo(as8, asT8, loc, "§5\u2B1B §6No deaths :           §b0", "one");
					}
					if (Vars.twoPerfect == 1) {
						createHolo(as8, asT8, loc,
								"§5\u2B1B §6No deaths :        §b" + Files.config.getInt("score.noDeath"), "two");
						Vars.twoGoldsRd += Files.config.getInt("score.noDeath");
					}
					if (Vars.twoPerfect == 0) {
						createHolo(as8, asT8, loc, "§5\u2B1B §6No deaths :           §b0", "two");
					}
					loc -= 0.25;

					break;
				case 12:

					if (Vars.whoBegin.equals("OaTd")) {
						if (Vars.oneBlitz == 1) {
							createHolo(as9, asT9, loc,
									"§5\u2B1B §6Blitzkrieg :       §b" + Files.config.getInt("score.blitz"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.blitz");
						}
						if (Vars.oneBlitz == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6Blitzkrieg :        §b0", "one");
						}
						if (Vars.twoPerfDef == 1) {
							createHolo(as9, asT9, loc,
									"§5\u2B1B §6Perfect Def. :  §b" + Files.config.getInt("score.perfDef"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.perfDef");
						}
						if (Vars.twoPerfDef == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6No Perfect Def. :   §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.onePerfDef == 1) {
							createHolo(as9, asT9, loc,
									"§5\u2B1B §6Perfect Def. :  §b" + Files.config.getInt("score.perfDef"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.perfDef");
						}
						if (Vars.onePerfDef == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6No Perfect Def. :   §b0", "one");
						}
						if (Vars.twoBlitz == 1) {
							createHolo(as9, asT9, loc,
									"§5\u2B1B §6Blitzkrieg :       §b" + Files.config.getInt("score.blitz"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.blitz");
						}
						if (Vars.twoBlitz == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6Blitzkrieg :        §b0", "two");
						}
					}
					loc -= 0.25;

					break;
				case 14:

					createHolo(as10, asT10, loc, "§f-----------------------", "one");
					createHolo(as10, asT10, loc, "§f-----------------------", "two");
					loc -= 0.25;

					createHolo(as11, asT11, loc, "§6Total round golds : §b" + Vars.oneGoldsRd, "one");
					createHolo(as11, asT11, loc, "§6Total round golds : §b" + Vars.twoGoldsRd, "two");
					loc -= 0.25;

					createHolo(as12, asT12, loc, "§6Total §4RED §6golds : §b" + (Vars.oneGoldsRd + Vars.oneGoldsTotal),
							"one");
					createHolo(as12, asT12, loc,
							"§6Total §aGREEN §6golds : §b" + (Vars.twoGoldsRd + Vars.twoGoldsTotal), "two");
					Vars.oneGoldsTotal += Vars.oneGoldsRd;
					Vars.twoGoldsTotal += Vars.twoGoldsRd;

					break;
				case 20:

					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

					String killOneScRoomCommand = "kill @e[x=-53,y=66,z=-134,r=2,type=armor_stand]";
					Bukkit.dispatchCommand(console, killOneScRoomCommand);
					String killTwoScRoomCommand = "kill @e[x=54,y=66,z=-134,r=2,type=armor_stand]";
					Bukkit.dispatchCommand(console, killTwoScRoomCommand);
					String killCasterScRoomCommand = "kill @e[x=0,y=27,z=-15,r=3,type=armor_stand]";
					Bukkit.dispatchCommand(console, killCasterScRoomCommand);

					Main.getInstance().getServer().getScheduler().cancelTask(holoScoreTest);
					break;
				}
				n++;
			}
		}, 20, 20);
	}

	public void createHolo(ArmorStand as, ArmorStand asT, double loc, String name, String castLoc) {

		Location asLocCastOne = new Location(Tp.casterOneScRoomHolo.getWorld(), Tp.casterOneScRoomHolo.getX(),
				Tp.casterOneScRoomHolo.getY() + loc, Tp.casterOneScRoomHolo.getZ());
		Location asLocCastTwo = new Location(Tp.casterTwoScRoomHolo.getWorld(), Tp.casterTwoScRoomHolo.getX(),
				Tp.casterTwoScRoomHolo.getY() + loc, Tp.casterTwoScRoomHolo.getZ());

		Location asLocOne = new Location(Tp.oneScRoomHolo.getWorld(), Tp.oneScRoomHolo.getX(),
				Tp.oneScRoomHolo.getY() + loc, Tp.oneScRoomHolo.getZ());
		Location asLocTwo = new Location(Tp.twoScRoomHolo.getWorld(), Tp.twoScRoomHolo.getX(),
				Tp.twoScRoomHolo.getY() + loc, Tp.twoScRoomHolo.getZ());

		if (castLoc.equalsIgnoreCase("one")) {
			as = (ArmorStand) asLocCastOne.getWorld().spawnEntity(asLocCastOne, EntityType.ARMOR_STAND);
			asT = (ArmorStand) asLocOne.getWorld().spawnEntity(asLocOne, EntityType.ARMOR_STAND);
		}
		if (castLoc.equalsIgnoreCase("two")) {
			as = (ArmorStand) asLocCastTwo.getWorld().spawnEntity(asLocCastTwo, EntityType.ARMOR_STAND);
			asT = (ArmorStand) asLocTwo.getWorld().spawnEntity(asLocTwo, EntityType.ARMOR_STAND);
		}

		as.setCustomName(name);
		as.setCustomNameVisible(true);
		as.setVisible(false);
		as.setGravity(false);

		asT.setCustomName(name);
		asT.setCustomNameVisible(true);
		asT.setVisible(false);
		asT.setGravity(false);
	}
}
