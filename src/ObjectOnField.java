public class ObjectOnField {
	protected Coordinate coordinate;

	ObjectOnField(Coordinate coordinate, Field field) {
		this.coordinate = coordinate;
		field.AddObjectOnField(this);
	}

    protected int getX() {
	    return this.coordinate.x;
	}
    protected int getY() {
	    return this.coordinate.y;
	}


}
