package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageTime {

	private ItemStack day, night, sun, rain;
	private Inventory inv;

	public AdminManageTime(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Time Menu");
	}
		
	public void createManageTimeMenu(Player p) {
		
		day = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
		ItemMeta dM = day.getItemMeta();
		dM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		dM.setDisplayName("§6§lSet Day");
		day.setItemMeta(dM);
		inv.setItem(12, day);
		
		night = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
		ItemMeta nM = night.getItemMeta();
		nM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		nM.setDisplayName("§6§lSet Night");
		night.setItemMeta(nM);
		inv.setItem(21, night);
		
		sun = new ItemStack(Material.SUNFLOWER, 1);
		ItemMeta sM = sun.getItemMeta();
		sM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sM.setDisplayName("§6§lSet Sun Weather");
		sun.setItemMeta(sM);
		inv.setItem(14, sun);
		
		rain = new ItemStack(Material.WATER_BUCKET, 1);
		ItemMeta rM = rain.getItemMeta();
		rM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rM.setDisplayName("§6§lSet Rain Weather");
		rain.setItemMeta(rM);
		inv.setItem(23, rain);
		
		p.openInventory(inv);
		
	}
}
