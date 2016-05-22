package com.darichey.blockgame.reference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Holds references to textures of game objects.
 */
public class TextureReference {
	public static Texture stone  = new Texture(Gdx.files.internal("stone.png"));
	public static Texture dirt   = new Texture(Gdx.files.internal("dirt.png"));
	public static Texture grass  = new Texture(Gdx.files.internal("grass.png"));
	public static Texture player = new Texture(Gdx.files.internal("player.png"));
}
