package com.darichey.blockgame.register;

import com.darichey.blockgame.entity.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Glorified Bi-Map implementation to register {@link Block} objects and assign them a unique numerical value.
 */
public class BlockRegistry {

	/**
	 * The registered Blocks.
	 */
	private static HashMap<Integer, Block> blockRegistry = new HashMap<Integer, Block>();

	/**
	 * Register a new block with the given ID.
	 * @param id The id with which to register this block.
	 * @param block The block to register.
	 */
	public static void register(int id, Block block) {
		blockRegistry.put(id, block);
	}

	/**
	 * Gets a block from the given ID.
	 * @param id The ID of the block.
	 * @return The Block associated with that ID.
	 */
	public static Block getBlockForID(int id) {
		return blockRegistry.get(id);
	}

	/**
	 * Gets an ID from the given Block.
	 * @param block The Block
	 * @return The ID associated with that Block.
	 */
	public static int getIDForBlock(Block block) {
		for (Map.Entry entry : blockRegistry.entrySet()) {
			if (entry.getValue().equals(block)) {
				return (Integer) entry.getKey();
			}
		}
		return -1;
	}
}
