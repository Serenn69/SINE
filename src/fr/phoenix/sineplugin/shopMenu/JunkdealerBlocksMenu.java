package fr.phoenix.sineplugin.shopMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;

public class JunkdealerBlocksMenu {

	private Inventory inv;
	public static ItemStack block, blockNo, blockHash;

	public JunkdealerBlocksMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.junk.blocks").replace("&", "§") + " §lShop");
	}

	// BLOCKS GETTER PRICE
	public static ItemStack getBlock(Material m, int nbr, String type, String team, int l) {
		
		block = new ItemStack(m, nbr);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".lore.end").replace("&",
				"§");
		String addPrice = "§a" + Files.shopJUBlocksItemsConfig.getInt("junk.blocks." + type + ".p" + nbr) + " §aGolds";
		ItemMeta blockM = block.getItemMeta();
		blockM.setDisplayName(
				Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".nopBegin").replace("&", "§")+nbr+Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".nopEnd").replace("&", "§"));
		lore.add(Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".lore."+l).replace("&", "§"));
		lore.add(addLoreEnd);
		lore.add("");
		lore.add(addPrice);
		blockM.setLore(lore);
		block.setItemMeta(blockM);
		return block;
	}
	
	// BLOCKS GETTER NO PRICE
	public static ItemStack getBlockNo(Material m, int nbr, String type, String team) {

		blockNo = new ItemStack(m, nbr);
		blockHash = new ItemStack(m,1);
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".lore.end").replace("&",
				"§");
		String addLoreA = Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".lore.a").replace("&", "§");
		ItemMeta blockNoM = blockNo.getItemMeta();
		ItemMeta blockHashM = blockHash.getItemMeta();
		blockNoM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		blockNoM.setDisplayName(
				Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".name").replace("&", "§"));
		blockHashM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		blockHashM.setDisplayName(
				Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopJUBlocksItemsConfig.getInt("junk.blocks." + type + ".lore.nbr");
		for (int i = 1; i <= l; i++) {
			loreList = Files.shopJUBlocksItemsConfig.getString("junk.blocks." + type + ".lore." + i).replace("&", "§");
			lore.add(loreList);
		}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		blockNoM.setLore(lore);
		blockHashM.setLore(lore);
		blockNo.setItemMeta(blockNoM);
		blockHash.setItemMeta(blockHashM);
		if (team.equals("one")) {
			
			if (CasterMenu.teamOneBuy.containsKey(blockHash)) {
				CasterMenu.teamOneBuy.put(blockHash, CasterMenu.teamOneBuy.get(blockHash) + nbr);
			} else {
				CasterMenu.teamOneBuy.put(blockHash, nbr);
			}
		}
		if (team.equals("two")) {

			if (CasterMenu.teamTwoBuy.containsKey(blockHash)) {
				CasterMenu.teamTwoBuy.put(blockHash, CasterMenu.teamTwoBuy.get(blockHash) + nbr);
			} else {
				CasterMenu.teamTwoBuy.put(blockHash, nbr);
			}
		}
		if (Vars.canPlaceOn.contains(m) || m.equals(Material.TNT)) {
			net.minecraft.server.v1_14_R1.ItemStack blockNoNMS = CraftItemStack.asNMSCopy(blockNo);
			NBTTagList canPlaceOnList = new NBTTagList();
			canPlaceOnList.add(new NBTTagString("minecraft:wool"));
			canPlaceOnList.add(new NBTTagString("minecraft:stone"));
			canPlaceOnList.add(new NBTTagString("minecraft:planks"));
			canPlaceOnList.add(new NBTTagString("minecraft:stonebrick"));
			canPlaceOnList.add(new NBTTagString("minecraft:iron_block"));
			canPlaceOnList.add(new NBTTagString("minecraft:end_stone"));
			NBTTagCompound cp = (blockNoNMS.hasTag()) ? blockNoNMS.getTag() : new NBTTagCompound();
			cp.set("CanPlaceOn", canPlaceOnList);
			blockNoNMS.setTag(cp);
			blockNo = CraftItemStack.asBukkitCopy(blockNoNMS);
			}
		return blockNo;
	}

	public void createBlocksMenu() {
		
		// PLANKS
		inv.setItem(0, getBlockNo(Material.OAK_WOOD, 1, "planks", ""));

		// STONEBRICKS
		inv.setItem(2, getBlockNo(Material.STONE_BRICKS, 1, "stonebricks", ""));

		// IRON BLOCKS
		inv.setItem(4, getBlockNo(Material.IRON_BLOCK, 1, "iron", ""));

		// END STONES
		inv.setItem(6, getBlockNo(Material.END_STONE, 1, "ender", ""));

		// TNT
		inv.setItem(8, getBlockNo(Material.TNT, 1, "tnt", ""));

		// TOOLS TAB
		ItemStack toolsTab = new ItemStack(Material.GOLDEN_PICKAXE, 1);
		ItemMeta toolsTabM = toolsTab.getItemMeta();
		toolsTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		toolsTabM.setDisplayName("§6§lTools and Beacons");
		toolsTabM.setLore(Arrays.asList("§7§oSwitch to tools and beacons shop menu"));
		toolsTab.setItemMeta(toolsTabM);
		inv.setItem(26, toolsTab);
	}

	public void openMenu(Player p) {
		p.openInventory(inv);
	}
}
