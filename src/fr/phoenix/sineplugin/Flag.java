package fr.phoenix.sineplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.phoenix.sineplugin.phases.Countdown;
import fr.phoenix.sineplugin.phases.Fight;
import fr.phoenix.sineplugin.phases.Victory;
import fr.phoenix.sineplugin.playerProfile.PlayerStats;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;

public class Flag {

	public static Location bannerLocBase;
	private DyeColor color;
	public static int captur, leftCaptur;
	public static int oneTakeFlag, twoTakeFlag;

	@SuppressWarnings("deprecation")
	public void createBanner() {
		if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
			if (Vars.whoBegin.equals("OaTd")) {
				bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70, 14,
						-15);
				color = DyeColor.GREEN;
			}
			if (Vars.whoBegin.equals("OdTa")) {
				bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14,
						-15);
				color = DyeColor.RED;
			}
		}
		if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
			if (Vars.whoBegin.equals("OaTd")) {
				bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70, 14,
						-5);
				color = DyeColor.GREEN;
			}
			if (Vars.whoBegin.equals("OdTa")) {
				bannerLocBase = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14, -5);
				color = DyeColor.RED;
			}
		}
	bannerLocBase.getBlock().setType(Material.WHITE_BANNER);
		bannerLocBase.getBlock().setData((byte) 8, true);
		Banner bannerBlock = (Banner) bannerLocBase.getBlock().getState();
		bannerBlock.setBaseColor(color);
		bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
		bannerBlock.update(true);
	}

	public void clearBanner() {

		Location bannerLocSmallOne = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14,
				-15);
		Location bannerLocSmallTwo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70,
				14, -15);
		Location bannerLocLargeOne = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14,
				-5);
		Location bannerLocLargeTwo = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70,
				14, -5);

		ArrayList<Location> bannerLocs = new ArrayList<Location>(
				Arrays.asList(bannerLocSmallOne, bannerLocSmallTwo, bannerLocLargeOne, bannerLocLargeTwo));

		for (Location loc : bannerLocs) {
			if (loc.getBlock().getType().equals(Material.RED_BANNER) || loc.getBlock().getType().equals(Material.LIME_BANNER)) {
				loc.getBlock().setType(Material.AIR);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void onFlagHolderDeath(Player p, Player k) {

		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getType().equals(Material.RED_BANNER)) {

				if (Main.getInstance().getServer().getScheduler().isQueued(oneTakeFlag)) {
					Main.getInstance().getServer().getScheduler().cancelTask(oneTakeFlag);
					for (Player pl : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
					}
				}
				if (Main.getInstance().getServer().getScheduler().isQueued(twoTakeFlag)) {
					Main.getInstance().getServer().getScheduler().cancelTask(twoTakeFlag);
					for (Player pl : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer
								.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
					}
				}
				for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld())
						.getApplicableRegions(p.getLocation())) {
					if (r.getId().equalsIgnoreCase("oneflagcapturzone")
							|| r.getId().equalsIgnoreCase("twoflagcapturzone")) {
						Main.getInstance().getServer().getScheduler().cancelTask(captur);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							IChatBaseComponent barmsg = ChatSerializer
									.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', "") + "\"}");
							PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
							((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
						}
					}
				}

				replaceHelmet(p);

				Random r = new Random();

				int x = p.getLocation().getBlockX() + r.nextInt(3);
				int y = p.getLocation().getBlockY();
				int z = p.getLocation().getBlockZ() + r.nextInt(3);

				Location dropBanner = new Location(p.getWorld(), x, y, z);
				dropBanner.getBlock().setType(Material.WHITE_BANNER);
				dropBanner.getBlock().setData((byte) 8, true);
				Banner bannerM = (Banner) dropBanner.getBlock().getState();
				if (Vars.whoBegin.equals("OaTd"))
					bannerM.setBaseColor(DyeColor.GREEN);
				if (Vars.whoBegin.equals("OdTa"))
					bannerM.setBaseColor(DyeColor.RED);
				bannerM.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerM.update(true);

				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					if (Vars.pNameTeamTwo.containsKey(k.getName())) {
						Vars.pNameFlagHolder.remove(p.getName());
						new PlayerStats().updateTotalFlagDrop(k);
						Vars.twoFlagDrop++;
						Vars.totalTwoDrop++;
					}
					Bukkit.broadcastMessage("\u2690 &6§lFlag has been dropped by §c§l" + p.getName() + "§6§l thanks to "
							+ Fight.killer + " §f§l! \u2690");

				} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					if (Vars.pNameTeamOne.containsKey(k.getName())) {
						Vars.pNameFlagHolder.remove(p.getName());
						new PlayerStats().updateTotalFlagDrop(k);
						Vars.oneFlagDrop++;
						Vars.totalOneDrop++;
					}
					Bukkit.broadcastMessage("\u2690 &6§lFlag has been dropped by §a§l" + p.getName() + "§6§l thanks to "
							+ Fight.killer + " §f§l! \u2690");
				}
			}
		}
	}

	public void oneTakeFlagTask(Player p) {

		oneTakeFlag = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			int n = 0;

			public void run() {

				if (n == 0 || n % 6 == 0) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						Fight.barmsg = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&',
										"§0§l\u2691 §4§l" + p.getName() + "&6§l took the flag §f§l! §0§l\u2691")
								+ "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(Fight.barmsg);
						((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
					}
				}
				n++;
			}
		}, 10, 10);
	}

	public void twoTakeFlagTask(Player p) {

		twoTakeFlag = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			int n = 0;

			public void run() {

				if (n == 0 || n % 6 == 0) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						Fight.barmsg = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&',
										"§0§l\u2691 §a§l" + p.getName() + "&6§l took the flag §f§l! §0§l\u2691")
								+ "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(Fight.barmsg);
						((CraftPlayer) pl).getHandle().playerConnection.sendPacket(bar);
					}
				}
				n++;
			}
		}, 10, 10);
	}

	public void onFlagCapturing(int cd) {

		leftCaptur = cd;

		captur = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			int nB = 0;
			int count = (cd / 2) + 1;

			public void run() {

				if (leftCaptur % 2 == 0) {
					count--;
					nB = 10 - count;

					String w = Vars.repeat(count, "§f§l\u2B1C");
					String b = Vars.repeat(nB, "§0§l\u2B1B");

					for (Player p : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&6§lFlag capture progress... : " + b + w)
								+ "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
					}
				}

				if (leftCaptur == 0) {
					Main.getInstance().getServer().getScheduler().cancelTask(captur);
					if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.lastTry)) {
						Main.getInstance().getServer().getScheduler().cancelTask(Countdown.lastTry);
						if (Countdown.cdLastTry.isVisible()) {
							Countdown.cdLastTry.removeAll();
						}
					if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.blitz)) {
						Main.getInstance().getServer().getScheduler().cancelTask(Countdown.blitz);
						if (Countdown.cdBlitzBar.isVisible()) {
							Countdown.cdBlitzBar.removeAll();
						}
						if (Vars.whoBegin.equals("OaTd")) {
							Vars.oneBlitz = 1;
						}
						if (Vars.whoBegin.equals("OdTa")) {
							Vars.twoBlitz = 1;
						}
					}
						for (String pN : Vars.pNameCaster) {
							Player pC = Bukkit.getPlayerExact(pN);
							pC.sendMessage(
									"§0[§4SINE§0] §7§oTime left on Last Try : " + Countdown.leftLastTry + " second(s)");
						}
					}
					leftCaptur = -5;
					for (Player p : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent barmsg = ChatSerializer.a("{\"text\":\""
								+ ChatColor.translateAlternateColorCodes('&', "&6§lFlag was captured !") + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
					}
					new Flag().onFlagCaptured();
				}
				leftCaptur--;
			}
		}, 10, 10);
	}

	public void onFlagCaptured() {

		if (Vars.whoBegin.equals("OaTd")) {
			Location oneFlagSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -68, 14,
					-88);
			Location oneFlagSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -70, 14,
					-88);
			Location oneFlagSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), -72, 14,
					-88);

			if (!oneFlagSlot1.getBlock().getType().equals(Material.RED_BANNER) || !oneFlagSlot1.getBlock().getType().equals(Material.LIME_BANNER)) {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
				}
				oneFlagSlot1.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) oneFlagSlot1.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.GREEN);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.oneScore++;
				Vars.oneFlagCaptur++;
				Vars.totalOneCaptur++;
				Vars.oneWinRd = 1;
				Vars.totalOneWinRd++;
				Vars.twoLoseRd = 1;
				Vars.totalTwoLoseRd++;
				new Victory().updateScore("one");
				
			} else if (!oneFlagSlot2.getBlock().getType().equals(Material.RED_BANNER) || !oneFlagSlot2.getBlock().getType().equals(Material.LIME_BANNER)) {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
				}
				oneFlagSlot2.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) oneFlagSlot2.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.GREEN);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.oneScore++;
				Vars.oneFlagCaptur++;
				Vars.totalOneCaptur++;
				Vars.oneWinRd = 1;
				Vars.totalOneWinRd++;
				Vars.twoLoseRd = 1;
				Vars.totalTwoLoseRd++;
				new Victory().updateScore("one");
				
			} else {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
				}
				oneFlagSlot3.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) oneFlagSlot3.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.GREEN);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.oneScore++;
				Vars.oneFlagCaptur++;
				Vars.totalOneCaptur++;
				Vars.oneWinRd = 1;
				Vars.totalOneWinRd++;
				Vars.twoLoseRd = 1;
				Vars.totalTwoLoseRd++;
				new Victory().updateScore("one");
			}
		} else if (Vars.whoBegin.equals("OdTa")) {
			Location twoFlagSlot1 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 72, 14,
					-88);
			Location twoFlagSlot2 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 70, 14,
					-88);
			Location twoFlagSlot3 = new Location(Bukkit.getWorld(Files.setsNameConfig.getString("world.name")), 68, 14,
					-88);

			if (!twoFlagSlot1.getBlock().getType().equals(Material.RED_BANNER) || !twoFlagSlot1.getBlock().getType().equals(Material.LIME_BANNER)) {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
					Vars.pNameFlagHolder.remove(p.getName());
				}
				twoFlagSlot1.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) twoFlagSlot1.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.RED);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.twoScore++;
				Vars.twoFlagCaptur++;
				Vars.totalTwoCaptur++;
				Vars.twoWinRd = 1;
				Vars.totalTwoWinRd++;
				Vars.oneLoseRd = 1;
				Vars.totalOneLoseRd++;
				new Victory().updateScore("two");
			} else if (!twoFlagSlot2.getBlock().getType().equals(Material.RED_BANNER) || !twoFlagSlot2.getBlock().getType().equals(Material.LIME_BANNER)) {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
					Vars.pNameFlagHolder.remove(p.getName());
				}
				twoFlagSlot2.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) twoFlagSlot2.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.RED);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.twoScore++;
				Vars.twoFlagCaptur++;
				Vars.totalTwoCaptur++;
				Vars.twoWinRd = 1;
				Vars.totalTwoWinRd++;
				Vars.oneLoseRd = 1;
				Vars.totalOneLoseRd++;
				new Victory().updateScore("two");
			} else {
				for (String pN : Vars.pNameFlagHolder) {
					Player p = Bukkit.getPlayerExact(pN);
					replaceHelmet(p);
					Vars.pNameFlagHolder.remove(p.getName());
				}
				twoFlagSlot3.getBlock().setType(Material.WHITE_BANNER);
				Banner bannerBlock = (Banner) twoFlagSlot3.getBlock().getState();
				bannerBlock.setBaseColor(DyeColor.RED);
				bannerBlock.addPattern(new Pattern(DyeColor.YELLOW, PatternType.BORDER));
				bannerBlock.update(true);
				Vars.twoScore++;
				Vars.twoFlagCaptur++;
				Vars.totalTwoCaptur++;
				Vars.twoWinRd = 1;
				Vars.totalTwoWinRd++;
				Vars.oneLoseRd = 1;
				Vars.totalOneLoseRd++;
				new Victory().updateScore("two");
			}
		}
	}

	public void replaceHelmet(Player p) {

		ItemStack bestHelmet = null;

		ItemStack[] pInv = p.getInventory().getContents();
		for (ItemStack item : pInv) {
			if (item != null) {
				if (item.getType().equals(Material.DIAMOND_HELMET)) {
					if (bestHelmet == null || bestHelmet.getType().equals(Material.LEATHER_HELMET)
							|| bestHelmet.getType().equals(Material.IRON_HELMET))
						bestHelmet = item;
				} else if (item.getType().equals(Material.IRON_HELMET)) {
					if (bestHelmet == null || bestHelmet.getType().equals(Material.LEATHER_HELMET))
						bestHelmet = item;
				} else if (item.getType().equals(Material.LEATHER_HELMET)) {
					if (bestHelmet == null)
						bestHelmet = item;
				} else {
					p.getInventory().clear(103);
				}
			}
		}
		p.getInventory().remove(bestHelmet);
		p.getInventory().setHelmet(bestHelmet);
	}
}
