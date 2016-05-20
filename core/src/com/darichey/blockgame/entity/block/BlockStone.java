package com.darichey.blockgame.entity.block;

import com.darichey.blockgame.reference.TextureReference;

/**
 * Stone Block.
 */
public class BlockStone extends Block {
	public BlockStone() {
		this.name = "Stone";
		this.texture = TextureReference.stone;
	}
}
