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
	
	public void initMoves(Player player) {
		while(!this.reachMaximumUselessMoves()) {
			try {
				if (!player.hasPieces()) {
					break;
				}

				BoardPoint point = player.getRandomPoint();

				// двигаем рандомную фигуру игрока стоящую на конкретной клетке
				Chessman eatenPiece = this.movePiece(point);
				if (eatenPiece != null) {
					this.resetMoves();
				}
				this.incrementMoves();
				// ход успешен, выходим
				break;
			} catch (PieceDoesnHaveAvailableMovesException e) {
				// пробуем следующую фигуру
				continue;
			}
		}

		if (this.reachMaximumUselessMoves()) {
			throw new ReachMaximumUselessMovesException();
		}
	}

	protected Chessman movePiece(BoardPoint point) {
		// получим случайный доступный ход для фигуры
		Move move = point.getPiece().getMoveFrom(point.getX(), point.getY(), this.board.getSize() - 1);
		
		try {	
			// используя доску попробуем двинуть фигуру по координатам
			Chessman piece = point.getPiece();
			BoardPoint targetPoint = this.board.getPoint(move.getX(), move.getY());
			Chessman targetPiece = targetPoint.hasEnemy(piece) ? targetPoint.getPiece() : null;
			boolean kingIsEaten = false;

			// проверим наличие союзника на клетке
			if (targetPoint.hasAlly(piece)) {
				throw new CantMoveToAllyException();
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
		} catch (CantMoveToAllyException e) {
			// попытка сходить на клетку своего союзника
			throw new PieceDoesnHaveAvailableMovesException(point.getPiece());
		}
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