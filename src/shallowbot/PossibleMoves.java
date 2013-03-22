package shallowbot;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 8:28 PM
 */
public class PossibleMoves {
    private static int currentPlayer;
    private static boolean mateChecking;
	private static int totalGeneratedMoves = 0;

    public static boolean generatePossibleMoves(Board board, ArrayList<Move> moves, int player) {
        boolean _case = false;
        currentPlayer = player;

        mateChecking = (moves == null);
//	    if(!mateChecking) {
//		    totalGeneratedMoves++;
//		    if(totalGeneratedMoves%100000==0) System.out.println("Generated "+totalGeneratedMoves);
//	    }
//	    if(totalGeneratedMoves>6200000) {
//		    System.out.println(board);
//		    System.out.println("Player: "+player);
//	    }

        for(int i=0; i<64; i++) {
            if((board.board[i]&8) != 8*(player-1)) continue;
            switch(board.board[i]&7) {
                case PieceLabels.W_KNIGHT:
	                //if(totalGeneratedMoves==2467) log("KNIGHT");
	                _case |= generateKnightMoves(board, moves, i);
	                break;
                case PieceLabels.W_QUEEN:
	                //if(totalGeneratedMoves==2467) log("QUEEN");
	                _case |= generateQueenMoves(board, moves, i);
	                break;
                case PieceLabels.W_PAWN:
	                //if(totalGeneratedMoves==2467) log("PAWN");
                    _case |= generatePawnMoves(board, moves, i);
	                break;
                case PieceLabels.W_ROOK:
	                //if(totalGeneratedMoves==2467) log("ROOK");
	                _case |= generateRookMoves(board, moves, i);
	                break;
                case PieceLabels.W_BISHOP:
	                //if(totalGeneratedMoves==2467) log("BISHOP");
	                _case |= generateBishopMoves(board, moves, i);
	                break;
                case PieceLabels.W_KING:
	                //if(totalGeneratedMoves==2467) log("KING");
	                _case |= generateKingMoves(board, moves, i);
	                break;
            }
        }

        //if(!mateChecking)
          //  removeInvalidMoves(board, moves);

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
	    _case |= generateMacroMoves(b, moveList, position, 1, 9);
        _case |= generateMacroMoves(b, moveList, position, 1, 7);
        _case |= generateMacroMoves(b, moveList, position, 1, -7);
        _case |= generateMacroMoves(b, moveList, position, 1, -9);

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

        int l_pos = position%8;

        if(l_pos<6)_case |= generateMacroMoves(b, moveList, position, 1, -6 );
        if(l_pos<6) _case |= generateMacroMoves(b, moveList, position, 1, 10);
        _case |= generateMacroMoves(b, moveList, position, 1, 17);
        _case |= generateMacroMoves(b, moveList, position, 1, 15);
        _case |= generateMacroMoves(b, moveList, position, 1, -15);
        _case |= generateMacroMoves(b, moveList, position, 1, -17);
        if(l_pos>1)_case |= generateMacroMoves(b, moveList, position, 1, -10);
        if(l_pos>1)_case |= generateMacroMoves(b, moveList, position, 1, 6);

        return _case;
    }

    private static boolean generatePawnMoves(Board b, ArrayList<Move> moveList, int position) {

        boolean _case = false;

	    int l_pos = position%8;

        if(position/8 != (currentPlayer == 1 ? 1 : 6)) {
            _case |= generateMacroPawnMoves(b, moveList, position, 1, currentPlayer==1?8:-8);
	        //if(totalGeneratedMoves==2467) log("PAWN Probs");
        } else {
            _case |= generateMacroPawnMoves(b, moveList, position, 2, currentPlayer==1?8:-8);
	        //if(totalGeneratedMoves==2467) log("PAWN Probs 2");

        }
	    if(currentPlayer==1) {
            if(l_pos<7) _case |= generatePawnCapture(b, moveList, position, 9);
            if(l_pos>0) _case |= generatePawnCapture(b, moveList, position, 7);
	    } else {
		    if(l_pos<7) _case |= generatePawnCapture(b, moveList, position, -7);
		    if(l_pos>0) _case |= generatePawnCapture(b, moveList, position, -9);
		    //if(totalGeneratedMoves==2467) log("PAWN Capture 2");
	    }

        considerPromotionMoves(b, moveList);
	    //if(totalGeneratedMoves==2467) log("PAWN promotion");

        return _case;
    }

    private static void considerPromotionMoves(Board b, ArrayList<Move> moveList) {
        if(moveList == null) return;
	    int moveListSize = moveList.size();
        for(int i=0; i<moveListSize; i++) {
	        Move m = moveList.get(i);
	        //System.out.println(moveList);
            if((b.board[m.from]&7)==PieceLabels.W_PAWN && (m.to/8==7 || m.to/8==0) && m.promotion==0) {
                m.promotion |= 8*(currentPlayer - 1);
                moveList.add(new Move(m.from, m.to, (byte)(m.promotion|PieceLabels.W_QUEEN)));
                m.promotion |= PieceLabels.W_KNIGHT;
            }

        }
    }

 	private static boolean generateMacroMoves(Board b, ArrayList<Move> moveList, int pos, int maxTo, int vec) {
		int pos_init = pos;

        int l_pos0;
        int l_vec = vec%8;


        while(maxTo>0) {
            l_pos0 = pos%8;
            pos += vec;

            //Check bounds
            if(pos > 63 || pos < 0 || l_pos0%8==7 && l_vec>0 || l_pos0%8==0 && l_vec<0) {
                return false;
            }

            //Check for empty/taken pieces.
            if(b.board[pos]==0) {
                if(!mateChecking) moveList.add(new Move(pos_init, pos));
            } else if((b.board[pos] & 8) != (currentPlayer==1?0:8)) {
                if(!mateChecking) moveList.add(new Move(pos_init, pos));
                if(b.board[pos]==(currentPlayer==1 ? PieceLabels.B_KING : PieceLabels.W_KING)) {
                    return true;
                }
	            return false;
            } else {
                break;
            }

            maxTo--;
        }

        return false;
	}
    private static boolean generateMacroPawnMoves(Board b, ArrayList<Move> moveList, int pos, int maxTo, int vec) {
        int pos_init = pos;

        int l_pos0;
        int l_vec = vec%8;

        while(maxTo>0) {
            l_pos0 = pos%8;
            pos += vec;
            //Check bounds
            if(pos > 63 || pos < 0 || l_pos0%8==7 && l_vec>0 || l_pos0%8==0 && l_vec<0) {
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
        if(pos > 63 || pos < 0) {
            return false;
        }

        //Check for diagonal capture
        if(b.board[pos]!=0 && (b.board[pos]&8)!=(currentPlayer-1)*8) {
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
	    Iterator<Move> i = moveList.iterator();
	    Move m;
	    while(i.hasNext()) {
		    m = i.next();
		    b.makeMove(m);
		    if(generatePossibleMoves(b, null, currentPlayer))
			    i.remove();
		    b.makeMove(m.reverse());
	    }
    }
	private static void log(Object s) {
		System.out.println("LOG: "+s.toString());
	}
}
