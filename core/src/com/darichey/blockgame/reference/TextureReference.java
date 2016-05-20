package com.darichey.blockgame.reference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Holds references to textures of game objects.
 */
public class TextureReference {
	public static Texture stone = new Texture(Gdx.files.internal("stone.png"));
	public static Texture player = new Texture(Gdx.files.internal("player.png"));
}
