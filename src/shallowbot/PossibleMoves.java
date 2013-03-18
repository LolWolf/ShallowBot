package shallowbot;

import java.util.ArrayList;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 8:28 PM
 */
public class PossibleMoves {
	public static int[] generatePossibleMoves(Board board, int player) {
		int i=0;



		return null;
	}



	//TODO: CLEAN THIS UP. IT LOOKS LIKE SOMEONE LITERALLY JUST CRAPPED CODE.
	private static int[] rookPossibleMoves(Board board, int currentPos) {
		int _temp[] = new int[16];

		if(currentPos > 63 || currentPos < 0) return null;

		int idx = 0;
		int flags = 0;
		boolean _end = false;
		boolean _unequal_ends = false;
		int posFinal = 0;
		for(int i=0; i<8; i++) {
			if((flags & 1) != 1 &&
					isValid(currentPos, i, board) &&
					(_end = (board.board[currentPos]==0))) {
				_temp[idx++] = i;
			} else if(!_end && (_unequal_ends = ((board.board[currentPos+i]&8) != (board.board[currentPos]&8)))) {
				_temp[idx++] = i;
				flags |= 1;
				_unequal_ends=false;
			} else if(!_end && !_unequal_ends) {
				flags |= 1;
			}
			_end = false;
			if((flags & 2) < 1 &&
					isValid(currentPos, -i, board) &&
					(_end = (board.board[currentPos]==0))) {
				_temp[idx++] = -i;
			} else if(!_end && (_unequal_ends = ((board.board[currentPos-i]&8) != (board.board[currentPos]&8)))) {
				_temp[idx++] = -i;
				flags |= 2;
			} else if(!_end && !_unequal_ends) {
				flags |= 2;
				_unequal_ends=false;
			}
			_end = false;
			if((flags & 4) < 1 &&
					isValid(currentPos, 8*i, board) &&
					(_end = (board.board[currentPos]==0))) {
				_temp[idx++] = 8*i;
			} else if(!_end && (_unequal_ends = ((board.board[currentPos+8*i]&8) != (board.board[currentPos]&8)))) {
				_temp[idx++] = 8*i;
				flags |= 4;
			} else if(!_end && !_unequal_ends) {
				flags |= 4;
				_unequal_ends=false;
			}
			if((flags & 8) < 1 &&
					isValid(currentPos, -8*i, board) &&
					(_end = (board.board[currentPos]==0))) {
				_temp[idx++] = -8*i;
			} else if(!_end && (_unequal_ends = ((board.board[currentPos-8*i]&8) != (board.board[currentPos]&8)))) {
				_temp[idx++] = -8*i;
				flags |= 8;
			} else if(!_end && !_unequal_ends) {
				flags |= 8;
				_unequal_ends=false;
			}
			_end = false;
		}

		return null;
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
	//End crappy coding.     2
}
