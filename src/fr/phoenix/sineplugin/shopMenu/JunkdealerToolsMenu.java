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

public class JunkdealerToolsMenu {

	private Inventory inv;
	public static ItemStack tool;

	public JunkdealerToolsMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size,
				Files.shopNamesConfig.getString("menu.shop.junk.tools").replace("&", "§") + " §lShop");
	}

	// TOOLS GETTER
	@SuppressWarnings("deprecation")
	public static ItemStack getTool(Material m, int nbr, String piece, String type, String team) {
		if (type.equals("poison")) {
			tool = new ItemStack(m, nbr, (short)13);
		} else if (type.equals("slow")) {
			tool = new ItemStack(m, nbr, (short)10);
		} else if (type.equals("harm")) {
			tool = new ItemStack(m, nbr, (short)8);
		} else if (type.equals("weak")) {
			tool = new ItemStack(m, nbr, (short)3);
		} else {
			tool = new ItemStack(m, nbr);
		}
		List<String> lore = new ArrayList<String>();
		String addLoreEnd = Files.shopJUToolsItemsConfig.getString("junk.tools."+piece+type+".lore.end").replace("&", "§");
		String addLoreA = Files.shopJUToolsItemsConfig.getString("junk.tools."+piece+type+".lore.a").replace("&", "§");
		String addPrice = "§a" + Files.shopJUToolsItemsConfig.getInt("junk.tools."+piece+type+".p") + " §aGolds";
		ItemMeta toolM = tool.getItemMeta();
		toolM.addItemFlags(ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_DESTROYS);
		toolM.setDisplayName(Files.shopJUToolsItemsConfig.getString("junk.tools."+piece+type+".name").replace("&", "§"));
		String loreList = null;
		int l = Files.shopJUToolsItemsConfig.getInt("junk.tools."+piece+type+".lore.nbr");
		for(int i = 1; i <= l; i++) {
			loreList = Files.shopJUToolsItemsConfig.getString("junk.tools."+piece+type+".lore."+i).replace("&", "§");
			lore.add(loreList);
			}
		lore.add(addLoreEnd);
		lore.add(addLoreA);
		lore.add("");
		lore.add(addPrice);
		toolM.setLore(lore);
		tool.setItemMeta(toolM);
		if (team.equals("one")) {

			if (CasterMenu.teamOneBuy.containsKey(tool)) {
				CasterMenu.teamOneBuy.put(tool, CasterMenu.teamOneBuy.get(tool) + nbr);
			} else {
				CasterMenu.teamOneBuy.put(tool, nbr);
			}
		}
		if (team.equals("two")) {
			if (CasterMenu.teamTwoBuy.containsKey(tool)) {
				CasterMenu.teamTwoBuy.put(tool, CasterMenu.teamTwoBuy.get(tool) + nbr);
			} else {
				CasterMenu.teamTwoBuy.put(tool, nbr);
			}
		}
		if (Vars.canDestroy.contains(m)) {
			net.minecraft.server.v1_14_R1.ItemStack toolNMS = CraftItemStack.asNMSCopy(tool);
			NBTTagList canDestroyList = new NBTTagList();
			if (m.equals(Material.WOODEN_PICKAXE)) {
				canDestroyList.add(new NBTTagString("minecraft:beacon"));
				canDestroyList.add(new NBTTagString("minecraft:planks"));
			}
			if (m.equals(Material.STONE_PICKAXE)) {
				canDestroyList.add(new NBTTagString("minecraft:beacon"));
				canDestroyList.add(new NBTTagString("minecraft:planks"));
				canDestroyList.add(new NBTTagString("minecraft:stonebrick"));
			}
			if (m.equals(Material.IRON_PICKAXE)) {
				canDestroyList.add(new NBTTagString("minecraft:beacon"));
				canDestroyList.add(new NBTTagString("minecraft:planks"));
				canDestroyList.add(new NBTTagString("minecraft:stonebrick"));
				canDestroyList.add(new NBTTagString("minecraft:iron_block"));
			}
			if (m.equals(Material.DIAMOND_PICKAXE)) {
				canDestroyList.add(new NBTTagString("minecraft:beacon"));
				canDestroyList.add(new NBTTagString("minecraft:planks"));
				canDestroyList.add(new NBTTagString("minecraft:stonebrick"));
				canDestroyList.add(new NBTTagString("minecraft:iron_block"));
				canDestroyList.add(new NBTTagString("minecraft:end_stone"));
			}
			NBTTagCompound cp = (toolNMS.hasTag()) ? toolNMS.getTag() : new NBTTagCompound();
			cp.set("CanDestroy", canDestroyList);
			toolNMS.setTag(cp);
			tool = CraftItemStack.asBukkitCopy(toolNMS);
		}
		if (m.equals(Material.BEACON) || m.equals(Material.BLACK_CARPET) || m.equals(Material.WATER_BUCKET)) {
			net.minecraft.server.v1_14_R1.ItemStack toolNMS = CraftItemStack.asNMSCopy(tool);
			NBTTagList canPlaceOnList = new NBTTagList();
			canPlaceOnList.add(new NBTTagString("minecraft:grass"));
			canPlaceOnList.add(new NBTTagString("minecraft:stone"));
			canPlaceOnList.add(new NBTTagString("minecraft:planks"));
			canPlaceOnList.add(new NBTTagString("minecraft:stonebrick"));
			canPlaceOnList.add(new NBTTagString("minecraft:iron_block"));
			canPlaceOnList.add(new NBTTagString("minecraft:end_stone"));
			NBTTagCompound cp = (toolNMS.hasTag()) ? toolNMS.getTag() : new NBTTagCompound();
			cp.set("CanPlaceOn", canPlaceOnList);
			toolNMS.setTag(cp);
			tool = CraftItemStack.asBukkitCopy(toolNMS);
		}
		if (m.equals(Material.LADDER)) {
			net.minecraft.server.v1_14_R1.ItemStack toolNMS = CraftItemStack.asNMSCopy(tool);
			NBTTagList canPlaceOnList = new NBTTagList();
			canPlaceOnList.add(new NBTTagString("minecraft:planks"));
			canPlaceOnList.add(new NBTTagString("minecraft:stonebrick"));
			canPlaceOnList.add(new NBTTagString("minecraft:iron_block"));
			canPlaceOnList.add(new NBTTagString("minecraft:end_stone"));
			NBTTagCompound cp = (toolNMS.hasTag()) ? toolNMS.getTag() : new NBTTagCompound();
			cp.set("CanPlaceOn", canPlaceOnList);
			toolNMS.setTag(cp);
			tool = CraftItemStack.asBukkitCopy(toolNMS);
		}
		return tool;
	}
	
	public void createToolsMenu() {

		// PICKAXES
		inv.setItem(0, getTool(Material.WOODEN_PICKAXE, 1, "pickaxes.", "wood", ""));
		inv.setItem(1, getTool(Material.STONE_PICKAXE, 1, "pickaxes.", "stone", ""));
		inv.setItem(2, getTool(Material.IRON_PICKAXE, 1, "pickaxes.", "iron", ""));
		inv.setItem(3, getTool(Material.DIAMOND_PICKAXE, 1, "pickaxes.", "diamond", ""));
		
		// BEACONS
		inv.setItem(5, getTool(Material.BEACON, 1, "beacons.", "strength", ""));
		inv.setItem(6, getTool(Material.BEACON, 1, "beacons.", "swiftness", ""));
		inv.setItem(7, getTool(Material.BEACON, 1, "beacons.", "regen", ""));
		inv.setItem(8, getTool(Material.BEACON, 1, "beacons.", "protection", ""));
		
		// BUCKETS
		inv.setItem(18, getTool(Material.WATER_BUCKET, 1, "buckets.", "water", ""));
		inv.setItem(19, getTool(Material.MILK_BUCKET, 1, "buckets.", "milk", ""));
		
		// FISHING ROD, LIGHTER, LADDERS, ENDER PEARL
		inv.setItem(23, getTool(Material.FISHING_ROD, 1, "fishrod", "", ""));
		inv.setItem(24, getTool(Material.FLINT_AND_STEEL, 1, "lighter", "", ""));
		inv.setItem(25, getTool(Material.LADDER, 2, "ladders", "", ""));
		inv.setItem(26, getTool(Material.ENDER_PEARL, 1, "enderpearl", "", ""));
		
		// TRAPS
		inv.setItem(36, getTool(Material.LIME_CARPET, 1, "traps.", "poison", ""));
		inv.setItem(37, getTool(Material.MAGENTA_CARPET, 1, "traps.", "slow", ""));
		inv.setItem(38, getTool(Material.RED_CARPET, 1, "traps.", "harm", ""));
		inv.setItem(39, getTool(Material.BLACK_CARPET, 1, "traps.", "weak", ""));
		
		//BLOCKS TAB
		ItemStack blocksTab = new ItemStack(Material.COBBLESTONE_WALL, 1);
		ItemMeta blocksTabM = blocksTab.getItemMeta();
		blocksTabM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		blocksTabM.setDisplayName("§6§lWall blocks and TnT");
		blocksTabM.setLore(Arrays.asList("§7§oSwitch to wall blocks and tnt shop menu"));
		blocksTab.setItemMeta(blocksTabM);
		inv.setItem(44, blocksTab);
	}
	
	public void openMenu(Player p) {
		p.openInventory(inv);
	}
	
}
