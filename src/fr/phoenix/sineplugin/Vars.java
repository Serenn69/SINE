package fr.phoenix.sineplugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import fr.phoenix.sineplugin.shopMenu.AlchemistLingeringPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.AlchemistPersonnalPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.AlchemistSplashPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.BlacksmithArmorsMenu;
import fr.phoenix.sineplugin.shopMenu.BlacksmithWeaponsMenu;
import fr.phoenix.sineplugin.shopMenu.EnchanterArmorsMenu;
import fr.phoenix.sineplugin.shopMenu.EnchanterWeaponsMenu;
import fr.phoenix.sineplugin.shopMenu.JunkdealerBlocksMenu;
import fr.phoenix.sineplugin.shopMenu.JunkdealerToolsMenu;

public class Vars {

	public static Map<String, Integer> teamsNbrP = new HashMap<String, Integer>();
	public static Map<Player, PlayerStats> pStatsSeason = new HashMap<>();
	public static Map<Player, PlayerStats> pStatsLastGame = new HashMap<>();
	
	// CanDestroy items
	public static ArrayList<Material> canBreak = new ArrayList<Material>(
			Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE));
	public static ArrayList<Material> canDestroy = new ArrayList<Material>(Arrays.asList(Material.WOODEN_PICKAXE,
			Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE));

	// CanPlaceOn items
	public static ArrayList<Material> canPlaceOn = new ArrayList<Material>(
			Arrays.asList(Material.OAK_WOOD, Material.STONE_BRICKS, Material.IRON_BLOCK, Material.END_STONE));

	// Création des scoreboards
	public static ScoreboardManager sb = Bukkit.getScoreboardManager();
	public static Scoreboard sineBoard = sb.getMainScoreboard();
	public static Scoreboard sineBoardCaster = sb.getNewScoreboard();
	public static Scoreboard sineBoardOne = sb.getNewScoreboard();
	public static Scoreboard sineBoardTwo = sb.getNewScoreboard();

	// Objectif du nombre de joueurs en début de partie
	public static Objective nbrP;

	// Objectif des teams golds pour casteurs
	public static Objective allGolds;

	// Objectif des trackers pour teams et caster
	public static Objective trackerOne, trackerTwo, trackerCaster;
	public static Objective oneHealth, twoHealth, casterHealth;

	public static int aBarGolds;

	public static Team teamOne, teamTwo;
	public static Team oneTeamOne, oneTeamTwo;
	public static Team twoTeamOne, twoTeamTwo;
	public static Team casterTeamOne, casterTeamTwo;

	public static ArrayList<UUID> uuidTeamOne = new ArrayList<>();
	public static ArrayList<UUID> uuidTeamTwo = new ArrayList<>();
	public static HashMap<String, Integer> pNameTeamOne = new HashMap<>();
	public static HashMap<String, Integer> pNameTeamTwo = new HashMap<>();
	public static ArrayList<String> pNameAdmin = new ArrayList<>();
	public static ArrayList<String> pNameCaster = new ArrayList<>();
	public static ArrayList<String> pNameSpec = new ArrayList<>();
	public static ArrayList<String> pNameGamers = new ArrayList<>();
	public static ArrayList<String> pNameFlagHolder = new ArrayList<>();

	public static int freeze = 0;

	// Game Statistics Vars
	public static int oneKills, twoKills, oneDeaths, twoDeaths, oneMateKills, twoMateKills, oneFlagTaken, twoFlagTaken,
			oneFlagCaptur, twoFlagCaptur, oneFlagDrop, twoFlagDrop, oneFlagBack, twoFlagBack, onePerfect, twoPerfect,
			onePerfDef, twoPerfDef, oneBlitz, twoBlitz, oneWinRd, twoWinRd, oneLoseRd, twoLoseRd, oneGoldsRd,
			twoGoldsRd, oneGoldsTotal, twoGoldsTotal, draw;

	public static int oneScore = 0, twoScore = 0;
	public static int totalDraw = 0, totalOneWinRd = 0, totalTwoWinRd = 0, totalOneLoseRd = 0, totalTwoLoseRd = 0,
			totalOneKills = 0, totalTwoKills = 0, totalOneDeaths = 0, totalTwoDeaths = 0, totalOneMates = 0,
			totalTwoMates = 0, totalOneTaken = 0, totalTwoTaken = 0, totalOneCaptur = 0, totalTwoCaptur = 0,
			totalOneDrop = 0, totalTwoDrop = 0, totalOneBack = 0, totalTwoBack = 0, totalOnePerfect = 0,
			totalTwoPerfect = 0, totalOnePerfDef = 0, totalTwoPerfDef = 0, totalOneBlitz = 0, totalTwoBlitz = 0,
			totalOneWinGolds = 0, totalTwoWinGolds = 0;
	public static String whoBegin = "OaTd";
	public static String arenaMap = "forest_", arenaSize = "small_";

	public static Random r = new Random();

	Date date = new Date();
	String DATE_FORMAT = "dd-MM-yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	String time = sdf.format(date);

	public void checkTeamsGolds(Material m, PotionType pot, FileConfiguration conf, String npc, String item,
			String piece, String type, Player p, int nbr, Inventory pInv, Enchantment en, int lvl, boolean time,
			boolean up, String effect) {

		if (pNameTeamOne.containsKey(p.getName())) {

			if (conf.equals(Files.shopBSWeaponsItemsConfig) || conf.equals(Files.shopBSArmorsItemsConfig)
					|| conf.equals(Files.shopJUToolsItemsConfig)) {

				if (Vars.oneGoldsTotal >= conf.getInt("" + npc + "." + item + "." + piece + type + ".p")) {

					Vars.oneGoldsTotal -= conf.getInt("" + npc + "." + item + "." + piece + type + ".p");

					if (conf.equals(Files.shopBSWeaponsItemsConfig)) {
						pInv.addItem(BlacksmithWeaponsMenu.getWeapon(m, piece, type, nbr, pot, "one"));
					} else if (conf.equals(Files.shopBSArmorsItemsConfig)) {
						pInv.addItem(BlacksmithArmorsMenu.getArmor(m, piece, type, p, "one"));
					} else if (conf.equals(Files.shopJUToolsItemsConfig)) {
						pInv.addItem(JunkdealerToolsMenu.getTool(m, nbr, piece, type, "one"));
					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + piece + type + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + piece + type + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopENWeaponsItemsConfig) || conf.equals(Files.shopENArmorsItemsConfig)) {

				if (Vars.oneGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + lvl + ".p")) {

					Vars.oneGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + lvl + ".p");

					if (conf.equals(Files.shopENWeaponsItemsConfig)) {
						p.sendMessage("§7§oClick on item to enchant it (" + Files.shopENWeaponsItemsConfig
								.getString("enchanter.weapons." + type + lvl + ".name").replace("&o", "§3§o")
								+ "§7§l)");

						pInv.addItem(EnchanterWeaponsMenu.getWeaponEnchant(en, type, lvl, "one"));

					} else if (conf.equals(Files.shopENArmorsItemsConfig)) {
						p.sendMessage("§7§oClick on item to enchant it (" + Files.shopENArmorsItemsConfig
								.getString("enchanter.armors." + type + lvl + ".name").replace("&o", "§3§o") + "§7§l)");

						pInv.addItem(EnchanterArmorsMenu.getArmorEnchant(en, type, lvl, "one"));

					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + lvl + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + lvl + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopALPotionsItemsConfig)) {

				if (Vars.oneGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + effect + ".p")) {

					Vars.oneGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + effect + ".p");

					if (conf.equals(Files.shopALPotionsItemsConfig)) {
						if (m.equals(Material.POTION))
							pInv.addItem(AlchemistPersonnalPotionsMenu.getPersonnalPotion(pot, time, up, type, effect,
									"one"));
						if (m.equals(Material.SPLASH_POTION))
							pInv.addItem(
									AlchemistSplashPotionsMenu.getSplashPotion(pot, time, up, type, effect, "one"));
						if (m.equals(Material.LINGERING_POTION))
							pInv.addItem(AlchemistLingeringPotionsMenu.getLingeringPotion(pot, time, up, type, effect,
									"one"));
					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + effect + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + effect + ".name").replace("&o",
									"§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopJUBlocksItemsConfig)) {

				if (Vars.oneGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr)) {

					Vars.oneGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr);

					pInv.addItem(JunkdealerBlocksMenu.getBlockNo(m, nbr, type, "one"));

				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr) + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
		}

		if (pNameTeamTwo.containsKey(p.getName())) {

			if (conf.equals(Files.shopBSWeaponsItemsConfig) || conf.equals(Files.shopBSArmorsItemsConfig)
					|| conf.equals(Files.shopJUToolsItemsConfig)) {

				if (Vars.twoGoldsTotal >= conf.getInt("" + npc + "." + item + "." + piece + type + ".p")) {

					Vars.twoGoldsTotal -= conf.getInt("" + npc + "." + item + "." + piece + type + ".p");

					if (conf.equals(Files.shopBSWeaponsItemsConfig)) {
						pInv.addItem(BlacksmithWeaponsMenu.getWeapon(m, piece, type, nbr, pot, "two"));
					} else if (conf.equals(Files.shopBSArmorsItemsConfig)) {
						pInv.addItem(BlacksmithArmorsMenu.getArmor(m, piece, type, p, "two"));
					} else if (conf.equals(Files.shopJUToolsItemsConfig)) {
						pInv.addItem(JunkdealerToolsMenu.getTool(m, nbr, piece, type, "two"));
					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + piece + type + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + piece + type + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopENWeaponsItemsConfig) || conf.equals(Files.shopENArmorsItemsConfig)) {

				if (Vars.twoGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + lvl + ".p")) {

					Vars.twoGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + lvl + ".p");

					if (conf.equals(Files.shopENWeaponsItemsConfig)) {
						p.sendMessage("§7§oClick on item to enchant it (" + Files.shopENWeaponsItemsConfig
								.getString("enchanter.weapons." + type + lvl + ".name").replace("&o", "§3§o")
								+ "§7§l)");

						pInv.addItem(EnchanterWeaponsMenu.getWeaponEnchant(en, type, lvl, "two"));
					} else if (conf.equals(Files.shopENArmorsItemsConfig)) {
						p.sendMessage("§7§oClick on item to enchant it (" + Files.shopENArmorsItemsConfig
								.getString("enchanter.armors." + type + lvl + ".name").replace("&o", "§3§o") + "§7§l)");

						pInv.addItem(EnchanterArmorsMenu.getArmorEnchant(en, type, lvl, "two"));
					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + lvl + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + lvl + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopALPotionsItemsConfig)) {

				if (Vars.twoGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + effect + ".p")) {

					Vars.twoGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + effect + ".p");

					if (conf.equals(Files.shopALPotionsItemsConfig)) {
						if (m.equals(Material.POTION))
							pInv.addItem(AlchemistPersonnalPotionsMenu.getPersonnalPotion(pot, time, up, type, effect,
									"two"));
						if (m.equals(Material.SPLASH_POTION))
							pInv.addItem(
									AlchemistSplashPotionsMenu.getSplashPotion(pot, time, up, type, effect, "two"));
						if (m.equals(Material.LINGERING_POTION))
							pInv.addItem(AlchemistLingeringPotionsMenu.getLingeringPotion(pot, time, up, type, effect,
									"two"));
					}
				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + effect + ".p") + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + effect + ".name").replace("&o",
									"§7§o")
							+ " !");
				}
			}
			if (conf.equals(Files.shopJUBlocksItemsConfig)) {

				if (Vars.twoGoldsTotal >= conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr)) {

					Vars.twoGoldsTotal -= conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr);
					;

					pInv.addItem(JunkdealerBlocksMenu.getBlockNo(m, nbr, type, "two"));

				} else {
					p.sendMessage("§7§oNot enough golds ! "
							+ conf.getInt("" + npc + "." + item + "." + type + ".p" + nbr) + " golds for "
							+ conf.getString("" + npc + "." + item + "." + type + ".name").replace("&o", "§7§o")
							+ " !");
				}
			}
		}
	}

	public void resetStatsVars() {

		oneKills = 0;
		twoKills = 0;
		oneDeaths = 0;
		twoDeaths = 0;
		oneMateKills = 0;
		twoMateKills = 0;
		oneFlagTaken = 0;
		twoFlagTaken = 0;
		oneFlagCaptur = 0;
		twoFlagCaptur = 0;
		oneFlagDrop = 0;
		twoFlagDrop = 0;
		oneFlagBack = 0;
		twoFlagBack = 0;
		onePerfect = 1;
		twoPerfect = 1;
		onePerfDef = 1;
		twoPerfDef = 1;
		oneBlitz = 0;
		twoBlitz = 0;
		oneWinRd = 0;
		twoWinRd = 0;
		oneLoseRd = 0;
		twoLoseRd = 0;
		oneGoldsRd = 0;
		twoGoldsRd = 0;
		draw = 0;
	}

	public static String repeat(int n, String str) {

		String result = str;

		for (int x = n - 1; x > 0; x--) {
			str += result;
		}
		if (n == 0)
			return "";
		return str;

	}

}
