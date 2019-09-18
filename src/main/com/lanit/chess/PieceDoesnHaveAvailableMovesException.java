package com.lanit.chess;

import java.lang.RuntimeException;
import com.lanit.chess.piece.Chessman;

public class PieceDoesnHaveAvailableMovesException extends RuntimeException {
	private Chessman piece;

	public PieceDoesnHaveAvailableMovesException(Chessman piece) {
        super(String.format("Фигура `%s` не имеет возможных ходов", piece.getName()));

        this.piece = piece;
    }
}