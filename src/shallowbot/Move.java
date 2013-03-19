package shallowbot;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 10:29 PM
 */
public class Move {
	public int from, to;
    byte promotion;

	public Move(Move o) {
		this.from  = o.from;
		this.to = o.to;
        this.promotion = o.promotion;
	}
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
        this.promotion = PieceLabels.EMPTY;
	}
    public Move(int from, int to, byte promotion) {
        this.from = from;
        this.to = to;
        this.promotion = PieceLabels.EMPTY;
    }
    public Move reverse() {
        return new Move(this.to, this.from, this.promotion);
    }

    @Override
    public String toString() {
        return Board.toStd(this.from)+" "+Board.toStd(this.to);
    }
}
