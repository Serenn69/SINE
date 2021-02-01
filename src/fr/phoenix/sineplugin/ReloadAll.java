package fr.phoenix.sineplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import fr.phoenix.sineplugin.caster.CasterHotbar;
import fr.phoenix.sineplugin.news.NewsManager;
import fr.phoenix.sineplugin.npc.NpcCustom;
import fr.phoenix.sineplugin.phases.Begin;
import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.regions.RegionsWG;

public class ReloadAll {

	public void restart() {


	}
	
	public void erase() {
		
		Beacons.beaconIDs.clear();
		
		defaultWGProt();
		weatherSun();
		reloadSettingsSign();
		clearTeamsHallSign();
		clearFenceTeamHall();
		new Flag().clearBanner();
		new Arena().clearArena();
		killEntities();
		
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
	}
	
	public void recreate() {
		
		Random r = new Random();

		Location hall = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
				Files.coordConfig.getDouble("hall.spawn.x") + r.nextInt(3), Files.coordConfig.getDouble("hall.spawn.y"),
				Files.coordConfig.getDouble("hall.spawn.z") + r.nextInt(3),
				Files.coordConfig.getInt("hall.spawn.pitch"), Files.coordConfig.getInt("hall.spawn.yaw"));
		
		new Files().createFiles();
		new Files().exportSchem();
		
		new NewsManager().displayNewsBanner();
		
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
		
		new Begin().createTeams();
		new Begin().createNbrPlayers();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(hall);
			new CasterHotbar().casterStuffMenu(p, 0);
			new CasterHotbar().specStuffMenu(p, 0);
			NewsManager.newsBar.addPlayer(p);
		}
		
	}

	Block bDoorOb = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("teams.one.door.x"), Files.coordConfig.getInt("teams.one.door.y"),
			Files.coordConfig.getInt("teams.one.door.z"));
	Block bDoorOh = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("teams.one.door.x"), Files.coordConfig.getInt("teams.one.door.y") + 1,
			Files.coordConfig.getInt("teams.one.door.z"));;
	Block bDoorHOb = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("hall.dooro.x"), Files.coordConfig.getInt("hall.dooro.y"),
			Files.coordConfig.getInt("hall.dooro.z"));
	Block bDoorHOh = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("hall.dooro.x"), Files.coordConfig.getInt("hall.dooro.y") + 1,
			Files.coordConfig.getInt("hall.dooro.z"));
	Block bDoorTb = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("teams.two.door.x"), Files.coordConfig.getInt("teams.two.door.y"),
			Files.coordConfig.getInt("teams.two.door.z"));
	Block bDoorTh = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("teams.two.door.x"), Files.coordConfig.getInt("teams.two.door.y") + 1,
			Files.coordConfig.getInt("teams.two.door.z"));
	Block bDoorHTb = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("hall.doort.x"), Files.coordConfig.getInt("hall.doort.y"),
			Files.coordConfig.getInt("hall.doort.z"));
	Block bDoorHTh = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
			Files.coordConfig.getInt("hall.doort.x"), Files.coordConfig.getInt("hall.doort.y") + 1,
			Files.coordConfig.getInt("hall.doort.z"));

	public void defaultWGProt() {

		RegionContainer container = Main.getInstance().getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")));

		if (regions != null || regions == null) {
			if (!regions.hasRegion("oneSmallZone"))
				regions.addRegion(RegionsWG.oneSmallZone);
			if (!regions.hasRegion("twoSmallZone"))
				regions.addRegion(RegionsWG.twoSmallZone);
			if (!regions.hasRegion("oneSmallDef"))
				regions.addRegion(RegionsWG.oneSmallDef);
			if (!regions.hasRegion("twoSmallDef"))
				regions.addRegion(RegionsWG.twoSmallDef);
			new RegionsWG().setFlag(RegionsWG.oneSmallZone, RegionsWG.oneSmallDef);
			new RegionsWG().setFlag(RegionsWG.twoSmallZone, RegionsWG.twoSmallDef);
			if (regions.hasRegion("oneLargeZone"))
				regions.removeRegion("oneLargeZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
			if (regions.hasRegion("twoLargeZone"))
				regions.removeRegion("twoLargeZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
			if (regions.hasRegion("oneLargeDef"))
				regions.removeRegion("oneLargeDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
			if (regions.hasRegion("twoLargeDef"))
				regions.removeRegion("twoLargeDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
		}
	}

	public void weatherSun() {

		Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setStorm(false);
		Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setThundering(false);
		Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setWeatherDuration(2000000);

	}

	@SuppressWarnings("deprecation")
	public void reloadSettingsSign() {

		// Mise à défaut des panneaux de settings dans la settings room

		// Rank :
		Block rankS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.game.rank.x"), Files.coordConfig.getInt("settings.game.rank.y") + 1,
				Files.coordConfig.getInt("settings.game.rank.z"));
		if (rankS.getState() instanceof Sign) {
			Sign rankSS = (Sign) rankS.getState();
			if (!rankSS.getLine(1)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.game.rank.b").replace("&", "§"))) {
				rankSS.setLine(1, Files.setsNameConfig.getString("settings.game.rank.b").replace("&", "§"));
				rankSS.update();
			} else {
			}
		}

		// Time :
		Block timeS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.game.day.x"), Files.coordConfig.getInt("settings.game.day.y") + 1,
				Files.coordConfig.getInt("settings.game.day.z"));
		if (timeS.getState() instanceof Sign) {
			Sign timeSS = (Sign) timeS.getState();
			if (!timeSS.getLine(1).equalsIgnoreCase("§c§lDAY")) {
				timeSS.setLine(1, "§c§lDAY");
				timeSS.update();
				Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setTime(6000);
				Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setGameRuleValue("doDaylightCycle",
						"false");
				Files.config.set("settings.game.time", "&9Time : &cDAY");
			} else {
			}
		}

		// Weather :
		Block weathS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.game.weather.x"),
				Files.coordConfig.getInt("settings.game.weather.y") + 1,
				Files.coordConfig.getInt("settings.game.weather.z"));
		if (weathS.getState() instanceof Sign) {
			Sign weathSS = (Sign) weathS.getState();
			if (!weathSS.getLine(1).equalsIgnoreCase("§c§lSUN")) {
				weathSS.setLine(1, "§c§lSUN");
				weathSS.update();
				Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setStorm(false);
				Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setThundering(false);
				Bukkit.getWorld(Files.setsNameConfig.getString("world.name")).setWeatherDuration(2000000);
				Files.config.set("settings.game.weather", "&9Weather : &cSUN");
			} else {
			}
		}

		// Begin :
		Block beginS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.game.begin.x"),
				Files.coordConfig.getInt("settings.game.begin.y") + 1,
				Files.coordConfig.getInt("settings.game.begin.z"));
		if (beginS.getState() instanceof Sign) {
			Sign beginSS = (Sign) beginS.getState();
			if (!beginSS.getLine(3)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.game.beginOrder.b").replace("&", "§"))) {
				beginSS.setLine(2, Files.setsNameConfig.getString("settings.game.beginOrder.a").replace("&", "§"));
				beginSS.setLine(3, Files.setsNameConfig.getString("settings.game.beginOrder.b").replace("&", "§"));
				beginSS.update();
				Files.config.set("options.begin", 0);
				Files.config.set("settings.game.begin",
						"&9Game Begin : &c"
								+ Files.setsNameConfig.getString("settings.game.beginOrder.a").replace("&l", "")
										.replace("&0", "&f")
								+ " &9/ " + Files.setsNameConfig.getString("settings.game.beginOrder.b")
										.replace("&l", "").replace("&0", "&f"));
			} else {
			}
		}

		// Golds Multiplicator :
		Block goldS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.game.golds.x"),
				Files.coordConfig.getInt("settings.game.golds.y") + 1,
				Files.coordConfig.getInt("settings.game.golds.z"));
		if (goldS.getState() instanceof Sign) {
			Sign goldSS = (Sign) goldS.getState();
			if (!goldSS.getLine(2)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&", "§"))) {
				goldSS.setLine(2, Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&", "§"));
				goldSS.update();
				Files.config.set("options.golds", 1);
				Files.config.set("settings.game.golds", "&9Golds Multiplicator : &c"
						+ Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&l", ""));
			} else {
			}
		}

		// Preparation timer :
		Block prepS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.timers.prep.x"),
				Files.coordConfig.getInt("settings.timers.prep.y") + 1,
				Files.coordConfig.getInt("settings.timers.prep.z"));
		if (prepS.getState() instanceof Sign) {
			Sign prepSS = (Sign) prepS.getState();
			if (!prepSS.getLine(3)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&", "§"))) {
				prepSS.setLine(3, Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&", "§"));
				prepSS.update();
				Files.config.set("options.prep", Files.setsNameConfig.getInt("settings.timers.prepNum.a"));
				Files.config.set("settings.game.prep", "&9Preparation : &c"
						+ Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&l", ""));
			} else {
			}
		}

		// Fight timer :
		Block fightS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.timers.fight.x"),
				Files.coordConfig.getInt("settings.timers.fight.y") + 1,
				Files.coordConfig.getInt("settings.timers.fight.z"));
		if (fightS.getState() instanceof Sign) {
			Sign fightSS = (Sign) fightS.getState();
			if (!fightSS.getLine(3).equalsIgnoreCase(
					Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&", "§"))) {
				fightSS.setLine(3, Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&", "§"));
				fightSS.update();
				Files.config.set("options.fight", Files.setsNameConfig.getInt("settings.timers.fightNum.a"));
				Files.config.set("settings.game.fight", "&9Fight : &c"
						+ Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&l", ""));
			} else {
			}
		}

		// Defense timer :
		Block defS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.timers.def.x"),
				Files.coordConfig.getInt("settings.timers.def.y") + 1,
				Files.coordConfig.getInt("settings.timers.def.z"));
		if (defS.getState() instanceof Sign) {
			Sign defSS = (Sign) defS.getState();
			if (!defSS.getLine(3)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&", "§"))) {
				defSS.setLine(3, Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&", "§"));
				defSS.update();
				Files.config.set("options.def", Files.setsNameConfig.getInt("settings.timers.defNum.a"));
				Files.config.set("settings.game.def", "&9Defense : &c"
						+ Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&l", ""));
			} else {
			}
		}

		// Taille de la map :
		Block sizeS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.maps.size.x"), Files.coordConfig.getInt("settings.maps.size.y") + 1,
				Files.coordConfig.getInt("settings.maps.size.z"));
		if (sizeS.getState() instanceof Sign) {
			Sign sizeSS = (Sign) sizeS.getState();
			if (!sizeSS.getLine(0)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&", "§"))) {
				sizeSS.setLine(0, Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&", "§"));
				sizeSS.setLine(1, Files.setsNameConfig.getString("settings.maps.sizePlay.a").replace("&", "§"));
				sizeSS.setLine(3, Files.setsNameConfig.getString("settings.maps.sizeDim.a").replace("&", "§"));
				sizeSS.update();
				Files.config.set("settings.game.size", "&9Map Size : &c"
						+ Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&l", ""));
			} else {
			}
		}

		// Map :
		Block mapS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.maps.map.x"), Files.coordConfig.getInt("settings.maps.map.y") + 1,
				Files.coordConfig.getInt("settings.maps.map.z"));
		if (mapS.getState() instanceof Sign) {
			Sign mapSS = (Sign) mapS.getState();
			if (!mapSS.getLine(1)
					.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.name.a").replace("&", "§"))) {
				mapSS.setLine(1, Files.setsNameConfig.getString("settings.maps.name.a").replace("&", "§"));
				mapSS.update();
				Files.config.set("settings.game.map",
						"&9Map : &c" + Files.setsNameConfig.getString("settings.maps.name.a").replace("&l", ""));
			} else {
			}
		}

		// Invisible Mates :
		Block invisS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.teams.invis.x"),
				Files.coordConfig.getInt("settings.teams.invis.y") + 1,
				Files.coordConfig.getInt("settings.teams.invis.z"));
		if (invisS.getState() instanceof Sign) {
			Sign invisSS = (Sign) invisS.getState();
			if (!invisSS.getLine(2).equalsIgnoreCase("§lOFF")) {
				invisSS.setLine(2, "§lOFF");
				invisSS.update();
				Vars.teamOne.setCanSeeFriendlyInvisibles(false);
				Vars.teamTwo.setCanSeeFriendlyInvisibles(false);
				Files.config.set("settings.game.invis", "&9Invisible Mates : &cOFF");
			} else {
			}
		}

		// Friendly Fire :
		Block ffS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.teams.ff.x"), Files.coordConfig.getInt("settings.teams.ff.y") + 1,
				Files.coordConfig.getInt("settings.teams.ff.z"));
		if (ffS.getState() instanceof Sign) {
			Sign ffSS = (Sign) ffS.getState();
			if (!ffSS.getLine(2).equalsIgnoreCase("§lOFF")) {
				ffSS.setLine(2, "§lOFF");
				ffSS.update();
				Vars.teamOne.setAllowFriendlyFire(false);
				Vars.teamTwo.setAllowFriendlyFire(false);
				Files.config.set("settings.game.ff", "&9FriendlyFire : &cOFF");
			} else {
			}
		}

		// Equilibrage des équipes :
		Block equiS = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("settings.teams.equi.x"),
				Files.coordConfig.getInt("settings.teams.equi.y") + 1,
				Files.coordConfig.getInt("settings.teams.equi.z"));
		if (equiS.getState() instanceof Sign) {
			Sign equiSS = (Sign) equiS.getState();
			if (!equiSS.getLine(2).equalsIgnoreCase("§lON")) {
				equiSS.setLine(2, "§lON");
				equiSS.update();
				Files.config.set("options.equi", 0);
				Files.config.set("settings.game.equi", "&9Balance Teams : &cON");
			} else {
			}
		}
	}

	public void clearTeamsHallSign() {

		// Clear Panneau ready dans le TeamHall de la TeamOne
		Block ob = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("teams.one.rdySign.x"), Files.coordConfig.getInt("teams.one.rdySign.y"),
				Files.coordConfig.getInt("teams.one.rdySign.z"));
		BlockState obs = ob.getState();

		if (obs instanceof Sign) {
			Sign os = (Sign) obs;
			if (os.getLine(1).equalsIgnoreCase("§lReady ?")) {
				os.setLine(0, "");
				os.setLine(1, "");
				os.setLine(2, "");
				os.setLine(3, "");
				os.update();
			}
			if (os.getLine(1).equalsIgnoreCase("§lWait for")) {
				os.setLine(0, "");
				os.setLine(1, "");
				os.setLine(2, "");
				os.setLine(3, "");
				os.update();
			}
			if (os.getLine(2).equalsIgnoreCase("§lREADY !")) {
				os.setLine(0, "");
				os.setLine(1, "");
				os.setLine(2, "");
				os.setLine(3, "");
				os.update();
			}
			if (os.getLine(2).equalsIgnoreCase("§lgame...")) {
				os.setLine(0, "");
				os.setLine(1, "");
				os.setLine(2, "");
				os.setLine(3, "");
				os.update();
			}
		}

		// Clear Panneau ready dans le TeamHall de la TeamTwo
		Block tb = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(
				Files.coordConfig.getInt("teams.two.rdySign.x"), Files.coordConfig.getInt("teams.two.rdySign.y"),
				Files.coordConfig.getInt("teams.two.rdySign.z"));
		BlockState tbs = tb.getState();

		if (tbs instanceof Sign) {
			Sign ts = (Sign) tbs;
			if (ts.getLine(1).equalsIgnoreCase("§lReady ?")) {
				ts.setLine(0, "");
				ts.setLine(1, "");
				ts.setLine(2, "");
				ts.setLine(3, "");
				ts.update();
			}
			if (ts.getLine(1).equalsIgnoreCase("§lWait for")) {
				ts.setLine(0, "");
				ts.setLine(1, "");
				ts.setLine(2, "");
				ts.setLine(3, "");
				ts.update();
			}
			if (ts.getLine(2).equalsIgnoreCase("§lREADY !")) {
				ts.setLine(0, "");
				ts.setLine(1, "");
				ts.setLine(2, "");
				ts.setLine(3, "");
				ts.update();
			}
			if (ts.getLine(2).equalsIgnoreCase("§lgame...")) {
				ts.setLine(0, "");
				ts.setLine(1, "");
				ts.setLine(2, "");
				ts.setLine(3, "");
				ts.update();
			}
		}
	}

	public void clearFenceTeamHall() {

		if (bDoorOb.getType() != Material.AIR) {
			bDoorOb.setType(Material.AIR);
		}

		if (bDoorOh.getType() != Material.AIR) {
			bDoorOh.setType(Material.AIR);
		}

		if (bDoorHOb.getType() != Material.AIR) {
			bDoorHOb.setType(Material.AIR);
		}

		if (bDoorHOh.getType() != Material.AIR) {
			bDoorHOh.setType(Material.AIR);
		}

		if (bDoorTb.getType() != Material.AIR) {
			bDoorTb.setType(Material.AIR);
		}

		if (bDoorTh.getType() != Material.AIR) {
			bDoorTh.setType(Material.AIR);
		}

		if (bDoorHTb.getType() != Material.AIR) {
			bDoorHTb.setType(Material.AIR);
		}

		if (bDoorHTh.getType() != Material.AIR) {
			bDoorHTh.setType(Material.AIR);
		}
	}

	public void killEntities() {
		
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
		
		String killOneScRoomCommand = "kill @e[x=-53,y=66,z=-134,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killOneScRoomCommand);
		String killTwoScRoomCommand = "kill @e[x=54,y=66,z=-134,r=2,type=armor_stand]";
		Bukkit.dispatchCommand(console, killTwoScRoomCommand);
		String killCasterScRoomCommand = "kill @e[x=0,y=27,z=-15,r=3,type=armor_stand]";
		Bukkit.dispatchCommand(console, killCasterScRoomCommand);
		
		String killPrevRoundCommand = "kill @e[x=-3,y=110,z=-82,r=3,type=armor_stand]";
		Bukkit.dispatchCommand(console, killPrevRoundCommand);
	}

}
