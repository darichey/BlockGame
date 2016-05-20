package com.darichey.blockgame;

import com.badlogic.gdx.Game;
import com.darichey.blockgame.screen.GameScreen;

public class BlockGame extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
