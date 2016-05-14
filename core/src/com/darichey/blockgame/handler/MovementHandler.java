package com.darichey.blockgame.handler;

import com.darichey.blockgame.entity.dynamic.EntityPlayer;

public class MovementHandler implements IHandler {

	private EntityPlayer player;

	public MovementHandler(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void update(float deltaTime) {
		player.getPosition().add(player.getVelocity().scl(deltaTime));
		player.getVelocity().scl(player.dampingSpeed);
	}
}
