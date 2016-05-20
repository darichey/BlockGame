package com.darichey.blockgame.world;

import com.badlogic.gdx.math.Vector2;
import com.darichey.blockgame.entity.block.Block;
import com.darichey.blockgame.entity.dynamic.DynamicEntity;
import com.darichey.blockgame.entity.dynamic.EntityPlayer;
import com.darichey.blockgame.init.Blocks;
import com.darichey.blockgame.register.BlockRegistry;

import java.util.ArrayList;
import java.util.Arrays;

public class World {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;

	private int[][] blockMap = new int[WIDTH][HEIGHT];
	private ArrayList<DynamicEntity> dynamicEntities = new ArrayList<DynamicEntity>();

	public EntityPlayer player = new EntityPlayer(this, new Vector2(10, 10));

	public World() {
		for (int[] xRow : blockMap)
			Arrays.fill(xRow, 0);

		for (int i = 0; i < World.WIDTH; i++) {
			setBlockAt(Blocks.stone, new Vector2(i, 5));
		}

		for (int i = 5; i < 10; i++) {
			setBlockAt(Blocks.stone, new Vector2(i, 6));
		}
	}

	public int[][] getBlockMap() {
		return this.blockMap;
	}

	public ArrayList<DynamicEntity> getDynamicEntities() {
		return this.dynamicEntities;
	}

	public Block getBlockAt(Vector2 pos) {
		return BlockRegistry.getBlockForID(blockMap[(int) pos.x][(int) pos.y]);
	}

	public void setBlockAt(Block block, Vector2 pos) {
		this.blockMap[(int) pos.x][(int) pos.y] = BlockRegistry.getIDForBlock(block);
	}
}
