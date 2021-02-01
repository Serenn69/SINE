package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminTeleport {

	private ItemStack hall, settings, rules, casterScoreRoom, logo, redTp, redShop, redScoreRoom, redDef, redAtk, greenTp, greenShop, greenScoreRoom, greenDef, greenAtk;
	private Inventory inv;

	public AdminTeleport(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oTeleport Menu");
	}
		
	public void createTeleportMenu(Player p) {
		
		hall = new ItemStack(Material.OAK_DOOR, 1);
		ItemMeta hM = hall.getItemMeta();
		hM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		hM.setDisplayName("§6§lHall");
		hall.setItemMeta(hM);
		inv.setItem(16, hall);
		
		settings = new ItemStack(Material.STICKY_PISTON, 1);
		ItemMeta sM = settings.getItemMeta();
		sM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sM.setDisplayName("§6§lSettings Room");
		settings.setItemMeta(sM);
		inv.setItem(17, settings);
		
		rules = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta rM = rules.getItemMeta();
		rM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rM.setDisplayName("§6§lRules Room");
		rules.setItemMeta(rM);
		inv.setItem(25, rules);
		
		casterScoreRoom = new ItemStack(Material.GRAY_DYE, 1);
		ItemMeta csrM = casterScoreRoom.getItemMeta();
		csrM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		csrM.setDisplayName("§6§lCaster Score Room");
		casterScoreRoom.setItemMeta(csrM);
		inv.setItem(26, casterScoreRoom);
		
		logo = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta lM = logo.getItemMeta();
		lM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lM.setDisplayName("§6§lSINE Logo");
		logo.setItemMeta(lM);
		inv.setItem(8, logo);
		
		redTp = new ItemStack(Material.RED_BANNER, 1);
		ItemMeta rtM = redTp.getItemMeta();
		rtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rtM.setDisplayName("§c§lRed §6§lTeleporters");
		redTp.setItemMeta(rtM);
		inv.setItem(10, redTp);
		
		redShop = new ItemStack(Material.CHEST, 1);
		ItemMeta rsM = redShop.getItemMeta();
		rsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rsM.setDisplayName("§c§lRed §6§lShop");
		redShop.setItemMeta(rsM);
		inv.setItem(11, redShop);
		
		redScoreRoom = new ItemStack(Material.GRAY_DYE, 1);
		ItemMeta rsrM = redScoreRoom.getItemMeta();
		rsrM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rsrM.setDisplayName("§c§lRed §6§lScore Room");
		redScoreRoom.setItemMeta(rsrM);
		inv.setItem(12, redScoreRoom);
		
		redDef = new ItemStack(Material.COBBLESTONE_WALL, 1);
		ItemMeta rdM = redDef.getItemMeta();
		rdM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rdM.setDisplayName("§c§lRed §6§lBase Defense");
		redDef.setItemMeta(rdM);
		inv.setItem(13, redDef);
		
		redAtk = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta raM = redAtk.getItemMeta();
		raM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		raM.setDisplayName("§c§lRed §6§lBase Attack");
		redAtk.setItemMeta(raM);
		inv.setItem(14, redAtk);
		
		greenTp = new ItemStack(Material.LIME_BANNER, 1);
		ItemMeta gtM = greenTp.getItemMeta();
		gtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gtM.setDisplayName("§a§lGreen §6§lTeleporters");
		greenTp.setItemMeta(gtM);
		inv.setItem(19, greenTp);
		
		greenShop = new ItemStack(Material.CHEST, 1);
		ItemMeta gsM = greenShop.getItemMeta();
		gsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gsM.setDisplayName("§a§lGreen §6§lShop");
		greenShop.setItemMeta(gsM);
		inv.setItem(20, greenShop);
		
		greenScoreRoom = new ItemStack(Material.GRAY_DYE, 1);
		ItemMeta gsrM = greenScoreRoom.getItemMeta();
		gsrM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gsrM.setDisplayName("§a§lGreen §6§lScore Room");
		greenScoreRoom.setItemMeta(gsrM);
		inv.setItem(21, greenScoreRoom);
		
		greenDef = new ItemStack(Material.COBBLESTONE_WALL, 1);
		ItemMeta gdM = greenDef.getItemMeta();
		gdM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gdM.setDisplayName("§a§lGreen §6§lBase Defense");
		greenDef.setItemMeta(gdM);
		inv.setItem(22, greenDef);
		
		greenAtk = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta gaM = greenAtk.getItemMeta();
		gaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gaM.setDisplayName("§a§lGreen §6§lBase Attack");
		greenAtk.setItemMeta(gaM);
		inv.setItem(23, greenAtk);
		
		p.openInventory(inv);
		
	}
}
