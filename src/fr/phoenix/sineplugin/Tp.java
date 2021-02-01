package fr.phoenix.sineplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Tp {

	// Hologram Welcome Location
	public static Location welcHolo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5,
			35, -4);

	// GameSettings
	public static Location equiClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.teams.equi.x"), Files.coordConfig.getInt("settings.teams.equi.y"),
			Files.coordConfig.getInt("settings.teams.equi.z"));
	public static Location ffClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.teams.ff.x"), Files.coordConfig.getInt("settings.teams.ff.y"),
			Files.coordConfig.getInt("settings.teams.ff.z"));
	public static Location invisClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.teams.invis.x"), Files.coordConfig.getInt("settings.teams.invis.y"),
			Files.coordConfig.getInt("settings.teams.invis.z"));
	public static Location mapClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.maps.map.x"), Files.coordConfig.getInt("settings.maps.map.y"),
			Files.coordConfig.getInt("settings.maps.map.z"));
	public static Location sizeClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.maps.size.x"), Files.coordConfig.getInt("settings.maps.size.y"),
			Files.coordConfig.getInt("settings.maps.size.z"));
	public static Location defClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.timers.def.x"), Files.coordConfig.getInt("settings.timers.def.y"),
			Files.coordConfig.getInt("settings.timers.def.z"));
	public static Location fightClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.timers.fight.x"), Files.coordConfig.getInt("settings.timers.fight.y"),
			Files.coordConfig.getInt("settings.timers.fight.z"));
	public static Location prepClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.timers.prep.x"), Files.coordConfig.getInt("settings.timers.prep.y"),
			Files.coordConfig.getInt("settings.timers.prep.z"));
	public static Location rankClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.game.rank.x"), Files.coordConfig.getInt("settings.game.rank.y"),
			Files.coordConfig.getInt("settings.game.rank.z"));
	public static Location dayClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.game.day.x"), Files.coordConfig.getInt("settings.game.day.y"),
			Files.coordConfig.getInt("settings.game.day.z"));
	public static Location weatherClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.game.weather.x"), Files.coordConfig.getInt("settings.game.weather.y"),
			Files.coordConfig.getInt("settings.game.weather.z"));
	public static Location beginClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.game.begin.x"), Files.coordConfig.getInt("settings.game.begin.y"),
			Files.coordConfig.getInt("settings.game.begin.z"));
	public static Location goldsClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.game.golds.x"), Files.coordConfig.getInt("settings.game.golds.y"),
			Files.coordConfig.getInt("settings.game.golds.z"));
	public static Location broadClic = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("settings.broadcast.x"), Files.coordConfig.getInt("settings.broadcast.y"),
			Files.coordConfig.getInt("settings.broadcast.z"));

	// Hall
	public static Location bLocO = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.one.rdySign.x"), Files.coordConfig.getInt("teams.one.rdySign.y"),
			Files.coordConfig.getInt("teams.one.rdySign.z"));
	public static Location bLocT = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.two.rdySign.x"), Files.coordConfig.getInt("teams.two.rdySign.y"),
			Files.coordConfig.getInt("teams.two.rdySign.z"));
	public static Location doorOb = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.one.door.x"), Files.coordConfig.getInt("teams.one.door.y"),
			Files.coordConfig.getInt("teams.one.door.z"));
	public static Location doorOh = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.one.door.x"), Files.coordConfig.getInt("teams.one.door.y") + 1,
			Files.coordConfig.getInt("teams.one.door.z"));
	public static Location doorTb = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.two.door.x"), Files.coordConfig.getInt("teams.two.door.y"),
			Files.coordConfig.getInt("teams.two.door.z"));
	public static Location doorTh = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("teams.two.door.x"), Files.coordConfig.getInt("teams.two.door.y") + 1,
			Files.coordConfig.getInt("teams.two.door.z"));
	public static Location doorHOb = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("hall.dooro.x"), Files.coordConfig.getInt("hall.dooro.y"),
			Files.coordConfig.getInt("hall.dooro.z"));
	public static Location doorHOh = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("hall.dooro.x"), Files.coordConfig.getInt("hall.dooro.y") + 1,
			Files.coordConfig.getInt("hall.dooro.z"));
	public static Location doorHTb = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("hall.doort.x"), Files.coordConfig.getInt("hall.doort.y"),
			Files.coordConfig.getInt("hall.doort.z"));
	public static Location doorHTh = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("hall.doort.x"), Files.coordConfig.getInt("hall.doort.y") + 1,
			Files.coordConfig.getInt("hall.doort.z"));

	// Hall => TeamOne
	public static Location oTfH = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.fromHall.x"), Files.coordConfig.getDouble("teams.one.fromHall.y"),
			Files.coordConfig.getDouble("teams.one.fromHall.z"), Files.coordConfig.getInt("teams.one.fromHall.pitch"),
			Files.coordConfig.getInt("teams.one.fromHall.yaw"));

	// Hall => TeamTwo
	public static Location tTfH = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.fromHall.x"), Files.coordConfig.getDouble("teams.two.fromHall.y"),
			Files.coordConfig.getDouble("teams.two.fromHall.z"), Files.coordConfig.getInt("teams.two.fromHall.pitch"),
			Files.coordConfig.getInt("teams.two.fromHall.yaw"));

	// Hall => TeamThree
	public static Location thTfH = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.three.fromHall.x"),
			Files.coordConfig.getDouble("teams.three.fromHall.y"),
			Files.coordConfig.getDouble("teams.three.fromHall.z"),
			Files.coordConfig.getInt("teams.three.fromHall.pitch"),
			Files.coordConfig.getInt("teams.three.fromHall.yaw"));

	// TeamOne, TeamTwo and TeamThree => Hall
	public static Location hfT = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("hall.fromTeams.x"), Files.coordConfig.getDouble("hall.fromTeams.y"),
			Files.coordConfig.getDouble("hall.fromTeams.z"), Files.coordConfig.getInt("hall.fromTeams.pitch"),
			Files.coordConfig.getInt("hall.fromTeams.yaw"));

	// Hall => Settings
	public static Location sfH = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("settingsLoc.fromHall.x"),
			Files.coordConfig.getDouble("settingsLoc.fromHall.y"),
			Files.coordConfig.getDouble("settingsLoc.fromHall.z"),
			Files.coordConfig.getInt("settingsLoc.fromHall.pitch"),
			Files.coordConfig.getInt("settingsLoc.fromHall.yaw"));

	// Hall => Rules
	public static Location rfH = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("rules.fromHall.x"), Files.coordConfig.getDouble("rules.fromHall.y"),
			Files.coordConfig.getDouble("rules.fromHall.z"), Files.coordConfig.getInt("rules.fromHall.pitch"),
			Files.coordConfig.getInt("rules.fromHall.yaw"));

	// Settings and Rules => Hall
	public static Location hfSR = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("hall.fromSetRul.x"), Files.coordConfig.getDouble("hall.fromSetRul.y"),
			Files.coordConfig.getDouble("hall.fromSetRul.z"), Files.coordConfig.getInt("hall.fromSetRul.pitch"),
			Files.coordConfig.getInt("hall.fromSetRul.yaw"));

	// CasterAppEvents
	public static Location hall = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("hall.spawn.x"), Files.coordConfig.getDouble("hall.spawn.y"),
			Files.coordConfig.getDouble("hall.spawn.z"), 180, 0);
	public static Location shopO = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.shop.x"), Files.coordConfig.getDouble("teams.one.shop.y"),
			Files.coordConfig.getDouble("teams.one.shop.z"));
	public static Location shopT = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.shop.x"), Files.coordConfig.getDouble("teams.two.shop.y"),
			Files.coordConfig.getDouble("teams.two.shop.z"));
	public static Location settings = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("settingsLoc.fromHall.x"),
			Files.coordConfig.getDouble("settingsLoc.fromHall.y"),
			Files.coordConfig.getDouble("settingsLoc.fromHall.z"), 90, 0);
	public static Location admin = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("admin.x"), Files.coordConfig.getDouble("admin.y"),
			Files.coordConfig.getDouble("admin.z"));
	public static Location rules = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("rules.fromHall.x"), Files.coordConfig.getDouble("rules.fromHall.y"),
			Files.coordConfig.getDouble("rules.fromHall.z"), Files.coordConfig.getInt("rules.fromHall.pitch"),
			Files.coordConfig.getInt("rules.fromHall.yaw"));
	public static Location logo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("logo.x"), Files.coordConfig.getDouble("logo.y"),
			Files.coordConfig.getDouble("logo.z"), Files.coordConfig.getInt("logo.pitch"),
			Files.coordConfig.getInt("logo.yaw"));

	// Arena Loading
	public static Location smallArenaE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			-99, 8, -90);
	public static Location smallArenaW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 42,
			8, -90);
	public static Location largeArenaE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			-109, 8, -90);
	public static Location largeArenaW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 32,
			8, -90);
	public static Location emptyE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 31, 7,
			-90);
	public static Location emptyW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -109, 7,
			-90);
	public static Location oneAtkBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -74,
			14, -79);
	public static Location twoAtkBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 66,
			14, -79);

	// Def and Fight Location
	public static Location oAtk = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.atk.x") + Vars.r.nextInt(6),
			Files.coordConfig.getDouble("teams.one.atk.y"),
			Files.coordConfig.getDouble("teams.one.atk.z") + Vars.r.nextInt(2),
			Files.coordConfig.getInt("teams.one.atk.pitch"), Files.coordConfig.getInt("teams.one.atk.yaw"));
	public static Location oDefS = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.defSmall.x") + Vars.r.nextInt(12),
			Files.coordConfig.getDouble("teams.one.defSmall.y"),
			Files.coordConfig.getDouble("teams.one.defSmall.z") + Vars.r.nextInt(6),
			Files.coordConfig.getInt("teams.one.defSmall.pitch"), Files.coordConfig.getInt("teams.one.defSmall.yaw"));
	public static Location oDefL = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.defLarge.x") + Vars.r.nextInt(20),
			Files.coordConfig.getDouble("teams.one.defLarge.y"),
			Files.coordConfig.getDouble("teams.one.defLarge.z") + Vars.r.nextInt(10),
			Files.coordConfig.getInt("teams.one.defLarge.pitch"), Files.coordConfig.getInt("teams.one.defLarge.yaw"));
	public static Location tAtk = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.atk.x") + Vars.r.nextInt(6),
			Files.coordConfig.getDouble("teams.two.atk.y"),
			Files.coordConfig.getDouble("teams.two.atk.z") + Vars.r.nextInt(2),
			Files.coordConfig.getInt("teams.two.atk.pitch"), Files.coordConfig.getInt("teams.two.atk.yaw"));
	public static Location tDefS = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.defSmall.x") + Vars.r.nextInt(12),
			Files.coordConfig.getDouble("teams.two.defSmall.y"),
			Files.coordConfig.getDouble("teams.two.defSmall.z") + Vars.r.nextInt(6),
			Files.coordConfig.getInt("teams.two.defSmall.pitch"), Files.coordConfig.getInt("teams.two.defSmall.yaw"));
	public static Location tDefL = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.defLarge.x") + Vars.r.nextInt(20),
			Files.coordConfig.getDouble("teams.one.defLarge.y"),
			Files.coordConfig.getDouble("teams.one.defLarge.z") + Vars.r.nextInt(10),
			Files.coordConfig.getInt("teams.one.defLarge.pitch"), Files.coordConfig.getInt("teams.one.defLarge.yaw"));

	// Countdowns Launch to Prep
	public static Location oShop = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.shop.x"), Files.coordConfig.getDouble("teams.one.shop.y"),
			Files.coordConfig.getDouble("teams.one.shop.z"));
	public static Location tShop = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.shop.x"), Files.coordConfig.getDouble("teams.two.shop.y"),
			Files.coordConfig.getDouble("teams.two.shop.z"));
	public static Location casterShop = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.caster.shop.x"), Files.coordConfig.getDouble("teams.caster.shop.y"),
			Files.coordConfig.getDouble("teams.caster.shop.z"), Files.coordConfig.getInt("teams.caster.shop.pitch"),
			Files.coordConfig.getInt("teams.caster.shop.yaw"));

	// Countdowns Prep to Def
	public static Location casterFigO = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.caster.figO.x"), Files.coordConfig.getDouble("teams.caster.figO.y"),
			Files.coordConfig.getDouble("teams.caster.figO.z"), Files.coordConfig.getInt("teams.caster.figO.pitch"),
			Files.coordConfig.getInt("teams.caster.figO.yaw"));
	public static Location casterFigT = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.caster.figT.x"), Files.coordConfig.getDouble("teams.caster.figT.y"),
			Files.coordConfig.getDouble("teams.caster.figT.z"), Files.coordConfig.getInt("teams.caster.figT.pitch"),
			Files.coordConfig.getInt("teams.caster.figT.yaw"));

	// Locations Score
	public static Location oneScoreNW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.one.NW.x"), Files.coordConfig.getInt("score.one.NW.y"),
			Files.coordConfig.getInt("score.one.NW.z"));
	public static Location oneScoreSW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.one.SW.x"), Files.coordConfig.getInt("score.one.SW.y"),
			Files.coordConfig.getInt("score.one.SW.z"));
	public static Location oneScoreNE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.one.NE.x"), Files.coordConfig.getInt("score.one.NE.y"),
			Files.coordConfig.getInt("score.one.NE.z"));
	public static Location oneScoreSE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.one.SE.x"), Files.coordConfig.getInt("score.one.SE.y"),
			Files.coordConfig.getInt("score.one.SE.z"));

	public static Location twoScoreNW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.two.NW.x"), Files.coordConfig.getInt("score.two.NW.y"),
			Files.coordConfig.getInt("score.two.NW.z"));
	public static Location twoScoreSW = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.two.SW.x"), Files.coordConfig.getInt("score.two.SW.y"),
			Files.coordConfig.getInt("score.two.SW.z"));
	public static Location twoScoreNE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.two.NE.x"), Files.coordConfig.getInt("score.two.NE.y"),
			Files.coordConfig.getInt("score.two.NE.z"));
	public static Location twoScoreSE = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("score.two.SE.x"), Files.coordConfig.getInt("score.two.SE.y"),
			Files.coordConfig.getInt("score.two.SE.z"));

	// Holo, Lamp and Players slots in score room

	public static Location oneScRoomHolo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("scoreRoom.holo.one.x"), Files.coordConfig.getDouble("scoreRoom.holo.one.y"),
			Files.coordConfig.getDouble("scoreRoom.holo.one.z"));
	public static Location twoScRoomHolo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("scoreRoom.holo.two.x"), Files.coordConfig.getDouble("scoreRoom.holo.two.y"),
			Files.coordConfig.getDouble("scoreRoom.holo.two.z"));
	public static Location casterOneScRoomHolo = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("scoreRoom.holo.casterOne.x"),
			Files.coordConfig.getDouble("scoreRoom.holo.casterOne.y"),
			Files.coordConfig.getDouble("scoreRoom.holo.casterOne.z"));
	public static Location casterTwoScRoomHolo = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("scoreRoom.holo.casterTwo.x"),
			Files.coordConfig.getDouble("scoreRoom.holo.casterTwo.y"),
			Files.coordConfig.getDouble("scoreRoom.holo.casterTwo.z"));

	public static Location oneScRoomLamp = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("scoreRoom.lamp.one.x"), Files.coordConfig.getInt("scoreRoom.lamp.one.y"),
			Files.coordConfig.getInt("scoreRoom.lamp.one.z"));
	public static Location twoScRoomLamp = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("scoreRoom.lamp.two.x"), Files.coordConfig.getInt("scoreRoom.lamp.two.y"),
			Files.coordConfig.getInt("scoreRoom.lamp.two.z"));
	public static Location casterOneScRoomLamp = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("scoreRoom.lamp.casterOne.x"),
			Files.coordConfig.getInt("scoreRoom.lamp.casterOne.y"),
			Files.coordConfig.getInt("scoreRoom.lamp.casterOne.z"));
	public static Location casterTwoScRoomLamp = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getInt("scoreRoom.lamp.casterTwo.x"),
			Files.coordConfig.getInt("scoreRoom.lamp.casterTwo.y"),
			Files.coordConfig.getInt("scoreRoom.lamp.casterTwo.z"));

	public static Location oneScRoomSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.scoreRoom.1.x"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.1.y"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.1.z"),
			Files.coordConfig.getInt("teams.one.scoreRoom.1.pitch"),
			Files.coordConfig.getInt("teams.one.scoreRoom.1.yaw"));
	public static Location oneScRoomSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.scoreRoom.2.x"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.2.y"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.2.z"),
			Files.coordConfig.getInt("teams.one.scoreRoom.2.pitch"),
			Files.coordConfig.getInt("teams.one.scoreRoom.2.yaw"));
	public static Location oneScRoomSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.scoreRoom.3.x"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.3.y"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.3.z"),
			Files.coordConfig.getInt("teams.one.scoreRoom.3.pitch"),
			Files.coordConfig.getInt("teams.one.scoreRoom.3.yaw"));
	public static Location oneScRoomSlot4 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.scoreRoom.4.x"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.4.y"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.4.z"),
			Files.coordConfig.getInt("teams.one.scoreRoom.4.pitch"),
			Files.coordConfig.getInt("teams.one.scoreRoom.4.yaw"));
	public static Location oneScRoomSlot5 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.one.scoreRoom.5.x"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.5.y"),
			Files.coordConfig.getDouble("teams.one.scoreRoom.5.z"),
			Files.coordConfig.getInt("teams.one.scoreRoom.5.pitch"),
			Files.coordConfig.getInt("teams.one.scoreRoom.5.yaw"));

	public static Location twoScRoomSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.scoreRoom.1.x"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.1.y"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.1.z"),
			Files.coordConfig.getInt("teams.two.scoreRoom.1.pitch"),
			Files.coordConfig.getInt("teams.two.scoreRoom.1.yaw"));
	public static Location twoScRoomSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.scoreRoom.2.x"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.2.y"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.2.z"),
			Files.coordConfig.getInt("teams.two.scoreRoom.2.pitch"),
			Files.coordConfig.getInt("teams.two.scoreRoom.2.yaw"));
	public static Location twoScRoomSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.scoreRoom.3.x"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.3.y"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.3.z"),
			Files.coordConfig.getInt("teams.two.scoreRoom.3.pitch"),
			Files.coordConfig.getInt("teams.two.scoreRoom.3.yaw"));
	public static Location twoScRoomSlot4 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.scoreRoom.4.x"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.4.y"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.4.z"),
			Files.coordConfig.getInt("teams.two.scoreRoom.4.pitch"),
			Files.coordConfig.getInt("teams.two.scoreRoom.4.yaw"));
	public static Location twoScRoomSlot5 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.two.scoreRoom.5.x"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.5.y"),
			Files.coordConfig.getDouble("teams.two.scoreRoom.5.z"),
			Files.coordConfig.getInt("teams.two.scoreRoom.5.pitch"),
			Files.coordConfig.getInt("teams.two.scoreRoom.5.yaw"));

	public static Location casterScRoomSlot = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")),
			Files.coordConfig.getDouble("teams.caster.scoreRoom.x"),
			Files.coordConfig.getDouble("teams.caster.scoreRoom.y"),
			Files.coordConfig.getDouble("teams.caster.scoreRoom.z"),
			Files.coordConfig.getInt("teams.caster.scoreRoom.pitch"),
			Files.coordConfig.getInt("teams.caster.scoreRoom.yaw"));

	public static Location casterPreviousRound = new Location(
			Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), Files.coordConfig.getDouble("prevRd.x"),
			Files.coordConfig.getDouble("prevRd.y"), Files.coordConfig.getDouble("prevRd.z"));

	
	// VICTORY LOCATIONS
	public static Location podiumSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -0.5, 131.5, -79.5, 0, 0);
	public static Location podiumSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5, 131.5, -79.5, 0, 0);
	public static Location podiumSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 1.5, 131.5, -79.5, 0, 0);
	
	public static Location loserSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -0.5, 131, -73.5, 180, -10);
	public static Location loserSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5, 131, -73.5, 180, -10);
	public static Location loserSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 1.5, 131, -73.5, 180, -10);
	
	public static Location casterSlot = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5, 131, -74.5, 180, -8);
	
	public static Location specSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -1.5, 131, -74.5, -160, -8);
	public static Location specSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 2.5, 131, -74.5, 160, -8);
	
	public static Location teamWin = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5, 136, -80);
	public static Location pWin1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -0.5, 135.5, -80);
	public static Location pWin2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0.5, 135, -80);
	public static Location pWin3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 1.5, 135.5, -80);
	
	public static Location settingsGame = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 4, 137, -66);
	public static Location statsGame = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 0, 137, -66);
	
	// MVP LOCATIONS
	public static Location mvpStats = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -4, 134, -66);

}
