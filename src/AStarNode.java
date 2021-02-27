import java.util.ArrayList;

/**
 * This class represents a node in the search tree of the A Star Algorithm.
 */
public class AStarNode implements Comparable<Object> {
	public static SlidingBlock goalState;
	public static int numberOfAllowedJumps=2;

	AStarNode parentNode;
	int costFromStart;
	int heuristicCost;
	SlidingBlock slidingBlock;
	Move moveThatGotMeHere;
	
	public int getCost() {
		return costFromStart + heuristicCost;
	}
	
	/**
	 * Constructor for this Object. 
	 * 
	 * @param AStarNode parentNode this is a pointer to its parent node
	 * @param int costFromStart
	 * @param SlidingBlock slidingBlock the current block
	 * @param Move move the move that got here from the previous block.
	 */
	public AStarNode(AStarNode parentNode, int costFromStart, SlidingBlock slidingBlock,Move move) {
		super();
		this.parentNode = parentNode;
		this.costFromStart = costFromStart;
		this.heuristicCost = SlidingBlock.numOfDifferences(slidingBlock,goalState);
		this.slidingBlock = slidingBlock;
		this.moveThatGotMeHere=move;
	}
	



	@Override
	public String toString() {
		return "AStarNode [parentNode=" + parentNode + ", costFromStart=" + costFromStart + ", heuristicCost=" + heuristicCost
				+ ", slidingBlock=" + slidingBlock + "]";
	}


	/** 
	 * Needed for the Priority Queue.
	 * The Metric used to find the most promising Node is F(n) =g(n) +h(n)
	 * = the cost to get here from initial  block+ the heuristic function.
	 * */ 
	public int compareTo(Object other) {
		int thisValue = this.getCost();
		int otherValue = ((AStarNode) other).getCost();

		int v = thisValue - otherValue;
		return (v > 0) ? 1 : (v < 0) ? -1 : 0; // sign function
	}
	
	/** 
	 * returns true if this is the goal state, false otherwise.
	 * */ 
	public boolean isEndState() {
		return SlidingBlock.isSolved(this.slidingBlock);
	}
	
	
	/** 
	 * This functions creates and returns a list of the children of the current node.
	 * Children are all the blocks that can be generated through a valid action of the problem in the current block.
	 * */ 
	public ArrayList<AStarNode> getChildren(){
		ArrayList<AStarNode> children =new ArrayList<AStarNode>();
		for(int jump=0; jump<=numberOfAllowedJumps; jump++) {
			//Clone returns a deep copy. 
			//here i add (if it is legal) the child for for the move on the right and the left.
			SlidingBlock newBlock = SlidingBlock.clone(this.slidingBlock);
			int aristera=this.slidingBlock.emptyLoc-1-jump;
			int deksia=this.slidingBlock.emptyLoc+1+jump;
			if(newBlock.switchBlocks(newBlock.emptyLoc, aristera)) {
				newBlock.emptyLoc=aristera;
				AStarNode child = new AStarNode(this, this.costFromStart+ jump+1, newBlock, new Move(aristera,this.slidingBlock.emptyLoc));
				children.add(child);
			}
			newBlock=SlidingBlock.clone(this.slidingBlock);
			if(newBlock.switchBlocks(newBlock.emptyLoc, deksia)) {
				newBlock.emptyLoc=deksia;
				AStarNode child = new AStarNode(this, this.costFromStart+ jump+1, newBlock,new Move(deksia,this.slidingBlock.emptyLoc));
				children.add(child);
			}
		}
		return children;
	}

}