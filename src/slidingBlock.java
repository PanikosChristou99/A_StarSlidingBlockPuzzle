
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

	public slidingBlock(String line) {
		this.blocks = line.toCharArray();
		this.length = line.length();
		this.emptyLoc = line.indexOf('E');

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

	public static boolean isSolved(slidingBlock wanted) {
		int locOfRightestWhite = -1;
		boolean foundBlack = false;
		int locOfLeftistBlack = -1;

		char[] ar = wanted.blocks;

		for (int i = 0; i < ar.length; i++) {
			char c = ar[i];

			switch (c) {
			case 'W': {

				if (foundBlack)
					return false;
				locOfRightestWhite = i;
				break;
			}
			case 'B': {

				if (foundBlack)
					continue;
				else {
					locOfLeftistBlack = i;
					foundBlack = true;
				}
				break;
			}

			default: {
			}
			}
		}

		if (locOfLeftistBlack < locOfRightestWhite)
			return false;
		return true;
	}
}
