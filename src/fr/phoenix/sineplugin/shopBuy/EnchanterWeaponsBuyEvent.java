package fr.phoenix.sineplugin.shopBuy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionType;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import fr.phoenix.sineplugin.shopMenu.EnchanterWeaponsMenu;

public class EnchanterWeaponsBuyEvent implements Listener {

	List<Enchantment> weaponEnchantments = new ArrayList<>(Arrays.asList(Enchantment.DAMAGE_ALL, Enchantment.KNOCKBACK,
			Enchantment.FIRE_ASPECT, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_FIRE,
			Enchantment.ARROW_INFINITE));

	List<Material> allMeleeWeaponType = new ArrayList<>(
			Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD,
					Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE));

	List<Material> swordWeaponType = new ArrayList<>(
			Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD));

	List<Material> bowWeaponType = new ArrayList<>(Arrays.asList(Material.BOW));

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();
		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.enchanter.weapons").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// Si clic sur un enchant
			if (it.getType().equals(Material.ENCHANTED_BOOK)) {
				// Si le joueur ne possède pas déja d'enchant
				if (!pInv.contains(Material.ENCHANTED_BOOK)) {

					for (Enchantment en : weaponEnchantments) {
						int enLvl = it.getEnchantmentLevel(en);
						switch (enLvl) {
						case 1:
							if (en.equals(Enchantment.DAMAGE_ALL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "sharpness.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.KNOCKBACK))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "knockback.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.FIRE_ASPECT))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "fireaspect.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.ARROW_DAMAGE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "power.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.ARROW_KNOCKBACK))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "punch.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.ARROW_FIRE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "flame.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.ARROW_INFINITE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "infinity.", p, 0, pInv,
										en, 1, false, false, "");
							break;
						case 2:
							if (en.equals(Enchantment.DAMAGE_ALL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "sharpness.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.KNOCKBACK))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "knockback.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.FIRE_ASPECT))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "fireaspect.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.ARROW_DAMAGE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "power.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.ARROW_KNOCKBACK))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "punch.", p, 0, pInv,
										en, 2, false, false, "");
							break;
						case 3:
							if (en.equals(Enchantment.DAMAGE_ALL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "sharpness.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.ARROW_DAMAGE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "power.", p, 0, pInv,
										en, 3, false, false, "");
							break;
						case 4:
							if (en.equals(Enchantment.DAMAGE_ALL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "sharpness.", p, 0, pInv,
										en, 4, false, false, "");
							if (en.equals(Enchantment.ARROW_DAMAGE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "power.", p, 0, pInv,
										en, 4, false, false, "");
							break;
						case 5:
							if (en.equals(Enchantment.DAMAGE_ALL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "sharpness.", p, 0, pInv,
										en, 5, false, false, "");
							if (en.equals(Enchantment.ARROW_DAMAGE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENWeaponsItemsConfig, "enchanter", "weapons", "", "power.", p, 0, pInv,
										en, 5, false, false, "");
							break;
						}
					}
					// Si le joueur possède déja un enchant
				} else {
					p.sendMessage("§0[§4SINE§0] §7§oYou already have an enchant in progress...");
				}
				// Si clic sur cancel
			} else if (it.getType().equals(Material.BARRIER)) {
				// Si le joueur ne possède pas d'enchant
				if (!pInv.contains(Material.ENCHANTED_BOOK)) {
					p.sendMessage("§0[§4SINE§0] §7§oNo enchant selected...");
					// Si le joueur possède un enchant
				} else {
					scanAndCancelEnchant(p);
				}

			} else if (it.getType().equals(Material.GOLDEN_CHESTPLATE) || it.getType().equals(Material.GOLDEN_SWORD)) {

			} else {
				// Si le joueur ne possède pas d'enchant
				if (!pInv.contains(Material.ENCHANTED_BOOK)) {
					p.sendMessage("§0[§4SINE§0] §7§oSelect an enchantment before...");
					// Si le joueur possède déja un enchant
				} else {
					// Quel est l'enchant déja choisi
					for (i = 0; i < p.getInventory().getSize(); i++) {
						if (p.getInventory().getItem(i).getType().equals(Material.ENCHANTED_BOOK)) {
							break;
						}
					}
					ItemStack enItem = p.getInventory().getItem(i);
					ItemMeta enMeta = enItem.getItemMeta();
					String enName = enMeta.getDisplayName();
					int enLevel;

					if (enItem.containsEnchantment(Enchantment.DAMAGE_ALL)) {
						int enPLevel = enMeta.getEnchantLevel(Enchantment.DAMAGE_ALL);
						if (allMeleeWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.DAMAGE_ALL, enPLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for sword and axe weapons...");
						}
					} else if (enItem.containsEnchantment(Enchantment.KNOCKBACK)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.KNOCKBACK);
						if (swordWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.KNOCKBACK, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for sword weapons...");
						}
					} else if (enItem.containsEnchantment(Enchantment.FIRE_ASPECT)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.FIRE_ASPECT);
						if (swordWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.FIRE_ASPECT, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for sword weapons...");
						}
					} else if (enItem.containsEnchantment(Enchantment.ARROW_DAMAGE)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.ARROW_DAMAGE);
						if (bowWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.ARROW_DAMAGE, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for bow...");
						}
					} else if (enItem.containsEnchantment(Enchantment.ARROW_KNOCKBACK)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.ARROW_KNOCKBACK);
						if (bowWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.ARROW_KNOCKBACK, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for bow...");
						}
					} else if (enItem.containsEnchantment(Enchantment.ARROW_FIRE)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.ARROW_FIRE);
						if (bowWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.ARROW_FIRE, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for bow...");
						}
					} else if (enItem.containsEnchantment(Enchantment.ARROW_INFINITE)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.ARROW_INFINITE);
						if (bowWeaponType.contains(it.getType())) {
							it.addEnchantment(Enchantment.ARROW_INFINITE, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect a weapon item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for bow...");
						}
					}
				}
			}

		}
	}

	Enchantment enEnch;
	String enType;
	int i;

	public void scanAndCancelEnchant(Player p) {

		for (i = 0; i < p.getInventory().getSize(); i++) {
			if (p.getInventory().getItem(i).getType().equals(Material.ENCHANTED_BOOK)) {
				break;
			}
		}
		ItemStack enItem = p.getInventory().getItem(i);
		ItemMeta enMeta = enItem.getItemMeta();
		String enName = enMeta.getDisplayName();

		if (enItem.containsEnchantment(Enchantment.DAMAGE_ALL)) {
			enEnch = Enchantment.DAMAGE_ALL;
			enType = "sharpness.";
		} else if (enItem.containsEnchantment(Enchantment.KNOCKBACK)) {
			enEnch = Enchantment.KNOCKBACK;
			enType = "knockback.";
		} else if (enItem.containsEnchantment(Enchantment.FIRE_ASPECT)) {
			enEnch = Enchantment.FIRE_ASPECT;
			enType = "fireaspect.";
		} else if (enItem.containsEnchantment(Enchantment.ARROW_DAMAGE)) {
			enEnch = Enchantment.ARROW_DAMAGE;
			enType = "power.";
		} else if (enItem.containsEnchantment(Enchantment.ARROW_KNOCKBACK)) {
			enEnch = Enchantment.ARROW_KNOCKBACK;
			enType = "punch.";
		} else if (enItem.containsEnchantment(Enchantment.ARROW_FIRE)) {
			enEnch = Enchantment.ARROW_FIRE;
			enType = "flame.";
		} else if (enItem.containsEnchantment(Enchantment.ARROW_INFINITE)) {
			enEnch = Enchantment.ARROW_INFINITE;
			enType = "infinity.";
		}
		int enLevel = enMeta.getEnchantLevel(enEnch);
		p.sendMessage("§0[§4SINE§0] §7§oYou cancelled " + enName);
		if (Vars.pNameTeamOne.containsKey(p.getName())) {
			p.sendMessage("§b"
					+ Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + enType + enLevel + ".p")
					+ "§7§o golds have been credited to "
					+ Files.setsNameConfig.getString("teams.display.one").replace("&", "§") + "§7§o golds !");
			p.getInventory().remove(Material.ENCHANTED_BOOK);
			Vars.oneGoldsTotal  += Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + enType + enLevel + ".p");
			
			CasterMenu.teamOneBuy.remove(EnchanterWeaponsMenu.weaponEnchant, CasterMenu.teamOneBuy.get(EnchanterWeaponsMenu.weaponEnchant));
			CasterMenu.teamOneBuyed.remove(EnchanterWeaponsMenu.weaponEnchant, CasterMenu.teamOneBuyed.get(EnchanterWeaponsMenu.weaponEnchant));
		}
		if (Vars.pNameTeamTwo.containsKey(p.getName())) {
			p.sendMessage("§b§l"
					+ Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + enType + enLevel + ".p")
					+ "§7§o golds have been credited to "
					+ Files.setsNameConfig.getString("teams.display.two").replace("&", "§") + "§7§o golds !");
			p.getInventory().remove(Material.ENCHANTED_BOOK);
			Vars.twoGoldsTotal  += Files.shopENWeaponsItemsConfig.getInt("enchanter.weapons." + enType + enLevel + ".p");
		
			CasterMenu.teamTwoBuy.remove(EnchanterWeaponsMenu.weaponEnchant, CasterMenu.teamTwoBuy.get(EnchanterWeaponsMenu.weaponEnchant));
			CasterMenu.teamTwoBuyed.remove(EnchanterWeaponsMenu.weaponEnchant, CasterMenu.teamTwoBuyed.get(EnchanterWeaponsMenu.weaponEnchant));
		}
	}
}
