package fr.phoenix.sineplugin.structureLoader;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.phoenix.sineplugin.Main;

public class TrafficListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Main.getInstance().getSelectionManager().register(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Main.getInstance().getSelectionManager().unregister(event.getPlayer());
	}
}
