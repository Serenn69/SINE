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

public class AlchemistPotionsBuyEvent implements Listener {

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.alchemist.solo").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// PERSONNAL POTIONS
			if (it.getType().equals(Material.POTION)) {

				// REGEN
				if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.regen.name").replace("&", "§"))) {

					new Vars().checkTeamsGolds(Material.POTION, PotionType.REGEN,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "regen");

					// SWIFTNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.swiftness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.SPEED,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "swiftness");

					// HEAL
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.heal.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.INSTANT_HEAL,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "heal");

					// STRENGTH
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.strength.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.STRENGTH,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "strength");

					// LEAPING II
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.leaping2.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.JUMP,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, true, "leaping2");

					// WATER BREATHING
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.waterbreath.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.WATER_BREATHING,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "waterbreath");

					// INVISIBILITY
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.solo.invis.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.POTION, PotionType.INVISIBILITY,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "solo.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "invis");
				}
			}
		} else if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.alchemist.splash").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// SPLASH POTIONS
			if (it.getType().equals(Material.SPLASH_POTION)) {

				// REGEN
				if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.regen.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.REGEN,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "regen");

					// SWIFTNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.swiftness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.SPEED,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "swiftness");

					// HEAL
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.heal.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.INSTANT_HEAL,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "heal");

					// POISON
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.poison.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.POISON,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "poison");

					// HARMING
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.harming.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.INSTANT_DAMAGE,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "harming");

					// SLOWNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.slowness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.SLOWNESS,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "slowness");

					// WEAKNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.splash.weakness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.SPLASH_POTION, PotionType.WEAKNESS,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "splash.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "weakness");
				}
			}
		} else if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.alchemist.lingering").replace("&", "§")
						+ " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// LINGERING POTIONS
			if (it.getType().equals(Material.LINGERING_POTION)) {

				// REGEN
				if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.lingering.regen.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.LINGERING_POTION, PotionType.REGEN,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "lingering.", p, 0, pInv,
							Enchantment.LUCK, 1, true, false, "regen");

					// POISON
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.lingering.poison.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.LINGERING_POTION, PotionType.POISON,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "lingering.", p, 0, pInv,
							Enchantment.LUCK, 1, true, false, "poison");

					// HARMING
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.lingering.harming.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.LINGERING_POTION, PotionType.INSTANT_DAMAGE,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "lingering.", p, 0, pInv,
							Enchantment.LUCK, 1, false, false, "harming");

					// SLOWNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.lingering.slowness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.LINGERING_POTION, PotionType.SLOWNESS,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "lingering.", p, 0, pInv,
							Enchantment.LUCK, 1, true, false, "slowness");

					// WEAKNESS
				} else if (it.getItemMeta().getDisplayName().equals(Files.shopALPotionsItemsConfig
						.getString("alchemist.potions.lingering.weakness.name").replace("&", "§"))) {
					
					new Vars().checkTeamsGolds(Material.LINGERING_POTION, PotionType.WEAKNESS,
							Files.shopALPotionsItemsConfig, "alchemist", "potions", "", "lingering.", p, 0, pInv,
							Enchantment.LUCK, 1, true, false, "weakness");
				}
			}
		}
	}
}
