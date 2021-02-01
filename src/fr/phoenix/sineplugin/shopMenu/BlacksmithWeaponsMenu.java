package fr.phoenix.sineplugin.shopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;

public class BlacksmithWeaponsMenu {

	private Inventory inv;
	public static ItemStack weapon;

	public BlacksmithWeaponsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.blacksmith.weapons").replace("&", "§") + " §lShop");
	}

	// WEAPONS GETTER
	public static ItemStack getWeapon(Material m, String piece, String type, int nbr, PotionType pot, String team) {
		weapon = new ItemStack(m, nbr);
		if (m.equals(Material.TIPPED_ARROW)) {
			PotionMeta pMeta = (PotionMeta) weapon.getItemMeta();
			pMeta.setBasePotionData(new PotionData(pot));
			weapon.setItemMeta(pMeta);
		}
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopBSWeaponsItemsConfig
				.getString("blacksmith.weapons." + piece + type + ".lore.end").replace("&", "§");
		String addLoreA = Files.shopBSWeaponsItemsConfig
				.getString("blacksmith.weapons." + piece + type + ".lore.a").replace("&", "§");
		String addPrice = "§a"
				+ Files.shopBSWeaponsItemsConfig.getInt("blacksmith.weapons." + piece + type + ".p")
				+ " §aGolds";
		ItemMeta weaponM = weapon.getItemMeta();
		//weaponM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		if (m.equals(Material.TIPPED_ARROW))
			weaponM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		weaponM.setDisplayName(Files.shopBSWeaponsItemsConfig
				.getString("blacksmith.weapons." + piece + type + ".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopBSWeaponsItemsConfig.getInt("blacksmith.weapons." + piece + type + ".lore.nbr");
		;
		for (int i = 1; i <= l; i++) {
			loreList = Files.shopBSWeaponsItemsConfig
					.getString("blacksmith.weapons." + piece + type + ".lore." + i).replace("&", "§");
			lore.add(loreList);
		}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		weaponM.setLore(lore);
		weapon.setItemMeta(weaponM);
		if (team.equals("one")) {

			if (CasterMenu.teamOneBuy.containsKey(weapon)) {
				CasterMenu.teamOneBuy.put(weapon, CasterMenu.teamOneBuy.get(weapon) + nbr);
			} else {
				CasterMenu.teamOneBuy.put(weapon, nbr);
			}
		}
		if (team.equals("two")) {
			if (CasterMenu.teamTwoBuy.containsKey(weapon)) {
				CasterMenu.teamTwoBuy.put(weapon, CasterMenu.teamTwoBuy.get(weapon) + nbr);
			} else {
				CasterMenu.teamTwoBuy.put(weapon, nbr);
			}
		}
		if (Vars.canBreak.contains(m)) {
		net.minecraft.server.v1_14_R1.ItemStack weaponNMS = CraftItemStack.asNMSCopy(weapon);
		NBTTagList canBreakList = new NBTTagList();
		canBreakList.add(new NBTTagString("minecraft:standing_banner"));
		canBreakList.add(new NBTTagString("minecraft:wall_banner"));
		NBTTagCompound cp = (weaponNMS.hasTag()) ? weaponNMS.getTag() : new NBTTagCompound();
		cp.set("CanDestroy", canBreakList);
		weaponNMS.setTag(cp);
		weapon = CraftItemStack.asBukkitCopy(weaponNMS);
		}
		return weapon;
	}

	public void createWeaponsMenu() {

		// SWORDS
		inv.setItem(0, getWeapon(Material.WOODEN_SWORD, "swords.", "wood", 1, PotionType.LUCK, ""));
		inv.setItem(1, getWeapon(Material.STONE_SWORD, "swords.", "stone", 1, PotionType.LUCK, ""));
		inv.setItem(2, getWeapon(Material.IRON_SWORD, "swords.", "iron", 1, PotionType.LUCK, ""));
		inv.setItem(3, getWeapon(Material.DIAMOND_SWORD, "swords.", "diamond", 1, PotionType.LUCK, ""));

		// AXES
		inv.setItem(5, getWeapon(Material.WOODEN_AXE, "axes.", "wood", 1, PotionType.LUCK, ""));
		inv.setItem(6, getWeapon(Material.STONE_AXE, "axes.", "stone", 1, PotionType.LUCK, ""));
		inv.setItem(7, getWeapon(Material.IRON_AXE, "axes.", "iron", 1, PotionType.LUCK, ""));
		inv.setItem(8, getWeapon(Material.DIAMOND_AXE, "axes.", "diamond", 1, PotionType.LUCK, ""));

		// BOW
		inv.setItem(18, getWeapon(Material.BOW, "bow", "", 1, PotionType.LUCK, ""));

		// ARROWS
		inv.setItem(21, getWeapon(Material.ARROW, "arrows.", "standard", 20, PotionType.LUCK, ""));
		inv.setItem(22, getWeapon(Material.SPECTRAL_ARROW, "arrows.", "spectral", 5, PotionType.LUCK, ""));
		inv.setItem(23, getWeapon(Material.TIPPED_ARROW, "arrows.", "poison", 5, PotionType.POISON, ""));
		inv.setItem(24, getWeapon(Material.TIPPED_ARROW, "arrows.", "weakness", 5, PotionType.WEAKNESS, ""));
		inv.setItem(25, getWeapon(Material.TIPPED_ARROW, "arrows.", "slowness", 5, PotionType.SLOWNESS, ""));
		inv.setItem(26, getWeapon(Material.TIPPED_ARROW, "arrows.", "harming", 5, PotionType.INSTANT_DAMAGE, ""));

		// ARMORS TAB
		ItemStack armorsTab = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
		ItemMeta armorsTabM = armorsTab.getItemMeta();
		armorsTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		armorsTabM.setDisplayName("§6§lArmors");
		armorsTabM.setLore(Arrays.asList("§7§oSwitch to armors shop menu"));
		armorsTab.setItemMeta(armorsTabM);
		inv.setItem(44, armorsTab);

	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}

}
