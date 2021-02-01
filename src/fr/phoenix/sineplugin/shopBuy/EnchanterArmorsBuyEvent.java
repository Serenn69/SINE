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
import fr.phoenix.sineplugin.shopMenu.EnchanterArmorsMenu;

public class EnchanterArmorsBuyEvent implements Listener {

	List<Enchantment> armorEnchantments = new ArrayList<>(Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL,
			Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.THORNS, Enchantment.OXYGEN,
			Enchantment.DEPTH_STRIDER, Enchantment.FROST_WALKER));

	List<Material> allArmorType = new ArrayList<>(Arrays.asList(Material.LEATHER_HELMET, Material.IRON_HELMET,
			Material.DIAMOND_HELMET, Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE,
			Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.LEATHER_BOOTS,
			Material.IRON_BOOTS, Material.DIAMOND_BOOTS));

	List<Material> helmetArmorType = new ArrayList<>(
			Arrays.asList(Material.LEATHER_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET));

	List<Material> bootsArmorType = new ArrayList<>(
			Arrays.asList(Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS));

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();
		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.enchanter.armors").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// Si clic sur un enchant
			if (it.getType().equals(Material.ENCHANTED_BOOK)) {
				// Si le joueur ne possède pas déja d'enchant
				if (!pInv.contains(Material.ENCHANTED_BOOK)) {

					for (Enchantment en : armorEnchantments) {
						int enLvl = it.getEnchantmentLevel(en);
						switch (enLvl) {
						case 1:
							if (en.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "protection.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.PROTECTION_FIRE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "fireprot.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.PROTECTION_PROJECTILE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "projectileprot.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.THORNS))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "thorns.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.OXYGEN))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "respiration.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.DEPTH_STRIDER))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "depthstrider.", p, 0, pInv,
										en, 1, false, false, "");
							if (en.equals(Enchantment.FROST_WALKER))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "frostwalker.", p, 0, pInv,
										en, 1, false, false, "");
							break;
						case 2:
							if (en.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "protection.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.PROTECTION_FIRE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "fireprot.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.PROTECTION_PROJECTILE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "projectileprot.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.THORNS))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "thorns.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.OXYGEN))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "respiration.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.DEPTH_STRIDER))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "depthstrider.", p, 0, pInv,
										en, 2, false, false, "");
							if (en.equals(Enchantment.FROST_WALKER))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "frostwalker.", p, 0, pInv,
										en, 2, false, false, "");
							break;
						case 3:
							if (en.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "protection.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.PROTECTION_FIRE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "fireprot.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.PROTECTION_PROJECTILE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "projectileprot.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.THORNS))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "thorns.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.OXYGEN))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "respiration.", p, 0, pInv,
										en, 3, false, false, "");
							if (en.equals(Enchantment.DEPTH_STRIDER))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "depthstrider.", p, 0, pInv,
										en, 3, false, false, "");
							break;
						case 4:
							if (en.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "protection.", p, 0, pInv,
										en, 4, false, false, "");
							if (en.equals(Enchantment.PROTECTION_FIRE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "fireprot.", p, 0, pInv,
										en, 4, false, false, "");
							if (en.equals(Enchantment.PROTECTION_PROJECTILE))
								new Vars().checkTeamsGolds(Material.ENCHANTED_BOOK, PotionType.LUCK,
										Files.shopENArmorsItemsConfig, "enchanter", "armors", "", "projectileprot.", p, 0, pInv,
										en, 4, false, false, "");
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

					if (enItem.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
						int enPLevel = enMeta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
						if (allArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enPLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						}
					} else if (enItem.containsEnchantment(Enchantment.PROTECTION_FIRE)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.PROTECTION_FIRE);
						if (allArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.PROTECTION_FIRE, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						}
					} else if (enItem.containsEnchantment(Enchantment.PROTECTION_PROJECTILE)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.PROTECTION_PROJECTILE);
						if (allArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.PROTECTION_PROJECTILE, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						}
					} else if (enItem.containsEnchantment(Enchantment.THORNS)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.THORNS);
						if (allArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.THORNS, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						}
					} else if (enItem.containsEnchantment(Enchantment.OXYGEN)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.OXYGEN);
						if (helmetArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.OXYGEN, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for helmet armor...");
						}
					} else if (enItem.containsEnchantment(Enchantment.DEPTH_STRIDER)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.DEPTH_STRIDER);
						if (bootsArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.DEPTH_STRIDER, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for boots armor...");
						}
					} else if (enItem.containsEnchantment(Enchantment.FROST_WALKER)) {
						enLevel = enMeta.getEnchantLevel(Enchantment.FROST_WALKER);
						if (bootsArmorType.contains(it.getType())) {
							it.addEnchantment(Enchantment.FROST_WALKER, enLevel);
							p.getInventory().remove(Material.ENCHANTED_BOOK);
						} else if (it == null || it.getType().equals(Material.AIR)) {
							p.sendMessage("§0[§4SINE§0] §7§oSelect an armor item please...");
						} else {
							p.sendMessage("§0[§4SINE§0] §7§o" + enName + " is only for boots armor...");
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

		if (enItem.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
			enEnch = Enchantment.PROTECTION_ENVIRONMENTAL;
			enType = "protection.";
		} else if (enItem.containsEnchantment(Enchantment.PROTECTION_FIRE)) {
			enEnch = Enchantment.PROTECTION_FIRE;
			enType = "fireprot.";
		} else if (enItem.containsEnchantment(Enchantment.PROTECTION_PROJECTILE)) {
			enEnch = Enchantment.PROTECTION_PROJECTILE;
			enType = "projectileprot.";
		} else if (enItem.containsEnchantment(Enchantment.THORNS)) {
			enEnch = Enchantment.THORNS;
			enType = "thorns.";
		} else if (enItem.containsEnchantment(Enchantment.OXYGEN)) {
			enEnch = Enchantment.OXYGEN;
			enType = "respiration.";
		} else if (enItem.containsEnchantment(Enchantment.DEPTH_STRIDER)) {
			enEnch = Enchantment.DEPTH_STRIDER;
			enType = "depthstrider.";
		} else if (enItem.containsEnchantment(Enchantment.FROST_WALKER)) {
			enEnch = Enchantment.FROST_WALKER;
			enType = "frostwalker.";
		}
		int enLevel = enMeta.getEnchantLevel(enEnch);
		p.sendMessage("§0[§4SINE§0] §7§oYou cancelled " + enName);
		if (Vars.pNameTeamOne.containsKey(p.getName())) {
			p.sendMessage("§b"
					+ Files.shopENArmorsItemsConfig.getInt("enchanter.armors." + enType + enLevel + ".p")
					+ "§7§o golds have been credited to "
					+ Files.setsNameConfig.getString("teams.display.one").replace("&", "§") + "§7§o golds !");
			p.getInventory().remove(Material.ENCHANTED_BOOK);
			Vars.oneGoldsTotal += Files.shopENArmorsItemsConfig.getInt("enchanter.armors." + enType + enLevel + ".p");
			
			CasterMenu.teamOneBuy.remove(EnchanterArmorsMenu.armorEnchant, CasterMenu.teamOneBuy.get(EnchanterArmorsMenu.armorEnchant));
			CasterMenu.teamOneBuyed.remove(EnchanterArmorsMenu.armorEnchant, CasterMenu.teamOneBuyed.get(EnchanterArmorsMenu.armorEnchant));
		}
		if (Vars.pNameTeamTwo.containsKey(p.getName())) {
			p.sendMessage("§b§l"
					+ Files.shopENArmorsItemsConfig.getInt("enchanter.armors." + enType + enLevel + ".p")
					+ "§7§o golds have been credited to "
					+ Files.setsNameConfig.getString("teams.display.two").replace("&", "§") + "§7§o golds !");
			p.getInventory().remove(Material.ENCHANTED_BOOK);
			Vars.twoGoldsTotal  += Files.shopENArmorsItemsConfig.getInt("enchanter.armors." + enType + enLevel + ".p");
			
			CasterMenu.teamTwoBuy.remove(EnchanterArmorsMenu.armorEnchant, CasterMenu.teamTwoBuy.get(EnchanterArmorsMenu.armorEnchant));
			CasterMenu.teamTwoBuyed.remove(EnchanterArmorsMenu.armorEnchant, CasterMenu.teamTwoBuyed.get(EnchanterArmorsMenu.armorEnchant));
		}
	}
}
