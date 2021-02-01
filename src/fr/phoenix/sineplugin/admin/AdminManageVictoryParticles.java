package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageVictoryParticles {

	private ItemStack waterBubble, dragonBreath, endRod, totem, redstone, smoke_normal, spell_mob_ambient, suspend_depth, water_wake, flame;
	private Inventory inv;

	public AdminManageVictoryParticles(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Victory Particles Menu");
	}
		
	public void createManageVictoryParticlesMenu(Player p) {
		
		waterBubble = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta wbM = waterBubble.getItemMeta();
		wbM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wbM.setDisplayName("§6§lWater Bubble Particles");
		waterBubble.setItemMeta(wbM);
		inv.setItem(11, waterBubble);
		
		dragonBreath = new ItemStack(Material.DRAGON_HEAD, 1);
		ItemMeta dbM = dragonBreath.getItemMeta();
		dbM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		dbM.setDisplayName("§6§lDragon Breath Particles");
		dragonBreath.setItemMeta(dbM);
		inv.setItem(12, dragonBreath);
		
		endRod = new ItemStack(Material.END_ROD, 1);
		ItemMeta erM = endRod.getItemMeta();
		erM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		erM.setDisplayName("§6§lEnd Rod Particles");
		endRod.setItemMeta(erM);
		inv.setItem(13, endRod);
		
		totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		ItemMeta tM = totem.getItemMeta();
		tM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tM.setDisplayName("§6§lTotem Particles");
		totem.setItemMeta(tM);
		inv.setItem(14, totem);
		
		flame = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta fM = flame.getItemMeta();
		fM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		fM.setDisplayName("§6§lFlame Particles");
		flame.setItemMeta(fM);
		inv.setItem(15, flame);
		
		redstone = new ItemStack(Material.REDSTONE, 1);
		ItemMeta rM = redstone.getItemMeta();
		rM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		rM.setDisplayName("§6§lRedstone Particles");
		redstone.setItemMeta(rM);
		inv.setItem(20, redstone);
		
		smoke_normal = new ItemStack(Material.COAL, 1);
		ItemMeta snM = smoke_normal.getItemMeta();
		snM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		snM.setDisplayName("§6§lSmoke Normal Particles");
		smoke_normal.setItemMeta(snM);
		inv.setItem(21, smoke_normal);
		
		spell_mob_ambient = new ItemStack(Material.SKELETON_SKULL, 1);
		ItemMeta smaM = spell_mob_ambient.getItemMeta();
		smaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		smaM.setDisplayName("§6§lSpell Mob Ambient Particles");
		spell_mob_ambient.setItemMeta(smaM);
		inv.setItem(22, spell_mob_ambient);
		
		suspend_depth = new ItemStack(Material.MELON_SEEDS, 1);
		ItemMeta sdM = suspend_depth.getItemMeta();
		sdM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sdM.setDisplayName("§6§lSuspend Depth Particles");
		suspend_depth.setItemMeta(sdM);
		inv.setItem(23, suspend_depth);
		
		water_wake = new ItemStack(Material.WATER_BUCKET, 1);
		ItemMeta wwM = water_wake.getItemMeta();
		wwM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wwM.setDisplayName("§6§lWater Wake Particles");
		water_wake.setItemMeta(wwM);
		inv.setItem(24, water_wake);
		
		p.openInventory(inv);	
	}
}
