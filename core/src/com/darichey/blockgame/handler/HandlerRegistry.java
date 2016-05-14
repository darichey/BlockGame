package com.darichey.blockgame.handler;

import java.util.ArrayList;

public class HandlerRegistry {
	private static ArrayList<IHandler> handlers = new ArrayList<IHandler>();

	public static void register(IHandler handler) {
		handlers.add(handler);
	}

	public static void updateAll(float deltaTime) {
		for (IHandler handler : handlers) {
			handler.update(deltaTime);
		}
	}
}
