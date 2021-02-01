package fr.phoenix.sineplugin.shopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.caster.CasterMenu;

public class AlchemistPersonnalPotionsMenu {

	private Inventory inv;
	public static ItemStack potionPerso;

	public AlchemistPersonnalPotionsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.alchemist.solo").replace("&", "§") + " §lShop");
	}

	// POTIONS GETTER
	public static ItemStack getPersonnalPotion(PotionType pot, boolean time, boolean up, String type, String effect,
			String team) {
		potionPerso = new ItemStack(Material.POTION, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopALPotionsItemsConfig
				.getString("alchemist.potions." + type + effect + ".lore.end").replace("&", "§");
		String addLoreA = Files.shopALPotionsItemsConfig
				.getString("alchemist.potions." + type + effect + ".lore.a").replace("&", "§");
		String addPrice = "§a"
				+ Files.shopALPotionsItemsConfig.getInt("alchemist.potions." + type + effect + ".p")
				+ " §aGolds";
		PotionMeta potionPM = (PotionMeta) potionPerso.getItemMeta();
		potionPM.setBasePotionData(new PotionData(pot, time, up));
		potionPerso.setItemMeta(potionPM);
		ItemMeta potionM = potionPerso.getItemMeta();
		potionM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		potionM.setDisplayName(Files.shopALPotionsItemsConfig
				.getString("alchemist.potions." + type + effect + ".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopALPotionsItemsConfig.getInt("alchemist.potions." + type + effect + ".lore.nbr");
		for (int i = 1; i <= l; i++) {
			loreList = Files.shopALPotionsItemsConfig
					.getString("alchemist.potions." + type + effect + ".lore." + i).replace("&", "§");
			lore.add(loreList);
		}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		potionM.setLore(lore);
		potionPerso.setItemMeta(potionM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(potionPerso)) {
				CasterMenu.teamOneBuy.put(potionPerso, CasterMenu.teamOneBuy.get(potionPerso)+1);
			} else {
				CasterMenu.teamOneBuy.put(potionPerso, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(potionPerso)) {
				CasterMenu.teamTwoBuy.put(potionPerso, CasterMenu.teamTwoBuy.get(potionPerso)+1);
			} else {
				CasterMenu.teamTwoBuy.put(potionPerso, 1);
			}
		}
		return potionPerso;
		
	}

	public void createPersonnalPotionsMenu() {

		// REGEN
		inv.setItem(1, getPersonnalPotion(PotionType.REGEN, false, false, "solo.", "regen", ""));

		// SWIFTNESS
		inv.setItem(2, getPersonnalPotion(PotionType.SPEED, false, false, "solo.", "swiftness", ""));

		// HEAL
		inv.setItem(3, getPersonnalPotion(PotionType.INSTANT_HEAL, false, false, "solo.", "heal", ""));

		// STRENGTH
		inv.setItem(4, getPersonnalPotion(PotionType.STRENGTH, false, false, "solo.", "strength", ""));

		// LEAPING II
		inv.setItem(5, getPersonnalPotion(PotionType.JUMP, false, true, "solo.", "leaping2", ""));

		// WATER BREATHING
		inv.setItem(6, getPersonnalPotion(PotionType.WATER_BREATHING, false, false, "solo.", "waterbreath", ""));

		// INVISIBILITY
		inv.setItem(7, getPersonnalPotion(PotionType.INVISIBILITY, false, false, "solo.", "invis", ""));

		// SPLASH TAB
		ItemStack splashTab = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta splashTabM = splashTab.getItemMeta();
		splashTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		splashTabM.setDisplayName("§6§lSplash Potions");
		splashTabM.setLore(Arrays.asList("§7§oSwitch to splash potions shop menu"));
		splashTab.setItemMeta(splashTabM);
		inv.setItem(25, splashTab);

		// LINGERING TAB
		ItemStack lingeringTab = new ItemStack(Material.LINGERING_POTION, 1);
		ItemMeta lingeringTabM = lingeringTab.getItemMeta();
		lingeringTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		lingeringTabM.setDisplayName("§6§lLingering Potions");
		lingeringTabM.setLore(Arrays.asList("§7§oSwitch to lingering potions shop menu"));
		lingeringTab.setItemMeta(lingeringTabM);
		inv.setItem(26, lingeringTab);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}

}
