package fr.phoenix.sineplugin.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import fr.phoenix.sineplugin.Files;
import fr.phoenix.sineplugin.Main;

public class NewsManager {

	public static BossBar newsBar;

	public void displayNewsBanner() {

		List<String> newsList = new ArrayList<>(
				Arrays.asList(Files.newsConfig.getString("welcome").replace("&", "§"),
						Files.newsConfig.getString("news.infos.1").replace("&", "§"),
						Files.newsConfig.getString("news.events.1").replace("&", "§"),
						Files.newsConfig.getString("news.ads.1").replace("&", "§"),
						Files.newsConfig.getString("news.infos.2").replace("&", "§"),
						Files.newsConfig.getString("news.events.2").replace("&", "§"),
						Files.newsConfig.getString("news.ads.2").replace("&", "§"),
						Files.newsConfig.getString("news.infos.3").replace("&", "§"),
						Files.newsConfig.getString("news.events.3").replace("&", "§"),
						Files.newsConfig.getString("news.ads.3").replace("&", "§")));

		newsBar = Bukkit.createBossBar(Files.newsConfig.getString("welcome").replace("&", "§"),BarColor.YELLOW , BarStyle.SOLID, new BarFlag[0]);

		for (Player p : Bukkit.getOnlinePlayers()) {
			newsBar.addPlayer(p);
		}

		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {

			double seen = 0.0;
			int i = 0;

			@Override
			public void run() {

				seen += 0.005;

				if (seen >= 1) {
					seen = 0;
					i++;

					if (i >= newsList.size()) {
						i = 0;
					}

					newsBar.setTitle(newsList.get(i));
					if (i == 0)
						newsBar.setColor(BarColor.YELLOW);
					if (i == 1 || i == 4 || i == 7)
						newsBar.setColor(BarColor.RED);
					if (i == 2 || i == 5 || i == 8)
						newsBar.setColor(BarColor.PURPLE);
					if (i == 3 || i == 6 || i == 9)
						newsBar.setColor(BarColor.BLUE);
				}
				newsBar.setProgress(seen);
			}
		}, 1, 1);
	}

}
