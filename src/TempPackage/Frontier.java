package TempPackage;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Frontier {
	
	private PriorityQueue<AStarNode> pQueue;
	HashMap<SlidingBlock, Integer> slidingBlocks;
	
	
	public Frontier() {
		this.pQueue= new PriorityQueue<AStarNode>();
		this.slidingBlocks= new HashMap<SlidingBlock, Integer>();
	}
	
	public boolean isEmpty() {
		return this.pQueue.isEmpty();
	}
	
	public AStarNode poll() {
		AStarNode nodeToReturn= this.pQueue.poll();
		int countOfSlidingBlock= this.slidingBlocks.get(nodeToReturn.slidingBlock);
		if(countOfSlidingBlock==1) {
			this.slidingBlocks.remove(nodeToReturn.slidingBlock);
		}
		else {
			this.slidingBlocks.replace(nodeToReturn.slidingBlock, countOfSlidingBlock-1);
		}
		return nodeToReturn;		
	}
	
	public boolean add(AStarNode nodeToAdd) {
		if(this.slidingBlocks.containsKey(nodeToAdd.slidingBlock)) {
			int currentCounter=this.slidingBlocks.get(nodeToAdd.slidingBlock);
			this.slidingBlocks.replace(nodeToAdd.slidingBlock,currentCounter+1);
		}
		else {
			this.slidingBlocks.put(nodeToAdd.slidingBlock, 1);
		}
		return this.pQueue.add(nodeToAdd);
		
	}
	
	// maybe needs another datastructure.
	public AStarNode getNodeWithThisSlidingBlock(SlidingBlock block) {
		boolean flag=false;
		for (AStarNode node : this.pQueue) {
			if(node.slidingBlock.equals(block)) {
				return node;
			}
		}
		return null;
	}
	
	

}
