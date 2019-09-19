package com.lanit.chess.board;

import com.lanit.chess.Color;
import com.lanit.chess.InvalidChessPieceLogicException;
import com.lanit.chess.piece.*;
import com.lanit.chess.player.Player;
import com.lanit.chess.util.Randomizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *   x: 0 1 2 3 4 5 6 7
 *	    _______________
 * y:   A B C D E F G H
 * 0| 1 . . . . . . . .
 * 1| 2 . . . . . . . .
 * 2| 3 . . . . . . . .
 * 3| 4 . . . . . . . .
 * 4| 5 . . . . . . . .
 * 5| 6 . . . . . . . .
 * 6| 7 . . . . . . . .
 * 7| 8 . . . . . . . .
 *
 * [3][2] == D3
 * [5][7] == F8
 * etc...
 */
public class Board {
	private int size;
	private final BoardPoint[][] points;

	public Board(int size) {
		this.size = size;
		this.points = new BoardPoint[size][size];
		initPieces();
	}

	public int getSize() {
		return this.size;
	}

	public void initPieces() {
		for(int i = 0; i < getSize(); i++) {
    		for(int j = 0; j < getSize(); j++) {
    			Chessman piece = null;
    			try {
    				piece = getDefaultPieceByCoordinate(i, j);
    			} catch (InvalidChessPieceLogicException e) {}

	    		this.points[i][j] = new BoardPoint(i, j, piece);
    		}
		}
	}

	public BoardPoint[][] getPoints() {
		return this.points;
	}

	public BoardPoint getRandomPoint(Color color, boolean withPiece) {
		BoardPoint point = getPoint(Randomizer.generate(0, getSize() - 1), Randomizer.generate(0, getSize() - 1));
		if (point.getPiece() != null && point.getPiece().getColor() == color) {
			if (withPiece && point.getPiece() != null) {	
				return point;
			}
		}

		return getRandomPoint(color, withPiece);
	}

	public ArrayList getPlayerPieces(Player player) {
		ArrayList<BoardPoint> points = new ArrayList<>();

		for(int i = 0; i < getSize(); i++) {
    		for(int j = 0; j < getSize(); j++) {
	    		BoardPoint point = this.points[i][j];
	    		if (point.getPiece() != null && point.getPiece().getColor() == player.getColor()) {
	    			points.add(point);
	    		}
    		}
		}

		return points;
	}

	public boolean playerHasPieces(Player player) {
		return getPlayerPieces(player).size() > 0;
	}

	private Chessman getDefaultPieceByCoordinate(int x, int y) throws InvalidChessPieceLogicException {
		Color color = y <= 1 ? Color.WHITE : Color.BLACK;

		// низ/верх доски
		if(y == 0 || y == getSize() - 1) {
			// ладья, начало или конец линии 
			if (x == 0 || x == getSize() - 1) {
				return new Rook(color);
			}

			// конь, вторая клетка с начала/конца линии
			if(x == 1 || x == getSize() - 2) {
				return new Horse(color);
			}

			// слон, третья клетка с начала/конца линии
			if(x == 2 || x == getSize() - 3) {
				return new Elephant(color);
			}

			// ферзь, четвертая клетка с начала линии
			if(x == 3) {
				return new Queen(color);
			}

			// король, пятая клетка с начала линии
			if(x == 4) {
				return new King(color);
			}
		}

		// пешка, ибо второй ряд (снизу и сверху), `x` не важен
		if(y == 1 || y == getSize() - 2) {
			return new Pawn(color);
		}

		throw new InvalidChessPieceLogicException();
	}

	public BoardPoint getPoint(int x, int y) {
		return this.points[x][y];
	}
}