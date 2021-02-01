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

public class JunkdealerToolsBuyEvent implements Listener {

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle()
				.equalsIgnoreCase(Files.shopNamesConfig.getString("menu.shop.junk.tools").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// PICKAXES
			if (it.getType().equals(Material.WOODEN_PICKAXE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.pickaxes.wood.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.WOODEN_PICKAXE, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "pickaxes.", "wood", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.STONE_PICKAXE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.pickaxes.stone.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.STONE_PICKAXE, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "pickaxes.", "stone", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.IRON_PICKAXE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.pickaxes.iron.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_PICKAXE, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "pickaxes.", "iron", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.DIAMOND_PICKAXE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.pickaxes.diamond.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.DIAMOND_PICKAXE, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "pickaxes.", "diamond", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
			}

			// BUCKETS
			else if (it.getType().equals(Material.WATER_BUCKET) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.buckets.water.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.WATER_BUCKET, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "buckets.", "water", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.MILK_BUCKET) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.buckets.milk.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.MILK_BUCKET, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "buckets.", "milk", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
			}

			// BEACONS
			if (it.getType().equals(Material.BEACON)) {

				if (it.getItemMeta().getDisplayName()
						.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.strength.name").replace("&", "§"))) {

					new Vars().checkTeamsGolds(Material.BEACON, PotionType.LUCK,
							Files.shopJUToolsItemsConfig, "junk", "tools", "beacons.", "strength", p, 1, pInv,
							Enchantment.LUCK, 1, false, false, "");
				}
				if (it.getItemMeta().getDisplayName().equals(
						Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.swiftness.name").replace("&", "§"))) {

					new Vars().checkTeamsGolds(Material.BEACON, PotionType.LUCK,
							Files.shopJUToolsItemsConfig, "junk", "tools", "beacons.", "swiftness", p, 1, pInv,
							Enchantment.LUCK, 1, false, false, "");
				}
				if (it.getItemMeta().getDisplayName()
						.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.regen.name").replace("&", "§"))) {

					new Vars().checkTeamsGolds(Material.BEACON, PotionType.LUCK,
							Files.shopJUToolsItemsConfig, "junk", "tools", "beacons.", "regen", p, 1, pInv,
							Enchantment.LUCK, 1, false, false, "");
				}
				if (it.getItemMeta().getDisplayName().equals(
						Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.protection.name").replace("&", "§"))) {

					new Vars().checkTeamsGolds(Material.BEACON, PotionType.LUCK,
							Files.shopJUToolsItemsConfig, "junk", "tools", "beacons.", "protection", p, 1, pInv,
							Enchantment.LUCK, 1, false, false, "");
				}
			}

			// FISHING ROD, LIGHTER, LADDERS, ENDER PEARL
			else if (it.getType().equals(Material.FISHING_ROD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.fishrod.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.FISHING_ROD, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "fishrod", "", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.FLINT_AND_STEEL) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.lighter.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.FLINT_AND_STEEL, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "lighter", "", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
				
			} else if (it.getType().equals(Material.LADDER) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.ladders.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.LADDER, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "ladders", "", p, 2, pInv,
						Enchantment.LUCK, 1, false, false, "");

			} else if (it.getType().equals(Material.ENDER_PEARL) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUToolsItemsConfig.getString("junk.tools.enderpearl.name").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.ENDER_PEARL, PotionType.LUCK,
						Files.shopJUToolsItemsConfig, "junk", "tools", "enderpearl", "", p, 1, pInv,
						Enchantment.LUCK, 1, false, false, "");
			}
		}
	}
}
