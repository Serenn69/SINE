package fr.phoenix.sineplugin.structureLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StructureLoader {

	public static class API {
		public static void writeToFile(String filename, Selection selection) throws IOException {
			File arenaDB = new File("plugins/SINE/ArenaDatabase");
			if (!arenaDB.exists()) {
				arenaDB.mkdirs();
			}

			FileWriter writer = new FileWriter("plugins/SINE/ArenaDatabase/" + filename + ".json");
			writer.write(selection.toJSON().toJSONString());
			writer.close();
		}

		public static JSONArray readFromFile(String path) throws FileNotFoundException, IOException, ParseException {
			return (JSONArray) (new JSONParser()).parse(new FileReader(path));
		}

		@SuppressWarnings("deprecation")
		public static void placeStructure(String fileName, Location baseLocation) {
			try {
				Selection selection = new Selection(readFromFile("plugins/SINE/ArenaDatabase/" + fileName + ".json"));
				ArrayList<SavedBlock> e = new ArrayList<SavedBlock>();
				Iterator<SavedBlock> arg4 = selection.getSavedBlocks().iterator();

				SavedBlock savedBlock;
				Block realBlock;
				while (arg4.hasNext()) {
					savedBlock = (SavedBlock) arg4.next();
					if (savedBlock.isAttachable()) {
						e.add(0, savedBlock);
					} else {
						realBlock = baseLocation.clone().add((double) savedBlock.getRelativeX(),
								(double) savedBlock.getRelativeY(), (double) savedBlock.getRelativeZ()).getBlock();
						realBlock.setType(savedBlock.getType());
					//	realBlock.setData(savedBlock.getData());
					}
				}

				arg4 = e.iterator();

				while (arg4.hasNext()) {
					savedBlock = (SavedBlock) arg4.next();
					realBlock = baseLocation.clone().add((double) savedBlock.getRelativeX(),
							(double) savedBlock.getRelativeY(), (double) savedBlock.getRelativeZ()).getBlock();
					Block below = realBlock.getRelative(BlockFace.DOWN);
					Material belowType = below.getType();
				//	byte belowData = below.getData();
					if (!below.getType().isSolid()) {
						below.setType(Material.STONE);
					}

					realBlock.setType(savedBlock.getType());
				//	realBlock.setData(savedBlock.getData());
					below.setType(belowType);
				//	below.setData(belowData);
				}
			} catch (ParseException | IOException arg9) {
				arg9.printStackTrace();
			}

		}

		@SuppressWarnings("deprecation")
		public static boolean isPartOfShape(Block block, Material baseType, byte baseData) {
			Location bedrock = block.getLocation();
			bedrock.setY(0.0D);

			while (bedrock.getY() <= (double) block.getLocation().getBlockY()) {
				Block currentBlock = bedrock.getBlock();
				if (currentBlock.getType() == baseType && currentBlock.getData() == baseData) {
					return true;
				}

				bedrock.add(0.0D, 1.0D, 0.0D);
			}

			return false;
		}
	}
}
