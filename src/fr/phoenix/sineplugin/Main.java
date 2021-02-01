package fr.phoenix.sineplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.phoenix.sineplugin.MVP.MvpVotePointsEvents;
import fr.phoenix.sineplugin.admin.AdminCommands;
import fr.phoenix.sineplugin.admin.AdminMenuEvents;
import fr.phoenix.sineplugin.caster.CasterEvents;
import fr.phoenix.sineplugin.caster.CasterHotbar;
import fr.phoenix.sineplugin.colors.ChatEvents;
import fr.phoenix.sineplugin.colors.JavaColors;
import fr.phoenix.sineplugin.news.NewsManager;
import fr.phoenix.sineplugin.npc.NpcCustom;
import fr.phoenix.sineplugin.npc.NpcEvents;
import fr.phoenix.sineplugin.phases.Begin;
import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.phases.Fight;
import fr.phoenix.sineplugin.phases.GameSettings;
import fr.phoenix.sineplugin.phases.Hall;
import fr.phoenix.sineplugin.phases.Victory;
import fr.phoenix.sineplugin.playerProfile.PlayerStatsEvents;
import fr.phoenix.sineplugin.shopBuy.AlchemistPotionsBuyEvent;
import fr.phoenix.sineplugin.shopBuy.BlacksmithArmorsBuyEvent;
import fr.phoenix.sineplugin.shopBuy.BlacksmithWeaponsBuyEvent;
import fr.phoenix.sineplugin.shopBuy.EnchanterArmorsBuyEvent;
import fr.phoenix.sineplugin.shopBuy.EnchanterWeaponsBuyEvent;
import fr.phoenix.sineplugin.shopBuy.JunkdealerBlocksBuyEvent;
import fr.phoenix.sineplugin.shopBuy.JunkdealerToolsBuyEvent;
import fr.phoenix.sineplugin.structureLoader.CmdPasteStructure;
import fr.phoenix.sineplugin.structureLoader.CmdSaveSpecialStructure;
import fr.phoenix.sineplugin.structureLoader.CmdSaveStructure;
import fr.phoenix.sineplugin.structureLoader.SelectionListener;
import fr.phoenix.sineplugin.structureLoader.SelectionManager;
import fr.phoenix.sineplugin.structureLoader.TrafficListener;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	
    public SqlConnection sql;

	private SelectionManager selectionManager;

	public SelectionManager getSelectionManager() {
		return this.selectionManager;
	}

	public static Main getInstance() {
		return instance;
	}
	
	public WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
	// Au lancement du serveur
	public void onEnable() {

		instance = this;
		
		sql = new SqlConnection("jdbc:mysql://","sql3.dedishops.com", 3306, "consolemc7614", "consolemc7614", "8b861b6f7a");
		sql.connect();
		
		// Création et sauvegarde des fichiers config.yml, coordinates.yml et
		// settingsNames.yml
		new Files().createFiles();
		new Files().exportSchem();
		
		Beacons.beaconIDs.clear();

		// Display NewsBar
		new NewsManager().displayNewsBanner();

		// Spawn des NPC Shop
		new NpcCustom().SpawnNpcShop(5, "one", 1);
		new NpcCustom().SpawnNpcShop(5, "one", 2);
		new NpcCustom().SpawnNpcShop(5, "one", 3);
		new NpcCustom().SpawnNpcShop(5, "one", 4);
		new NpcCustom().SpawnNpcShop(5, "one", 5);
		new NpcCustom().SpawnNpcShop(5, "one", 6);

		new NpcCustom().SpawnNpcShop(6, "two", 1);
		new NpcCustom().SpawnNpcShop(6, "two", 2);
		new NpcCustom().SpawnNpcShop(6, "two", 3);
		new NpcCustom().SpawnNpcShop(5, "two", 4);
		new NpcCustom().SpawnNpcShop(5, "two", 5);
		new NpcCustom().SpawnNpcShop(5, "two", 6);

		new NpcCustom().spawnTeleporter("one");
		new NpcCustom().spawnTeleporter("two");

		PluginManager pm = getServer().getPluginManager();

		// Enregistrement des différents events
		pm.registerEvents(new PlayersJoins(), this);
		pm.registerEvents(new Hall(), this);
		pm.registerEvents(new GameSettings(), this);
		pm.registerEvents(new NpcEvents(), this);
		pm.registerEvents(new BlacksmithWeaponsBuyEvent(), this);
		pm.registerEvents(new BlacksmithArmorsBuyEvent(), this);
		pm.registerEvents(new EnchanterWeaponsBuyEvent(), this);
		pm.registerEvents(new EnchanterArmorsBuyEvent(), this);
		pm.registerEvents(new AlchemistPotionsBuyEvent(), this);
		pm.registerEvents(new JunkdealerBlocksBuyEvent(), this);
		pm.registerEvents(new JunkdealerToolsBuyEvent(), this);
		pm.registerEvents(new CasterEvents(), this);
		pm.registerEvents(new Fight(), this);
		pm.registerEvents(new TrafficListener(), this);
		pm.registerEvents(new SelectionListener(), this);
		pm.registerEvents(new Beacons(),  this);
		pm.registerEvents(new AdminMenuEvents(),  this);
		pm.registerEvents(new ChatEvents(),  this);
		pm.registerEvents(new PlayerStatsEvents(),  this);
		pm.registerEvents(new MvpVotePointsEvents(), this);

		this.getCommand("save").setExecutor(new CmdSaveStructure());
		this.getCommand("save-special").setExecutor(new CmdSaveSpecialStructure());
		this.getCommand("paste").setExecutor(new CmdPasteStructure());
		this.selectionManager = new SelectionManager();

		// CREATE TEAMS AND UTILITY VARS
		new Begin().createTeams();
		new Begin().createNbrPlayers();

		// CLEAR ITEMS ON RELOAD
		new ReloadAll().clearTeamsHallSign();
		new ReloadAll().reloadSettingsSign();
		new ReloadAll().clearFenceTeamHall();
		new ReloadAll().weatherSun();

		getCommand("admin").setExecutor(new AdminCommands());
		getCommand("caster").setExecutor(new AdminCommands());
		getCommand("spec").setExecutor(new AdminCommands());
		getCommand("player").setExecutor(new AdminCommands());
		getCommand("unblack").setExecutor(new AdminCommands());
		getCommand("vote").setExecutor(new AdminCommands());
		
		// Message console
		System.out.println(JavaColors.Bright+JavaColors.White+"--------------------------------------"+JavaColors.Reset);
		System.out.println(JavaColors.Bright+JavaColors.White+"|"+JavaColors.Yellow+" S.I.N.E "+JavaColors.White+"Plugin "+JavaColors.Cyan+"Alpha v0.3 "+JavaColors.Red+"LOADED ! "+JavaColors.White+"|"+JavaColors.Reset);
		System.out.println(JavaColors.Bright+JavaColors.White+"--------------------------------------"+JavaColors.Reset);

	}

	// Désactivation du plugin
	public void onDisable() {
		
		sql.disconnect();

		Random r = new Random();

		Location hall = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
				Files.coordConfig.getDouble("hall.spawn.x") + r.nextInt(3), Files.coordConfig.getDouble("hall.spawn.y"),
				Files.coordConfig.getDouble("hall.spawn.z") + r.nextInt(3),
				Files.coordConfig.getInt("hall.spawn.pitch"), Files.coordConfig.getInt("hall.spawn.yaw"));

		// Effacement des scores des FakePlayers et de la sidebar
		Vars.sineBoard.resetScores(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
		Vars.sineBoard.resetScores(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
		Vars.sineBoard.clearSlot(DisplaySlot.SIDEBAR);

		if (Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")) != null) {
			Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.one").replace("&", "§")).unregister();
			System.out.println("teamOne unregistred");
		}
		if (Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")) != null) {
			Vars.sineBoard.getTeam(Files.setsNameConfig.getString("teams.name.two").replace("&", "§")).unregister();
			System.out.println("teamTwo unregistred");
		}

		Vars.sineBoardCaster.resetScores(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
		Vars.sineBoardCaster.resetScores(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
		Vars.sineBoardCaster.clearSlot(DisplaySlot.SIDEBAR);

		// Effacement des scores des joueurs et de la sidebar
		for (Player p : Bukkit.getOnlinePlayers()) {

			if (p.getScoreboard().equals(Vars.sineBoardCaster)) {
				Vars.sineBoardCaster.resetScores(p.getName());
				Vars.sineBoardCaster.clearSlot(DisplaySlot.SIDEBAR);
				p.setScoreboard(Vars.sineBoard);
			}
			if (p.getScoreboard().equals(Vars.sineBoardOne)) {
				Vars.sineBoardOne.resetScores(p.getName());
				Vars.sineBoardOne.clearSlot(DisplaySlot.SIDEBAR);
				p.setScoreboard(Vars.sineBoard);
			}
			if (p.getScoreboard().equals(Vars.sineBoardTwo)) {
				Vars.sineBoardTwo.resetScores(p.getName());
				Vars.sineBoardTwo.clearSlot(DisplaySlot.SIDEBAR);
				p.setScoreboard(Vars.sineBoard);
			}
			if (p.getScoreboard().equals(Vars.sineBoard)) {
				Vars.sineBoard.resetScores(p.getName());
				Vars.sineBoard.clearSlot(DisplaySlot.SIDEBAR);
			}
		}

		NewsManager.newsBar.removeAll();
		if (Countdown.cdLaunchBar != null) {
			Countdown.cdLaunchBar.removeAll();
		}
		if (Countdown.cdPrepBar != null) {
			Countdown.cdPrepBar.removeAll();
		}
		if (Countdown.cdDefBar != null) {
			Countdown.cdDefBar.removeAll();
		}
		if (Countdown.cdFightBar != null) {
			Countdown.cdFightBar.removeAll();
		}

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		String killVillager = "kill @e[type=villager]";
		Bukkit.dispatchCommand(console, killVillager);
		String killEvoker = "kill @e[type=evocation_illager]";
		Bukkit.dispatchCommand(console, killEvoker);
		String killWitch = "kill @e[type=witch]";
		Bukkit.dispatchCommand(console, killWitch);
		String killVindicator = "kill @e[type=vindication_illager]";
		Bukkit.dispatchCommand(console, killVindicator);
		String killVex = "kill @e[type=vex]";
		Bukkit.dispatchCommand(console, killVex);
		
		String killGameStats = "kill @e[x=-2,y=131,z=-66,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killGameStats);
		String killGameSettings = "kill @e[x=2,y=131,z=-66,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killGameSettings);
		
		String killTeamWin = "kill @e[x=0.5,y=136,z=-80,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killTeamWin);
		String killP1Win = "kill @e[x=-0.5,y=135.5,z=-80,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killP1Win);
		String killP2Win = "kill @e[x=0.5,y=135,z=-80,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killP2Win);
		String killP3Win = "kill @e[x=1.5,y=135.5,z=-80,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killP3Win);
		
		if (Main.getInstance().getServer().getScheduler().isQueued(Victory.crown)) {
			Main.getInstance().getServer().getScheduler().cancelTask(Victory.crown);
		}
		if (Main.getInstance().getServer().getScheduler().isQueued(Victory.victory)) {
			Main.getInstance().getServer().getScheduler().cancelTask(Victory.victory);
		}
		if (Main.getInstance().getServer().getScheduler().isQueued(Victory.wings)) {
			Main.getInstance().getServer().getScheduler().cancelTask(Victory.wings);
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(hall);
			new CasterHotbar().casterStuffMenu(p, 0);
			new CasterHotbar().specStuffMenu(p, 0);
		}

		new Flag().clearBanner();
		new Arena().clearArena();

		// Message console
		System.out.println(JavaColors.Bright+JavaColors.White+"----------------------------------------"+JavaColors.Reset);
		System.out.println(JavaColors.Bright+JavaColors.White+"|"+JavaColors.Yellow+" S.I.N.E "+JavaColors.White+"Plugin "+JavaColors.Cyan+"Alpha v0.3 "+JavaColors.Red+"UNLOADED ! "+JavaColors.White+"|"+JavaColors.Reset);
		System.out.println(JavaColors.Bright+JavaColors.White+"----------------------------------------"+JavaColors.Reset);
	}

}
