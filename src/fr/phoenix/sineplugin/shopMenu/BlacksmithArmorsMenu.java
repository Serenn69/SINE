package fr.phoenix.sineplugin.shopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;

public class BlacksmithArmorsMenu {
	
	private Inventory inv;
	public static ItemStack armor;

	public BlacksmithArmorsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.blacksmith.armors").replace("&", "§") + " §lShop");
	}

	// ARMORS GETTER
	public static ItemStack getArmor(Material m, String piece, String type, Player p, String team) {
		armor = new ItemStack(m, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopBSArmorsItemsConfig.getString("blacksmith.armors."+piece+type+".lore.end").replace("&", "§");
		String addLoreA = Files.shopBSArmorsItemsConfig.getString("blacksmith.armors."+piece+type+".lore.a").replace("&", "§");
		String addPrice = "§a" + Files.shopBSArmorsItemsConfig.getInt("blacksmith.armors."+piece+type+".p") + " §aGolds";
		ItemMeta armorM = armor.getItemMeta();
		if (m.equals(Material.SHIELD)) {
			armorM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
			BlockStateMeta bMeta = (BlockStateMeta) armorM;
			Banner banner = (Banner) bMeta.getBlockState();
			if (Vars.pNameTeamOne.containsKey(p.getName()))	banner.setBaseColor(DyeColor.RED);
			if (Vars.pNameTeamTwo.containsKey(p.getName()))	banner.setBaseColor(DyeColor.GREEN);
			banner.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
			banner.update();
			bMeta.setBlockState(banner);
			armor.setItemMeta(bMeta); }
		armorM.setDisplayName(
				Files.shopBSArmorsItemsConfig.getString("blacksmith.armors." + piece + type + ".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopBSArmorsItemsConfig.getInt("blacksmith.armors."+piece+type+".lore.nbr");;
		for(int i = 1; i <= l; i++) {
			loreList = Files.shopBSArmorsItemsConfig.getString("blacksmith.armors."+piece+type+".lore."+i).replace("&", "§");
			lore.add(loreList);
			}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		armorM.setLore(lore);
		armor.setItemMeta(armorM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(armor)) {
				CasterMenu.teamOneBuy.put(armor, CasterMenu.teamOneBuy.get(armor)+1);
			} else {
				CasterMenu.teamOneBuy.put(armor, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(armor)) {
				CasterMenu.teamTwoBuy.put(armor, CasterMenu.teamTwoBuy.get(armor)+1);
			} else {
				CasterMenu.teamTwoBuy.put(armor, 1);
			}
		}
		return armor;
	}
	
	public void createArmorsMenu(Player p) {

		// HELMETS
		inv.setItem(0, getArmor(Material.LEATHER_HELMET, "helmets.", "leather", p, ""));
		inv.setItem(1, getArmor(Material.IRON_HELMET, "helmets.", "iron", p, ""));
		inv.setItem(2, getArmor(Material.DIAMOND_HELMET, "helmets.", "diamond", p, ""));

		// CHESTPLATES
		inv.setItem(4, getArmor(Material.LEATHER_CHESTPLATE, "chestplates.", "leather", p, ""));
		inv.setItem(5, getArmor(Material.IRON_CHESTPLATE, "chestplates.", "iron", p, ""));
		inv.setItem(6, getArmor(Material.DIAMOND_CHESTPLATE, "chestplates.", "diamond", p, ""));

		// LEGGINGS
		inv.setItem(18, getArmor(Material.LEATHER_LEGGINGS, "leggings.", "leather", p, ""));
		inv.setItem(19, getArmor(Material.IRON_LEGGINGS, "leggings.", "iron", p, ""));
		inv.setItem(20, getArmor(Material.DIAMOND_LEGGINGS, "leggings.", "diamond", p, ""));

		// BOOTS
		inv.setItem(22, getArmor(Material.LEATHER_BOOTS, "boots.", "leather", p, ""));
		inv.setItem(23, getArmor(Material.IRON_BOOTS, "boots.", "iron", p, ""));
		inv.setItem(24, getArmor(Material.DIAMOND_BOOTS, "boots.", "diamond", p, ""));

		// SHIELDS
		inv.setItem(17, getArmor(Material.SHIELD, "shield", "", p, ""));
		

		// WEAPONS TAB
		ItemStack weaponsTab = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta weaponsTabM = weaponsTab.getItemMeta();
		weaponsTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		weaponsTabM.setDisplayName("§6§lWeapons");
		weaponsTabM.setLore(Arrays.asList("§7§oSwitch to weapons shop menu"));
		weaponsTab.setItemMeta(weaponsTabM);
		inv.setItem(44, weaponsTab);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}
}
