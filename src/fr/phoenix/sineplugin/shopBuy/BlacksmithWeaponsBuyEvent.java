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

public class BlacksmithWeaponsBuyEvent implements Listener {

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.blacksmith.weapons").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// SWORDS
			if (it.getType().equals(Material.WOODEN_SWORD)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.swords.wood.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.WOODEN_SWORD, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "swords.", "wood", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.STONE_SWORD)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.swords.stone.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.STONE_SWORD, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "swords.", "stone", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.IRON_SWORD)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.swords.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_SWORD, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "swords.", "iron", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_SWORD)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.swords.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_SWORD, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "swords.", "diamond", p, 1,
						pInv, Enchantment.LUCK, 1, false, false, "");

				// AXES
			} else if (it.getType().equals(Material.WOODEN_AXE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.axes.wood.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.WOODEN_AXE, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "axes.", "wood", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.STONE_AXE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.axes.stone.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.STONE_AXE, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "axes.", "stone", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.IRON_AXE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.axes.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_AXE, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "axes.", "iron", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.DIAMOND_AXE)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.axes.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_AXE, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "axes.", "diamond", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");

				// BOW
			} else if (it.getType().equals(Material.BOW) && it.getItemMeta().getDisplayName().equals(
					Files.shopBSWeaponsItemsConfig.getString("blacksmith.weapons.bow.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.BOW, PotionType.LUCK, Files.shopBSWeaponsItemsConfig,
						"blacksmith", "weapons", "bow", "", p, 1, pInv, Enchantment.LUCK, 1, false, false, "");

				// ARROWS
			} else if (it.getType().equals(Material.ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.standard.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.ARROW, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "standard", p, 20,
						pInv, Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.SPECTRAL_ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.spectral.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.SPECTRAL_ARROW, PotionType.LUCK,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "spectral", p, 5,
						pInv, Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.TIPPED_ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.poison.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TIPPED_ARROW, PotionType.POISON,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "poison", p, 5, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.TIPPED_ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.weakness.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TIPPED_ARROW, PotionType.WEAKNESS,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "weakness", p, 5,
						pInv, Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.TIPPED_ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.slowness.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TIPPED_ARROW, PotionType.SLOWNESS,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "slowness", p, 5,
						pInv, Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.TIPPED_ARROW)
					&& it.getItemMeta().getDisplayName().equals(Files.shopBSWeaponsItemsConfig
							.getString("blacksmith.weapons.arrows.harming.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TIPPED_ARROW, PotionType.INSTANT_DAMAGE,
						Files.shopBSWeaponsItemsConfig, "blacksmith", "weapons", "arrows.", "harming", p, 5,
						pInv, Enchantment.LUCK, 1, false, false, "");
			}
		}
	}
}
