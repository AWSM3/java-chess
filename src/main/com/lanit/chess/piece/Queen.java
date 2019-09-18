package com.lanit.chess.piece;

import com.lanit.chess.util.Randomizer;
import com.lanit.chess.Color;

public class Queen extends Chessman {
	public Queen(Color color) {
		super(color);
	}

	public String getName() {
		return "Ферзь";
	}

	public String getIcon() {
		return this.getColor() == Color.WHITE ? "♕" : "♛";
	}

	public Move getMoveFrom(int x, int y, int max) {
		int newX = Randomizer.generate(0, max);
		int newY = Randomizer.generate(0, max);

		while (!this.validMove(x, y, newX, newY)) {
			newX = Randomizer.generate(0, max);
			newY = Randomizer.generate(0, max);
		}
		
		return new Move(newX, newY);
	}

	private boolean validMove(int x, int y, int newX, int newY) {
		return x == newX || y == newY || Math.abs(x - newX) == Math.abs(y - newY);
	}
}