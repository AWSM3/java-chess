package com.lanit.chess.piece;

import com.lanit.chess.Color;
import com.lanit.chess.PieceDoesnHaveAvailableMovesException;
import com.lanit.chess.util.Randomizer;

public class Pawn extends Chessman {
	public Pawn(Color color) {
		super(color);
	}

	public String getName() {
		return "Пешка";
	}

	public String getIcon() {
		return getColor() == Color.WHITE ? "♙" : "♟";
	}
	
	public Move getMoveFrom(int x, int y, int max) throws PieceDoesnHaveAvailableMovesException {
		int newX = x + Randomizer.generate(-1, 1); // смещение по горизонтали
		int newY = y;
		if (getColor() == Color.WHITE) {
			// ходим вверх
			newY += 1;
		} else {
			// ходим вниз
			newY -= 1;
		}
		
		// проверяем, что не вышли за границы доски
		if (newX >= 0 && newX <= max && newY >= 0 && newY <= max) {
			return new Move(newX, newY);
		}
		
		throw new PieceDoesnHaveAvailableMovesException(this);
	}
}