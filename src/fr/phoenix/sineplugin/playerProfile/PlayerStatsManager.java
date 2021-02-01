package fr.phoenix.sineplugin.playerProfile;

import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.SqlConnection;
import fr.phoenix.sineplugin.Vars;

public class PlayerStatsManager {

	public void loadPlayerStatsSeason(Player p) {
		if (!Vars.pStatsSeason.containsKey(p)) {
			PlayerStats pStats = SqlConnection.loadPlayerStatsSeasonFromDatabase(p);
			Vars.pStatsSeason.put(p, pStats);
		}		
	}
	
	public void loadPlayerStatsLastGame(Player p) {
		if (!Vars.pStatsLastGame.containsKey(p)) {
			PlayerStats pStats = SqlConnection.loadPlayerStatsLastGameFromDatabase(p);
			Vars.pStatsLastGame.put(p, pStats);
		}		
	}
	
	public void savePlayerStats(Player p) {
		if (Vars.pStatsSeason.containsKey(p)) {
			SqlConnection.updatePlayerStatsSeasonToDatabase(p);
		}
		if (Vars.pStatsLastGame.containsKey(p)) {
			SqlConnection.updatePlayerStatsLastGameToDatabase(p);
		}
	}
	
	public void saveAccessAndBlacklist(Player p) {
		
		if (Vars.pStatsSeason.containsKey(p)) {
			SqlConnection.updatePlayerStatsAccessToDatabase(p);
			SqlConnection.updatePlayerStatsBlacklistToDatabase(p);
		}			
	}
}
