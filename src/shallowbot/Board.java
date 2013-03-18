package shallowbot;

import java.util.HashMap;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/13/13
 * Time: 12:34 AM
 */
public class Board {
	public byte[] board;
	public Board() {
		board = new byte[64];
		for(int i=0; i<board.length; i++) {
			board[i] = PieceLabels.EMPTY;
		}
	}
	public void setInitialPosition() {
		for(char _temp='a'; _temp<='h'; _temp++) {
			setPiece(_temp, 2, PieceLabels.W_PAWN);
			setPiece(_temp, 7, PieceLabels.B_PAWN);
		}

		setPiece("a1", PieceLabels.W_ROOK);
		setPiece("h1", PieceLabels.W_ROOK);

		setPiece("b1", PieceLabels.W_KNIGHT);
		setPiece("g1", PieceLabels.W_KNIGHT);

		setPiece("c1", PieceLabels.W_BISHOP);
		setPiece("f1", PieceLabels.W_BISHOP);

		setPiece("e1", PieceLabels.W_KING);
		setPiece("d1", PieceLabels.W_QUEEN);

		setPiece("a8", PieceLabels.B_ROOK);
		setPiece("h8", PieceLabels.B_ROOK);

		setPiece("b8", PieceLabels.B_KNIGHT);
		setPiece("g8", PieceLabels.B_KNIGHT);

		setPiece("c8", PieceLabels.B_BISHOP);
		setPiece("f8", PieceLabels.B_BISHOP);

		setPiece("e8", PieceLabels.B_KING);
		setPiece("d8", PieceLabels.B_QUEEN);
	}


	public boolean setPiece(char org_a, int org_b, char to_a, int to_b, byte piece) {
		setPiece(to_a, to_b, piece);
		board[toIdx(org_a, org_b)] = PieceLabels.EMPTY;

		return true;
	}
	public boolean setPiece(char to_a, int to_b, byte piece) {
		int to_idx = toIdx(to_a, to_b);

		//Check if the piece is the same
		if( board[to_idx] != 0 && (board[to_idx]&8) == (piece&8) ) return false;

		//Swap pieces
		board[to_idx] = piece;

		return true;
	}
	public boolean setPiece(String a, byte piece) {
		return setPiece(a.charAt(0), a.charAt(1)-'0', piece);
	}

	public boolean pieceVector(int initial, int vec) {
		byte pieceType = board[initial];

		board[initial] = PieceLabels.EMPTY;
		board[initial+vec] = pieceType;

		return true;
	}

	public static int toIdx(char a, int b) {
	    //Standard notation to index
		return 8*(b-1)+(a-'a');
	}
}