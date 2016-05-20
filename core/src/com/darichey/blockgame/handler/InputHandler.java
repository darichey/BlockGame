package com.darichey.blockgame.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.darichey.blockgame.entity.dynamic.EntityPlayer;
import com.darichey.blockgame.render.WorldRenderer;

public class InputHandler extends InputAdapter implements IHandler {
	private EntityPlayer player;

	public InputHandler(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public boolean scrolled(int amount) {
		WorldRenderer.viewportWidth += amount;
		WorldRenderer.viewportHeight += amount;
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.E) {
			WorldRenderer.viewportWidth = 40;
			WorldRenderer.viewportHeight = 23;
		}

		// Jumping
		if (keycode == Input.Keys.SPACE && player.isOnGround()) {
			player.getVelocity().add(0, 10);
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.getVelocity().set(player.speed, player.getVelocity().y);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.getVelocity().set(-player.speed, player.getVelocity().y);
		}
	}
}
