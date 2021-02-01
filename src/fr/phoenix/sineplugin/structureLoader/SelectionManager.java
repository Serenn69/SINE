package fr.phoenix.sineplugin.structureLoader;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class SelectionManager {
	private Map<Player, Selection> selections = new HashMap<Player, Selection>();

	public Selection getSelection(Player player) {
		return (Selection) this.selections.get(player);
	}

	public void register(Player player) {
		this.selections.put(player, new Selection());
	}

	public void unregister(Player player) {
		this.selections.remove(player);
	}
}
