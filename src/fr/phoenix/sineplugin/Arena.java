package fr.phoenix.sineplugin;

import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class Arena {

	public void chooseArena() {
		
		if (Files.config.getString("settings.game.map").equalsIgnoreCase("&9Map : &cFOREST")) {
			Vars.arenaMap = "forest_";
			if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
				Vars.arenaSize = "small_";
			}
			else if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
				Vars.arenaSize = "large_";
			}
		}
		if (Files.config.getString("settings.game.map").equalsIgnoreCase("&9Map : &cHILL")) {
			Vars.arenaMap = "hill_";
			if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
				Vars.arenaSize = "small_";
			}
			else if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
				Vars.arenaSize = "large_";
			}
		}
		if (Files.config.getString("settings.game.map").equalsIgnoreCase("&9Map : &cTRENCHEES")) {
			Vars.arenaMap = "trenchees_";
			if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cSMALL")) {
				Vars.arenaSize = "small_";
			}
			else if (Files.config.getString("settings.game.size").equalsIgnoreCase("&9Map Size : &cLARGE")) {
				Vars.arenaSize = "large_";
			}
		}
	}

	public void loadArena() {

		if (Vars.arenaSize.equals("small_")) {
			
			API.placeStructure(Vars.arenaMap + Vars.arenaSize + "OaTd", Tp.smallArenaE);
			API.placeStructure(Vars.arenaMap + Vars.arenaSize + "OdTa", Tp.smallArenaW);
		}
		if (Vars.arenaSize.equals("large_")) {

			API.placeStructure(Vars.arenaMap + Vars.arenaSize + "OaTd", Tp.largeArenaE);
			API.placeStructure(Vars.arenaMap + Vars.arenaSize + "OdTa", Tp.largeArenaW);
		}
	}
	
	public void clearArena() {
		
		API.placeStructure("empty_arena", Tp.emptyE);
		API.placeStructure("empty_arena", Tp.emptyW);
		
	}
	
	public void loadAtkBase() {
		
		if (Vars.whoBegin.equals("OaTd")) {
			API.placeStructure("one_atk_base", Tp.oneAtkBase);
		}
		if (Vars.whoBegin.equals("OdTa")) {
			API.placeStructure("two_atk_base", Tp.twoAtkBase);
		}
	}
	
	public void clearAtkBase() {
		
		if (Vars.whoBegin.equals("OaTd")) {
			API.placeStructure("empty_atk_base", Tp.oneAtkBase);
		}
		if (Vars.whoBegin.equals("OdTa")) {
			API.placeStructure("empty_atk_base", Tp.twoAtkBase);
		}
	}
	
}
