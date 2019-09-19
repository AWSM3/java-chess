package com.lanit.chess.piece;

import com.lanit.chess.Color;
import com.lanit.chess.PieceDoesnHaveAvailableMovesException;

public abstract class Chessman {
	private Color color;

	public Chessman(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	abstract public String getName();

	abstract public String getIcon();

	abstract public Move getMoveFrom(int x, int y, int max) throws PieceDoesnHaveAvailableMovesException;
}