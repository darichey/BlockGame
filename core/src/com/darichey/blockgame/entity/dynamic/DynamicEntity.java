package com.darichey.blockgame.entity.dynamic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.Entity;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.init.Blocks;
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
