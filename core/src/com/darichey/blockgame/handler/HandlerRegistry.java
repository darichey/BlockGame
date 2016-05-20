package com.darichey.blockgame.handler;

import java.util.ArrayList;

/**
 * A registry to store all of the handlers that need to be updated every frame.
 */
public class HandlerRegistry {

	/**
	 * List of registered handlers.
	 */
	private static ArrayList<IHandler> handlers = new ArrayList<IHandler>();

	/**
	 * Registers a new handler.
	 * @param handler The new handler.
	 */
	public static void register(IHandler handler) {
		handlers.add(handler);
	}

	/**
	 * Invoke the {@link IHandler#update(float)} method of every registered handler.
	 * @param deltaTime The time passed between this frame and the last.
	 */
	public static void updateAll(float deltaTime) {
		for (IHandler handler : handlers) {
			handler.update(deltaTime);
		}
	}
}
