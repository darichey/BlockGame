package com.darichey.blockgame.handler;

/**
 * Base handler class.
 */
public interface IHandler {

	/**
	 * Handle whatever needs to be handled.
	 * @param deltaTime The time passed between this frame and the last one. See {@link com.badlogic.gdx.Screen#render(float)}
	 */
	void update(float deltaTime);
}
