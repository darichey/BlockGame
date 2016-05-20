package com.darichey.blockgame.entity.dynamic;

import com.darichey.blockgame.reference.TextureReference;

public class EntityPlayer extends DynamicEntity {
	public EntityPlayer() {
		this.texture = TextureReference.player;
		this.name = "Player";
		this.width = 1;
		this.height = 2;
		this.speed = 6F;
		this.dampingSpeed = .08F;
	}
}
