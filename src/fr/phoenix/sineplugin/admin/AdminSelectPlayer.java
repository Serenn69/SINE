package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class AdminSelectPlayer {
	
	private ItemStack pSkull, freezeAll, unfreezeAll;
	private Inventory inv;

	public AdminSelectPlayer(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oSelect Player Menu");
	}
		
	@SuppressWarnings("deprecation")
	public void createSelectPlayerMenu(Player p) {
		
		freezeAll = new ItemStack(Material.ICE, 1);
		ItemMeta faM = freezeAll.getItemMeta();
		faM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		faM.setDisplayName("§6§lFreeze All Players");
		freezeAll.setItemMeta(faM);
		inv.setItem(16, freezeAll);
		
		unfreezeAll = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta uaM = unfreezeAll.getItemMeta();
		uaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		uaM.setDisplayName("§6§lUnfreeze All Players");
		unfreezeAll.setItemMeta(uaM);
		inv.setItem(25, unfreezeAll);
		
		int slot = 10;

		for (Player pl : Bukkit.getOnlinePlayers()) {
			pSkull = new ItemStack(Material.PLAYER_HEAD, 1);
			SkullMeta skM = (SkullMeta) pSkull.getItemMeta();
			skM.setOwner(pl.getName());
			skM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			skM.setDisplayName("§6§l" + pl.getName());
			pSkull.setItemMeta(skM);
			inv.setItem(slot, pSkull);

			slot++;
			if (slot > 14 || slot > 23 || slot > 32)
				slot += 4;
			if (slot > 41)
				p.sendMessage("§0[§4SINE§0] §7§oToo many players for Select Players Admin Menu !");
		}
		
		p.openInventory(inv);
		
	}
}
