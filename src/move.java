
public class move {

	int posBefore;
	int posAfter;

	public move(int posBefore, int posAfter) {
		super();
		this.posBefore = posBefore;
		this.posAfter = posAfter;
	}

	@Override
	public String toString() {
		return "tile " + posBefore + " to " + posAfter;
	}

}
