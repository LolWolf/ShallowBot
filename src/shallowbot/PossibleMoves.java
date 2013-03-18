package shallowbot;

import java.util.ArrayList;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 8:28 PM
 */
public class PossibleMoves {
	public static ArrayList<Move> generatePossibleMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();


		return moves;
	}

	private static ArrayList<Move> generateRookMoves(Board b, int player, ArrayList<Move> moveList) {
		return null;
	}

	private static boolean generateMacroMoves(Board b, ArrayList<Move> moveList, int pos, int maxTo, int d) {
		return false;
	}

	private static boolean inBounds(int position) {
		return position < 64 && position > 0;
	}
	
	//Checks for end validity, does not check path taken.
	/*
	 HORRID CODE; DO NOT TRY THIS AT HOME, KIDS.
	 Checks inbounds, checks for looping along the sides of the board.
	*/
	private static boolean isValid(int position, int vec, Board b) {
		return inBounds(position+vec) &&
				(vec%8==0 ? true : 
						(position%8+vec<8 && position%8+vec>0));
	}
	//End crappy coding.
}
