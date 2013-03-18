package shallowbot;

/**
 * User: @LolWolf
 * Project: ShallowBot
 * Date: 3/17/13
 * Time: 10:29 PM
 */
public class Move {
	public int from, to;
	public Move(Move o) {
		this.from  = o.from;
		this.to = o.to;
	}
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
}
