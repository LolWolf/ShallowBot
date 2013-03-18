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

	private static boolean generateRookMoves(Board b, ArrayList<Move> moveList, int position) {
        boolean _case = false;

        _case |= generateMacroMoves(b, moveList, position, 8, 1);
        _case |= generateMacroMoves(b, moveList, position, 8, -1);
        _case |= generateMacroMoves(b, moveList, position, 8, 8);
        _case |= generateMacroMoves(b, moveList, position, 8, -8);

        return _case;
	}

    private static boolean generateKingMoves(Board b, ArrayList<Move> moveList, int position) {
        boolean _case = false;

        _case |= generateMacroMoves(b, moveList, position, 1, 1);
        _case |= generateMacroMoves(b, moveList, position, 1, -1);
        _case |= generateMacroMoves(b, moveList, position, 1, 8);
        _case |= generateMacroMoves(b, moveList, position, 1, -8);

        return _case;
    }

    private static boolean generateBishopMoves(Board b, ArrayList<Move> moveList, int position) {
        boolean _case = false;

        _case |= generateMacroMoves(b, moveList, position, 8, 9);
        _case |= generateMacroMoves(b, moveList, position, 8, 7);
        _case |= generateMacroMoves(b, moveList, position, 8, -7);
        _case |= generateMacroMoves(b, moveList, position, 8, -9);

        return _case;
    }

    private static boolean generateQueenMoves(Board b, ArrayList<Move> moveList, int position) {
        boolean _case = false;

        _case |= generateBishopMoves(b, moveList, position);
        _case |= generateRookMoves(b, moveList, position);

        return _case;
    }

    private static boolean generateKnightMoves(Board b, ArrayList<Move> moveList, int position) {
        boolean _case = false;

        _case |= generateMacroMoves(b, moveList, position, 1, 23);
        _case |= generateMacroMoves(b, moveList, position, 1, 25);
        _case |= generateMacroMoves(b, moveList, position, 1, 11);
        _case |= generateMacroMoves(b, moveList, position, 1, 5);
        _case |= generateMacroMoves(b, moveList, position, 1, -5);
        _case |= generateMacroMoves(b, moveList, position, 1, -23);
        _case |= generateMacroMoves(b, moveList, position, 1, -25);
        _case |= generateMacroMoves(b, moveList, position, 1, -11);

        return false;
    }

	private static boolean generateMacroMoves(Board b, ArrayList<Move> moveList, int pos, int maxTo, int vec) {
		int pos_init = pos;
        while(maxTo>0) {
            pos += vec;

            //Check bounds
            if(pos_init%8 < pos%8 || pos > 63 || pos < 0) {
                return false;
            }

            //Check for empty/taken pieces.
            if(b.board[pos]==0) {
                moveList.add(new Move(pos_init, pos));
            } else if((b.board[pos] & 8) != 8) {
                moveList.add(new Move(pos_init, pos));
                if(b.board[pos]==PieceLabels.W_KING) {
                    return true;
                }
            } else {
                break;
            }

            maxTo--;
        }

        return false;
	}
}
