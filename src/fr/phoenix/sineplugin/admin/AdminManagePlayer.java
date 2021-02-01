package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Vars;

public class AdminManagePlayer {

	private ItemStack changeTeam, changeTeamRed, changeTeamGreen, clearInv, clearArmor, killP, banP;
	private Inventory inv;

	public AdminManagePlayer(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Player Menu");
	}

	public void createManagePlayerMenu(Player p, String skOwner) {

		if (Vars.pNameTeamOne.containsKey(skOwner)) {
			changeTeam = new ItemStack(Material.LIME_BANNER, 1);
			ItemMeta ctM = changeTeam.getItemMeta();
			ctM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			ctM.setDisplayName("§6§lChange to §a§lGreen §6§lTeam");
			changeTeam.setItemMeta(ctM);
			inv.setItem(10, changeTeam);
		}
		else if (Vars.pNameTeamTwo.containsKey(skOwner)) {
			changeTeam = new ItemStack(Material.RED_BANNER, 1);
			ItemMeta ctM = changeTeam.getItemMeta();
			ctM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			ctM.setDisplayName("§6§lChange to §c§lRed §6§lTeam");
			changeTeam.setItemMeta(ctM);
			inv.setItem(10, changeTeam);
		} else {
			changeTeamRed = new ItemStack(Material.LIME_BANNER, 1);
			ItemMeta ctrM = changeTeamRed.getItemMeta();
			ctrM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			ctrM.setDisplayName("§6§lChange to §a§lGreen §6§lTeam");
			changeTeamRed.setItemMeta(ctrM);
			inv.setItem(10, changeTeamRed);
			
			changeTeamGreen = new ItemStack(Material.RED_BANNER, 1);
			ItemMeta ctgM = changeTeamGreen.getItemMeta();
			ctgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			ctgM.setDisplayName("§6§lChange to §c§lRed §6§lTeam");
			changeTeamGreen.setItemMeta(ctgM);
			inv.setItem(19, changeTeamGreen);
		}

		clearInv = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta ciM = clearInv.getItemMeta();
		ciM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ciM.setDisplayName("§6§lClear Inventory");
		clearInv.setItemMeta(ciM);
		inv.setItem(12, clearInv);
		
		clearArmor = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta caM = clearArmor.getItemMeta();
		caM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		caM.setDisplayName("§6§lClear All Armors");
		clearArmor.setItemMeta(caM);
		inv.setItem(21, clearArmor);

		killP = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta kpM = killP.getItemMeta();
		kpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		kpM.setDisplayName("§6§lKill Player");
		killP.setItemMeta(kpM);
		inv.setItem(14, killP);

		banP = new ItemStack(Material.BARRIER, 1);
		ItemMeta bpM = banP.getItemMeta();
		bpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bpM.setDisplayName("§6§lBan Player");
		banP.setItemMeta(bpM);
		inv.setItem(16, banP);

		p.openInventory(inv);

	}
}
