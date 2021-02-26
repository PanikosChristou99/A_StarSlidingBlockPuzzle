import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	public static String findPath (AStarNode finalNode) {
		Stack<String> stack = new Stack<String>();
		AStarNode node=finalNode;
		while(node!=null) {
			stack.push(node.slidingBlock.toString());
			stack.push(node.moveThatGotMeHere.toString());
			node=node.parentNode;
		}
		String path="";
		while(stack.isEmpty()==false) {
			path=path+stack.pop()+"\n";
		}
		return path;
	}

	public static String solve(SlidingBlock initialSlidingBlock, SlidingBlock wanted) {
		AStarNode.goalState=wanted;

//		Queue<HistoryState> history = new LinkedList<HistoryState>();
		AStarNode finalNode=null;

		AStarNode startNode = new AStarNode(null,0,initialSlidingBlock, new Move());
        Frontier frontier = new Frontier();
        HashMap<SlidingBlock, Integer> exploredBlocks = new HashMap<>();

        frontier.add(startNode);
        while(!frontier.isEmpty()) {
        	AStarNode currentNode= frontier.poll();
        	if(currentNode.isEndState()) {
        		finalNode=currentNode;
        		break;
//        		return history;
        	}
        	exploredBlocks.put(currentNode.slidingBlock,currentNode.costFromStart);
        	ArrayList<AStarNode> childrenBlocks= currentNode.getChildren();
        	for(AStarNode child: childrenBlocks) {
        		AStarNode frontierNodeWithSameSlidingBlock=frontier.getNodeWithThisSlidingBlock(child.slidingBlock);
        		if(!exploredBlocks.containsKey(child.slidingBlock) ||
        			(frontierNodeWithSameSlidingBlock==null)){
        			frontier.add(child);
        		}
        		else if(frontierNodeWithSameSlidingBlock!=null) {
        			frontierNodeWithSameSlidingBlock.parentNode=child.parentNode;
        			frontierNodeWithSameSlidingBlock.costFromStart=child.costFromStart;
        			frontierNodeWithSameSlidingBlock.heurCost=child.heurCost;
        			frontierNodeWithSameSlidingBlock.moveThatGotMeHere=child.moveThatGotMeHere;
        		}
        	}
        }
        if(finalNode==null) {
        	System.out.println("i found no path");
        	return null;
        }
        else {
        	return findPath(finalNode);
//        	return history;
        }
	}

	public static void main(String[] args) {
		//start of real program
		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka:");

		Scanner scan = new Scanner(System.in);
//		String line = scan.nextLine().toUpperCase();
		String line = "BWWBEBBW";

		checkInput(line);

		SlidingBlock sb = new SlidingBlock(line);

		System.out.println("Dose m se morfi WWBBEBBW px ton pinaka pou thes na ftasi:");
//WWWBEBB
//		line = scan.nextLine().toUpperCase();
		line = "WWWBBEBB";
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

		String path= solve(sb, wanted);
		System.out.println(path);
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
