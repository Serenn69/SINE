package fr.phoenix.sineplugin.caster;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.phoenix.sineplugin.phases.Countdown;

public class CasterHotbar {

	public static ItemStack hall, redShop, greenShop, settings, chest, enderchest;

	public void casterStuffMenu(Player p, int menu) {

		hall = new ItemStack(Material.OAK_DOOR, 1);
		ItemMeta hM = hall.getItemMeta();
		hM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		hM.setDisplayName("§6§lTp to Hall");
		hall.setItemMeta(hM);
		if (menu == 1)
			p.getInventory().setItem(0, hall);
		if (menu == 0)
			p.getInventory().remove(hall);

		redShop = new ItemStack(Material.RED_WOOL, 1);
		ItemMeta rSM = redShop.getItemMeta();
		rSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rSM.setDisplayName("§6§lTp to §c§lRED §6§lShop");
		redShop.setItemMeta(rSM);
		if (menu == 1)
			p.getInventory().setItem(1, redShop);
		if (menu == 0)
			p.getInventory().remove(redShop);

		greenShop = new ItemStack(Material.LIME_WOOL, 1);
		ItemMeta gSM = greenShop.getItemMeta();
		gSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gSM.setDisplayName("§6§lTp to §a§lGREEN §6§lShop");
		greenShop.setItemMeta(gSM);
		if (menu == 1)
			p.getInventory().setItem(2, greenShop);
		if (menu == 0)
			p.getInventory().remove(greenShop);

		settings = new ItemStack(Material.STICKY_PISTON, 1);
		ItemMeta sM = settings.getItemMeta();
		sM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sM.setDisplayName("§6§lTp to Settings");
		settings.setItemMeta(sM);
		if (menu == 1)
			p.getInventory().setItem(3, settings);
		if (menu == 0)
			p.getInventory().remove(settings);

		chest = new ItemStack(Material.CHEST, 1);
		ItemMeta chM = chest.getItemMeta();
		chM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		chM.setDisplayName("§6§lRed and Green purchases");
		chM.setLore(Arrays.asList("§7§oOpen me to see purchases from Red and Green Teams"));
		chest.setItemMeta(chM);
		if (menu == 1)
			p.getInventory().setItem(7, chest);
		if (menu == 0)
			p.getInventory().remove(chest);

		if (Countdown.round >= 2) {
			enderchest = new ItemStack(Material.ENDER_CHEST, 1);
			ItemMeta echM = enderchest.getItemMeta();
			echM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			echM.setDisplayName("§6§lPrevious Red and Green purchases");
			echM.setLore(Arrays.asList("§7§oOpen me to see purchases from Red and Green Teams in previous round"));
			enderchest.setItemMeta(echM);
			if (menu == 1)
				p.getInventory().setItem(8, enderchest);
			if (menu == 0)
				p.getInventory().remove(enderchest);
		}

	}

	@SuppressWarnings("deprecation")
	public void specStuffMenu(Player p, int menu) {

		hall = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta skM = (SkullMeta) hall.getItemMeta();
		skM.setOwner("Serenny");
		skM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skM.setDisplayName("§6§lTp to Hall");
		hall.setItemMeta(skM);
		if (menu == 1)
			p.getInventory().setItem(27, hall);
		if (menu == 0)
			p.getInventory().remove(hall);

		redShop = new ItemStack(Material.RED_WOOL, 1);
		ItemMeta rSM = redShop.getItemMeta();
		rSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rSM.setDisplayName("§6§lTp to §c§lRED §6§lShop");
		redShop.setItemMeta(rSM);
		if (menu == 1)
			p.getInventory().setItem(28, redShop);
		if (menu == 0)
			p.getInventory().remove(redShop);

		greenShop = new ItemStack(Material.LIME_WOOL, 1);
		ItemMeta gSM = greenShop.getItemMeta();
		gSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gSM.setDisplayName("§6§lTp to §a§lGREEN §6§lShop");
		greenShop.setItemMeta(gSM);
		if (menu == 1)
			p.getInventory().setItem(29, greenShop);
		if (menu == 0)
			p.getInventory().remove(greenShop);
	}
}
