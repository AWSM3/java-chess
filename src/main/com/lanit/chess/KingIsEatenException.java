package com.lanit.chess;

import java.lang.Exception;
import com.lanit.chess.piece.Chessman;

public class KingIsEatenException extends Exception {
	private Chessman piece;

	public KingIsEatenException(Chessman piece) {
        super("Король съеден");
        this.piece = piece;
    }

    public Chessman getWinnerPiece() {
    	return this.piece;
    }
}