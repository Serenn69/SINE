package fr.phoenix.sineplugin.caster;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.phases.Countdown;

public class CasterEvents implements Listener {

	@EventHandler
	public void clicItemsMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		ItemStack it = e.getCurrentItem();

		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().equals(Material.RED_BANNER) || p.getInventory().getHelmet().equals(Material.LIME_BANNER)) {
				if (it.getType().equals(Material.RED_BANNER) || it.getType().equals(Material.LIME_BANNER)) {
					e.setCancelled(true);
				}
			}
		}

		if (e.getView().getTitle().equalsIgnoreCase("       §4RED       §dR§f:§d" + Countdown.round + "      §2GREEN  ")
				|| inv != null) {
			if (it == null || it.getType() == null)
				return;
			if (it.getType().equals(Material.OAK_DOOR)
					&& it.getItemMeta().getDisplayName().equals("§6§lTp to Hall")) {
				p.teleport(Tp.hall);
				p.updateInventory();
			}
			if (it.getType().equals(Material.RED_WOOL)
					&& it.getItemMeta().getDisplayName().equals("§6§lTp to §c§lRED §6§lShop")) {
				p.teleport(Tp.shopO);
				p.updateInventory();
			}
			if (it.getType().equals(Material.LIME_WOOL)
					&& it.getItemMeta().getDisplayName().equals("§6§lTp to §a§lGREEN §6§lShop")) {
				p.teleport(Tp.shopT);
				p.updateInventory();
			}
			if (it.getType().equals(Material.CHEST)
					&& it.getItemMeta().getDisplayName().equals("§6§lRed and Green purchases")) {
				CasterMenu menu = new CasterMenu(54, "");
				menu.createCasterChestMenu(p, "");
			}
			if (it.getType().equals(Material.ENDER_CHEST)
					&& it.getItemMeta().getDisplayName().equals("§6§lPrevious Red and Green purchases")) {
				CasterMenu menu = new CasterMenu(54, "yes");
				menu.createCasterChestMenu(p, "yes");
			}
			if (it.getType().equals(Material.BARRIER)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void closeInventory(InventoryCloseEvent e) {

		Inventory inv = e.getInventory();

		if (inv.equals(CasterMenu.inv)) {
			Main.getInstance().getServer().getScheduler().cancelTask(CasterMenu.refresh);
			CasterMenu.teamOneBuyed.clear();
			CasterMenu.teamTwoBuyed.clear();
		}
	}  
	
	@EventHandler
	public void openInventory(InventoryOpenEvent e) {
		
		Inventory inv = e.getInventory();
		
		if (inv.equals(CasterMenu.inv)) {
			
		}	
	}

}
