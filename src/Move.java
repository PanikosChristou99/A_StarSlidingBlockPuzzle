
// a nice way of showing moves and handling them
public class Move {

	int posBefore;
	int posAfter;

	public Move(int posBefore, int posAfter) {
		this.posBefore = posBefore;
		this.posAfter = posAfter;
	}

	public Move() {
		this.posBefore = -1;
		this.posAfter = -1;
	}

	@Override
	public String toString() {
		if (this.posBefore == -1)
			return "initial Block";
		else
			return "Move plate from " + posBefore + " to " + posAfter;
	}

}
