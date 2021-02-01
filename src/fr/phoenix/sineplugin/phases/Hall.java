package fr.phoenix.sineplugin.phases;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterHotbar;
import fr.phoenix.sineplugin.news.NewsManager;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class Hall implements Listener {

	private int nbrTeamOne = 0, nbrTeamTwo = 0;
	protected int oRdy, tRdy, cORdy, cTRdy;
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();
		Block b = e.getClickedBlock();

		Block lamp = Bukkit.getServer().getWorld(Files.setsNameConfig.getString("world.name")).getBlockAt(Files.coordConfig.getInt("hall.lamp.x"), Files.coordConfig.getInt("hall.lamp.y"), Files.coordConfig.getInt("hall.lamp.z"));
		
		Block bOne = p.getWorld().getBlockAt(Tp.bLocO);
		Block bTwo = p.getWorld().getBlockAt(Tp.bLocT);

		if (a == Action.RIGHT_CLICK_BLOCK) {
			BlockState bOs = bOne.getState();
			BlockState bTs = bTwo.getState();
			BlockState bs = b.getState();
			if (bs instanceof Sign && bOs instanceof Sign && bTs instanceof Sign) {
				Sign signO = (Sign) bOs;
				Sign signT = (Sign) bTs;
				if (b.getLocation().equals(Tp.bLocO)) {
					if (signO.getLine(2).equalsIgnoreCase("§lgame...")) {
						if (Files.config.getInt("options.cancel") == 0) {
							p.sendMessage("§0[§4SINE§0] §7§oGame launching in progress...");
						} else if (Files.config.getInt("options.cancel") == 1) {
							signO.setLine(1, "§lReady ?");
							signO.setLine(2, "§lClick me !");
							signO.update();
							lamp.setType(Material.AIR);
							Bukkit.getServer().getScheduler().cancelTask(Countdown.launch);
							Countdown.cdLaunchBar.removeAll();
							Objective nbrP = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("nbrP");
							nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
						}
					}
					if (oRdy < tRdy) {
						Bukkit.broadcastMessage("§0[§4SINE§0] §7§oStill need at least §f" + (tRdy - oRdy) + " §c"
								+ Files.setsNameConfig.getString("teams.display.one").replace("&", "§") + " §7§oplayers !");
					} else if (oRdy > tRdy) {
						Bukkit.broadcastMessage("§0[§4SINE§0] §7§oStill need at least §f" + (oRdy - tRdy) + " §a"
								+ Files.setsNameConfig.getString("teams.display.two").replace("&", "§") + " §7§oplayers !");
					} else if (cORdy == 1) {
						if (Files.config.getInt("options.cancel") == 1) {
							signO.setLine(1, "§lReady ?");
							signO.setLine(2, "§lClick me !");
							signO.update();
							cORdy = 0;
							lamp.setType(Material.AIR);
							Bukkit.getScheduler().cancelTask(Countdown.launch);
							Countdown.cdLaunchBar.removeAll();
							Objective nbrP = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("nbrP");
							nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
						}
					} else if (signO.getLine(1).equalsIgnoreCase("§lReady ?")) {
						if (Files.config.getInt("options.cancel") == 1) {
							signO.setLine(1, Files.setsNameConfig.getString("teams.display.one").replace("&", "§") + " §0§lTeam");
							signO.setLine(2, "§0§lREADY !");
							signO.update();
							cORdy = 1;
							if (cTRdy == 1) {
								signO.setLine(1, "§lLaunch");
								signO.setLine(2, "§lgame...");
								signO.update();
								signT.setLine(1, "§lLaunch");
								signT.setLine(2, "§lgame...");
								signT.update();
								for (String pG : Vars.pNameGamers) {
									Player plG = Bukkit.getServer().getPlayerExact(pG);
									NewsManager.newsBar.removePlayer(plG);
								}
								new Countdown().countDownLaunch(Files.setsNameConfig.getInt("settings.game.launch"));
							}
						}
					}
					if (cORdy == 0) {
						Block bDoorOb = p.getWorld().getBlockAt(Tp.doorOb);
						bDoorOb.setType(Material.AIR);
						Block bDoorOh = p.getWorld().getBlockAt(Tp.doorOh);
						bDoorOh.setType(Material.AIR);
						Block bDoorHOb = p.getWorld().getBlockAt(Tp.doorHOb);
						bDoorHOb.setType(Material.AIR);
						Block bDoorHOh = p.getWorld().getBlockAt(Tp.doorHOh);
						bDoorHOh.setType(Material.AIR);
					} else {
						Block bDoorOb = p.getWorld().getBlockAt(Tp.doorOb);
						bDoorOb.setType(Material.IRON_BARS);
						Block bDoorOh = p.getWorld().getBlockAt(Tp.doorOh);
						bDoorOh.setType(Material.IRON_BARS);
						Block bDoorHOb = p.getWorld().getBlockAt(Tp.doorHOb);
						bDoorHOb.setType(Material.IRON_BARS);
						Block bDoorHOh = p.getWorld().getBlockAt(Tp.doorHOh);
						bDoorHOh.setType(Material.IRON_BARS);
					}
				}

				if (b.getLocation().equals(Tp.bLocT)) {
					if (signT.getLine(2).equalsIgnoreCase("§lgame...")) {
						if (Files.config.getInt("options.cancel") == 0) {
							p.sendMessage("§0[§4SINE§0] §7§oGame launching in progress...");
						} else if (Files.config.getInt("settings.game.cancel") == 1) {
							signT.setLine(1, "§lReady ?");
							signT.setLine(2, "§lClick me !");
							signT.update();
							lamp.setType(Material.AIR);
							Bukkit.getScheduler().cancelTask(Countdown.launch);
							Countdown.cdLaunchBar.removeAll();
							Objective nbrP = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("nbrP");
							nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
						}
					}
					if (oRdy < tRdy) {
						Bukkit.broadcastMessage("§0[§4SINE§0] §7§oStill need at least §f" + (tRdy - oRdy) + " §c"
								+ Files.setsNameConfig.getString("teams.display.one").replace("&", "§") + " §7§oplayers !");
					} else if (oRdy > tRdy) {
						Bukkit.broadcastMessage("§0[§4SINE§0] §7§oStill need at least §f" + (oRdy - tRdy) + " §a"
								+ Files.setsNameConfig.getString("teams.display.two").replace("&", "§") + " §7§oplayers !");
					} else if (cTRdy == 1) {
						if (Files.config.getInt("options.cancel") == 1) {
							signT.setLine(1, "§lReady ?");
							signT.setLine(2, "§lClick me !");
							signT.update();
							cTRdy = 0;
							lamp.setType(Material.AIR);
							Bukkit.getScheduler().cancelTask(Countdown.launch);
							Countdown.cdLaunchBar.removeAll();
							Objective nbrP = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("nbrP");
							nbrP.setDisplayName(" §6SINE Alpha v0.3 Test : ");
						}
					} else if (signT.getLine(1).equalsIgnoreCase("§lReady ?")) {
						if (Files.config.getInt("options.cancel") == 1) {
							signT.setLine(1, Files.setsNameConfig.getString("teams.display.two").replace("&", "§") + " §0§lTeam");
							signT.setLine(2, "§0§lREADY !");
							signT.update();
							cTRdy = 1;
							if (cORdy == 1) {
								signT.setLine(1, "§lLaunch");
								signT.setLine(2, "§lgame...");
								signT.update();
								signO.setLine(1, "§lLaunch");
								signO.setLine(2, "§lgame...");
								signO.update();
								for (String pG : Vars.pNameGamers) {
									Player plG = Bukkit.getServer().getPlayerExact(pG);
									NewsManager.newsBar.removePlayer(plG);
								}
								new Countdown().countDownLaunch(Files.setsNameConfig.getInt("settings.game.launch"));								
							}
						}
					}
					if (cTRdy == 0) {
						Block bDoorTb = p.getWorld().getBlockAt(Tp.doorTb);
						bDoorTb.setType(Material.AIR);
						Block bDoorTh = p.getWorld().getBlockAt(Tp.doorTh);
						bDoorTh.setType(Material.AIR);
						Block bDoorHTb = p.getWorld().getBlockAt(Tp.doorHTb);
						bDoorHTb.setType(Material.AIR);
						Block bDoorHTh = p.getWorld().getBlockAt(Tp.doorHTh);
						bDoorHTh.setType(Material.AIR);
					} else {
						Block bDoorTb = p.getWorld().getBlockAt(Tp.doorTb);
						bDoorTb.setType(Material.IRON_BARS);
						Block bDoorTh = p.getWorld().getBlockAt(Tp.doorTh);
						bDoorTh.setType(Material.IRON_BARS);
						Block bDoorHTb = p.getWorld().getBlockAt(Tp.doorHTb);
						bDoorHTb.setType(Material.IRON_BARS);
						Block bDoorHTh = p.getWorld().getBlockAt(Tp.doorHTh);
						bDoorHTh.setType(Material.IRON_BARS);
					}
				}
			}

		}
	}

	@EventHandler
	public void onEnterTeleporters(RegionEnterEvent e) {

		Player p = e.getPlayer();
		
		// Setup TeamOne Players Sign
		Block oS = p.getWorld().getBlockAt(Files.coordConfig.getInt("teams.one.nbrSign.x"), Files.coordConfig.getInt("teams.one.nbrSign.y"),
				Files.coordConfig.getInt("teams.one.nbrSign.z"));
		BlockState obs = oS.getState();
		Sign oneSign = (Sign) obs;
		if (nbrTeamOne == 0) {
			oneSign.setLine(3, "§c§l" + nbrTeamOne);
			oneSign.update();
		}

		// Setup TeamTwo Players Sign
		Block tS = p.getWorld().getBlockAt(Files.coordConfig.getInt("teams.two.nbrSign.x"), Files.coordConfig.getInt("teams.two.nbrSign.y"),
				Files.coordConfig.getInt("teams.two.nbrSign.z"));
		BlockState tbs = tS.getState();
		Sign twoSign = (Sign) tbs;
		if (nbrTeamTwo == 0) {
			twoSign.setLine(3, "§a§l" + nbrTeamTwo);
			twoSign.update();
		}

		// Teleporters list :

		// Hall => TeamOne
		if (e.getRegion().getId().equals("halltoteamone")) {
			p.teleport(Tp.oTfH);
			Vars.teamOne.addEntry(p.getName());
			Vars.oneTeamOne.addEntry(p.getName());
			Vars.twoTeamOne.addEntry(p.getName());
			Vars.casterTeamOne.addEntry(p.getName());
			p.getInventory().clear();
			nbrTeamOne++;
			oneSign.setLine(3, "§c§l" + nbrTeamOne);
			oneSign.update();
			Score scNbrPO = Vars.nbrP.getScore(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
			scNbrPO.setScore(scNbrPO.getScore() + 1);
			oRdy++;
			if (!Vars.pNameTeamOne.containsKey(p.getName())) {
				Vars.pNameTeamOne.put(p.getName(), 1);
			}
			if (!Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.add(p.getName());
			}
			new PlayerStats().createProfileSeason(p);
		}

		// Hall => TeamTwo
		if (e.getRegion().getId().equals("halltoteamtwo")) {
			p.teleport(Tp.tTfH);
			Vars.teamTwo.addEntry(p.getName());
			Vars.oneTeamTwo.addEntry(p.getName());
			Vars.twoTeamTwo.addEntry(p.getName());
			Vars.casterTeamTwo.addEntry(p.getName());
			p.getInventory().clear();
			nbrTeamTwo++;
			twoSign.setLine(3, "§a§l" + nbrTeamTwo);
			twoSign.update();
			Score scNbrPT = Vars.nbrP.getScore(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
			scNbrPT.setScore(scNbrPT.getScore() + 1);
			tRdy++;
			if (!Vars.pNameTeamTwo.containsKey(p.getName())) {
				Vars.pNameTeamTwo.put(p.getName(), 1);
			}
			if (!Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.add(p.getName());
			}
			new PlayerStats().createProfileSeason(p);
		}
		
		// Hall => TeamThree
		if (e.getRegion().getId().equals("halltoteamthree")) {
			p.teleport(Tp.thTfH);
			p.getInventory().clear();
			p.setGameMode(GameMode.SPECTATOR);
			if (p.isOp()) {
				if (!Vars.pNameCaster.contains(p.getName())) {
					Vars.pNameCaster.add(p.getName());
					new CasterHotbar().casterStuffMenu(p, 1);
				}
				if (!Vars.pNameGamers.contains(p.getName())) {
					Vars.pNameGamers.add(p.getName());
				}
			} else if (!Vars.pNameSpec.contains(p.getName())) {
					Vars.pNameSpec.add(p.getName());
					new CasterHotbar().specStuffMenu(p, 1);
				}
			if (!Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.add(p.getName());
			}
		}
		
		// TeamOne => Hall
		if (e.getRegion().getId().equals("teamonetohall")) {
			p.teleport(Tp.hfT);
			Vars.teamOne.removeEntry(p.getName());
			Vars.oneTeamOne.removeEntry(p.getName());
			Vars.twoTeamOne.removeEntry(p.getName());
			Vars.casterTeamOne.removeEntry(p.getName());
			p.getInventory().clear();
			nbrTeamOne--;
			oneSign.setLine(3, "§c§l" + nbrTeamOne);
			oneSign.update();
			Score scNbrPO = Vars.nbrP.getScore(Files.setsNameConfig.getString("teams.fakePlayers.one").replace("&", "§"));
			scNbrPO.setScore(scNbrPO.getScore() - 1);
			oRdy--;
			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				Vars.pNameTeamOne.remove(p.getName());
			}
			if (Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.remove(p.getName());
			}
		}

		// TeamTwo => Hall
		if (e.getRegion().getId().equals("teamtwotohall")) {
			p.teleport(Tp.hfT);
			Vars.teamTwo.removeEntry(p.getName());
			Vars.oneTeamTwo.removeEntry(p.getName());
			Vars.twoTeamTwo.removeEntry(p.getName());
			Vars.casterTeamTwo.removeEntry(p.getName());
			p.getInventory().clear();
			nbrTeamTwo--;
			twoSign.setLine(3, "§a§l" + nbrTeamTwo);
			twoSign.update();
			Score scNbrPT = Vars.nbrP.getScore(Files.setsNameConfig.getString("teams.fakePlayers.two").replace("&", "§"));
			scNbrPT.setScore(scNbrPT.getScore() - 1);
			tRdy--;
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				Vars.pNameTeamTwo.remove(p.getName());
			}
			if (Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.remove(p.getName());
			}
		}

		// TeamThree => Hall
		if (e.getRegion().getId().equals("teamthreetohall")) {
			p.teleport(Tp.hfT);
			p.getInventory().clear();
			p.setGameMode(GameMode.ADVENTURE);
			if (p.isOp()) {
				if (Vars.pNameCaster.contains(p.getName())) {
					Vars.pNameCaster.remove(p.getName());
					new CasterHotbar().casterStuffMenu(p, 0);
				}
				if (Vars.pNameGamers.contains(p.getName())) {
					Vars.pNameGamers.remove(p.getName());
				}
			} else if (Vars.pNameSpec.contains(p.getName())) {
					Vars.pNameSpec.remove(p.getName());
					new CasterHotbar().specStuffMenu(p, 0);
				}
			if (Vars.pNameGamers.contains(p.getName())) {
				Vars.pNameGamers.remove(p.getName());
			}
		}
		
		// Hall => Settings
		if (e.getRegion().getId().equals("halltosettings")) {
			if (p.isOp()) {
				p.teleport(Tp.sfH);
			} else {
				p.sendMessage("§0[§4SINE§0] §7§oYou're not authorized to do this !");
			}
		}
		// Hall => Rules
		if (e.getRegion().getId().equals("halltorules")) {
			p.teleport(Tp.rfH);
		}
		// Settings => Hall
		if (e.getRegion().getId().equals("settingstohall")) {
			p.teleport(Tp.hfSR);
		}
		// Rules => Hall
		if (e.getRegion().getId().equals("rulestohall")) {
			p.teleport(Tp.hfSR);
		}

		if (e.getRegion().getId().equals("halltoteamone") || e.getRegion().getId().equals("halltoteamtwo")
				|| e.getRegion().getId().equals("teamonetohall") || e.getRegion().getId().equals("teamtwotohall")) {

			// Affichage des panneaux RDY en fonction de l'equilibrage des equipes
			// (si activé dans les settings)

			Block oRdyS = p.getWorld().getBlockAt(Files.coordConfig.getInt("teams.one.rdySign.x"),
					Files.coordConfig.getInt("teams.one.rdySign.y"), Files.coordConfig.getInt("teams.one.rdySign.z"));
			BlockState oRdyBS = oRdyS.getState();
			Sign oneRdySign = (Sign) oRdyBS;

			Block tRdyS = p.getWorld().getBlockAt(Files.coordConfig.getInt("teams.two.rdySign.x"),
					Files.coordConfig.getInt("teams.two.rdySign.y"), Files.coordConfig.getInt("teams.two.rdySign.z"));
			BlockState tRdyBS = tRdyS.getState();
			Sign twoRdySign = (Sign) tRdyBS;

			// Si option d'équilibrage activée (=0)
			if (Files.config.getInt("options.equi") == 0) {
				if (oRdy == tRdy) {
					if (oRdy > 0) {
						oneRdySign.setLine(1, "§lReady ?");
						oneRdySign.setLine(2, "§lClick me !");
						oneRdySign.setLine(3, "§l-");
						oneRdySign.update();
					}
					if (tRdy > 0) {
						twoRdySign.setLine(1, "§lReady ?");
						twoRdySign.setLine(2, "§lClick me !");
						twoRdySign.setLine(3, "§l-");
						twoRdySign.update();
					} else {
						if (oRdyS.getType().equals(Material.OAK_WALL_SIGN)) {
							oneRdySign.setLine(1, "§lWait for");
							oneRdySign.setLine(2, "§lplayers...");
							oneRdySign.setLine(3, "§l-");
							oneRdySign.update();
						}
						if (tRdyS.getType().equals(Material.OAK_WALL_SIGN)) {
							twoRdySign.setLine(1, "§lWait for");
							twoRdySign.setLine(2, "§lplayers...");
							twoRdySign.setLine(3, "§l-");
							twoRdySign.update();
						}
					}
				} else {
					oneRdySign.setLine(1, "§lWait for");
					oneRdySign.setLine(2, "§lplayers...");
					oneRdySign.setLine(3, "§l-");
					oneRdySign.update();
					twoRdySign.setLine(1, "§lWait for");
					twoRdySign.setLine(2, "§lplayers...");
					twoRdySign.setLine(3, "§l-");
					twoRdySign.update();
				}

				// Si option d'équilibrage pas activée (=1)
			} else {
				oneRdySign.setLine(1, "§lReady ?");
				oneRdySign.setLine(2, "§lClick me !");
				oneRdySign.setLine(3, "§l-");
				oneRdySign.update();
				twoRdySign.setLine(1, "§lReady ?");
				twoRdySign.setLine(2, "§lClick me !");
				twoRdySign.setLine(3, "§l-");
				twoRdySign.update();
			}
		}
	}
}