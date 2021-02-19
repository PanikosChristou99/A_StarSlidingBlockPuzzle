
// a nice way of showing a change and printing it
public class history {

	protected slidingBlock sb;
	protected move m;

	public history(slidingBlock sb, move m) {
		super();
		this.sb = sb;
		this.m = m;
	}

	@Override
	public String toString() {
		return "the move was : \n" + m + "\nand the new block is: \n" + sb;
	}

}
