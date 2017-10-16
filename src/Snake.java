public class Snake {
    private PieceOfSnake tail;
    private PieceOfSnake head;
    private Direction direction;
    private int size;

    Snake(Coordinate coordinate, Field field) {
        this.direction = Direction.Right;
        PieceOfSnake newPiece = new PieceOfSnake(coordinate.getNeighborCoordinate(Direction.Up), field, this.direction, null);
        this.tail = newPiece;
        this.head = newPiece;
        this.size = 1;
        this.push(field);
    }

    public int size() {
        return this.size;
    }

    private void push(Field field) {
        PieceOfSnake newPiece = new PieceOfSnake(
                this.head.coordinate.getNeighborCoordinate(this.direction),
                field, this.direction, this.head);
        this.head = newPiece;
        this.size += 1;
    }
    
    public boolean checkEndGame(Field field) {
    	return ((field.getObjectOnField(this.head.coordinate.getNeighborCoordinate(Direction.Up)) instanceof ImpenetrableObject) && 
		    	(field.getObjectOnField(this.head.coordinate.getNeighborCoordinate(Direction.Down)) instanceof ImpenetrableObject) && 
		    	(field.getObjectOnField(this.head.coordinate.getNeighborCoordinate(Direction.Left)) instanceof ImpenetrableObject) && 
		    	(field.getObjectOnField(this.head.coordinate.getNeighborCoordinate(Direction.Right)) instanceof ImpenetrableObject));
    }

    private void pop(Field field) {
        this.tail.nextPiece.lastPiece = null;
        field.deleteObjectOnField(this.tail.coordinate);
        this.tail = this.tail.nextPiece;
        this.size -= 1;
    }

    private ObjectOnField getNeighborObject(Field field) {
        return field.getObjectOnField(this.head.coordinate.getNeighborCoordinate(this.direction));
    }

    public boolean isInFrontWall(Field field) {
        return this.getNeighborObject(field) instanceof ImpenetrableObject;
    }

    public void move(Field field) {
    	if (this.isInFrontWall(field))
    		return;
    	boolean needToGrow = this.getNeighborObject(field) instanceof Apple;
        this.push(field);
        if (!(needToGrow)) {
            this.pop(field);
        }
    }
    
    public void setDirection(Direction direction) {
    	if (this.direction.ordinal() + direction.ordinal() != 5)
    		this.direction = direction;
    }

}
