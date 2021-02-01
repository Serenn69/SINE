package fr.phoenix.sineplugin.admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.phases.Countdown;

public class AdminDebug {

	private ItemStack clearHolo, addOne, addTwo, addCast, chest, enderchest, testHolo;
	private Inventory inv;

	public AdminDebug(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oDebug Menu");
	}

	public void createDebugMenu(Player p) {

		addOne = new ItemStack(Material.RED_STAINED_GLASS, 1);
		ItemMeta aOM = addOne.getItemMeta();
		aOM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		aOM.setDisplayName("§cAdd/Remove PNameOne");
		addOne.setItemMeta(aOM);
		inv.setItem(10, addOne);

		addTwo = new ItemStack(Material.LIME_STAINED_GLASS, 1);
		ItemMeta aTM = addTwo.getItemMeta();
		aTM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		aTM.setDisplayName("§aAdd/Remove PNameTwo");
		addTwo.setItemMeta(aTM);
		inv.setItem(11, addTwo);

		addCast = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
		ItemMeta acM = addCast.getItemMeta();
		acM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		acM.setDisplayName("§0Add/Remove PNameCaster");
		addCast.setItemMeta(acM);
		inv.setItem(12, addCast);
		
		clearHolo = new ItemStack(Material.ARMOR_STAND, 1);
		ItemMeta chM = clearHolo.getItemMeta();
		chM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		chM.setDisplayName("§6§lClear Holograms");
		clearHolo.setItemMeta(chM);
		inv.setItem(14, clearHolo);

		chest = new ItemStack(Material.CHEST, 1);
		ItemMeta cM = chest.getItemMeta();
		cM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cM.setDisplayName("§6§lRed and Green purchases");
		cM.setLore(Arrays.asList("§7§oSee purchases from Red and Green Teams"));
		chest.setItemMeta(cM);
		inv.setItem(15, chest);

		if (Countdown.round >= 2) {
			enderchest = new ItemStack(Material.ENDER_CHEST, 1);
			ItemMeta echM = enderchest.getItemMeta();
			echM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			echM.setDisplayName("§6§lPrevious Red and Green purchases");
			echM.setLore(Arrays.asList("§7§oSee purchases from Red and Green Teams in previous round"));
			enderchest.setItemMeta(echM);
			inv.setItem(16, enderchest);
		}
		
		testHolo = new ItemStack(Material.REDSTONE, 1);
		ItemMeta thM = testHolo.getItemMeta();
		thM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		thM.setDisplayName("§6§lTest Holograms");
		testHolo.setItemMeta(thM);
		inv.setItem(26, testHolo);

		p.openInventory(inv);

	}

}
