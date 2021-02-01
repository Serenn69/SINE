package fr.phoenix.sineplugin.playerProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.Vars;
import fr.phoenix.sineplugin.phases.Countdown;

public class PlayerStatsEvents implements Listener {

	public PlayerStats ps;

	ArrayList<Material> swords = new ArrayList<Material>(
			Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD));
	ArrayList<Material> axes = new ArrayList<Material>(
			Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE));
	
	@EventHandler
	public void sneaking(PlayerToggleSneakEvent e) {

		Player p = e.getPlayer();

		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {

			if (!p.isSneaking()) {
				ps.updateMaxSneakTime(p);
				ps.updateTotalSneakTime(p);
				p.setStatistic(Statistic.SNEAK_TIME, 0);
			}
		}
	}

	@EventHandler
	public void deathPlayer(PlayerDeathEvent e) {

		Player p = (Player) e.getEntity();
		Player k = p.getKiller();

		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {

			if (Vars.pNameTeamOne.containsKey(k.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {

				ps.updateTotalPlayersKilled(k);
				ps.updateTotalDeaths(p);

			} else if (Vars.pNameTeamTwo.containsKey(k.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {

				ps.updateTotalPlayersKilled(k);
				ps.updateTotalDeaths(p);

			} else if (Vars.pNameTeamOne.containsKey(k.getName()) && Vars.pNameTeamOne.containsKey(p.getName())) {

				ps.updateTotalMateKills(k);

			} else if (Vars.pNameTeamTwo.containsKey(k.getName()) && Vars.pNameTeamTwo.containsKey(p.getName())) {

				ps.updateTotalMateKills(k);
			}
			ps.updateMaxTimeNoDeath(p);
			ps.updateTotalTimeNoDeath(p);
		}
	}

	@EventHandler
	public void swingWeapon(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();
		Material mh = p.getInventory().getItemInMainHand().getType();

		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {
				if (swords.contains(mh)) {
					ps.updateTotalSwordSwing(p);
				}
				if (axes.contains(mh)) {
					ps.updateTotalAxeSwing(p);
				}
			}
		}
	}

	@EventHandler
	public void hitsWeapon(EntityDamageByEntityEvent e) {

		Player p = (Player) e.getDamager();
		DamageCause c = e.getCause();
		Material mh = p.getInventory().getItemInMainHand().getType();
		Projectile a = (Projectile) e.getDamager();
		Player pa = (Player) a.getShooter();
		
		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			if (c == DamageCause.ENTITY_ATTACK || c == DamageCause.ENTITY_SWEEP_ATTACK) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						if (swords.contains(mh)) {
							ps.updateTotalSwordHits(p);
						}
						if (axes.contains(mh)) {
							ps.updateTotalAxeHits(p);
						}
					}
				}
			}
			if (c == DamageCause.PROJECTILE) {
				if (a instanceof Arrow || a instanceof AbstractArrow || a instanceof SpectralArrow) {
					if (pa instanceof Player) {
						ps.updateTotalArrowHits(pa);
					}
				}
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void shotsProjectile(EntityShootBowEvent e) {

		Player p = (Player) e.getEntity();
		Entity a = e.getProjectile();
		
		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			if (p instanceof Player) {
				if (a.getType().equals(Material.ARROW) || a.getType().equals(Material.SPECTRAL_ARROW) || a.getType().equals(Material.TIPPED_ARROW)) {
					ps.updateTotalArrowShots(p);
				}
			}
		}
	}
	
	public void heal(EntityRegainHealthEvent e) {
		
		Player p = (Player) e.getEntity();
		RegainReason r = e.getRegainReason();
		Material mh = p.getInventory().getItemInMainHand().getType();
		
		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			if (r == RegainReason.MAGIC || r == RegainReason.MAGIC_REGEN) {
				if (mh.equals(Material.POTION)) {
					ps.updateTotalHealPotUsed(p);
				}
			}			
		}		
	}	
	
	@SuppressWarnings("unlikely-arg-type")
	public void splashHeal(PotionSplashEvent e) {
		
		Player p = (Player) e.getEntity().getShooter();
		Collection<PotionEffect> pe = e.getPotion().getEffects();
				
		if (Main.getInstance().getServer().getScheduler().isQueued(Countdown.fight)) {
			if (p instanceof Player) {
				if (pe.contains(PotionEffectType.HEAL) || pe.contains(PotionEffectType.REGENERATION)) {
					for (LivingEntity ap : e.getAffectedEntities()) {
						if (ap instanceof Player) {
							if (Vars.pNameTeamOne.containsKey(p.getName()) && Vars.pNameTeamOne.containsKey(ap.getName())) {
								ps.updateTotalHealMates(p);
							}
							if (Vars.pNameTeamTwo.containsKey(p.getName()) && Vars.pNameTeamTwo.containsKey(ap.getName())) {
								ps.updateTotalHealMates(p);
							}
						}						
					}
				}
			}
		}		
	}
}
