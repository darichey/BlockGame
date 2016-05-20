package com.darichey.blockgame.handler;

import com.darichey.blockgame.entity.dynamic.EntityPlayer;

public class MovementHandler implements IHandler {

	private EntityPlayer player;

	public MovementHandler(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void update(float deltaTime) {

		// Gravity handling
		if (!player.isOnGround()) {
			player.getVelocity().sub(0, 25 * deltaTime);
		}

		player.getPosition().add(player.getVelocity().x * deltaTime, player.getVelocity().y * deltaTime);
		player.getVelocity().x *= player.dampingSpeed;
	}
}
