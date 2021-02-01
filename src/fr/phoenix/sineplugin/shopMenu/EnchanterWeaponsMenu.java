package fr.phoenix.sineplugin.shopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.caster.CasterMenu;

public class EnchanterWeaponsMenu {

	private Inventory inv;
	public static ItemStack weaponEnchant;

	public EnchanterWeaponsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.enchanter.weapons").replace("&", "§") + " §lShop");
	}

	// WEAPON ENCHANTMENTS GETTER
	public static ItemStack getWeaponEnchant(Enchantment en, String type, int lvl, String team) {

		weaponEnchant = new ItemStack(Material.ENCHANTED_BOOK, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopENWeaponsItemsConfig
				.getString("enchanter.weapons." + type + lvl + ".lore.end").replace("&", "§");
		String addLoreA = Files.shopENWeaponsItemsConfig.getString("enchanter.weapons." + type + lvl + ".lore.a")
				.replace("&", "§");
		String addPrice = "§a" + Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + type + lvl + ".p")
				+ " §aGolds";
		weaponEnchant.addUnsafeEnchantment(en, lvl);
		ItemMeta weaponEnchantM = weaponEnchant.getItemMeta();
		weaponEnchantM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		weaponEnchantM.setDisplayName(Files.shopENWeaponsItemsConfig
				.getString("enchanter.weapons." + type + lvl + ".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + type + lvl + ".lore.nbr");
		for (int i = 1; i <= l; i++) {
			loreList = Files.shopENWeaponsItemsConfig.getString("enchanter.weapons." + type + lvl + ".lore." + i)
					.replace("&", "§");
			lore.add(loreList);
		}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		weaponEnchantM.setLore(lore);
		weaponEnchant.setItemMeta(weaponEnchantM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(weaponEnchant)) {
				CasterMenu.teamOneBuy.put(weaponEnchant, CasterMenu.teamOneBuy.get(weaponEnchant)+1);
			} else {
				CasterMenu.teamOneBuy.put(weaponEnchant, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(weaponEnchant)) {
				CasterMenu.teamTwoBuy.put(weaponEnchant, CasterMenu.teamTwoBuy.get(weaponEnchant)+1);
			} else {
				CasterMenu.teamTwoBuy.put(weaponEnchant, 1);
			}
		}
		return weaponEnchant;
	}

	public void createWeaponsEnchantMenu() {

		// SHARPNESS
		inv.setItem(0, getWeaponEnchant(Enchantment.DAMAGE_ALL, "sharpness.", 1, ""));
		inv.setItem(1, getWeaponEnchant(Enchantment.DAMAGE_ALL, "sharpness.", 2, ""));
		inv.setItem(2, getWeaponEnchant(Enchantment.DAMAGE_ALL, "sharpness.", 3, ""));
	//	inv.setItem(3, getWeaponEnchant(Enchantment.DAMAGE_ALL, "sharpness.", 4, ""));
	//	inv.setItem(4, getWeaponEnchant(Enchantment.DAMAGE_ALL, "sharpness.", 5, ""));

		// KNOCKBACK
		inv.setItem(4, getWeaponEnchant(Enchantment.KNOCKBACK, "knockback.", 1, ""));
		inv.setItem(5, getWeaponEnchant(Enchantment.KNOCKBACK, "knockback.", 2, ""));

		// FIRE ASPECT
		inv.setItem(7, getWeaponEnchant(Enchantment.FIRE_ASPECT, "fireaspect.", 1, ""));
		inv.setItem(8, getWeaponEnchant(Enchantment.FIRE_ASPECT, "fireaspect.", 2, ""));

		// POWER
		inv.setItem(18, getWeaponEnchant(Enchantment.ARROW_DAMAGE, "power.", 1, ""));
		inv.setItem(19, getWeaponEnchant(Enchantment.ARROW_DAMAGE, "power.", 2, ""));
		inv.setItem(20, getWeaponEnchant(Enchantment.ARROW_DAMAGE, "power.", 3, ""));
	//	inv.setItem(21, getWeaponEnchant(Enchantment.ARROW_DAMAGE, "power.", 4, ""));
	//	inv.setItem(22, getWeaponEnchant(Enchantment.ARROW_DAMAGE, "power.", 5, ""));

		// PUNCH
		inv.setItem(22, getWeaponEnchant(Enchantment.ARROW_KNOCKBACK, "punch.", 1, ""));
		inv.setItem(23, getWeaponEnchant(Enchantment.ARROW_KNOCKBACK, "punch.", 2, ""));

		// FLAME
	//	inv.setItem(39, getWeaponEnchant(Enchantment.ARROW_FIRE, "flame.", 1, ""));

		// INFINITY
		inv.setItem(25, getWeaponEnchant(Enchantment.ARROW_INFINITE, "infinity.", 1, ""));

		// CANCEL ENCHANT
		ItemStack cancel = new ItemStack(Material.BARRIER);
		ItemMeta cancelM = cancel.getItemMeta();
		cancelM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		cancelM.setDisplayName("§6§lCancel Enchantment");
		cancelM.setLore(Arrays.asList("§7§oClick me to cancel enchantment in progress..."));
		cancel.setItemMeta(cancelM);
		inv.setItem(34, cancel);

		// ARMORS TAB
		ItemStack armorsTab = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
		armorsTab.addEnchantment(Enchantment.VANISHING_CURSE, 1);
		ItemMeta armorsTabM = armorsTab.getItemMeta();
		armorsTabM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		armorsTabM.setDisplayName("§6§lArmors Enchantments");
		armorsTabM.setLore(Arrays.asList("§7§oSwitch to armors enchants shop menu"));
		armorsTab.setItemMeta(armorsTabM);
		inv.setItem(35, armorsTab);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}

}
