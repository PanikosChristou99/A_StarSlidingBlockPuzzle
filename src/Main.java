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


	/**
	 * This function helps us trace back the path from the final node 
	 * to the initial node.
	 * 
	 * Basically it puts in a stack the sliding block and 
	 * then the move that changed the block into that one
	 * from the final node up to the initial node.   
	 * 
	 * @param AStarNode finalNode, this is the final Node
	 * @return returns the path in string format.
	 */
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
	
	/**
	 * This function finds the path from the initial Block to the desired block. 
	 * 
	 * This function implements the A star algorithm to find the path from the initial block 
	 * to the desired block. 
	 * 
	 * @param SlidingBlock initialSlidingBlock, this is the initial Block.
	 * @param SlidingBlock desiredBlock, this is the desired Block.
	 * @return returns the path in string format.
	 */
	public static String solve(SlidingBlock initialSlidingBlock, SlidingBlock desiredBlock) {
		AStarNode.goalState=desiredBlock; // start by setting the goal state. 

		AStarNode finalNode=null;
		AStarNode startNode = new AStarNode(null,0,initialSlidingBlock, new Move());
        Frontier frontier = new Frontier(); // this is the frontier. Here the soon to be explored nodes will wait. 
        HashMap<SlidingBlock, Integer> exploredBlocks = new HashMap<>(); //Used to avoid cycles.

        //put initial node in the froniter.
        frontier.add(startNode);
        while(!frontier.isEmpty()) {
        	//get most promessing node from frontier. 
        	AStarNode currentNode= frontier.poll();
        	// check to see if it a the goal state.
        	if(currentNode.isEndState()) {
        		// if it is then great. No need to see other nodes since 					F(n)= h(n)+ g(n) where h(n) is the heuristic function
        		// A star expands the node n with the  F(n)	and since h(n)					and g(n) is the path cost. 
        		// here is 0 this means that this g(n) must be minimal. 
        		finalNode=currentNode;
        		break;
        	}
        	//is it isn't then we set this block as explored.
        	exploredBlocks.put(currentNode.slidingBlock,currentNode.costFromStart);
        	// find the children of this block.
        	ArrayList<AStarNode> childrenBlocks= currentNode.getChildren();
        	for(AStarNode child: childrenBlocks) {
        		//for each child check to see if there is another
        		//node in the frontier with the same Sliding block
        		AStarNode frontierNodeWithSameSlidingBlock=frontier.getNodeWithThisSlidingBlock(child.slidingBlock);
        		if(!exploredBlocks.containsKey(child.slidingBlock) ||
        			(frontierNodeWithSameSlidingBlock==null)){
        			//if there is not any node with this sliding block and
        			//this block is not expored then
        			frontier.add(child);
        		}
        		else if(frontierNodeWithSameSlidingBlock!=null) {
        			//if is it not expored but there is a node with this sliding block then we
        			//just set the node in the frontier to be the same as this node.
        			frontierNodeWithSameSlidingBlock.parentNode=child.parentNode;
        			frontierNodeWithSameSlidingBlock.costFromStart=child.costFromStart;
        			frontierNodeWithSameSlidingBlock.heuristicCost=child.heuristicCost;
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
