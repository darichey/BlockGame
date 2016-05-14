package com.darichey.blockgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darichey.blockgame.entity.block.BlockStone;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;
import com.darichey.blockgame.render.WorldRenderer;
import com.darichey.blockgame.world.World;

public class BlockGame extends ApplicationAdapter {
	@Override
	public void create () {
		Blocks.init();
		World world = new World();
		WorldRenderer.world = world;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.537f, 0.929f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		WorldRenderer.render();
	}
}
