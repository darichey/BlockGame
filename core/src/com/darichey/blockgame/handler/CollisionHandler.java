package com.darichey.blockgame.handler;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.world.World;

public class CollisionHandler implements IHandler {

	private World world;

	public CollisionHandler(World world) {
		this.world = world;
	}

	@Override
	public void update(float deltaTime) {
		for (int x = 0; x < World.WIDTH; x++) {
			for (int y = 0; y < World.HEIGHT; y++) {
				Block block = world.getBlockAt(new Vector2(x, y));
				if (block != null) {
					Rectangle blockBounds = new Rectangle(x, y, block.width, block.height);
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

	private float getOverlap1D(float min1, float max1, float min2, float max2) {
		return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
	}
}
