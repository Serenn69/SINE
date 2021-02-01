package fr.phoenix.sineplugin.phases;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Flag;
import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class Fight implements Listener {

	ArrayList<Material> swords = new ArrayList<>(
			Arrays.asList(Material.STONE_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD));
	ArrayList<Material> axes = new ArrayList<>(
			Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE));
	ArrayList<Material> picks = new ArrayList<>(Arrays.asList(Material.WOODEN_PICKAXE, Material.STONE_PICKAXE,
			Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE));

	public static String killer;
	private static ItemStack helmet;
	public static int temp;
	public static IChatBaseComponent barmsg = null;

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {

		Player p = (Player) e.getEntity();
		Player k = p.getKiller();

		if (k != null) {
			if (Vars.pNameTeamOne.containsKey(k.getName())) {
				killer = "§4§l" + k.getName();
			}
			if (Vars.pNameTeamTwo.containsKey(k.getName())) {
				killer = "§a§l" + k.getName();
			}
		}

		// Update Tracker on Player death

		if (Vars.pNameTeamOne.containsKey(p.getName())) {

			if (Vars.trackerOne.getScore(p.getName()) != null) {

				Score scOne = Vars.trackerOne.getScore(p.getName());
				int iOne = scOne.getScore();
				Vars.sineBoardOne.resetScores(p.getName());
				Vars.trackerOne.getScore("§7§o§m" + p.getName()).setScore(iOne);
			}
			if (Vars.trackerCaster != null && Vars.trackerCaster.getScore(p.getName()) != null) {

				Score scCaster = Vars.trackerCaster.getScore(p.getName());
				int iCaster = scCaster.getScore();
				Vars.sineBoardCaster.resetScores(p.getName());
				Vars.trackerCaster.getScore("§7§o§m" + p.getName()).setScore(iCaster);
			}
		}

		if (Vars.pNameTeamTwo.containsKey(p.getName())) {

			if (Vars.trackerTwo.getScore(p.getName()) != null) {

				Score scTwo = Vars.trackerTwo.getScore(p.getName());
				int iTwo = scTwo.getScore();
				Vars.sineBoardTwo.resetScores(p.getName());
				Vars.trackerTwo.getScore("§7§o§m" + p.getName()).setScore(iTwo);
			}
			if (Vars.trackerCaster != null && Vars.trackerCaster.getScore(p.getName()) != null) {

				Score scCaster = Vars.trackerCaster.getScore(p.getName());
				int iCaster = scCaster.getScore();
				Vars.sineBoardCaster.resetScores(p.getName());
				Vars.trackerCaster.getScore("§7§o§m" + p.getName()).setScore(iCaster);
			}
		}

		// Update Player Profile

		if (Vars.pNameTeamOne.containsKey(k.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {
			// Add Kills
			new PlayerStats().updateTotalPlayersKilled(k);
			Vars.oneKills++;
			Vars.totalOneKills++;
			Vars.twoPerfect = 0;
			// Add Deaths
			new PlayerStats().updateTotalDeaths(p);
			Vars.twoDeaths++;
			Vars.totalTwoDeaths++;
			
		} else if (Vars.pNameTeamTwo.containsKey(k.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {
			// Add Kills
			new PlayerStats().updateTotalPlayersKilled(k);
			Vars.twoKills++;
			Vars.totalTwoKills++;
			Vars.onePerfect = 0;
			// Add Deaths
			new PlayerStats().updateTotalDeaths(p);
			Vars.oneDeaths++;
			Vars.totalOneDeaths++;
			
		} else if (Vars.pNameTeamOne.containsKey(k.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {
			// Add Mate Kills
			new PlayerStats().updateTotalMateKills(k);
			Vars.oneMateKills++;
			Vars.totalOneMates++;
			Vars.onePerfect = 0;
			
		} else if (Vars.pNameTeamTwo.containsKey(k.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {
			// Add Mate Kills
			new PlayerStats().updateTotalMateKills(k);
			Vars.twoMateKills++;
			Vars.totalTwoMates++;
			Vars.twoPerfect = 0;
		}

		if (Vars.pNameFlagHolder != null) {
			if (Vars.pNameFlagHolder.contains(p.getName())) {
				new Flag().onFlagHolderDeath(p, k);
			}
		}

		if (Vars.twoKills == Vars.pNameTeamOne.size() && Vars.oneKills < Vars.pNameTeamTwo.size()) {
			if (Vars.whoBegin.equals("OaTd")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
							+ ChatColor.translateAlternateColorCodes('&', "&f§oAll §c§oAttackers §f§o are dead !")
							+ "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.blitz)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Countdown.blitz);
					if (Countdown.cdBlitzBar.isVisible()) {
						Countdown.cdBlitzBar.removeAll();
					}
					Vars.oneBlitz = 0;
				}
				Vars.oneLoseRd = 1;
				Vars.totalOneLoseRd++;
				Vars.twoWinRd = 1;
				Vars.totalTwoWinRd++;
				new Victory().updateScore("two");
			}
			if (Vars.whoBegin.equals("OdTa")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
							+ ChatColor.translateAlternateColorCodes('&', "&f§oAll §c§oDefensers §f§o are dead !")
							+ "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				new Countdown().allDefenseDeath(31, "two");
			}
		} else if (Vars.oneKills == Vars.pNameTeamTwo.size() && Vars.twoKills < Vars.pNameTeamOne.size()) {
			if (Vars.whoBegin.equals("OaTd")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
							+ ChatColor.translateAlternateColorCodes('&', "&f§oAll §a§oDefensers §f§o are dead !")
							+ "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				new Countdown().allDefenseDeath(31, "one");
			}
			if (Vars.whoBegin.equals("OdTa")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					PlayerConnection connection = ((CraftPlayer) pl).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\""
							+ ChatColor.translateAlternateColorCodes('&', "&f§oAll §a§oAttackers §f§o are dead !")
							+ "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.blitz)) {
					Main.getInstance().getServer().getScheduler().cancelTask(Countdown.blitz);
					if (Countdown.cdBlitzBar.isVisible()) {
						Countdown.cdBlitzBar.removeAll();
					}
					Vars.twoBlitz = 0;
				}
				Vars.twoLoseRd = 1;
				Vars.totalTwoLoseRd++;
				Vars.oneWinRd = 1;
				Vars.totalOneWinRd++;
				new Victory().updateScore("one");
			}
		}
	}

	public void beginFightMsg() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Vars.whoBegin.equals("OaTd")) {

				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a(
							"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&4§oAttack §f§o!") + "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a(
							"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&a§oDefend §f§o!") + "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
			}
			if (Vars.whoBegin.equals("OdTa")) {

				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a(
							"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&4§oDefend §f§o!") + "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					IChatBaseComponent Title = ChatSerializer
							.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
					IChatBaseComponent subTitle = ChatSerializer.a(
							"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "&a§oAttack §f§o!") + "\"}");
					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, Title, 1, 2, 1);
					PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
					connection.sendPacket(title);
					connection.sendPacket(subtitle);
				}
			}
		}
	}

	@EventHandler
	public void onPlaceBlockOutBase(BlockPlaceEvent e) {

		Block b = e.getBlockPlaced();
		Player p = e.getPlayer();

		if (Vars.canPlaceOn.contains(b.getType())) {
			if (Vars.whoBegin.equals("OaTd")) {
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {

						if (b.getLocation().getBlockX() > -63 || b.getLocation().getBlockX() < -77
								|| b.getLocation().getBlockZ() > -8 || b.getLocation().getBlockZ() < -22) {
							if (!p.isOp()) {
								e.setCancelled(true);
								p.sendMessage("§0[§4SINE§0] §7§oYou can't build outside the defense base");
							}
						}
						if (b.getLocation().getBlockX() > -72 && b.getLocation().getBlockX() < -68
								&& b.getLocation().getBlockZ() > -17 && b.getLocation().getBlockZ() < -13) {
							if (!p.isOp()) {
								e.setCancelled(true);
								p.sendMessage("§0[§4SINE§0] §7§oYou can't build inside the flag area");
							}
						}
					}
					if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {

						if (b.getLocation().getBlockX() > -59 || b.getLocation().getBlockX() < -81
								|| b.getLocation().getBlockZ() > 6 || b.getLocation().getBlockZ() < -16) {
							if (!p.isOp()) {
								e.setCancelled(true);
								p.sendMessage("§0[§4SINE§0] §7§oYou can't build outside the defense base");
							}
						}
						if (b.getLocation().getBlockX() > -72 && b.getLocation().getBlockX() < -68
								&& b.getLocation().getBlockZ() > -7 && b.getLocation().getBlockZ() < -3) {
							if (!p.isOp()) {
								e.setCancelled(true);
								p.sendMessage("§0[§4SINE§0] §7§oYou can't build inside the flag area");
							}
						}
					}
				}
				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
					RegionManager regions = container.get((World) p.getWorld());
					for (ProtectedRegion r : regions.getRegion(p.getWorld())
							.getApplicableRegions(p.getLocation())) {
						if (r.getId().equalsIgnoreCase("onelargezone") || r.getId().equalsIgnoreCase("onesmallzone")) {
							e.setCancelled(true);
							p.sendMessage("§0[§4SINE§0] §7§oWhen you are attacker you're not allowed to build wall blocks");
						}
					}
				}
			}
		}
		if (Vars.whoBegin.equals("OdTa")) {
			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {

					if (b.getLocation().getBlockX() > 77 || b.getLocation().getBlockX() < 63
							|| b.getLocation().getBlockZ() > -8 || b.getLocation().getBlockZ() < -22) {
						if (!p.isOp()) {
							e.setCancelled(true);
							p.sendMessage("§0[§4SINE§0] §7§oYou can't build outside the defense base");
						}
					}
					if (b.getLocation().getBlockX() > 68 && b.getLocation().getBlockX() < 72
							&& b.getLocation().getBlockZ() > -17 && b.getLocation().getBlockZ() < -13) {
						if (!p.isOp()) {
							e.setCancelled(true);
							p.sendMessage("§0[§4SINE§0] §7§oYou can't build inside the flag area");
						}
					}
				}
				if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {

					if (b.getLocation().getBlockX() > 81 || b.getLocation().getBlockX() < 59
							|| b.getLocation().getBlockZ() > 6 || b.getLocation().getBlockZ() < -16) {
						if (!p.isOp()) {
							e.setCancelled(true);
							p.sendMessage("§0[§4SINE§0] §7§oYou can't build outside the defense base");
						}
					}
					if (b.getLocation().getBlockX() > 68 && b.getLocation().getBlockX() < 72
							&& b.getLocation().getBlockZ() > -7 && b.getLocation().getBlockZ() < -3) {
						if (!p.isOp()) {
							e.setCancelled(true);
							p.sendMessage("§0[§4SINE§0] §7§oYou can't build inside the flag area");
						}
					}
				}
			}
			if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld())
						.getApplicableRegions(p.getLocation())) {
					if (r.getId().equalsIgnoreCase("twolargezone") || r.getId().equalsIgnoreCase("twosmallzone")) {
						e.setCancelled(true);
						p.sendMessage("§0[§4SINE§0] §7§oWhen you are attacker you're not allowed to build wall blocks");
					}
				}
			} else if (Vars.whoBegin == null) {
			}
		}
		if (b.getType().equals(Material.COMMAND_BLOCK)) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreakBanner(BlockBreakEvent e) {

		Player p = e.getPlayer();
		Block b = e.getBlock();

		Location bannerLoc = new Location(p.getWorld(), b.getLocation().getX(), b.getLocation().getY(),
				b.getLocation().getZ());

		if (b.getType().equals(Material.RED_WALL_BANNER) || b.getType().equals(Material.LIME_WALL_BANNER)) {

			if (Vars.whoBegin.equals("OaTd")) {
				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					e.setCancelled(true);
					bannerLoc.getBlock().setType(Material.AIR);
					Vars.pNameFlagHolder.add(p.getName());
					new PlayerStats().updateTotalFlagTaken(p);
					Vars.oneFlagTaken++;
					Vars.totalOneTaken++;
					new Flag().oneTakeFlagTask(p);

					helmet = p.getInventory().getHelmet();
					if (p.getInventory().getHelmet() != null) {
						p.getInventory().addItem(helmet);
					}
					ItemStack banner = new ItemStack(Material.LIME_BANNER, 1);
					BannerMeta bannerM = (BannerMeta) banner.getItemMeta();
					bannerM.setBaseColor(DyeColor.GREEN);
					bannerM.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
					bannerM.setDisplayName("§a§oGREEN Flag §f§o!");
					banner.setItemMeta(bannerM);
					p.getInventory().setHelmet(banner);
					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 0, true, false));

				} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					Location bannerLocBaseTwoS = new Location(
							Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70, 14, -15);
					Location bannerLocBaseTwoL = new Location(
							Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70, 14, -5);

					if (b.getLocation().equals(bannerLocBaseTwoS) || b.getLocation().equals(bannerLocBaseTwoL)) {
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						bannerLoc.getBlock().setType(Material.AIR);
						new PlayerStats().updateTotalFlagBack(p);
						Vars.twoFlagBack++;
						Vars.totalTwoBack++;
						new Flag().createBanner();
						Bukkit.broadcastMessage("\u2690 §a§l" + p.getName() + "&6§l returned the flag §f§l! \u2690");

					}
				}
			} else if (Vars.whoBegin.equals("OdTa")) {
				if (Vars.pNameTeamOne.containsKey(p.getName())) {

					Location bannerLocBaseOneS = new Location(
							Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14, -15);
					Location bannerLocBaseOneL = new Location(
							Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14, -5);

					if (b.getLocation().equals(bannerLocBaseOneS) || b.getLocation().equals(bannerLocBaseOneL)) {
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						bannerLoc.getBlock().setType(Material.AIR);
						new PlayerStats().updateTotalFlagBack(p);
						Vars.oneFlagBack++;
						Vars.totalOneBack++;
						new Flag().createBanner();

						Main.getInstance().getServer().getScheduler().cancelTask(temp);
						Bukkit.broadcastMessage("\u2690 §c§l" + p.getName() + "&6§l returned the flag §f§l! \u2690");
					}
				} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					e.setCancelled(true);
					bannerLoc.getBlock().setType(Material.AIR);
					Vars.pNameFlagHolder.add(p.getName());
					new PlayerStats().updateTotalFlagTaken(p);
					Vars.twoFlagTaken++;
					Vars.totalTwoTaken++;
					new Flag().twoTakeFlagTask(p);

					helmet = p.getInventory().getHelmet();
					if (p.getInventory().getHelmet() != null) {
						p.getInventory().addItem(helmet);
					}
					ItemStack banner = new ItemStack(Material.RED_BANNER, 1);
					BannerMeta bannerM = (BannerMeta) banner.getItemMeta();
					bannerM.setBaseColor(DyeColor.RED);
					bannerM.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
					bannerM.setDisplayName("§4§oRED Flag §f§o!");
					banner.setItemMeta(bannerM);
					p.getInventory().setHelmet(banner);
					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 0, true, false));
				}
			}
		}
	}

	@EventHandler
	public void enterFlagCapturZone(RegionEnterEvent e) {

		Player p = e.getPlayer();

		if (e.getRegion().getId().equalsIgnoreCase("oneflagcapturzone")) {
			if (Vars.pNameFlagHolder != null) {
				if (Vars.pNameFlagHolder.contains(p.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {
					if (p.getHealth() > 0) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.oneTakeFlag);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							IChatBaseComponent barmsg = ChatSerializer
									.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
							PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
							((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
						}
						new Flag().onFlagCapturing(20);
					}
				}
			}
		}
		if (e.getRegion().getId().equalsIgnoreCase("twoflagcapturzone")) {
			if (Vars.pNameFlagHolder != null) {
				if (Vars.pNameFlagHolder.contains(p.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {
					if (p.getHealth() > 0) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.twoTakeFlag);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							IChatBaseComponent barmsg = ChatSerializer
									.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
							PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
							((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
						}
						new Flag().onFlagCapturing(20);
					}
				}
			}
		}
	}

	@EventHandler
	public void leaveFlagCapturZone(RegionLeaveEvent e) {

		Player p = e.getPlayer();

		if (e.getRegion().getId().equalsIgnoreCase("oneflagcapturzone")) {
			if (Vars.pNameFlagHolder != null) {
				if (Vars.pNameFlagHolder.contains(p.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {
					if (p.getHealth() > 0) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.captur);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							IChatBaseComponent barmsg = ChatSerializer
									.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
							PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
							((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
						}
						new Flag().oneTakeFlagTask(p);
					}
				}
			}
		}
		if (e.getRegion().getId().equalsIgnoreCase("twoflagcapturzone")) {
			if (Vars.pNameFlagHolder != null) {
				if (Vars.pNameFlagHolder.contains(p.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {
					if (p.getHealth() > 0) {
						Main.getInstance().getServer().getScheduler().cancelTask(Flag.captur);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							IChatBaseComponent barmsg = ChatSerializer
									.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
							PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
							((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
						}
						new Flag().twoTakeFlagTask(p);
					}
				}
			}
		}

	}

	@EventHandler
	public void freezePlayers(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();

		if (Vars.freeze == 1) {
			if (Vars.pNameTeamOne.containsKey(p.getName()) || Vars.pNameTeamTwo.containsKey(p.getName())) {

				double x = Math.floor(from.getX());
				double z = Math.floor(from.getZ());
				if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
					x += .5;
					z += .5;
					p.teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
				}
			}
		}	
	}

	@EventHandler
	public void noDmgPlayers(EntityDamageEvent e) {

		Player p = (Player) e.getEntity();

		if (Vars.freeze == 1) {
			if (Vars.pNameTeamOne.containsKey(p.getName()) || Vars.pNameTeamTwo.containsKey(p.getName())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void noEnderPearl(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if (Vars.pNameFlagHolder.contains(p.getName())) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
					|| e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
					e.setCancelled(true);
				}
			}
		}
		if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
			if (p.getLocation().getBlockX() > 41 && p.getLocation().getBlockX() < 99 && p.getLocation().getBlockY() > 7
					&& p.getLocation().getBlockY() < 33 && p.getLocation().getBlockZ() > -90
					&& p.getLocation().getBlockZ() < 14) {

			} else {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
						|| e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
						e.setCancelled(true);
					}
				}
			}
			if (p.getLocation().getBlockX() > -99 && p.getLocation().getBlockX() < -41
					&& p.getLocation().getBlockY() > 7 && p.getLocation().getBlockY() < 33
					&& p.getLocation().getBlockZ() > -90 && p.getLocation().getBlockZ() < 14) {

			} else {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
						|| e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
						e.setCancelled(true);
					}
				}
			}
		}
		if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
			if (p.getLocation().getBlockX() > 31 && p.getLocation().getBlockX() < 109 && p.getLocation().getBlockY() > 7
					&& p.getLocation().getBlockY() < 33 && p.getLocation().getBlockZ() > -90
					&& p.getLocation().getBlockZ() < 34) {

			} else {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
						|| e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
						e.setCancelled(true);
					}
				}
			}
			if (p.getLocation().getBlockX() > -109 && p.getLocation().getBlockX() < -31
					&& p.getLocation().getBlockY() > 7 && p.getLocation().getBlockY() < 33
					&& p.getLocation().getBlockZ() > -90 && p.getLocation().getBlockZ() < 34) {

			} else {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
						|| e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void noEmptyBucket(PlayerBucketEmptyEvent e) {

		Player p = e.getPlayer();

		ItemStack[] pInv = p.getInventory().getContents();

		for (ItemStack it : pInv) {
			if (it.getType().equals(Material.BUCKET)) {
				it.setAmount(0);
			}
		}
	}
}