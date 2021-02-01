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

public class AlchemistLingeringPotionsMenu {

	private Inventory inv;
	public static ItemStack potionLinger;

	public AlchemistLingeringPotionsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.alchemist.lingering").replace("&", "§") + " §lShop");
	}

	// POTIONS GETTER
	public static ItemStack getLingeringPotion(PotionType pot, boolean time, boolean up, String type, String effect, String team) {
		potionLinger = new ItemStack(Material.LINGERING_POTION, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".lore.end").replace("&", "§");
		String addLoreA = Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".lore.a").replace("&", "§");
		String addPrice = "§a" + Files.shopALPotionsItemsConfig.getInt("alchemist.potions."+type+effect+".p") + " §aGolds";
		PotionMeta potionLingerPM = (PotionMeta) potionLinger.getItemMeta();
		potionLingerPM.setBasePotionData(new PotionData(pot, time, up));
		potionLinger.setItemMeta(potionLingerPM);
		ItemMeta potionLingerM = potionLinger.getItemMeta();
		potionLingerM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		potionLingerM.setDisplayName(Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopALPotionsItemsConfig.getInt("alchemist.potions."+type+effect+".lore.nbr");
		for(int i = 1; i <= l; i++) {
			loreList = Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".lore."+i).replace("&", "§");
			lore.add(loreList);
			}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		potionLingerM.setLore(lore);
		potionLinger.setItemMeta(potionLingerM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(potionLinger)) {
				CasterMenu.teamOneBuy.put(potionLinger, CasterMenu.teamOneBuy.get(potionLinger)+1);
			} else {
				CasterMenu.teamOneBuy.put(potionLinger, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(potionLinger)) {
				CasterMenu.teamTwoBuy.put(potionLinger, CasterMenu.teamTwoBuy.get(potionLinger)+1);
			} else {
				CasterMenu.teamTwoBuy.put(potionLinger, 1);
			}
		}
		return potionLinger;
	}

	public void createLingeringPotionsMenu() {

		// REGEN
		inv.setItem(2, getLingeringPotion(PotionType.REGEN, true, false, "lingering.", "regen", ""));

		// POISON
		inv.setItem(3, getLingeringPotion(PotionType.POISON, true, false, "lingering.", "poison", ""));

		// HARMING
		inv.setItem(4, getLingeringPotion(PotionType.INSTANT_DAMAGE, false, false, "lingering.", "harming", ""));

		// SLOWNESS
		inv.setItem(5, getLingeringPotion(PotionType.SLOWNESS, true, false, "lingering.", "slowness", ""));

		// WEAKNESS
		inv.setItem(6, getLingeringPotion(PotionType.WEAKNESS, true, false, "lingering.", "weakness", ""));

		// PERSONNAL TAB
		ItemStack personnalTab = new ItemStack(Material.POTION, 1);
		ItemMeta personnalTabM = personnalTab.getItemMeta();
		personnalTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		personnalTabM.setDisplayName("§6§lPersonnal Potions");
		personnalTabM.setLore(Arrays.asList("§7§oSwitch to personnal potions shop menu"));
		personnalTab.setItemMeta(personnalTabM);
		inv.setItem(25, personnalTab);

		// SPLASH TAB
		ItemStack splashTab = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta splashTabM = splashTab.getItemMeta();
		splashTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		splashTabM.setDisplayName("§6§lSplash Potions");
		splashTabM.setLore(Arrays.asList("§7§oSwitch to splash potions shop menu"));
		splashTab.setItemMeta(splashTabM);
		inv.setItem(26, splashTab);

	}
	
	public void openMenu(Player p) {
		p.openInventory(inv);
	}
	
}
