package com.darichey.blockgame.handler;

import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.world.World;

/**
 * Handles the application of gravity in the given world.
 */
public class GravityHandler implements IHandler {

	/**
	 * The world this handler is applying gravity to.
	 */
	private World world;

	public GravityHandler(World world) {
		this.world = world;
	}

	@Override
	public void update(float deltaTime) {
		for (DynamicEntity entity : world.getDynamicEntities()) {
			if (!entity.isOnGround() && entity.isAffectedByGravity) {
				entity.getVelocity().sub(0, World.GRAVITY_VELOCITY * deltaTime);
			}
		}
	}
}
