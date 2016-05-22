package com.darichey.blockgame.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.render.WorldRenderer;
import com.darichey.blockgame.world.World;

/**
 * Handles input from the mouse and keyboard.
 */
public class InputHandler extends InputAdapter implements IHandler {

	/**
	 * The world this handler is processing input for.
	 */
	private World world;

	public InputHandler(World world) {
		this.world = world;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 tempPos = WorldRenderer.camera.unproject(new Vector3(screenX, screenY, 0));
		Vector2 worldPos = new Vector2((int) Math.floor(tempPos.x), (int) Math.floor(tempPos.y));
		Block block = world.getBlockAt(worldPos);

		if (block == null)  {
			world.setBlockAt(Blocks.stone, worldPos);
		} else if (block.isBreakable) {
			world.setBlockAt(null, worldPos);
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		WorldRenderer.viewportWidth += 5 * amount;
		WorldRenderer.viewportHeight += 5 * amount;
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.E) {
			WorldRenderer.viewportWidth = 40;
			WorldRenderer.viewportHeight = 23;
		}

		// Jumping
		if (keycode == Input.Keys.SPACE && world.player.isOnGround()) {
			world.player.getVelocity().add(0, 10);
		}

		if (keycode == Input.Keys.SHIFT_LEFT) {
			world.player.isWalking = true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.SHIFT_LEFT) {
			world.player.isWalking = false;
		}

		return false;
	}

	@Override
	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			world.player.getVelocity().set(world.player.speed * (world.player.isWalking ? .5f : 1), world.player.getVelocity().y);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			world.player.getVelocity().set(-world.player.speed * (world.player.isWalking ? .5f : 1), world.player.getVelocity().y);
		}
	}
}
