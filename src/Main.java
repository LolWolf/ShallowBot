import shallowbot.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Board b = new Board();
        b.setInitialPosition();


//      b.board[56] = PieceLabels.W_KING;
//	    b.board[0] = PieceLabels.B_QUEEN;
//

//
	    MoveScore ms;

	    for(int i=0; i<15; i++) {
		    ms = NegaMax.negaMaxInit(b, i%2+1, 5);
		    b.makeMove(ms.move);
		    System.out.println(b);
		    System.out.println(ms);
	    }

    }
}
