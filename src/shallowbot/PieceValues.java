package shallowbot;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/20/13
 * Time: 10:41 PM
 */



public class PieceValues {
	public static final int KING_SCORE = 200;
	public static final int QUEEN_SCORE = 9;
	public static final int ROOK_SCORE = 5;
	public static final int BISHOP_SCORE = 3;
	public static final int KNIGHT_SCORE = 3;
	public static final int PAWN_SCORE = 1;

	public static final int[] possibly_dangerous_array_of_scores = {PAWN_SCORE, KNIGHT_SCORE,
			BISHOP_SCORE, ROOK_SCORE, QUEEN_SCORE, KING_SCORE};
}
