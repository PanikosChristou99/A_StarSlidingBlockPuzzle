
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SlidingBlock {

	protected int length;
	protected char[] blocks;
	protected int emptyLoc;

	public SlidingBlock(int length) {
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

	public SlidingBlock(String line) {
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

	protected SlidingBlock clone(SlidingBlock a) {

		SlidingBlock temp = new SlidingBlock(a.length);

		for (int i = 0; i < a.blocks.length; i++) {
			temp.blocks[i] = a.blocks[i];
		}
		temp.emptyLoc = a.emptyLoc;
		return temp;
	}

	public static boolean isSolved(SlidingBlock wanted) {
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

	public static boolean canMatch(SlidingBlock sb, SlidingBlock wanted) {
		char[] a = sb.blocks.clone();

		char[] b = wanted.blocks.clone();

		Arrays.sort(a);
		Arrays.sort(b);

		System.out.println(sb.blocks);
		System.out.println(a);

		System.out.println(wanted.blocks);
		System.out.println(b);

		for (int i = 0; i < b.length; i++) {
			if (a[i] != b[i])
				return false;

		}
		return true;

	}
	// find [W|W.....W|E|B|B|.....B]
//			 |-------|   |........|
	// numOfW numOfBlack

	public static int closeToSolution(SlidingBlock sb, SlidingBlock generalSolution) {

		String str = new String(generalSolution.blocks);
		int posOfLastWhite = str.lastIndexOf('W');

		int posOfFirstBlack = str.indexOf('B');

		char[] b = sb.blocks;

		int count = 0;

		for (int i = 0; i <= posOfLastWhite; i++) { // the sum of the blacks lefter than fiirs black
			if (b[i] == 'B')
				count += posOfFirstBlack - i;

		}

		if (b[posOfLastWhite + 1] != 'E') // if E is where it ssuppossed to be
			count++;

		for (int i = posOfFirstBlack; i < b.length; i++) { // the sum of the whites righter than last white

			if (b[i] == 'W')
				count += i - posOfLastWhite;
		}
		return count;
	}

	public static int numOfDifferences(SlidingBlock sb, SlidingBlock wanted) {

		int count = 0;

		char[] a = sb.blocks;
		char[] b = wanted.blocks;

		for (int i = 0; i < b.length; i++) {
			if (b[i] != a[i])
				count++;
		}
		return count;
	}

	public static SlidingBlock getGeneralSolution(SlidingBlock sb) {
		char[] a = sb.blocks.clone();

		Arrays.sort(a); // B<E<W

		Collections.reverse(Arrays.asList(a)); // get something like the above
		return new SlidingBlock(new String(a));

	}

	public static int calcCost(SlidingBlock sb, SlidingBlock wanted, SlidingBlock generalSolution) {

		return numOfDifferences(sb, wanted) + closeToSolution(sb, generalSolution);

	}
}
