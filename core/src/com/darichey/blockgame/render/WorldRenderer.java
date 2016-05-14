package com.darichey.blockgame.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.darichey.blockgame.entity.Entity;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.world.World;

public class WorldRenderer {
	public static World world;
	private static SpriteBatch batch = new SpriteBatch();

	public static float viewportWidth = 40;
	public static float viewportHeight = 23;
	private static OrthographicCamera camera = new OrthographicCamera(viewportWidth, viewportHeight);


	public static void render() {
		camera.position.x = world.player.getPosition().x;
		camera.position.y = world.player.getPosition().y;

		camera.viewportWidth = viewportWidth;
		camera.viewportHeight = viewportHeight;

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (int x = 0; x < World.WIDTH; x++) {
			for (int y = 0; y < World.HEIGHT; y++) {
				Block block = world.getBlockAt(x, y);
				if (block != null && entityCanBeeSeenAt(block, new Vector2(x, y))) {
					batch.draw(block.texture, x, y, block.width, block.height);
				}
			}
		}

		for (DynamicEntity entity : world.getDynamicEntities()) {
			batch.draw(entity.texture, entity.getPosition().x, entity.getPosition().y, entity.width, entity.height);
		}

		batch.end();
	}

	private static boolean entityCanBeeSeenAt(Entity entity, Vector2 worldPos) {
		Vector3 bottomLeft = camera.project(new Vector3(worldPos.x, worldPos.y, 0));
		Vector3 topLeft = camera.project(new Vector3(worldPos.x, worldPos.y + entity.height, 0));
		Vector3 bottomRight = camera.project(new Vector3(worldPos.x + entity.width, worldPos.y, 0));
		Vector3 topRight = camera.project(new Vector3(worldPos.x + entity.width, worldPos.y + entity.height, 0));

		return screenContainsPos(bottomLeft) || screenContainsPos(topLeft) || screenContainsPos(bottomRight) || screenContainsPos(topRight);
	}

	private static boolean screenContainsPos(Vector3 pos) {
		if (pos.x > 0 && pos.x < Gdx.graphics.getWidth()) {
			if (pos.y > 0 && pos.y < Gdx.graphics.getHeight()) {
				return true;
			}
		}
		return false;
	}
}
