package fr.phoenix.sineplugin.regions;

import org.bukkit.util.BlockVector;

import com.sk89q.worldguard.protection.flags.RegionGroup;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionsWG {

	private static BlockVector minOneSmallZone = new BlockVector(-98, 8, -89);
	private static BlockVector maxOneSmallZone = new BlockVector(-42, 32, 13);
	public static ProtectedRegion oneSmallZone = new ProtectedCuboidRegion("oneSmallZone", minOneSmallZone,
			maxOneSmallZone);

	private static BlockVector minOneLargeZone = new BlockVector(-108, 8, -89);
	private static BlockVector maxOneLargeZone = new BlockVector(-32, 32, 33);
	public static ProtectedRegion oneLargeZone = new ProtectedCuboidRegion("oneLargeZone", minOneLargeZone,
			maxOneLargeZone);

	private static BlockVector minTwoSmallZone = new BlockVector(42, 8, -89);
	private static BlockVector maxTwoSmallZone = new BlockVector(98, 32, 13);
	public static ProtectedRegion twoSmallZone = new ProtectedCuboidRegion("twoSmallZone", minTwoSmallZone,
			maxTwoSmallZone);

	private static BlockVector minTwoLargeZone = new BlockVector(32, 8, -89);
	private static BlockVector maxTwoLargeZone = new BlockVector(108, 32, 33);
	public static ProtectedRegion twoLargeZone = new ProtectedCuboidRegion("twoLargeZone", minTwoLargeZone,
			maxTwoLargeZone);

	private static BlockVector minOneSmallDef = new BlockVector(63, 14, -22);
	private static BlockVector maxOneSmallDef = new BlockVector(77, 32, -8);
	public static ProtectedRegion oneSmallDef = new ProtectedCuboidRegion("oneSmallDef", minOneSmallDef,
			maxOneSmallDef);

	private static BlockVector minOneLargeDef = new BlockVector(59, 14, -16);
	private static BlockVector maxOneLargeDef = new BlockVector(81, 32, 6);
	public static ProtectedRegion oneLargeDef = new ProtectedCuboidRegion("oneLargeDef", minOneLargeDef,
			maxOneLargeDef);

	private static BlockVector minTwoSmallDef = new BlockVector(-77, 14, -22);
	private static BlockVector maxTwoSmallDef = new BlockVector(-63, 32, -8);
	public static ProtectedRegion twoSmallDef = new ProtectedCuboidRegion("twoSmallDef", minTwoSmallDef,
			maxTwoSmallDef);

	private static BlockVector minTwoLargeDef = new BlockVector(-81, 14, -16);
	private static BlockVector maxTwoLargeDef = new BlockVector(-59, 32, 6);
	public static ProtectedRegion twoLargeDef = new ProtectedCuboidRegion("twoLargeDef", minTwoLargeDef,
			maxTwoLargeDef);

	public void setFlag(ProtectedRegion zone, ProtectedRegion def) {

		zone.setFlag(DefaultFlag.HEAL_AMOUNT, 0);
		zone.setFlag(DefaultFlag.PVP, State.ALLOW);
		zone.setFlag(DefaultFlag.PVP.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setFlag(DefaultFlag.ENDERPEARL, State.ALLOW);
		zone.setFlag(DefaultFlag.ENDERPEARL.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setFlag(DefaultFlag.EXIT, State.DENY);
		zone.setFlag(DefaultFlag.EXIT.getRegionGroupFlag(), RegionGroup.NON_MEMBERS);
		zone.setFlag(DefaultFlag.USE, State.ALLOW);
		zone.setFlag(DefaultFlag.USE.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setFlag(DefaultFlag.LIGHTER, State.ALLOW);
		zone.setFlag(DefaultFlag.LIGHTER.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setFlag(DefaultFlag.BLOCK_BREAK, State.ALLOW);
		zone.setFlag(DefaultFlag.BLOCK_BREAK.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setFlag(DefaultFlag.BLOCK_PLACE, State.ALLOW);
		zone.setFlag(DefaultFlag.BLOCK_PLACE.getRegionGroupFlag(), RegionGroup.ALL);
		zone.setPriority(20);

		def.setFlag(DefaultFlag.TNT, State.ALLOW);
		def.setFlag(DefaultFlag.TNT.getRegionGroupFlag(), RegionGroup.ALL);
		def.setFlag(DefaultFlag.FIRE_SPREAD, State.ALLOW);
		def.setFlag(DefaultFlag.OTHER_EXPLOSION, State.ALLOW);
		def.setPriority(40);

	}

}
