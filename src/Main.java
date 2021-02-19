import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public Queue<history> solve(slidingBlock sb) {

		Queue<history> q = new LinkedList<history>();
		
		//todo
		
		return null;
	}

	public static void main(String[] args) {

		slidingBlock s = new slidingBlock(10);

		System.out.println(s + "\n");

		s.switchBlocks(2, 5);
		history h = new history(s, new move(2, 5));

		System.out.println(h);

	}
}
