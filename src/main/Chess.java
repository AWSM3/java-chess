import com.lanit.chess.*;

import com.lanit.chess.board.Board;
import com.lanit.chess.player.*;

/**
 * - не учитывается ход пешки из начальной позиции на две клетки
 * - превращение пешки не происходит
 */
public class Chess {
	public static final int BOARD_SIZE = 8;

	public static void main(String[] args) {
		Board board = new Board(BOARD_SIZE);
		
		new Gamer(board, new WhitePlayer(), new BlackPlayer()).run();
	}
}