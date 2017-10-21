public abstract class ObjectOnField {
	protected Coordinate coordinate;
	
	ObjectOnField(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

    protected int getX() {
	    return this.coordinate.x;
	}
    protected int getY() {
	    return this.coordinate.y;
	}

    public abstract void toInteractWithSnake(Snake snake, Field field);
    
	public String nameOfTheObject() {
		return this.getClass().getName();
	}
}
