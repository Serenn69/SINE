package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageScore {

	private ItemStack redTeam, greenTeam, addRedScore, addGreenScore, remRedScore, remGreenScore;
	private Inventory inv;

	public AdminManageScore(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Score Teams Menu");
	}
		
	public void createManageScoreMenu(Player p) {
		
		redTeam = new ItemStack(Material.RED_BANNER, 1);
		ItemMeta rtM = redTeam.getItemMeta();
		rtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rtM.setDisplayName("§c§lRed §6§lTeam");
		redTeam.setItemMeta(rtM);
		inv.setItem(13, redTeam);
		
		greenTeam = new ItemStack(Material.LIME_BANNER, 1);
		ItemMeta gtM = greenTeam.getItemMeta();
		gtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gtM.setDisplayName("§a§lGreen §6§lTeam");
		greenTeam.setItemMeta(gtM);
		inv.setItem(22, greenTeam);
		
		addRedScore = new ItemStack(Material.LIME_DYE, 1);
		ItemMeta arsM = addRedScore.getItemMeta();
		arsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		arsM.setDisplayName("§6§lAdd 1 to §c§lRed §6§lScore");
		addRedScore.setItemMeta(arsM);
		inv.setItem(12, addRedScore);
		
		addGreenScore = new ItemStack(Material.LIME_DYE, 1);
		ItemMeta agsM = addGreenScore.getItemMeta();
		agsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		agsM.setDisplayName("§6§lAdd 1 to §a§lGreen §6§lScore");
		addGreenScore.setItemMeta(agsM);
		inv.setItem(21, addGreenScore);
		
		remRedScore = new ItemStack(Material.RED_DYE, 1);
		ItemMeta rrsM = remRedScore.getItemMeta();
		rrsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rrsM.setDisplayName("§6§lRemove 1 to §c§lRed §6§lScore");
		remRedScore.setItemMeta(rrsM);
		inv.setItem(14, remRedScore);
		
		remGreenScore = new ItemStack(Material.RED_DYE, 1);
		ItemMeta rgsM = remGreenScore.getItemMeta();
		rgsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rgsM.setDisplayName("§6§lRemove 1 to §a§lGreen §6§lScore");
		remGreenScore.setItemMeta(rgsM);
		inv.setItem(23, remGreenScore);
		
		p.openInventory(inv);
		
	}
}
