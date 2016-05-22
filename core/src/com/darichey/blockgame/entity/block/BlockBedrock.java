package com.darichey.blockgame.entity.block;

import com.darichey.blockgame.reference.TextureReference;

public class BlockBedrock extends Block {
	public BlockBedrock() {
		this.name = "Bedrock";
		this.texture = TextureReference.bedrock;
		this.isBreakable = false;
	}
}
