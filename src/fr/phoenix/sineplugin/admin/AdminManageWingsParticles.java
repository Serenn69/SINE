package fr.phoenix.sineplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminManageWingsParticles {

	private ItemStack crit, crit_magic, waterBubble, dragonBreath, enchantmentTable, explosionNormal, heart, portal, redstone, smoke_normal, spell, spell_instant, spell_mob_ambient, spell_witch, suspend_depth, sweep_attack, water_wake, flame, totem;
	private Inventory inv;

	public AdminManageWingsParticles(int size) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		inv = Bukkit.createInventory(null, size, "§5§oManage Wings Particles Menu");
	}
		
	public void createManageWingsParticlesMenu(Player p) {
		
		crit = new ItemStack(Material.PRISMARINE_SHARD, 1);
		ItemMeta cM = crit.getItemMeta();
		cM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cM.setDisplayName("§6§lCrit Particles");
		crit.setItemMeta(cM);
		inv.setItem(10, crit);
		
		crit_magic = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta cmM = crit_magic.getItemMeta();
		cmM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cmM.setDisplayName("§6§lCrit Magic Particles");
		crit_magic.setItemMeta(cmM);
		inv.setItem(11, crit_magic);
		
		waterBubble = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta wbM = waterBubble.getItemMeta();
		wbM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wbM.setDisplayName("§6§lWater Bubble Particles");
		waterBubble.setItemMeta(wbM);
		inv.setItem(12, waterBubble);
		
		dragonBreath = new ItemStack(Material.DRAGON_HEAD, 1);
		ItemMeta dbM = dragonBreath.getItemMeta();
		dbM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		dbM.setDisplayName("§6§lDragon Breath Particles");
		dragonBreath.setItemMeta(dbM);
		inv.setItem(13, dragonBreath);
		
		enchantmentTable = new ItemStack(Material.ENCHANTING_TABLE, 1);
		ItemMeta etM = enchantmentTable.getItemMeta();
		etM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		etM.setDisplayName("§6§lEnchantment Table Particles");
		enchantmentTable.setItemMeta(etM);
		inv.setItem(14, enchantmentTable);
		
		explosionNormal = new ItemStack(Material.TNT, 1);
		ItemMeta enM = explosionNormal.getItemMeta();
		enM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		enM.setDisplayName("§6§lExplosion Normal Particles");
		explosionNormal.setItemMeta(enM);
		inv.setItem(15, explosionNormal);
		
		heart = new ItemStack(Material.APPLE, 1);
		ItemMeta hM = heart.getItemMeta();
		hM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		hM.setDisplayName("§6§lHeart Particles");
		heart.setItemMeta(hM);
		inv.setItem(16, heart);
		
		portal = new ItemStack(Material.ENDER_EYE, 1);
		ItemMeta pM = portal.getItemMeta();
		pM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pM.setDisplayName("§6§lPortal Particles");
		portal.setItemMeta(pM);
		inv.setItem(19, portal);
		
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
		
		spell = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta sM = spell.getItemMeta();
		sM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sM.setDisplayName("§6§lSpell Particles");
		spell.setItemMeta(sM);
		inv.setItem(22, spell);
		
		spell_instant = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
		ItemMeta siM = spell_instant.getItemMeta();
		siM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		siM.setDisplayName("§6§lSpell Instant Particles");
		spell_instant.setItemMeta(siM);
		inv.setItem(23, spell_instant);
		
		spell_mob_ambient = new ItemStack(Material.SKELETON_SKULL, 1);
		ItemMeta smaM = spell_mob_ambient.getItemMeta();
		smaM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		smaM.setDisplayName("§6§lSpell Mob Ambient Particles");
		spell_mob_ambient.setItemMeta(smaM);
		inv.setItem(24, spell_mob_ambient);
		
		spell_witch = new ItemStack(Material.SPLASH_POTION, 1);
		ItemMeta swM = spell_witch.getItemMeta();
		swM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		swM.setDisplayName("§6§lSpell Witch Particles");
		spell_witch.setItemMeta(swM);
		inv.setItem(25, spell_witch);
		
		flame = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta fM = flame.getItemMeta();
		fM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		fM.setDisplayName("§6§lFlame Particles");
		flame.setItemMeta(fM);
		inv.setItem(29, flame);
		
		suspend_depth = new ItemStack(Material.MELON_SEEDS, 1);
		ItemMeta sdM = suspend_depth.getItemMeta();
		sdM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sdM.setDisplayName("§6§lSuspend Depth Particles");
		suspend_depth.setItemMeta(sdM);
		inv.setItem(30, suspend_depth);
		
		sweep_attack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta saM = sweep_attack.getItemMeta();
		saM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		saM.setDisplayName("§6§lSweep Attack Particles");
		sweep_attack.setItemMeta(saM);
		inv.setItem(31, sweep_attack);
		
		water_wake = new ItemStack(Material.WATER_BUCKET, 1);
		ItemMeta wwM = water_wake.getItemMeta();
		wwM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		wwM.setDisplayName("§6§lWater Wake Particles");
		water_wake.setItemMeta(wwM);
		inv.setItem(32, water_wake);
		
		totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		ItemMeta tM = totem.getItemMeta();
		tM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		tM.setDisplayName("§6§lTotem Particles");
		totem.setItemMeta(tM);
		inv.setItem(33, totem);
		
		p.openInventory(inv);
		
	}
}
