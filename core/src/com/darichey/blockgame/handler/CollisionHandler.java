package com.darichey.blockgame.handler;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.world.World;
import com.darichey.blockgame.world.chunk.Chunk;

/**
 * Handles collision between entities in the world.
 */
public class CollisionHandler implements IHandler {

	/**
	 * The world this handler is handling collision for.
	 */
	private World world;

	public CollisionHandler(World world) {
		this.world = world;
	}

	@Override
	public void update(float deltaTime) {
		for (Chunk chunk : world.getLoadedChunks()) {
			for (int x = 0; x < Chunk.WIDTH; x++) {
				for (int y = 0; y < Chunk.HEIGHT; y++) {
					Vector2 worldPos = chunk.convertChunkToWorldPos(new Vector2(x, y));
					Block block = world.getBlockAt(worldPos);
					if (block != null) {
						Rectangle blockBounds = new Rectangle(worldPos.x, worldPos.y, block.width, block.height);
						Rectangle playerBounds = world.player.getBounds();

						if (playerBounds.overlaps(blockBounds)) {
							float overlapX = getOverlap1D(playerBounds.x, playerBounds.x + playerBounds.width, blockBounds.x, blockBounds.x + blockBounds.width);
							float overlapY = getOverlap1D(playerBounds.y, playerBounds.y + playerBounds.height, blockBounds.y, blockBounds.y + blockBounds.height);

							if (overlapX < overlapY) { // x < y so we need to resolve x
								world.player.getPosition().add(world.player.getVelocity().x > 0 ? -overlapX : overlapX, 0); // add or subtract the overlap depending on which direction the player is moving
								world.player.getVelocity().x = 0;
							} else { // resolve y
								world.player.getPosition().add(0, world.player.getVelocity().y > 0 ? -overlapY : overlapY);
								world.player.getVelocity().y = 0;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the amount of one-dimensional overlap between two positions.
	 * @param min1 The minimum of the first line.
	 * @param max1 The maximum of the first line.
	 * @param min2 The minimum of the second line.
	 * @param max2 The maximum of the second line.
	 * @return The amount of overlap between the two lines.
	 */
	private float getOverlap1D(float min1, float max1, float min2, float max2) {
		return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
	}
}
