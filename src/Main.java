import shallowbot.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Board b = new Board();
//        b.board[56] = PieceLabels.W_KING;
//	    b.board[0] = PieceLabels.B_QUEEN;
//
//	    ArrayList<Move> checkArr = new ArrayList<Move>();
//
//	    PossibleMoves.generatePossibleMoves(b, checkArr, 1);
//	    System.out.println(checkArr);

	    b.setInitialPosition();

	    MoveScore ms = NegaMax.negaMaxInit(b, 1, 7);
	    System.out.println(ms);
	    b.makeMove(ms.move);
	    ms = NegaMax.negaMaxInit(b, 2, 7);
	    System.out.println(ms);
	    b.makeMove(ms.move);
	    ms = NegaMax.negaMaxInit(b, 1, 7);
	    System.out.println(ms);
	    b.makeMove(ms.move);
	    ms = NegaMax.negaMaxInit(b, 2, 7);
	    System.out.println(ms);
    }
}
