package shallowbot;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/20/13
 * Time: 10:31 PM
 */
public class MoveScore {
	public int score;
	public Move move;

	public MoveScore() {
		this.score = -Constants.INFTY;
		this.move = null;
	}
	public MoveScore(Move move, int score) {
		this.move = move;
		this.score = score;
	}

	@Override
	public String toString() {
		return this.move.toString()+" with score "+this.score;
	}
}
