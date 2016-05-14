package com.darichey.blockgame.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.world.World;

import java.util.Arrays;

public class WorldRenderer {
	public static World world;
	private static SpriteBatch batch = new SpriteBatch();
	private static OrthographicCamera camera = new OrthographicCamera(40, 23);


	public static void render() {
		camera.position.x = 0;
		camera.position.y = 0;
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (int x = 0; x < World.WIDTH; x++) {
			for (int y = 0; y < World.HEIGHT; y++) {
				Block block = world.getBlockAt(x, y);
				if (block != null) {
					batch.draw(block.texture, x, y, block.width, block.height);
				}
			}
		}

		batch.end();
	}
}
