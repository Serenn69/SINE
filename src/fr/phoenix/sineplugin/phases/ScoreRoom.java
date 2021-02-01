package fr.phoenix.sineplugin.phases;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import fr.phoenix.sineplugin.caster.PreviousRound;
import fr.phoenix.sineplugin.recapGame.GameStatsFile;

public class ScoreRoom {

	private int holoScoreTimer;

	public void tpScoreRoom() {

		int slotO = 1;
		int slotT = 1;
		int n = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {

			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				switch (slotO) {
				case 1:
					p.teleport(Tp.oneScRoomSlot1);
					slotO++;
					break;
				case 2:
					p.teleport(Tp.oneScRoomSlot2);
					slotO++;
					break;
				case 3:
					p.teleport(Tp.oneScRoomSlot3);
					slotO++;
					break;
				case 4:
					p.teleport(Tp.oneScRoomSlot4);
					slotO++;
					break;
				case 5:
					p.teleport(Tp.oneScRoomSlot5);
					break;
				}
			}
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				switch (slotT) {
				case 1:
					p.teleport(Tp.twoScRoomSlot1);
					slotT++;
					break;
				case 2:
					p.teleport(Tp.twoScRoomSlot2);
					slotT++;
					break;
				case 3:
					p.teleport(Tp.twoScRoomSlot3);
					slotT++;
					break;
				case 4:
					p.teleport(Tp.twoScRoomSlot4);
					slotT++;
					break;
				case 5:
					p.teleport(Tp.twoScRoomSlot5);
					break;
				}
			}
			if (Vars.pNameCaster.contains(p.getName())) {
				p.teleport(Tp.casterScRoomSlot);
			}
			if (Vars.pNameSpec.contains(p.getName())) {
				switch (n) {

				case 0:
					p.teleport(Tp.oneScRoomSlot1);
					n++;
				case 1:
					p.teleport(Tp.twoScRoomSlot1);
					n--;
				}
			}
		}
	}

	double loc = 0;
	ArmorStand as1, as2, as3, as4, as5, as6, as7, as8, as9, as10, as11, as12;
	ArmorStand asT1, asT2, asT3, asT4, asT5, asT6, asT7, asT8, asT9, asT10, asT11, asT12;
	
	public void holoScore() {

		holoScoreTimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
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
					
					if (Vars.oneWinRd == 1) {
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round won :       §b" + Files.config.getInt("score.winRd"), "one");
						Vars.oneGoldsRd += Files.config.getInt("score.winRd");
					}
					if (Vars.twoWinRd == 1) {
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round won :       §b" + Files.config.getInt("score.winRd"), "two");
						Vars.twoGoldsRd += Files.config.getInt("score.winRd");
					}
					if (Vars.oneLoseRd == 1) {
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round lost :            §b" + Files.config.getInt("score.loseRd"), "one");
						Vars.oneGoldsRd += Files.config.getInt("score.loseRd");
					}
					if (Vars.twoLoseRd == 1) {
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round lost :            §b" + Files.config.getInt("score.loseRd"), "two");
						Vars.twoGoldsRd += Files.config.getInt("score.loseRd");
					}
					if (Vars.draw == 1) {
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round draw :            §b" + Files.config.getInt("score.draw"), "one");
						createHolo(as3, asT3, loc, "§5\u2B1B §6Round draw :            §b" + Files.config.getInt("score.draw"), "two");
						Vars.oneGoldsRd += Files.config.getInt("score.draw");
						Vars.twoGoldsRd += Files.config.getInt("score.draw");
					}
					loc -= 0.25;
					
					break;
					
				case 4:
					
					if (Vars.whoBegin.equals("OaTd")) {
						if (Vars.oneFlagTaken >= 1) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6Flag taken :       §b" + Files.config.getInt("score.takeFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.takeFlag");
						}
						if (Vars.oneFlagTaken == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag taken :     §b0", "one");
						}
						if (Vars.twoFlagDrop >= 1) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6Flag dropped :     §b" + Files.config.getInt("score.dropFlag"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.dropFlag");
						}
						if (Vars.twoFlagDrop == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag dropped :     §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.oneFlagDrop >= 1) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6Flag dropped :     §b" + Files.config.getInt("score.dropFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.dropFlag");
						}
						if (Vars.oneFlagDrop == 0) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6No flag dropped :     §b0", "one");
						}
						if (Vars.twoFlagTaken >= 1) {
							createHolo(as4, asT4, loc, "§5\u2B1B §6Flag taken :       §b" + Files.config.getInt("score.takeFlag"), "two");
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
							createHolo(as5, asT5, loc, "§5\u2B1B §6Flag captured :    §b" + Files.config.getInt("score.capturFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.capturFlag");
						}
						if (Vars.oneFlagCaptur == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag captured :  §b0", "one");
						}
						if (Vars.twoFlagBack >= 1) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6Flag returned :    §b" + Files.config.getInt("score.backFlag"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.backFlag");
						}
						if (Vars.twoFlagBack == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag returned :    §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.oneFlagBack >= 1) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6Flag returned :    §b" + Files.config.getInt("score.backFlag"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.backFlag");
						}
						if (Vars.oneFlagBack == 0) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6No flag returned :    §b0", "one");
						}
						if (Vars.twoFlagCaptur >= 1) {
							createHolo(as5, asT5, loc, "§5\u2B1B §6Flag captured :    §b" + Files.config.getInt("score.capturFlag"), "two");
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
					Vars.oneGoldsRd += ((Files.config.getInt("score.kills") * Vars.oneKills));
					Vars.twoGoldsRd += ((Files.config.getInt("score.kills") * Vars.twoKills));
					
					createHolo(as7, asT7, loc, "§5\u2B1B §6Mates killed :         §b"
							+ (Files.config.getInt("score.mateKills") * Vars.oneMateKills), "one");
					createHolo(as7, asT7,  loc, "§5\u2B1B §6Mates killed :         §b"
							+ (Files.config.getInt("score.mateKills") * Vars.twoMateKills), "two");
					loc -= 0.25;
					Vars.oneGoldsRd += ((Files.config.getInt("score.mateKills") * Vars.oneMateKills));
					Vars.twoGoldsRd += ((Files.config.getInt("score.mateKills") * Vars.twoMateKills));
					
					break;
					
				case 10:
					
					if (Vars.onePerfect == 1) {
						Vars.totalOnePerfect++;
						createHolo(as8, asT8, loc, "§5\u2B1B §6No deaths :        §b" + Files.config.getInt("score.noDeath"), "one");
						Vars.oneGoldsRd += Files.config.getInt("score.noDeath");
					}
					if (Vars.onePerfect == 0) {
						createHolo(as8, asT8, loc, "§5\u2B1B §6No deaths :           §b0", "one");
					}
					if (Vars.twoPerfect == 1) {
						Vars.totalTwoPerfect++;
						createHolo(as8, asT8, loc, "§5\u2B1B §6No deaths :        §b" + Files.config.getInt("score.noDeath"), "two");
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
							Vars.totalOneBlitz++;
							createHolo(as9, asT9, loc, "§5\u2B1B §6Blitzkrieg :       §b" + Files.config.getInt("score.blitz"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.blitz");
						}
						if (Vars.oneBlitz == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6Blitzkrieg :        §b0", "one");
						}
						if (Vars.twoPerfDef == 1) {
							Vars.totalTwoPerfDef++;
							createHolo(as9, asT9, loc, "§5\u2B1B §6Perfect Def. :  §b" + Files.config.getInt("score.perfDef"), "two");
							Vars.twoGoldsRd += Files.config.getInt("score.perfDef");
						}
						if (Vars.twoPerfDef == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6No Perfect Def. :   §b0", "two");
						}
					} else if (Vars.whoBegin.equals("OdTa")) {
						if (Vars.onePerfDef == 1) {
							Vars.totalOnePerfDef++;
							createHolo(as9, asT9, loc, "§5\u2B1B §6Perfect Def. :  §b" + Files.config.getInt("score.perfDef"), "one");
							Vars.oneGoldsRd += Files.config.getInt("score.perfDef");
						}
						if (Vars.onePerfDef == 0) {
							createHolo(as9, asT9, loc, "§5\u2B1B §6No Perfect Def. :   §b0", "one");
						}
						if (Vars.twoBlitz == 1) {
							Vars.totalTwoBlitz++;
							createHolo(as9, asT9, loc, "§5\u2B1B §6Blitzkrieg :       §b" + Files.config.getInt("score.blitz"), "two");
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
					
					createHolo(as12, asT12, loc, "§6Total §4RED §6golds : §b" + (Vars.oneGoldsRd + Vars.oneGoldsTotal), "one");
					createHolo(as12, asT12, loc, "§6Total §aGREEN §6golds : §b" + (Vars.twoGoldsRd + Vars.twoGoldsTotal), "two");
					Vars.oneGoldsTotal += Vars.oneGoldsRd;
					Vars.twoGoldsTotal += Vars.twoGoldsRd;
					Vars.totalOneWinGolds += Vars.oneGoldsRd;
					Vars.totalTwoWinGolds += Vars.twoGoldsRd;
					
					break;
					
				case 20:
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					String killOneScRoomCommand = "kill @e[x=-53,y=66,z=-134,r=2,type=armor_stand]";
					Bukkit.dispatchCommand(console, killOneScRoomCommand);
					String killTwoScRoomCommand = "kill @e[x=54,y=66,z=-134,r=2,type=armor_stand]";
					Bukkit.dispatchCommand(console, killTwoScRoomCommand);
					String killCasterScRoomCommand = "kill @e[x=0,y=27,z=-15,r=3,type=armor_stand]";
					Bukkit.dispatchCommand(console, killCasterScRoomCommand);
					
					Main.getInstance().getServer().getScheduler().cancelTask(holoScoreTimer);
					endRound();
					break;
				}
				n++;
			}
		}, 20, 20);
	}

	public void createHolo(ArmorStand as, ArmorStand asT, double loc, String name, String castLoc) {

		Location asLocCastOne = new Location(Tp.casterOneScRoomHolo.getWorld(), Tp.casterOneScRoomHolo.getX(), Tp.casterOneScRoomHolo.getY()+loc, Tp.casterOneScRoomHolo.getZ());
		Location asLocCastTwo = new Location(Tp.casterTwoScRoomHolo.getWorld(), Tp.casterTwoScRoomHolo.getX(), Tp.casterTwoScRoomHolo.getY()+loc, Tp.casterTwoScRoomHolo.getZ());
		
		Location asLocOne = new Location(Tp.oneScRoomHolo.getWorld(), Tp.oneScRoomHolo.getX(), Tp.oneScRoomHolo.getY()+loc, Tp.oneScRoomHolo.getZ());
		Location asLocTwo = new Location(Tp.twoScRoomHolo.getWorld(), Tp.twoScRoomHolo.getX(), Tp.twoScRoomHolo.getY()+loc, Tp.twoScRoomHolo.getZ());
		
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
	

	public void endRound() {
		
		new GameStatsFile().addFightStats();

		CasterMenu.teamOneBuyPrevious.clear();
		CasterMenu.teamTwoBuyPrevious.clear();
		CasterMenu.teamOneBuyPrevious.putAll(CasterMenu.teamOneBuy);
		CasterMenu.teamTwoBuyPrevious.putAll(CasterMenu.teamTwoBuy);
		CasterMenu.teamOneBuy.clear();
		CasterMenu.teamOneBuyed.clear();
		CasterMenu.teamTwoBuy.clear();
		CasterMenu.teamTwoBuyed.clear();

		new PreviousRound().previousRoundStats();

		Vars.freeze = 0;

		if (Vars.whoBegin.equals("OaTd"))
			Vars.whoBegin = "OdTa";
		else if (Vars.whoBegin.equals("OdTa"))
			Vars.whoBegin = "OaTd";

		new Countdown().countDownPrep(Files.config.getInt("options.prep"));
	}

}
