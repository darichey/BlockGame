package com.darichey.blockgame.world;

import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.entity.dynamic.EntityPlayer;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains all of the entities and other game objects that the player can interact with.
 */
public class World {

	/**
	 * The width of the world.
	 */
	public static final int WIDTH = 100;

	/**
	 * The height of the world.
	 */
	public static final int HEIGHT = 100;

	/**
	 * The gravity of this world.
	 */
	public static final int GRAVITY_VELOCITY = 25;

	/**
	 * 2-Dimensional array of block IDs.
	 * @see BlockRegistry
	 */
	private int[][] blockMap = new int[WIDTH][HEIGHT];

	/**
	 * A list of {@link DynamicEntity}s in the world.
	 */
	private ArrayList<DynamicEntity> dynamicEntities = new ArrayList<DynamicEntity>();

	/**
	 * The {@link EntityPlayer} in the world.
	 */
	public EntityPlayer player = (EntityPlayer) spawnEntityAt(new EntityPlayer(), new Vector2(10, 10));

	public World() {
		for (int[] xRow : blockMap)
			Arrays.fill(xRow, 0);

		for (int i = 0; i < World.WIDTH; i++) {
			setBlockAt(Blocks.stone, new Vector2(i, 5));
		}

		for (int i = 5; i < 10; i++) {
			setBlockAt(Blocks.stone, new Vector2(i, 6));
		}
	}

	/**
	 * Gets the 2D block map.
	 * @return The block map.
	 */
	public int[][] getBlockMap() {
		return this.blockMap;
	}

	/**
	 * Gets the list of dynamic entities in the world.
	 * @return The dynamic entities.
	 */
	public ArrayList<DynamicEntity> getDynamicEntities() {
		return this.dynamicEntities;
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

	/**
	 * Spawns an entity at the given position.
	 * @param entity The entity.
	 * @param pos The position.
	 * @return The entity at the given position.
	 */
	public DynamicEntity spawnEntityAt(DynamicEntity entity, Vector2 pos) {
		entity.setPosition(pos);
		entity.setWorld(this);
		getDynamicEntities().add(entity);
		return entity;
	}
}
