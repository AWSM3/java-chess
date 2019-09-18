package com.lanit.chess.board;

import com.lanit.chess.Color;
import com.lanit.chess.InvalidChessPieceLogicException;
import com.lanit.chess.piece.*;

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
	private BoardPoint[][] points;

	public Board(int size) {
		this.size = size;
		this.points = new BoardPoint[size][size];
		this.initPieces();
	}

	public int getSize() {
		return this.size;
	}

	public void initPieces() {
		for(int i = 0; i < this.getSize(); i++) {
    		for(int j = 0; j < this.getSize(); j++) {
    			Chessman piece = null;
    			try {
    				piece = this.getDefaultPieceByCoordinate(i, j);
    			} catch (InvalidChessPieceLogicException e) {}

	    		this.points[i][j] = new BoardPoint(i, j, piece);
    		}
		}
	}

	public BoardPoint[][] getPoints() {
		return this.points;
	}

	public BoardPoint[] getPointsFlat() {
        BoardPoint[] result = new BoardPoint[this.getSize() * this.getSize()];
        int index = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                result[index++] = this.points[i][j];
            }
        }

        return result;
    }

	private Chessman getDefaultPieceByCoordinate(int x, int y) {
		Color color = y <= 1 ? Color.WHITE : Color.BLACK;

		// низ/верх доски
		if(y == 0 || y == this.getSize() - 1) {
			// ладья, начало или конец линии 
			if (x == 0 || x == this.getSize() - 1) {
				return new Rook(color);
			}

			// конь, вторая клетка с начала/конца линии
			if(x == 1 || x == this.getSize() - 2) {
				return new Horse(color);
			}

			// слон, третья клетка с начала/конца линии
			if(x == 2 || x == this.getSize() - 3) {
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
		if(y == 1 || y == this.getSize() - 2) {
			return new Pawn(color);
		}

		throw new InvalidChessPieceLogicException();
	}

	public BoardPoint getPoint(int x, int y) {
		return this.points[x][y];
	}
}