package com.lanit.chess.piece;

import com.lanit.chess.PieceDoesnHaveAvailableMovesException;
import com.lanit.chess.Color;

public class Horse extends Chessman {
	public Horse(Color color) {
		super(color);
	}
	
	public String getName() {
		return "Конь";
	}

	public String getIcon() {
		return this.getColor() == Color.WHITE ? "♘" : "♞";
	}

	public Move getMoveFrom(int x, int y, int max) {
		// доступные смещения координат во время хода конём
		int[][] validOffsets = {{-1, -2}, {-2, -1}, {-2,  1}, {1, -2}, {-1,  2}, {2, -1}, {1,  2}, {2,  1}};
		int newX;
		int newY;

		for (int i = 0; i < max; i++) {
	        newX = x + validOffsets[i][0];
	        newY = y + validOffsets[i][1];
	        if (this.isValidCoords(newX, newY)) {
				return new Move(newX, newY);
	        }
    	}

    	throw new PieceDoesnHaveAvailableMovesException(this);
	}

	private boolean isValidCoords(int x, int y) {
    	return x >= 0 && x < 8 && y >= 0 && y < 8;
	}
}