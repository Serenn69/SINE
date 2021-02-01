package fr.phoenix.sineplugin;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Beacons implements Listener {

	public static int runOneStrTask, runOneSwiftTask, runOneProtTask, runOneRegenTask;
	public static int runTwoStrTask, runTwoSwiftTask, runTwoProtTask, runTwoRegenTask;
	public static int oneStrBuff, oneSwiftBuff, oneProtBuff, oneRegenBuff;
	public static int twoStrBuff, twoSwiftBuff, twoProtBuff, twoRegenBuff;
	public static HashMap<UUID, UUID> beaconIDs = new HashMap<>();
	public static ArrayList<Entity> beaconAs = new ArrayList<Entity>();

	private int points;
	private double radius;

	public void onBeaconPlace(Block b, String blockName, Player p) {

		if (blockName != null) {

			if (blockName.equals(
					Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.strength.name").replace("&", "§"))) {

				Location asLoc = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY(),
						b.getLocation().getBlockZ() + 0.5);

				Item enStr = b.getWorld().dropItem(asLoc, new ItemStack(Material.GOLDEN_SWORD, 1));

				ArmorStand en = (ArmorStand) asLoc.getWorld().spawnEntity(asLoc, EntityType.ARMOR_STAND);

				if (Vars.pNameTeamOne.containsKey(p.getName()))
					en.setCustomName("§4§lBeacon of Strength");
				if (Vars.pNameTeamTwo.containsKey(p.getName()))
					en.setCustomName("§a§lBeacon of Strength");
				en.setCustomNameVisible(true);
				en.setVisible(false);
				en.setGravity(false);
				en.addPassenger(enStr);
				beaconAs.add(en);
				for (Entity it : en.getPassengers()) {
					if (it.getType().equals(enStr.getType())) {
						beaconIDs.put(en.getUniqueId(), it.getUniqueId());
					}
				}

				if (Vars.arenaSize.equals("small_")) {
					points = 150;
					radius = 7.5d;
				} else if (Vars.arenaSize.equals("large_")) {
					points = 230;
					radius = 11.5d;
				}
				final Location origin = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5,
						b.getLocation().getBlockY() + 0.1, b.getLocation().getBlockZ() + 0.5);

				if (Vars.pNameTeamOne.containsKey(p.getName())) {

					runOneStrTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							for (int i = 0; i < points; i++) {
								double angle = 2 * Math.PI * i / points;
								Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
										radius * Math.cos(angle));
								b.getWorld().spawnParticle(Particle.REDSTONE, point, 6, 0, 0, 0, 0);
							}
						}
					}, 30, 30);

					oneStrBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamOne.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamOne.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									}
								}
							}
						}
					}, 5, 5);
				}
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {

					runTwoStrTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							for (int i = 0; i < points; i++) {
								double angle = 2 * Math.PI * i / points;
								Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
										radius * Math.cos(angle));
								b.getWorld().spawnParticle(Particle.REDSTONE, point, 6, 0, 0, 0, 0);
							}
						}
					}, 30, 30);

					twoStrBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamTwo.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamTwo.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									}
								}
							}
						}
					}, 5, 5);
				}
			} else if (blockName.equals(
					Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.swiftness.name").replace("&", "§")))

			{

				Location asLoc = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY(),
						b.getLocation().getBlockZ() + 0.5);

				Item enSwift = b.getWorld().dropItem(asLoc, new ItemStack(Material.GOLDEN_BOOTS, 1));

				ArmorStand en = (ArmorStand) asLoc.getWorld().spawnEntity(asLoc, EntityType.ARMOR_STAND);

				if (Vars.pNameTeamOne.containsKey(p.getName()))
					en.setCustomName("§4§lBeacon of Swiftness");
				if (Vars.pNameTeamTwo.containsKey(p.getName()))
					en.setCustomName("§a§lBeacon of Swiftness");
				en.setCustomNameVisible(true);
				en.setVisible(false);
				en.setGravity(false);
				en.addPassenger(enSwift);
				beaconAs.add(en);
				for (Entity it : en.getPassengers()) {
					if (it.getType().equals(enSwift.getType())) {
						beaconIDs.put(en.getUniqueId(), it.getUniqueId());
					}
				}

				if (Vars.arenaSize.equals("small_")) {
					points = 75;
					radius = 7.5d;
				} else if (Vars.arenaSize.equals("large_")) {
					points = 115;
					radius = 11.5d;
				}
				final Location origin = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5,
						b.getLocation().getBlockY() - 0.3, b.getLocation().getBlockZ() + 0.5);

				if (Vars.pNameTeamOne.containsKey(p.getName())) {

					runOneSwiftTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.SPELL, point, 6, 0, 0, 0, 0);
									}
								}
							}, 30, 30);

					oneSwiftBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamOne.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamOne.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.SPEED);
									}
								}
							}
						}
					}, 5, 5);
				}

				if (Vars.pNameTeamTwo.containsKey(p.getName())) {

					runTwoSwiftTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.SPELL, point, 6, 0, 0, 0, 0);
									}
								}
							}, 30, 30);

					twoSwiftBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamTwo.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamTwo.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.SPEED);
									}
								}
							}
						}
					}, 5, 5);
				}
			} else if (blockName.equals(
					Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.regen.name").replace("&", "§"))) {

				Location asLoc = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY(),
						b.getLocation().getBlockZ() + 0.5);

				Item enRegen = b.getWorld().dropItem(asLoc, new ItemStack(Material.GOLDEN_APPLE, 1));

				ArmorStand en = (ArmorStand) asLoc.getWorld().spawnEntity(asLoc, EntityType.ARMOR_STAND);

				if (Vars.pNameTeamOne.containsKey(p.getName()))
					en.setCustomName("§4§lBeacon of Regeneration");
				if (Vars.pNameTeamTwo.containsKey(p.getName()))
					en.setCustomName("§a§lBeacon of Regeneration");
				en.setCustomNameVisible(true);
				en.setVisible(false);
				en.setGravity(false);
				en.addPassenger(enRegen);
				beaconAs.add(en);
				for (Entity it : en.getPassengers()) {
					if (it.getType().equals(enRegen.getType())) {
						beaconIDs.put(en.getUniqueId(), it.getUniqueId());
					}
				}

				if (Vars.arenaSize.equals("small_")) {
					points = 60;
					radius = 7.5d;
				} else if (Vars.arenaSize.equals("large_")) {
					points = 90;
					radius = 11.5d;
				}
				final Location origin = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5,
						b.getLocation().getBlockY() + 0.2, b.getLocation().getBlockZ() + 0.5);

				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					runOneRegenTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, point, 6, 0, 0, 0, 0);

									}
								}
							}, 60, 60);

					oneRegenBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamOne.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamOne.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
									} else {
										p.removePotionEffect(PotionEffectType.REGENERATION);
									}
								}
							}
						}
					}, 5, 5);
				}
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					runTwoRegenTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, point, 6, 0, 0, 0, 0);
									}
								}
							}, 60, 60);

					twoRegenBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamTwo.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamTwo.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
									} else {
										p.removePotionEffect(PotionEffectType.REGENERATION);
									}
								}
							}
						}
					}, 5, 5);
				}
			} else if (blockName.equals(
					Files.shopJUToolsItemsConfig.getString("junk.tools.beacons.protection.name").replace("&", "§"))) {

				Location asLoc = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY(),
						b.getLocation().getBlockZ() + 0.5);

				Item enProt = b.getWorld().dropItem(asLoc, new ItemStack(Material.GOLDEN_CHESTPLATE, 1));

				ArmorStand en = (ArmorStand) asLoc.getWorld().spawnEntity(asLoc, EntityType.ARMOR_STAND);

				if (Vars.pNameTeamOne.containsKey(p.getName()))
					en.setCustomName("§4§lBeacon of Protection");
				if (Vars.pNameTeamTwo.containsKey(p.getName()))
					en.setCustomName("§a§lBeacon of Protection");
				en.setCustomNameVisible(true);
				en.setVisible(false);
				en.setGravity(false);
				en.addPassenger(enProt);
				beaconAs.add(en);
				for (Entity it : en.getPassengers()) {
					if (it.getType().equals(enProt.getType())) {
						beaconIDs.put(en.getUniqueId(), it.getUniqueId());
					}
				}

				if (Vars.arenaSize.equals("small_")) {
					points = 150;
					radius = 7.5d;
				} else if (Vars.arenaSize.equals("large_")) {
					points = 230;
					radius = 11.5d;
				}
				final Location origin = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5,
						b.getLocation().getBlockY() + 0.3, b.getLocation().getBlockZ() + 0.5);

				if (Vars.pNameTeamOne.containsKey(p.getName())) {
					runOneProtTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, point, 6, 0, 0, 0, 0);
									}
								}
							}, 30, 30);

					oneProtBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamOne.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamOne.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
									}
								}
							}
						}
					}, 5, 5);
				}
				if (Vars.pNameTeamTwo.containsKey(p.getName())) {
					runTwoProtTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
							new Runnable() {

								public void run() {
									for (int i = 0; i < points; i++) {
										double angle = 2 * Math.PI * i / points;
										Location point = origin.clone().add(radius * Math.sin(angle), 0.0d,
												radius * Math.cos(angle));
										b.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, point, 6, 0, 0, 0, 0);
									}
								}
							}, 30, 30);

					twoProtBuff = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

						public void run() {
							if (Vars.pNameTeamTwo.containsKey(p.getName())) {
								for (String pN : Vars.pNameTeamTwo.keySet()) {
									Player p = Bukkit.getPlayerExact(pN);
									if (p.getLocation().distance(asLoc) <= radius) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
									} else {
										p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
									}
								}
							}
						}
					}, 5, 5);
				}
			}
		}
	}

	private UUID it;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBeaconBreak(BlockBreakEvent e) {

		Block b = e.getBlock();

		Location block = new Location(b.getWorld(), b.getLocation().getX() + 0.5, b.getLocation().getBlockY(),
				b.getLocation().getZ() + 0.5);
		Location blockTeam = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY() + 0.3,
				b.getLocation().getBlockZ() + 0.5);
		try {
			if (b.getType().equals(Material.BEACON)) {
				for (Entity en : beaconAs) {
					if (en.getLocation().equals(block)) {
						for (UUID id : beaconIDs.keySet()) {
							if (id == en.getUniqueId()) {
								for (Entity enPass : en.getPassengers()) {
									it = enPass.getUniqueId();
								}
								if (it == beaconIDs.get(id)) {
									beaconIDs.remove(id, it);
									beaconAs.remove(en);
									if (en.getCustomName().equalsIgnoreCase("§4§lBeacon of Strength")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runOneStrTask);
										oneBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.strength.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(oneStrBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§a§lBeacon of Strength")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runTwoStrTask);
										twoBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.strength.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(twoStrBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§4§lBeacon of Swiftness")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runOneSwiftTask);
										oneBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.swiftness.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(oneSwiftBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§a§lBeacon of Swiftness")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runTwoSwiftTask);
										twoBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.swiftness.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(twoSwiftBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§4§lBeacon of Regeneration")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runOneRegenTask);
										oneBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.regen.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(oneRegenBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§a§lBeacon of Regeneration")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runTwoRegenTask);
										twoBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.regen.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(twoRegenBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§4§lBeacon of Protection")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runOneProtTask);
										oneBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.protection.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(oneProtBuff);
									}
									if (en.getCustomName().equalsIgnoreCase("§a§lBeacon of Protection")) {
										Main.getInstance().getServer().getScheduler().cancelTask(runTwoProtTask);
										twoBeaconPlace.remove(Files.shopJUToolsItemsConfig
												.getString("junk.tools.beacons.protection.name").replace("&", "§"));
										Main.getInstance().getServer().getScheduler().cancelTask(twoProtBuff);
									}
									en.getPassenger().remove();
									en.remove();
								}
							}
						}

					} else if (en.getLocation().equals(blockTeam)) {
						beaconAs.remove(en);
						en.remove();
					}
				}
			}

		} catch (ConcurrentModificationException CME) {
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {

		UUID tid = event.getItem().getUniqueId();
		if (beaconIDs.containsValue(tid)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemDespawn(ItemDespawnEvent event) {

		UUID tid = event.getEntity().getUniqueId();
		if (beaconIDs.containsValue(tid)) {
			event.setCancelled(true);
		}
	}

	public static String blockName;
	public static ArrayList<String> oneBeaconPlace = new ArrayList<>();
	public static ArrayList<String> twoBeaconPlace = new ArrayList<>();

	@EventHandler
	public void onBeaconBlockPlace(BlockPlaceEvent e) {

		Player p = e.getPlayer();
		Block b = e.getBlock();

		if (b.getType().equals(Material.BEACON)) {
			blockName = e.getItemInHand().getItemMeta().getDisplayName();
			if (Vars.pNameTeamOne.containsKey(p.getName())) {
				if (!oneBeaconPlace.contains(blockName)) {
					onBeaconPlace(b, blockName, p);
					oneBeaconPlace.add(blockName);
				} else {
					e.setCancelled(true);
					p.sendMessage("§0[§4SINE§0] §7§oOnly 1 beacon type by team");

				}
			} else if (Vars.pNameTeamTwo.containsKey(p.getName())) {
				if (!twoBeaconPlace.contains(blockName)) {
					onBeaconPlace(b, blockName, p);
					twoBeaconPlace.add(blockName);
				} else {
					e.setCancelled(true);
					p.sendMessage("§0[§4SINE§0] §7§oOnly 1 beacon type by team");
				}
			}
		}
	}

	public void beaconTeam(String team, Block b) {

		Location asLocTeam = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.5, b.getLocation().getY() + 0.3,
				b.getLocation().getBlockZ() + 0.5);

		ArmorStand enTeam = (ArmorStand) asLocTeam.getWorld().spawnEntity(asLocTeam, EntityType.ARMOR_STAND);

		if (team.equals("one"))
			enTeam.setCustomName("§4§lRed Team");
		if (team.equals("two"))
			enTeam.setCustomName("§a§lGreen Team");
		enTeam.setCustomNameVisible(true);
		enTeam.setVisible(false);
		enTeam.setGravity(false);
		beaconAs.add(enTeam);

	}
}
