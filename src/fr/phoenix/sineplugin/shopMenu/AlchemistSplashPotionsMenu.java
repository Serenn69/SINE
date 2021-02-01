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

public class AlchemistSplashPotionsMenu {

	private Inventory inv;
	public static ItemStack potionSplash;

	public AlchemistSplashPotionsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.alchemist.splash").replace("&", "§") + " §lShop");
	}

	// POTIONS GETTER
	public static ItemStack getSplashPotion(PotionType pot, boolean time, boolean up, String type, String effect, String team) {
		potionSplash = new ItemStack(Material.SPLASH_POTION, 1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".lore.end").replace("&", "§");
		String addLoreA = Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".lore.a").replace("&", "§");
		String addPrice = "§a" + Files.shopALPotionsItemsConfig.getInt("alchemist.potions."+type+effect+".p") + " §aGolds";
		PotionMeta potionSplashPM = (PotionMeta) potionSplash.getItemMeta();
		potionSplashPM.setBasePotionData(new PotionData(pot, time, up));
		potionSplash.setItemMeta(potionSplashPM);
		ItemMeta potionSplashM = potionSplash.getItemMeta();
		potionSplashM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		potionSplashM.setDisplayName(Files.shopALPotionsItemsConfig.getString("alchemist.potions."+type+effect+".name").replace("&", "§"));
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
		potionSplashM.setLore(lore);
		potionSplash.setItemMeta(potionSplashM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(potionSplash)) {
				CasterMenu.teamOneBuy.put(potionSplash, CasterMenu.teamOneBuy.get(potionSplash)+1);
			} else {
				CasterMenu.teamOneBuy.put(potionSplash, 1);
			}
		}
		if (team.equals("two")) {
			
			if (CasterMenu.teamTwoBuy.containsKey(potionSplash)) {
				CasterMenu.teamTwoBuy.put(potionSplash, CasterMenu.teamTwoBuy.get(potionSplash)+1);
			} else {
				CasterMenu.teamTwoBuy.put(potionSplash, 1);
			}
		}
		return potionSplash;
	}
		
	public void createSplashPotionsMenu() {

		// REGEN
		inv.setItem(1, getSplashPotion(PotionType.REGEN, false, false, "splash.", "regen", ""));

		// SWIFTNESS
		inv.setItem(2, getSplashPotion(PotionType.SPEED, false, false, "splash.", "swiftness", ""));

		// HEAL
		inv.setItem(3, getSplashPotion(PotionType.INSTANT_HEAL, false, false, "splash.", "heal", ""));

		// POISON
		inv.setItem(4, getSplashPotion(PotionType.POISON, false, false, "splash.", "poison", ""));

		// HARMING
		inv.setItem(5, getSplashPotion(PotionType.INSTANT_DAMAGE, false, false, "splash.", "harming", ""));

		// SLOWNESS
		inv.setItem(6, getSplashPotion(PotionType.SLOWNESS, false, false, "splash.", "slowness", ""));

		// WEAKNESS
		inv.setItem(7, getSplashPotion(PotionType.WEAKNESS, false, false, "splash.", "weakness", ""));

		// PERSONNAL TAB
		ItemStack personnalTab = new ItemStack(Material.POTION, 1);
		ItemMeta personnalTabM = personnalTab.getItemMeta();
		personnalTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		personnalTabM.setDisplayName("§6§lPersonnal Potions");
		personnalTabM.setLore(Arrays.asList("§7§oSwitch to personnal potions shop menu"));
		personnalTab.setItemMeta(personnalTabM);
		inv.setItem(25, personnalTab);

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
