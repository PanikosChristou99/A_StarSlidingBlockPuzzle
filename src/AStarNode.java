import java.util.ArrayList;

public class AStarNode implements Comparable<Object> {
	public static SlidingBlock goalState;
	public static int numberOfAllowedJumps=2;

	AStarNode parentNode;
	int costFromStart;
	int heurCost;
	SlidingBlock slidingBlock;
	Move moveThatGotMeHere;
	
	public int getCost() {
		return costFromStart + heurCost;
	}
	
	public AStarNode(AStarNode parentNode, int costFromStart, SlidingBlock slidingBlock,Move move) {
		super();
		this.parentNode = parentNode;
		this.costFromStart = costFromStart;
		this.heurCost = SlidingBlock.numOfDifferences(slidingBlock,goalState);
		this.slidingBlock = slidingBlock;
		this.moveThatGotMeHere=move;
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
		for(int jump=0; jump<=numberOfAllowedJumps; jump++) {
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