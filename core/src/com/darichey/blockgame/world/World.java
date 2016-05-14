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

		for (int i = 0; i < 5; i++) {
			setBlockAt(Blocks.stone, i, 0);
		}

		for (int i = 0; i < 4; i++) {
			setBlockAt(Blocks.stone, i, 1);
		}

		for (int i = 0; i < 3; i++) {
			setBlockAt(Blocks.stone, i, 2);
		}

		for (int i = 0; i < 2; i++) {
			setBlockAt(Blocks.stone, i, 3);
		}

		for (int i = 0; i < 1; i++) {
			setBlockAt(Blocks.stone, i, 4);
		}

		for (int i = 6; i < 11; i++) {
			setBlockAt(Blocks.stone, i, 0);
		}

		for (int i = 7; i < 11; i++) {
			setBlockAt(Blocks.stone, i, 1);
		}

		for (int i = 8; i < 11; i++) {
			setBlockAt(Blocks.stone, i, 2);
		}

		for (int i = 9; i < 11; i++) {
			setBlockAt(Blocks.stone, i, 3);
		}

		for (int i = 10; i < 11; i++) {
			setBlockAt(Blocks.stone, i, 4);
		}

		/*
		for (int i = 0; i < 50; i++) {
			setBlockAt(Blocks.stone, 0, i);
		}

		for (int i = 1; i < 50; i++) {
			setBlockAt(Blocks.stone, i, 0);
		}

		for (int i = 1; i < 50; i++) {
			setBlockAt(Blocks.stone, i, 49);
		}

		for (int i = 0; i < 50; i++) {
			setBlockAt(Blocks.stone, 49, i);
		}
		*/
	}

	public int[][] getBlockMap() {
		return this.blockMap;
	}

	public ArrayList<DynamicEntity> getDynamicEntities() {
		return this.dynamicEntities;
	}

	public Block getBlockAt(int x, int y) {
		return BlockRegistry.getBlockForID(blockMap[x][y]);
	}

	public void setBlockAt(Block block, int x, int y) {
		this.blockMap[x][y] = BlockRegistry.getIDForBlock(block);
	}
}
