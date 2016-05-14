package com.darichey.blockgame.register;

import com.darichey.blockgame.entity.block.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {
	private static HashMap<Integer, Block> blockRegistry = new HashMap<Integer, Block>();

	public static void register(int id, Block block) {
		blockRegistry.put(id, block);
	}

	public static Block getBlockForID(int id) {
		return blockRegistry.get(id);
	}

	public static int getIDForBlock(Block block) {
		for (Map.Entry entry : blockRegistry.entrySet()) {
			if (entry.getValue().equals(block)) {
				return (Integer) entry.getKey();
			}
		}
		return -1;
	}
}
