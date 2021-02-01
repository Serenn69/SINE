package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageGolds {

	private ItemStack redTeam, greenTeam;
	private Inventory inv;

	public AdminManageGolds(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Golds Teams Menu");
	}
		
	public void createManageGoldsMenu(Player p) {
		
		redTeam = new ItemStack(Material.RED_BANNER, 1);
		ItemMeta rtM = redTeam.getItemMeta();
		rtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rtM.setDisplayName("§c§lRed §6§lTeam Golds");
		redTeam.setItemMeta(rtM);
		inv.setItem(12, redTeam);
		
		greenTeam = new ItemStack(Material.LIME_BANNER, 1);
		ItemMeta gtM = greenTeam.getItemMeta();
		gtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gtM.setDisplayName("§a§lGreen §6§lTeam Golds");
		greenTeam.setItemMeta(gtM);
		inv.setItem(14, greenTeam);
		
		p.openInventory(inv);
		
	}
}
