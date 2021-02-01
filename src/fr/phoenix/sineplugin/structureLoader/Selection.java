package fr.phoenix.sineplugin.structureLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.phoenix.sineplugin.structureLoader.StructureLoader.API;

public class Selection {
	private Location leftClickLocation;
	private Location rightClickLocation;
	private List<SavedBlock> savedBlocks;

	public Selection() {
		this.savedBlocks = new ArrayList<SavedBlock>();
	}

	public Selection(JSONArray savedBlockData) {
		this();
		Iterator<?> iterator = savedBlockData.iterator();

		while (iterator.hasNext()) {
			this.savedBlocks.add(new SavedBlock((JSONObject) iterator.next()));
		}

	}

	public Location getLeftClickLocation() {
		return this.leftClickLocation;
	}

	public Location getRightClickLocation() {
		return this.rightClickLocation;
	}

	public void setLeftClickLocation(Location location) {
		this.leftClickLocation = location;
	}

	public void setRightClickLocation(Location location) {
		this.rightClickLocation = location;
	}

	public void saveCurrentSelection() {
		ArrayList<SavedBlock> blocks = new ArrayList<SavedBlock>();
		if (this.leftClickLocation != null && this.rightClickLocation != null) {
			int xMin = Math.min(this.leftClickLocation.getBlockX(), this.rightClickLocation.getBlockX());
			int xMax = Math.max(this.leftClickLocation.getBlockX(), this.rightClickLocation.getBlockX());
			int yMin = Math.min(this.leftClickLocation.getBlockY(), this.rightClickLocation.getBlockY());
			int yMax = Math.max(this.leftClickLocation.getBlockY(), this.rightClickLocation.getBlockY());
			int zMin = Math.min(this.leftClickLocation.getBlockZ(), this.rightClickLocation.getBlockZ());
			int zMax = Math.max(this.leftClickLocation.getBlockZ(), this.rightClickLocation.getBlockZ());
			World world = this.leftClickLocation.getWorld();
			Location baseBlock = new Location(world, (double) xMin, (double) yMin, (double) zMin);

			for (int x = xMin; x <= xMax; ++x) {
				for (int y = yMin; y <= yMax; ++y) {
					for (int z = zMin; z <= zMax; ++z) {
						Location l = new Location(world, (double) x, (double) y, (double) z);
						blocks.add(new SavedBlock(baseBlock, l.getBlock()));
					}
				}
			}
		}

		this.savedBlocks = blocks;
	}

	public void saveCurrentSelectionOnlyAbove(Material baseType, byte baseData, boolean ignoreBaseBlocks) {
		ArrayList<SavedBlock> blocks = new ArrayList<SavedBlock>();
		if (this.leftClickLocation != null && this.rightClickLocation != null) {
			int xMin = Math.min(this.leftClickLocation.getBlockX(), this.rightClickLocation.getBlockX());
			int xMax = Math.max(this.leftClickLocation.getBlockX(), this.rightClickLocation.getBlockX());
			int yMin = Math.min(this.leftClickLocation.getBlockY(), this.rightClickLocation.getBlockY());
			int yMax = Math.max(this.leftClickLocation.getBlockY(), this.rightClickLocation.getBlockY());
			int zMin = Math.min(this.leftClickLocation.getBlockZ(), this.rightClickLocation.getBlockZ());
			int zMax = Math.max(this.leftClickLocation.getBlockZ(), this.rightClickLocation.getBlockZ());
			World world = this.leftClickLocation.getWorld();
			Location baseBlock = new Location(world, (double) xMin, (double) yMin, (double) zMin);

			for (int it = xMin; it <= xMax; ++it) {
				for (int savedBlock = yMin; savedBlock <= yMax; ++savedBlock) {
					for (int z = zMin; z <= zMax; ++z) {
						Location l = new Location(world, (double) it, (double) savedBlock, (double) z);
						Block b = l.getBlock();
						if (API.isPartOfShape(b, baseType, baseData)) {
							blocks.add(new SavedBlock(baseBlock, b));
						}
					}
				}
			}

			if (ignoreBaseBlocks) {
				Iterator<SavedBlock> arg17 = blocks.iterator();

				while (arg17.hasNext()) {
					SavedBlock arg18 = (SavedBlock) arg17.next();
					if (arg18.getType() == baseType && arg18.getData() == baseData) {
						arg17.remove();
					}
				}
			}
		}

		this.savedBlocks = blocks;
	}

	public List<SavedBlock> getSavedBlocks() {
		return this.savedBlocks;
	}

	@SuppressWarnings("unchecked")
	public JSONArray toJSON() {
		JSONArray blockDataArray = new JSONArray();
		Iterator<SavedBlock> arg2 = this.savedBlocks.iterator();

		while (arg2.hasNext()) {
			SavedBlock block = (SavedBlock) arg2.next();
			blockDataArray.add(block.toJSON());
		}

		return blockDataArray;
	}
}