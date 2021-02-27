

import java.util.HashMap;
import java.util.PriorityQueue;

public class Frontier {
	private PriorityQueue<AStarNode> pQueue;
	HashMap<SlidingBlock, AStarNode> slidingBlocks;
	
	
	public Frontier() {
		this.pQueue= new PriorityQueue<AStarNode>();
		this.slidingBlocks= new HashMap<SlidingBlock, AStarNode>();
	}
	
	public boolean isEmpty() {
		return this.pQueue.isEmpty();
	}
	
	public AStarNode poll() {
		AStarNode nodeToReturn= this.pQueue.poll();
		this.slidingBlocks.remove(nodeToReturn.slidingBlock);
		return nodeToReturn;		
	}
	
	public boolean add(AStarNode nodeToAdd) {
		this.slidingBlocks.put(nodeToAdd.slidingBlock, nodeToAdd);
		return this.pQueue.add(nodeToAdd);
		
	}
	
	// maybe needs another datastructure.
	public AStarNode getNodeWithThisSlidingBlock(SlidingBlock block) {
		return this.slidingBlocks.get(block);
	}
	

}
