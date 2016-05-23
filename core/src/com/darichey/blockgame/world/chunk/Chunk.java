package com.darichey.blockgame.world.chunk;

import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;
import com.darichey.blockgame.util.PerlinNoise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 16 block wide section of the world.
 */
public class Chunk {

	/**
	 * The width of the world.
	 */
	public static final int WIDTH = 16;

	/**
	 * The height of the world.
	 */
	public static final int HEIGHT = 256;

	/**
	 * The x-position of this chunk in the world.
	 */
	private final int xPos;

	/**
	 * A list of the x-positions this chunk contains for the world.
	 */
	private List<Integer> worldBlockXs = new ArrayList<Integer>(16);

	/**
	 * 2-Dimensional array of block IDs.
	 * @see BlockRegistry
	 */
	private int[][] blockMap = new int[WIDTH][HEIGHT];

	private PerlinNoise noise;

	public Chunk(PerlinNoise noise, int xPos) {
		this.noise = noise;
		this.xPos = xPos;

		worldBlockXs.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

		for (int i = 0; i < worldBlockXs.size(); i++) {
			worldBlockXs.set(i, i + (WIDTH * xPos));
		}

		for (int[] xRow : blockMap)
			Arrays.fill(xRow, 0);

		generateRandomTerrain();
		//generateTestTerrain();
	}

	private void generateRandomTerrain() {
		for (int x = worldBlockXs.get(0); x < worldBlockXs.get(worldBlockXs.size() - 1) + 1; x++) {
			int columnHeight = noise.getNoise(x, HEIGHT);
			for (int y = 0; y < columnHeight; y++) {
				Block block;
				if (y < columnHeight - 5) {
					block = Blocks.stone;
				} else if (y == columnHeight - 1) {
					block = Blocks.grass;
				} else {
					block = Blocks.dirt;
				}
				if (y == 0) block = Blocks.bedrock;

				setBlockAt(block, convertWorldToChunkPos(new Vector2(x, y)));
			}
		}
	}

	private void generateTestTerrain() {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < 120; y++) {
				setBlockAt(Blocks.stone, new Vector2(x, y));
			}
		}

		for (int x = 0; x < WIDTH; x++) {
			for (int y = 120; y < 128; y++) {
				setBlockAt(Blocks.dirt, new Vector2(x, y));
			}
		}

		for (int x = 0; x < WIDTH; x++) {
			setBlockAt(Blocks.grass, new Vector2(x, 128));
		}
	}

	/**
	 * Gets the {@link Block} at the given position.
	 * @param pos The position in the world.
	 * @return The Block at that position.
	 */
	public Block getBlockAt(Vector2 pos) {
		return BlockRegistry.getBlockForID(blockMap[(int) pos.x][(int) pos.y]);
	}

	/**
	 * Sets the block at the given position to the given block.
	 * @param block The block.
	 * @param pos The position in the world.
	 */
	public void setBlockAt(Block block, Vector2 pos) {
		this.blockMap[(int) pos.x][(int) pos.y] = BlockRegistry.getIDForBlock(block);
	}

	public Vector2 convertWorldToChunkPos(Vector2 worldPos) {
		return new Vector2(worldBlockXs.indexOf((int) worldPos.x), worldPos.y);
	}

	public Vector2 convertChunkToWorldPos(Vector2 chunkPos) {
		return new Vector2(worldBlockXs.get((int) chunkPos.x), chunkPos.y);
	}

	public int getPosition() {
		return this.xPos;
	}
}
