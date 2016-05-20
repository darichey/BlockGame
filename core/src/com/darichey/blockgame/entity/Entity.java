package com.darichey.blockgame.entity;

import com.badlogic.gdx.graphics.Texture;

/**
 * Base entity class.
 */
public class Entity {

	/**
	 * The texture that should be drawn for this entity.
	 */
	public Texture texture;

	/**
	 * The name this entity should be referred to as.
	 */
	public String name;

	/**
	 * The width of this entity.
	 */
	public int width;

	/**
	 * The height of this entity.
	 */
	public int height;
}
