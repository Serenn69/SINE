package fr.phoenix.sineplugin.phases;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import fr.phoenix.sineplugin.Arena;
import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Flag;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterHotbar;
import fr.phoenix.sineplugin.caster.CasterMenu;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import fr.phoenix.sineplugin.recapGame.GameStatsFile;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;

public class Countdown {

	public static int round;
	public static int launch;
	public static int prep;
	public static int def;
	public static int fight;
	public static int blitz;
	public static int lastTry;

	public static int leftLaunch, leftPrep, leftDef, leftFight, leftBlitz, aBarGolds, leftLastTry;

	public static BossBar cdLaunchBar;
	public static BossBar cdPrepBar;
	public static BossBar cdDefBar;
	public static BossBar cdFightBar;
	public static BossBar cdCapturBar;
	public static BossBar cdBlitzBar;
	public static BossBar cdLastTry;

	// Loc lamp cancel launch
	Block lamp = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("hall.lamp.x"), Files.coordConfig.getInt("hall.lamp.y"),
			Files.coordConfig.getInt("hall.lamp.z"));

	// Method cast "i" in countdown
	public String secToMin(int i) {

		if (i == -1 || i == -2 || i == -5) {
			String u = "§cUnlim.";
			return u;
		} else {
			int ms = i / 60;
			int ss = i % 60;
			String m = (ms < 10 ? "0" : "") + ms;
			String s = (ss < 10 ? "0" : "") + ss;
			String f = m + ":" + s;
			return f;
		}
	}

	// Game launching countdown
	public void countDownLaunch(int cd) {

		leftLaunch = cd;

		// Create launch countbar
		cdLaunchBar = Bukkit.createBossBar("§6Game begins in §2" + secToMin(cd), BarColor.GREEN, BarStyle.SEGMENTED_10,
				new BarFlag[0]);
		for (Player p : Bukkit.getOnlinePlayers()) {
			cdLaunchBar.addPlayer(p);
		}

		// Light cancel lamp and game is cancelable
		lamp.setType(Material.REDSTONE_BLOCK);
		Files.config.set("options.cancel", 1);

		// Begin of countdown task loop
		launch = Bukkit.getScheduler().scheduleSyncRepeatingTask((Main.getInstance()), new Runnable() {

			double d = (double) cd / cd;

			public void run() {
				d -= 0.1;
				leftLaunch--;

				if (leftLaunch <= cd && leftLaunch > 4) {
					cdLaunchBar.setTitle("§6Game begins in §2" + secToMin(leftLaunch));
					cdLaunchBar.setProgress(d);
				}
				if (leftLaunch == 4) {
					lamp.setType(Material.AIR);
					Files.config.set("options.cancel", 0);
					for (Player p : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&4§lArena loading...") + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
					}
				}
				if (leftLaunch == 3) {
					new Arena().loadArena(); // load arena
					if (Files.config.getString("settings.game.time").equalsIgnoreCase("&9Time : &cNIGHT")) {
						for (String pNC : Vars.pNameCaster) {
							Player pC = Bukkit.getPlayerExact(pNC);
							pC.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
						}
						for (String pNC : Vars.pNameSpec) {
							Player pC = Bukkit.getPlayerExact(pNC);
							pC.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
						}
					}
				}
				if (leftLaunch <= 4 && leftLaunch > 0) {

					cdLaunchBar.setTitle("§6Game begins in §c" + secToMin(leftLaunch));
					cdLaunchBar.setProgress(d);
					cdLaunchBar.setColor(BarColor.RED);

				}
				if (leftLaunch == 0) {
					new GameStatsFile().createGameFile();
					d = 0;
					Main.getInstance().getServer().getScheduler().cancelTask(launch);
					cdLaunchBar.removeAll();
					
					for (String pN : Vars.pNameTeamOne.keySet()) {
						Player p = Bukkit.getPlayerExact(pN);
						new PlayerStats().createProfileLastGame(p);
					}
					for (String pN : Vars.pNameTeamTwo.keySet()) {
						Player p = Bukkit.getPlayerExact(pN);
						new PlayerStats().createProfileLastGame(p);
					}
					for (String pN : Vars.pNameCaster) {
						Player p = Bukkit.getPlayerExact(pN);
						p.sendMessage("§0[§4SINE§0] §7§oAll players profile for this game has been created !");
					}
					

					countDownPrep(Files.config.getInt("options.prep"));
				}
			}
		}, 20, 20);
	}

	// Preparation phase countdown
	public void countDownPrep(int iCD) {

		round++; // increment round

		String i = "one";

		for (Player p : Bukkit.getOnlinePlayers()) {

			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				p.teleport(Tp.oShop);
			}
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				p.teleport(Tp.tShop);
			}
			if (Vars.pNameCaster.contains(p.getName())) {
				p.teleport(Tp.casterShop);
				CasterMenu menu = new CasterMenu(54, "");
				menu.createCasterChestMenu(p, "");
				new CasterHotbar().casterStuffMenu(p, 1);
			}
			if (Vars.pNameSpec.contains(p.getName())) {
				if (i.equals("one")) {
					p.teleport(Tp.oShop);
					new CasterHotbar().specStuffMenu(p, 1);
					i = "two";
				}
				if (i.equals("two")) {
					p.teleport(Tp.tShop);
					new CasterHotbar().specStuffMenu(p, 1);
					i = "one";
				}
			}
		}

		Vars.sineBoard.clearSlot(DisplaySlot.SIDEBAR);
		new Begin().createTrackerTeams();
		new Vars().resetStatsVars();

		new GameStatsFile().addNewRound();

		if (round <= 1) {
			Vars.oneGoldsTotal = Files.config.getInt("options.golds") * Files.config.getInt("options.begingold");
			Vars.twoGoldsTotal = Files.config.getInt("options.golds") * Files.config.getInt("options.begingold");
		}

		new Begin().displayTeamsGolds(); // display golds teams

		leftPrep = iCD;

		// create preparation phase countbar
		cdPrepBar = Bukkit
				.createBossBar(
						"§b" + Files.setsNameConfig.getString("settings.timers.name.b") + " left §f:§2 " + secToMin(iCD)
								+ "  §6-  §dRound §f: §c" + round,
						BarColor.GREEN, BarStyle.SEGMENTED_20, new BarFlag[0]);

		for (Player p : Bukkit.getOnlinePlayers()) {

			cdPrepBar.addPlayer(p);
		}
		// Begin of countdown task loop
		prep = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double d = (double) iCD / iCD;
			double id = (double) 1 / iCD;

			public void run() {
				d -= id;
				leftPrep--;

				if (leftPrep <= iCD && leftPrep > 5) {
					cdPrepBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.b") + " left §f:§2 "
							+ secToMin(leftPrep) + "  §6-  §dRound §f: §c" + round);
					cdPrepBar.setProgress(d);
				}
				if (leftPrep <= 5 && leftPrep > 0) {
					cdPrepBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.b") + " left §f:§4 "
							+ secToMin(leftPrep) + "  §6-  §dRound §f: §c" + round);
					cdPrepBar.setProgress(d);
					cdPrepBar.setColor(BarColor.RED);
				}
				if (leftPrep == 0) {
					d = 0;
					Main.getInstance().getServer().getScheduler().cancelTask(prep);
					cdPrepBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.b") + " left §f:§2 "
							+ secToMin(leftPrep) + "  §6-  §dRound §f: §c" + round);
					cdPrepBar.removeAll();
					countDownDef(Files.config.getInt("options.def"));
				}
				if (leftPrep == -2) {
					Main.getInstance().getServer().getScheduler().cancelTask(prep);
					cdPrepBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.b") + " left §f:§2 "
							+ secToMin(leftPrep) + "  §6-  §dRound §f: §c" + round);
				}
			}
		}, 20, 20);
	}

	// Defense phase countdown
	public void countDownDef(int iCD) {

		for (Player p : Bukkit.getOnlinePlayers()) {

			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				if (Vars.whoBegin.equals("OaTd")) {
					p.teleport(Tp.oAtk);
				}
				if (Vars.whoBegin.equals("OdTa")) {
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
						p.teleport(Tp.oDefS);
					} else if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
						p.teleport(Tp.oDefL);
					}
				}
			}
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				if (Vars.whoBegin.equals("OdTa")) {
					p.teleport(Tp.tAtk);
				}
				if (Vars.whoBegin.equals("OaTd")) {
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
						p.teleport(Tp.tDefS);
					} else if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
						p.teleport(Tp.tDefL);
					}
				}
			}
			if (Vars.pNameCaster.contains(p.getName())) {
				if (Vars.whoBegin.equals("OaTd")) {
					p.teleport(Tp.casterFigO);
				}
				if (Vars.whoBegin.equals("OdTa")) {
					p.teleport(Tp.casterFigT);
				}

			}
			String i = "one";
			if (Vars.pNameSpec.contains(p.getName())) {
				if (i.equals("one")) {
					if (Vars.whoBegin.equals("OaTd")) {
						p.teleport(Tp.oAtk);
						i = "two";
					}
					if (Vars.whoBegin.equals("OdTa")) {
						if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
							p.teleport(Tp.oDefS);
							i = "two";
						} else if (Files.config.getString("settings.game.size")
								.equalsIgnoreCase("&9Map Size : &cLARGE")) {
							p.teleport(Tp.oDefL);
							i = "two";
						}
					}
				}
				if (i.equals("two")) {
					if (Vars.whoBegin.equals("OdTa")) {
						p.teleport(Tp.tAtk);
						i = "one";
					}
					if (Vars.whoBegin.equals("OaTd")) {
						if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
							p.teleport(Tp.tDefS);
							i = "one";
						} else if (Files.config.getString("settings.game.size")
								.equalsIgnoreCase("&9Map Size : &cLARGE")) {
							p.teleport(Tp.tDefL);
							i = "one";
						}
					}
				}
			}
		}

		new Flag().createBanner(); // set flag on arena
		new Arena().loadAtkBase(); // load atk base
		new GameStatsFile().addBuys(); // add teams buys in game stats file

		leftDef = iCD;

		// create defense phase countbar
		cdDefBar = Bukkit
				.createBossBar(
						"§b" + Files.setsNameConfig.getString("settings.timers.name.a") + " left §f:§2 " + secToMin(iCD)
								+ "  §6-  §dRound §f: §c" + round,
						BarColor.GREEN, BarStyle.SEGMENTED_20, new BarFlag[0]);

		// teleport players is team one, team two, caster, spec and if arena is
		// small or large
		for (Player p : Bukkit.getOnlinePlayers()) {

			cdDefBar.addPlayer(p);
		}

		// begin countdown task loop
		def = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double d = (double) iCD / iCD;
			double id = (double) 1 / iCD;

			public void run() {
				d -= id;
				leftDef--;

				// Back defense players in base if is too far of flag in defense
				// phase
				World w = Bukkit.getWorld(Files.setsNameConfig.getString("world.name"));
				Random r = new Random();
				Location newLocS = new Location(w, (Flag.bannerLocBase.getX() - 6) + r.nextInt(12),
						Flag.bannerLocBase.getY(), (Flag.bannerLocBase.getZ() - 6) + r.nextInt(12), 180, 0);
				Location newLocL = new Location(w, (Flag.bannerLocBase.getX() - 10) + r.nextInt(20),
						Flag.bannerLocBase.getY(), (Flag.bannerLocBase.getZ() - 10) + r.nextInt(20), 180, 0);

				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
					if (Vars.whoBegin.equals("OaTd")) {
						for (String pn : Vars.pNameTeamTwo.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() >= -61 || p.getLocation().getBlockX() <= -79
									|| p.getLocation().getBlockZ() >= -6 || p.getLocation().getBlockZ() <= -24) {
								p.teleport(newLocS);
							}
						}
					}
					if (Vars.whoBegin.equals("OdTa")) {
						for (String pn : Vars.pNameTeamOne.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() <= 61 || p.getLocation().getBlockX() >= 79
									|| p.getLocation().getBlockZ() >= -6 || p.getLocation().getBlockZ() <= -24) {
								p.teleport(newLocS);
							}
						}
					}
				}
				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
					if (Vars.whoBegin.equals("OaTd")) {
						for (String pn : Vars.pNameTeamTwo.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() >= -57 || p.getLocation().getBlockX() <= -83
									|| p.getLocation().getBlockZ() >= 8 || p.getLocation().getBlockZ() <= -18) {
								p.teleport(newLocL);
							}
						}
					}
					if (Vars.whoBegin.equals("OdTa")) {
						for (String pn : Vars.pNameTeamOne.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() <= 57 || p.getLocation().getBlockX() >= 83
									|| p.getLocation().getBlockZ() >= 8 || p.getLocation().getBlockZ() <= -18) {
								p.teleport(newLocL);
							}
						}
					}
				}

				if (leftDef <= iCD && leftDef > 5) {
					cdDefBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.a") + " left §f:§2 "
							+ secToMin(leftDef) + "  §6-  §dRound §f: §c" + round);
					cdDefBar.setProgress(d);
				}
				if (leftDef <= 5 && leftDef > 0) {
					cdDefBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.a") + " left §f:§4 "
							+ secToMin(leftDef) + "  §6-  §dRound §f: §c" + round);
					cdDefBar.setProgress(d);
					cdDefBar.setColor(BarColor.RED);
				}
				if (leftDef == 0) {
					d = 0;
					Main.getInstance().getServer().getScheduler().cancelTask(def);
					leftDef = -5;
					cdDefBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.a") + " left §f:§2 "
							+ secToMin(leftDef) + "  §6-  §dRound §f: §c" + round);
					cdDefBar.removeAll(); // remove defense phase countbar for
											// all
					new Arena().clearAtkBase(); // clear atk base
					new Fight().beginFightMsg(); // display begin fight message
					countDownFight(Files.config.getInt("options.fight")); // launch
																			// fight
																			// phase
																			// countbar
				}
				if (leftDef == -2) {
					Main.getInstance().getServer().getScheduler().cancelTask(def);
					leftDef = -5;
					cdDefBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.a") + " left §f:§2 "
							+ secToMin(leftDef) + "  §6-  §dRound §f: §c" + round);
					cdDefBar.removeAll();
					new Arena().clearAtkBase(); // clear atk base
					new Fight().beginFightMsg(); // display begin fight message
					countDownFight(Files.config.getInt("options.fight"));
				}
			}
		}, 20, 20);
	}

	// Fight phase countdown
	public void countDownFight(int iCD) {

		leftFight = iCD;

		// create fight phase countbar
		cdFightBar = Bukkit
				.createBossBar(
						"§b" + Files.setsNameConfig.getString("settings.timers.name.c") + " left §f:§2 " + secToMin(iCD)
								+ "  §6-  §dRound §f: §c" + round,
						BarColor.GREEN, BarStyle.SEGMENTED_20, new BarFlag[0]);

		for (Player p : Bukkit.getOnlinePlayers()) {

			cdFightBar.addPlayer(p);
			p.setStatistic(Statistic.SNEAK_TIME, 0);
			p.setStatistic(Statistic.DAMAGE_DEALT, 0);
			p.setStatistic(Statistic.DAMAGE_TAKEN, 0);
			p.setStatistic(Statistic.TIME_SINCE_DEATH, 0);
		}

		onBlitz(120);

		// begin countdown task loop
		fight = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double d = (double) iCD / iCD;
			double id = (double) 1 / iCD;

			public void run() {
				d -= id;
				leftFight--;

				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
					if (Vars.whoBegin.equals("OaTd")) {
						for (String pn : Vars.pNameTeamOne.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() < -62 || p.getLocation().getBlockX() > -78
									|| p.getLocation().getBlockZ() < -7 || p.getLocation().getBlockZ() > -23) {
								Vars.twoPerfDef = 0;
							}
						}
					}
					if (Vars.whoBegin.equals("OdTa")) {
						for (String pn : Vars.pNameTeamTwo.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() > 62 || p.getLocation().getBlockX() < 78
									|| p.getLocation().getBlockZ() < -7 || p.getLocation().getBlockZ() > -23) {
								Vars.onePerfDef = 0;
							}
						}
					}
				}
				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
					if (Vars.whoBegin.equals("OaTd")) {
						for (String pn : Vars.pNameTeamOne.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() < -58 || p.getLocation().getBlockX() > -82
									|| p.getLocation().getBlockZ() < 7 || p.getLocation().getBlockZ() > -17) {
								Vars.twoPerfDef = 0;
							}
						}
					}
					if (Vars.whoBegin.equals("OdTa")) {
						for (String pn : Vars.pNameTeamTwo.keySet()) {
							Player p = Bukkit.getPlayerExact(pn);
							if (p.getLocation().getBlockX() > 58 || p.getLocation().getBlockX() < 82
									|| p.getLocation().getBlockZ() < 7 || p.getLocation().getBlockZ() > -17) {
								Vars.onePerfDef = 0;
							}
						}
					}
				}

				if (leftFight <= iCD && leftFight > 30) {
					cdFightBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.c") + " left §f:§2 "
							+ secToMin(leftFight) + "  §6-  §dRound §f: §c" + round);
					cdFightBar.setProgress(d);
				}
				if (leftFight <= 30 && leftFight > 0) {
					cdFightBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.c") + " left §f:§4 "
							+ secToMin(leftFight) + "  §6-  §dRound §f: §c" + round);
					cdFightBar.setProgress(d);
					cdFightBar.setColor(BarColor.RED);
				}
				if (leftFight == 0) {
					d = 0;

					if (Main.getInstance().getServer().getScheduler().isQueued(Flag.oneTakeFlag)) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.oneTakeFlag);
					}
					if (Main.getInstance().getServer().getScheduler().isQueued(Flag.twoTakeFlag)) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.twoTakeFlag);
					}
					if (Main.getInstance().getServer().getScheduler().isQueued(Flag.captur)) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.captur);
					}
					if (Main.getInstance().getServer().getScheduler().isQueued(blitz)) {
						Main.getInstance().getServer().getScheduler().cancelTask(blitz);
						if (cdBlitzBar.isVisible()) {
							cdBlitzBar.removeAll();
						}
						if (Vars.whoBegin.equals("OaTd")) {
							Vars.oneBlitz = 0;
						}
						if (Vars.whoBegin.equals("OdTa")) {
							Vars.twoBlitz = 0;
						}
					}
					if (Main.getInstance().getServer().getScheduler().isQueued(lastTry)) {
						Main.getInstance().getServer().getScheduler().cancelTask(lastTry);
						if (cdLastTry.isVisible()) {
							cdLastTry.removeAll();
						}
					}

					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getInventory().getHelmet().getType().equals(Material.RED_BANNER) || p.getInventory().getHelmet().getType().equals(Material.LIME_BANNER)) {
							new Flag().replaceHelmet(p);
						}
					}
					leftFight = -5;
					cdFightBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.c") + " left §f:§4 "
							+ secToMin(leftFight) + "  §6-  §dRound §f: §c" + round);
					Vars.draw = 1;
					Vars.totalDraw++;
					new Victory().updateScore("draw");
				}
				if (leftFight == -2) {
					leftFight = -5;
					cdFightBar.setTitle("§b" + Files.setsNameConfig.getString("settings.timers.name.c") + " left §f:§2 "
							+ secToMin(leftFight) + "  §6-  §dRound §f: §c" + round);
				}
			}
		}, 20, 20);
	}

	public void onBlitz(int cd) {

		leftBlitz = cd;

		if (Vars.whoBegin.equals("OaTd")) {
			cdBlitzBar = Bukkit.createBossBar("§4Blitzkrieg §6ends in §2" + secToMin(cd), BarColor.GREEN,
					BarStyle.SEGMENTED_10, new BarFlag[0]);
			Vars.oneBlitz = 1;
		}
		if (Vars.whoBegin.equals("OdTa")) {
			cdBlitzBar = Bukkit.createBossBar("§aBlitzkrieg §6ends in §2" + secToMin(cd), BarColor.GREEN,
					BarStyle.SEGMENTED_10, new BarFlag[0]);
			Vars.twoBlitz = 1;
		}
		for (String pN : Vars.pNameCaster) {
			Player p = Bukkit.getPlayerExact(pN);
			cdBlitzBar.addPlayer(p);
		}

		blitz = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double d = (double) cd / cd;
			double id = (double) 1 / cd;

			public void run() {
				d -= id;
				leftBlitz--;

				if (leftBlitz <= cd && leftBlitz > 20) {
					if (Vars.whoBegin.equals("OaTd")) {
						cdBlitzBar.setTitle("§4Blitzkrieg §6ends in §2" + secToMin(leftBlitz));
					}
					if (Vars.whoBegin.equals("OdTa")) {
						cdBlitzBar.setTitle("§aBlitzkrieg §6ends in §2" + secToMin(leftBlitz));
					}
					cdBlitzBar.setProgress(d);
				}
				if (leftBlitz <= 30 && leftBlitz > 0) {
					if (d < 0)
						d = 0;
					if (Vars.whoBegin.equals("OaTd")) {
						cdBlitzBar.setTitle("§4Blitzkrieg §6ends in §4" + secToMin(leftBlitz));
					}
					if (Vars.whoBegin.equals("OdTa")) {
						cdBlitzBar.setTitle("§aBlitzkrieg §6ends in §4" + secToMin(leftBlitz));
					}
					cdBlitzBar.setProgress(d);
					cdBlitzBar.setColor(BarColor.RED);
				}
				if (leftBlitz == 0) {
					d = 0;
					Main.getInstance().getServer().getScheduler().cancelTask(blitz);
					if (Vars.whoBegin.equals("OaTd")) {
						Vars.oneBlitz = 0;
					}
					if (Vars.whoBegin.equals("OdTa")) {
						Vars.twoBlitz = 0;
					}
					cdBlitzBar.removeAll();
				}
			}
		}, 20, 20);
	}

	public void allDefenseDeath(int cd, String team) {

		leftLastTry = cd;

		if (team.equals("one")) {
			cdLastTry = Bukkit.createBossBar("§cLast Try §6ends in §2" + secToMin(cd), BarColor.GREEN,
					BarStyle.SEGMENTED_10, new BarFlag[0]);
		}
		if (team.equals("two")) {
			cdLastTry = Bukkit.createBossBar("§aLast Try §6ends in §2" + secToMin(cd), BarColor.GREEN,
					BarStyle.SEGMENTED_10, new BarFlag[0]);
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			cdLastTry.addPlayer(p);
		}

		lastTry = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double d = (double) cd / cd;
			double id = (double) 1 / cd;

			public void run() {
				d -= id;
				leftLastTry--;

				if (leftLastTry <= cd && leftLastTry > 10) {
					if (team.equals("one")) {
						cdLastTry.setTitle("§cLast Try §6ends in §2" + secToMin(leftLastTry));
					}
					if (team.equals("two")) {
						cdLastTry.setTitle("§aLast Try §6ends in §2" + secToMin(leftLastTry));
					}
					cdLastTry.setProgress(d);
				}
				if (leftLastTry <= 10 && leftLastTry > 0) {
					if (d < 0)
						d = 0;
					if (team.equals("one")) {
						cdLastTry.setTitle("§cLast Try §6ends in §4" + secToMin(leftLastTry));
					}
					if (team.equals("two")) {
						cdLastTry.setTitle("§aLast Try §6ends in §4" + secToMin(leftLastTry));
					}
					cdLastTry.setProgress(d);
					cdLastTry.setColor(BarColor.RED);
				}
				if (leftLastTry == 0) {
					d = 0;
					Main.getInstance().getServer().getScheduler().cancelTask(lastTry);
					if (cdLastTry.isVisible()) {
						cdLastTry.removeAll();
					}

					if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.blitz)) {
						Bukkit.broadcastMessage("CD Blitz is currently running");
						Main.getInstance().getServer().getScheduler().cancelTask(Countdown.blitz);
						if (Countdown.cdBlitzBar.isVisible()) {
							Countdown.cdBlitzBar.removeAll();
						}
						if (Vars.whoBegin.equals("OaTd")) {
							Vars.oneBlitz = 0;
						}
						if (Vars.whoBegin.equals("OdTa")) {
							Vars.twoBlitz = 0;
						}
					}
					if (Main.getInstance().getServer().getScheduler().isQueued(Flag.captur)) {
						Bukkit.broadcastMessage("Flag.captur is currently running");
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.captur);
					}
					if (team.equals("one")) {
						for (String pN : Vars.pNameTeamOne.keySet()) {
							Player p = Bukkit.getPlayerExact(pN);
							if (p.getInventory().getHelmet().getType().equals(Material.RED_BANNER) || p.getInventory().getHelmet().getType().equals(Material.LIME_BANNER)) {
								Vars.draw = 1;
								Vars.totalDraw++;
								new Victory().updateScore("draw");
							} else {
								Vars.oneLoseRd = 1;
								Vars.totalOneLoseRd++;
								Vars.twoWinRd = 1;
								Vars.totalTwoWinRd++;
								new Victory().updateScore("two");
							}
						}
					}
					if (team.equals("two")) {
						for (String pN : Vars.pNameTeamTwo.keySet()) {
							Player p = Bukkit.getPlayerExact(pN);
							if (p.getInventory().getHelmet().getType().equals(Material.RED_BANNER) || p.getInventory().getHelmet().getType().equals(Material.LIME_BANNER)) {
								Vars.draw = 1;
								Vars.totalDraw++;
								new Victory().updateScore("draw");
							} else {
								Vars.twoLoseRd = 1;
								Vars.totalTwoLoseRd++;
								Vars.oneWinRd = 1;
								Vars.totalOneWinRd++;
								new Victory().updateScore("one");
							}
						}
					}
				}
			}
		}, 20, 20);
	}

}
