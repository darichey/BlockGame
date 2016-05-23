package com.darichey.blockgame.util;

import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.reference.TextureReference;
import com.darichey.blockgame.world.World;
import com.darichey.blockgame.world.chunk.Chunk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorldImage {

	private World world;

	public WorldImage(World world) {
		this.world = world;
	}

	public void writeImage() throws IOException {
		ArrayList<Chunk> chunks = world.getChunks();
		BufferedImage image = new BufferedImage(chunks.size() * Chunk.WIDTH, Chunk.HEIGHT, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setRGB(x, y, new Color(255, 255, 255).getRGB());
			}
		}

		int negativeOffset = 0;
		for (Chunk chunk : chunks) {
			if (chunk.getPosition() < 0) {
				negativeOffset++;
			}
		}
		negativeOffset *= 16;

		for (Chunk chunk : chunks) {
			for (int x = 0; x < Chunk.WIDTH; x++) {
				for (int y = 0; y < Chunk.HEIGHT; y++) {
					Vector2 worldPos = chunk.convertChunkToWorldPos(new Vector2(x, y));
					if (world.getBlockAt(worldPos) != null) {
						TextureData data = world.getBlockAt(worldPos).texture.getTextureData();
						data.prepare();
						int color = data.consumePixmap().getPixel(1, 1);
						System.out.println(new Color(color));
						image.setRGB((int) worldPos.x + negativeOffset, Chunk.HEIGHT - ((int) worldPos.y + 1), new Color(color, true).getRGB());
					}
				}
			}
		}

		ImageIO.write(image, "png", new File("world.png"));
	}
}
