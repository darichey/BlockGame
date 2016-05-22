package com.darichey.blockgame.init;

import com.darichey.blockgame.entity.block.*;
import com.darichey.blockgame.register.BlockRegistry;

/**
 * Holds references to {@link Block} objects.
 */
public class Blocks {

	public static final Block stone = new BlockStone();
	public static final Block dirt = new BlockDirt();
	public static final Block grass = new BlockGrass();
	public static final Block bedrock = new BlockBedrock();

	/**
	 * Register each Block in the {@link BlockRegistry}.
	 */
	public static void init() {
		BlockRegistry.register(1, stone);
		BlockRegistry.register(2, dirt);
		BlockRegistry.register(3, grass);
		BlockRegistry.register(4, bedrock);
	}
}
