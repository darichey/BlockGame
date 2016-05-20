package com.darichey.blockgame.handler;

import com.darichey.blockgame.entity.dynamic.EntityPlayer;

/**
 * Handles movement for the given player. (Updates the position of the player based on its velocity)
 */
public class MovementHandler implements IHandler {

	/**
	 * The player this handler is handling movement for.
	 */
	private EntityPlayer player;

	public MovementHandler(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void update(float deltaTime) {
		player.getPosition().add(player.getVelocity().x * deltaTime, player.getVelocity().y * deltaTime);
		player.getVelocity().x *= player.dampingSpeed;
	}
}
