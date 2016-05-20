package com.darichey.blockgame.entity.dynamic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.Entity;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.world.World;

/**
 * An entity that does not have a fixed position and may move freely of the BlockMap.
 */
public class DynamicEntity extends Entity {

	/**
	 * The position of this entity.
	 */
	private Vector2 position;

	/**
	 * The velocity of this entity.
	 */
	private Vector2 velocity;

	/**
	 * The world this entity belongs to.
	 */
	private World world;

	/**
	 * The speed at which this entity moves.
	 */
	public float speed;

	/**
	 * The damping speed that should be applied to this entity when moving.
	 */
	public float dampingSpeed;

	/**
	 * Whether or not this entity is affected by gravity.
	 */
	public boolean isAffectedByGravity = true;

	DynamicEntity() {
		this.position = Vector2.Zero;
		this.velocity = Vector2.Zero;
	}

	/**
	 * Gets the current position of the entity.
	 * @return The position.
	 */
	public Vector2 getPosition() {
		return this.position;
	}

	/**
	 * Sets the position of the entity.
	 * @param position The new position.
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * Sets the world of the entity.
	 * @param world The world.
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Gets the current velocity of the entity.
	 * @return The velocity.
	 */
	public Vector2 getVelocity() {
		return this.velocity;
	}

	/**
	 * Gets the bounding box of this entity as defined by its current position and dimensions.
	 * @return The bounding box of this entity.
	 */
	public Rectangle getBounds() {
		return new Rectangle(getPosition().x, getPosition().y, width, height);
	}

	/**
	 * Gets whether this entity is on the ground or not.
	 * @return True if on the ground, false if otherwise.
	 */
	public boolean isOnGround() {
		// At most, the player can be standing on two blocks at once. This is true if one of them is solid and can be walked on.
		boolean isStandingOnSolid;

		// If the player is standing on two blocks, we need to check both. Otherwise, we just need to check the one directly below him
		if (Math.floor(getPosition().x) != Math.ceil(getPosition().x)) {
			Block leftBlock = world.getBlockAt(new Vector2((int)Math.floor(getPosition().x), getPosition().y - 1));
			Block rightBlock = world.getBlockAt(new Vector2((int)Math.ceil(getPosition().x), getPosition().y - 1));

			isStandingOnSolid = (leftBlock != null) || (rightBlock != null);
		} else {
			isStandingOnSolid = (world.getBlockAt(new Vector2((int)getPosition().x, getPosition().y - 1)) != null);
		}

		return (getPosition().y == Math.round(getPosition().y)) && isStandingOnSolid;
	}
}
