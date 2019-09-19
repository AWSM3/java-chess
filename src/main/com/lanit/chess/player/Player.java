package com.lanit.chess.player;

import com.lanit.chess.Color;

public class Player {
	protected Color color;
	protected boolean loser = false;

	public Player(Color color) {
		this.color = color;
	}

	public String toString() {
		return getColor().name();
	}

	public Color getColor() {
		return this.color;
	}

	public void lose() {
		this.loser = true;
	}

	public boolean isLoser() {
		return this.loser;
	}
}