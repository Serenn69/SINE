package fr.phoenix.sineplugin.shopBuy;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;

public class BlacksmithArmorsBuyEvent implements Listener {

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.blacksmith.armors").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// HELMETS
			if (it.getType().equals(Material.LEATHER_HELMET)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.helmets.leather.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.LEATHER_HELMET, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "helmets.", "leather", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.IRON_HELMET)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.helmets.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_HELMET, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "helmets.", "iron", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_HELMET)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.helmets.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_HELMET, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "helmets.", "diamond", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

				// CHESTPLATES
			} else if (it.getType().equals(Material.LEATHER_CHESTPLATE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.chestplates.leather.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.LEATHER_CHESTPLATE, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "chestplates.", "leather", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");


			} else if (it.getType().equals(Material.IRON_CHESTPLATE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.chestplates.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_CHESTPLATE, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "chestplates.", "iron", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_CHESTPLATE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.chestplates.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_CHESTPLATE, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "chestplates.", "diamond", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

				// LEGGINGS
			} else if (it.getType().equals(Material.LEATHER_LEGGINGS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.leggings.leather.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.LEATHER_LEGGINGS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "leggings.", "leather", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.IRON_LEGGINGS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.leggings.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_LEGGINGS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "leggings.", "iron", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_LEGGINGS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.leggings.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_LEGGINGS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "leggings.", "diamond", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

				// BOOTS
			} else if (it.getType().equals(Material.LEATHER_BOOTS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.boots.leather.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.LEATHER_BOOTS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "boots.", "leather", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.IRON_BOOTS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.boots.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_BOOTS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "boots.", "iron", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_BOOTS)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.boots.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_BOOTS, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "boots.", "diamond", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");

				// SHIELD
			} else if (it.getType().equals(Material.SHIELD)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSArmorsItemsConfig
							.getString("blacksmith.armors.shield.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.SHIELD, PotionType.LUCK,
						Files.shopBSArmorsItemsConfig, "blacksmith", "armors", "shield", "", p, 0, pInv,
						Enchantment.LUCK, 1, false, false, "");
			}
		}
	}
}
