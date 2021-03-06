import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SlidingBlock {

	protected int length;
	protected char[] blocks = new char[length];
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

	public boolean switchBlocks(int a, int b) {

		if (a < 0 || a >= this.length || b < 0 || b >= this.length)
			return false;
		char prev = this.blocks[a];
		this.blocks[a] = this.blocks[b];
		this.blocks[b] = prev;
		return true;
	}

	protected static SlidingBlock clone(SlidingBlock a) {

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

	public static int howCloseToSolution(SlidingBlock sb, SlidingBlock generalSolution) {

		String str = new String(generalSolution.blocks);
		int posOfLastWhite = str.lastIndexOf('W');

		int posOfFirstBlack = str.indexOf('B');

		char[] b = sb.blocks;
//WWWWWEBBB  BBWWEBWWW
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

		if (isSolved(sb)) {
			// just in case we reach a solution, and the E is further than 2 blocks, then we
			// can know how far E is from its desired place
			int posOfEmpty1 = (new String(sb.blocks)).indexOf('E');
			int posOfEmpty2 = (new String(sb.blocks)).indexOf('E');

			count += Math.abs(posOfEmpty1 - posOfEmpty2);
		}
		return count;
	}

	/**what is this? */ 
	public static SlidingBlock getGeneralSolution(SlidingBlock sb) {
		char[] a = sb.blocks.clone();

		Arrays.sort(a); // B<E<W

		Collections.reverse(Arrays.asList(a)); // get something like the above
		return new SlidingBlock(new String(a));

	}

	public static int calcCost(SlidingBlock sb, SlidingBlock wanted, SlidingBlock generalSolution) {

		return numOfDifferences(sb, wanted) + howCloseToSolution(sb, generalSolution);

	}
	
	public static SlidingBlock copy(SlidingBlock original) {
		return null;
	}
	public static ArrayList<SlidingBlock> getMoves(SlidingBlock slidingBlock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(blocks);
		result = prime * result + emptyLoc;
		result = prime * result + length;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlidingBlock other = (SlidingBlock) obj;
		if (!Arrays.equals(blocks, other.blocks))
			return false;
		if (emptyLoc != other.emptyLoc)
			return false;
		if (length != other.length)
			return false;
		return true;
	}
	

	
}
