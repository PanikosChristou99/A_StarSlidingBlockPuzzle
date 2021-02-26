package TempPackage;

import java.util.ArrayList;

public class AStarNode implements Comparable<Object> {
	public static SlidingBlock goalState;
	public static int numberOfAllowedJumps;

	AStarNode parentNode;
	int costFromStart;
	int heurCost;
	SlidingBlock slidingBlock;

	public int getCost() {
		return costFromStart + heurCost;
	}
	
	public AStarNode(AStarNode parentNode, int costFromStart, SlidingBlock slidingBlock) {
		super();
		this.parentNode = parentNode;
		this.costFromStart = costFromStart;
		this.heurCost = SlidingBlock.numOfDifferences(slidingBlock,goalState);
		this.slidingBlock = slidingBlock;
	}
	



	@Override
	public String toString() {
		return "AStarNode [parentNode=" + parentNode + ", costFromStart=" + costFromStart + ", heurCost=" + heurCost
				+ ", slidingBlock=" + slidingBlock + "]";
	}


	/** Make sure that you have to use both variables ,g(x) and h(x) here.*/ 
	public int compareTo(Object other) {
		int thisValue = this.getCost();
		int otherValue = ((AStarNode) other).getCost();

		int v = thisValue - otherValue;
		return (v > 0) ? 1 : (v < 0) ? -1 : 0; // sign function
	}
	
	public boolean isEndState() {
		return SlidingBlock.isSolved(this.slidingBlock);
	}
	
	public ArrayList<AStarNode> getChildren(){
		//TODO create children.
		ArrayList<AStarNode> children =new ArrayList<AStarNode>();
		for(int jump=1; jump<=numberOfAllowedJumps+1; jump++) {
			SlidingBlock newBlock = SlidingBlock.copy(this.slidingBlock);
			int aristera=this.slidingBlock.emptyLoc-1-jump;
			int deksia=this.slidingBlock.emptyLoc+1+jump;
			if(newBlock.switchBlocks(newBlock.emptyLoc, aristera)) {
				AStarNode child = new AStarNode(this, this.costFromStart+ jump+1, newBlock);
				children.add(child);
			}
			if(newBlock.switchBlocks(newBlock.emptyLoc, deksia)) {
				AStarNode child = new AStarNode(this, this.costFromStart+ jump+1, newBlock);
				children.add(child);
			}
		}
		return children;
	}

}