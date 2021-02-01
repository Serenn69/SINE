package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageMap {

	private ItemStack small_forest, large_forest, small_hill, large_hill, small_trench, large_trench, small_ruins;
	private ItemStack redBaseAtk, greenBaseAtk, respawnRedFlag, respawnGreenFlag;
	private Inventory inv;

	public AdminManageMap(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Map Menu");
	}
		
	public void createManageMapMenu(Player p) {
		
		small_forest = new ItemStack(Material.MAP, 1);
		ItemMeta sfM = small_forest.getItemMeta();
		sfM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sfM.setDisplayName("§6§lReload Forest Small size");
		small_forest.setItemMeta(sfM);
		inv.setItem(10, small_forest);
		
		large_forest = new ItemStack(Material.FILLED_MAP, 1);
		ItemMeta lfM = large_forest.getItemMeta();
		lfM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lfM.setDisplayName("§6§lReload Forest Large size");
		large_forest.setItemMeta(lfM);
		inv.setItem(19, large_forest);
		
		small_hill = new ItemStack(Material.MAP, 1);
		ItemMeta shM = small_hill.getItemMeta();
		shM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		shM.setDisplayName("§6§lReload Hill Small size");
		small_hill.setItemMeta(shM);
		inv.setItem(11, small_hill);
		
		large_hill = new ItemStack(Material.FILLED_MAP, 1);
		ItemMeta lhM = large_hill.getItemMeta();
		lhM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lhM.setDisplayName("§6§lReload Hill Large size");
		large_hill.setItemMeta(lhM);
		inv.setItem(20, large_hill);
		
		small_trench = new ItemStack(Material.MAP, 1);
		ItemMeta stM = small_trench.getItemMeta();
		stM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		stM.setDisplayName("§6§lReload Trenchees Small size");
		small_trench.setItemMeta(stM);
		inv.setItem(12, small_trench);
		
		large_trench = new ItemStack(Material.FILLED_MAP, 1);
		ItemMeta ltM = large_trench.getItemMeta();
		ltM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ltM.setDisplayName("§6§lReload Trenchees Large size");
		large_trench.setItemMeta(ltM);
		inv.setItem(21, large_trench);
		
		small_ruins = new ItemStack(Material.MAP, 1);
		ItemMeta srM = small_ruins.getItemMeta();
		srM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		srM.setDisplayName("§6§lReload Ruins Small size");
		small_ruins.setItemMeta(srM);
		inv.setItem(13, small_ruins);
		
		redBaseAtk = new ItemStack(Material.RED_WOOL, 1);
		ItemMeta rbaM = redBaseAtk.getItemMeta();
		rbaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rbaM.setDisplayName("§6§lReload §c§lRed §6§lBase Attack");
		redBaseAtk.setItemMeta(rbaM);
		inv.setItem(15, redBaseAtk);
		
		greenBaseAtk = new ItemStack(Material.LIME_WOOL, 1);
		ItemMeta gbaM = greenBaseAtk.getItemMeta();
		gbaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gbaM.setDisplayName("§6§lReload §a§lGreen §6§lBase Attack");
		greenBaseAtk.setItemMeta(gbaM);
		inv.setItem(24, greenBaseAtk);
		
		respawnRedFlag = new ItemStack(Material.RED_BANNER, 1);
		ItemMeta rrfM = respawnRedFlag.getItemMeta();
		rrfM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rrfM.setDisplayName("§6§lReload §c§lRed §6§lFlag");
		respawnRedFlag.setItemMeta(rrfM);
		inv.setItem(16, respawnRedFlag);
		
		respawnGreenFlag = new ItemStack(Material.LIME_BANNER, 1);
		ItemMeta rgfM = respawnGreenFlag.getItemMeta();
		rgfM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rgfM.setDisplayName("§6§lReload §a§lGreen §6§lFlag");
		respawnGreenFlag.setItemMeta(rgfM);
		inv.setItem(25, respawnGreenFlag);
		
		p.openInventory(inv);
		
	}
}
