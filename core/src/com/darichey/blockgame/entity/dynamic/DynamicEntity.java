package com.darichey.blockgame.entity.dynamic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.Entity;
import com.darichey.blockgame.world.World;

public class DynamicEntity extends Entity {
	private Vector2 position;
	private Vector2 velocity;

	private World world;

	public float speed;
	public float dampingSpeed;

	public boolean isAffectedByGravity = true;

	public DynamicEntity(World world, Vector2 position) {
		this(world);
		this.position = position;
	}

	public DynamicEntity(World world) {
		this.world = world;
		this.position = Vector2.Zero;
		this.velocity = Vector2.Zero;
		world.getDynamicEntities().add(this);
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public Vector2 getVelocity() {
		return this.velocity;
	}

	public Rectangle getBounds() {
		return new Rectangle(getPosition().x, getPosition().y, width, height);
	}

	public boolean isOnGround() {
		return (getPosition().y == (int) getPosition().y) && (world.getBlockAt(new Vector2(getPosition().x, getPosition().y - 1)) != null);
	}
}
