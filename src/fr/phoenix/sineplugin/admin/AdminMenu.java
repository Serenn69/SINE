package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminMenu {

	private Inventory inv;

	public static ItemStack selectPlayer, mngGolds, mngRounds, mngScore, mngMap, mngTime, teleport, particles, debug;

	public AdminMenu(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oAdmin Menu");
	}

	public void createAdminMenu(Player p) {

		selectPlayer = new ItemStack(Material.PLAYER_HEAD, 1);
		ItemMeta spM = selectPlayer.getItemMeta();
		spM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		spM.setDisplayName("§6§lSelect Player Menu");
		selectPlayer.setItemMeta(spM);
		inv.setItem(10, selectPlayer);

		mngGolds = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta mgM = mngGolds.getItemMeta();
		mgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mgM.setDisplayName("§6§lManage Golds Menu");
		mngGolds.setItemMeta(mgM);
		inv.setItem(12, mngGolds);

		mngRounds = new ItemStack(Material.COMPASS, 1);
		ItemMeta mrM = mngRounds.getItemMeta();
		mrM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mrM.setDisplayName("§6§lManage Rounds Menu");
		mngRounds.setItemMeta(mrM);
		inv.setItem(14, mngRounds);

		mngScore = new ItemStack(Material.PAINTING, 1);
		ItemMeta msM = mngScore.getItemMeta();
		msM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		msM.setDisplayName("§6§lManage Score Menu");
		mngScore.setItemMeta(msM);
		inv.setItem(16, mngScore);

		mngMap = new ItemStack(Material.MAP, 1);
		ItemMeta mmM = mngMap.getItemMeta();
		mmM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mmM.setDisplayName("§6§lManage Map Menu");
		mngMap.setItemMeta(mmM);
		inv.setItem(20, mngMap);

		mngTime = new ItemStack(Material.CLOCK, 1);
		ItemMeta mtM = mngTime.getItemMeta();
		mtM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mtM.setDisplayName("§6§lManage Time Menu");
		mngTime.setItemMeta(mtM);
		inv.setItem(22, mngTime);

		teleport = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta tpM = teleport.getItemMeta();
		tpM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tpM.setDisplayName("§6§lTeleport Menu");
		teleport.setItemMeta(tpM);
		inv.setItem(24, teleport);
		
		particles = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta pM = particles.getItemMeta();
		pM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pM.setDisplayName("§6§lParticles Menu");
		particles.setItemMeta(pM);
		inv.setItem(28, particles);

		if (p.getName().equals("Serenny")) {
			debug = new ItemStack(Material.SPAWNER, 1);
			ItemMeta dM = debug.getItemMeta();
			dM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			dM.setDisplayName("§6§lDebug Menu");
			debug.setItemMeta(dM);
			inv.setItem(34, debug);
		}

		p.openInventory(inv);

	}
}