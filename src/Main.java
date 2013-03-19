import shallowbot.Board;
import shallowbot.Move;
import shallowbot.PossibleMoves;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Board b = new Board();
        b.setInitialPosition();

        ArrayList<Move> moves = new ArrayList<Move>();
        PossibleMoves.generatePossibleMoves(b, moves, 1);

        System.out.println(moves);
    }
}
