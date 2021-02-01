package fr.phoenix.sineplugin.MVP;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.phoenix.sineplugin.Vars;

public class MvpVotePoints {

	private Inventory inv;
	
	private ItemStack oneTeam, twoTeam, pOSkull, pTSkull;
	
	public MvpVotePoints(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oMVP Vote Menu");
	}
	
	@SuppressWarnings("deprecation")
	public void createVotePlayerMenu(Player p) {
		
		oneTeam = new ItemStack(Material.RED_BANNER, 1);
		ItemMeta otM = oneTeam.getItemMeta();
		otM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		otM.setDisplayName("§6§lRed Team Players");
		oneTeam.setItemMeta(otM);
		inv.setItem(11, oneTeam);
		
		twoTeam = new ItemStack(Material.LIME_BANNER, 1);
		ItemMeta ttM = twoTeam.getItemMeta();
		ttM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ttM.setDisplayName("§6§lGreen Team Players");
		twoTeam.setItemMeta(ttM);
		inv.setItem(29, twoTeam);
		
		int slotO = 13;
		int slotT = 31;

		for (String pN : Vars.pNameTeamOne.keySet()) {
			Player pO = Bukkit.getPlayerExact(pN);
			
			pOSkull = new ItemStack(Material.PLAYER_HEAD, 1);
			SkullMeta skM = (SkullMeta) pOSkull.getItemMeta();
			skM.setOwner(pO.getName());
			skM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			skM.setDisplayName("§6§lVote for" + pO.getName());
			pOSkull.setItemMeta(skM);
			inv.setItem(slotO, pOSkull);

			slotO++;
			if (slotO > 15) {
				for (String pNC : Vars.pNameCaster) {
					Player pC = Bukkit.getPlayerExact(pNC);
					pC.sendMessage("§0[§4SINE§0] §7§oToo many Red players for MVP vote Menu !");
				}
				for (String pNS : Vars.pNameSpec) {
					Player pS = Bukkit.getPlayerExact(pNS);
					pS.sendMessage("§0[§4SINE§0] §7§oToo many Red players for MVP vote Menu !");
				}
			}
		}
		for (String pN : Vars.pNameTeamTwo.keySet()) {
			Player pT = Bukkit.getPlayerExact(pN);
			
			pTSkull = new ItemStack(Material.PLAYER_HEAD, 1);
			SkullMeta skM = (SkullMeta) pTSkull.getItemMeta();
			skM.setOwner(pT.getName());
			skM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			skM.setDisplayName("§6§lVote for" + pT.getName());
			pTSkull.setItemMeta(skM);
			inv.setItem(slotT, pTSkull);

			slotT++;
			if (slotT > 33) {
				for (String pNC : Vars.pNameCaster) {
					Player pC = Bukkit.getPlayerExact(pNC);
					pC.sendMessage("§0[§4SINE§0] §7§oToo many Green players for MVP vote Menu !");
				}
				for (String pNS : Vars.pNameSpec) {
					Player pS = Bukkit.getPlayerExact(pNS);
					pS.sendMessage("§0[§4SINE§0] §7§oToo many Green players for MVP vote Menu !");
				}
			}
		}
		
		p.openInventory(inv);
		
	}
	
}
