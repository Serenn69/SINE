package fr.phoenix.sineplugin.npc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.shopMenu.AlchemistLingeringPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.AlchemistPersonnalPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.AlchemistSplashPotionsMenu;
import fr.phoenix.sineplugin.shopMenu.BlacksmithArmorsMenu;
import fr.phoenix.sineplugin.shopMenu.BlacksmithWeaponsMenu;
import fr.phoenix.sineplugin.shopMenu.EnchanterArmorsMenu;
import fr.phoenix.sineplugin.shopMenu.EnchanterWeaponsMenu;
import fr.phoenix.sineplugin.shopMenu.JunkdealerBlocksMenu;
import fr.phoenix.sineplugin.shopMenu.JunkdealerToolsMenu;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class NpcEvents implements Listener {

	private int redRdy = 0, greenRdy = 0;
	private int wait;

	@EventHandler
	public void noDamageNpc(EntityDamageByEntityEvent e) {
		
		Entity p = e.getDamager();
		Entity ent = e.getEntity();

		if (p instanceof Player) {
			if (ent instanceof Villager || ent instanceof Witch || ent instanceof Evoker || ent instanceof Vindicator
					|| ent instanceof Vex) {
				
				if (ent.isCustomNameVisible() && ent.getCustomName() != null)
					e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void interactNpc(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();

		if (ent instanceof Villager) {

			Villager npc = (Villager) ent;

			// Si le Npc de l'event est un blacksmith
			if (npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName()
					.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.blacksmith").replace("&", "§"))) {
				e.setCancelled(true);

				NpcMenu menu = new NpcMenu(27, "blacksmith");
				menu.createMenuBS();
				menu.openMenu(p);

			}
			// Si le Npc de l'event est un Npc Easter
			else if (npc.isCustomNameVisible() && npc.getCustomName() != null) {
				e.setCancelled(true);
			}
		}
		if (ent instanceof Evoker) {

			Evoker npc = (Evoker) ent;

			// Si le Npc de l'event est un enchanter
			if (npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName()
					.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.enchanter").replace("&", "§"))) {
				e.setCancelled(true);

				NpcMenu menu = new NpcMenu(27, "enchanter");
				menu.createMenuEN();
				menu.openMenu(p);

			}
		}
		if (ent instanceof Witch) {

			Witch npc = (Witch) ent;
			// Si le Npc de l'event est un alchemist
			if (npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName()
					.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.alchemist").replace("&", "§"))) {
				e.setCancelled(true);

				NpcMenu menu = new NpcMenu(27, "alchemist");
				menu.createMenuAL();
				menu.openMenu(p);

			}
		}
		if (ent instanceof Vindicator) {

			Vindicator npc = (Vindicator) ent;
			// Si le Npc de l'event est un junk dealer
			if (npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName()
					.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.junk").replace("&", "§"))) {
				e.setCancelled(true);

				NpcMenu menu = new NpcMenu(27, "junk");
				menu.createMenuJU();
				menu.openMenu(p);
			}
		}
		if (ent instanceof Vex) {

			Vex npc = (Vex) ent;
			// Si le Npc de l'event est un teleporter
			if (npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName()
					.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.tp").replace("&", "§"))) {
				e.setCancelled(true);

				NpcMenu menu = new NpcMenu(27, "tp");
				menu.createMenuTP();
				menu.openMenu(p);
			}
		}
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e) {

		Player p = (Player) e.getPlayer();

		if (!p.isOp()) {
			if (e.getInventory().getType() == InventoryType.WORKBENCH)
				e.setCancelled(true);
			if (e.getInventory().getType() == InventoryType.ANVIL)
				e.setCancelled(true);
			if (e.getInventory().getType() == InventoryType.ENCHANTING)
				e.setCancelled(true);
			if (e.getInventory().getType() == InventoryType.BREWING)
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void onClickNpcMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();

		if (inv != null
				&& e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("npc.shop.blacksmith").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.blacksmith.weapons").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.blacksmith.armors").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			if (it.getType().equals(Material.GOLDEN_SWORD) && it.getItemMeta().getDisplayName().equals("§6§lWeapons")) {
				BlacksmithWeaponsMenu menu = new BlacksmithWeaponsMenu(45);
				menu.createWeaponsMenu();
				menu.openMenu(p);
			} else if (it.getType().equals(Material.GOLDEN_CHESTPLATE)
					&& it.getItemMeta().getDisplayName().equals("§6§lArmors")) {
				BlacksmithArmorsMenu menu = new BlacksmithArmorsMenu(45);
				menu.createArmorsMenu(p);
				menu.openMenu(p);
			}
		}

		if (inv != null
				&& e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("npc.shop.enchanter").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.enchanter.weapons").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.enchanter.armors").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			if (it.getType().equals(Material.GOLDEN_SWORD)
					&& it.getItemMeta().getDisplayName().equals("§6§lWeapons Enchantments")) {
				EnchanterWeaponsMenu menu = new EnchanterWeaponsMenu(36);
				menu.createWeaponsEnchantMenu();
				menu.openMenu(p);
			} else if (it.getType().equals(Material.GOLDEN_CHESTPLATE)
					&& it.getItemMeta().getDisplayName().equals("§6§lArmors Enchantments")) {
				EnchanterArmorsMenu menu = new EnchanterArmorsMenu(54);
				menu.createArmorsEnchantMenu();
				menu.openMenu(p);
			}
		}

		if (inv != null
				&& e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("npc.shop.alchemist").replace("&", "§") + " §lShop")
				|| e.getView().getTitle()
						.equalsIgnoreCase(Files.shopNamesConfig.getString("menu.shop.alchemist.solo").replace("&", "§")
								+ " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.alchemist.splash").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.alchemist.lingering").replace("&", "§")
								+ " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			if (it.getType().equals(Material.POTION)
					&& it.getItemMeta().getDisplayName().equals("§6§lPersonnal Potions")) {
				AlchemistPersonnalPotionsMenu menu = new AlchemistPersonnalPotionsMenu(27);
				menu.createPersonnalPotionsMenu();
				menu.openMenu(p);
			} else if (it.getType().equals(Material.SPLASH_POTION)
					&& it.getItemMeta().getDisplayName().equals("§6§lSplash Potions")) {
				AlchemistSplashPotionsMenu menu = new AlchemistSplashPotionsMenu(27);
				menu.createSplashPotionsMenu();
				menu.openMenu(p);
			} else if (it.getType().equals(Material.LINGERING_POTION)
					&& it.getItemMeta().getDisplayName().equals("§6§lLingering Potions")) {
				AlchemistLingeringPotionsMenu menu = new AlchemistLingeringPotionsMenu(27);
				menu.createLingeringPotionsMenu();
				menu.openMenu(p);
			}
		}

		if (inv != null
				&& e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("npc.shop.junk").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.junk.blocks").replace("&", "§") + " §lShop")
				|| e.getView().getTitle().equalsIgnoreCase(
						Files.shopNamesConfig.getString("menu.shop.junk.tools").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			if (it.getType().equals(Material.COBBLESTONE_WALL)
					&& it.getItemMeta().getDisplayName().equals("§6§lWall blocks and TnT")) {
				JunkdealerBlocksMenu menu = new JunkdealerBlocksMenu(27);
				menu.createBlocksMenu();
				menu.openMenu(p);
			} else if (it.getType().equals(Material.GOLDEN_PICKAXE)
					&& it.getItemMeta().getDisplayName().equals("§6§lTools and Beacons")) {
				JunkdealerToolsMenu menu = new JunkdealerToolsMenu(45);
				menu.createToolsMenu();
				menu.openMenu(p);
			}
		}

		if (inv != null && e.getView().getTitle()
				.equalsIgnoreCase(Files.shopNamesConfig.getString("npc.shop.tp").replace("&", "§") + " §lShop")) {
			if (it == null || it.getType() == null)
				return;
			e.setCancelled(true);

			if (it.getType().equals(Material.DIAMOND_SWORD)
					&& it.getItemMeta().getDisplayName().equals("§6§lTeleport to Arena !")) {
				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					redRdy = 1;
					for (Player pl : Bukkit.getOnlinePlayers()) {
						PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&4RED &fteam are ready !") + "\"}");
						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);
					}
					if (greenRdy == 1 && redRdy == 1) {
						waitRdy();
					}
				} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					greenRdy = 1;
					for (Player pl : Bukkit.getOnlinePlayers()) {
						PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
						IChatBaseComponent Title = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&aGREEN &fteam are ready !") + "\"}");
						PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
						PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
						connection.sendPacket(title);
						connection.sendPacket(subtitle);
					}
					if (redRdy == 1 && greenRdy == 1) {
						waitRdy();
					}
				}
			}
		}
	}

	public void waitRdy() {

		redRdy = 0;
		greenRdy = 0;

		Main.getInstance().getServer().getScheduler().cancelTask(Vars.aBarGolds);

		wait = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			int i = 4;

			@Override
			public void run() {
				i--;

				if (i > 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',
										"&bDefense phase begins in &6" + i + "&b seconds...") + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
					}
				}
				if (i == 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer.a("{\"text\":\"\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
					}
					Main.getInstance().getServer().getScheduler().cancelTask(Countdown.prep);
					Countdown.cdPrepBar.removeAll();
					Main.getInstance().getServer().getScheduler().cancelTask(wait);
					new Countdown().countDownDef(Files.config.getInt("options.def"));
				}
			}
		}, 20, 20);
	}

}
