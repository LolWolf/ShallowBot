package shallowbot;

import java.util.ArrayList;

/**
 * Project: ShallowBot
 * User: @LolWolf
 * Date: 3/21/13
 * Time: 7:04 AM
 */
public class NegaMax {
	private static int leaves=0;
    public static MoveScore negaMaxInit(Board b, int player, int depth) {
	    leaves = 0;
        MoveScore ms = new MoveScore();

        ArrayList<Move> possible_moves = new ArrayList<Move>();

        PossibleMoves.generatePossibleMoves(b, possible_moves, player);

        int _temp;

	    byte pieceTaken;

        for(Move m : possible_moves) {
	        pieceTaken = b.makeMove(m);


            _temp = -negaMax(b, player==1?2:1, depth-1);
            if(_temp > ms.score) {
                ms.score = _temp;
                ms.move = m;
            }

	        b.makeMove(m.reverse(), pieceTaken);
        }

        return ms;
    }

    public static int negaMax(Board b, int player, int depth) {
//        leaves++;
//	    if(leaves%1000==0) System.out.println("At "+leaves+" leaves");
	    if(depth<=0) {
            return evaluateBoard(b, player);
        }

        ArrayList<Move> possible_moves = new ArrayList<Move>();
        PossibleMoves.generatePossibleMoves(b, possible_moves, player);

        if(possible_moves.size()<=0)
            return -Constants.CHECKMATE;

        int _temp;
        int _max = -Constants.INFTY;

	    byte pieceTaken;

        for(Move m : possible_moves) {
            pieceTaken = b.makeMove(m);

            _temp = -negaMax(b, player==1?2:1, depth-1);
            if(_temp > _max) {
                _max = _temp;
            }

            b.makeMove(m.reverse(), pieceTaken);
//	        System.out.println("Making move "+m.toString()+" with reverse "+m.reverse().toString());
        }

        return _max;
    }
    public static int evaluateBoard(Board b, int player) {
        int white_score = 0;
        int black_score = 0;


        for(int i=0; i<64; i++) {
            if(b.board[i]==0) continue;
            if((b.board[i]&8)>0) black_score += PieceValues.possibly_dangerous_array_of_scores[(b.board[i]&7)-1];
            if((b.board[i]&8)==0) white_score += PieceValues.possibly_dangerous_array_of_scores[(b.board[i]&7)-1];
        }

        return (player==1?1:-1)*(white_score-black_score);
    }
}
