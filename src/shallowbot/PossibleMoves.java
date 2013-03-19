package shallowbot;

import java.util.ArrayList;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 8:28 PM
 */
public class PossibleMoves {
    private static int currentPlayer;
    private static boolean mateChecking;

    public static boolean generatePossibleMoves(Board board, ArrayList<Move> moves, int player) {
        boolean _case = false;
        currentPlayer = player;

        if(moves==null)
            mateChecking = true;
        else
            mateChecking = false;


        for(int i=0; i<64; i++) {
            if((board.board[i]&8) != 8*(player-1)) continue;
            switch(board.board[i]&7) {
                case PieceLabels.W_KNIGHT:
                    generateKnightMoves(board, moves, i);
                    break;
                case PieceLabels.W_QUEEN:
                    generateQueenMoves(board, moves, i);
                    break;
                case PieceLabels.W_PAWN:
                    generatePawnMoves(board, moves, i);
                    break;
                case PieceLabels.W_ROOK:
                    generateRookMoves(board, moves, i);
                    break;
                case PieceLabels.W_BISHOP:
                    generateBishopMoves(board, moves, i);
                    break;
                case PieceLabels.W_KING:
                    generateKingMoves(board, moves, i);
                    break;
            }
        }

        if(!mateChecking)
            removeInvalidMoves(board, moves);

        return _case;

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

        return _case;
    }

    private static boolean generatePawnMoves(Board b, ArrayList<Move> moveList, int position) {

        boolean _case = false;

        if(position/8!=(currentPlayer==1?1:6)) {
            _case |= generateMacroPawnMoves(b, moveList, position, 1, currentPlayer==1?8:-8);
        } else {
            _case |= generateMacroPawnMoves(b, moveList, position, 2, currentPlayer==1?8:-8);
        }
        _case |= generatePawnCapture(b, moveList, position, currentPlayer==1?9:-9);
        _case |= generatePawnCapture(b, moveList, position, currentPlayer==1?7:-7);

        considerPromotionMoves(b, moveList);

        return _case;
    }

    private static void considerPromotionMoves(Board b, ArrayList<Move> moveList) {
        if(moveList == null) return;
        for(Move m : moveList) {
            if((b.board[m.from]&7)==PieceLabels.W_PAWN && (m.to/8==7 || m.to/8==0)) {
                m.promotion |= 8*(currentPlayer - 1);
                moveList.add(new Move(m.from, m.to, (byte)(m.promotion|PieceLabels.W_QUEEN)));
                m.promotion |= PieceLabels.W_KNIGHT;
            }
        }
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
                if(!mateChecking) moveList.add(new Move(pos_init, pos));
            } else if((b.board[pos] & 8) != (currentPlayer-1)*8) {
                if(!mateChecking) moveList.add(new Move(pos_init, pos));
                if(b.board[pos]==(currentPlayer==1 ? PieceLabels.W_KING : PieceLabels.B_KING)) {
                    return true;
                }
            } else {
                break;
            }

            maxTo--;
        }

        return false;
	}
    private static boolean generateMacroPawnMoves(Board b, ArrayList<Move> moveList, int pos, int maxTo, int vec) {
        int pos_init = pos;

        while(maxTo>0) {
            pos += vec;
            //Check bounds
            if(pos_init%8 < pos%8 || pos > 63 || pos < 0) {
                return false;
            }

            //Check for empty/taken pieces.
            if(b.board[pos]==0) {
                if(!mateChecking) moveList.add(new Move(pos_init, pos));
            }
            maxTo--;
        }
        return false;
    }
    private static boolean generatePawnCapture(Board b, ArrayList<Move> moveList, int pos, int vec) {
        int pos_init = pos;
        pos += vec;

        //Check bounds
        if(pos_init%8 < pos%8 || pos > 63 || pos < 0) {
            return false;
        }

        //Check for diagonal capture
        if((b.board[pos]&8)!=(currentPlayer-1)*8 && b.board[pos]!=0) {
            if(!mateChecking) moveList.add(new Move(pos_init, pos));
            if((b.board[pos]&7)==PieceLabels.W_KING)
                return true;
        }
        return false;
    }
    private static void removeInvalidMoves(Board b, ArrayList<Move> moveList) {
        mateChecking = true;
        currentPlayer = currentPlayer==1?2:1;
        //Make move, check for mate, and undo those that evaluate to true.
        for(Move m : moveList) {
            b.makeMove(m);
            if(generatePossibleMoves(b, null, currentPlayer))
                moveList.remove(m);
            b.makeMove(m.reverse());
        }
    }
}
