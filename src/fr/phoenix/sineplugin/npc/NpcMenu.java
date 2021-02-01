package fr.phoenix.sineplugin.npc;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Files;

public class NpcMenu {

	private Inventory inv;

	public NpcMenu(int size, String name) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("npc.shop." + name).replace("&", "§") + " §lShop");

	}

	public void createMenuBS() {

		String loreW = "§4§oSwords", loreW2 = "§4§oAxes", loreW3 = "§4§oBow and arrows";
		ItemStack weapons = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta wM = weapons.getItemMeta();
		wM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wM.setDisplayName("§6§lWeapons");
		wM.setLore(Arrays.asList(loreW, loreW2, loreW3));
		weapons.setItemMeta(wM);
		inv.setItem(12, weapons);

		String loreA = "§4§oLeather", loreA2 = "§4§oIron", loreA3 = "§4§oDiamond", loreA4 = "§4§oShield";
		ItemStack armors = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
		ItemMeta aM = armors.getItemMeta();
		aM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		aM.setDisplayName("§6§lArmors");
		aM.setLore(Arrays.asList(loreA, loreA2, loreA3, loreA4));
		armors.setItemMeta(aM);
		inv.setItem(14, armors);
	}

	public void createMenuEN() {

		String loreWE = "§4§oSharpness", loreWE2 = "§4§oPower", loreWE3 = "§4§oKnockback", loreWE4 = "§4§oPunch",
				loreWE5 = "§4§oFire Aspect", loreWE6 = "§4§oFlame", loreWE7 = "§4§oInfinity";
		ItemStack weaponsEnchant = new ItemStack(Material.GOLDEN_SWORD, 1);
		weaponsEnchant.addEnchantment(Enchantment.MENDING, 1);
		ItemMeta wEM = weaponsEnchant.getItemMeta();
		wEM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		wEM.setDisplayName("§6§lWeapons Enchantments");
		wEM.setLore(Arrays.asList(loreWE, loreWE2, loreWE3, loreWE4, loreWE5, loreWE6, loreWE7));
		weaponsEnchant.setItemMeta(wEM);
		inv.setItem(12, weaponsEnchant);

		String loreAE = "§4§oProtection", loreAE2 = "§4§oFire Protection", loreAE3 = "§4§oProjectiles Protection",
				loreAE4 = "§4§oRespiration", loreAE5 = "§4§oThorns", loreAE6 = "§4§oDepth Strider",
				loreAE7 = "§4§oFrost Walker";
		ItemStack armorsEnchant = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
		armorsEnchant.addEnchantment(Enchantment.VANISHING_CURSE, 1);
		ItemMeta aEM = armorsEnchant.getItemMeta();
		aEM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		aEM.setDisplayName("§6§lArmors Enchantments");
		aEM.setLore(Arrays.asList(loreAE, loreAE2, loreAE3, loreAE4, loreAE5, loreAE6, loreAE7));
		armorsEnchant.setItemMeta(aEM);
		inv.setItem(14, armorsEnchant);
	}

	public void createMenuAL() {

		String lorePP = "§4§oRegeneration", lorePP2 = "§4§oSwiftness", lorePP3 = "§4§oHealing",
				lorePP4 = "§4§oStrength", lorePP5 = "§4§oLeaping II", lorePP6 = "§4§oWater Breathing",
				lorePP7 = "§4§oInvisibility";
		ItemStack personnalPotions = new ItemStack(Material.POTION, 1);
		ItemMeta pPM = personnalPotions.getItemMeta();
		pPM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		pPM.setDisplayName("§6§lPersonnal Potions");
		pPM.setLore(Arrays.asList(lorePP, lorePP2, lorePP3, lorePP4, lorePP5, lorePP6, lorePP7));
		personnalPotions.setItemMeta(pPM);
		inv.setItem(11, personnalPotions);

		String loreSP = "§4§oRegeneration", loreSP2 = "§4§oSwiftness", loreSP3 = "§4§oHealing", loreSP4 = "§4§oPoison",
				loreSP5 = "§4§oHarming", loreSP6 = "§4§oSlowness", loreSP7 = "§4§oWeakness";
		ItemStack splashPotions = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta sPM = splashPotions.getItemMeta();
		sPM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		sPM.setDisplayName("§6§lSplash Potions");
		sPM.setLore(Arrays.asList(loreSP, loreSP2, loreSP3, loreSP4, loreSP5, loreSP6, loreSP7));
		splashPotions.setItemMeta(sPM);
		inv.setItem(13, splashPotions);
		
		String loreLP = "§4§oRegeneration", loreLP2 = "§4§oPoison", loreLP3 = "§4§oHarming", loreLP4 = "§4§oSlowness",
				loreLP5 = "§4§oWeakness";
		ItemStack lingeringPotions = new ItemStack(Material.LINGERING_POTION, 1);
		ItemMeta lPM = lingeringPotions.getItemMeta();
		lPM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		lPM.setDisplayName("§6§lLingering Potions");
		lPM.setLore(Arrays.asList(loreLP, loreLP2, loreLP3, loreLP4, loreLP5));
		lingeringPotions.setItemMeta(lPM);
		inv.setItem(15, lingeringPotions);
	}

	public void createMenuJU() {

		String loreB = "§4§oPlanks", loreB2 = "§4§oIron blocks", loreB3 = "§4§oStonebricks", loreB4 = "§4§oEnd Stones",
				loreB5 = "§4§oand TnT";
		ItemStack blocks = new ItemStack(Material.COBBLESTONE_WALL, 1);
		ItemMeta bM = blocks.getItemMeta();
		bM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bM.setDisplayName("§6§lWall blocks and TnT");
		bM.setLore(Arrays.asList(loreB, loreB2, loreB3, loreB4, loreB5));
		blocks.setItemMeta(bM);
		inv.setItem(12, blocks);

		String loreT = "§4§oPickaxes", loreT2 = "§4§oBuckets", loreT3 = "§4§oLadders", loreT4 = "§4§oEnder Pearls",
				loreT5 = "§4§oLighters", loreT6 = "§4§oand Beacons";
		ItemStack tools = new ItemStack(Material.GOLDEN_PICKAXE, 1);
		ItemMeta tM = tools.getItemMeta();
		tM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tM.setDisplayName("§6§lTools and Beacons");
		tM.setLore(Arrays.asList(loreT, loreT2, loreT3, loreT4, loreT5, loreT6));
		tools.setItemMeta(tM);
		inv.setItem(14, tools);
	}
	
	public void createMenuTP() {
	
		ItemStack tp = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta tpM = tp.getItemMeta();
		tpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tpM.setDisplayName("§6§lTeleport to Arena !");
		tpM.setLore(Arrays.asList("§7§oReady to fight ?!"));
		tp.setItemMeta(tpM);
		inv.setItem(13, tp);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}

}
