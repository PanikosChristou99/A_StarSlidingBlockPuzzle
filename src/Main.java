import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public Queue<history> solve(slidingBlock sb) {

		Queue<history> q = new LinkedList<history>();

		// todo heuristic maybe how right are the whites from the leftest black?

		//

		return null;
	}

	public static void main(String[] args) {

		slidingBlock s = new slidingBlock(10);

		System.out.println(s + "\n");

		s.switchBlocks(2, 5);
		history h = new history(s, new move(2, 5));

		System.out.println(h);

		System.out.println("----------------------------------------------------");

		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka:");

		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine().toUpperCase();

		checkInput(line);

		slidingBlock sb = new slidingBlock(line);

		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka pou thes na ftasi:");

		line = scan.nextLine().toUpperCase();

		checkInput(line);

		slidingBlock wanted = new slidingBlock(line);

		if (!slidingBlock.isSolved(wanted)) {
			System.out.println("What you  gave me is not solved");
			System.exit(1);
		}

	}

	private static void checkInput(String line) {
		char[] a = line.toCharArray();
		boolean emptyAppeared = false;
		for (int i = 0; i < a.length; i++) {
			char c = a[i];
			if (c == 'E') {
				if (emptyAppeared) {
					System.out.println("More than 1 empty");
					System.exit(1);
				}

				emptyAppeared = true;
			} else if (c != 'W' && c != 'B') {
				System.out.println("Contains something other than W or B");
				System.exit(1);
			}
		}
		if (!emptyAppeared) {
			System.out.println("No empty");
			System.exit(1);
		}
	}

}
