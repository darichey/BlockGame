package com.darichey.blockgame.handler;

import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.world.World;

public class GravityHandler implements IHandler {

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
