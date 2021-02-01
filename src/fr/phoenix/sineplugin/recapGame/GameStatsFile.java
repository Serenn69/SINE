package fr.phoenix.sineplugin.recapGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import fr.phoenix.sineplugin.phases.Countdown;

public class GameStatsFile {

	public static FileConfiguration recapConfig;
	static File recapGameFile;

	static Date date = new Date();
	static String DATE_FORMAT = "dd-MM-yyyy";
	static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	static String time = sdf.format(date);

	private int nbrG = 1;

	public void createGameFile() {

		if (Files.config.getInt("options.begin") == 0)
			Vars.whoBegin = "OaTd";
		else if (Files.config.getInt("options.begin") == 1)
			Vars.whoBegin = "OdTa";

		File pathFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SINE").getDataFolder(),
				File.separator + "GameDatabase");
		recapGameFile = new File(pathFile, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);

		if (!recapGameFile.exists()) {

			try {
				recapConfig.set("******/ Game Results \\******", "x");
				recapConfig.createSection("game");
				recapConfig.set("game.date", time);
				recapConfig.set("game.players", Vars.pNameTeamOne.keySet() + " - " + Vars.pNameTeamTwo.keySet());
				recapConfig.createSection("game.victory");
				recapConfig.set("game.victory.team", "x");
				recapConfig.set("game.victory.players", "x");
				recapConfig.createSection("game.defeat");
				recapConfig.set("game.defeat.team", "x");
				recapConfig.set("game.defeat.players", "x");
				recapConfig.set("game.rounds", 0);
				recapConfig.set("******/ Game Settings \\******", "x");
				recapConfig.createSection("options");
				recapConfig.set("options.rank",
						Files.config.getString("settings.game.rank").replace("&9Game : &c", ""));
				recapConfig.set("options.time",
						Files.config.getString("settings.game.time").replace("&9Time : &c", ""));
				recapConfig.set("options.weather",
						Files.config.getString("settings.game.weather").replace("&9Weather : &c", ""));
				recapConfig.set("options.prep",
						Files.config.getString("settings.game.prep").replace("&9Preparation : &c", ""));
				recapConfig.set("options.def",
						Files.config.getString("settings.game.def").replace("&9Defense : &c", ""));
				recapConfig.set("options.fight",
						Files.config.getString("settings.game.fight").replace("&9Fight : &c", ""));
				recapConfig.set("options.begin", Files.config.getString("settings.game.begin")
						.replace("Game Begin : &c", "").replace("&9", "").replace("&a", "").replace("&f", ""));
				recapConfig.set("options.golds",
						Files.config.getString("settings.game.golds").replace("&9Golds Multiplicator : &c", ""));
				recapConfig.set("options.map", Files.config.getString("settings.game.map").replace("&9Map : &c", ""));
				recapConfig.set("options.size",
						Files.config.getString("settings.game.size").replace("&9Map Size : &c", ""));
				recapConfig.set("options.equi",
						Files.config.getString("settings.game.equi").replace("&9Balance Teams : &c", ""));
				recapConfig.set("options.ff",
						Files.config.getString("settings.game.ff").replace("&9FriendlyFire : &c", ""));
				recapConfig.set("options.invis",
						Files.config.getString("settings.game.invis").replace("&9Invisible Mates : &c", ""));
				recapConfig.set("*******/ Game Finish \\*******", "x");
				recapConfig.set("finish", 0);
				recapConfig.set("******/ Game Statistics \\******", "x");
				recapConfig.createSection("rounds");

				recapConfig.save(recapGameFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (recapGameFile.exists()) {
			try {
				recapConfig.load(recapGameFile);
				if (recapGameFile.getName().equals(time + "_gameStats" + nbrG + ".yml")
						&& recapConfig.getInt("finish") == 1) {
					nbrG++;
					createGameFile();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	public void addNewRound() {

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);
		try {
			recapConfig.set("game.rounds", Countdown.round);
			recapConfig.createSection("rounds." + Countdown.round + ".one");
			recapConfig.createSection("rounds." + Countdown.round + ".two");
			if (Vars.whoBegin.equals("OaTd")) {
				recapConfig.set("rounds." + Countdown.round + ".one.atk", Files.setsNameConfig
						.getString("settings.game.beginOrder.a").replace("&0&l", "").replace("&c&l", ""));
				recapConfig.set("rounds." + Countdown.round + ".two.def", Files.setsNameConfig
						.getString("settings.game.beginOrder.b").replace("&0&l", "").replace("&a&l", ""));
			}
			if (Vars.whoBegin.equals("OdTa")) {
				recapConfig.set("rounds." + Countdown.round + ".one.def", Files.setsNameConfig
						.getString("settings.game.beginOrder.c").replace("&0&l", "").replace("&c&l", ""));
				recapConfig.set("rounds." + Countdown.round + ".two.atk", Files.setsNameConfig
						.getString("settings.game.beginOrder.d").replace("&0&l", "").replace("&a&l", ""));
			}
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addBuys() {

		String keyName = null;
		int keyValue;

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);

		try {
			for (ItemStack isO : CasterMenu.teamOneBuy.keySet()) {
				keyName = isO.getItemMeta().getDisplayName().replace("§o", "");
				keyValue = CasterMenu.teamOneBuy.get(isO);
				if (recapConfig.getString("rounds." + Countdown.round + ".one.buyRd") != null) {
					recapConfig.set("rounds." + Countdown.round + ".one.buyRd", recapConfig.getString("rounds."+Countdown.round+".one.buyRd")+keyName + " x " + keyValue + " / ");
				}
				else {
					recapConfig.set("rounds." + Countdown.round + ".one.buyRd", keyName + " x " + keyValue + " / ");
				}
			}

			for (ItemStack isT : CasterMenu.teamTwoBuy.keySet()) {
				keyName = isT.getItemMeta().getDisplayName().replace("§o", "");
				keyValue = CasterMenu.teamTwoBuy.get(isT);
				if (recapConfig.getString("rounds." + Countdown.round + ".two.buyRd") != null) {
					recapConfig.set("rounds." + Countdown.round + ".two.buyRd", recapConfig.getString("rounds."+Countdown.round+".two.buyRd")+keyName + " x " + keyValue + " / ");
				}
				else {
					recapConfig.set("rounds." + Countdown.round + ".two.buyRd", keyName + " x " + keyValue + " / ");
				}
			}
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void addFightStats() {

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);
		try {
			recapConfig.set("rounds." + Countdown.round + ".draw", Vars.draw);
			recapConfig.set("rounds." + Countdown.round + ".one.kills", Vars.oneKills);
			recapConfig.set("rounds." + Countdown.round + ".two.kills", Vars.twoKills);
			recapConfig.set("rounds." + Countdown.round + ".one.deaths", Vars.oneDeaths);
			recapConfig.set("rounds." + Countdown.round + ".two.deaths", Vars.twoDeaths);
			recapConfig.set("rounds." + Countdown.round + ".one.mateKills", Vars.oneMateKills);
			recapConfig.set("rounds." + Countdown.round + ".two.mateKills", Vars.twoMateKills);
			recapConfig.set("rounds." + Countdown.round + ".one.flagTaken", Vars.oneFlagTaken);
			recapConfig.set("rounds." + Countdown.round + ".two.flagTaken", Vars.twoFlagTaken);
			recapConfig.set("rounds." + Countdown.round + ".one.flagCaptur", Vars.oneFlagCaptur);
			recapConfig.set("rounds." + Countdown.round + ".two.flagCaptur", Vars.twoFlagCaptur);
			recapConfig.set("rounds." + Countdown.round + ".one.flagDrop", Vars.oneFlagDrop);
			recapConfig.set("rounds." + Countdown.round + ".two.flagDrop", Vars.twoFlagDrop);
			recapConfig.set("rounds." + Countdown.round + ".one.flagBack", Vars.oneFlagBack);
			recapConfig.set("rounds." + Countdown.round + ".two.flagBack", Vars.twoFlagBack);
			recapConfig.set("rounds." + Countdown.round + ".one.perfect", Vars.onePerfect);
			recapConfig.set("rounds." + Countdown.round + ".two.perfect", Vars.twoPerfect);
			recapConfig.set("rounds." + Countdown.round + ".one.perfDef", Vars.onePerfDef);
			recapConfig.set("rounds." + Countdown.round + ".two.perfDef", Vars.twoPerfDef);
			recapConfig.set("rounds." + Countdown.round + ".one.blitz", Vars.oneBlitz);
			recapConfig.set("rounds." + Countdown.round + ".two.blitz", Vars.twoBlitz);
			recapConfig.set("rounds." + Countdown.round + ".one.winRd", Vars.oneWinRd);
			recapConfig.set("rounds." + Countdown.round + ".two.winRd", Vars.twoWinRd);
			recapConfig.set("rounds." + Countdown.round + ".one.loseRd", Vars.oneLoseRd);
			recapConfig.set("rounds." + Countdown.round + ".two.loseRd", Vars.twoLoseRd);
			recapConfig.set("rounds." + Countdown.round + ".one.goldsRd", Vars.oneGoldsRd);
			recapConfig.set("rounds." + Countdown.round + ".two.goldsRd", Vars.twoGoldsRd);
			recapConfig.set("rounds." + Countdown.round + ".one.goldsTotal", Vars.oneGoldsTotal);
			recapConfig.set("rounds." + Countdown.round + ".two.goldsTotal", Vars.twoGoldsTotal);
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addVictory(String teamVic, String pVic) {

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);
		try {
			recapConfig.set("game.victory.team", teamVic);
			recapConfig.set("game.victory.players", pVic);
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addDefeat(String teamDef, String pDef) {

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);
		try {
			recapConfig.set("game.defeat.team", teamDef);
			recapConfig.set("game.defeat.players", pDef);
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void finishGame() {

		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recapGameFile = new File(pathRecDefault, File.separator + time + "_gameStats" + nbrG + ".yml");
		recapConfig = YamlConfiguration.loadConfiguration(recapGameFile);
		try {
			recapConfig.set("finish", 1);
			recapConfig.save(recapGameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
