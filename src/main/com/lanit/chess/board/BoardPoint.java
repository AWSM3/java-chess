package com.lanit.chess.board;

import com.lanit.chess.piece.Chessman;
import com.lanit.chess.Color;

public class BoardPoint {
	private int x;
	private int y;
	private Chessman piece = null;

	public BoardPoint(int x, int y, Chessman piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	public String toString() {
		Chessman piece = this.piece;
		if (piece == null) {
			return null;
		}

		return String.format(
			"%s.%s", 
			this.piece.getColor() == Color.WHITE ? "Б" : "Ч", 
			this.piece.getName()
		);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Chessman getPiece() {
		return this.piece;
	}

	public boolean hasAlly(Chessman current) {
		return this.getPiece() != null && this.getPiece().getColor() == current.getColor();
	}

	public boolean hasEnemy(Chessman current) {
		return this.getPiece() != null && this.getPiece().getColor() != current.getColor();
	}

	public void setPiece(Chessman piece) {
		this.piece = piece;
	}
}