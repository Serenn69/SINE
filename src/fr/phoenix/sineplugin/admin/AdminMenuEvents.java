package fr.phoenix.sineplugin.admin;

import java.util.Date;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.phoenix.sineplugin.Arena;
import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Flag;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.PlayersJoins;
import fr.phoenix.sineplugin.Tp;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.caster.CasterMenu;
import fr.phoenix.sineplugin.holograms.Holograms;
import fr.phoenix.sineplugin.phases.Begin;
import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.phases.Fight;
import fr.phoenix.sineplugin.phases.Victory;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class AdminMenuEvents implements Listener {

	private String skOwner;
	private int banMenu = 0, redGoldsMenu = 0, greenGoldsMenu = 0;
	private Location bannerLocBase;
	private DyeColor color;

	private int test = 0;

	@EventHandler
	public void openAdminMenu(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();

		if (Vars.pNameAdmin.contains(p.getName())) {
			if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR
					|| a == Action.RIGHT_CLICK_BLOCK) {
				if (p.getInventory().getItemInMainHand().getType().equals(Material.COMMAND_BLOCK)) {
					AdminMenu menu = new AdminMenu(45);
					menu.createAdminMenu(p);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClickAdminMenu(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		ItemStack it = e.getCurrentItem();

		// CLICK OPEN ADMIN MENU
		if (it.getType().equals(Material.COMMAND_BLOCK)
				&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdmin Menu")) {
			AdminMenu menu = new AdminMenu(45);
			menu.createAdminMenu(p);
			e.setCancelled(true);
		}

		// CLICK ADMIN MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oAdmin Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;
			if (it.getType().equals(Material.PLAYER_HEAD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSelect Player Menu")) {
				AdminSelectPlayer menu = new AdminSelectPlayer(54);
				menu.createSelectPlayerMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.GOLD_INGOT)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lManage Golds Menu")) {
				AdminManageGolds menu = new AdminManageGolds(36);
				menu.createManageGoldsMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.COMPASS)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lManage Rounds Menu")) {
				AdminManageRounds menu = new AdminManageRounds(27);
				menu.createManageRoundsMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.PAINTING)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lManage Score Menu")) {
				AdminManageScore menu = new AdminManageScore(36);
				menu.createManageScoreMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.MAP)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lManage Map Menu")) {
				AdminManageMap menu = new AdminManageMap(36);
				menu.createManageMapMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.CLOCK)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lManage Time Menu")) {
				AdminManageTime menu = new AdminManageTime(36);
				menu.createManageTimeMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ENDER_PEARL)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTeleport Menu")) {
				AdminTeleport menu = new AdminTeleport(36);
				menu.createTeleportMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.NETHER_STAR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lParticles Menu")) {
				AdminSelectParticles menu = new AdminSelectParticles(36);
				menu.createSelectParticlesMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.SPAWNER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDebug Menu")) {
				AdminDebug menu = new AdminDebug(27);
				menu.createDebugMenu(p);
				e.setCancelled(true);
			}
		}

		// CLICK SELECT PLAYER MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oSelect Player Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;
			if (it.getType().equals(Material.PLAYER_HEAD)) {
				if (it.hasItemMeta()) {
					SkullMeta skM = (SkullMeta) it.getItemMeta();
					skOwner = skM.getOwner();
					AdminManagePlayer menu = new AdminManagePlayer(36);
					menu.createManagePlayerMenu(p, skOwner);
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.ICE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFreeze All Players")) {
				Vars.freeze = 1;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.LAVA_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lUnfreeze All Players")) {
				Vars.freeze = 0;
				e.setCancelled(true);
			}
		}

		// CLICK MANAGE PLAYER MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Player Menu") && inv != null) {

			Player pl = Bukkit.getPlayerExact(skOwner);

			if (it == null || it.getType() == null)
				return;
			if (it.getType().equals(Material.RED_BANNER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lChange to §c§lRed §6§lTeam")) {
				if (Vars.pNameAdmin.contains(skOwner)) {
					Vars.pNameAdmin.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Admin");
				}
				if (Vars.pNameCaster.contains(skOwner)) {
					Vars.pNameCaster.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Caster");
				}
				if (Vars.pNameSpec.contains(skOwner)) {
					Vars.pNameSpec.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spec");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Spec");
				}
				if (!Vars.pNameGamers.contains(skOwner)) {
					Vars.pNameGamers.add(skOwner);
				}
				if (pl.isOp())
					pl.setOp(false);

				if (Vars.pNameTeamTwo.containsKey(skOwner)) {
					Vars.pNameTeamTwo.remove(skOwner);
					Vars.teamTwo.removeEntry(skOwner);
					Vars.oneTeamTwo.removeEntry(skOwner);
					Vars.twoTeamTwo.removeEntry(skOwner);
					Vars.casterTeamTwo.removeEntry(skOwner);

					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §aGreen");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §aGreen");
				}

				Vars.pNameTeamOne.put(skOwner, 1);
				Vars.teamOne.addEntry(skOwner);
				Vars.oneTeamOne.addEntry(skOwner);
				Vars.twoTeamOne.addEntry(skOwner);
				Vars.casterTeamOne.addEntry(skOwner);

				pl.sendMessage("§0[§4SINE§0] §7§oYou are now §cRed");
				p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are now §cRed");

				p.setScoreboard(Vars.sineBoardOne);
				Vars.trackerOne.getScore(p.getName()).setScore(Begin.iO);
				Vars.trackerCaster.getScore(p.getName()).setScore(Begin.iO);
				Begin.iO--;

				for (String pN : Vars.pNameTeamOne.keySet()) {
					Player pO = Bukkit.getPlayerExact(pN);
					pl.teleport(pO);
					break;
				}

				pl.getInventory().clear();
				pl.setGameMode(GameMode.ADVENTURE);
				new PlayerStats().createProfileSeason(pl);

				e.setCancelled(true);
			}
			if (it.getType().equals(Material.LIME_BANNER)
					& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lChange to §a§lGreen §6§lTeam")) {

				if (Vars.pNameAdmin.contains(skOwner)) {
					Vars.pNameAdmin.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Admin");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Admin");
				}
				if (Vars.pNameCaster.contains(skOwner)) {
					Vars.pNameCaster.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Caster");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Caster");
				}
				if (Vars.pNameSpec.contains(skOwner)) {
					Vars.pNameSpec.remove(skOwner);
					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §6Spec");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §6Spec");
				}
				if (!Vars.pNameGamers.contains(skOwner)) {
					Vars.pNameGamers.add(skOwner);
				}
				if (pl.isOp())
					pl.setOp(false);

				if (Vars.pNameTeamOne.containsKey(skOwner)) {
					Vars.pNameTeamOne.remove(skOwner);
					Vars.teamOne.removeEntry(skOwner);
					Vars.oneTeamOne.removeEntry(skOwner);
					Vars.twoTeamOne.removeEntry(skOwner);
					Vars.casterTeamOne.removeEntry(skOwner);

					pl.sendMessage("§0[§4SINE§0] §7§oYou are no longer §cRed");
					p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are no longer §cRed");
				}

				Vars.pNameTeamTwo.put(skOwner, 1);
				Vars.teamTwo.addEntry(skOwner);
				Vars.oneTeamTwo.addEntry(skOwner);
				Vars.twoTeamTwo.addEntry(skOwner);
				Vars.casterTeamTwo.addEntry(skOwner);

				pl.sendMessage("§0[§4SINE§0] §7§oYou are now §aGreen");
				p.sendMessage("§0[§4SINE§0] §7§o" + skOwner + "§7§o are now §aGreen");

				p.setScoreboard(Vars.sineBoardTwo);
				Vars.trackerTwo.getScore(p.getName()).setScore(Begin.iT);
				Vars.trackerCaster.getScore(p.getName()).setScore(Begin.iT);
				Begin.iT--;

				for (String pN : Vars.pNameTeamTwo.keySet()) {
					Player pO = Bukkit.getPlayerExact(pN);
					pl.teleport(pO);
					break;
				}

				pl.getInventory().clear();
				pl.setGameMode(GameMode.ADVENTURE);
				new PlayerStats().createProfileSeason(pl);

				e.setCancelled(true);
			}
			if (it.getType().equals(Material.LAVA_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClear Inventory")) {
				pl.getInventory().clear();
			}
			if (it.getType().equals(Material.LAVA_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClear All Armors")) {
				pl.getInventory().getHelmet().setType(Material.AIR);
				pl.getInventory().getChestplate().setType(Material.AIR);
				pl.getInventory().getLeggings().setType(Material.AIR);
				pl.getInventory().getBoots().setType(Material.AIR);
			}
			if (it.getType().equals(Material.DIAMOND_SWORD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lKillPlayer")) {
				pl.setHealth(0);
			}

			if (it.getType().equals(Material.BARRIER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan Player")) {

				if (banMenu == 0) {
					banMenu = 1;
				} else {
					banMenu = 0;
				}

				if (banMenu == 1) {
					ItemStack banTenMins = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta btmM = banTenMins.getItemMeta();
					btmM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					btmM.setDisplayName("§6§lBan §d10 Minutes");
					banTenMins.setItemMeta(btmM);
					inv.setItem(24, banTenMins);

					ItemStack banTwoHours = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta bthM = banTwoHours.getItemMeta();
					bthM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					bthM.setDisplayName("§6§lBan §d2 Hours");
					banTwoHours.setItemMeta(bthM);
					inv.setItem(25, banTwoHours);

					ItemStack banOneDay = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta bodM = banOneDay.getItemMeta();
					bodM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					bodM.setDisplayName("§6§lBan §d1 Day");
					banOneDay.setItemMeta(bodM);
					inv.setItem(26, banOneDay);

					ItemStack banOneWeek = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta bowM = banOneWeek.getItemMeta();
					bowM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					bowM.setDisplayName("§6§lBan §d1 Week");
					banOneWeek.setItemMeta(bowM);
					inv.setItem(33, banOneWeek);

					ItemStack banOneMonth = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta bomM = banOneMonth.getItemMeta();
					bomM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					bomM.setDisplayName("§6§lBan §d1 Month");
					banOneMonth.setItemMeta(bomM);
					inv.setItem(34, banOneMonth);

					ItemStack banForLife = new ItemStack(Material.STRUCTURE_VOID, 1);
					ItemMeta bflM = banForLife.getItemMeta();
					bflM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					bflM.setDisplayName("§6§lBan §dFor LIFE");
					banForLife.setItemMeta(bflM);
					inv.setItem(35, banForLife);
				} else {
					inv.clear(24);
					inv.clear(25);
					inv.clear(26);
					inv.clear(33);
					inv.clear(34);
					inv.clear(35);
				}
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §d10 Minutes")) {
				Date tenMins = new Date(System.currentTimeMillis() + 10 * (60 * 1000));
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d10 Minutes", tenMins, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d10 Minutes");
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §d2 Hours")) {
				Date twoHours = new Date(System.currentTimeMillis() + 2 * (60 * (60 * 1000)));
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d2 Hours", twoHours, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d2 Hours");
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §d1 Day")) {
				Date oneDay = new Date(System.currentTimeMillis() + 24 * (60 * (60 * 1000)));
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Day", oneDay, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Day");
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §d1 Week")) {
				Date oneWeek = new Date(System.currentTimeMillis() + 7 * (24 * (60 * (60 * 1000))));
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Week", oneWeek, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Week");
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §d1 Month")) {
				Date oneMonth = new Date(System.currentTimeMillis() + 4 * (7 * (24 * (60 * (60 * 1000)))));
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Month", oneMonth, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §d1 Month");
			}
			if (it.getType().equals(Material.STRUCTURE_VOID)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBan §dFor LIFE")) {
				Bukkit.getBanList(BanList.Type.NAME).addBan(skOwner,
						"§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §dFor LIFE", null, null);
				pl.kickPlayer("§0[§4SINE§0] §7§oYou are banned by §6Admin §7§ofor §dFor LIFE");
			}
		}

		// MANAGE GOLDS TEAM MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Golds Teams Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.GOLD_BLOCK)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §c§lRed §6§lGolds line :")
						|| it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove §c§lRed §6§lGolds line :")
						|| it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §a§lGreen §6§lGolds line :")
						|| it.getItemMeta().getDisplayName()
								.equalsIgnoreCase("§6§lRemove §a§lGreen §6§lGolds line :")) {
					e.setCancelled(true);
				}
			}

			if (it.getType().equals(Material.RED_BANNER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lTeam Golds")) {

				e.setCancelled(true);

				if (redGoldsMenu == 0) {
					redGoldsMenu = 1;
					greenGoldsMenu = 0;
				} else {
					redGoldsMenu = 0;
				}

				if (redGoldsMenu == 1) {
					ItemStack addRedGolds = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
					ItemMeta argM = addRedGolds.getItemMeta();
					argM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					argM.setDisplayName("§6§lAdd §c§lRed §6§lGolds line :");
					addRedGolds.setItemMeta(argM);
					inv.setItem(20, addRedGolds);

					ItemStack remRedGolds = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
					ItemMeta rrgM = remRedGolds.getItemMeta();
					rrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rrgM.setDisplayName("§6§lRemove §c§lRed §6§lGolds line :");
					remRedGolds.setItemMeta(rrgM);
					inv.setItem(29, remRedGolds);

					ItemStack addOneRedGolds = new ItemStack(Material.GOLD_NUGGET, 1);
					ItemMeta aorgM = addOneRedGolds.getItemMeta();
					aorgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					aorgM.setDisplayName("§6§lAdd §d1 §6§lGold to §cRed §6§lTeam");
					addOneRedGolds.setItemMeta(aorgM);
					inv.setItem(21, addOneRedGolds);

					ItemStack addFiveRedGolds = new ItemStack(Material.GOLD_INGOT, 1);
					ItemMeta afrgM = addFiveRedGolds.getItemMeta();
					afrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					afrgM.setDisplayName("§6§lAdd §d5 §6§lGolds to §cRed §6§lTeam");
					addFiveRedGolds.setItemMeta(afrgM);
					inv.setItem(22, addFiveRedGolds);

					ItemStack addTwentyRedGolds = new ItemStack(Material.GOLD_ORE, 1);
					ItemMeta atrgM = addTwentyRedGolds.getItemMeta();
					atrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					atrgM.setDisplayName("§6§lAdd §d20 §6§lGolds to §cRed §6§lTeam");
					addTwentyRedGolds.setItemMeta(atrgM);
					inv.setItem(23, addTwentyRedGolds);

					ItemStack addHundredRedGolds = new ItemStack(Material.GOLD_BLOCK, 1);
					ItemMeta ahrgM = addHundredRedGolds.getItemMeta();
					ahrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					ahrgM.setDisplayName("§6§lAdd §d100 §6§lGolds to §cRed §6§lTeam");
					addHundredRedGolds.setItemMeta(ahrgM);
					inv.setItem(24, addHundredRedGolds);

					ItemStack remOneRedGolds = new ItemStack(Material.IRON_NUGGET, 1);
					ItemMeta rorgM = remOneRedGolds.getItemMeta();
					rorgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rorgM.setDisplayName("§6§lRemove §d1 §6§lGold to §cRed §6§lTeam");
					remOneRedGolds.setItemMeta(rorgM);
					inv.setItem(30, remOneRedGolds);

					ItemStack remFiveRedGolds = new ItemStack(Material.IRON_INGOT, 1);
					ItemMeta rfrgM = remFiveRedGolds.getItemMeta();
					rfrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rfrgM.setDisplayName("§6§lRemove §d5 §6§lGolds to §cRed §6§lTeam");
					remFiveRedGolds.setItemMeta(rfrgM);
					inv.setItem(31, remFiveRedGolds);

					ItemStack remTwentyRedGolds = new ItemStack(Material.IRON_ORE, 1);
					ItemMeta rtrgM = remTwentyRedGolds.getItemMeta();
					rtrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rtrgM.setDisplayName("§6§lRemove §d20 §6§lGolds to §cRed §6§lTeam");
					remTwentyRedGolds.setItemMeta(rtrgM);
					inv.setItem(32, remTwentyRedGolds);

					ItemStack remHundredRedGolds = new ItemStack(Material.IRON_BLOCK, 1);
					ItemMeta rhrgM = remHundredRedGolds.getItemMeta();
					rhrgM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rhrgM.setDisplayName("§6§lRemove §d100 §6§lGolds to §cRed §6§lTeam");
					remHundredRedGolds.setItemMeta(rhrgM);
					inv.setItem(33, remHundredRedGolds);
				} else {
					for (int i = 20; i < 25; i++) {
						inv.clear(i);
					}
					for (int i = 29; i < 34; i++) {
						inv.clear(i);
					}
				}
			}

			if (it.getType().equals(Material.LIME_BANNER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lTeam Golds")) {

				e.setCancelled(true);

				if (greenGoldsMenu == 0) {
					greenGoldsMenu = 1;
					redGoldsMenu = 0;
				} else {
					greenGoldsMenu = 0;
				}

				if (greenGoldsMenu == 1) {

					ItemStack addGreenGolds = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
					ItemMeta aggM = addGreenGolds.getItemMeta();
					aggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					aggM.setDisplayName("§6§lAdd §a§lGreen §6§lGolds line :");
					addGreenGolds.setItemMeta(aggM);
					inv.setItem(20, addGreenGolds);

					ItemStack remGreenGolds = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
					ItemMeta rggM = remGreenGolds.getItemMeta();
					rggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rggM.setDisplayName("§6§lRemove §a§lGreen §6§lGolds line :");
					remGreenGolds.setItemMeta(rggM);
					inv.setItem(29, remGreenGolds);

					ItemStack addOneGreenGolds = new ItemStack(Material.GOLD_NUGGET, 1);
					ItemMeta aoggM = addOneGreenGolds.getItemMeta();
					aoggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					aoggM.setDisplayName("§6§lAdd §d1 §6§lGold to §aGreen §6§lTeam");
					addOneGreenGolds.setItemMeta(aoggM);
					inv.setItem(21, addOneGreenGolds);

					ItemStack addFiveGreenGolds = new ItemStack(Material.GOLD_INGOT, 1);
					ItemMeta afggM = addFiveGreenGolds.getItemMeta();
					afggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					afggM.setDisplayName("§6§lAdd §d5 §6§lGolds to §aGreen §6§lTeam");
					addFiveGreenGolds.setItemMeta(afggM);
					inv.setItem(22, addFiveGreenGolds);

					ItemStack addTwentyGreenGolds = new ItemStack(Material.GOLD_ORE, 1);
					ItemMeta atggM = addTwentyGreenGolds.getItemMeta();
					atggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					atggM.setDisplayName("§6§lAdd §d20 §6§lGolds to §aGreen §6§lTeam");
					addTwentyGreenGolds.setItemMeta(atggM);
					inv.setItem(23, addTwentyGreenGolds);

					ItemStack addHundredGreenGolds = new ItemStack(Material.GOLD_BLOCK, 1);
					ItemMeta ahggM = addHundredGreenGolds.getItemMeta();
					ahggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					ahggM.setDisplayName("§6§lAdd §d100 §6§lGolds to §aGreen §6§lTeam");
					addHundredGreenGolds.setItemMeta(ahggM);
					inv.setItem(24, addHundredGreenGolds);

					ItemStack remOneGreenGolds = new ItemStack(Material.IRON_NUGGET, 1);
					ItemMeta roggM = remOneGreenGolds.getItemMeta();
					roggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					roggM.setDisplayName("§6§lRemove §d1 §6§lGold to §aGreen §6§lTeam");
					remOneGreenGolds.setItemMeta(roggM);
					inv.setItem(30, remOneGreenGolds);

					ItemStack remFiveGreenGolds = new ItemStack(Material.IRON_INGOT, 1);
					ItemMeta rfggM = remFiveGreenGolds.getItemMeta();
					rfggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rfggM.setDisplayName("§6§lRemove §d5 §6§lGolds to §aGreen §6§lTeam");
					remFiveGreenGolds.setItemMeta(rfggM);
					inv.setItem(31, remFiveGreenGolds);

					ItemStack remTwentyGreenGolds = new ItemStack(Material.IRON_ORE, 1);
					ItemMeta rtggM = remTwentyGreenGolds.getItemMeta();
					rtggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rtggM.setDisplayName("§6§lRemove §d20 §6§lGolds to §aGreen §6§lTeam");
					remTwentyGreenGolds.setItemMeta(rtggM);
					inv.setItem(32, remTwentyGreenGolds);

					ItemStack remHundredGreenGolds = new ItemStack(Material.IRON_BLOCK, 1);
					ItemMeta rhggM = remHundredGreenGolds.getItemMeta();
					rhggM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					rhggM.setDisplayName("§6§lRemove §d100 §6§lGolds to §aGreen §6§lTeam");
					remHundredGreenGolds.setItemMeta(rhggM);
					inv.setItem(33, remHundredGreenGolds);

				} else {
					for (int i = 20; i < 25; i++) {
						inv.clear(i);
					}
					for (int i = 29; i < 34; i++) {
						inv.clear(i);
					}
				}
			}

			if (it.getType().equals(Material.GOLD_NUGGET)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d1 §6§lGold to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal += 1;
					Vars.totalOneWinGolds += 1;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d1 §6§lGold to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal += 1;
					Vars.totalTwoWinGolds += 1;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.GOLD_INGOT)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d5 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal += 5;
					Vars.totalOneWinGolds += 5;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d5 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal += 5;
					Vars.totalTwoWinGolds += 5;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.GOLD_ORE)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d20 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal += 20;
					Vars.totalOneWinGolds += 20;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d20 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal += 20;
					Vars.totalTwoWinGolds += 20;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.GOLD_BLOCK)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d100 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal += 100;
					Vars.totalOneWinGolds += 100;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd §d100 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal += 100;
					Vars.totalTwoWinGolds += 100;
					e.setCancelled(true);
				}
			}

			if (it.getType().equals(Material.IRON_NUGGET)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove §d1 §6§lGold to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal -= 1;
					Vars.totalOneWinGolds -= 1;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove §d1 §6§lGold to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal -= 1;
					Vars.totalTwoWinGolds -= 1;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.IRON_INGOT)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove §d5 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal -= 5;
					Vars.totalOneWinGolds -= 5;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName()
						.equalsIgnoreCase("§6§lRemove §d5 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal -= 5;
					Vars.totalTwoWinGolds -= 5;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.IRON_ORE)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove §d20 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal -= 20;
					Vars.totalOneWinGolds -= 20;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName()
						.equalsIgnoreCase("§6§lRemove §d20 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal -= 20;
					Vars.totalTwoWinGolds -= 20;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.IRON_BLOCK)) {
				if (it.getItemMeta().getDisplayName()
						.equalsIgnoreCase("§6§lRemove §d100 §6§lGolds to §cRed §6§lTeam")) {
					Vars.oneGoldsTotal -= 100;
					Vars.totalOneWinGolds -= 100;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName()
						.equalsIgnoreCase("§6§lRemove §d100 §6§lGolds to §aGreen §6§lTeam")) {
					Vars.twoGoldsTotal -= 100;
					Vars.totalTwoWinGolds -= 100;
					e.setCancelled(true);
				}
			}
		}

		// MANAGE ROUNDS MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Rounds Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.CHEST)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFinish Preparation Phase")) {
				Main.getInstance().getServer().getScheduler().cancelTask(Countdown.prep);
				Countdown.cdPrepBar.removeAll();
				new Countdown().countDownDef(Files.config.getInt("options.def"));
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.COBBLESTONE_WALL)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFinish Defense Phase")) {
				Main.getInstance().getServer().getScheduler().cancelTask(Countdown.def);
				Countdown.cdDefBar.removeAll();
				new Arena().clearAtkBase();
				new Fight().beginFightMsg();
				new Countdown().countDownFight(Files.config.getInt("options.fight"));
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.DIAMOND_SWORD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFinish Fight Phase")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(Flag.oneTakeFlag)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Flag.oneTakeFlag);
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Flag.twoTakeFlag)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Flag.twoTakeFlag);
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Flag.captur)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Flag.captur);
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.blitz)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Countdown.blitz);
					if (Countdown.cdBlitzBar.isVisible()) {
						Countdown.cdBlitzBar.removeAll();
					}
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.lastTry)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Countdown.lastTry);
					if (Countdown.cdLastTry.isVisible()) {
						Countdown.cdLastTry.removeAll();
					}
				}

				for (Player pF : Bukkit.getOnlinePlayers()) {
					if (pF.getInventory().getHelmet().getType().equals(Material.BLACK_BANNER)) {
						new Flag().replaceHelmet(pF);
					}
				}
				new Vars().resetStatsVars();
				if (Vars.whoBegin.equals("OaTd")) {
					Vars.twoPerfDef = 0;
				} else if (Vars.whoBegin.equals("OdTa")) {
					Vars.onePerfDef = 0;
				}
				Vars.onePerfect = 0;
				Vars.twoPerfect = 0;
				Vars.draw = 1;
				Vars.totalDraw++;
				new Victory().updateScore("draw");
				e.setCancelled(true);
			}
		}

		// MANAGE SCORE MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Score Teams Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.RED_BANNER) || it.getType().equals(Material.LIME_BANNER)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lTeam")
						|| it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lTeam")) {
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.INK_SAC)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd 1 to §c§lRed §6§lScore")) {
					Vars.oneScore += 1;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd 1 to §a§lGreen §6§lScore")) {
					Vars.twoScore += 1;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove 1 to §c§lRed §6§lScore")) {
					Vars.oneScore -= 1;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove 1 to §a§lGreen §6§lScore")) {
					Vars.twoScore -= 1;
					e.setCancelled(true);
				}
			}
		}

		// MANAGE MAP MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Map Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.MAP)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Forest Small size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("forest_small_OaTd", Tp.smallArenaE);
					API.placeStructure("forest_small_OdTa", Tp.smallArenaW);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Hill Small size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("hill_small_OaTd", Tp.smallArenaE);
					API.placeStructure("hill_small_OdTa", Tp.smallArenaW);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Trenchees Small size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("trenchees_small_OaTd", Tp.smallArenaE);
					API.placeStructure("trenchees_small_OdTa", Tp.smallArenaW);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Ruins Small size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("ruins_small_OaTd", Tp.smallArenaE);
					API.placeStructure("ruins_small_OdTa", Tp.smallArenaW);
				}
			}
			if (it.getType().equals(Material.MAP)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Forest Large size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("forest_large_OaTd", Tp.largeArenaE);
					API.placeStructure("forest_large_OdTa", Tp.largeArenaW);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Hill Large size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("hill_large_OaTd", Tp.largeArenaE);
					API.placeStructure("hill_large_OdTa", Tp.largeArenaW);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload Trenchees Large size")) {
					e.setCancelled(true);
					new Arena().clearArena();
					API.placeStructure("trenchees_large_OaTd", Tp.largeArenaE);
					API.placeStructure("trenchees_large_OdTa", Tp.largeArenaW);
				}
			}
			if (it.getType().equals(Material.RED_WOOL) || it.getType().equals(Material.LIME_WOOL)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload §c§lRed §6§lBase Attack")) {
					e.setCancelled(true);
					API.placeStructure("one_atk_base", Tp.oneAtkBase);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload §a§lGreen §6§lBase Attack")) {
					e.setCancelled(true);
					API.placeStructure("two_atk_base", Tp.twoAtkBase);
				}
			}
			if (it.getType().equals(Material.RED_BANNER) || it.getType().equals(Material.LIME_BANNER)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload §c§lRed §6§lFlag")) {
					e.setCancelled(true);
					new Flag().clearBanner();
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
						bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70,
								14, -15);
						color = DyeColor.RED;
					}
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
						bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70,
								14, -5);
						color = DyeColor.RED;
					}
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lReload §a§lGreen §6§lFlag")) {
					e.setCancelled(true);
					new Flag().clearBanner();
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
						bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70,
								14, -15);
						color = DyeColor.GREEN;
					}
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
						bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70,
								14, -5);
						color = DyeColor.GREEN;
					}
				}
				bannerLocBase.getBlock().setType(Material.WHITE_BANNER);
				//bannerLocBase.getBlock().setData((byte) 8, true);
				Banner bannerBlock = (Banner) bannerLocBase.getBlock().getState();
				bannerBlock.setBaseColor(color);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
			}
		}

		// MANAGE TIME MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Time Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.YELLOW_STAINED_GLASS_PANE) || it.getType().equals(Material.BLUE_STAINED_GLASS_PANE)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet Day")) {
					p.getWorld().setTime(6000);
					p.getWorld().setGameRuleValue("doDaylightCycle", "false");
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet Night")) {
					p.getWorld().setTime(18000);
					p.getWorld().setGameRuleValue("doDaylightCycle", "false");
					e.setCancelled(true);
				}
				if (it.getType().equals(Material.SUNFLOWER)
						&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet Sun Weather")) {
					p.getWorld().setStorm(false);
					p.getWorld().setThundering(false);
					p.getWorld().setWeatherDuration(2000000);
					e.setCancelled(true);
				}
				if (it.getType().equals(Material.WATER_BUCKET)
						&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet Rain Weather")) {
					p.getWorld().setStorm(true);
					p.getWorld().setThundering(true);
					p.getWorld().setWeatherDuration(2000000);
					e.setCancelled(true);
				}
			}
		}

		// ADMIN TELEPORT MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oTeleport Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.RED_BANNER) || it.getType().equals(Material.LIME_BANNER)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lTeleporters")
						|| it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lTeleporters")) {
					e.setCancelled(true);
				}
			}

			if (it.getType().equals(Material.OAK_DOOR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lHall")) {
				p.teleport(Tp.hall);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.STICKY_PISTON)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSettings Room")) {
				p.teleport(Tp.settings);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.WRITABLE_BOOK)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRules Room")) {
				p.teleport(Tp.rules);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.NAME_TAG)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSINE Logo")) {
				p.teleport(Tp.casterSlot);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.INK_SAC) || it.getType().equals(Material.RED_DYE) || it.getType().equals(Material.LIME_DYE)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lCaster Score Room")) {
					p.teleport(Tp.casterScRoomSlot);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lScore Room")) {
					p.teleport(Tp.oneScRoomSlot1);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lScore Room")) {
					p.teleport(Tp.twoScRoomSlot1);
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.CHEST)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lShop")) {
					p.teleport(Tp.oShop);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lShop")) {
					p.teleport(Tp.tShop);
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.COBBLESTONE_WALL)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lBase Defense")) {
					p.teleport(Tp.casterFigO);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lBase Defense")) {
					p.teleport(Tp.casterFigT);
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.DIAMOND_SWORD)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRed §6§lBase Attack")) {
					p.teleport(Tp.oAtk);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lGreen §6§lBase Attack")) {
					p.teleport(Tp.tAtk);
					e.setCancelled(true);
				}
			}
		}

		// ADMIN DEBUG MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oDebug Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.RED_STAINED_GLASS) || it.getType().equals(Material.LIME_STAINED_GLASS)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§cAdd/Remove PNameOne")) {
					if (!Vars.pNameTeamOne.containsKey(p.getName())) {
						Vars.pNameTeamOne.put(p.getName(), 1);
						p.sendMessage(p.getName() + " est ajouté à §cPNameOne");
					} else if (Vars.pNameTeamOne.containsKey(p.getName())) {
						Vars.pNameTeamOne.remove(p.getName());
						p.sendMessage(p.getName() + " est retiré de §cPNameOne");
					}
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§cAdd/Remove PNameTwo")) {
					if (!Vars.pNameTeamTwo.containsKey(p.getName())) {
						Vars.pNameTeamTwo.put(p.getName(), 1);
						p.sendMessage(p.getName() + " est ajouté à §aPNameTwo");
					} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
						Vars.pNameTeamTwo.remove(p.getName());
						p.sendMessage(p.getName() + " est retiré de §aPNameTwo");
					}
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§0Add/Remove PNameCaster")) {
					if (!Vars.pNameCaster.contains(p.getName())) {
						Vars.pNameCaster.add(p.getName());
						p.sendMessage(p.getName() + " est ajouté à §0PNameCaster");
					} else if (Vars.pNameCaster.contains(p.getName())) {
						Vars.pNameCaster.remove(p.getName());
						p.sendMessage(p.getName() + " est retiré de §0PNameCaster");
					}
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.ARMOR_STAND)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClear Holograms")) {
					if (Holograms.holoOneList.contains(Holograms.holoScoreOne)) {
						Holograms.holoScoreOne.hideAll(Holograms.holoOneList);
					}
					if (Holograms.holoCasterList.contains(Holograms.holoScoreOneCaster)) {
						Holograms.holoScoreOneCaster.hideAll(Holograms.holoCasterList);
					}
					if (Holograms.holoCasterList.contains(Holograms.holoScoreTwoCaster))
						Holograms.holoScoreTwoCaster.hideAll(Holograms.holoCasterList);
				}
				if (Holograms.holoTwoList.contains(Holograms.holoScoreTwo)) {
					Holograms.holoScoreTwo.hideAll(Holograms.holoTwoList);
				}
				if (Holograms.welcList.contains(Holograms.holoWelc)) {
					Holograms.holoWelc.hideAll(Holograms.welcList);
				}
				e.setCancelled(true);
			}
		}

		// SELECT PARTICLES MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oSelect Particles Victory Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.GOLDEN_HELMET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lCrown Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.crown)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.crown);
				}
				new PlayersJoins().crownTest();
				AdminManageCrownParticles menu = new AdminManageCrownParticles(36);
				menu.createManageCrownParticlesMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BARRIER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lStop Crown Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.crown)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.crown);
				}
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ELYTRA)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWings Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.wings)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.wings);
				}
				new PlayersJoins().wingsTest();
				AdminManageWingsParticles menu = new AdminManageWingsParticles(45);
				menu.createManageWingsParticlesMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BARRIER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lStop Wings Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.wings)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.wings);
				}
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.MAP)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lVictory Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.victory)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.victory);
				}
				new PlayersJoins().victoryTest();
				AdminManageVictoryParticles menu = new AdminManageVictoryParticles(36);
				menu.createManageVictoryParticlesMenu(p);
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BARRIER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lStop Victory Particles")) {
				if (Main.getInstance().getServer().getScheduler().isQueued(PlayersJoins.victory)) {
					Main.getInstance().getServer().getScheduler().cancelTask(PlayersJoins.victory);
				}
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.RED_DYE) || it.getType().equals(Material.LIME_DYE)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAdd 1 particle on Crown")) {
					Victory.crownPoints++;
					p.sendMessage("§0[§4SINE§0] §7§oParticle(s) on Crown = " + Victory.crownPoints);
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRemove 1 particle on Crown")) {
					Victory.crownPoints--;
					p.sendMessage("§0[§4SINE§0] §7§oParticle(s) on Crown = " + Victory.crownPoints);
					e.setCancelled(true);
				}
			}
		}

		// MANAGE CROWN PARTICLES MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Crown Particles Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.PLAYER_HEAD) || it.getType().equals(Material.DRAGON_HEAD) || it.getType().equals(Material.SKELETON_SKULL)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAngry Villager Particles")) {
					Victory.crownParticle = Particle.VILLAGER_ANGRY;
					Victory.crownPoints = 3;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDragon Breath Particles")) {
					Victory.crownParticle = Particle.DRAGON_BREATH;
					Victory.crownPoints = 10;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Mob Ambient Particles")) {
					Victory.crownParticle = Particle.SPELL_MOB_AMBIENT;
					Victory.crownPoints = 10;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.GHAST_TEAR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Bubble Particles")) {
				Victory.crownParticle = Particle.WATER_BUBBLE;
				Victory.crownPoints = 12;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.END_ROD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEnd Rod Particles")) {
				Victory.crownParticle = Particle.END_ROD;
				Victory.crownPoints = 24;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.TOTEM_OF_UNDYING)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTotem Particles")) {
				Victory.crownParticle = Particle.TOTEM;
				Victory.crownPoints = 24;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.APPLE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lHeart Particles")) {
				Victory.crownParticle = Particle.HEART;
				Victory.crownPoints = 3;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ENDER_EYE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPortal Particles")) {
				Victory.crownParticle = Particle.PORTAL;
				Victory.crownPoints = 11;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.REDSTONE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRedstone Particles")) {
				Victory.crownParticle = Particle.REDSTONE;
				Victory.crownPoints = 10;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.COAL)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSmoke Normal Particles")) {
				Victory.crownParticle = Particle.SMOKE_NORMAL;
				Victory.crownPoints = 11;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.MELON_SEEDS)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSuspend Depth Particles")) {
				Victory.crownParticle = Particle.SUSPENDED_DEPTH;
				Victory.crownPoints = 12;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.WATER_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Wake Particles")) {
				Victory.crownParticle = Particle.WATER_WAKE;
				Victory.crownPoints = 18;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BLAZE_POWDER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFlame Particles")) {
				Victory.crownParticle = Particle.FLAME;
				Victory.crownPoints = 11;
				e.setCancelled(true);
			}
		}

		// MANAGE WINGS PARTICLES MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Wings Particles Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.DRAGON_HEAD) || it.getType().equals(Material.SKELETON_SKULL)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDragon Breath Particles")) {
					Victory.wingsParticle = Particle.DRAGON_BREATH;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Mob Ambient Particles")) {
					Victory.wingsParticle = Particle.SPELL_MOB_AMBIENT;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.PRISMARINE_SHARD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lCrit Particles")) {
				Victory.wingsParticle = Particle.CRIT;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.NETHER_STAR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lCrit Magic Particles")) {
				Victory.wingsParticle = Particle.CRIT_MAGIC;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.GHAST_TEAR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Bubble Particles")) {
				Victory.wingsParticle = Particle.WATER_BUBBLE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ENCHANTING_TABLE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEnchantment Table Particles")) {
				Victory.wingsParticle = Particle.ENCHANTMENT_TABLE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.TNT)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lExplosion Normal Particles")) {
				Victory.wingsParticle = Particle.EXPLOSION_NORMAL;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.APPLE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lHeart Particles")) {
				Victory.wingsParticle = Particle.HEART;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ENDER_EYE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPortal Particles")) {
				Victory.wingsParticle = Particle.PORTAL;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.REDSTONE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRedstone Particles")) {
				Victory.wingsParticle = Particle.REDSTONE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.COAL)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSmoke Normal Particles")) {
				Victory.wingsParticle = Particle.SMOKE_NORMAL;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.ENCHANTED_BOOK)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Particles")) {
				Victory.wingsParticle = Particle.SPELL;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.EXPERIENCE_BOTTLE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Instant Particles")) {
				Victory.wingsParticle = Particle.SPELL_INSTANT;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.SPLASH_POTION)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Witch Particles")) {
				Victory.wingsParticle = Particle.SPELL_WITCH;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.MELON_SEEDS)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSuspend Depth Particles")) {
				Victory.wingsParticle = Particle.SUSPENDED_DEPTH;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.DIAMOND_AXE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSweep Attack Particles")) {
				Victory.wingsParticle = Particle.SWEEP_ATTACK;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.WATER_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Wake Particles")) {
				Victory.wingsParticle = Particle.WATER_WAKE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BLAZE_POWDER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFlame Particles")) {
				Victory.wingsParticle = Particle.FLAME;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.TOTEM_OF_UNDYING)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTotem Particles")) {
				Victory.wingsParticle = Particle.TOTEM;
				e.setCancelled(true);
			}
		}

		// MANAGE VICTORY PARTICLES MENU
		if (e.getView().getTitle().equalsIgnoreCase("§5§oManage Victory Particles Menu") && inv != null) {

			if (it == null || it.getType() == null)
				return;

			if (it.getType().equals(Material.DRAGON_HEAD) || it.getType().equals(Material.SKELETON_SKULL)) {
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDragon Breath Particles")) {
					Victory.victoryParticle = Particle.DRAGON_BREATH;
					e.setCancelled(true);
				}
				if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpell Mob Ambient Particles")) {
					Victory.victoryParticle = Particle.SPELL_MOB_AMBIENT;
					e.setCancelled(true);
				}
			}
			if (it.getType().equals(Material.GHAST_TEAR)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Bubble Particles")) {
				Victory.victoryParticle = Particle.WATER_BUBBLE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.END_ROD)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEnd Rod Particles")) {
				Victory.victoryParticle = Particle.END_ROD;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.TOTEM_OF_UNDYING)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTotem Particles")) {
				Victory.victoryParticle = Particle.TOTEM;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.REDSTONE)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRedstone Particles")) {
				Victory.victoryParticle = Particle.REDSTONE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.COAL)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSmoke Normal Particles")) {
				Victory.victoryParticle = Particle.SMOKE_NORMAL;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.MELON_SEEDS)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSuspend Depth Particles")) {
				Victory.victoryParticle = Particle.SUSPENDED_DEPTH;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.WATER_BUCKET)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lWater Wake Particles")) {
				Victory.victoryParticle = Particle.WATER_WAKE;
				e.setCancelled(true);
			}
			if (it.getType().equals(Material.BLAZE_POWDER)
					&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFlame Particles")) {
				Victory.victoryParticle = Particle.FLAME;
				e.setCancelled(true);
			}
		}

		if (it.getType().equals(Material.CHEST)) {
			if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRed and Green purchases")) {
				CasterMenu menu = new CasterMenu(54, "");
				menu.createCasterChestMenu(p, "");
			}
		}
		if (it.getType().equals(Material.ENDER_CHEST)) {
			if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPrevious Red and Green purchases")) {
				CasterMenu menu = new CasterMenu(54, "yes");
				menu.createCasterChestMenu(p, "yes");
			}
		}
		if (it.getType().equals(Material.REDSTONE)) {
			if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTest Holograms")) {

				e.setCancelled(true);

				if (test == 0) {
					Bukkit.broadcastMessage("Set AS " + test);

					new PlayersJoins().holoScore();

					test = 1;
				} else if (test == 1) {
					Bukkit.broadcastMessage("Delete AS " + test);

					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "kill @e[type=armor_stand]";
					Bukkit.dispatchCommand(console, command);

					test = 0;
				}
			}
		}
	}
}