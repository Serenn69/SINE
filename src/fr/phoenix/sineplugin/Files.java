package fr.phoenix.sineplugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.phoenix.sineplugin.colors.JavaColors;

public class Files {

	Date date = new Date();
	String DATE_FORMAT = "dd-MM-yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	String time = sdf.format(date);

	public static FileConfiguration config, coordConfig;
	public static FileConfiguration setsNameConfig;
	public static FileConfiguration pData;
	public static FileConfiguration recDefaultConfig;
	public static FileConfiguration pDefaultConfig;
	public static FileConfiguration shopCoordConfig;
	public static FileConfiguration shopBSWeaponsItemsConfig;
	public static FileConfiguration shopBSArmorsItemsConfig;
	public static FileConfiguration shopALPotionsItemsConfig;
	public static FileConfiguration shopENWeaponsItemsConfig;
	public static FileConfiguration shopENArmorsItemsConfig;
	public static FileConfiguration shopJUBlocksItemsConfig;
	public static FileConfiguration shopJUToolsItemsConfig;
	public static FileConfiguration shopNamesConfig;
	public static FileConfiguration newsConfig;
	public static FileConfiguration authConfig;
	public static FileConfiguration gradeConfig;
	FileConfiguration shopSignsConfig;
	File confFile, coordFile, setsNameFile, recDefaultFile, pDefaultFile, shopCoordFile, shopSignsFile, shopNamesFile,
			shopBSWeaponsItemsFile, shopBSArmorsItemsFile, shopALPotionsItemsFile, shopENWeaponsItemsFile,
			shopENArmorsItemsFile, shopJUBlocksItemsFile, shopJUToolsItemsFile, newsFile;
	public static File authFile, gradeFile;

	File empty_arena, empty_atk_base, one_atk_base, two_atk_base, score_0_one, score_0_two, score_1_one, score_1_two,
			score_2_one_N, score_2_one_S, score_2_two_N, score_2_two_S, score_3_one_N, score_3_one_S, score_3_two_N,
			score_3_two_S;

	File forest_small_OaTd, forest_small_OdTa, forest_large_OaTd, forest_large_OdTa;
	File ruins_small_OaTd, ruins_small_OdTa;

	public void exportSchem() {

		File emptyArenaPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		empty_arena = new File(emptyArenaPath, File.separator + "empty_arena.json");
		File emptyAtkBasePath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		empty_atk_base = new File(emptyAtkBasePath, File.separator + "empty_atk_base.json");
		File oneAtkBasePath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		one_atk_base = new File(oneAtkBasePath, File.separator + "one_atk_base.json");
		File twoAtkBasePath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		two_atk_base = new File(twoAtkBasePath, File.separator + "two_atk_base.json");
		
		File score0OnePath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_0_one = new File(score0OnePath, File.separator + "score_0_one.json");
		File score0TwoPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_0_two = new File(score0TwoPath, File.separator + "score_0_two.json");
		File score1OnePath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_1_one = new File(score1OnePath, File.separator + "score_1_one.json");
		File score1TwoPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_1_two = new File(score1TwoPath, File.separator + "score_1_two.json");
		File score2OneNPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_2_one_N = new File(score2OneNPath, File.separator + "score_2_one_N.json");
		File score2OneSPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_2_one_S = new File(score2OneSPath, File.separator + "score_2_one_S.json");
		File score2TwoNPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_2_two_N = new File(score2TwoNPath, File.separator + "score_2_two_N.json");
		File score2TwoSPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_2_two_S = new File(score2TwoSPath, File.separator + "score_2_two_S.json");
		File score3OneNPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_3_one_N = new File(score3OneNPath, File.separator + "score_3_one_N.json");
		File score3OneSPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_3_one_S = new File(score3OneSPath, File.separator + "score_3_one_S.json");
		File score3TwoNPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_3_two_N = new File(score3TwoNPath, File.separator + "score_3_two_N.json");
		File score3TwoSPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		score_3_two_S = new File(score3TwoSPath, File.separator + "score_3_two_S.json");
		
		File forestSOaTdPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		forest_small_OaTd = new File(forestSOaTdPath, File.separator + "forest_small_OaTd.json");
		File forestSOdTaPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		forest_small_OdTa = new File(forestSOdTaPath, File.separator + "forest_small_OdTa.json");
		File forestLOaTdPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		forest_large_OaTd = new File(forestLOaTdPath, File.separator + "forest_large_OaTd.json");
		File forestLOdTaPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		forest_large_OdTa = new File(forestLOdTaPath, File.separator + "forest_large_OdTa.json");
		
		File ruinsSOaTdPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		ruins_small_OaTd = new File(ruinsSOaTdPath, File.separator + "ruins_small_OaTd.json");
		File ruinsSOdTaPath = new File(Main.getInstance().getDataFolder() + File.separator + "ArenaDatabase");
		ruins_small_OdTa = new File(ruinsSOdTaPath, File.separator + "ruins_small_OdTa.json");

		if (!empty_arena.exists()) {
			empty_arena.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/empty_arena.json", false);
		}
		if (!empty_atk_base.exists()) {
			empty_atk_base.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/empty_atk_base.json", false);
		}
		if (!one_atk_base.exists()) {
			one_atk_base.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/one_atk_base.json", false);
		}
		if (!two_atk_base.exists()) {
			two_atk_base.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/two_atk_base.json", false);
		}
		
		if (!score_0_one.exists()) {
			score_0_one.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_0_one.json", false);
		}
		if (!score_0_two.exists()) {
			score_0_two.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_0_two.json", false);
		}
		if (!score_1_one.exists()) {
			score_1_one.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_1_one.json", false);
		}
		if (!score_1_two.exists()) {
			score_1_two.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_1_two.json", false);
		}
		if (!score_2_one_N.exists()) {
			score_2_one_N.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_2_one_N.json", false);
		}
		if (!score_2_one_S.exists()) {
			score_2_one_S.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_2_one_S.json", false);
		}
		if (!score_2_two_N.exists()) {
			score_2_two_N.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_2_two_N.json", false);
		}
		if (!score_2_two_S.exists()) {
			score_2_two_S.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_2_two_S.json", false);
		}
		if (!score_3_one_N.exists()) {
			score_3_one_N.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_3_one_N.json", false);
		}
		if (!score_3_one_S.exists()) {
			score_3_one_S.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_3_one_S.json", false);
		}
		if (!score_3_two_N.exists()) {
			score_3_two_N.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_3_two_N.json", false);
		}
		if (!score_3_two_S.exists()) {
			score_3_two_S.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/score_3_two_S.json", false);
		}
		
		if (!forest_small_OaTd.exists()) {
			forest_small_OaTd.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/forest_small_OaTd.json", false);
		}
		if (!forest_small_OdTa.exists()) {
			forest_small_OdTa.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/forest_small_OdTa.json", false);
		}
		if (!forest_large_OaTd.exists()) {
			forest_large_OaTd.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/forest_large_OaTd.json", false);
		}
		if (!forest_large_OdTa.exists()) {
			forest_large_OdTa.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/forest_large_OdTa.json", false);
		}
		
		if (!ruins_small_OaTd.exists()) {
			ruins_small_OaTd.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/ruins_small_OaTd.json", false);
		}
		if (!ruins_small_OdTa.exists()) {
			ruins_small_OdTa.getParentFile().mkdirs();
			Main.getInstance().saveResource("ArenaDatabase/ruins_small_OdTa.json", false);
		}
	}

	// Methode de création des config, sauvegardes et affichage console
	public void createFiles() {

		confFile = new File(Main.getInstance().getDataFolder(), "config.yml");
		coordFile = new File(Main.getInstance().getDataFolder(), "coordinates.yml");
		setsNameFile = new File(Main.getInstance().getDataFolder(), "settingsNames.yml");
		authFile = new File(Main.getInstance().getDataFolder(), "authLogs.yml");
		File pathRecDefault = new File(Main.getInstance().getDataFolder() + File.separator + "GameDatabase");
		recDefaultFile = new File(pathRecDefault, File.separator + "#gameStatsDefault.yml");
		File pathPDefault = new File(Main.getInstance().getDataFolder(), File.separator + "PlayerDatabase");
		pDefaultFile = new File(pathPDefault, File.separator + "#playerStatsDefault.yml");
		File pathShopCoord = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopCoordFile = new File(pathShopCoord, File.separator + "shopCoordinates.yml");
		File pathShopSigns = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopSignsFile = new File(pathShopSigns, File.separator + "shopSigns.yml");
		File pathShopNames = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopNamesFile = new File(pathShopNames, File.separator + "shopNames.yml");
		File pathShopBSWeaponsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopBSWeaponsItemsFile = new File(pathShopBSWeaponsItems, File.separator + "shopBlacksmithWeaponsItems.yml");
		File pathShopBSArmorsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopBSArmorsItemsFile = new File(pathShopBSArmorsItems, File.separator + "shopBlacksmithArmorsItems.yml");
		File pathShopENWeaponsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopENWeaponsItemsFile = new File(pathShopENWeaponsItems, File.separator + "shopEnchanterWeaponsItems.yml");
		File pathShopENArmorsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopENArmorsItemsFile = new File(pathShopENArmorsItems, File.separator + "shopEnchanterArmorsItems.yml");
		File pathShopALPotionsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopALPotionsItemsFile = new File(pathShopALPotionsItems, File.separator + "shopAlchemistPotionsItems.yml");
		File pathShopJUBlocksItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopJUBlocksItemsFile = new File(pathShopJUBlocksItems, File.separator + "shopJunkBlocksItems.yml");
		File pathShopJUToolsItems = new File(Main.getInstance().getDataFolder(), File.separator + "ShopDatabase");
		shopJUToolsItemsFile = new File(pathShopJUToolsItems, File.separator + "shopJunkToolsItems.yml");
		File pathNews = new File(Main.getInstance().getDataFolder(), File.separator + "NewsDatabase");
		newsFile = new File(pathNews, File.separator + "newsBar.yml");
		File pathAuth = new File(Main.getInstance().getDataFolder(), File.separator + "AdminDatabase");
		authFile = new File(pathAuth, File.separator + "authLogs.yml");
		File pathGrade = new File(Main.getInstance().getDataFolder(), File.separator + "AdminDatabase");
		gradeFile = new File(pathGrade, File.separator + "userGrade.yml");

		if (!confFile.exists()) {
			confFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("config.yml", false);
		} else
			Main.getInstance().saveResource("config.yml", true);

		if (!coordFile.exists()) {
			coordFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("coordinates.yml", false);
		} else
			Main.getInstance().saveResource("coordinates.yml", true);

		if (!setsNameFile.exists()) {
			setsNameFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("settingsNames.yml", false);
		} else
			Main.getInstance().saveResource("settingsNames.yml", true);
		
		if (!authFile.exists()) {
			authFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("AdminDatabase/authLogs.yml", false);
		}
		
		if (!gradeFile.exists()) {
			gradeFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("AdminDatabase/userGrade.yml", false);
		}

		if (!recDefaultFile.exists()) {
			recDefaultFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("GameDatabase/#gameStatsDefault.yml", false);
		}
		if (!pDefaultFile.exists()) {
			pDefaultFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("PlayerDatabase/#playerStatsDefault.yml", false);
		}
		if (!shopCoordFile.exists()) {
			shopCoordFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopCoordinates.yml", false);
		}
		if (!shopSignsFile.exists()) {
			shopSignsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopSigns.yml", false);
		}
		if (!shopNamesFile.exists()) {
			shopNamesFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopNames.yml", false);
		}
		if (!shopBSWeaponsItemsFile.exists()) {
			shopBSWeaponsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopBlacksmithWeaponsItems.yml", false);
		}
		if (!shopBSArmorsItemsFile.exists()) {
			shopBSArmorsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopBlacksmithArmorsItems.yml", false);
		}
		if (!shopENWeaponsItemsFile.exists()) {
			shopENWeaponsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopEnchanterWeaponsItems.yml", false);
		}
		if (!shopENArmorsItemsFile.exists()) {
			shopENArmorsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopEnchanterArmorsItems.yml", false);
		}
		if (!shopALPotionsItemsFile.exists()) {
			shopALPotionsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopAlchemistPotionsItems.yml", false);
		}
		if (!shopJUBlocksItemsFile.exists()) {
			shopJUBlocksItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopJunkBlocksItems.yml", false);
		}
		if (!shopJUToolsItemsFile.exists()) {
			shopJUToolsItemsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("ShopDatabase/shopJunkToolsItems.yml", false);
		}
		if (!newsFile.exists()) {
			newsFile.getParentFile().mkdirs();
			Main.getInstance().saveResource("NewsDatabase/newsBar.yml", false);
		}

		config = new YamlConfiguration();
		coordConfig = new YamlConfiguration();
		setsNameConfig = new YamlConfiguration();
		recDefaultConfig = new YamlConfiguration();
		pDefaultConfig = new YamlConfiguration();
		shopCoordConfig = new YamlConfiguration();
		shopSignsConfig = new YamlConfiguration();
		shopNamesConfig = new YamlConfiguration();
		shopBSWeaponsItemsConfig = new YamlConfiguration();
		shopBSArmorsItemsConfig = new YamlConfiguration();
		shopENWeaponsItemsConfig = new YamlConfiguration();
		shopENArmorsItemsConfig = new YamlConfiguration();
		shopALPotionsItemsConfig = new YamlConfiguration();
		shopJUBlocksItemsConfig = new YamlConfiguration();
		shopJUToolsItemsConfig = new YamlConfiguration();
		newsConfig = new YamlConfiguration();
		authConfig = new YamlConfiguration();
		gradeConfig = new YamlConfiguration();

		try {
			config.load(confFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Default Config            "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			coordConfig.load(coordFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Tp & Blocks Coordinates   "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			setsNameConfig.load(setsNameFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"All Settings Names        "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			authConfig.load(authFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Auth Logs                 "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			gradeConfig.load(gradeFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"User Grades               "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			recDefaultConfig.load(recDefaultFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Default GameStatsFile     "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			pDefaultConfig.load(pDefaultFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Default PlayerStatsFile   "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			newsConfig.load(newsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"News Banner               "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopCoordConfig.load(shopCoordFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Shop Coordinates          "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopSignsConfig.load(shopSignsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Shop Signs                "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopNamesConfig.load(shopNamesFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Shop Names                "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopBSWeaponsItemsConfig.load(shopBSWeaponsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Blacksmith Weapon Items   "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopBSArmorsItemsConfig.load(shopBSArmorsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Blacksmith Armor Items    "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopENWeaponsItemsConfig.load(shopENWeaponsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Enchanter Weapon Items    "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopENArmorsItemsConfig.load(shopENArmorsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Enchanter Armor Items     "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopALPotionsItemsConfig.load(shopALPotionsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Alchemist Potion Items    "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopJUBlocksItemsConfig.load(shopJUBlocksItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Junk Dealer Block Items   "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);
			shopJUToolsItemsConfig.load(shopJUToolsItemsFile);
			System.out.println(JavaColors.Bright+JavaColors.White+"| "+JavaColors.Green+"OK	"+JavaColors.Magenta+"Junk Dealer Tool Items    "+JavaColors.White+" |"+JavaColors.Reset);
			System.out.println(JavaColors.Bright+JavaColors.White+"-----------------------------------"+JavaColors.Reset);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
