package fr.phoenix.sineplugin.MVP;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;

public class MvpVotePointsEvents implements Listener {

	private String skOwner;
	private int totalVoteMVPPoints;

	public static HashMap<Player, Integer> hasVoted = new HashMap<Player, Integer>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void clicMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		ItemStack it = e.getCurrentItem();

		if (e.getView().getTitle().equalsIgnoreCase("§5§oMVP Vote Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.RED_BANNER) || it.getType().equals(Material.LIME_BANNER)) {
				e.setCancelled(true);
			}

			if (it.getType().equals(Material.PLAYER_HEAD)) {
				if (hasVoted.get(p) != 1) {
					if (it.hasItemMeta()) {
						SkullMeta skM = (SkullMeta) it.getItemMeta();
						skOwner = skM.getOwner();

						p.sendMessage("§0[§4SINE§0] §7§oYour vote is recorded, Thanks !");
						hasVoted.put(p, 1);

						MvpChoosen.totalVoteMVP++;

						Player pV = Bukkit.getPlayerExact(skOwner);

						PlayerStats dataPS = Vars.pStatsLastGame.get(pV);

						int voteMVP = dataPS.getVoteMVP();
						totalVoteMVPPoints = voteMVP + 50;
						dataPS.setVoteMVP(totalVoteMVPPoints);

						new MvpChoosen().checkVotes();

						p.closeInventory();

						e.setCancelled(true);

					}
				} else {
					p.sendMessage("§0[§4SINE§0] §7§oYou have already voted !");
					
					for (String pN : Vars.pNameCaster) {
						Player pC = Bukkit.getPlayerExact(pN);
						pC.sendMessage("§0[§4SINE§0] §d§o"+p.getName()+" §7§otry to vote again...");
					}
				}

			}
		}
	}
}
