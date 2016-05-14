package com.darichey.blockgame.entity.dynamic;

import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.reference.TextureReference;
import com.darichey.blockgame.world.World;

public class EntityPlayer extends DynamicEntity {
	public EntityPlayer(World world, Vector2 position) {
		super(world, position);
		this.texture = TextureReference.player;
		this.name = "Player";
		this.width = 1;
		this.height = 2;
		this.speed = 6F;
		this.dampingSpeed = .08F;
	}
}
