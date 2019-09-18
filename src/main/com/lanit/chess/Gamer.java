package com.lanit.chess;

import com.lanit.chess.piece.Chessman;
import com.lanit.chess.player.*;
import com.lanit.chess.board.Board;
import com.lanit.chess.util.BoardPrinter;

public class Gamer {
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Mover mover;
	private int currentNumberOfMoves;

	public Gamer(Board board, WhitePlayer playerOne, BlackPlayer playerTwo) {
		this.board = board;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.mover = new Mover(board);
	}

	public void run() {
		// присваиваем поля(фигуры)
		this.playerOne.setPoints(this.board.getPointsFlat());
		this.playerTwo.setPoints(this.board.getPointsFlat());

		try {
			// ходим до тех пор, пока есть фигуры у каждого из игроков
			while (this.playerOne.hasPieces() && this.playerTwo.hasPieces()) {
				this.mover.initMoves(this.playerOne);
				this.mover.initMoves(this.playerTwo);
			}
		} catch (KingIsEatenException e) {
			Chessman winnerPiece = e.getWinnerPiece();
			if (Color.BLACK == winnerPiece.getColor()) {
				this.playerOne.lose();
			} else {
				this.playerTwo.lose();
			}
		} catch (ReachMaximumUselessMovesException e) {
			this.endGame("Достигнуто максимальное количество бесполезных ходов");
			return;
		}

		this.endGame("победитель " + this.getWinner());
	}

	public Player getWinner() {
		return this.playerTwo.isLoser() && !this.playerOne.isLoser() ? this.playerOne : this.playerTwo;
	}

	public void endGame(String cause) {
		System.out.println(
			String.format("Игра завершена. Количество сделанных ходов: %s. Причина завершения игры: %s.\n\n", 
				this.mover.getTotalMoves(), 
				cause
			)
		);

		BoardPrinter.print(this.board);
	}
}