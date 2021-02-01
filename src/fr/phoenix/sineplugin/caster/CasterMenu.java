package fr.phoenix.sineplugin.caster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.phoenix.sineplugin.Main;
import fr.phoenix.sineplugin.phases.Countdown;

public class CasterMenu {

	ArrayList<Integer> invOneSlots = new ArrayList<>(
			Arrays.asList(0, 1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21, 27, 28, 29, 30, 36, 37, 38, 39, 45, 46, 47, 48));
	ArrayList<Integer> invTwoSlots = new ArrayList<>(
			Arrays.asList(5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26, 32, 33, 34, 35, 41, 42, 43, 44, 50, 51, 52, 53));
	public static ArrayList<Integer> amountBlocks = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 16, 32, 64));

	public static HashMap<ItemStack, Integer> teamOneBuy = new HashMap<ItemStack, Integer>();
	public static HashMap<ItemStack, Integer> teamTwoBuy = new HashMap<ItemStack, Integer>();
	public static HashMap<ItemStack, Integer> teamOneBuyed = new HashMap<ItemStack, Integer>();
	public static HashMap<ItemStack, Integer> teamTwoBuyed = new HashMap<ItemStack, Integer>();
	public static HashMap<ItemStack, Integer> teamOneBuyPrevious = new HashMap<ItemStack, Integer>();
	public static HashMap<ItemStack, Integer> teamTwoBuyPrevious = new HashMap<ItemStack, Integer>();

	public static Inventory inv, invPrev;
	public static int refresh;

	private int one = 1;
	private int two = 1;

	public CasterMenu(int size, String prev) {

		if (size == 0)
			size = 9;
		if (size > 54)
			size = 54;

		if (prev.equals("yes")) {
			invPrev = Bukkit.createInventory(null, size,
					"       §4RED       §dR§0:§d" + (Countdown.round - 1) + "      §2GREEN  ");
		} else {
			inv = Bukkit.createInventory(null, size,
					"       §4RED       §dR§0:§d" + Countdown.round + "      §2GREEN  ");
		}
	}

	public void createCasterChestMenu(Player p, String prev) {

		if (prev.equals("yes")) {
			ItemStack limit = new ItemStack(Material.BARRIER, 1);
			ItemMeta lM = limit.getItemMeta();
			lM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			lM.setDisplayName(" ");
			limit.setItemMeta(lM);
			invPrev.setItem(4, limit);
			invPrev.setItem(13, limit);
			invPrev.setItem(22, limit);
			invPrev.setItem(31, limit);
			invPrev.setItem(40, limit);
			invPrev.setItem(49, limit);

			p.openInventory(invPrev);

			Bukkit.broadcastMessage("updatePrevious");
			updatePreviousCasterMenu(p);

		} else {
			ItemStack limit = new ItemStack(Material.BARRIER, 1);
			ItemMeta lM = limit.getItemMeta();
			lM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			lM.setDisplayName(" ");
			limit.setItemMeta(lM);
			inv.setItem(4, limit);
			inv.setItem(13, limit);
			inv.setItem(22, limit);
			inv.setItem(31, limit);
			inv.setItem(40, limit);
			inv.setItem(49, limit);

			p.openInventory(inv);

			checkRefreshCasterMenu(p, inv);
		}
	}

	public void updateCasterMenu(Player p) {

		if (one == 1) {

			int rd = 0;
			int left = 0;

			for (ItemStack itO : teamOneBuy.keySet()) {
				int nbrO = teamOneBuy.get(itO);

				if (nbrO <= 64) {

					if (teamOneBuyed.containsKey(itO)) {

						for (int slot : invOneSlots) {
							if (inv.getItem(slot).getType().equals(itO.getType())) {
								inv.clear(slot);
								inv.setItem(slot, itO);
								inv.getItem(slot).setAmount(nbrO);
								p.updateInventory();
								teamOneBuyed.put(itO, nbrO);
								break;
							}
						}
					} else if (!teamOneBuyed.containsKey(itO)) {

						for (int slot : invOneSlots) {

							if (inv.getItem(slot) == null) {
								inv.setItem(slot, itO);
								inv.getItem(slot).setAmount(nbrO);
								p.updateInventory();
								teamOneBuyed.put(itO, nbrO);
								break;
							}
						}
					}
				} else if (nbrO > 64) {

					left = nbrO % 64;
					rd = nbrO / 64;

					if (rd >= 1) {

						for (int slot : invOneSlots) {

							if (inv.getItem(slot) != null && inv.getItem(slot).getType().equals(itO.getType())) {
								inv.clear(slot);
							}
						}
						for (int x = 1; x <= rd; x++) {

							for (int slot : invOneSlots) {

								if (inv.getItem(slot) == null) {
									inv.setItem(slot, itO);
									inv.getItem(slot).setAmount(64);
									p.updateInventory();
									break;
								}
							}
						}
					}
					if (left != 0) {
						for (int slot : invOneSlots) {
							if (inv.getItem(slot) == null) {
								inv.setItem(slot, itO);
								inv.getItem(slot).setAmount(left);
								p.updateInventory();
								break;
							}
						}
					}
					p.updateInventory();
					teamOneBuyed.put(itO, nbrO);
				}
			}
		}

		if (two == 1) {

			int rd = 0;
			int left = 0;

			for (ItemStack itT : teamTwoBuy.keySet()) {
				int nbrT = teamTwoBuy.get(itT);

				if (nbrT <= 64) {

					if (teamTwoBuyed.containsKey(itT)) {

						for (int slot : invTwoSlots) {
							if (inv.getItem(slot).getType().equals(itT.getType())) {
								inv.clear(slot);
								inv.setItem(slot, itT);
								inv.getItem(slot).setAmount(nbrT);
								p.updateInventory();
								teamTwoBuyed.put(itT, nbrT);
								break;
							}
						}
					} else if (!teamTwoBuyed.containsKey(itT)) {

						for (int slot : invTwoSlots) {

							if (inv.getItem(slot) == null) {
								inv.setItem(slot, itT);
								inv.getItem(slot).setAmount(nbrT);
								p.updateInventory();
								teamTwoBuyed.put(itT, nbrT);
								break;
							}
						}
					}
				} else if (nbrT > 64) {

					left = nbrT % 64;
					rd = nbrT / 64;

					if (rd >= 1) {

						for (int slot : invTwoSlots) {

							if (inv.getItem(slot) != null && inv.getItem(slot).getType().equals(itT.getType())) {
								inv.clear(slot);
							}
						}
						for (int x = 1; x <= rd; x++) {

							for (int slot : invTwoSlots) {

								if (inv.getItem(slot) == null) {
									inv.setItem(slot, itT);
									inv.getItem(slot).setAmount(64);
									p.updateInventory();
									break;
								}
							}
						}
					}
					if (left != 0) {
						for (int slot : invTwoSlots) {
							if (inv.getItem(slot) == null) {
								inv.setItem(slot, itT);
								inv.getItem(slot).setAmount(left);
								p.updateInventory();
								break;
							}
						}
					}
					p.updateInventory();
					teamOneBuyed.put(itT, nbrT);
				}
			}
		}
	}

	public void checkRefreshCasterMenu(Player p, Inventory inv) {
		
		updateCasterMenu(p);

		refresh = Bukkit.getScheduler().scheduleSyncRepeatingTask((Main.getInstance()), new Runnable() {

			@Override
			public void run() {

				if (!teamOneBuy.equals(teamOneBuyed)) {
					one = 1;
					updateCasterMenu(p);
				} else {
					one = 0;
				}

				if (!teamTwoBuy.equals(teamTwoBuyed)) {
					two = 1;
					updateCasterMenu(p);
				} else {
					two = 0;
				}
			}
		}, 10, 10);
	}

	public void updatePreviousCasterMenu(Player p) {

		int rdO = 0;
		int leftO = 0;

		for (ItemStack itO : teamOneBuyPrevious.keySet()) {
			int nbrO = teamOneBuyPrevious.get(itO);

			if (nbrO <= 64) {

				for (int slot : invOneSlots) {

					if (invPrev.getItem(slot) != null) {
						if (invPrev.getItem(slot).getType().equals(itO.getType())) {
							invPrev.clear(slot);
							invPrev.setItem(slot, itO);
							invPrev.getItem(slot).setAmount(nbrO);
							p.updateInventory();
							break;
						}
					}
					if (invPrev.getItem(slot) == null) {
						invPrev.setItem(slot, itO);
						invPrev.getItem(slot).setAmount(nbrO);
						p.updateInventory();
						break;
					}
				}
			} else if (nbrO > 64) {

				leftO = nbrO % 64;
				rdO = nbrO / 64;

				if (rdO >= 1) {

					for (int slot : invOneSlots) {

						if (invPrev.getItem(slot) != null && invPrev.getItem(slot).getType().equals(itO.getType())) {
							invPrev.clear(slot);
						}
					}
					for (int x = 1; x <= rdO; x++) {

						for (int slot : invOneSlots) {

							if (invPrev.getItem(slot) == null) {
								invPrev.setItem(slot, itO);
								invPrev.getItem(slot).setAmount(64);
								p.updateInventory();
								break;
							}
						}
					}
				}
				if (leftO != 0) {
					for (int slot : invOneSlots) {
						if (invPrev.getItem(slot) == null) {
							invPrev.setItem(slot, itO);
							invPrev.getItem(slot).setAmount(leftO);
							p.updateInventory();
							break;
						}
					}
				}
				p.updateInventory();
			}
		}

		int rdT = 0;
		int leftT = 0;

		for (ItemStack itT : teamTwoBuyPrevious.keySet()) {
			int nbrT = teamTwoBuyPrevious.get(itT);

			if (nbrT <= 64) {

				for (int slot : invTwoSlots) {

					if (invPrev.getItem(slot) != null) {
						if (invPrev.getItem(slot).getType().equals(itT.getType())) {
							invPrev.clear(slot);
							invPrev.setItem(slot, itT);
							invPrev.getItem(slot).setAmount(nbrT);
							p.updateInventory();
							break;
						}
					}
					if (invPrev.getItem(slot) == null) {
						invPrev.setItem(slot, itT);
						invPrev.getItem(slot).setAmount(nbrT);
						p.updateInventory();
						break;
					}
				}
			} else if (nbrT > 64) {

				leftT = nbrT % 64;
				rdT = nbrT / 64;

				if (rdT >= 1) {

					for (int slot : invTwoSlots) {

						if (invPrev.getItem(slot) != null && invPrev.getItem(slot).getType().equals(itT.getType())) {
							invPrev.clear(slot);
						}
					}
					for (int x = 1; x <= rdT; x++) {

						for (int slot : invTwoSlots) {

							if (invPrev.getItem(slot) == null) {
								invPrev.setItem(slot, itT);
								invPrev.getItem(slot).setAmount(64);
								p.updateInventory();
								break;
							}
						}
					}
				}
				if (leftT != 0) {
					for (int slot : invTwoSlots) {
						if (invPrev.getItem(slot) == null) {
							invPrev.setItem(slot, itT);
							invPrev.getItem(slot).setAmount(leftT);
							p.updateInventory();
							break;
						}
					}
				}
				p.updateInventory();
			}
		}
	}
}
