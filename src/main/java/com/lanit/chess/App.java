package com.lanit.chess;

import com.lanit.chess.board.Board;
import com.lanit.chess.player.Player;
import com.lanit.chess.Color;

/**
 * - не учитывается ход пешки из начальной позиции на две клетки
 * - превращение пешки не происходит
 */
public class App {
	public static final int BOARD_SIZE = 8;

	public static void main(String[] args) {
		Board board = new Board(BOARD_SIZE);
		
		new Gamer(board, new Player(Color.WHITE), new Player(Color.BLACK)).run();
	}
}