package com.lanit.chess;

import com.lanit.chess.piece.Chessman;
import com.lanit.chess.player.Player;
import com.lanit.chess.board.Board;
import com.lanit.chess.util.BoardPrinter;

public class Gamer {
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Mover mover;
	private int currentNumberOfMoves;

	public Gamer(Board board, Player playerOne, Player playerTwo) {
		this.board = board;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.mover = new Mover(board);
	}

	public void run() {
		try {
			// ходим до тех пор, пока есть фигуры у каждого из игроков
			while (this.board.playerHasPieces(this.playerOne) && this.board.playerHasPieces(this.playerTwo)) {
				this.mover.move(this.playerOne);
				this.mover.move(this.playerTwo);
			}
		} catch (KingIsEatenException e) {
			Chessman winnerPiece = e.getWinnerPiece();
			if (Color.BLACK == winnerPiece.getColor()) {
				this.playerOne.lose();
			} else {
				this.playerTwo.lose();
			}
		} catch (ReachMaximumUselessMovesException e) {
			printGameResults("Достигнуто максимальное количество бесполезных ходов");
			return;
		}

		printGameResults("победитель " + getWinner());
	}

	public Player getWinner() {
		return (this.playerTwo.isLoser() || !this.board.playerHasPieces(this.playerTwo)) 
			   && (!this.playerOne.isLoser() || this.board.playerHasPieces(this.playerOne)) ? this.playerOne : this.playerTwo;
	}

	public void printGameResults(String cause) {
		System.out.println(
			String.format("Игра завершена. Количество сделанных ходов: %s. Причина завершения игры: %s.\n\n", 
				this.mover.getTotalMoves(), 
				cause
			)
		);

		BoardPrinter.print(this.board);
	}
}