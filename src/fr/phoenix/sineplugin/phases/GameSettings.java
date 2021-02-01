package fr.phoenix.sineplugin.phases;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import fr.phoenix.sineplugin.Arena;
import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.regions.RegionsWG;

public class GameSettings implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSettingsInteract(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();
		Block b = e.getClickedBlock();

		if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {

		}

		if (a == Action.RIGHT_CLICK_BLOCK) {
			Block sB = p.getWorld().getBlockAt(b.getX(), b.getY() + 1, b.getZ());
			BlockState sBBS = sB.getState();
			if (b.getType() == Material.STONE_BUTTON) {
				if (sBBS instanceof Sign) {
					Sign dataSign = (Sign) sBBS;
					if (b.getLocation().equals(Tp.equiClic)) {
						if (dataSign.getLine(2).equalsIgnoreCase("§lOFF")) {
							dataSign.setLine(2, "§lON");
							dataSign.update();
							Files.config.set("settings.game.equi", "&9Balance Teams : &cON");
							Main.getInstance().saveDefaultConfig();
							Files.config.set("options.equi", 0);
							
						} else {
							dataSign.setLine(2, "§lOFF");
							dataSign.update();
							Files.config.set("settings.game.equi", "&9Balance Teams : &cOFF");
							Main.getInstance().saveDefaultConfig();
							Files.config.set("options.equi", 1);
							
						}
					}
					if (b.getLocation().equals(Tp.ffClic)) {
						if (dataSign.getLine(2).equalsIgnoreCase("§lOFF")) {
							dataSign.setLine(2, "§lON");
							dataSign.update();
							Vars.teamOne.setAllowFriendlyFire(true);
							Vars.teamTwo.setAllowFriendlyFire(true);
							Vars.oneTeamOne.setAllowFriendlyFire(true);
							Vars.oneTeamTwo.setAllowFriendlyFire(true);
							Vars.twoTeamOne.setAllowFriendlyFire(true);
							Vars.twoTeamTwo.setAllowFriendlyFire(true);
							Vars.casterTeamOne.setAllowFriendlyFire(true);
							Vars.casterTeamTwo.setAllowFriendlyFire(true);
							Files.config.set("settings.game.ff", "&9FriendlyFire : &cON");							
							Main.getInstance().saveDefaultConfig();
							
						} else {
							dataSign.setLine(2, "§lOFF");
							dataSign.update();
							Vars.teamOne.setAllowFriendlyFire(false);
							Vars.teamTwo.setAllowFriendlyFire(false);
							Vars.oneTeamOne.setAllowFriendlyFire(false);
							Vars.oneTeamTwo.setAllowFriendlyFire(false);
							Vars.twoTeamOne.setAllowFriendlyFire(false);
							Vars.twoTeamTwo.setAllowFriendlyFire(false);
							Vars.casterTeamOne.setAllowFriendlyFire(false);
							Vars.casterTeamTwo.setAllowFriendlyFire(false);
							Files.config.set("settings.game.ff", "&9FriendlyFire : &cOFF");							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.invisClic)) {
						if (dataSign.getLine(2).equalsIgnoreCase("§lOFF")) {
							dataSign.setLine(2, "§lON");
							dataSign.update();
							Vars.teamOne.setCanSeeFriendlyInvisibles(true);
							Vars.teamTwo.setCanSeeFriendlyInvisibles(true);
							Vars.oneTeamOne.setCanSeeFriendlyInvisibles(true);
							Vars.oneTeamTwo.setCanSeeFriendlyInvisibles(true);
							Vars.twoTeamOne.setCanSeeFriendlyInvisibles(true);
							Vars.twoTeamTwo.setCanSeeFriendlyInvisibles(true);
							Vars.casterTeamOne.setCanSeeFriendlyInvisibles(true);
							Vars.casterTeamTwo.setCanSeeFriendlyInvisibles(true);
							Files.config.set("settings.game.invis", "&9Invisible Mates : &cON");							
							Main.getInstance().saveDefaultConfig();
							
						} else {
							dataSign.setLine(2, "§lOFF");
							dataSign.update();
							Vars.teamOne.setCanSeeFriendlyInvisibles(false);
							Vars.teamTwo.setCanSeeFriendlyInvisibles(false);
							Vars.oneTeamOne.setCanSeeFriendlyInvisibles(false);
							Vars.oneTeamTwo.setCanSeeFriendlyInvisibles(false);
							Vars.twoTeamOne.setCanSeeFriendlyInvisibles(false);
							Vars.twoTeamTwo.setCanSeeFriendlyInvisibles(false);
							Vars.casterTeamOne.setCanSeeFriendlyInvisibles(false);
							Vars.casterTeamTwo.setCanSeeFriendlyInvisibles(false);
							Files.config.set("settings.game.invis", "&9Invisible Mates : &cOFF");							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.mapClic)) {
						if (dataSign.getLine(1)
								.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.name.a").replace("&", "§"))) {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.name.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.map",
									"&9Map : " + Files.setsNameConfig.getString("settings.maps.name.b").replace("&l", ""));
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();
							
						} else if (dataSign.getLine(1)
								.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.name.b").replace("&", "§"))) {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.name.c").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.map",
									"&9Map : " + Files.setsNameConfig.getString("settings.maps.name.c").replace("&l", ""));						
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();	
							
						} else if (dataSign.getLine(1)
								.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.name.c").replace("&", "§"))) {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.name.d").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.map",
									"&9Map : " + Files.setsNameConfig.getString("settings.maps.name.d").replace("&l", ""));
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();
						} else if (dataSign.getLine(1)
								.equalsIgnoreCase(Files.setsNameConfig.getString("settings.maps.name.d").replace("&", "§"))) {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.name.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.map",
									"&9Map : " + Files.setsNameConfig.getString("settings.maps.name.a").replace("&l", ""));
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();
						}
					}
					if (b.getLocation().equals(Tp.sizeClic)) {
						if (dataSign.getLine(0).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&", "§"))) {
							dataSign.setLine(0, Files.setsNameConfig.getString("settings.maps.sizeName.b").replace("&", "§"));
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.sizePlay.b").replace("&", "§"));
							dataSign.setLine(3, Files.setsNameConfig.getString("settings.maps.sizeDim.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.size", "&9Map Size : "
									+ Files.setsNameConfig.getString("settings.maps.sizeName.b").replace("&l", ""));
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();
							RegionContainer container = Main.getInstance().getWorldGuard().getRegionContainer();
							RegionManager regions = container.get((World) Bukkit.getWorld(Files.setsNameConfig.getString("world.name")));
							regions.addRegion(RegionsWG.oneLargeZone);
							regions.addRegion(RegionsWG.twoLargeZone);
							regions.addRegion(RegionsWG.oneLargeDef);
							regions.addRegion(RegionsWG.twoLargeDef);
							new RegionsWG().setFlag(RegionsWG.oneLargeZone, RegionsWG.oneLargeDef);
							new RegionsWG().setFlag(RegionsWG.twoLargeZone, RegionsWG.twoLargeDef);
							if (regions.hasRegion("oneSmallZone"))	regions.removeRegion("oneSmallZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("twoSmallZone"))	regions.removeRegion("twoSmallZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("oneSmallDef"))	regions.removeRegion("oneSmallDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("twoSmallDef"))	regions.removeRegion("twoSmallDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							
						} else if (dataSign.getLine(0).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.maps.sizeName.b").replace("&", "§"))) {
							dataSign.setLine(0, Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&", "§"));
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.maps.sizePlay.a").replace("&", "§"));
							dataSign.setLine(3, Files.setsNameConfig.getString("settings.maps.sizeDim.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.size", "&9Map Size : "
									+ Files.setsNameConfig.getString("settings.maps.sizeName.a").replace("&l", ""));							
							Main.getInstance().saveDefaultConfig();
							new Arena().chooseArena();
							RegionContainer container = Main.getInstance().getWorldGuard().getRegionContainer();
							RegionManager regions = container.get((World) Bukkit.getWorld(Files.setsNameConfig.getString("world.name")));
							regions.addRegion(RegionsWG.oneSmallZone);
							RegionsWG.oneSmallZone.setFlag(DefaultFlag.HEAL_AMOUNT, 0);
							regions.addRegion(RegionsWG.twoSmallZone);
							regions.addRegion(RegionsWG.oneSmallDef);
							regions.addRegion(RegionsWG.twoSmallDef);
							new RegionsWG().setFlag(RegionsWG.oneSmallZone, RegionsWG.oneSmallDef);
							new RegionsWG().setFlag(RegionsWG.twoSmallZone, RegionsWG.twoSmallDef);
							if (regions.hasRegion("oneLargeZone"))	regions.removeRegion("oneLargeZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("twoLargeZone"))	regions.removeRegion("twoLargeZone", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("oneLargeDef"))	regions.removeRegion("oneLargeDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
							if (regions.hasRegion("twoLargeDef"))	regions.removeRegion("twoLargeDef", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
						}
					}
					if (b.getLocation().equals(Tp.defClic)) {
						if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.defSecs.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.def", "&9Defense : &c"
									+ Files.setsNameConfig.getString("settings.timers.defSecs.b").replace("&l", ""));
							Files.config.set("options.def", Files.setsNameConfig.getInt("settings.timers.defNum.b"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.defSecs.b").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.defSecs.c").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.def", "&9Defense : &c"
									+ Files.setsNameConfig.getString("settings.timers.defSecs.c").replace("&l", ""));
							Files.config.set("options.def", Files.setsNameConfig.getInt("settings.timers.defNum.c"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.defSecs.c").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.def", "&9Defense : &c"
									+ Files.setsNameConfig.getString("settings.timers.defSecs.a").replace("&l", ""));
							Files.config.set("options.def", Files.setsNameConfig.getInt("settings.timers.defNum.a"));							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.fightClic)) {
						if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.fightSecs.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.fight", "&9Fight : &c"
									+ Files.setsNameConfig.getString("settings.timers.fightSecs.b").replace("&l", ""));
							Files.config.set("options.fight", Files.setsNameConfig.getInt("settings.timers.fightNum.b"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.fightSecs.b").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.fightSecs.c").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.fight", "&9Fight : &c"
									+ Files.setsNameConfig.getString("settings.timers.fightSecs.c").replace("&l", ""));
							Files.config.set("options.fight", Files.setsNameConfig.getInt("settings.timers.fightNum.c"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.fightSecs.c").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.fightSecs.d").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.fight", "&9Fight : &c"
									+ Files.setsNameConfig.getString("settings.timers.fightSecs.d").replace("&l", ""));
							Files.config.set("options.fight", Files.setsNameConfig.getInt("settings.timers.fightNum.d"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.fightSecs.d").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.fight", "&9Fight : &c"
									+ Files.setsNameConfig.getString("settings.timers.fightSecs.a").replace("&l", ""));
							Files.config.set("options.fight", Files.setsNameConfig.getInt("settings.timers.fightNum.a"));							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.prepClic)) {
						if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.prepSecs.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.prep", "&9Preparation : &c"
									+ Files.setsNameConfig.getString("settings.timers.prepSecs.b").replace("&l", ""));
							Files.config.set("options.prep", Files.setsNameConfig.getInt("settings.timers.prepNum.b"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.prepSecs.b").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.prepSecs.c").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.prep", "&9Preparation : &c"
									+ Files.setsNameConfig.getString("settings.timers.prepSecs.c").replace("&l", ""));
							Files.config.set("options.prep", Files.setsNameConfig.getInt("settings.timers.prepNum.c"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.prepSecs.c").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.prepSecs.d").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.prep", "&9Preparation : &c"
									+ Files.setsNameConfig.getString("settings.timers.prepSecs.d").replace("&l", ""));
							Files.config.set("options.prep", Files.setsNameConfig.getInt("settings.timers.prepNum.d"));							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(3).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.timers.prepSecs.d").replace("&", "§"))) {
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.prep", "&9Preparation : &c"
									+ Files.setsNameConfig.getString("settings.timers.prepSecs.a").replace("&l", ""));
							Files.config.set("options.prep", Files.setsNameConfig.getInt("settings.timers.prepNum.a"));							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.rankClic)) {
						if (dataSign.getLine(1)
								.equalsIgnoreCase(Files.setsNameConfig.getString("settings.game.rank.a").replace("&", "§"))) {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.game.rank.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.rank",
									"&9Game : &c" + Files.setsNameConfig.getString("settings.game.rank.b").replace("&l", ""));							
							Main.getInstance().saveDefaultConfig();
							
						} else {
							dataSign.setLine(1, Files.setsNameConfig.getString("settings.game.rank.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.rank",
									"&9Game : &c" + Files.setsNameConfig.getString("settings.game.rank.a").replace("&l", ""));							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.dayClic)) {
						if (dataSign.getLine(1).equalsIgnoreCase("§c§lDAY")) {
							dataSign.setLine(1, "§c§lNIGHT");
							dataSign.update();
							p.getWorld().setTime(18000);
							p.getWorld().setGameRuleValue("doDaylightCycle", "false");
							Files.config.set("settings.game.time", "&9Time : &cNIGHT");							
							Main.getInstance().saveDefaultConfig();
							
						} else {
							dataSign.setLine(1, "§c§lDAY");
							dataSign.update();
							p.getWorld().setTime(6000);
							p.getWorld().setGameRuleValue("doDaylightCycle", "false");
							Files.config.set("settings.game.time", "&9Time : &cDAY");							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.weatherClic)) {
						if (dataSign.getLine(1).equalsIgnoreCase("§c§lSUN")) {
							dataSign.setLine(1, "§c§lTHUNDER");
							dataSign.update();
							p.getWorld().setStorm(true);
							p.getWorld().setThundering(true);
							p.getWorld().setWeatherDuration(2000000);
							Files.config.set("settings.game.weather", "&9Weather : &cTHUNDER");							
							Main.getInstance().saveDefaultConfig();
							
						} else {
							dataSign.setLine(1, "§c§lSUN");
							dataSign.update();
							p.getWorld().setStorm(false);
							p.getWorld().setThundering(false);
							p.getWorld().setWeatherDuration(2000000);
							Files.config.set("settings.game.weather", "&9Weather : &cSUN");							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.beginClic)) {
						if (dataSign.getLine(2).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.game.beginOrder.c").replace("&", "§"))) {
							dataSign.setLine(2,
									Files.setsNameConfig.getString("settings.game.beginOrder.a").replace("&", "§"));
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.game.beginOrder.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.begin",
									"&9Game Begin : &c"
											+ Files.setsNameConfig.getString("settings.game.beginOrder.a").replace("&l", "")
													.replace("&0", "&f")
											+ " &9/ " + Files.setsNameConfig.getString("settings.game.beginOrder.b")
													.replace("&l", "").replace("&0", "&f"));
							Files.config.set("options.begin", 0);	
							Vars.whoBegin = "OaTd";
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(2).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.game.beginOrder.a").replace("&", "§"))) {
							dataSign.setLine(2,
									Files.setsNameConfig.getString("settings.game.beginOrder.c").replace("&", "§"));
							dataSign.setLine(3,
									Files.setsNameConfig.getString("settings.game.beginOrder.d").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.begin",
									"&9Game Begin : &c"
											+ Files.setsNameConfig.getString("settings.game.beginOrder.c").replace("&l", "")
													.replace("&0", "&f")
											+ " &9/ " + Files.setsNameConfig.getString("settings.game.beginOrder.d")
													.replace("&l", "").replace("&0", "&f"));
							Files.config.set("options.begin", 1);
							Vars.whoBegin = "OdTa";
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.goldsClic)) {
						if (dataSign.getLine(2).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&", "§"))) {
							dataSign.setLine(2,
									Files.setsNameConfig.getString("settings.game.goldsMulti.b").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.golds", "&9Golds Multiplicator : &c"
									+ Files.setsNameConfig.getString("settings.game.goldsMulti.b").replace("&l", ""));
							Files.config.set("options.golds", 2);							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(2).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.game.goldsMulti.b").replace("&", "§"))) {
							dataSign.setLine(2,
									Files.setsNameConfig.getString("settings.game.goldsMulti.c").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.golds", "&9Golds Multiplicator : &c"
									+ Files.setsNameConfig.getString("settings.game.goldsMulti.c").replace("&l", ""));
							Files.config.set("options.golds", 3);							
							Main.getInstance().saveDefaultConfig();
							
						} else if (dataSign.getLine(2).equalsIgnoreCase(
								Files.setsNameConfig.getString("settings.game.goldsMulti.c").replace("&", "§"))) {
							dataSign.setLine(2,
									Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&", "§"));
							dataSign.update();
							Files.config.set("settings.game.golds", "&9Golds Multiplicator : &c"
									+ Files.setsNameConfig.getString("settings.game.goldsMulti.a").replace("&l", ""));
							Files.config.set("options.golds", 1);							
							Main.getInstance().saveDefaultConfig();
						}
					}
					if (b.getLocation().equals(Tp.broadClic)) {
						Main.getInstance().saveDefaultConfig();
						Bukkit.broadcastMessage(Files.config.getString("settings.default.gene").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.rank").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.time").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.weather").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.begin").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.golds").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.default.timers").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.prep").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.fight").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.def").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.default.maps").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.map").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.size").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.default.teams").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.equi").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.ff").replace("&", "§"));
						Bukkit.broadcastMessage(Files.config.getString("settings.game.invis").replace("&", "§"));
					}
				}
			}
		}

	}
}
