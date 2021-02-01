package fr.phoenix.sineplugin.admin;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import fr.phoenix.sineplugin.playerProfile.PlayerStatsManager;

public class AdminAccess {

	// MANAGE COMMAND ACCESS

	public int attempt = 0;

	public boolean checkBL(Player p, int rank) {

		boolean access = true;

		PlayerStats dataPS = Vars.pStatsSeason.get(p);

		int blacklistRank = dataPS.getBlacklist();

		if (blacklistRank < rank) {
			p.sendMessage("§0[§4SINE§0] §7§oYou're blacklisted, you're not allowed to do this.");
			p.sendMessage("§0[§4SINE§0] §7§oContact an admin !");
			access = false;
		}
		return access;

	}

	public void auth(Player p, String rank) {
		String pName = p.getName();
		UUID pID = p.getUniqueId();
		
		PlayerStats dataPS = Vars.pStatsSeason.get(p);
		
		if (rank.equals("admin"))	dataPS.setAccess(40);
		if (rank.equals("caster"))	dataPS.setAccess(30);
		if (rank.equals("spec"))	dataPS.setAccess(20);
		
		new PlayerStatsManager().saveAccessAndBlacklist(p);
		
		try {
			Files.authConfig.set(pID + " - " + pName + "." + rank + "Auth." + PlayerStats.time, 1);
			Files.authConfig.save(Files.authFile);
			Files.gradeConfig.set(rank + "." + pID, p.getName() + " - " + PlayerStats.time);
			Files.gradeConfig.save(Files.gradeFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void wrongAuthPass(Player p, String rank) {

		String pName = p.getName();
		UUID pID = p.getUniqueId();
		
		PlayerStats dataPS = Vars.pStatsSeason.get(p);
		
		int wrongPass = dataPS.getAccess();
		wrongPass--;
		dataPS.setAccess(wrongPass);

		try {
			Files.authConfig.set(pID + " - " + pName + "." + rank + "Attempt." + attempt, PlayerStats.time);
			Files.authConfig.save(Files.authFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (dataPS.getAccess() == 39 || dataPS.getAccess() == 29 || dataPS.getAccess() == 19 ) {
			p.sendMessage("§0[§4SINE§0] §7§oWrong password, access denied !");
			p.sendMessage("§0[§4SINE§0] §7§oTwo remaining attempts...");
		} else if (dataPS.getAccess() == 38 || dataPS.getAccess() == 28 || dataPS.getAccess() == 18 ) {
			p.sendMessage("§0[§4SINE§0] §7§oWrong password, access denied !");
			p.sendMessage("§0[§4SINE§0] §7§oLast possible attempt...");
		} else if (dataPS.getAccess() == 37 || dataPS.getAccess() == 27 || dataPS.getAccess() == 17 ) {
			p.sendMessage("§0[§4SINE§0] §7§oWrong password, access denied !");
			p.sendMessage("§0[§4SINE§0] §7§oYour profile was blacklisted ! Contact an admin...");
			blacklist(p, rank);
		}
	}

	public void pardon(Player p, String rank) {
		String pName = p.getName();
		UUID pID = p.getUniqueId();
		
		PlayerStats dataPS = Vars.pStatsSeason.get(p);
		
		if (rank.equals("caster"))	dataPS.setAccess(30);
		if (rank.equals("spec"))	dataPS.setAccess(20);
		if (rank.equals("player"))	dataPS.setAccess(10);
		
		try {
			Files.authConfig.set(pID + " - " + pName + ".pardon." + PlayerStats.time, 1);
			Files.authConfig.save(Files.authFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blacklist(Player p, String rank) {
		String pName = p.getName();
		UUID pID = p.getUniqueId();
		
		PlayerStats dataPS = Vars.pStatsSeason.get(p);
		
		if (rank.equals("admin"))	dataPS.setBlacklist(30);
		if (rank.equals("caster"))	dataPS.setBlacklist(20);
		if (rank.equals("spec"))	dataPS.setBlacklist(10);
		
		new PlayerStatsManager().saveAccessAndBlacklist(p);
		
		try {
			Files.authConfig.set(pID + " - " + pName + "." + rank + "Black." + PlayerStats.time, 1);
			Files.authConfig.save(Files.authFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
