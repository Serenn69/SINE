package fr.phoenix.sineplugin.MVP;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.phases.Victory;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class MvpChoosen {

	ArrayList<Player> eligible = new ArrayList<Player>();

	IChatBaseComponent subTitle;

	int totalSpec = Vars.pNameSpec.size();
	int totalCaster = Vars.pNameCaster.size();
	private int bestMvpScore = 0;
	private String bestMvpName = null;
	public static int totalVoteMVP = 0, voteOpened = 1;

	int names, n = 0;

	public void chooseMVP() {

		for (String pNO : Vars.pNameTeamOne.keySet()) {
			Player pO = Bukkit.getPlayerExact(pNO);
			eligible.add(pO);
		}
		for (String pNT : Vars.pNameTeamTwo.keySet()) {
			Player pT = Bukkit.getPlayerExact(pNT);
			eligible.add(pT);
		}
		for (Player p : eligible) {

			if (new MvpStatsPoints().totalPlayerStatsPoints(p) > bestMvpScore) {
				bestMvpScore = new MvpStatsPoints().totalPlayerStatsPoints(p);
				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					bestMvpName = "§c§l"+p.getName();
				}
				else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					bestMvpName = "§a§l"+p.getName();
				}
			}
		}
	}

	public void checkVotes() {

		if (totalVoteMVP > totalSpec + totalCaster) {
			for (String pN : Vars.pNameCaster) {
				Player p = Bukkit.getPlayerExact(pN);
				p.sendMessage("§0[§4SINE§0] §7§oVote exceeded numbers of Spec and Caster... all votes are reset !");
				for (String pNO : Vars.pNameTeamOne.keySet()) {
					Player pO = Bukkit.getPlayerExact(pNO);

					PlayerStats dataPSO = Vars.pStatsLastGame.get(pO);

					dataPSO.setVoteMVP(0);
				}
				for (String pNT : Vars.pNameTeamOne.keySet()) {
					Player pT = Bukkit.getPlayerExact(pNT);

					PlayerStats dataPST = Vars.pStatsLastGame.get(pT);

					dataPST.setVoteMVP(0);
				}
			}
		}
		if (totalVoteMVP < totalSpec + totalCaster) {

			Bukkit.broadcastMessage("§0[§4SINE§0] §7§oNeed again " + ((totalSpec + totalCaster) - totalVoteMVP)
					+ " §7§ovotes before election...");
			
			for (String pNS : Vars.pNameSpec) {
				Player pS = Bukkit.getPlayerExact(pNS);
				
				if (MvpVotePointsEvents.hasVoted.get(pS) != 1) {
					MvpVotePoints menu = new MvpVotePoints(45);
					menu.createVotePlayerMenu(pS);
					pS.sendMessage("§0[§4SINE§0] §7§oPlease vote for MVP");
				}
			}
			for (String pNC : Vars.pNameCaster) {
				Player pC = Bukkit.getPlayerExact(pNC);
				
				if (MvpVotePointsEvents.hasVoted.get(pC) != 1) {
					MvpVotePoints menu = new MvpVotePoints(45);
					menu.createVotePlayerMenu(pC);
					pC.sendMessage("§0[§4SINE§0] §7§oPlease vote for MVP");
				}
			}
		}
		if (totalVoteMVP == totalSpec + totalCaster) {

			Bukkit.broadcastMessage("§0[§4SINE§0] §7§oVotes are closed !");
			checkNames();
		}
	}

	public void checkNames() {

		names = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {

				if (Main.getInstance().getServer().getScheduler().isQueued(names)) {
					if (n >= 2) {
						Main.getInstance().getServer().getScheduler().cancelTask(names);
						n = 0;
					}
				}
				switch (n) {
				case 0:

					for (Player p : Bukkit.getOnlinePlayers()) {

						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						subTitle = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&6§oChecking for MVP...") + "\"}");

						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);

						chooseMVP();
					}
					n++;
					break;

				case 1:

					for (Player p : Bukkit.getOnlinePlayers()) {

						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&fCongratulations §6!!!") + "\"}");
						subTitle = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&6§oMost Valuable Player is "
										+ bestMvpName + " §6§owith §d§l" + bestMvpScore + " §6§opoints !")
								+ "\"}");

						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);
					}
					
				case 2:
					
					Player pMVP = Bukkit.getPlayerExact(bestMvpName);
					new MvpHologram().mvpStats(pMVP);
					new Victory().transferStatsToDatabase(Victory.teamWin);

				}
			}
		}, 60, 60);
	}
}