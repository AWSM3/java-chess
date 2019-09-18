package com.lanit.chess.util;

import com.lanit.chess.board.Board;
import java.util.Arrays;

public class BoardPrinter {
	public static void print(Board board) {
		for (int i = 0; i < board.getPoints().length; i++) {
			System.out.println(Arrays.toString(board.getPoints()[i]));
		}
	}
}