package com.darichey.blockgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darichey.blockgame.entity.block.BlockStone;
import com.darichey.blockgame.handler.HandlerRegistry;
import com.darichey.blockgame.handler.InputHandler;
import com.darichey.blockgame.handler.MovementHandler;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;
import com.darichey.blockgame.render.WorldRenderer;
import com.darichey.blockgame.screen.GameScreen;
import com.darichey.blockgame.world.World;

public class BlockGame extends Game {
	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}
