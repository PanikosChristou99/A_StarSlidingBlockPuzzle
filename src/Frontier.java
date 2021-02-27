

import java.util.HashMap;
import java.util.PriorityQueue;
/**
 * This class represents the frontier of the search tree of A star Algorithm.
 */
public class Frontier {
	
	/**This feature is used to keep truck of the most promessing node*/ 
	private PriorityQueue<AStarNode> pQueue;
	/**This Feature is used to be able ti find at O(1) if a sliding block is in the PriorityQueue*/ 
	HashMap<SlidingBlock, AStarNode> slidingBlocks;
	
	/**
	 Constructor of the Object.
	 */
	public Frontier() {
		this.pQueue= new PriorityQueue<AStarNode>();
		this.slidingBlocks= new HashMap<SlidingBlock, AStarNode>();
	}
	
	/**
	 returns true if the frontier is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.pQueue.isEmpty();
	}
	
	/**
	 returns the node at the top of the Queue, meaning the most promessing one.
	 */
	public AStarNode poll() {
		AStarNode nodeToReturn= this.pQueue.poll();
		this.slidingBlocks.remove(nodeToReturn.slidingBlock);
		return nodeToReturn;		
	}
	
	/**
	Adds the Given Node in the Frontier.
	 */
	public boolean add(AStarNode nodeToAdd) {
		this.slidingBlocks.put(nodeToAdd.slidingBlock, nodeToAdd);
		return this.pQueue.add(nodeToAdd);
		
	}
	
	/**
	 return in O(1) time the pointer to the Node in frontier with the given block.
	 */
	public AStarNode getNodeWithThisSlidingBlock(SlidingBlock block) {
		return this.slidingBlocks.get(block);
	}
	

}
