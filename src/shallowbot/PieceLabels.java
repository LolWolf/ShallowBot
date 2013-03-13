package shallowbot;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/13/13
 * Time: 12:35 AM
 */

public class PieceLabels {
	//White Pieces
	public static byte W_PAWN = 1;
	public static byte W_KNIGHT = 2;
	public static byte W_BISHOP = 3;
	public static byte W_ROOK = 4;
	public static byte W_QUEEN = 5;
	public static byte W_KING = 6;

	//Black Pieces
	public static byte B_PAWN = (byte)(W_PAWN + 8);
	public static byte B_KNIGHT = (byte)(W_KNIGHT + 8);
	public static byte B_BISHOP = (byte)(W_BISHOP + 8);
	public static byte B_ROOK = (byte)(W_ROOK + 8);
	public static byte B_QUEEN = (byte)(W_QUEEN + 8);
	public static byte B_KING = (byte)(W_KING + 8);

	//Empty
	public static byte EMPTY = 0;
}
