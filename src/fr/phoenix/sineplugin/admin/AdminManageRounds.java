package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageRounds {

	private ItemStack endPrep, endDef, endFight;
	private Inventory inv;

	public AdminManageRounds(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Rounds Menu");
	}
		
	public void createManageRoundsMenu(Player p) {
		
		endPrep = new ItemStack(Material.CHEST, 1);
		ItemMeta epM = endPrep.getItemMeta();
		epM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		epM.setDisplayName("§6§lFinish Preparation Phase");
		endPrep.setItemMeta(epM);
		inv.setItem(11, endPrep);
		
		endDef = new ItemStack(Material.COBBLESTONE_WALL, 1);
		ItemMeta edM = endDef.getItemMeta();
		edM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		edM.setDisplayName("§6§lFinish Defense Phase");
		endDef.setItemMeta(edM);
		inv.setItem(13, endDef);
		
		endFight = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta efM = endFight.getItemMeta();
		efM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		efM.setDisplayName("§6§lFinish Fight Phase");
		endFight.setItemMeta(efM);
		inv.setItem(15, endFight);
		
		p.openInventory(inv);
		
	}
}
