
public class AStarNode implements Comparable<Object> {

	AStarNode pathParent;
	int costFromStart;
	int heurCost;
	SlidingBlock currBlock;

	public int getCost() {
		return costFromStart + heurCost;
	}
	
	public AStarNode(AStarNode pathParent, int costFromStart, int heurCost, SlidingBlock currBlock) {
		super();
		this.pathParent = pathParent;
		this.costFromStart = costFromStart;
		this.heurCost = heurCost;
		this.currBlock = currBlock;
	}



	@Override
	public String toString() {
		return "AStarNode [pathParent=" + pathParent + ", costFromStart=" + costFromStart + ", heurCost=" + heurCost
				+ ", currBlock=" + currBlock + "]";
	}



	public int compareTo(Object other) {
		int thisValue = this.getCost();
		int otherValue = ((AStarNode) other).getCost();

		int v = thisValue - otherValue;
		return (v > 0) ? 1 : (v < 0) ? -1 : 0; // sign function
	}

}