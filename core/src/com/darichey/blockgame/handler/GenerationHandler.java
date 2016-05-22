package com.darichey.blockgame.handler;

import com.darichey.blockgame.world.World;
import com.darichey.blockgame.world.chunk.Chunk;

public class GenerationHandler implements IHandler {

	private World world;

	public GenerationHandler(World world) {
		this.world = world;
	}

	@Override
	public void update(float deltaTime) {
		int playerChunkPos = world.getChunkForWorldPos(world.player.getPosition()).getPosition();

		Chunk leftChunk = world.getChunkAt(playerChunkPos - World.LOAD_RADIUS);
		Chunk rightChunk = world.getChunkAt(playerChunkPos + World.LOAD_RADIUS);

		if (leftChunk == null) {
			world.addChunk(new Chunk(world.noise, playerChunkPos - World.LOAD_RADIUS));
		} else if (rightChunk == null) {
			world.addChunk(new Chunk(world.noise, playerChunkPos + World.LOAD_RADIUS));
		}
	}
}
