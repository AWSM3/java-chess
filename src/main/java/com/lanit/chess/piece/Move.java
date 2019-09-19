package com.lanit.chess.piece;

public class Move {
	private int x;
	private int y;

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}