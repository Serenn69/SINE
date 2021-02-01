package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminSelectParticles {

	private ItemStack crown, wings, victory, stopCrown, stopWings, stopVictory, morePoints, lessPoints;
	private Inventory inv;

	public AdminSelectParticles(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oSelect Particles Victory Menu");
	}
		
	public void createSelectParticlesMenu(Player p) {
		
		crown = new ItemStack(Material.GOLDEN_HELMET, 1);
		ItemMeta cM = crown.getItemMeta();
		cM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cM.setDisplayName("§6§lCrown Particles");
		crown.setItemMeta(cM);
		inv.setItem(11, crown);
		
		stopCrown = new ItemStack(Material.BARRIER, 1);
		ItemMeta socM = stopCrown.getItemMeta();
		socM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		socM.setDisplayName("§6§lStop Crown Particles");
		stopCrown.setItemMeta(socM);
		inv.setItem(20, stopCrown);
		
		wings = new ItemStack(Material.ELYTRA, 1);
		ItemMeta wM = wings.getItemMeta();
		wM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wM.setDisplayName("§6§lWings Particles");
		wings.setItemMeta(wM);
		inv.setItem(12, wings);
		
		stopWings = new ItemStack(Material.BARRIER, 1);
		ItemMeta sowM = stopWings.getItemMeta();
		sowM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sowM.setDisplayName("§6§lStop Wings Particles");
		stopWings.setItemMeta(sowM);
		inv.setItem(21, stopWings);
		
		victory = new ItemStack(Material.MAP, 1);
		ItemMeta vM = victory.getItemMeta();
		vM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		vM.setDisplayName("§6§lVictory Particles");
		victory.setItemMeta(vM);
		inv.setItem(13, victory);
		
		stopVictory = new ItemStack(Material.BARRIER, 1);
		ItemMeta sovM = stopVictory.getItemMeta();
		sovM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sovM.setDisplayName("§6§lStop Victory Particles");
		stopVictory.setItemMeta(sovM);
		inv.setItem(22, stopVictory);
		
		morePoints = new ItemStack(Material.LIME_DYE, 1);
		ItemMeta mpM = morePoints.getItemMeta();
		mpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mpM.setDisplayName("§6§lAdd 1 particle on Crown");
		morePoints.setItemMeta(mpM);
		inv.setItem(15, morePoints);
		
		lessPoints = new ItemStack(Material.RED_DYE, 1);
		ItemMeta lpM = lessPoints.getItemMeta();
		lpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lpM.setDisplayName("§6§lRemove 1 particle on Crown");
		lessPoints.setItemMeta(lpM);
		inv.setItem(24, lessPoints);
		
		p.openInventory(inv);
		
	}
}
