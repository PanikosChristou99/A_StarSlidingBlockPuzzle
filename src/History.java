
// a nice way of showing a change and printing it
public class History {

	protected SlidingBlock sb;
	protected Move m;

	public History(SlidingBlock sb, Move m) {
		super();
		this.sb = sb;
		this.m = m;
	}

	@Override
	public String toString() {
		return "the move was : \n" + m + "\nand the new block is: \n" + sb;
	}

}
