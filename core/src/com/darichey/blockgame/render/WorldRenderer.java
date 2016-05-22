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
import com.darichey.blockgame.world.chunk.Chunk;

/**
 * Handles the rendering of the current {@link World}.
 */
public class WorldRenderer {

	/**
	 * The World being rendered.
	 */
	public static World world;

	/**
	 * SpriteBatch used to draw to the screen.
	 */
	private static SpriteBatch batch = new SpriteBatch();

	/**
	 * Width the camera's viewport.
	 */
	public static float viewportWidth = 40;

	/**
	 * Height of the camera's viewport.
	 */
	public static float viewportHeight = 23;

	/**
	 * The camera through which the player sees.
	 */
	public static OrthographicCamera camera = new OrthographicCamera(viewportWidth, viewportHeight);

	/**
	 * Render the world.
	 */
	public static void render() {
		camera.position.x = world.player.getPosition().x;
		camera.position.y = world.player.getPosition().y;

		camera.viewportWidth = viewportWidth;
		camera.viewportHeight = viewportHeight;

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (Chunk chunk : world.getLoadedChunks()) {
			for (int x = 0; x < Chunk.WIDTH; x++) {
				for (int y = 0; y < Chunk.HEIGHT; y++) {
					Vector2 worldPos = chunk.convertChunkToWorldPos(new Vector2(x, y));
					Block block = world.getBlockAt(worldPos);
					if (block != null && entityCanBeeSeenAt(block, worldPos)) {
						batch.draw(block.texture, worldPos.x, worldPos.y, block.width, block.height);
					}
				}
			}
		}

		for (DynamicEntity entity : world.getDynamicEntities()) {
			batch.draw(entity.texture, entity.getPosition().x, entity.getPosition().y, entity.width, entity.height);
		}

		batch.end();
	}

	/**
	 * Whether or not the given entity can be seen at the given world position.
	 * @param entity The entity.
	 * @param worldPos The world position.
	 * @return True if that entity can be seen there, false if otherwise.
	 */
	private static boolean entityCanBeeSeenAt(Entity entity, Vector2 worldPos) {
		Vector3 bottomLeft = camera.project(new Vector3(worldPos.x, worldPos.y, 0));
		Vector3 topLeft = camera.project(new Vector3(worldPos.x, worldPos.y + entity.height, 0));
		Vector3 bottomRight = camera.project(new Vector3(worldPos.x + entity.width, worldPos.y, 0));
		Vector3 topRight = camera.project(new Vector3(worldPos.x + entity.width, worldPos.y + entity.height, 0));

		return screenContainsPos(bottomLeft) || screenContainsPos(topLeft) || screenContainsPos(bottomRight) || screenContainsPos(topRight);
	}

	/**
	 * Whether or not the current screen contains the given pixel position.
	 * @param pos The pixel position.
	 * @return True if the position if present on the screen, false if otherwise.
	 */
	private static boolean screenContainsPos(Vector3 pos) {
		if (pos.x > 0 && pos.x < Gdx.graphics.getWidth()) {
			if (pos.y > 0 && pos.y < Gdx.graphics.getHeight()) {
				return true;
			}
		}
		return false;
	}
}
