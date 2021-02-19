
import java.util.Iterator;
import java.util.Random;

public class slidingBlock {

	protected int length;
	protected char[] blocks;
	protected int emptyLoc;

	public slidingBlock(int length) {
		Random rd = new Random(2);

		this.length = length;

		this.blocks = new char[length];

		for (int i = 0; i < blocks.length; i++) {

			if (rd.nextBoolean()) {
				blocks[i] = 'W';
			} else {
				blocks[i] = 'B';
			}

		}

		int random_integer = rd.nextInt((length - 1) - 0) + 0;

		this.blocks[random_integer] = 'E';
		this.emptyLoc = random_integer;
	}

	public String toStringDetails() {

		return "length is " + this.length + " with blocks " + new String(this.blocks).replaceAll("", "|");
	}

	public String toString() {

		return new String(this.blocks).replaceAll("", "|");
	}

	public void switchBlocks(int a, int b) {

		if (a < 0 || a > this.length || b < 0 || b > this.length)
			return;
		char prev = this.blocks[a];
		this.blocks[a] = this.blocks[b];
		this.blocks[b] = prev;
	}

	protected slidingBlock clone(slidingBlock a) {

		slidingBlock temp = new slidingBlock(a.length);

		for (int i = 0; i < a.blocks.length; i++) {
			temp.blocks[i] = a.blocks[i];
		}
		temp.emptyLoc = a.emptyLoc;
		return temp;
	}
}
