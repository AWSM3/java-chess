package com.lanit.chess;

import com.lanit.chess.piece.Chessman;
import com.lanit.chess.piece.Move;
import com.lanit.chess.piece.King;
import com.lanit.chess.board.*;
import com.lanit.chess.player.Player;

public class Mover {
	public static final int MAX_USELESS_MOVES = 1000;
	private Board board;
	private int totalMoves = 0;
	private int uselessMoves = 0;

	public Mover(Board board) {
		this.board = board;
	}
	
	public void move(Player player) throws ReachMaximumUselessMovesException, KingIsEatenException {
		while(!reachMaximumUselessMoves()) {
			try {
				if (!this.board.playerHasPieces(player)) {
					break;
				}

				BoardPoint point = this.board.getRandomPoint(player.getColor(), true);

				// двигаем рандомную фигуру игрока стоящую на конкретной клетке
				Chessman eatenPiece = movePiece(point);
				if (eatenPiece != null) {
					resetMoves();
				}
				incrementMoves();
				// ход успешен, выходим
				break;
			} catch (PieceDoesnHaveAvailableMovesException e) {
				// пробуем следующую фигуру
				continue;
			}
		}

		if (reachMaximumUselessMoves()) {
			throw new ReachMaximumUselessMovesException();
		}
	}

	protected Chessman movePiece(BoardPoint point) throws KingIsEatenException, PieceDoesnHaveAvailableMovesException {
		// получим случайный доступный ход для фигуры
		Move move = point.getPiece().getMoveFrom(point.getX(), point.getY(), this.board.getSize() - 1);
		
			// используя доску попробуем двинуть фигуру по координатам
			Chessman piece = point.getPiece();
			BoardPoint targetPoint = this.board.getPoint(move.getX(), move.getY());
			Chessman targetPiece = targetPoint.hasEnemy(piece) ? targetPoint.getPiece() : null;
			boolean kingIsEaten = false;

			// проверим наличие союзника на клетке
			if (targetPoint.hasAlly(piece)) {
				// попытка сходить на клетку своего союзника
				throw new PieceDoesnHaveAvailableMovesException(point.getPiece());
			}

			// проверим, является ли таргет королём
			if (targetPoint.getPiece() instanceof King) {
				kingIsEaten = true;
			}

			// занимает новую клетку
			targetPoint.setPiece(piece);
			// освобождает старую клетку
			point.setPiece(null);

			if (kingIsEaten) {
				throw new KingIsEatenException(piece);
			}

			return targetPiece;
	}

	protected void incrementMoves() {
		this.uselessMoves++;
		this.totalMoves++;
	}

	protected void resetMoves() {
		this.uselessMoves = 0;
	}

	protected boolean reachMaximumUselessMoves() {
		return this.uselessMoves >= MAX_USELESS_MOVES;
	}

	public int getTotalMoves() {
		return this.totalMoves;
	}
}