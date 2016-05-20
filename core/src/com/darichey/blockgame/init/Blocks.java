package com.darichey.blockgame.init;

import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.block.BlockStone;
import com.darichey.blockgame.register.BlockRegistry;

/**
 * Holds references to {@link Block} objects.
 */
public class Blocks {
	public static final Block stone = new BlockStone();

	/**
	 * Register each Block in the {@link BlockRegistry}.
	 */
	public static void init() {
		BlockRegistry.register(1, stone);
	}
}
