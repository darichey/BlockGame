package com.darichey.blockgame.world;

import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;

import java.util.Arrays;

public class World {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;

	private int[][] blockMap = new int[WIDTH][HEIGHT];

	public World() {
		for (int[] xRow : blockMap)
			Arrays.fill(xRow, 0);

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				setBlockAt(Blocks.stone, x, y);
			}
		}
	}

	public int[][] getBlockMap() {
		return this.blockMap;
	}

	public Block getBlockAt(int x, int y) {
		return BlockRegistry.getBlockForID(blockMap[x][y]);
	}

	public void setBlockAt(Block block, int x, int y) {
		this.blockMap[x][y] = BlockRegistry.getIDForBlock(block);
	}
}
