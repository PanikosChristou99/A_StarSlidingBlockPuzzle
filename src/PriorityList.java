import java.util.LinkedList;

public class PriorityList extends LinkedList<AStarNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1379944506903595845L;

	public boolean add(AStarNode object) {
		for (int i = 0; i < size(); i++) {
			if (object.compareTo(get(i)) <= 0) {
				add(i, object);
				return true;
			}
		}
		addLast(object);
		return true;
	}

}
