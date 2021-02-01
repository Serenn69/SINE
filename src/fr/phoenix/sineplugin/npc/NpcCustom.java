package fr.phoenix.sineplugin.npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;

import fr.phoenix.sineplugin.Files;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.NBTTagCompound;


public class NpcCustom {
	
	FileConfiguration shopCoordConfig = Files.shopCoordConfig;
	FileConfiguration setsNameConfig = Files.setsNameConfig;
	FileConfiguration shopNamesConfig = Files.shopNamesConfig;
	
		
	public void SpawnNpcShop(int nbr, String team, int mag) {
				
		
		//Location dynamique des NPC
		Location locNpc1 = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager1.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager1.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager1.z"));
		Location locNpc2 = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager2.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager2.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".villager2.z"));
		Location locBS = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".blacksmith.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".blacksmith.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".blacksmith.z"));
		Location locEN = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".enchanter.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".enchanter.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".enchanter.z"));
		Location locAL = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".alchemist.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".alchemist.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".alchemist.z"));
		Location locJU = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".junk.x"), 
				shopCoordConfig.getDouble("teams."+team+".mag"+mag+".junk.y"), shopCoordConfig.getDouble("teams."+team+".mag"+mag+".junk.z"));
		
		

			//Spawn des NPC
			Villager npc1 = (Villager) locNpc1.getWorld().spawnEntity(locNpc1, EntityType.VILLAGER);
			Villager bs = (Villager) locBS.getWorld().spawnEntity(locBS, EntityType.VILLAGER);
			Evoker en = (Evoker) locEN.getWorld().spawnEntity(locEN, EntityType.EVOKER);
			Witch al = (Witch) locAL.getWorld().spawnEntity(locAL, EntityType.WITCH);
			Vindicator ju = (Vindicator) locJU.getWorld().spawnEntity(locJU, EntityType.VINDICATOR);
			
			//Customisation des NPC
			npc1.setCustomName(shopNamesConfig.getString("npc.easter.teams."+team+".mag"+mag+".v1").replace("&", "§"));
			npc1.setCustomNameVisible(true);
			bs.setCustomName(shopNamesConfig.getString("npc.shop.blacksmith").replace("&", "§"));
			bs.setCustomNameVisible(true);
			en.setCustomName(shopNamesConfig.getString("npc.shop.enchanter").replace("&", "§"));
			en.setCustomNameVisible(true);
			al.setCustomName(shopNamesConfig.getString("npc.shop.alchemist").replace("&", "§"));
			al.setCustomNameVisible(true);
			ju.setCustomName(shopNamesConfig.getString("npc.shop.junk").replace("&", "§"));
			ju.setCustomNameVisible(true);
			
			bs.setProfession(Profession.WEAPONSMITH);
			npc1.setProfession(Profession.LIBRARIAN);
			
			//Déclaration des NPC en NMS
			Entity nmsNpc1 = ((CraftEntity) npc1).getHandle();
			Entity nmsBS = ((CraftEntity) bs).getHandle();
			Entity nmsEN = ((CraftEntity) en).getHandle();
			Entity nmsAL = ((CraftEntity) al).getHandle();
			Entity nmsJU = ((CraftEntity) ju).getHandle();
						
			//Positionnement des NPC
			nmsNpc1.setPositionRotation(locNpc1.getX(), locNpc1.getY(), locNpc1.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".villager1.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".villager1.pitch"));
			nmsBS.setPositionRotation(locBS.getX(), locBS.getY(), locBS.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".blacksmith.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".blacksmith.pitch"));
			nmsEN.setPositionRotation(locEN.getX(), locEN.getY(), locEN.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".enchanter.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".enchanter.pitch"));
			nmsAL.setPositionRotation(locAL.getX(), locAL.getY(), locAL.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".alchemist.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".alchemist.pitch"));
			nmsJU.setPositionRotation(locJU.getX(), locJU.getY(), locJU.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".junk.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".junk.pitch"));
		
			//Desactivation de l'IA et Silence les NPC
			NBTTagCompound tagNpc1 = new NBTTagCompound();
	        nmsNpc1.c(tagNpc1);
	        tagNpc1.setInt("NoAI", 1);
	        tagNpc1.setInt("Silent", 1);
	        nmsNpc1.f(tagNpc1);
	        
	        NBTTagCompound tagBS = new NBTTagCompound();
	        nmsBS.c(tagBS);
	        tagBS.setInt("NoAI", 1);
	        tagBS.setInt("Silent", 1);
	        nmsBS.f(tagBS);
			
	        NBTTagCompound tagEN = new NBTTagCompound();
	        nmsEN.c(tagEN);
	        tagEN.setInt("NoAI", 1);
	        tagEN.setInt("Silent", 1);
	        nmsEN.f(tagEN);
			
	        NBTTagCompound tagAL = new NBTTagCompound();
	        nmsAL.c(tagAL);
	        tagAL.setInt("NoAI", 1);
	        tagAL.setInt("Silent", 1);
	        nmsAL.f(tagAL);
			
	        NBTTagCompound tagJU = new NBTTagCompound();
	        nmsJU.c(tagJU);
	        tagJU.setInt("NoAI", 1);
	        tagJU.setInt("Silent", 1);
	        nmsJU.f(tagJU);
	        
	        if (nbr == 6) {
				Villager npc2 = (Villager) locNpc2.getWorld().spawnEntity(locNpc2, EntityType.VILLAGER);
				Entity nmsNpc2 = ((CraftEntity) npc2).getHandle();
				npc2.setCustomName(shopNamesConfig.getString("npc.easter.teams."+team+".mag"+mag+".v2").replace("&", "§"));
				npc2.setCustomNameVisible(true);
				if (mag == 2 || mag == 3) {
					npc2.setProfession(Profession.CLERIC);
				}
				else if (mag == 1) {
					npc2.setProfession(Profession.BUTCHER);
					npc2.setAge(-32768);;
					npc2.setAgeLock(true);
				}
				nmsNpc2.setPositionRotation(locNpc2.getX(), locNpc2.getY(), locNpc2.getZ(), shopCoordConfig.getInt("teams."+team+".mag"+mag+".villager2.yaw"), shopCoordConfig.getInt("teams."+team+".mag"+mag+".villager2.pitch"));

		        NBTTagCompound tagNpc2 = new NBTTagCompound();
		        nmsNpc2.c(tagNpc2);
		        tagNpc2.setInt("NoAI", 1);
		        tagNpc2.setInt("Silent", 1);
		        nmsNpc2.f(tagNpc2);
			}
	}
	
	public void spawnTeleporter(String team) {
		
		Location locTPS = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".tpS.x"), 
				shopCoordConfig.getDouble("teams."+team+".tpS.y"), shopCoordConfig.getDouble("teams."+team+".tpS.z"));
		Location locTPN = new Location(Bukkit.getWorld(setsNameConfig.getString("world.name")), shopCoordConfig.getDouble("teams."+team+".tpN.x"), 
				shopCoordConfig.getDouble("teams."+team+".tpN.y"), shopCoordConfig.getDouble("teams."+team+".tpN.z"));
		
		Vex tpS = (Vex) locTPS.getWorld().spawnEntity(locTPS, EntityType.VEX);
		Vex tpN = (Vex) locTPN.getWorld().spawnEntity(locTPN, EntityType.VEX);

		Entity nmsTPS = ((CraftEntity) tpS).getHandle();
		Entity nmsTPN = ((CraftEntity) tpN).getHandle();

		tpS.setCustomName(shopNamesConfig.getString("npc.shop.tp").replace("&", "§"));
		tpS.setCustomNameVisible(true);
		tpN.setCustomName(shopNamesConfig.getString("npc.shop.tp").replace("&", "§"));
		tpN.setCustomNameVisible(true);

		nmsTPS.setPositionRotation(locTPS.getX(), locTPS.getY(), locTPS.getZ(), shopCoordConfig.getInt("teams."+team+".tpS.yaw"), shopCoordConfig.getInt("teams."+team+".tpS.pitch"));
		nmsTPN.setPositionRotation(locTPN.getX(), locTPN.getY(), locTPN.getZ(), shopCoordConfig.getInt("teams."+team+".tpN.yaw"), shopCoordConfig.getInt("teams."+team+".tpN.pitch"));
        
        NBTTagCompound tagTPS = new NBTTagCompound();
        nmsTPS.c(tagTPS);
        tagTPS.setInt("NoAI", 1);
        tagTPS.setInt("Silent", 1);
        nmsTPS.f(tagTPS);
        
        NBTTagCompound tagTPN = new NBTTagCompound();
        nmsTPN.c(tagTPN);
        tagTPN.setInt("NoAI", 1);
        tagTPN.setInt("Silent", 1);
        nmsTPN.f(tagTPN);
	}
	
}
