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
import fr.phoenix.sineplugin.shopMenu.JunkdealerBlocksMenu;

public class JunkdealerBlocksBuyEvent implements Listener {

	private int subW = 0, subS = 0, subI = 0, subE = 0, subT = 0;

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		Inventory pInv = p.getInventory();

		if (inv != null && e.getView().getTitle().equalsIgnoreCase(
				Files.shopNamesConfig.getString("menu.shop.junk.blocks").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			// PLANKS
			if (it.getType().equals(Material.OAK_WOOD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.name").replace("&", "§"))) {

				if (subW == 0) {
					clearSubBlocks(inv);
					inv.setItem(9, JunkdealerBlocksMenu.getBlock(Material.OAK_WOOD, 4, "planks", "", 16));
					inv.setItem(10, JunkdealerBlocksMenu.getBlock(Material.OAK_WOOD, 16, "planks", "", 16));
					inv.setItem(11, JunkdealerBlocksMenu.getBlock(Material.OAK_WOOD, 32, "planks", "", 16));
					inv.setItem(12, JunkdealerBlocksMenu.getBlock(Material.OAK_WOOD, 64, "planks", "", 16));
					p.updateInventory();
					subW = 1;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				} else {
					clearSubBlocks(inv);
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				}
			}

			if (it.getType().equals(Material.OAK_WOOD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopBegin").replace("&", "§") + 4
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.OAK_WOOD, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "planks", p, 4, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.OAK_WOOD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopBegin").replace("&", "§")
							+ 16 + Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.OAK_WOOD, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "planks", p, 16, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.OAK_WOOD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopBegin").replace("&", "§")
							+ 32 + Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.OAK_WOOD, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "planks", p, 32, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.OAK_WOOD) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopBegin").replace("&", "§")
							+ 64 + Files.shopJUBlocksItemsConfig.getString("junk.blocks.planks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.OAK_WOOD, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "planks", p, 64, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			// STONEBRICKS
			if (it.getType().equals(Material.STONE_BRICKS) && it.getItemMeta().getDisplayName().equals(
					Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.name").replace("&", "§"))) {

				if (subS == 0) {
					clearSubBlocks(inv);
					inv.setItem(10, JunkdealerBlocksMenu.getBlock(Material.STONE_BRICKS, 4, "stonebricks", "", 14));
					inv.setItem(11, JunkdealerBlocksMenu.getBlock(Material.STONE_BRICKS, 16, "stonebricks", "", 14));
					inv.setItem(12, JunkdealerBlocksMenu.getBlock(Material.STONE_BRICKS, 32, "stonebricks", "", 14));
					inv.setItem(13, JunkdealerBlocksMenu.getBlock(Material.STONE_BRICKS, 64, "stonebricks", "", 14));
					p.updateInventory();
					subS = 1;
					subW = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				} else {
					clearSubBlocks(inv);
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				}
			}

			if (it.getType().equals(Material.STONE_BRICKS) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopBegin").replace("&",
							"§") + 4 + Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopEnd")
									.replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.STONE_BRICKS, PotionType.LUCK, Files.shopJUBlocksItemsConfig,
						"junk", "blocks", "", "stonebricks", p, 4, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.STONE_BRICKS) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopBegin").replace("&",
							"§") + 16
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.STONE_BRICKS, PotionType.LUCK, Files.shopJUBlocksItemsConfig,
						"junk", "blocks", "", "stonebricks", p, 16, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.STONE_BRICKS) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopBegin").replace("&",
							"§") + 32
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.STONE_BRICKS, PotionType.LUCK, Files.shopJUBlocksItemsConfig,
						"junk", "blocks", "", "stonebricks", p, 32, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.STONE_BRICKS) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopBegin").replace("&",
							"§") + 64
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.stonebricks.nopEnd").replace("&",
									"§"))) {

				new Vars().checkTeamsGolds(Material.STONE_BRICKS, PotionType.LUCK, Files.shopJUBlocksItemsConfig,
						"junk", "blocks", "", "stonebricks", p, 64, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			// IRON BLOCKS
			if (it.getType().equals(Material.IRON_BLOCK) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.name").replace("&", "§"))) {

				if (subI == 0) {
					clearSubBlocks(inv);
					inv.setItem(12, JunkdealerBlocksMenu.getBlock(Material.IRON_BLOCK, 4, "iron", "", 13));
					inv.setItem(13, JunkdealerBlocksMenu.getBlock(Material.IRON_BLOCK, 16, "iron", "", 13));
					inv.setItem(14, JunkdealerBlocksMenu.getBlock(Material.IRON_BLOCK, 32, "iron", "", 13));
					inv.setItem(15, JunkdealerBlocksMenu.getBlock(Material.IRON_BLOCK, 64, "iron", "", 13));
					p.updateInventory();
					subI = 1;
					subW = 0;
					subS = 0;
					subE = 0;
					subT = 0;
				} else {
					clearSubBlocks(inv);
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				}
			}

			if (it.getType().equals(Material.IRON_BLOCK) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopBegin").replace("&", "§") + 4
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_BLOCK, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "iron", p, 4, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.IRON_BLOCK) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopBegin").replace("&", "§") + 16
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_BLOCK, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "iron", p, 16, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.IRON_BLOCK) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopBegin").replace("&", "§") + 32
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_BLOCK, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "iron", p, 32, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.IRON_BLOCK) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopBegin").replace("&", "§") + 64
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.iron.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.IRON_BLOCK, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "iron", p, 64, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			// ENDER STONES
			if (it.getType().equals(Material.END_STONE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.name").replace("&", "§"))) {

				if (subE == 0) {
					clearSubBlocks(inv);
					inv.setItem(13, JunkdealerBlocksMenu.getBlock(Material.END_STONE, 4, "ender", "", 12));
					inv.setItem(14, JunkdealerBlocksMenu.getBlock(Material.END_STONE, 16, "ender", "", 12));
					inv.setItem(15, JunkdealerBlocksMenu.getBlock(Material.END_STONE, 32, "ender", "", 12));
					inv.setItem(16, JunkdealerBlocksMenu.getBlock(Material.END_STONE, 64, "ender", "", 12));
					p.updateInventory();
					subE = 1;
					subW = 0;
					subS = 0;
					subI = 0;
					subT = 0;
				} else {
					clearSubBlocks(inv);
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				}
			}

			if (it.getType().equals(Material.END_STONE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopBegin").replace("&", "§") + 4
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.END_STONE, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "ender", p, 4, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.END_STONE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopBegin").replace("&", "§") + 16
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.END_STONE, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "ender", p, 16, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.END_STONE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopBegin").replace("&", "§") + 32
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.END_STONE, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "ender", p, 32, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.END_STONE) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopBegin").replace("&", "§") + 64
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.ender.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.END_STONE, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "ender", p, 64, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			// TNT
			if (it.getType().equals(Material.TNT) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.name").replace("&", "§"))) {

				if (subT == 0) {
					clearSubBlocks(inv);
					inv.setItem(14, JunkdealerBlocksMenu.getBlock(Material.TNT, 1, "tnt", "", 9));
					inv.setItem(15, JunkdealerBlocksMenu.getBlock(Material.TNT, 2, "tnt", "", 9));
					inv.setItem(16, JunkdealerBlocksMenu.getBlock(Material.TNT, 3, "tnt", "", 9));
					inv.setItem(17, JunkdealerBlocksMenu.getBlock(Material.TNT, 5, "tnt", "", 9));
					p.updateInventory();
					subT = 1;
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
				} else {
					clearSubBlocks(inv);
					subW = 0;
					subS = 0;
					subI = 0;
					subE = 0;
					subT = 0;
				}
			}
			if (it.getType().equals(Material.TNT) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopBegin").replace("&", "§") + 1
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopEnd").replace("&", "§"))) {
				
				new Vars().checkTeamsGolds(Material.TNT, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "tnt", p, 1, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.TNT) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopBegin").replace("&", "§") + 2
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TNT, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "tnt", p, 2, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.TNT) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopBegin").replace("&", "§") + 3
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TNT, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "tnt", p, 3, pInv, Enchantment.LUCK, 1, false, false, "");
			}

			if (it.getType().equals(Material.TNT) && it.getItemMeta().getDisplayName()
					.equals(Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopBegin").replace("&", "§") + 5
							+ Files.shopJUBlocksItemsConfig.getString("junk.blocks.tnt.nopEnd").replace("&", "§"))) {

				new Vars().checkTeamsGolds(Material.TNT, PotionType.LUCK, Files.shopJUBlocksItemsConfig, "junk",
						"blocks", "", "tnt", p, 5, pInv, Enchantment.LUCK, 1, false, false, "");
			}
		}
	}

	public void clearSubBlocks(Inventory inv) {

		for (int i = 9; i < 18; i++) {
			inv.clear(i);
		}
	}

	public static ItemStack wood4 = new ItemStack(Material.OAK_WOOD, 4);
	public static ItemStack wood16 = new ItemStack(Material.OAK_WOOD, 16);
	public static ItemStack wood32 = new ItemStack(Material.OAK_WOOD, 32);
	public static ItemStack wood64 = new ItemStack(Material.OAK_WOOD, 64);
	public static ItemStack stone4 = new ItemStack(Material.STONE_BRICKS, 4);
	public static ItemStack stone16 = new ItemStack(Material.STONE_BRICKS, 16);
	public static ItemStack stone32 = new ItemStack(Material.STONE_BRICKS, 32);
	public static ItemStack stone64 = new ItemStack(Material.STONE_BRICKS, 64);
	public static ItemStack iron4 = new ItemStack(Material.IRON_BLOCK, 4);
	public static ItemStack iron16 = new ItemStack(Material.IRON_BLOCK, 16);
	public static ItemStack iron32 = new ItemStack(Material.IRON_BLOCK, 32);
	public static ItemStack iron64 = new ItemStack(Material.IRON_BLOCK, 64);
	public static ItemStack ender4 = new ItemStack(Material.END_STONE, 4);
	public static ItemStack ender16 = new ItemStack(Material.END_STONE, 16);
	public static ItemStack ender32 = new ItemStack(Material.END_STONE, 32);
	public static ItemStack ender64 = new ItemStack(Material.END_STONE, 64);
	public static ItemStack tnt1 = new ItemStack(Material.TNT, 1);
	public static ItemStack tnt2 = new ItemStack(Material.TNT, 2);
	public static ItemStack tnt3 = new ItemStack(Material.TNT, 3);
	public static ItemStack tnt5 = new ItemStack(Material.TNT, 5);

}
