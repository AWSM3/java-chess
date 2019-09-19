package com.lanit.chess;

import java.lang.Exception;
import com.lanit.chess.piece.Chessman;

public class PieceDoesnHaveAvailableMovesException extends Exception {
	private Chessman piece;

	public PieceDoesnHaveAvailableMovesException(Chessman piece) {
        super(String.format("Фигура `%s` не имеет возможных ходов", piece.getName()));

        this.piece = piece;
    }
}