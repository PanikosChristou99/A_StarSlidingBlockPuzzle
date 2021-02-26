

public class HistoryState {

	protected SlidingBlock sb;
	protected Move m;

	public HistoryState(SlidingBlock sb, Move m) {
		super();
		this.sb = sb;
		this.m = m;
	}

	@Override
	public String toString() {
		return "the move was : \n" + m + "\nand the new block is: \n" + sb;
	}

}
