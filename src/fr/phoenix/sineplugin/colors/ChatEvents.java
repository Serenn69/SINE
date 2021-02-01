package fr.phoenix.sineplugin.colors;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.phoenix.sineplugin.Vars;

public class ChatEvents implements Listener {

	@EventHandler
	public void onChatEvents(AsyncPlayerChatEvent e) {
		
		String msg = e.getMessage();
		Player p = e.getPlayer();
		
		if (Vars.pNameAdmin.contains(p.getName())) {
			e.setFormat("§0[§4Admin§0] §6"+p.getName()+"§0 : §f"+msg);
		}
		if (Vars.pNameCaster.contains(p.getName())) {
			e.setFormat("§0[§4Caster§0] §9"+p.getName()+"§0 : §f§o"+msg);
		}
		if (Vars.pNameSpec.contains(p.getName())) {
			e.setFormat("§0[§4Spec§0] §7"+p.getName()+"§0 : §f§o"+msg);
		}
		if (Vars.pNameTeamOne.containsKey(p.getName())) {
			e.setFormat("§0[§cRed§0] §c"+p.getName()+"§0 : §f§o"+msg);
		}
		if (Vars.pNameTeamTwo.containsKey(p.getName())) {
			e.setFormat("§0[§aGreen§0] §a"+p.getName()+"§0 : §f§o"+msg);
		}
		
	}
	
}
