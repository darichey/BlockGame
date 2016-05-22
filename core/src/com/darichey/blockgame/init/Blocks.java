package com.darichey.blockgame.init;

import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.block.BlockDirt;
import com.darichey.blockgame.entity.block.BlockGrass;
import com.darichey.blockgame.entity.block.BlockStone;
import com.darichey.blockgame.register.BlockRegistry;

/**
 * Holds references to {@link Block} objects.
 */
public class Blocks {

	public static final Block stone = new BlockStone();
	public static final Block dirt = new BlockDirt();
	public static final Block grass = new BlockGrass();

	/**
	 * Register each Block in the {@link BlockRegistry}.
	 */
	public static void init() {
		BlockRegistry.register(1, stone);
		BlockRegistry.register(2, dirt);
		BlockRegistry.register(3, grass);
	}
}
