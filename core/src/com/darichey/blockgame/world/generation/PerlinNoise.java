package com.darichey.blockgame.world.generation;

import com.darichey.blockgame.world.chunk.Chunk;

public class PerlinNoise {
	private long seed;

	public PerlinNoise(long seed) {
		this.seed = seed;
	}

	public int getNoise(int x, int range) {
		int selectionSize = Chunk.WIDTH  * 16; // The distance between the selected points
		float noise = 0;

		range /= 2;
		while (selectionSize > 0) {

			int selectionIndex = x / selectionSize;

			float distanceToIndex = (x % selectionSize) / (float) (selectionSize); // The distance from a given x coordinate to the next selection index

			float leftRandom = random(selectionIndex, range);
			float rightRandom = random(selectionIndex + 1, range);

			noise += (1 - distanceToIndex) * leftRandom + distanceToIndex * rightRandom;

			selectionSize /= 2;
			range /= 2;

			range = Math.max(1, range);
		}

		return Math.round(noise);
	}

	private int random(int x, int range) {
		return (int) ((x + seed) ^ 10) % range;
	}
}

