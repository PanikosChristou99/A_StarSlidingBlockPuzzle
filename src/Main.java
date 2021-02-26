import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static Queue<History> solve(SlidingBlock sb, SlidingBlock wanted) {

		Queue<History> q = new LinkedList<History>();

		SlidingBlock sol = SlidingBlock.getGeneralSolution(sb);
		int heurCost = SlidingBlock.calcCost(sb, wanted, sol);
		
		AStarNode start = new AStarNode(null,0,heurCost,sb);
		
		
		// todo heuristic maybe how right are the whites from the leftest black?

		//

		return null;
	}

	public static void main(String[] args) {

		//check the below to see how it works
		SlidingBlock s = new SlidingBlock(10);

		System.out.println(s + "\n");

		s.switchBlocks(2, 5);
		
		
		History h = new History(s, new Move(2, 5));

		System.out.println(h);
		
		System.out.println();
		System.out.println("----------------------------------------------------");
		//start of real program
		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka:");

		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine().toUpperCase();

		checkInput(line);

		SlidingBlock sb = new SlidingBlock(line);

		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka pou thes na ftasi:");
//WWWBEBB
		line = scan.nextLine().toUpperCase();
		scan.close();
		checkInput(line);

		SlidingBlock wanted = new SlidingBlock(line);

		if (!SlidingBlock.isSolved(wanted)) {
			System.out.println("What you  gave me is not solved");
			System.exit(1);
		}

		if (!SlidingBlock.canMatch(sb,wanted)) {
			System.out.println("What you  gave me cannot become the other");
			System.exit(1);
		}
		
		Queue<History> q = solve(sb, wanted);
		
		for (History History : q) {
			System.out.println(History);
		}
	}

	//check if input in only Black and white and EMpty with only 1 empty
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
			System.out.println("No empty in your puzzle");
			System.exit(1);
		}
	}

}
