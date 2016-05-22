package com.darichey.blockgame.world;

import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.entity.dynamic.EntityPlayer;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.world.chunk.Chunk;
import com.darichey.blockgame.world.generation.PerlinNoise;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Contains all of the entities and other game objects that the player can interact with.
 */
public class World {

	/**
	 * The gravity of this world.
	 */
	public static final int GRAVITY_VELOCITY = 25;

	/**
	 * A hashmap of the {@link Chunk}s that make up the world. The Integer key is the x-position of the chunk in the world.
	 */
	private HashMap<Integer, Chunk> chunks = new HashMap<Integer, Chunk>();

	/**
	 * A list of {@link DynamicEntity}s in the world.
	 */
	private ArrayList<DynamicEntity> dynamicEntities = new ArrayList<DynamicEntity>();

	/**
	 * The {@link EntityPlayer} in the world.
	 */
	public EntityPlayer player = (EntityPlayer) spawnEntityAt(new EntityPlayer(), new Vector2(0, 5));

	PerlinNoise noise = new PerlinNoise(new Random().nextLong());

	public World() {

		for (int i = -8; i < 8; i++) {
			Chunk chunk = new Chunk(i);
			chunks.put(i, chunk);
		}

		PerlinNoise noise = new PerlinNoise(new Random().nextLong());

		for (int x = -128; x < 128; x++) {
			int columnHeight = noise.getNoise(x, 320 );
			for (int y = 0; y < columnHeight; y++) {
				Block block = (y == columnHeight - 1) ? Blocks.grass : Blocks.dirt;
				setBlockAt(block, new Vector2(x, y));
			}
		}

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
		return getChunkForPos(pos).getBlockAt(getChunkForPos(pos).convertWorldToChunkPos(pos));
	}

	/**
	 * Sets the block at the given position to the given block.
	 * @param block The block.
	 * @param pos The position in the world.
	 */
	public void setBlockAt(Block block, Vector2 pos) {
		Chunk chunk = getChunkForPos(pos);
		chunk.setBlockAt(block, chunk.convertWorldToChunkPos(pos));
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

	public Chunk getChunkForPos(Vector2 pos) {
		int x;
		if (pos.x < 0) {
			x = (int) (-1 * Math.ceil(Math.abs(pos.x) / Chunk.WIDTH));
		} else {
			x = (int) Math.floor(pos.x / Chunk.WIDTH);
		}

		return chunks.get(x);
	}

	public ArrayList<Chunk> getChunks() {
		return new ArrayList<Chunk>(chunks.values());
	}
}
