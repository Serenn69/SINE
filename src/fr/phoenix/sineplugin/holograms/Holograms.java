package fr.phoenix.sineplugin.holograms;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import net.minecraft.server.v1_14_R1.EntityArmorStand;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityLiving;

public class Holograms {

	public static List<EntityArmorStand> welcList = new ArrayList<>();
	public static List<EntityArmorStand> holoOneList = new ArrayList<>();
	public static List<EntityArmorStand> holoTwoList = new ArrayList<>();
	public static List<EntityArmorStand> holoCasterList = new ArrayList<>();
	private ArrayList<IChatBaseComponent> text;
	private Location loc;
	private double d = 0.25;
	int count;

	public static Holograms holoWelc, holoScoreOne, holoScoreTwo, holoScoreOneCaster, holoScoreTwoCaster,
			holoPreviousRound;

	public Holograms(ArrayList<IChatBaseComponent> text, Location loc, List<EntityArmorStand> asList) {

		this.text = text;
		this.loc = loc;
		createHolo(asList);
	}

	public void tempPlayerSee(final Player p, int time, List<EntityArmorStand> asList) {

		showPlayer(p, asList);

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			public void run() {
				hidePlayer(p, asList);
			}
		}, time);
	}

	public void tempAllSee(int time, List<EntityArmorStand> asList) {

		showAll(asList);

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			public void run() {
				hideAll(asList);
			}
		}, time);
	}

	public void showPlayer(Player p, List<EntityArmorStand> asList) {

		for (EntityArmorStand as : asList) {
			PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}

	public void hidePlayer(Player p, List<EntityArmorStand> asList) {

		for (EntityArmorStand as : asList) {
			PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(as.getId());
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}

	public void showAll(List<EntityArmorStand> asList) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			for (EntityArmorStand as : asList) {
				PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void hideAll(List<EntityArmorStand> asList) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			for (EntityArmorStand as : asList) {
				PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(as.getId());
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void showTeamOne(List<EntityArmorStand> asList) {

		for (String pN : Vars.pNameTeamOne.keySet()) {
			Player pO = Bukkit.getPlayerExact(pN);
			for (EntityArmorStand as : asList) {
				PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
				((CraftPlayer) pO).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void showTeamTwo(List<EntityArmorStand> asList) {

		for (String pN : Vars.pNameTeamTwo.keySet()) {
			Player pT = Bukkit.getPlayerExact(pN);
			for (EntityArmorStand as : asList) {
				PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
				((CraftPlayer) pT).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void showCaster(List<EntityArmorStand> asList) {

		for (String pN : Vars.pNameCaster) {
			Player pC = Bukkit.getPlayerExact(pN);
			for (EntityArmorStand as : asList) {
				PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
				((CraftPlayer) pC).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void showSpec(List<EntityArmorStand> asList) {

		for (String pN : Vars.pNameSpec) {
			Player pS = Bukkit.getPlayerExact(pN);
			for (EntityArmorStand as : asList) {
				PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
				((CraftPlayer) pS).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void createHolo(List<EntityArmorStand> asList) {

		for (IChatBaseComponent text : this.text) {
			EntityArmorStand en = new EntityArmorStand(((CraftWorld) this.loc.getWorld()).getHandle(), this.loc.getX(),
					this.loc.getY(), this.loc.getZ());
			en.setCustomName(text);
			en.setCustomNameVisible(true);
			en.setInvisible(true);
			en.setNoGravity(false);
			asList.add(en);
			this.loc.subtract(0, this.d, 0);
			count++;
		}

		for (int i = 0; i < count; i++) {
			this.loc.add(0, this.d, 0);
		}
		this.count = 0;
	}	
}
