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

public class EnchanterArmorsMenu {

	private Inventory inv;
	public static ItemStack armorEnchant;

	public EnchanterArmorsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.enchanter.armors").replace("&", "§") + " §lShop");
	}
	
	// ARMOR ENCHANTMENTS GETTER
	public static ItemStack getArmorEnchant(Enchantment en, String type, int lvl, String team) {

		armorEnchant = new ItemStack(Material.ENCHANTED_BOOK, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopENArmorsItemsConfig.getString("enchanter.armors."+type+lvl+".lore.end").replace("&", "§");
		String addLoreA = Files.shopENArmorsItemsConfig.getString("enchanter.armors."+type+lvl+".lore.a").replace("&", "§");
		String addPrice = "§a" + Files.shopENArmorsItemsConfig.getInt("enchanter.armors."+type+lvl+".p") + " §aGolds";
		armorEnchant.addUnsafeEnchantment(en, lvl);;
		ItemMeta armorEnchantM = armorEnchant.getItemMeta();
		armorEnchantM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		armorEnchantM.setDisplayName(Files.shopENArmorsItemsConfig.getString("enchanter.armors."+type+lvl+".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopENArmorsItemsConfig.getInt("enchanter.armors."+type+lvl+".lore.nbr");
		for(int i = 1; i <= l; i++) {
			loreList = Files.shopENArmorsItemsConfig.getString("enchanter.armors."+type+lvl+".lore."+i).replace("&", "§");
			lore.add(loreList);
			}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		armorEnchantM.setLore(lore);
		armorEnchant.setItemMeta(armorEnchantM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(armorEnchant)) {
				CasterMenu.teamOneBuy.put(armorEnchant, CasterMenu.teamOneBuy.get(armorEnchant)+1);
			} else {
				CasterMenu.teamOneBuy.put(armorEnchant, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(armorEnchant)) {
				CasterMenu.teamTwoBuy.put(armorEnchant, CasterMenu.teamTwoBuy.get(armorEnchant)+1);
			} else {
				CasterMenu.teamTwoBuy.put(armorEnchant, 1);
			}
		}
		return armorEnchant;
		}

	public void createArmorsEnchantMenu() {
		
		// PROTECTION
		inv.setItem(0, getArmorEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, "protection.", 1, ""));
		inv.setItem(1, getArmorEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, "protection.", 2, ""));
		inv.setItem(2, getArmorEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, "protection.", 3, ""));
		inv.setItem(3, getArmorEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, "protection.", 4, ""));
		
		// FIRE PROTECTION
		inv.setItem(18, getArmorEnchant(Enchantment.PROTECTION_FIRE, "fireprot.", 1, ""));
		inv.setItem(19, getArmorEnchant(Enchantment.PROTECTION_FIRE, "fireprot.", 2, ""));
		inv.setItem(20, getArmorEnchant(Enchantment.PROTECTION_FIRE, "fireprot.", 3, ""));
		inv.setItem(21, getArmorEnchant(Enchantment.PROTECTION_FIRE, "fireprot.", 4, ""));
		
		// PROJECTILES PROTECTION
		inv.setItem(5, getArmorEnchant(Enchantment.PROTECTION_PROJECTILE, "projectileprot.", 1, ""));
		inv.setItem(6, getArmorEnchant(Enchantment.PROTECTION_PROJECTILE, "projectileprot.", 2, ""));
		inv.setItem(7, getArmorEnchant(Enchantment.PROTECTION_PROJECTILE, "projectileprot.", 3, ""));
		inv.setItem(8, getArmorEnchant(Enchantment.PROTECTION_PROJECTILE, "projectileprot.", 4, ""));
		
		// THORNS
		inv.setItem(24, getArmorEnchant(Enchantment.THORNS, "thorns.", 1, ""));
		inv.setItem(25, getArmorEnchant(Enchantment.THORNS, "thorns.", 2, ""));
		inv.setItem(26, getArmorEnchant(Enchantment.THORNS, "thorns.", 3, ""));
		
		// RESPIRATION
		inv.setItem(40, getArmorEnchant(Enchantment.OXYGEN, "respiration.", 1, ""));
		inv.setItem(41, getArmorEnchant(Enchantment.OXYGEN, "respiration.", 2, ""));
		inv.setItem(42, getArmorEnchant(Enchantment.OXYGEN, "respiration.", 3, ""));
		
		// DEPTH STRIDER
		inv.setItem(36, getArmorEnchant(Enchantment.DEPTH_STRIDER, "depthstrider.", 1, ""));
		inv.setItem(37, getArmorEnchant(Enchantment.DEPTH_STRIDER, "depthstrider.", 2, ""));
		inv.setItem(38, getArmorEnchant(Enchantment.DEPTH_STRIDER, "depthstrider.", 3, ""));
		
		// FROST WALKER
		inv.setItem(45, getArmorEnchant(Enchantment.FROST_WALKER, "frostwalker.", 1, ""));
		inv.setItem(46, getArmorEnchant(Enchantment.FROST_WALKER, "frostwalker.", 2, ""));
		
		// CANCEL ENCHANT
		ItemStack cancel = new ItemStack(Material.BARRIER);
		ItemMeta cancelM = cancel.getItemMeta();
		cancelM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		cancelM.setDisplayName("§6§lCancel Enchantment");
		cancelM.setLore(Arrays.asList("§7§oClick me to cancel enchantment in progress..."));
		cancel.setItemMeta(cancelM);
		inv.setItem(52, cancel);
		
		// WEAPONS TAB
		ItemStack armorsTab = new ItemStack(Material.GOLDEN_SWORD, 1);
		armorsTab.addEnchantment(Enchantment.MENDING, 1);
		ItemMeta armorsTabM = armorsTab.getItemMeta();
		armorsTabM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		armorsTabM.setDisplayName("§6§lWeapons Enchantments");
		armorsTabM.setLore(Arrays.asList("§7§oSwitch to weapons enchants shop menu"));
		armorsTab.setItemMeta(armorsTabM);
		inv.setItem(53, armorsTab);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}
	
}
