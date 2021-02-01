package fr.phoenix.sineplugin.caster;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;

public class PreviousRound {

	private double loc = 0;
	private ArmorStand as1, as2, as3, as4, as5, as6, as7, as8, as9, as10, as11, as12, as13; 
	
	public void previousRoundStats() {
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		String killPrevRoundCommand = "kill @e[x=-3,y=110,z=-82,r=3,type=armor_stand]";
		Bukkit.dispatchCommand(console, killPrevRoundCommand);

		createHolo(as1, loc ,"§f----- §6Previous round stats §f-----");
		loc -= 0.25;
		
		createHolo(as2, loc, "§f-----------------------------");
		loc -= 0.25;
		
		if (Vars.whoBegin.equals("OaTd")) {
			createHolo(as3, loc, "§6Attack : §cRED §f- §6Defense : §aGREEN");
		}
		if (Vars.whoBegin.equals("OdTa")) {
			createHolo(as3, loc, "§6Attack : §aGREEN §f- §6Defense : §cRED");
		}
		loc -= 0.25;
		
		if (Vars.oneWinRd == 1) {
			createHolo(as4, loc, "§6Win by : §cRED");
		}
		if (Vars.twoWinRd == 1) {
			createHolo(as4, loc, "§b\u2B1B §6Win by : §aGREEN");
		}
		if (Vars.draw == 1) {
			createHolo(as4, loc, "§b\u2B1B §6Draw round");
		}
		loc -= 0.25;
		
		if (Vars.oneFlagTaken >= 1) {
			if (Vars.oneFlagCaptur != 0) {
				createHolo(as5, loc, "§6Flag taken : §c" + Vars.oneFlagTaken + " §f- §6Flag captured : §cYES");
			}
			if (Vars.oneFlagCaptur == 0) {
				createHolo(as5, loc, "§6Flag taken : §c" + Vars.oneFlagTaken + " §f- §6Flag captured : §cNO");
			}
			loc -= 0.25;
			
			if (Vars.twoFlagDrop >= 1) {
				createHolo(as6, loc, "§6Flag dropped : §a" + Vars.twoFlagDrop + " §f- §6Flag returned : §a" + Vars.twoFlagBack);
				loc -= 0.25;
			}
		}
		if (Vars.twoFlagTaken >= 1) {
			if (Vars.twoFlagCaptur != 0) {
				createHolo(as5, loc, "§6Flag taken : §a" + Vars.twoFlagTaken + " §f- §6Flag captured : §aYES");
			}
			if (Vars.twoFlagCaptur == 0) {
				createHolo(as5, loc, "§6Flag taken : §a" + Vars.twoFlagTaken + " §f- §6Flag captured : §aNO");
			}
			loc -= 0.25;
			if (Vars.oneFlagDrop >= 1) {
				createHolo(as6, loc, "§6Flag dropped : §c" + Vars.oneFlagDrop + " §f- §6Flag returned : §c" + Vars.oneFlagBack);
				loc -= 0.25;
			}
		}
		
		createHolo(as7, loc, "§6Deaths : §c" + Vars.twoKills + " §cRED §f- §a" + Vars.oneKills + " §aGREEN");
		loc -= 0.25;
		
		if (Vars.onePerfect == 1) {
			if (Vars.twoPerfect == 1) {
				createHolo(as8, loc, "§6Perfect for : §cRED and §aGREEN");
			} else {
				createHolo(as8, loc, "§6Perfect for : §cRED");
			}
			loc -= 0.25;
			
		} else if (Vars.twoPerfect == 1) {
			createHolo(as8, loc, "§6Perfect for : §aGREEN");
			loc -= 0.25;
		} else {
			createHolo(as8, loc, "§6No Perfect ");
			loc -= 0.25;
		}
		if (Vars.whoBegin.equals("OaTd")) {
			if (Vars.oneBlitz == 1) {
				createHolo(as9, loc, "§6Blitzkrieg : §cSUCCESS");
			}
			if (Vars.twoPerfDef == 1) {
				createHolo(as9, loc, "§6Perfect Defense : §aSUCCESS");
			}
			loc -= 0.25;
		}
		if (Vars.whoBegin.equals("OdTa")) {
			if (Vars.twoBlitz == 1) {
				createHolo(as9, loc, "§6Blitzkrieg : §aSUCCESS");
			}
			if (Vars.onePerfDef == 1) {
				createHolo(as9, loc, "§6Perfect Defense : §cSUCCESS");
			}
			loc -= 0.25;
		}
		createHolo(as10, loc, "§f-----------------------------");
		loc -= 0.25;
		
		createHolo(as11, loc, "§6Round golds : §c" + Vars.oneGoldsRd + " §f- §a" + Vars.twoGoldsRd);
		loc -= 0.25;
		
		createHolo(as12, loc, "§6New total golds : §c" + Vars.oneGoldsTotal + " §f- §a" + Vars.twoGoldsTotal);
		loc -= 0.25;
		
		createHolo(as13, loc, "§6Actual Score : §c"+Vars.oneScore+ " §f- §a"+Vars.twoScore);
	}
	
	public void createHolo(ArmorStand as, double loc, String name) {
		
		as = (ArmorStand) Tp.casterPreviousRound.getWorld().spawnEntity(Tp.casterPreviousRound, EntityType.ARMOR_STAND);
		
		as.setCustomName(name);
		as.setCustomNameVisible(true);
		as.setVisible(false);
		as.setGravity(false);
	}
	
}
