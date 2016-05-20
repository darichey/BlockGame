package com.darichey.blockgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.darichey.blockgame.handler.*;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.render.WorldRenderer;
import com.darichey.blockgame.world.World;

/**
 * The screen presented when actually playing the game.
 */
public class GameScreen extends ScreenAdapter {

	/**
	 * The {@link World} the player is currently in.
	 */
	private World world;

	public GameScreen() {
		Blocks.init();

		world = new World();
		WorldRenderer.world = world;

		InputHandler inputHandler = new InputHandler(world);
		MovementHandler movementHandler = new MovementHandler(world.player);
		CollisionHandler collisionHandler = new CollisionHandler(world);
		GravityHandler gravityHandler = new GravityHandler(world);

		HandlerRegistry.register(inputHandler);
		HandlerRegistry.register(movementHandler);
		HandlerRegistry.register(collisionHandler);
		HandlerRegistry.register(gravityHandler);
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0.537f, 0.929f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		WorldRenderer.render();

		HandlerRegistry.updateAll(deltaTime);
	}
}
